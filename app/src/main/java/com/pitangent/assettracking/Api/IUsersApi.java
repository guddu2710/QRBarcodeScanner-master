package com.pitangent.assettracking.Api;



import com.pitangent.assettracking.model.damage.GetDamageModel;
import com.pitangent.assettracking.model.dashboard.EmployeeListModel;
import com.pitangent.assettracking.model.detailspage.GetEmployeeAssetModel;
import com.pitangent.assettracking.model.free.GetFreeAssetModel;
import com.pitangent.assettracking.model.loginModel.ResponseUser;
import com.pitangent.assettracking.model.scanning.GetAssetModel;
import com.pitangent.assettracking.model.scanning.GetAssignedModel;
import com.pitangent.assettracking.model.scanning.GetFreeModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IUsersApi {
    @FormUrlEncoded
    @POST("Userlogin/login")
    Call<ResponseUser> loginUser(@Field("email") String email,
                                 @Field("password") String password);
    @GET("employee/employee")
    Call<EmployeeListModel>getEmployeeUser(@Header("Authorization") String authorization);
    @FormUrlEncoded
    @POST("Management/assetDetails")
    Call<GetAssetModel>assignAsset(@Header("Authorization") String authorization,
                                   @Field("asset_sku_no") String asset_sku_no);
    @FormUrlEncoded
    @POST("Management/assetAssignment")
    Call<GetAssignedModel>getassignAsset(@Header("Authorization") String authorization,
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
    Call<GetDamageModel>getdamageAsset(@Header("Authorization") String authorization,
                                       @Field("employee_id") String employee_id,
                                       @Field("asset_id") String asset_id,
                                       @Field("status") String status);

    @FormUrlEncoded
    @POST("employee/assetDetailsEmployee")
    Call<GetEmployeeAssetModel>getemployeeAssetDetails(@Header("Authorization") String authorization,
                                                       @Field("employee_id") String employee_id);
    @GET("employee/freeassetDetails")
    Call<GetFreeAssetModel>getfreeAssetDetails(@Header("Authorization") String authorization);

     @GET("employee/damageassetDetails")
             Call<GetDamageModel>getdamageAssetDetails(@Header("Authorization") String authorization
//                                                    @Field("employee_id") String employee_id

    );


}
