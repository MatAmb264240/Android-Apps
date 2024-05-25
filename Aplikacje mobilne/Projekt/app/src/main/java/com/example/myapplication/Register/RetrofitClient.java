package com.example.myapplication.Register;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.UserProfile.RefreshTokenRequest;
import com.example.myapplication.UserProfile.RefreshTokenResponse;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitClient {
    private static RetrofitClient instance;
    private Api api;
    private Retrofit retrofit;

    private RetrofitClient(Context context) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        // Logging interceptor for debugging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(logging);

        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("access_token", null);

                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();

                Response response = chain.proceed(request);

//                if (response.code() == 401)
//                { // If token is expired, refresh it
//                    String refreshToken = sharedPreferences.getString("refresh_token", null);
//                    if (refreshToken != null) {
//                        Call<RefreshTokenResponse> call = api.refreshToken(new RefreshTokenRequest(refreshToken));
//                        retrofit2.Response<RefreshTokenResponse> refreshResponse = call.execute();
//
//                        if (refreshResponse.isSuccessful() && refreshResponse.body() != null) {
//                            String newAccessToken = refreshResponse.body().getAccess();
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString("access_token", newAccessToken);
//                            editor.apply();
//
//                            // Retry the original request with new token
//                            Request newRequest = chain.request().newBuilder()
//                                    .removeHeader("Authorization")
//                                    .addHeader("Authorization", "Bearer " + newAccessToken)
//                                    .build();
//
//                            return chain.proceed(newRequest);
//                        }
//                    }
//                }
                return response;
            }
        });

        OkHttpClient client = clientBuilder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8000/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public static synchronized RetrofitClient getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }

    public Api getApi() {
        return api;
    }
}
