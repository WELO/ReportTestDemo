package untitled.example.com.reporttestdemo.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import android.content.Context;
import android.test.mock.MockContext;
import android.test.suitebuilder.annotation.MediumTest;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.TestSubscriber;
import untitled.example.com.reporttestdemo.domain.repository.ReportDao;
import untitled.example.com.reporttestdemo.domain.repository.ReportDb;
import untitled.example.com.reporttestdemo.domain.repository.ReportRepository;
import untitled.example.com.reporttestdemo.domain.repository.entity.Report;
import untitled.example.com.reporttestdemo.domain.repository.imp.ReportRepositoryImp;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Amy on 2019/3/14
 */
@RunWith(JUnit4.class)
@MediumTest
public class ReportDbTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ReportDao reportDao;

    @Mock
    private Disposable disposable;

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
        Context context =  new MockContext();
        ReportDb.init(context);
        reportDao = ReportDb.getReportDao();
        reportRepository = new ReportRepositoryImp(reportDao);
    }

    @After
    public void closeDb() throws IOException {
       // ReportDb.closeDb();
        disposable.dispose();
    }

    @Test
    public void getReportListTest() {

        Report orginReport = Report.newBuilder().setContent("content").setTitle("title").setId(100).build();
        Report orginReport2 = Report.newBuilder().setContent("content222").setTitle("title333").setId(1020).build();
        reportRepository.addReport(orginReport).subscribe();
        disposable = reportRepository.getReportById(100)
                .subscribe(report -> {
            assertThat(orginReport2, equalTo(report));
        });
    }


}
