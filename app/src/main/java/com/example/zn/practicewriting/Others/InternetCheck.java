package com.example.zn.practicewriting.Others;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetCheck {

    private Context mContext;
    public InternetCheck(Context mContext){
        this.mContext = mContext;
    }

    public boolean InternetState(){
        boolean result = false;
        ConnectivityManager CM = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (CM == null) {
            result = false;
        }
        else {
            NetworkInfo info = CM.getActiveNetworkInfo();
            if (info != null && info.isConnected())
                result = true;
            else if(info != null && !info.isConnected())
                result = false;
        }
        return result;
    }
}
