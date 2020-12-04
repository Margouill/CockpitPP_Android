package fr.margouill.cockpitpp.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by margouill on 04/09/2016.
 *
 */
public class Ka50_PagerAdapter extends FragmentPagerAdapter {

    private String[] tabtitles;

    public Ka50_PagerAdapter(FragmentManager fm, String[] pTabtitles) {
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
                fragment = new Ka50_PUI800();
                break;
            case 1:
                fragment = new Ka50_Overhead();
                break;
            case 2:
                fragment = new Ka50_UV26();
                break;
            case 3:
                fragment = new Ka50_PRTz();
                break;
            case 4:
                fragment = new Ka50_LWR();
                break;
            case 5:
                fragment = new Ka50_EKRAN();
                break;

            default:
                fragment = new Ka50_PUI800();

        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}