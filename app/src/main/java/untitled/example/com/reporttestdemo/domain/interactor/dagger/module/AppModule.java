package untitled.example.com.reporttestdemo.domain.interactor.dagger.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import untitled.example.com.reporttestdemo.domain.repository.ReportDao;
import untitled.example.com.reporttestdemo.domain.repository.ReportDb;
import untitled.example.com.reporttestdemo.domain.repository.ReportRepository;
import untitled.example.com.reporttestdemo.domain.repository.imp.ReportRepositoryImp;
import untitled.example.com.reporttestdemo.presentation.MainViewModel;

/**
 * Created by Amy on 2019/4/17
 */

@Module(includes = ViewModelModule.class)
public class AppModule {
    @Provides
    @Singleton
    ReportDao provideReportDao() {
        return ReportDb.getReportDao();
    }

    @Provides
    @Singleton
    ReportRepository provideReportRepository(ReportDao reportDao) {
        return new ReportRepositoryImp(reportDao);
    }

//    @Provides
//    @Singleton
//    ViewModelProvider.Factory provideMainViewModelFactory(ReportRepository reportRepository) {
//        return new ViewModelProvider.Factory() {
//            @NonNull
//            @Override
//            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//                return (T) new MainViewModel(reportRepository);
//            }
//        };
//    }

}
