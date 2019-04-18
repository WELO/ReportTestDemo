package untitled.example.com.reporttestdemo.domain.interactor.dagger.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import untitled.example.com.reporttestdemo.domain.interactor.dagger.builder.ActivityBuilderModule;
import untitled.example.com.reporttestdemo.domain.interactor.dagger.module.AppModule;
import untitled.example.com.reporttestdemo.presentation.EditActivity;
import untitled.example.com.reporttestdemo.presentation.MainActivity;
import untitled.example.com.reporttestdemo.presentation.MainApplication;

/**
 * Created by Amy on 2019/4/16
 */
@Singleton
@Component(modules = {AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
    void inject(MainApplication application);

}
