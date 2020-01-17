package com.pitangent.assettracking.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.pitangent.assettracking.Api.MainApplication;
import com.pitangent.assettracking.R;
import com.pitangent.assettracking.model.loginModel.ResponseUser;
import com.pitangent.assettracking.ui.dashboard.Dashboard;
import com.pitangent.assettracking.utils.ConnectionCheck;
import com.pitangent.assettracking.utils.SharedPreferencesManager;
import com.pitangent.assettracking.utils.ShowToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    EditText input_email,input_password;
    Button btn_login;
    ConnectionCheck connectionCheck;
    ShowToast showToast=new ShowToast();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        input_email=findViewById(R.id.input_email);
        input_password=findViewById(R.id.input_password);
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:

                login();

                break;
        }
    }

    private void login() {
        Log.i(TAG, "Login");
        if (!validate()) {
            progressDialog = new ProgressDialog(LoginActivity.this);
            onLoginFailed();
            return;
        }
        btn_login.setEnabled(false);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        final String email = input_email.getText().toString();
        final String password = input_password.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess(email,password);
                        // onLoginFailed();
                    }
                }, 3000);
    }
    public void onLoginSuccess(String email, String password) {
       connectionCheck=new ConnectionCheck();
        if(connectionCheck.ConnectionCheck(LoginActivity.this))
        {
            MainApplication.apiManager.loginUser(email, password, new Callback<ResponseUser>() {
                @Override
                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                    ResponseUser responseUser=response.body();
                    Log.i("login","login");
                    if(response.isSuccessful()&& responseUser!=null){
                        if (responseUser.getSuccess()){
                            SharedPreferencesManager.getInstance().setLogged("logged");
                            SharedPreferencesManager.getInstance().setToken(responseUser.getToken());
                            progressDialog.dismiss();
                            Intent intent=new Intent(LoginActivity.this, Dashboard.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Log.i("login","false");
                            onLoginFailed();}

                    }
                    else {
                        Log.i(TAG, "Login");
                        onLoginFailed();
                    }
                }
                @Override
                public void onFailure(Call<ResponseUser> call, Throwable t) {
                    Log.i("login",t.getMessage());
                    onLoginFailed();
                }
            });

        }
       else {
            btn_login.setEnabled(true);
            progressDialog.dismiss();
            showToast.showToast(LoginActivity.this,"Check Your internet Connection");
       }
    }

    public void onLoginFailed() {
        showToast.showToast(LoginActivity.this,"Login failed");
        progressDialog.dismiss();
        btn_login.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.setError("enter a valid email address");
            valid = false;
        } else {
            input_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            input_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            input_password.setError(null);
        }
        return valid;
    }

}
