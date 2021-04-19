package com.skills.newsapp.data.remote;


import androidx.annotation.NonNull;

import com.skills.newsapp.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *  Custom interceptor for injecting api key in request
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api-key", BuildConfig.API_KEY)
                .build();

        Request request = originalRequest.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}