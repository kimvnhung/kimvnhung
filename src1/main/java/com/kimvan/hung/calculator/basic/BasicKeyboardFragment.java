package com.kimvan.hung.calculator.basic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kimvan.hung.calculator.R;
import com.kimvan.hung.calculator.ViewerScreenFragment;
import com.kimvan.hung.calculator.calculator.InfoFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BasicKeyboardFragment extends Fragment {

    public BasicKeyboardFragment() {
    }

    Map<Integer,String> numberText= new HashMap<>();

    public void setMap(){
        for(int i=0;i<10;i++){
            numberText.put(i,""+i);
        }
    }

    BasicKeyboardFragmentListenner activityCommander;

    public interface BasicKeyboardFragmentListenner{
        public void getTextFromKeyboard(String s);
        public void clear();
        public void equalsFunction();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (BasicKeyboardFragmentListenner) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_keyboard_fragment,container,false);

        setMap();
        //Tạo OnClick number
        final Button[] numButton = new Button[10];
        numButton[0] = (Button) view.findViewById(R.id.zero_button);
        numButton[1] = (Button) view.findViewById(R.id.one_button);
        numButton[2] = (Button) view.findViewById(R.id.two_button);
        numButton[3] = (Button) view.findViewById(R.id.three_button);
        numButton[4] = (Button) view.findViewById(R.id.four_button);
        numButton[5] = (Button) view.findViewById(R.id.five_button);
        numButton[6] = (Button) view.findViewById(R.id.six_button);
        numButton[7] = (Button) view.findViewById(R.id.seven_button);
        numButton[8] = (Button) view.findViewById(R.id.eight_button);
        numButton[9] = (Button) view.findViewById(R.id.nine_button);
        numButton[0].setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(numberText.get(0));
                    }
                }
        );
        numButton[1].setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(numberText.get(1));
                    }
                }
        );
        numButton[2].setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(numberText.get(2));
                    }
                }
        );
        numButton[3].setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(numberText.get(3));
                    }
                }
        );
        numButton[4].setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(numberText.get(4));
                    }
                }
        );
        numButton[5].setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(numberText.get(5));
                    }
                }
        );
        numButton[6].setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(numberText.get(6));
                    }
                }
        );
        numButton[7].setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(numberText.get(7));
                    }
                }
        );
        numButton[8].setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(numberText.get(8));
                    }
                }
        );
        numButton[9].setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(numberText.get(9));
                    }
                }
        );
        //Tao OnClick khác
        final Button clearAll = (Button) view.findViewById(R.id.clear_all);
        clearAll.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked("");
                    }
                }
        );
        final Button clear = (Button) view.findViewById(R.id.clear_button);
        clear.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        activityCommander.clear();
                    }
                }
        );
        final Button sum = (Button) view.findViewById(R.id.sum_button);
        sum.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked("+");
                    }
                }
        );
        final Button subtract = (Button) view.findViewById(R.id.subtract_button);
        subtract.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked("-");
                    }
                }
        );
        final Button multilition = (Button) view.findViewById(R.id.multi_button);
        multilition.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked("x");
                    }
                }
        );
        final Button divide = (Button) view.findViewById(R.id.divide_button);
        divide.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(":");
                    }
                }
        );
        final Button point = (Button) view.findViewById(R.id.point_button);
        point.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(".");
                    }
                }
        );
        final Button equalButton = (Button) view.findViewById(R.id.equal_button);
        equalButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        activityCommander.equalsFunction();
                    }
                }
        );
        final Button openParenthesisButton = (Button) view.findViewById(R.id.open_parenthesis_button);
        openParenthesisButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked("(");
                    }
                }
        );
        final Button closeParenthesisButton = (Button) view.findViewById(R.id.close_parenthesis_button);
        closeParenthesisButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(")");
                    }
                }
        );
        return view;
    }
    public void buttonClicked(String content){
        activityCommander.getTextFromKeyboard(content);

    }
}
