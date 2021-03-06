package fr.margouill.cockpitpp.fragments;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import fr.margouill.cockpitpp.R;
import fr.margouill.cockpitpp.utils.BroadcastKeys;
import fr.margouill.cockpitpp.utils.Ka50_Commands;
import fr.margouill.cockpitpp.utils.UDPSender;

import static android.view.Gravity.CENTER;


public class Ka50_LWR extends Fragment {

    private ImageView mBackgroundView;
    private LinearLayout mContainer;

    //LWR
    private ImageView mLWRLeftLed;
    private ImageView mLWRTopLed;
    private ImageView mLWRRightLed;
    private ImageView mLWRBottomLed;

    private ImageView mLWRAboveLed;
    private ImageView mLWRUnderLed;
    private ImageView mLWRRfLed;
    private ImageView mLWRTriLed;

    private Button mLWRResetPush;


    public Ka50_LWR() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(this.mBroadCastNewMessage, new IntentFilter(BroadcastKeys.KA50_LWR));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ka50_lwr, container, false);
        mBackgroundView = (ImageView) view.findViewById(R.id.layout);
        mContainer = (LinearLayout) view.findViewById(R.id.container);

        mLWRLeftLed = (ImageView) view.findViewById(R.id.ka50_lwr_left);
        mLWRTopLed = (ImageView) view.findViewById(R.id.ka50_lwr_top);
        mLWRRightLed = (ImageView) view.findViewById(R.id.ka50_lwr_right);
        mLWRBottomLed = (ImageView) view.findViewById(R.id.ka50_lwr_bottom);
        mLWRAboveLed = (ImageView) view.findViewById(R.id.ka50_lwr_above);
        mLWRUnderLed = (ImageView) view.findViewById(R.id.ka50_lwr_below);
        mLWRRfLed = (ImageView) view.findViewById(R.id.ka50_lwr_rf);
        mLWRTriLed = (ImageView) view.findViewById(R.id.ka50_lwr_tri);






        //LWR PushButton
        mLWRResetPush = (Button) view.findViewById(R.id.ka50_lwr_reset_bt);
        mLWRResetPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.LWRReset);
            }
        });

        return view;
    }

    BroadcastReceiver mBroadCastNewMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //Log.d("OnReceive", intent.getAction());
            if(intent.getAction().contains(BroadcastKeys.KA50_LWR)) {
                String LWR_Panel = intent.getExtras().getString(BroadcastKeys.KA50_LWR);
                //LWR_Panel.replace("\\n","");
                //Log.d("Ka80 LWR", LWR_Panel);


                if(!LWR_Panel.isEmpty()){

                    // On séparre les variables
                    String[] panel_data = LWR_Panel.split(";");

                    // Les leds
                     mLWRLeftLed.setVisibility(panel_data[0].equals("1") ? View.VISIBLE : View.INVISIBLE);
                     mLWRTopLed.setVisibility(panel_data[1].equals("1") ? View.VISIBLE : View.INVISIBLE);
                     mLWRRightLed.setVisibility(panel_data[2].equals("1") ? View.VISIBLE : View.INVISIBLE);
                     mLWRBottomLed.setVisibility(panel_data[3].equals("1") ? View.VISIBLE : View.INVISIBLE);
                     mLWRAboveLed.setVisibility(panel_data[4].equals("1") ? View.VISIBLE : View.INVISIBLE);
                     mLWRUnderLed.setVisibility(panel_data[5].equals("1") ? View.VISIBLE : View.INVISIBLE);
                     mLWRRfLed.setVisibility(panel_data[6].equals("1") ? View.VISIBLE : View.INVISIBLE);
                     mLWRTriLed.setVisibility(panel_data[7].equals("1") ? View.VISIBLE : View.INVISIBLE);
                }
            }


        }
    };



    private float getDegreesKnob(float value) {
        //Log.v(getTag(),"value: " + value);
        if(value < 0.075) {
            return 0;
        } else if(value < 0.125) {
            return 36;
        } else if(value < 0.225) {
            return 72;
        } else if(value < 0.325) {
            return 108;
        } else if(value < 0.425) {
            return 144;
        } else if(value < 0.525) {
            return 180;
        } else if(value < 0.625) {
            return 216;
        } else {
            return 252;
        }
    }




    private float getValueKnob(float value) {
        Log.v(getTag(),"value: " + value);
        if(value < 10) {
            return 0;
        } else if(value < 40) {
            return 0.099998474121094f;
        } else if(value < 76) {
            return 0.19999694824219f;
        } else if(value < 110) {
            return 0.29999542236328f;
        } else if(value < 146) {
            return 0.39999389648438f;
        } else if(value < 185) {
            return 0.49999237060547f;
        } else if(value < 220) {
            return 0.59999084472656f;
        } else {
            return 0.69998931884766f;
        }
    }




    private void sendCommand(Ka50_Commands pCommand) {
        sendCommand(pCommand, "1");
    }

    private void sendCommand(Ka50_Commands pCommand, String pValue) {
        UDPSender.getInstance().sendToDCS(pCommand.getTypeButton().getCode(),pCommand.getDevice().getCode(),pCommand.getCode(), pValue,getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        resizeView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        resizeView();
    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadCastNewMessage);
    }
}
