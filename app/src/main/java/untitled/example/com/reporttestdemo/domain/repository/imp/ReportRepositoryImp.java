package untitled.example.com.reporttestdemo.domain.repository.imp;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import untitled.example.com.reporttestdemo.domain.repository.ReportDao;
import untitled.example.com.reporttestdemo.domain.repository.entity.Report;
import untitled.example.com.reporttestdemo.domain.repository.ReportRepository;

/**
 * Created by Amy on 2019/1/3
 */

public class ReportRepositoryImp implements ReportRepository{


    private final ReportDao reportDao;

    public ReportRepositoryImp(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public Flowable<List<Report>> getReportList() {
        return reportDao.getReportList();
    }

    @Override
    public Single<Report> getReportById(long id) {
        return reportDao.getReportById(id);
    }

    @Override
    public Completable addReport(Report report) {
        return Completable.fromAction(()->reportDao.setReport(report));
    }

    @Override
    public Completable updateReport(Report report) {
        return Completable.fromAction(()->reportDao.updateReport(report));
    }

    @Override
    public Completable deleteReport(Report report) {
        return Completable.fromAction(()->reportDao.deleteReport(report));
    }


}
