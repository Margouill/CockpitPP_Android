package fr.margouill.cockpitpp.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * reated by margouill on 10/06/2018.
 */
public class MyPanel_PagerAdapter extends FragmentPagerAdapter {

    private String tabtitles[];

    public MyPanel_PagerAdapter(FragmentManager fm, String pTabtitles[]) {
        super(fm);
        tabtitles = pTabtitles;
    }

    @Override
    public int getCount() {
        return tabtitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new MyPanel_1();
                break;
            default:
                fragment = new MyPanel_1();

        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}