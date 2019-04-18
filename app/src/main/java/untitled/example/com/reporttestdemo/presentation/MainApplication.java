package untitled.example.com.reporttestdemo.presentation;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;
import untitled.example.com.reporttestdemo.domain.interactor.dagger.component.DaggerAppComponent;
import untitled.example.com.reporttestdemo.domain.repository.ReportDb;

/**
 * Created by Amy on 2019/1/2
 */

public class MainApplication extends Application implements HasActivityInjector {
    private static MainApplication application;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        application = this;

        ReportDb.init(this);

        DaggerAppComponent.builder().application(this).build().inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
