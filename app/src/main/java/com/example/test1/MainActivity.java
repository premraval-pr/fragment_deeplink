package com.example.test1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.test1.model.CountryContent;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.function.Predicate;

public class MainActivity extends AppCompatActivity implements AllCountriesFragment.OnFragmentListener {

    View rootView;
    Spinner maxWishListSpinner;
    AllCountriesFragment allCountriesFragment,prefCountriesFragment;
    int prefMaxCountry = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Locale[] locales = Locale.getAvailableLocales();
        rootView = findViewById(R.id.root);
        HashMap<String,ArrayList<String>> hashMap = new HashMap<>();
        maxWishListSpinner = findViewById(R.id.prefSize_spin);
        ArrayList<String> temp;

        for(Locale s:locales){
            temp = new ArrayList<>();
            if(!s.getDisplayCountry().equals("")){
                if(hashMap.containsKey(s.getDisplayCountry()))
                    temp = hashMap.get(s.getDisplayCountry());
                temp.add(s.getDisplayLanguage());
                hashMap.put(s.getDisplayCountry(),temp);
            }
        }

        hashMap.remove("World");

        Object[] keyArray = hashMap.keySet().toArray();

        Arrays.sort(keyArray);

        for(Object key : keyArray){
            CountryContent.ITEMS.add(new CountryContent.CountryItem(key.toString(),hashMap.get(key)));
        }



        allCountriesFragment = new AllCountriesFragment(CountryContent.ITEMS,this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.host1,allCountriesFragment)
                .commit();


        prefCountriesFragment = new AllCountriesFragment(CountryContent.PREF_ITEMS,this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.host2,prefCountriesFragment)
                .commit();
        maxWishListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prefMaxCountry = Integer.parseInt(maxWishListSpinner.getSelectedItem().toString());
                if(CountryContent.PREF_ITEMS.size()>prefMaxCountry){
                    CountryContent.PREF_ITEMS.subList(prefMaxCountry, CountryContent.PREF_ITEMS.size()).clear();
                    Snackbar.make(rootView,"Selected size less than countries added, extra countries removed.",Snackbar.LENGTH_LONG).show();
                    prefCountriesFragment.dataSetChange();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(final CountryContent.CountryItem countryItem) {
        if(!CountryContent.PREF_ITEMS.stream().anyMatch(new Predicate<CountryContent.CountryItem>() {
            @Override
            public boolean test(CountryContent.CountryItem x) {
                return x.countryName.equals(countryItem.countryName);
            }
        })){
            int prefListSize = CountryContent.PREF_ITEMS.size()-1;
            if(prefListSize==prefMaxCountry-1){
                CountryContent.PREF_ITEMS.remove(prefListSize);
                CountryContent.PREF_ITEMS.add(prefListSize,
                        new CountryContent.CountryItem(countryItem.countryName,countryItem.countryLanguages));
                Snackbar.make(rootView,"Your Preferred list is full. Last country replaced.",Snackbar.LENGTH_SHORT).show();
            }
            else {
                CountryContent.PREF_ITEMS.add(new CountryContent.CountryItem(countryItem.countryName,countryItem.countryLanguages));
                Snackbar.make(rootView,"Country Added to your Preferred List.",Snackbar.LENGTH_SHORT).show();
            }
            prefCountriesFragment.dataSetChange();
        }
        else{
            Snackbar.make(rootView,"Country already present.",Snackbar.LENGTH_SHORT).show();
        }
    }

    public void resetList(View view) {
        CountryContent.PREF_ITEMS.clear();
        prefCountriesFragment.dataSetChange();
        Snackbar.make(rootView,"Preferred list clear.",Snackbar.LENGTH_SHORT).show();
    }
}