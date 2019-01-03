package untitled.example.com.reporttestdemo.presentation;

import android.app.Application;

import timber.log.Timber;
import untitled.example.com.reporttestdemo.domain.repository.ReportDb;

/**
 * Created by Amy on 2019/1/2
 */

public class MainApplication extends Application {
    private static MainApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        application = this;

        ReportDb.init(this);

    }
}
