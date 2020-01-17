package com.pitangent.assettracking.Api;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pitangent.assettracking.model.damage.GetDamageModel;
import com.pitangent.assettracking.model.dashboard.EmployeeListModel;
import com.pitangent.assettracking.model.detailspage.GetEmployeeAssetModel;
import com.pitangent.assettracking.model.free.GetFreeAssetModel;
import com.pitangent.assettracking.model.loginModel.ResponseUser;
import com.pitangent.assettracking.model.scanning.GetAssetModel;
import com.pitangent.assettracking.model.scanning.GetAssignedModel;
import com.pitangent.assettracking.model.scanning.GetFreeModel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static IUsersApi service;
    private static ApiManager apiManager;
    private ApiManager() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.188/asset_management/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(IUsersApi.class);

    }
    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }
    public void loginUser(String email, String password, Callback<ResponseUser> callback) {
        Call<ResponseUser> userCall = service.loginUser(email,password);
        userCall.enqueue(callback);
    }
    public  void getEmployeeUser(String authorization, Callback<EmployeeListModel> callback){
        Call<EmployeeListModel> userCall = service.getEmployeeUser(authorization);
        userCall.enqueue(callback);
    }
    public void assignAsset(String authorization, String asset_sku_no, Callback<GetAssetModel> callback)
    {
        Call<GetAssetModel> userCall=service.assignAsset(authorization,asset_sku_no);
        userCall.enqueue(callback);
    }
    public void getassignAsset(String authorization, String employee_id, String asset_id, String status, Callback<GetAssignedModel> callback)
    {
        Call<GetAssignedModel> userCall=service.getassignAsset(authorization,employee_id,asset_id,status);
        userCall.enqueue(callback);
    }
    public void getfreeAsset(String authorization, String employee_id, String asset_id, String status, Callback<GetFreeModel> callback)
    {
        Call<GetFreeModel> userCall=service.getfreeAsset(authorization,employee_id,asset_id,status);
        userCall.enqueue(callback);
    }
    public void getdamageAsset(String authorization, String employee_id, String asset_id, String status, Callback<GetDamageModel> callback)
    {
        Call<GetDamageModel> userCall=service.getdamageAsset(authorization,employee_id,asset_id,status);
        userCall.enqueue(callback);
    }
    public void getemployeeAssetDetails(String authorization,String employee_id, Callback<GetEmployeeAssetModel> callback)
    {
        Call<GetEmployeeAssetModel> userCall=  service.getemployeeAssetDetails(authorization,employee_id);
        userCall.enqueue(callback);


    }
    public void getfreeAssetDetails(String authorization, Callback<GetFreeAssetModel> callback)
    {
        Call<GetFreeAssetModel> userCall=  service.getfreeAssetDetails(authorization);
        userCall.enqueue(callback);


    }
    public void getdamageAssetDetails(String authorization, Callback<GetDamageModel> callback)
    {
        Call<GetDamageModel> userCall=  service.getdamageAssetDetails(authorization);
        userCall.enqueue(callback);


    }



}
