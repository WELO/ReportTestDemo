package untitled.example.com.reporttestdemo.domain.interactor.dagger.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import untitled.example.com.reporttestdemo.domain.repository.ReportDao;
import untitled.example.com.reporttestdemo.domain.repository.ReportDb;
import untitled.example.com.reporttestdemo.domain.repository.ReportRepository;
import untitled.example.com.reporttestdemo.domain.repository.imp.ReportRepositoryImp;
import untitled.example.com.reporttestdemo.presentation.EditViewModel;
import untitled.example.com.reporttestdemo.presentation.MainViewModel;

/**
 * Created by Amy on 2019/4/15
 */

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindsMainViewModelFactory(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(EditViewModel.class)
    abstract ViewModel bindsEditViewModelFactory(EditViewModel editViewModel);

}
