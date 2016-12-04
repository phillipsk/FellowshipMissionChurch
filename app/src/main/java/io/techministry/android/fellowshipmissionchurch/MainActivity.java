package io.techministry.android.fellowshipmissionchurch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new BibleFragment())
                    .commit();
        }

        FetchBibleVerse bibleVerse = new FetchBibleVerse();
        bibleVerse.execute();
    }





/*    @OnClick(R.id.load_chapter)
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
    }*/
}
