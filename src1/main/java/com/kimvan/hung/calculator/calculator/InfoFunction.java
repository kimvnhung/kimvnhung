package com.kimvan.hung.calculator.calculator;

import android.util.Log;

import com.kimvan.hung.calculator.ViewerScreenFragment;

import java.util.ArrayList;


public class InfoFunction {
    //save the content function
    public static ArrayList<String> function = new ArrayList<>();


    public static ArrayList<String> changeTheElementOfIdx(int idx,String s,ArrayList<String> res){
        ArrayList<String> result = res;
        try{
            result.remove(idx);
            result.add(idx,s);
        }catch(Exception e){
            result.add(s);
        }
        return result;
    }
    public static void textIntoArrayList(String s){
        ArrayList<String> result = new ArrayList<>();
        String currentText ="";
        for (int i=0;i<s.length();i++){
            currentText+=s.charAt(i)+"";
            try {

                Double.parseDouble(currentText);
                if(currentText.length()==1){
                    result.add(currentText);
                }else{
                    result=changeTheElementOfIdx(result.size()-1,currentText,result);
                }
            }catch (Exception e){
                currentText="";
                result.add(s.charAt(i)+"");
            }
        }
        InfoFunction.function=result;
    }


    public static String currentText="";
    public static boolean isEquals = false;
    public static String answer="";
}
