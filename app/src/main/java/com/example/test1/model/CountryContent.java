package com.example.test1.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class CountryContent {

    public static final List<CountryItem> ITEMS = new ArrayList<>();

    public static final List<CountryItem> PREF_ITEMS = new ArrayList<>();


    /**
     * A dummy item representing a piece of content.
     */
    public static class CountryItem {
        public final String countryName;
        public final ArrayList<String> countryLanguages;

        public CountryItem(String countryName, ArrayList<String> countryLanguages) {
            this.countryName = countryName;
            this.countryLanguages = countryLanguages;
        }

        @Override
        public String toString() {
            return countryName;
        }
    }
}