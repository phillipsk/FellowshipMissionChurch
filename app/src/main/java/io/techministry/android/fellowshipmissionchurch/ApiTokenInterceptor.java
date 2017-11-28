package io.techministry.android.fellowshipmissionchurch;

import android.util.Base64;

import java.io.IOException;

import io.techministry.android.fellowshipmissionchurch.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiTokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();

        String loginString = BuildConfig.API_KEY;
        String password = "";
        final String encodedApiKey =
            Base64.encodeToString(String.format("%s:%s", loginString, password).getBytes("UTF-8"),
                Base64.NO_WRAP);

        Request authorisedRequest = request.newBuilder()
            .header("Authorisation", "Basic " + encodedApiKey)
            //.header("Content-Type", "application/json")
            //.header("Accept", "application/json")
            .build();
        return chain.proceed(authorisedRequest);
    }
}
