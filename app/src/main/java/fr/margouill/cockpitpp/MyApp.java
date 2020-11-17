package fr.margouill.cockpitpp;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by margouill on 23/04/2017.
 * Modified by Margouill' on 15/11/2020.
 *
 */
public class MyApp extends Application{

    public static String LUA_VERSION = "5";

    @Override
    public void onCreate() {
        super.onCreate();


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/calibri.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
}
