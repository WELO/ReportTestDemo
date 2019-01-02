package untitled.example.com.reporttestdemo.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import untitled.example.com.reporttestdemo.domain.model.Report;

/**
 * Created by Amy on 2019/1/2
 */

public class EditViewModel extends ViewModel {

    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> content = new MutableLiveData<>();

    public EditViewModel() {
    }

    public void setInitReport(Report report){
        title.setValue(report.getTitle());
        content.setValue(report.getContent());
    }
}
