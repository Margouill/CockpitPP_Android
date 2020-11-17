package fr.margouill.cockpitpp.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import fr.margouill.cockpitpp.R;
import fr.margouill.cockpitpp.fragments.Config_PagerAdapter;

public class Config_Activity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Bind the view
        setContentView(R.layout.activity_config);

        //Add the fragments to the viewPager
        Config_PagerAdapter pagerAdapter = new Config_PagerAdapter(getSupportFragmentManager(),new String[] { getString(R.string.settings), getString(R.string.sandbox) });
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
    }
}
