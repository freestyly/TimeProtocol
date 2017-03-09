package com.freestyly.timeprotocol.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.freestyly.timeprotocol.R;
import com.freestyly.timeprotocol.database.DataSource;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSettings extends Fragment implements  View.OnClickListener{

    private DataSource dataSource;

    public FragmentSettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onClick(View view) {
        Context context = getContext();
        //EditText editText = (EditText) MainActivity.act.findViewById(R.id.textEdit1);
        //if(editText.getText().toString().length() > 0)
        {
            dataSource = new DataSource(this.getActivity());
            dataSource.open();
            //dataSource.updateConfig
            dataSource.close();
        }
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }

        //fragmentTransaction.hide(this);
        //fragmentTransaction.commit();

    }

}
