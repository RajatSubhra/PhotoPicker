package io.rajat.rsc.photopicker.apis;

import java.io.IOException;

import javax.inject.Inject;

import io.rajat.rsc.photopicker.Constants;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ImageInterceptor implements Interceptor {



    @Inject
    public ImageInterceptor() {
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        String apiKey = Constants.API_KEY;

        Request original = chain.request();
        Request request = original
                .newBuilder()
                .header("Api-Key", apiKey)
                .build();

        return chain.proceed(request);
    }
}
