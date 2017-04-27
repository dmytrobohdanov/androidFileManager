package com.dmytrobohdanov.testtaskfilemanager;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.dmytrobohdanov.testtaskfilemanager.DataListFragment.DataListFragment;
import com.dmytrobohdanov.testtaskfilemanager.Utils.Constants;

public class MainActivity extends AppCompatActivity {
    private DataListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //show root folder(its folder id is 1)
        showFragment(1);
    }


    public void showFragment(int folderId) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (fragment != null) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }

        fragment = new DataListFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_FOLDER_ID, folderId);
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.main_activity_container, fragment);
        fragmentTransaction.commit();
    }
}