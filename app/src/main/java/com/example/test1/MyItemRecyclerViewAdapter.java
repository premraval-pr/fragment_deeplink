package com.example.test1;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test1.model.CountryContent.CountryItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link CountryItem}.
 *
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<CountryItem> mValues;

    AllCountriesFragment.OnFragmentListener mListener;

    public MyItemRecyclerViewAdapter(List<CountryItem> items, AllCountriesFragment.OnFragmentListener mListener) {
        mValues = items;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_all_countries, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCountryNameView.setText(mValues.get(position).countryName);
        holder.mCountryLanguageView.setText(mValues.get(position).countryLanguages.toString());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCountryNameView;
        public final TextView mCountryLanguageView;
        public ConstraintLayout mConstraintLayout;
        public CountryItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCountryNameView =  view.findViewById(R.id.country_name);
            mCountryLanguageView = view.findViewById(R.id.country_languages);
            mConstraintLayout = view.findViewById(R.id.rootView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCountryNameView.getText() + "'";
        }
    }
}