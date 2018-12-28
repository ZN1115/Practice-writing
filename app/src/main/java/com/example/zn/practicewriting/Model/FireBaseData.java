package com.example.zn.practicewriting.Model;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireBaseData {

    private String wordName;
    private DatabaseReference mDatebaseReference;
    private DataBaseDataType tmp;
    public FireBaseData(String mode,String wordName){
        mDatebaseReference = FirebaseDatabase.getInstance().getReference(mode);
        this.wordName = wordName;
    }

    public DataBaseDataType getInformation(){
        tmp = new DataBaseDataType();
        if(wordName != null) {
            mDatebaseReference.child(wordName).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                    try {
                        tmp.setMeanData(dataSnapshot.getValue().toString());
                        Log.i("Capoo","DataGetOK");
                    }
                    catch (NullPointerException e){
                        tmp.setMeanData("目前資料庫內無數據");
                        Log.i("Capoo","目前資料庫內無數據");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            mDatebaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    tmp.setVersion(dataSnapshot.getValue().toString());
                    Log.i("Capoo",dataSnapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        return tmp;
    }
}
