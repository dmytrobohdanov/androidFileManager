package com.dmytrobohdanov.testtaskfilemanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmytrobohdanov.testtaskfilemanager.Utils.Database.DataModel;

import java.util.ArrayList;


public class DataRVAdapter extends RecyclerView.Adapter<DataRVHolder> {

    Context context;
    LayoutInflater inflater;
    ArrayList<DataModel> dataList;

    public DataRVAdapter(Context context, ArrayList<DataModel> dataList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }

    @Override
    public DataRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataRVHolder(inflater.inflate(R.layout.data_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(DataRVHolder holder, int position) {
        DataModel dataModel = dataList.get(position);

        //blue line
        if (dataModel.isBlue()) {
            holder.blueLine.setVisibility(View.VISIBLE);
        } else {
            holder.blueLine.setVisibility(View.GONE);
        }

        //orange line
        if (dataModel.isOrange()) {
            holder.orangeLine.setVisibility(View.VISIBLE);
        } else {
            holder.orangeLine.setVisibility(View.GONE);
        }

        //set title
        holder.fileName.setText(dataModel.getFileName());

        //set modified date
        holder.modifiedDate.setText(dataModel.getModifiedDateString());

        holder.layout.setOnClickListener((view) -> {
            //todo open new fragment with new data list
        });

        //set image
        if (dataModel.isFolder()) {
            //todo change icon to folder
        } else {
            switch (dataModel.getFileType()) {
                case PDF:
                    //todo change icon
                    break;

                case IMAGE:
                    //todo change icon
                    break;

                case MOVIE:
                    //todo change icon
                    break;

                case MUSIC:
                    //todo change icon
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setData(ArrayList<DataModel> dataList) {
        this.dataList = dataList;
    }
}
