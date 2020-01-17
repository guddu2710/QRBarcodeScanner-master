package com.pitangent.project.Api;

import com.pitangent.project.model.detailspage.GetEmployeeAssetModel;
import com.pitangent.project.model.scanning.GetAssignedModel;
import com.pitangent.project.model.scanning.GetFreeModel;

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
    private static com.pitangent.project.Api.ApiManager apiManager;
    private ApiManager() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.199/root/asset_management/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IUsersApi.class);

    }
    public static com.pitangent.project.Api.ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new com.pitangent.project.Api.ApiManager();
        }
        return apiManager;
    }
    public void loginUser(String email, String password, Callback<com.pitangent.project.model.loginModel.ResponseUser> callback) {
        Call<com.pitangent.project.model.loginModel.ResponseUser> userCall = service.loginUser(email,password);
        userCall.enqueue(callback);
    }
    public  void getEmployeeUser(String authorization, Callback<com.pitangent.project.model.dashboard.EmployeeListModel> callback){
        Call<com.pitangent.project.model.dashboard.EmployeeListModel> userCall = service.getEmployeeUser(authorization);
        userCall.enqueue(callback);
    }
    public void assignAsset(String authorization, String asset_sku_no, Callback<com.pitangent.project.model.scanning.GetAssetModel> callback)
    {
        Call<com.pitangent.project.model.scanning.GetAssetModel> userCall=service.assignAsset(authorization,asset_sku_no);
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
    public void getdamageAsset(String authorization, String employee_id, String asset_id, String status, Callback<com.pitangent.project.model.damage.GetDamageModel> callback)
    {
        Call<com.pitangent.project.model.damage.GetDamageModel> userCall=service.getdamageAsset(authorization,employee_id,asset_id,status);
        userCall.enqueue(callback);
    }
    public void getemployeeAssetDetails(String authorization, Callback<GetEmployeeAssetModel> callback)
    {
        Call<GetEmployeeAssetModel> userCall=  service.getemployeeAssetDetails(authorization);
        userCall.enqueue(callback);


    }
    public void getfreeAssetDetails(String authorization, Callback<com.pitangent.project.model.free.GetFreeAssetModel> callback)
    {
        Call<com.pitangent.project.model.free.GetFreeAssetModel> userCall=  service.getfreeAssetDetails(authorization);
        userCall.enqueue(callback);


    }




}
