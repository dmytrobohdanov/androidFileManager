package com.dmytrobohdanov.testtaskfilemanager.DataListFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmytrobohdanov.testtaskfilemanager.R;
import com.dmytrobohdanov.testtaskfilemanager.Utils.Constants;
import com.dmytrobohdanov.testtaskfilemanager.Utils.Database.DBHandler;
import com.dmytrobohdanov.testtaskfilemanager.Utils.Database.DataModel;

import java.util.ArrayList;

/**
 * Main fragment
 * keeps recycle view with data
 */
public class DataListFragment extends Fragment {
    public static final String TAG = "dataListFrag";

    RecyclerView recyclerView;
    DataRVAdapter rvAdapter;
    RecyclerView.LayoutManager rvLayoutManager;

    ItemTouchHelper touchHelper = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.LEFT) {

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    if (direction == ItemTouchHelper.LEFT) {
                        //todo change item to actions from dialog
                    }
                }
            });


    public DataListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_data_list, container, false);

        initRV(rootView);

        return rootView;
    }

    private void initRV(View rootView) {
        ArrayList<DataModel> dataList = new ArrayList<>();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.data_recycle);
        rvLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(rvLayoutManager);

        rvAdapter = new DataRVAdapter(getContext(), dataList);

        recyclerView.setAdapter(rvAdapter);

        //attaching swipe listener
        //todo  touchHelper.attachToRecyclerView(recyclerView);

        //getting data to display from DB
        if (getArguments() != null && getArguments().containsKey(Constants.KEY_FOLDER_ID)) {
            dataList = DBHandler.getInstance(getContext()).getFilesByFolderId(getArguments().getInt(Constants.KEY_FOLDER_ID));
        } else {
            throw new IllegalStateException("expecting id of some folder to display");
        }
        rvAdapter.setData(dataList);
        rvAdapter.notifyDataSetChanged();
    }

}