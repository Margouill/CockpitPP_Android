package fr.astazou.cockpitplusplus.fragments;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
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
import android.widget.TextView;

import fr.astazou.cockpitplusplus.R;
import fr.astazou.cockpitplusplus.utils.BroadcastKeys;
import fr.astazou.cockpitplusplus.utils.Ka50_Commands;
import fr.astazou.cockpitplusplus.utils.UDPSender;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static android.view.Gravity.CENTER;


public class Ka50_PRTz extends Fragment {

    private ImageView mBackgroundView;
    private LinearLayout mContainer;

    // PRTz Datalink Control Panel
    private Button mPRTzVehiclePush;
    private ImageView mPRTzVehicleLed;

    private Button mPRTzSAMPush;
    private ImageView mPRTzSAMLed;

    private Button mPRTzOtherPush;
    private ImageView mPRTzOtherLed;

    private Button mPRTzPointPush;
    private ImageView mPRTzPointLed;

    private Button mPRTz1Push;
    private ImageView mPRTz1Led;

    private Button mPRTz2Push;
    private ImageView mPRTz2Led;

    private Button mPRTz3Push;
    private ImageView mPRTz3Led;

    private Button mPRTz4Push;
    private ImageView mPRTz4Led;

    private Button mPRTzAllPush;
    private ImageView mPRTzAllLed;

    private Button mPRTzEmptyPush;
    private ImageView mPRTzEmpytLed;

    private Button mPRTzClearPush;
    private ImageView mPRTzClearLed;

    private Button mPRTzIngressPush;
    private ImageView mPRTzIngressLed;

    private Button mPRTzSendPush;
    private ImageView mPRTzSendLed;

    public Ka50_PRTz() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(this.mBroadCastNewMessage, new IntentFilter(BroadcastKeys.KA50_PRTZ));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ka50_prtz, container, false);
        mBackgroundView = (ImageView) view.findViewById(R.id.layout);
        mContainer = (LinearLayout) view.findViewById(R.id.container);

        //PRTz Datalink Control Panel
        // PushButton
        mPRTzVehiclePush = (Button) view.findViewById(R.id.ka50_prtz_vehicle_bt);
        mPRTzVehiclePush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTzVehicle);
            }
        });

        mPRTzSAMPush = (Button) view.findViewById(R.id.ka50_prtz_sam_bt);
        mPRTzSAMPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTzSAM);
            }
        });

        mPRTzOtherPush = (Button) view.findViewById(R.id.ka50_prtz_other_bt);
        mPRTzOtherPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTzOther);
            }
        });

        mPRTzPointPush = (Button) view.findViewById(R.id.ka50_prtz_point_bt);
        mPRTzPointPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTzPoint);
            }
        });

        mPRTz1Push = (Button) view.findViewById(R.id.ka50_prtz_1_bt);
        mPRTz1Push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTz1);
            }
        });

        mPRTz2Push = (Button) view.findViewById(R.id.ka50_prtz_2_bt);
        mPRTz2Push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTz2);
            }
        });

        mPRTz3Push = (Button) view.findViewById(R.id.ka50_prtz_3_bt);
        mPRTz3Push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTz3);
            }
        });

        mPRTz4Push = (Button) view.findViewById(R.id.ka50_prtz_4_bt);
        mPRTz4Push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTz4);
            }
        });

        mPRTzAllPush = (Button) view.findViewById(R.id.ka50_prtz_all_bt);
        mPRTzAllPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTzAll);
            }
        });

        mPRTzEmptyPush = (Button) view.findViewById(R.id.ka50_prtz_empty_bt);
        mPRTzEmptyPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTzEmpty);
            }
        });

        mPRTzClearPush = (Button) view.findViewById(R.id.ka50_prtz_clear_bt);
        mPRTzClearPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTzClear);
            }
        });

        mPRTzIngressPush = (Button) view.findViewById(R.id.ka50_prtz_ingress_bt);
        mPRTzIngressPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTzIngress);
            }
        });

        mPRTzSendPush = (Button) view.findViewById(R.id.ka50_prtz_send_bt);
        mPRTzSendPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PRTzSend);
            }
        });

        // Leds
        mPRTzVehicleLed = (ImageView) view.findViewById(R.id.ka50_prtz_vehicle_on);
        mPRTzSAMLed = (ImageView) view.findViewById(R.id.ka50_prtz_sam_on);
        mPRTzOtherLed = (ImageView) view.findViewById(R.id.ka50_prtz_other_on);
        mPRTzPointLed = (ImageView) view.findViewById(R.id.ka50_prtz_point_on);
        mPRTz1Led = (ImageView) view.findViewById(R.id.ka50_prtz_1_on);
        mPRTz2Led = (ImageView) view.findViewById(R.id.ka50_prtz_2_on);
        mPRTz3Led = (ImageView) view.findViewById(R.id.ka50_prtz_3_on);
        mPRTz4Led = (ImageView) view.findViewById(R.id.ka50_prtz_4_on);
        mPRTzAllLed = (ImageView) view.findViewById(R.id.ka50_prtz_all_on);
        mPRTzEmpytLed = (ImageView) view.findViewById(R.id.ka50_prtz_empty_on);
        mPRTzClearLed = (ImageView) view.findViewById(R.id.ka50_prtz_clear_on);
        mPRTzIngressLed = (ImageView) view.findViewById(R.id.ka50_prtz_ingress_on);
        mPRTzSendLed = (ImageView) view.findViewById(R.id.ka50_prtz_send_on);
        return view;
    }

    BroadcastReceiver mBroadCastNewMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //Log.d("OnReceive", intent.getAction());

            if(intent.getAction().contains(BroadcastKeys.KA50_PRTZ)) {
                String PRTz_Panel = intent.getExtras().getString(BroadcastKeys.KA50_PRTZ);
                //PUI800_Panel.replace("\\n","");
                Log.d("Ka80 PRTz", PRTz_Panel);


                if(!PRTz_Panel.isEmpty()){

                    // On séparre les variables
                    String[] panel_data = PRTz_Panel.split(";");

                    for (int i=0; i< panel_data.length; i++) {
                        panel_data[i] = String.valueOf(Math.round(Float.parseFloat(panel_data[i]) * 10));
                    }

                    // Les leds
                    mPRTzVehicleLed.setVisibility(panel_data[0].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTzSAMLed.setVisibility(panel_data[1].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTzOtherLed.setVisibility(panel_data[2].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTzPointLed.setVisibility(panel_data[3].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTz1Led.setVisibility(panel_data[4].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTz2Led.setVisibility(panel_data[5].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTz3Led.setVisibility(panel_data[6].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTz4Led.setVisibility(panel_data[7].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTzAllLed.setVisibility(panel_data[8].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTzEmpytLed.setVisibility(panel_data[9].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTzClearLed.setVisibility(panel_data[10].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTzIngressLed.setVisibility(panel_data[11].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPRTzSendLed.setVisibility(panel_data[12].equals("1") ? View.VISIBLE : View.INVISIBLE);





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
