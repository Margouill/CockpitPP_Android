package fr.astazou.cockpitplusplus.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import fr.astazou.cockpitplusplus.R;
import fr.astazou.cockpitplusplus.fragments.Ka50_PagerAdapter;
import fr.astazou.cockpitplusplus.fragments.UH1H_PagerAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Ka50_Activity extends Module_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Bind the view
        setContentView(R.layout.activity_ka50);



        //Load the fragments in the viewPager
        Ka50_PagerAdapter pagerAdapter = new Ka50_PagerAdapter(getSupportFragmentManager(),new String[] { getString(R.string.ka50_armament) + " ", getString(R.string.ka50_overhead) + " ", getString(R.string.ka50_uv26) + " ", getString(R.string.ka50_prtz) + " ", getString(R.string.ka50_lwr) + " ",getString(R.string.ka50_ekran) + " ",});
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
        setCurrentAircraft("Ka-50");//Tells to Module_Activity what module the user is using
    }
}