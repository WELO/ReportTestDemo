package untitled.example.com.reporttestdemo.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import io.reactivex.Completable;
import timber.log.Timber;
import untitled.example.com.reporttestdemo.domain.repository.ReportRepository;
import untitled.example.com.reporttestdemo.domain.repository.entity.Report;

/**
 * Created by Amy on 2019/1/2
 */

public class EditViewModel extends ViewModel {

    private final ReportRepository reportRepository;
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> content = new MutableLiveData<>();

    public EditViewModel(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Completable addReport(Report report){
        return reportRepository.addReport(report);
    }

    public Completable updateReport(Report report){
        return reportRepository.updateReport(report);
    }

    public Completable setInitReport(long id){
        return reportRepository.getReportById(id).flatMapCompletable(report -> {
            Timber.d("Report = "+report.toString());
            title.postValue(report.getTitle());
            content.postValue(report.getContent());
            return Completable.complete();
        });
    }
}
