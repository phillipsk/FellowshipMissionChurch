package io.techministry.android.fellowshipmissionchurch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.techministry.android.fellowshipmissionchurch.model.ChapterResponse;
import io.techministry.android.fellowshipmissionchurch.model.PassagesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private BibleApi clientApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //TODO: make the database autoincrement works on each install
        //BibleDbHelpher.create(this);

        clientApi = Downloader.createClientApi();
    }

    @OnClick(R.id.load_chapter)
    public void loadChapter() {
        clientApi.chapter("eng-GNTD:2Tim")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<ChapterResponse>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.d("TEST", "onError");
                }

                @Override
                public void onNext(ChapterResponse chapterResponse) {
                    Log.d("TEST", "chapterResponse");
                }
            });

    }

    @OnClick(R.id.load_passages)
    public void loadPassages() {
        clientApi.passages("john+3:1-5", "eng-KJVA")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<PassagesResponse>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.e("Response", "error", e);
                }

                @Override
                public void onNext(PassagesResponse passagesResponse) {
                    Log.d("Response", "Test");
                }
            });
    }
}
