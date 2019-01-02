package untitled.example.com.reporttestdemo.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import untitled.example.com.reporttestdemo.R;
import untitled.example.com.reporttestdemo.Utility.BaseActivity;
import untitled.example.com.reporttestdemo.Utility.Define;
import untitled.example.com.reporttestdemo.domain.executor.ReportManager;
import untitled.example.com.reporttestdemo.domain.model.Report;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv_report)
    RecyclerView rvReport;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    ReportManager reportManager;
    Disposable disposable;
    ReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        reportManager = ReportManager.getInstance();
        rvReport.setLayoutManager(new LinearLayoutManager(context));
        disposable = reportManager.getReportList().subscribe(reportList -> {
            refreshReportList(reportList);
        }, Throwable::printStackTrace);

    }

    private void refreshReportList(List<Report> reportList) {
        adapter = new ReportAdapter(reportList);
        rvReport.setAdapter(adapter);
        adapter.setOnItemClickListioner(new ReportAdapter.onItemClickListioner() {
            @Override
            public void onClick(Report report) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra(Define.INTENT_PARAM_EDIT_ID, report.getId());
                startActivity(intent);
            }
        });
    }

    public void newReport(View view) {
        startActivity(new Intent(context, EditActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
