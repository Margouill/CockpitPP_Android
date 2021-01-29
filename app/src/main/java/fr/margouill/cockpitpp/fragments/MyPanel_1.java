package fr.margouill.cockpitpp.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import fr.margouill.cockpitpp.R;
import fr.margouill.cockpitpp.activities.M2kC_Activity;
import fr.margouill.cockpitpp.activities.M2kC_Activity_Tablet;
import fr.margouill.cockpitpp.activities.Menu_Activity;
import fr.margouill.cockpitpp.utils.BroadcastKeys;
import fr.margouill.cockpitpp.utils.UDPSender;

import static android.view.Gravity.CENTER;


public class MyPanel_1 extends Fragment {

    /**mBackgroundView and mConstainer are used to mange the rotation properly according to the image you will have in background**/
    private ImageView mBackgroundView;
    private LinearLayout mContainer;

    private Button [] MesBoutons;
    private Object [] MesParametres;

    public MyPanel_1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(this.mBroadCastNewMessage, new IntentFilter(BroadcastKeys.MYPANEL_1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypanel_1, container, false);
        mBackgroundView = (ImageView) view.findViewById(R.id.layout);
        mContainer = (LinearLayout) view.findViewById(R.id.container);

        MesBoutons[1] = (Button) view.findViewById(R.id.bt1);
        MesParametres[1] = new int[]{1, 1, 1, 1};


        MesBoutons[2]  = (Button) view.findViewById(R.id.bt2);
        MesParametres[2] = new int[]{1, 1, 1, 1};



        MesBoutons[1].setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                // On affiche une boite de dialogue

                //Dialog dialog = new Dialog();
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                //dialog.setContentView(R.layout.mypanel_config);

                //MesBoutons[1] = dialog.findViewById(R.id.bt)

                //LinearLayout btn_phone = (LinearLayout) dialog.findViewById(R.id.valider);
                //btn_phone.setOnClickListener(new View.OnClickListener() {
                //    @Override
                //    public void onClick(View v) {
                //        dialog.dismiss();
                //        startActivity(M2kC_Activity.class);
                //    }
                //});

                //dialog.show();


                return false;
            }
        });

        MesBoutons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //UDPSender.getInstance().sendToDCS();


            }
        });



        return view;
    }

    BroadcastReceiver mBroadCastNewMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().contains(BroadcastKeys.MYPANEL_1)) {

            }

        }
    };

    private void sendCommand(int pType, int pCommandCode, int pDevice, String pValue) {
        UDPSender.getInstance().sendToDCS(pType, pDevice, pCommandCode, pValue, getContext());

    }

    /**
     * Call resizeView to draw correctly the buttons/images over the background when arriving on the fragment
     */
    @Override
    public void onResume() {
        super.onResume();
        resizeView();
    }

    /**
     * Used to detect rotation of the phone to make the buttons/images zesized according to the background image
     * @param newConfig default
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        resizeView();
    }

    /**
     * ResizeView is made to draw correctly the buttons/images over the background.
     */
    private void resizeView() {
        mBackgroundView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @SuppressLint("NewApi")
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            mBackgroundView.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        } else {
                            mBackgroundView.getViewTreeObserver()
                                    .removeOnGlobalLayoutListener(this);
                        }
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(mBackgroundView.getLayoutParams().width, mBackgroundView.getLayoutParams().height);
                        layoutParams.gravity = CENTER;
                        mContainer.setLayoutParams(layoutParams);
                        mContainer.getLayoutParams().height = mBackgroundView.getHeight();
                        mContainer.getLayoutParams().width = mBackgroundView.getWidth();
                    }
                });
    }

    /**
     * If you don't unregister you can have weird behaviour coming back on this fragment
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadCastNewMessage);
    }
}
