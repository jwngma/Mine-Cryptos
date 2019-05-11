package com.store.minecryps.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.store.minecryps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoinClickedFragment extends Fragment {


    public CoinClickedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coin_clicked, container, false);
        return view;
    }

}
