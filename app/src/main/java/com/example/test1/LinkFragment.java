package com.example.test1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LinkFragment extends Fragment {

    String url;
    public LinkFragment() {
        // Required empty public constructor
    }

    public LinkFragment(String url) {
        this.url = url;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_link, container, false);
        ((TextView)v.findViewById(R.id.tv_link)).setText(url);
        return v;
    }
}