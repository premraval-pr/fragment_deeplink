package com.example.test1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test1.model.CountryContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class AllCountriesFragment extends Fragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    public AllCountriesFragment() {
    }

    public AllCountriesFragment(List<CountryContent.CountryItem> countryItemList,OnFragmentListener onFragmentListener) {
        myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(countryItemList,onFragmentListener);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_countries_list, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(myItemRecyclerViewAdapter);
        myItemRecyclerViewAdapter.notifyDataSetChanged();

        return view;
    }

    public void dataSetChange(){
        myItemRecyclerViewAdapter.notifyDataSetChanged();
    }

    interface OnFragmentListener{
        void onClick(CountryContent.CountryItem countryItem);
    }
}