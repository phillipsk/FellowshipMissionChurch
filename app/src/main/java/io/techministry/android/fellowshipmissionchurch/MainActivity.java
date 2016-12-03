package io.techministry.android.fellowshipmissionchurch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.techministry.android.fellowshipmissionchurch.model.ChapterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //TODO: make the database autoincrement works on each install
        //BibleDbHelpher.create(this);

    }

    @OnClick(R.id.load_chapter)
    public void loadChapter() {
        final BibleApi clientApi = Downloader.createClientApi();

        final Call<ChapterResponse> responseCall = clientApi.chapter("eng-GNTD:2Tim");
        responseCall.enqueue(new Callback<ChapterResponse>() {
            @Override
            public void onResponse(Call<ChapterResponse> call, Response<ChapterResponse> response) {
                Log.d("Response", "Test");
                //Log.d("Response", response.body().getChapters().toString());
            }

            @Override
            public void onFailure(Call<ChapterResponse> call, Throwable t) {
                Log.e("Response", "error", t);
            }
        });
    }
}
