package fr.astazou.cockpitplusplus.fragments;

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
import android.widget.TextView;

import fr.astazou.cockpitplusplus.R;
import fr.astazou.cockpitplusplus.utils.BroadcastKeys;
import fr.astazou.cockpitplusplus.utils.Ka50_Commands;
import fr.astazou.cockpitplusplus.utils.UDPSender;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static android.view.Gravity.CENTER;


public class Ka50_UV26 extends Fragment {

    private ImageView mBackgroundView;
    private LinearLayout mContainer;

    // UV26
    private Button mUV26SideSelector;
    private ImageView mUV26SideSelectorLeft;
    private ImageView mUV26SideSelectorCenter;
    private ImageView mUV26SideSelectorRight;

    private Button mUV26QteSelector;
    private ImageView mUV26QteSelectorLeft;
    private ImageView mUV26QteSelectorRight;
    private ImageView mUV26LeftLed;
    private ImageView mUV26RightLed;

    private TextView mUV26TextView;

    private Button mUV26NumberPush;
    private Button mUV26SequencePush;
    private Button mUV26StopPush;
    private Button mUV26IntervalPush;
    private Button mUV26ResetPush;
    private Button mUV26StartPush;


    public Ka50_UV26() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(this.mBroadCastNewMessage, new IntentFilter(BroadcastKeys.KA50_UV26));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ka50_uv26, container, false);
        mBackgroundView = (ImageView) view.findViewById(R.id.layout);
        mContainer = (LinearLayout) view.findViewById(R.id.container);



        //UV26
        mUV26SideSelector = (Button) view.findViewById(R.id.ka50_uv26_side_bt);
        mUV26SideSelectorLeft = (ImageView) view.findViewById(R.id.ka50_uv26_side_left);
        mUV26SideSelectorCenter = (ImageView) view.findViewById(R.id.ka50_uv26_side_center);
        mUV26SideSelectorRight = (ImageView) view.findViewById(R.id.ka50_uv26_side_right);
        mUV26LeftLed = (ImageView) view.findViewById(R.id.ka50_uv26_led_left);
        mUV26RightLed = (ImageView) view.findViewById(R.id.ka50_uv26_led_right);
        mUV26TextView = (TextView) view.findViewById(R.id.ka50_uv26_tv);

        mUV26SideSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUV26SideSelectorLeft.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.UV26Side, "0.1");
                } else if (mUV26SideSelectorCenter.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.UV26Side,"0.2");
                } else {
                    sendCommand(Ka50_Commands.UV26Side, "0");
                }
            }
        });



        mUV26QteSelector = (Button) view.findViewById(R.id.ka50_uv26_quant_bt);
        mUV26QteSelectorLeft = (ImageView) view.findViewById(R.id.ka50_uv26_quant_left);
        mUV26QteSelectorRight = (ImageView) view.findViewById(R.id.ka50_uv26_quant_right);
        mUV26QteSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUV26QteSelectorLeft.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.UV26Qte, "0.1");
                } else {
                    sendCommand(Ka50_Commands.UV26Qte, "0");
                }
            }


        });


        mUV26NumberPush = (Button) view.findViewById(R.id.ka50_uv26_number_bt);
        mUV26NumberPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.UV26Number);
            }
        });

        mUV26SequencePush = (Button) view.findViewById(R.id.ka50_uv26_sequence_bt);
        mUV26SequencePush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.UV26Sequence);
            }
        });

        mUV26StopPush = (Button) view.findViewById(R.id.ka50_uv26_stop_bt);
        mUV26StopPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.UV26Stop);
            }
        });

        mUV26IntervalPush = (Button) view.findViewById(R.id.ka50_uv26_interval_bt);
        mUV26IntervalPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.UV26Interval);
            }
        });

        mUV26ResetPush = (Button) view.findViewById(R.id.ka50_uv26_reset_bt);
        mUV26ResetPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.UV26Reset);
            }
        });

        mUV26StartPush = (Button) view.findViewById(R.id.ka50_uv26_start_bt);
        mUV26StartPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.UV26Start);
            }
        });




        return view;
    }

    BroadcastReceiver mBroadCastNewMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //Log.d("OnReceive", intent.getAction());
            //Log.d("Ka80 UV26", UV26_Panel);

            if(intent.getAction().contains(BroadcastKeys.KA50_UV26)) {
                String UV26_Panel = intent.getExtras().getString(BroadcastKeys.KA50_UV26);
                //PUI800_Panel.replace("\\n","");
                //Log.d("Ka80 UV26", UV26_Panel);


                if(!UV26_Panel.isEmpty()){

                    // On séparre les variables
                    String[] panel_data = UV26_Panel.split(";");


                    // Les switchs
                    panel_data[0] = String.valueOf(Math.round(Float.parseFloat(panel_data[0])*10));
                    mUV26SideSelectorLeft.setVisibility(panel_data[0].equals("0") ? View.VISIBLE : View.INVISIBLE);
                    mUV26SideSelectorCenter.setVisibility(panel_data[0].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mUV26SideSelectorRight.setVisibility(panel_data[0].equals("2") ? View.VISIBLE : View.INVISIBLE);

                    panel_data[1] = String.valueOf(Math.round(Float.parseFloat(panel_data[1])*10));
                    mUV26QteSelectorLeft.setVisibility(panel_data[1].equals("0") ? View.VISIBLE : View.INVISIBLE);
                    mUV26QteSelectorRight.setVisibility(panel_data[1].equals("1") ? View.VISIBLE : View.INVISIBLE);

                    // Les leds
                    mUV26LeftLed.setVisibility(panel_data[2].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mUV26RightLed.setVisibility(panel_data[3].equals("1") ? View.VISIBLE : View.INVISIBLE);

                    // Les afficheurs 7S
                    mUV26TextView.setText(panel_data[4].equals("---") ? "   " : panel_data[4]);
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
