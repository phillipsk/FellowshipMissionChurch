package io.techministry.android.fellowshipmissionchurch;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Downloader {

    //static String COMPLETE_SERVER_URL = "https://"+ BuildConfig.API_KEY +":x@en.bibles.org/";
    static String COMPLETE_SERVER_URL = "https://bibles.org/";

    public static BibleApi createClientApi() {
        final Gson gson = new Gson();
        final OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(new ApiTokenInterceptor())
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
        final Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .baseUrl(COMPLETE_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
        return retrofit.create(BibleApi.class);
    }
}
