package fr.margouill.cockpitpp.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import fr.margouill.cockpitpp.R;
import fr.margouill.cockpitpp.fragments.MyPanel_PagerAdapter;

public class MyPanel_Activity extends Module_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Bind the view
        setContentView(R.layout.activity_mypanel);

        //Load the fragments in the viewPager
        MyPanel_PagerAdapter pagerAdapter = new MyPanel_PagerAdapter(getSupportFragmentManager(),new String[] { getString(R.string.mypanel) + " "});
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);

        //Add the font in the titles of each panel
        Typeface fontTypeFace= Typeface.createFromAsset(getAssets(),"fonts/hemi_head_bd_it.ttf");
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagerTabStrip);
        for (int i = 0; i < pagerTabStrip.getChildCount(); ++i) {
            View nextChild = pagerTabStrip.getChildAt(i);
            if (nextChild instanceof TextView) {
                TextView textViewToConvert = (TextView) nextChild;
                textViewToConvert.setTypeface(fontTypeFace);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentAircraft(getString(R.string.mypanel));//Tells to Module_Activity what module the user is using
    }
}