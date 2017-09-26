package io.techministry.android.fellowshipmissionchurch;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.techministry.android.fellowshipmissionchurch.models.Announcement;

public class AnnouncementDetailActivity extends AppCompatActivity {

    Announcement announcement;
    @BindView(R.id.backdrop) ImageView backdrop;
    @BindView(R.id.content) TextView content;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.back_btn) FloatingActionButton back_btn;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_detail);
        ButterKnife.bind(this);
        mContext = this;

        announcement = (Announcement)getIntent().getExtras().getSerializable("announcement");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       // setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle("");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        displayData();
    }

    private void displayData() {


        // GLide allows you to set a fall back image, placeholder image in case of a fail loading the specified image
        Glide.with(mContext)
                .load(announcement.getPhoto())
                .error(R.mipmap.morning_prayer_definition)
                .fitCenter()
                .fallback(R.mipmap.morning_prayer_definition)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(backdrop);

        //content.loadData(announcement.getContent(),"text/plain","UTF-8");
       // content.loadData(announcement.getContent(), "text/html; charset=utf-8", "UTF-8");

        content.setText(Html.fromHtml(announcement.getContent()));

        //content.setText(Html.fromHtml(announcement.getContent()));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
