package io.techministry.android.fellowshipmissionchurch;

import android.util.Base64;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiTokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();

        final String encodedApiKey = Base64.encodeToString((BuildConfig.API_KEY + ":x").getBytes("UTF-8"), Base64.NO_WRAP);

        Request authorisedRequest = request.newBuilder()
            .header("Authorisation", "Basic " + encodedApiKey)
            .build();
        return chain.proceed(authorisedRequest);

    }

}
