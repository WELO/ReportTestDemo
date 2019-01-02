package untitled.example.com.reporttestdemo.presentation;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.Single;
import untitled.example.com.reporttestdemo.R;
import untitled.example.com.reporttestdemo.Utility.BaseActivity;
import untitled.example.com.reporttestdemo.Utility.Define;
import untitled.example.com.reporttestdemo.Utility.Utility;
import untitled.example.com.reporttestdemo.databinding.ActivityEditBinding;
import untitled.example.com.reporttestdemo.domain.executor.ReportManager;
import untitled.example.com.reporttestdemo.domain.model.Report;
import untitled.example.com.reporttestdemo.exception.InvalidReportInputException;

public class EditActivity extends BaseActivity {

    ReportManager reportManager;
    EditViewModel viewModel;
    long initReportId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_edit);
        ButterKnife.bind(this, binding.getRoot());
        viewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new EditViewModel();
            }
        }).get(EditViewModel.class);
        binding.setEditViewModel(viewModel);
        reportManager = ReportManager.getInstance();

        if (getIntent() != null) {
            initReportId = getIntent().getLongExtra(Define.INTENT_PARAM_EDIT_ID, 0);
            viewModel.setInitReport(reportManager.getReportById(initReportId));
        }
    }

    @SuppressLint("CheckResult")
    public void sendAndSaveReport(View view) {
        isValidInput().flatMapCompletable(isValid->{
            if (initReportId != 0) {
                return reportManager.updateReport(Report.newBuilder()
                        .setId(initReportId)
                        .setTitle(viewModel.title.getValue())
                        .setContent(viewModel.content.getValue())
                        .build());
            } else {
                return reportManager.addReport(Report.newBuilder()
                        .setId(System.currentTimeMillis())
                        .setTitle(viewModel.title.getValue())
                        .setContent(viewModel.content.getValue())
                        .build());
            }
        }).subscribe(this::onBackPressed, this::errorHandle);


    }

    public Single<Boolean> isValidInput(){
        return Single.fromCallable(()->{
            Utility.verifyReportInput(viewModel.title.getValue(),viewModel.content.getValue(),context);
            return true;
        });
    }

    public void errorHandle(Throwable throwable){
        if(throwable instanceof InvalidReportInputException){
            Toast.makeText(context,throwable.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
