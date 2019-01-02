package untitled.example.com.reporttestdemo.Utility;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Amber_Chen on 2018/10/2.
 */

public abstract class SimpleTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}