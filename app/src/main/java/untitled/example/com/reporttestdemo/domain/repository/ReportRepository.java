package untitled.example.com.reporttestdemo.domain.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import untitled.example.com.reporttestdemo.domain.repository.entity.Report;

/**
 * Created by Amy on 2019/1/3
 */

public interface ReportRepository {

    Flowable<List<Report>> getReportList();

    Single<Report> getReportById(long id);

    Completable addReport(Report report);

    Completable updateReport(Report report);

    Completable deleteReport(Report report);


}
