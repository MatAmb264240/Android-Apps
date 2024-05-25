package com.example.myapplication.Register;

import com.example.myapplication.UserProfile.RefreshTokenRequest;
import com.example.myapplication.UserProfile.RefreshTokenResponse;
import com.example.myapplication.UserProfile.UserProfileRequest;
import com.example.myapplication.UserProfile.UserProfileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
public interface Api {
    @POST("http://192.168.56.1:8000/api/post/")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @PUT("api/profile/")
    Call<UserProfileResponse> updateProfile(
            @Header("Authorization") String authorization,
            @Body UserProfileRequest request
    );

    @POST("api/token/refresh/")
    Call<RefreshTokenResponse> refreshToken(@Body RefreshTokenRequest request);
}