package fr.margouill.cockpitpp.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by margouill on 09/12/2017.
 *
 */
public class AV8BNA_PagerAdapter extends FragmentPagerAdapter {

    private String tabtitles[];

    public AV8BNA_PagerAdapter(FragmentManager fm, String pTabtitles[]) {
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
                fragment = new AV8BNA_NOZZLE_Controller();
                break;
            default:
                fragment = new AV8BNA_NOZZLE_Controller();

        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}