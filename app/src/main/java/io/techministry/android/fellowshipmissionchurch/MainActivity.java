package io.techministry.android.fellowshipmissionchurch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.techministry.android.fellowshipmissionchurch.data.BibleDbHelpher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BibleDbHelpher.create(this);
    }
}
