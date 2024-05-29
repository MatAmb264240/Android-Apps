package com.example.myapplication.Register;

import com.example.myapplication.Login.LoginRequest;
import com.example.myapplication.Login.LoginResponse;
import com.example.myapplication.UserProfile.RefreshTokenRequest;
import com.example.myapplication.UserProfile.RefreshTokenResponse;
import com.example.myapplication.UserProfile.UserProfileRequest;
import com.example.myapplication.UserProfile.UserProfileResponse;
import com.example.myapplication.UserProfile.Watched.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @POST("http://192.168.56.1:8000/api/post/")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @PUT("api/profile/")
    Call<UserProfileResponse> updateProfile(
            @Header("Authorization") String authorization,
            @Body UserProfileRequest request
    );
    @POST("/api/token/")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("api/token/refresh/")
    Call<RefreshTokenResponse> refreshToken(@Body RefreshTokenRequest request);

/*    @GET("api/profile/{username}/")
    Call<SeriesResponse> getUserProfile(@Path("username") String username);*/


    @GET("api/movies_watched/{username}/")
    Call<MoviesResponse> getMoviesWatched(@Path("username") String username);

    @GET("api/series_watched/{username}/")
    Call<MoviesResponse> getSeriesWatched(@Path("username") String username);

}