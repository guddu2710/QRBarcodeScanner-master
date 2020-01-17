package com.pitangent.assettracking.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;

import com.pitangent.assettracking.R;

public class ShowSnakbar {
    public void show(Activity activity,String msg){
        Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.content1), msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
