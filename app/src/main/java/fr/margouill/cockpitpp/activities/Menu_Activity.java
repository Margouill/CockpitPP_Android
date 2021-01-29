package fr.margouill.cockpitpp.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import fr.margouill.cockpitpp.R;
import fr.margouill.cockpitpp.services.Konector;

public class Menu_Activity extends Activity {

    //Looger
    private String LOGGER = this.getClass().getSimpleName();

    //To know if the user pressed the button in the last 2 seconds
    private boolean mBackButtonAlreadyPressed;

    //Boolean to know if service to communicate with DCS is started
    private boolean mServiceKonectorStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Bind the view
        setContentView(R.layout.activity_menu);

        //Get the version of the app
        String versionName = "";
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("",e.getMessage(),e);
        }

        //Display the current version of the app
        TextView textViewVersionInfo = (TextView) findViewById(R.id.subVersion);
        String versionDisplayed = "Version " + versionName;
        textViewVersionInfo.setText(versionDisplayed);

        //Bind the buttons from the screen
        Button myPanel = (Button) findViewById(R.id.mypanel);
        Button a10c = (Button) findViewById(R.id.a10c);
        Button av8bna = (Button) findViewById(R.id.av8bna);
        Button f15c = (Button) findViewById(R.id.f15c);
        Button huey = (Button) findViewById(R.id.huey);
        Button mig21 = (Button) findViewById(R.id.mig21);
        Button mirage = (Button) findViewById(R.id.mirage);
        Button ka50 = (Button) findViewById(R.id.ka50);
        Button guide = (Button) findViewById(R.id.guide);
        Button notworking = (Button) findViewById(R.id.not_working);
        Button config = (Button) findViewById(R.id.config);
        Button about = (Button) findViewById(R.id.about);
        Button faq = (Button) findViewById(R.id.faq);
        Button exit = (Button) findViewById(R.id.exit);


        //Bind the Discord button
        ImageView discord = (ImageView) findViewById(R.id.menuDiscord);
        discord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse(getString(R.string.discord));
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        //Set the actions on the buttons
        myPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, MyPanel_Activity.class);
                startActivity(intent);
            }
        });
        av8bna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, AV8BNA_Activity.class);
                startActivity(intent);
            }
        });
        mig21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, MiG21Bis_Activity.class);
                startActivity(intent);
            }
        });
        mirage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(Menu_Activity.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.mode_chooser);

                LinearLayout btn_tablet = (LinearLayout) dialog.findViewById(R.id.btn_tablet);
                btn_tablet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(M2kC_Activity_Tablet.class);
                    }
                });

                LinearLayout btn_phone = (LinearLayout) dialog.findViewById(R.id.btn_phone);
                btn_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(M2kC_Activity.class);
                    }
                });

                dialog.show();
            }
        });
        a10c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(Menu_Activity.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.mode_chooser);

                LinearLayout btn_tablet = (LinearLayout) dialog.findViewById(R.id.btn_tablet);
                btn_tablet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(A10C_Activity_Tablet.class);
                    }
                });

                LinearLayout btn_phone = (LinearLayout) dialog.findViewById(R.id.btn_phone);
                btn_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startActivity(A10C_Activity.class);
                    }
                });

                dialog.show();
            }
        });
        /*a10c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, A10C_Activity.class);
                startActivity(intent);
            }
        });*/
        huey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, UH1H_Activity.class);
                startActivity(intent);
            }
        });
        ka50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, Ka50_Activity.class);
                startActivity(intent);
            }
        });

        f15c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, F15C_Activity.class);
                startActivity(intent);
            }
        });
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, Config_Activity.class);
                startActivity(intent);
            }
        });
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, Guide_Activity.class);
                startActivity(intent);
            }
        });
        notworking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, Not_Working_Activity.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, About_Activity.class);
                startActivity(intent);
            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_Activity.this, FAQ_Activity.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Check if the user has to install a new app
        checkNewVersion();
    }

    /**
     * Get the version of the last time the user used the app, if the version augmented after
     * an upgrade we show a popup to tell user to install the new Cockpit.lua
     */
    private void checkNewVersion() {
        //Get the last version used by the user
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        float version = prefs.getFloat(getString(R.string.PREFERENCES_ANDROID_APP_VERSION), 1);

        //Get the current version of the app
        String versionName = "";
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOGGER,e.getMessage(),e);
        }

        //If there is a new version since last launch, tell to the user in a popup to install the
        //new Cockpit++.lua
        if(version < Float.parseFloat(versionName)) {
            prefs.edit().putFloat(getString(R.string.PREFERENCES_ANDROID_APP_VERSION),Float.parseFloat(versionName)).apply();
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(Menu_Activity.this, android.R.style.Theme_Material_Dialog_Alert);
            builder.setTitle(getString(R.string.app_name))
                    .setMessage(getString(R.string.need_update_lua))
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                        }
                    })
                    .setNegativeButton(R.string.changelog, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            showChangelog();
                        }
                    })
                    .setIcon(R.mipmap.mirage_icon)
                    .show();
        } else {
            //Nothing to do
        }
    }

    /**
     * After an update, user can see the changelog
     */
    private void showChangelog() {
        //Get the current version
        String versionName = "";
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOGGER,e.getMessage(),e);
        }

        //Fill the Dialog
        final String version = versionName;
        final String changelog = getString(R.string.changelog_8)
                + "\n" + getString(R.string.changelog_7)
                + "\n" + getString(R.string.changelog_6)
                + "\n" + getString(R.string.changelog_5)
                + "\n" + getString(R.string.changelog_4)
                + "\n" + getString(R.string.changelog_3)
                + "\n" + getString(R.string.changelog_2)
                + "\n" + getString(R.string.changelog_1);

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle(getString(R.string.changelog) + " " + version)
                .setMessage(changelog)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(R.mipmap.mirage_icon)
                .show();
    }

    /**
     * Need to start the service to communicate with DCS
     * @param tag
     */
    private void processStartService(final String tag) {
        Log.v(LOGGER,"processStartService: " + tag);
        mServiceKonectorStarted = true;
        Intent intent = new Intent(getApplicationContext(), Konector.class);
        intent.addCategory(tag);
        startService(intent);
    }

    /**
     * Need to stop the service
     * @param tag id
     */
    private void processStopService(final String tag) {
        Log.v(LOGGER,"processStopService: " + tag);
        mServiceKonectorStarted = false;
        Intent intent = new Intent(getApplicationContext(), Konector.class);
        intent.addCategory(tag);
        stopService(intent);
    }



    @Override
    protected void onResume() {
        super.onResume();

        //If we come back from a module activity, we need to stop the service and launch it again (Restart doesn't do the job properly)
        if(mServiceKonectorStarted) {
            processStopService(Konector.TAG);
        }

        //Start the service to get the data from DCS and send actions to the simulator
        processStartService(Konector.TAG);
    }

    /**
     * Override the backButton to be sure the user wants to leave the app, to prevent a wrong action
     */
    @Override
    public void onBackPressed() {
        if (mBackButtonAlreadyPressed) {
            super.onBackPressed();
        } else {
            mBackButtonAlreadyPressed = true;
            Toast.makeText(getApplicationContext(), getString(R.string.quitAgain), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    mBackButtonAlreadyPressed = false;
                }
            }, 2000);
        }
    }

    /**
     * Stop the service for broadcasting when leaving the app
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        processStopService(Konector.TAG);
    }

    /**
     * Just show a Toast
     * @param stringId the id of the message to show
     */
    public void showToast(int stringId){
        Toast.makeText(this,stringId,Toast.LENGTH_SHORT).show();
    }


    /**
     * Use to call activity from Dialog
     * @param pActivity
     */
    public void startActivity(Class pActivity) {
        startActivity(new Intent(Menu_Activity.this, pActivity));
    }
}

