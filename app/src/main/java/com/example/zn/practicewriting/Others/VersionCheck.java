package com.example.zn.practicewriting.Others;

import android.content.Context;

import com.example.zn.practicewriting.Model.DataBaseDataType;
import com.example.zn.practicewriting.Model.FireBaseData;
import com.example.zn.practicewriting.R;

public class VersionCheck {

    private final String version;
    private FireBaseData mFireBaseData;
    private DataBaseDataType mDataBase;

    public VersionCheck(Context mContext){
        version = mContext.getResources().getString(R.string.version);
        mFireBaseData = new FireBaseData("version",null);
        mDataBase = mFireBaseData.getInformation();
    }

    public boolean isNewVersion(){
        boolean tmp = false;
        if(mDataBase.getVersion() != null) {
            if (!mDataBase.getVersion().equals(version))
                tmp = true;
            else
                tmp = false;
        }
        return tmp;
    }

    public String getDataBase_Version() {
        return mDataBase.getVersion();
    }
}
