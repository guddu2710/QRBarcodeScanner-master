package com.pitangent.project.utils;

import android.content.Context;
import android.widget.Toast;

public class ShowToast {
    public void showToast(Context context, String msg){
        Toast.makeText(context, ""+msg, Toast.LENGTH_LONG).show();


    }
}
