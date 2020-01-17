package com.pitangent.project.Api;

import com.pitangent.project.model.scanning.GetFreeModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IUsersApi {
    @FormUrlEncoded
    @POST("Userlogin/login")
    Call<com.pitangent.project.model.loginModel.ResponseUser> loginUser(@Field("email") String email,
                                                                        @Field("password") String password);
    @GET("employee/employee")
    Call<com.pitangent.project.model.dashboard.EmployeeListModel>getEmployeeUser(@Header("Authorization") String authorization);
    @FormUrlEncoded
    @POST("Management/assetDetails")
    Call<com.pitangent.project.model.scanning.GetAssetModel>assignAsset(@Header("Authorization") String authorization,
                                                                        @Field("asset_sku_no") String asset_sku_no);
    @FormUrlEncoded
    @POST("Management/assetAssignment")
    Call<com.pitangent.project.model.scanning.GetAssignedModel>getassignAsset(@Header("Authorization") String authorization,
                                                                              @Field("employee_id") String employee_id,
                                                                              @Field("asset_id") String asset_id,
                                                                              @Field("status") String status);
    @FormUrlEncoded
    @POST("Management/assetFree")
    Call<GetFreeModel>getfreeAsset(@Header("Authorization") String authorization,
                                   @Field("employee_id") String employee_id,
                                   @Field("asset_id") String asset_id,
                                   @Field("status") String status);
    @FormUrlEncoded
    @POST("Management/assetDamage")
    Call<com.pitangent.project.model.damage.GetDamageModel>getdamageAsset(@Header("Authorization") String authorization,
                                                                          @Field("employee_id") String employee_id,
                                                                          @Field("asset_id") String asset_id,
                                                                          @Field("status") String status);


    @GET("employee/assetDetailsEmployee")
    Call<com.pitangent.project.model.detailspage.GetEmployeeAssetModel>getemployeeAssetDetails(@Header("Authorization") String authorization
//                                                    @Field("employee_id") String employee_id

    );
    @GET("employee/freeassetDetails")
    Call<com.pitangent.project.model.free.GetFreeAssetModel>getfreeAssetDetails(@Header("Authorization") String authorization
//                                                    @Field("employee_id") String employee_id

    );


}
