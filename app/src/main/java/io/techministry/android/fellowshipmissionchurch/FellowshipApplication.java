package io.techministry.android.fellowshipmissionchurch;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.onesignal.OneSignal;

public class FellowshipApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

    }

}
