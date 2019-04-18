package untitled.example.com.reporttestdemo.domain.interactor.dagger.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import untitled.example.com.reporttestdemo.presentation.EditActivity;
import untitled.example.com.reporttestdemo.presentation.MainActivity;

/**
 * Created by Amy on 2019/4/17
 */

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract MainActivity contributesMainActivity();

    @ContributesAndroidInjector
    abstract EditActivity contributesEditActivity();

}
