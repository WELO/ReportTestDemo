package untitled.example.com.reporttestdemo.presentation;

import com.android.databinding.library.baseAdapters.BR;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import untitled.example.com.reporttestdemo.R;
import untitled.example.com.reporttestdemo.Utility.BaseActivity;
import untitled.example.com.reporttestdemo.Utility.Define;
import untitled.example.com.reporttestdemo.Utility.Utility;
import untitled.example.com.reporttestdemo.Utility.ViewModelFactory;
import untitled.example.com.reporttestdemo.domain.repository.entity.Report;
import untitled.example.com.reporttestdemo.exception.InvalidReportInputException;

public class EditActivity extends BaseActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    EditViewModel viewModel;
    long initReportId = 0;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_edit);
        ButterKnife.bind(this, binding.getRoot());

        //DaggerAppComponent.builder().build().inject(this);

        AndroidInjection.inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EditViewModel.class);

        binding.setVariable(BR.editViewModel, viewModel);
        binding.setLifecycleOwner(this);

        if (getIntent() != null) {
            initReportId = getIntent().getLongExtra(Define.INTENT_PARAM_EDIT_ID, 0);
            if (initReportId != 0) {
                viewModel.setInitReport(initReportId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {
                        }, Throwable::printStackTrace);
            }
        }

        viewModel.content.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String content) {
                if (content.startsWith(" ") || content.endsWith(" ")) {
                    content = content.trim();
                    viewModel.content.setValue(content);
                    viewModel.contentSelection.setValue(content.length());
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    public void sendAndSaveReport(View view) {
        isValidInput()
                .flatMapCompletable(isValid -> {
                    Report newReport = Report.newBuilder()
                            .setId(initReportId != 0 ? initReportId : System.currentTimeMillis())
                            .setTitle(viewModel.title.getValue())
                            .setContent(viewModel.content.getValue())
                            .build();
                    if (initReportId != 0) {
                        return viewModel.updateReport(newReport);

                    } else {
                        return viewModel.addReport(newReport);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Timber.d("sendAndSaveReport success");
                    onBackPressed();
                }, this::errorHandle);
    }

    public Single<Boolean> isValidInput() {
        return Single.fromCallable(() -> {
            Utility.verifyReportInput(viewModel.title.getValue(), viewModel.content.getValue(), context);
            return true;
        });
    }

    public void errorHandle(Throwable throwable) {
        if (throwable instanceof InvalidReportInputException) {
            Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
