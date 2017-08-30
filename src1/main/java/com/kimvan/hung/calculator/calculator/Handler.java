package com.kimvan.hung.calculator.calculator;

import android.util.Log;

import com.kimvan.hung.calculator.ViewerScreenFragment;

import java.util.ArrayList;

/**
 * Created by h on 19/07/2017.
 */

public class Handler {
    public Handler(ArrayList<String> function){
        this.function=function;
    }

    //handle input text
    private  ArrayList<String> function ;
    private ArrayList<ArrayList<String>> groupAddSubtract = new ArrayList<>();

    // get sub ArrayList
    public ArrayList<String> subFunctionArrayList(int fromIdx,int toIdx){
        ArrayList<String> result = new ArrayList<>();
        for (int i=fromIdx;i<=toIdx;i++){
            result.add(function.get(i));
        }
        return result;
    }
    private ArrayList<String> subFunctionArrayList(int idx){
        ArrayList<String> result = new ArrayList<>();
        result.add(function.get(idx));
        return result;
    }

    //divide content Function string into units
    public void divideUnit(String s){
        int currentIdx=0;
        for (int i=0;i<s.length();i++){
           try {
               int c = Integer.parseInt(s.charAt(i)+"");
               continue;
           }catch (Exception e){
               if((s.charAt(i)+"").equals(".")){
                   continue;
               }else{
                   for (int start=currentIdx;start<i;start++){
                       String para="";
                       para += s.charAt(start)+"";
                       InfoFunction.function.add(para);
                       InfoFunction.function.add(s.charAt(i)+"");
                   }
               }
           }
        }
    }

    // divide function into group of " số hạng"s
    public void divideGroup(){
        int lastIdx =0;
        for(int function_i=0;function_i<function.size();function_i++){
            if (function.get(function_i).equals("+") || function.get(function_i).equals("-")){
                groupAddSubtract.add(subFunctionArrayList(lastIdx,function_i-1));
                groupAddSubtract.add(subFunctionArrayList(function_i));
                lastIdx=function_i+1;
            }
            if(function_i==function.size()-1){
                groupAddSubtract.add(subFunctionArrayList(lastIdx,function_i));
            }
        }
    }

    //tính kết quả từ group đã chia
    public String getResult(){
        divideGroup();
        double result=0;
        boolean isAdd=true;
        for (int groupAddSubtract_i=0;groupAddSubtract_i<groupAddSubtract.size();groupAddSubtract_i++){
            double para=0;
            try{
                double test = Double.parseDouble(groupAddSubtract.get(groupAddSubtract_i).get(0));
            }catch(Exception e){
                if(!groupAddSubtract.get(groupAddSubtract_i).get(0).equals("+")){
                    isAdd=false;
                }else{
                    isAdd=true;
                }
                continue;
            }
            ArrayList<String> paraArray = groupAddSubtract.get(groupAddSubtract_i);
            boolean isMulti = true;
            for (int subArrayList_i=0;subArrayList_i<paraArray.size();subArrayList_i++){
                double paraNext=0;
                try {
                    if(subArrayList_i==0){
                        para=Double.parseDouble(paraArray.get(0));
                        continue;
                    }
                    paraNext = Double.parseDouble(paraArray.get(subArrayList_i));
                }catch (Exception e){
                    if(!paraArray.get(subArrayList_i).equals("x")){
                        isMulti=false;
                    }else{
                        isMulti=true;
                    }
                    continue;
                }
                if (isMulti){
                    para*=paraNext;
                }else {
                    para/=paraNext;
                }
            }
            if (isAdd){
                result+=para;
            }else {
                result-=para;
            }
        }
        return String.valueOf(result);
    }

    // transform a*(b+c) into a*d
    public ArrayList<String> replaceParenthesis(){
        ArrayList<String> func = this.function;
        ArrayList<String> result = new ArrayList<>();
        int startIdx=0,endIdx=0;
        for (int function_i=0;function_i<func.size();function_i++){
            if (func.get(function_i).equals("(")){
                startIdx=function_i;
            }
            if (func.get(function_i).equals(")")){
                endIdx=function_i;
                break;
            }
        }
        for (int i=startIdx+1;i<endIdx;i++){
            result.add(func.get(startIdx+1));
            func.remove(startIdx+1);
        }
        Handler handler = new Handler(result);
        func.remove(startIdx);
        func.remove(startIdx);
        func.add(startIdx,handler.getResult());
        return func;
    }
}
