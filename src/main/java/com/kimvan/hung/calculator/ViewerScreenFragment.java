package com.kimvan.hung.calculator;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.media.VolumeProviderCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.kimvan.hung.calculator.calculator.Handler;
import com.kimvan.hung.calculator.calculator.InfoFunction;

import java.util.ArrayList;

public class ViewerScreenFragment extends Fragment{

    @Nullable
    private static EditText contentFuction;
    private static TextView equalsText;


    public static final String tag = "somethingWrong";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewer_screen_fragment,container,false);
        contentFuction = (EditText) view.findViewById(R.id.content_function);
        equalsText = (TextView) view.findViewById(R.id.equals_text);
        return view;
    }
    // input data
    public void nextElements(String s){
        if (InfoFunction.isEquals){
            if (contentFuction.getSelectionStart() != contentFuction.length()){
                InfoFunction.isEquals = false;
            }else {
                setAfterEquals();
                InfoFunction.function.add(InfoFunction.answer);
                setContentFuction();
                contentFuction.setSelection(contentFuction.length());
            }
            try {
                Integer.parseInt(s);
                setNull();
            }catch (Exception e){/* do nothing*/}
            nextElements(s);
        }else {
            boolean isChanged = true;
            int cursorPosition = contentFuction.getSelectionStart();
            StringBuilder contentFuctionText = new StringBuilder(contentFuction.getText().toString());

            //lấy vị trí con trỏ trong InfoFunction.function
            int count =0,functionCurIdx=0;
            for (int function_i=0;function_i<InfoFunction.function.size();function_i++){
                count+=InfoFunction.function.get(function_i).length();
                if (count>=cursorPosition){
                    functionCurIdx=function_i;
                    Log.i(ViewerScreenFragment.tag,String.valueOf(function_i));
                    break;
                }
            }

            if (s.equals(".")){
                String element = InfoFunction.function.get(functionCurIdx);
                for (int e=0;e<element.length();e++){
                    if ((element.charAt(e)+"").equals(".")){
                        isChanged=false;
                        break;
                    }
                    if (e==element.length()-1){
                        contentFuctionText.insert(cursorPosition,s);
                    }
                }
            }else {
                // when s is difference with "."
                try {
                    //when s is number
                    Integer.parseInt(s);
                    contentFuctionText.insert(cursorPosition,s);
                }catch (Exception e){
                    //when s is not number
                    if(InfoFunction.isEquals){
                        clear();
                        InfoFunction.function.add(s);
                        setContentFuction();
                    }else{
                        //when not clicked equals yet
                        if(contentFuctionText.length()>0 ){
                            if ((contentFuctionText.charAt(cursorPosition-1)+"").equals(".")){
                                // if before s is "."
                                contentFuctionText.insert(cursorPosition,s);
                            }else {
                                // if before s is not "."
                                try {
                                    //when before s is number or )
                                    Integer.parseInt(contentFuctionText.charAt(cursorPosition-1)+"");
                                    contentFuctionText.insert(cursorPosition,s);
                                }catch (Exception e1){
                                    // before s is not number
                                    if (s.equals("(") || s.equals(")")){
                                        //s is ( or )
                                        if (s.equals("(")){
                                            contentFuctionText.insert(cursorPosition,s);
                                        }else {/* do nothing*/         }
                                    }else {
                                        contentFuctionText.replace(cursorPosition-1,cursorPosition,s);
                                        isChanged=false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            InfoFunction.textIntoArrayList(contentFuctionText.toString());
            setContentFuction();
            if (isChanged){
                contentFuction.setSelection(cursorPosition+1);
            }else {
                contentFuction.setSelection(cursorPosition);
            }
        }

    }

    //Delete all ArrayList function
    public void setNull(){
        InfoFunction.function.clear();
        setContentFuction();

        InfoFunction.currentText="";
        equalsText.setText("");
        InfoFunction.isEquals=false;

    }
    // set up view screen after click equals
    private void setAfterEquals(){
        InfoFunction.answer = equalsText.getText().toString();
        setNull();

    }
    //change the last elements

    //set screen function text
    private void setContentFuction(){
        String allFunction = "";
        for(String x:InfoFunction.function){
            allFunction+=x;
        }
        contentFuction.setText(allFunction);
    }

    //clear one by one
    public void clear(){
        if (InfoFunction.isEquals){
            if (contentFuction.getSelectionStart() != contentFuction.length()){
                InfoFunction.isEquals = false;
                clear();
            }else {
                setAfterEquals();
                InfoFunction.function.add(InfoFunction.answer);
                setContentFuction();
                contentFuction.setSelection(contentFuction.length());
            }
        }else{
            try {
                int cursorPosition = contentFuction.getSelectionStart();
                int count =0,functionCurIdx=0;
                for (int function_i=0;function_i<InfoFunction.function.size();function_i++){
                    count+=InfoFunction.function.get(function_i).length();
                    if (count>=cursorPosition){
                        functionCurIdx=function_i;
                        break;
                    }
                }
                String para = InfoFunction.function.get(functionCurIdx);
                StringBuilder result=new StringBuilder();
                for(int i=0;i<para.length();i++){
                    if (i== cursorPosition-count+InfoFunction.function.get(functionCurIdx).length()-1)
                        continue;
                    result.append(para.charAt(i));
                }
                if(result.equals("")){
                    InfoFunction.function.remove(functionCurIdx);
                }else {
                    InfoFunction.function = InfoFunction.changeTheElementOfIdx(functionCurIdx,result.toString(),InfoFunction.function);
                }
                InfoFunction.currentText = result.toString();
                setContentFuction();
                contentFuction.setSelection(cursorPosition-1);
            }catch (Exception e){/*do nothing*/}
        }
    }
    //perform equals
    public void equalsFunction(){
        if(InfoFunction.isEquals){

        }
        blanceParenthesis(contentFuction.getText().toString());
        Handler handler = new Handler(InfoFunction.function);
        boolean isExistParenthesis = false;
        do {
            for (int function_i=0;function_i<InfoFunction.function.size();function_i++){
                if (InfoFunction.function.get(function_i).equals("(")){
                    isExistParenthesis = true;
                    Log.i(ViewerScreenFragment.tag,"isExistParenthesis is true");
                    break;
                }
            }
            if (isExistParenthesis){
                Log.i(ViewerScreenFragment.tag,"call repalceParenthesis");
                handler = new Handler(handler.replaceParenthesis());

            }
        }
        while (isExistParenthesis);
        equalsText.setText(handler.getResult());
        InfoFunction.isEquals=true;

    }

    //blance the Parenthesis
    private void blanceParenthesis(String s){
        int isBlance =0;
        String contentFunctionText = s;
        for (int i=0;i<contentFunctionText.length();i++){
            if ((contentFunctionText.charAt(i)+"").equals("(")){
                isBlance++;
            }
            if ((contentFunctionText.charAt(i)+"").equals(")")){
                isBlance--;
            }
        }

        if (isBlance!=0){
            Log.i(ViewerScreenFragment.tag,String.valueOf(isBlance));
            if (isBlance<0){
                equalsText.setText("Sai định dạng");
            }else {
                while (isBlance--!=0){
                    contentFunctionText+=")";
                }
                Log.i(ViewerScreenFragment.tag,contentFunctionText);
                InfoFunction.textIntoArrayList(contentFunctionText);
            }
        }
    }
    //get content function
    public EditText getContentFunction(){
        return this.contentFuction;
    }

}
