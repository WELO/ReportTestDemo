package untitled.example.com.reporttestdemo.presentation;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import untitled.example.com.reporttestdemo.R;
import untitled.example.com.reporttestdemo.Utility.BaseActivity;
import untitled.example.com.reporttestdemo.Utility.Define;
import untitled.example.com.reporttestdemo.Utility.ViewModelFactory;
import untitled.example.com.reporttestdemo.databinding.ActivityMainBinding;
import untitled.example.com.reporttestdemo.domain.repository.entity.Report;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rv_report)
    RecyclerView rvReport;

    Disposable disposable;
    ReportAdapter adapter;

    @Inject
    ViewModelFactory viewModelFactory;

    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this, binding.getRoot());

        AndroidInjection.inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        binding.setMainViewModel(viewModel);
        binding.setLifecycleOwner(this);

        initView();
    }

    private void initView() {
        rvReport.setLayoutManager(new LinearLayoutManager(context));
        disposable = viewModel.getReportList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reportList -> {
                    Timber.d("getReportList success");
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

            @SuppressLint("CheckResult")
            @Override
            public void onDelete(Report report) {
                viewModel.deleteReport(report).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {
                            Timber.d("deleteReport success");
                        }, Throwable::printStackTrace);
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
