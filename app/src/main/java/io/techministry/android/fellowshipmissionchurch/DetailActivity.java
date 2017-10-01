package io.techministry.android.fellowshipmissionchurch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DATA_ID = "EXTRA_DATA_ID";
//
//    @BindView(R.id.toolbar)
//    @BindView(R.id.collapsing_toolbar)

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);

       // ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra(EXTRA_DATA_ID)) {
            collapsingToolbar.setTitle(getIntent().getStringExtra(EXTRA_DATA_ID));
        }

    }
}
