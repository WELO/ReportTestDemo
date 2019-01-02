package untitled.example.com.reporttestdemo.presentation;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import untitled.example.com.reporttestdemo.R;
import untitled.example.com.reporttestdemo.databinding.ItemReportBinding;
import untitled.example.com.reporttestdemo.domain.model.Report;

/**
 * Created by Amy on 2019/1/2
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    List<Report> reportList = new ArrayList<>();
    private onItemClickListioner mOnItemClickListioner;

    public void setOnItemClickListioner(onItemClickListioner onItemClickListioner) {
        mOnItemClickListioner = onItemClickListioner;
    }

    interface onItemClickListioner{
        void onClick(Report report);
    }

    public ReportAdapter(List<Report> reports) {
        this.reportList = reports;
    }

    @NonNull
    @Override
    public ReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReportBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_report,
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ViewHolder holder, int position) {
        holder.bindReport(reportList.get(position));
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.view_item)
        RelativeLayout viewItem;

        ItemReportBinding binding;
        Report report;

        public ViewHolder(ItemReportBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            ButterKnife.bind(this,binding.getRoot());
        }

        public void bindReport(Report report){
            binding.setReport(report);
            this.report = report;
        }
        @OnClick({R.id.view_item})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.view_item:
                    if(mOnItemClickListioner!=null){
                        mOnItemClickListioner.onClick(report);
                    }
                    break;
            }
        }
    }
}
