package io.rajat.rsc.photopicker;


import android.app.Application;

import io.rajat.rsc.photopicker.daggar.AppComponent;
import io.rajat.rsc.photopicker.daggar.AppModule;
import io.rajat.rsc.photopicker.daggar.DaggerAppComponent;

public class MainApplication extends Application{
    private static MainApplication instance;

    public static MainApplication getInstance() {
        return instance;
    }
    protected AppComponent appComponent;
    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = initDagger(this);
    }

    protected AppComponent initDagger(MainApplication application) {

        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
