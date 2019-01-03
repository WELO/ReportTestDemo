package untitled.example.com.reporttestdemo.presentation;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import untitled.example.com.reporttestdemo.domain.repository.ReportRepository;
import untitled.example.com.reporttestdemo.domain.repository.entity.Report;

/**
 * Created by Amy on 2019/1/2
 */

public class MainViewModel extends ViewModel {

    private final ReportRepository reportRepository;

    public MainViewModel(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Flowable<List<Report>> getReportList(){
        return reportRepository.getReportList();
    }

    public Completable deleteReport(Report report){
        return reportRepository.deleteReport(report);
    }
}
