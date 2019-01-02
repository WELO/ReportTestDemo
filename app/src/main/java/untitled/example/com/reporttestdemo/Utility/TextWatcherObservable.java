package untitled.example.com.reporttestdemo.Utility;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextWatcher;

/**
 * Created by Amber_Chen on 2018/10/2.
 */

public class TextWatcherObservable extends BaseObservable {
    private TextWatcher textWatcher;

    @Bindable
    public TextWatcher getErrorMsg() {
        return textWatcher;
    }

    public void set(TextWatcher textWatcher) {
        if (textWatcher != null) {
            this.textWatcher = textWatcher;
            notifyChange();
        }
    }
}
