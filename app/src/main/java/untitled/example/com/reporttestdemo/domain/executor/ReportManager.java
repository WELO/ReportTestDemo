package untitled.example.com.reporttestdemo.domain.executor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.processors.PublishProcessor;
import untitled.example.com.reporttestdemo.domain.repository.entity.Report;

/**
 * Created by Amy on 2019/1/2
 */

public class ReportManager {

    PublishProcessor<List<Report>> reportPublishProcessor = PublishProcessor.create();
    List<Report> reportList = new ArrayList<>();

    private static ReportManager instance;

    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    private ReportManager() {
    }

    public Flowable<List<Report>> getReportList() {
        return reportPublishProcessor;
    }

    public Completable setReportList(List<Report> reportList) {
        return Completable.fromAction(() -> {
            this.reportList = reportList;
            reportPublishProcessor.onNext(reportList);
        });
    }

    public Completable addReport(Report report) {
        return Completable.fromAction(() -> {
            reportList.add(report);
            reportPublishProcessor.onNext(reportList);
        });
    }

    public Completable updateReport(Report report) {
        return Completable.fromAction(() -> {
            int i = 0;
            for (; i < reportList.size(); i++) {
                if (reportList.get(i).getId() == report.getId()) {
                    reportList.set(i, report);
                    break;
                }
            }
            if (i == reportList.size()) {
                reportList.add(report);
            }
            reportPublishProcessor.onNext(reportList);
        });
    }

    public Completable removeReport(Report report) {
        return Completable.fromAction(() -> {
            reportList.remove(report);
            reportPublishProcessor.onNext(reportList);
        });
    }

    public Completable removeReportById(long id) {
        return Completable.fromAction(() -> {
            for (int i = 0; i < reportList.size(); i++) {
                if (reportList.get(i).getId() == id) {
                    reportList.remove(i);
                }
            }
            reportPublishProcessor.onNext(reportList);
        });
    }

    public Report getReportById(long id) {
        for (Report report : reportList) {
            if (report.getId() == id) {
                return report;
            }
        }
        return Report.newBuilder().build();
    }
}
