package com.example.zn.practicewriting;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class DataHandler {

    private Context mContext;
    public DataHandler(Context con_Tmp){
        mContext = con_Tmp;
    }

    public ArrayList<String> getStrokeData(ArrayList<String> Data){
        String[] res =  mContext.getResources().getStringArray(R.array.Stroke);
        for (int i = 0; i < res.length; i++) {
            Data.add(res[i]);
        }
        return Data;
    }

    public ArrayList<String> getWordData(String name,ArrayList<String> Data){
        int resId = mContext.getResources().getIdentifier(name,"array",mContext.getPackageName());
        String[] res = mContext.getResources().getStringArray(resId);
        for (int i = 0; i < res.length; i++) {
            Data.add(res[i]);
        }
        return Data;
    }

    public ArrayList<DrawableDataType> getDrawableData(String stroke,ArrayList<DrawableDataType> Data){
        int resId = mContext.getResources().getIdentifier(stroke,"array",mContext.getPackageName());
        String[] res = mContext.getResources().getStringArray(resId);

        int[] weight = mContext.getResources().getIntArray(R.array.Storke_weight);
        int total = 0;
        for(int i = 0;i < deCode(stroke) - 1;i++){
            total += weight[i];
        }

        for(int i = 0;i < weight[deCode(stroke)-1];i++){
            Data.add(new DrawableDataType(res[i],"wg"+makeCode(total+1)));
            total++;
        }
        return Data;
    }
    //make code for data(Hexadecimal)
    public String makeCode(int number){
        String Code = Integer.toHexString(number).toUpperCase();
        while(Code.length() < 3){
            Code = "0"+Code;
        }
        return Code;
    }

    public int deCode(String code){
        String tmp_code = "";
        int codeNumber;
        for(int i = 0;i<code.length();i++){
            if(code.charAt(i) == '_'){
                tmp_code = code.substring(i+1);
                break;
            }
        }
        codeNumber = Integer.parseInt(tmp_code,16);
        return codeNumber;
    }
}
