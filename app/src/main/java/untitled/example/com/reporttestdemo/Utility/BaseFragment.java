package untitled.example.com.reporttestdemo.Utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.util.Objects;

import timber.log.Timber;
import untitled.example.com.reporttestdemo.R;

public abstract class BaseFragment extends Fragment {
    protected Context context;
    protected FragmentManager fragmentManager;
    private ProgressDialog progressDialog = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }


    protected abstract void updateView();

    public boolean onBackPressed() {
        return false;
    }


    protected void showProgressDialog() {
        Activity activity = getActivity();
        if (activity != null && (progressDialog == null || !progressDialog.isShowing()))
            progressDialog = ProgressDialog.show(activity, null, getString(R.string.gen_loading));
    }

    protected void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    protected AlertDialog.Builder getDialog(int title, int content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialog);
        return builder.setTitle(title).setMessage(content).setCancelable(false);
    }

    protected AlertDialog.Builder getDialog(int title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialog);
        return builder.setTitle(title).setMessage(content).setCancelable(false);
    }

    protected AlertDialog.Builder getContentViewDialog(int title, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialog);
        return builder.setTitle(title).setView(view).setCancelable(false);
    }

    protected <T extends Fragment> void setFragment(int resourceId, T fragment) {
        FragmentTransaction fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(resourceId, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }


}
