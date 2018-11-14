package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CategoriesPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Numbers", "Family", "Colors", "Phrases"};

    public CategoriesPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    /**
     * Set getItem method to show each category depending on the position of the in the adapter
     * @param position indicates the current position in the adapter
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NumbersFragment();
            case 1:
                return new FamilyFragment();
            case 2:
                return new ColorsFragment();
            case 3:
                return new PhrasesFragment();
        }
        return null;
    }
    /**
     * Set the adapter to 4 pager
     * @return int with the number of pages
     */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];

    }
}
