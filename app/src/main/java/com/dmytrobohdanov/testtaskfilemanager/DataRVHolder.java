package com.dmytrobohdanov.testtaskfilemanager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DataRVHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.data_holder_blue_line)
    View blueLine;

    @BindView(R.id.data_holder_orange_line)
    View orangeLine;

    @BindView(R.id.data_holder_file_name)
    TextView fileName;

    @BindView(R.id.data_holder_modified_date)
    TextView modifiedDate;

    @BindView(R.id.data_holder_icon)
    ImageView icon;

    @BindView(R.id.data_holder_layout)
    LinearLayout layout;


    public DataRVHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
