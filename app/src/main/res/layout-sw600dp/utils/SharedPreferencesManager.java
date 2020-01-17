package com.pitangent.project.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesManager {

    private static final com.pitangent.project.utils.SharedPreferencesManager ourInstance = new com.pitangent.project.utils.SharedPreferencesManager();

    public static final String USER_NAME = "UserName";
    public static final String USER_PASSWORD = "UserPassword";

    private SharedPreferences prefs;
    private Context context;

    public static com.pitangent.project.utils.SharedPreferencesManager getInstance() {
        return ourInstance;
    }

    private SharedPreferencesManager() {

    }

    public void init(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void setLogged(String value){
        prefs.edit().putString("logged",value).apply();
    }

    public String getLogged(){
        return prefs.getString("logged","");
    }
    public void setUserName(String value){
        prefs.edit().putString("username",value).apply();
    }

    public String getUserName(){
        return prefs.getString("username","");
    }
    public void setToken(String value){
        prefs.edit().putString("token",value).apply();
    }

    public String getToken(){
        return prefs.getString("token","");
    }
    public void setEmpId(String value){
        prefs.edit().putString("empid",value).apply();
    }

    public String getEmpId(){
        return prefs.getString("empid","");
    }
    public void setAssignType(String value){
        prefs.edit().putString("assignType",value).apply();
    }

    public String getAssignType(){
        return prefs.getString("assignType","");
    }
}

