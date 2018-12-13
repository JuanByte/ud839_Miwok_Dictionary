package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * {@link CategoriesPagerAdapter} is a {@link FragmentPagerAdapter} that can provide the layout for
 * each list item based on a data source which is a list of {@link Word} objects.
 */
public class CategoriesPagerAdapter extends FragmentPagerAdapter {
    /**
     * Number of pages
     */
    final int PAGE_COUNT = 4;
    /**
     * Array that store the tabs title of each page
     */
    private int tabTitles[] = new int[]{R.string.category_numbers, R.string.category_family, R.string.category_colors, R.string.category_phrases};
    /**
     * Context of the app
     */
    private Context mContext;

    /**
     * Create a new category adapter
     *
     * @param fm
     */
    public CategoriesPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Set getItem method to show each category depending on the position of the in the adapter
     *
     * @param position indicates the current position in the adapter
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
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
     *
     * @return int with the number of pages
     */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    /**
     * Set the title for each page depending on current position
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(tabTitles[position]);
    }
}
