package untitled.example.com.reporttestdemo.Utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import untitled.example.com.reporttestdemo.R;

public class BaseActivity extends AppCompatActivity {
    protected Context context;
    protected Toolbar toolbar = null;
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }


    protected AlertDialog.Builder getDialog(int title, int content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialog);
        return builder.setTitle(title).setMessage(content).setCancelable(false);
    }

    protected AlertDialog.Builder getDialog(int title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialog);
        return builder.setTitle(title).setMessage(content).setCancelable(false);
    }

    protected void showProgressDialog() {
        if (progressDialog == null || !progressDialog.isShowing())
            progressDialog = ProgressDialog.show(this, null, getString(R.string.gen_loading));
    }

    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
