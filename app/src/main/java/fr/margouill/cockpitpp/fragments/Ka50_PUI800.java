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
import android.support.v4.content.res.ResourcesCompat;
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

import fr.margouill.cockpitpp.R;
import fr.margouill.cockpitpp.utils.BroadcastKeys;
import fr.margouill.cockpitpp.utils.Ka50_Commands;
import fr.margouill.cockpitpp.utils.UDPSender;

import static android.view.Gravity.CENTER;


public class Ka50_PUI800 extends Fragment {

    private ImageView mBackgroundView;
    private LinearLayout mContainer;

    private Button mPUI800AAJettisonPush;
    private ImageView mPUI800AAJettisonPushPressed;

    private Button mPUI800ExtJettisonPush;
    private ImageView mPUI800ExtJettisonPushPressed;

    private Button mPUI800ModSelector;
    private ImageView mPUI800ModSelectorUp;
    private ImageView mPUI800ModSelectorDown;

    private Button mPUI800RateSelector;
    private ImageView mPUI800RateSelectorUp;
    private ImageView mPUI800RateSelectorDown;

    private Button mPUI800BurstSelector;
    private ImageView mPUI800BurstSelectorUp;
    private ImageView mPUI800BurstSelectorMid;
    private ImageView mPUI800BurstSelectorDown;

    private Button mPUI800MasterArmSelector;
    private ImageView mPUI800MasterArmSelectorUp;
    private ImageView mPUI800MasterArmSelectorDown;

    private Button mPUI800RoundSelector;
    private ImageView mPUI800RoundSelectorUp;
    private ImageView mPUI800RoundSelectorDown;

    private Button mPUI800ArmJettisonSelector;
    private ImageView mPUI800ArmJettisonSelectorUp;
    private ImageView mPUI800ArmJettisonSelectorDown;

    private Button mPUI800JettisonATGMSelector;
    private ImageView mPUI800JettisonATGMSelectorUp;
    private ImageView mPUI800JettisonATGMSelectorDown;

    private ImageView mPUI800PresentLed1;
    private ImageView mPUI800PresentLed2;
    private ImageView mPUI800PresentLed3;
    private ImageView mPUI800PresentLed4;

    private ImageView mPUI800ReadyLed1;
    private ImageView mPUI800ReadyLed2;
    private ImageView mPUI800ReadyLed3;
    private ImageView mPUI800ReadyLed4;

    private TextView mPUI800WeaponTypeTextView;
    private TextView mPUI800WeaponRemainTextView;
    private TextView mPUI800CanonRemainTextView;



    public Ka50_PUI800() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(this.mBroadCastNewMessage, new IntentFilter(BroadcastKeys.KA50_PUI800));



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ka50_armament, container, false);

        mBackgroundView = (ImageView) view.findViewById(R.id.layout);
        mContainer = (LinearLayout) view.findViewById(R.id.container);

        // Actions de tous mes boutons

        mPUI800AAJettisonPush = (Button) view.findViewById(R.id.ka50_pui800_aa_jettison);
        mPUI800AAJettisonPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sendCommand(Ka50_Commands.PUI800JettisonAA);
                }
        });

        mPUI800ExtJettisonPush = (Button) view.findViewById(R.id.ka50_pui800_ext_jettison);
        mPUI800ExtJettisonPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.PUI800JettisonExt);
            }
        });

        mPUI800ModSelector = (Button) view.findViewById(R.id.ka50_pui800_panel_mode_sw);
        mPUI800ModSelectorUp = (ImageView) view.findViewById(R.id.ka50_pui800_mode_sw_up);
        mPUI800ModSelectorDown = (ImageView) view.findViewById(R.id.ka50_pui800_mode_sw_down);
        mPUI800ModSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPUI800ModSelectorDown.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.PUI800Mode,"1");
                } else {
                    sendCommand(Ka50_Commands.PUI800Mode,"0");
                }
            }
        });


        mPUI800RateSelector = (Button) view.findViewById(R.id.ka50_pui800_rate_sw);
        mPUI800RateSelectorUp = (ImageView) view.findViewById(R.id.ka50_pui800_rate_sw_up);
        mPUI800RateSelectorDown = (ImageView) view.findViewById(R.id.ka50_pui800_rate_sw_down);
        mPUI800RateSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPUI800RateSelectorDown.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.PUI800Rate,"1");
                } else {
                    sendCommand(Ka50_Commands.PUI800Rate,"0");
                }
            }
        });

        mPUI800RoundSelector = (Button) view.findViewById(R.id.ka50_pui800_round_sw);
        mPUI800RoundSelectorUp = (ImageView) view.findViewById(R.id.ka50_pui800_round_sw_up);
        mPUI800RoundSelectorDown = (ImageView) view.findViewById(R.id.ka50_pui800_round_sw_down);
        mPUI800RoundSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPUI800RoundSelectorDown.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.PUI800Round, "1");
                } else {
                    sendCommand(Ka50_Commands.PUI800Round,"0");
                }
            }
        });

        mPUI800BurstSelector = (Button) view.findViewById(R.id.ka50_pui800_burst_sw);
        mPUI800BurstSelectorUp = (ImageView) view.findViewById(R.id.ka50_pui800_burst_sw_up);
        mPUI800BurstSelectorMid = (ImageView) view.findViewById(R.id.ka50_pui800_burst_sw_mid);
        mPUI800BurstSelectorDown = (ImageView) view.findViewById(R.id.ka50_pui800_burst_sw_down);
        mPUI800BurstSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPUI800BurstSelectorDown.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.PUI800Burst, "0.1");
                } else if(mPUI800BurstSelectorMid.getVisibility() == View.VISIBLE){
                    sendCommand(Ka50_Commands.PUI800Burst, "0.2");
                } else {
                    sendCommand(Ka50_Commands.PUI800Burst,"0");
                }
            }
        });


        mPUI800MasterArmSelector = (Button) view.findViewById(R.id.ka50_overhead_nav_sw);
        mPUI800MasterArmSelectorUp = (ImageView) view.findViewById(R.id.ka50_overhead_nav_sw_up);
        mPUI800MasterArmSelectorDown = (ImageView) view.findViewById(R.id.ka50_overhead_nav_sw_down);
        mPUI800MasterArmSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPUI800MasterArmSelectorDown.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.PUI800MasterArm, "1");
                } else {
                    sendCommand(Ka50_Commands.PUI800MasterArm,"0");
                }
            }
        });


        mPUI800ArmJettisonSelector = (Button) view.findViewById(R.id.ka50_pui800_arm_jettison_sw);
        mPUI800ArmJettisonSelectorUp = (ImageView) view.findViewById(R.id.ka50_pui800_arm_jettison_sw_up);
        mPUI800ArmJettisonSelectorDown = (ImageView) view.findViewById(R.id.ka50_pui800_arm_jettison_sw_down);
        mPUI800ArmJettisonSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPUI800ArmJettisonSelectorDown.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.PUI800JettisonArm, "1");
                } else {
                    sendCommand(Ka50_Commands.PUI800JettisonArm,"0");
                }
            }
        });


        mPUI800JettisonATGMSelector = (Button) view.findViewById(R.id.ka50_pui800_jettison_atgm_sw);
        mPUI800JettisonATGMSelectorUp = (ImageView) view.findViewById(R.id.ka50_pui800_jettison_atgm_sw_up);
        mPUI800JettisonATGMSelectorDown = (ImageView) view.findViewById(R.id.ka50_pui800_jettison_atgm_sw_down);
        mPUI800JettisonATGMSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPUI800JettisonATGMSelectorDown.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.PUI800JettisonATGM, "1");
                } else {
                    sendCommand(Ka50_Commands.PUI800JettisonATGM,"0");
                }
            }
        });

        mPUI800PresentLed1 = (ImageView) view.findViewById(R.id.ka50_pui800_present_led1);
        mPUI800PresentLed2 = (ImageView) view.findViewById(R.id.ka50_pui800_present_led2);
        mPUI800PresentLed3 = (ImageView) view.findViewById(R.id.ka50_pui800_present_led3);
        mPUI800PresentLed4 = (ImageView) view.findViewById(R.id.ka50_pui800_present_led4);

        mPUI800ReadyLed1 = (ImageView) view.findViewById(R.id.ka50_pui800_ready_led1);
        mPUI800ReadyLed2 = (ImageView) view.findViewById(R.id.ka50_pui800_ready_led2);
        mPUI800ReadyLed3 = (ImageView) view.findViewById(R.id.ka50_pui800_ready_led3);
        mPUI800ReadyLed4 = (ImageView) view.findViewById(R.id.ka50_pui800_ready_led4);

        mPUI800WeaponTypeTextView = (TextView) view.findViewById(R.id.ka50_pui800_type_textview);
        mPUI800WeaponRemainTextView = (TextView) view.findViewById(R.id.ka50_pui800_weaponremain_textview);
        mPUI800CanonRemainTextView = (TextView) view.findViewById(R.id.ka50_pui800_canonremain_textview);

        // Rétro compatibilité API29-
        mPUI800WeaponTypeTextView.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.ttf_digital_display_tfb));
        mPUI800WeaponRemainTextView.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.ttf_digital_display_tfb));
        mPUI800CanonRemainTextView.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.ttf_digital_display_tfb));

        return view;
    }

    BroadcastReceiver mBroadCastNewMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Log.d("OnReceive", intent.getAction());
            if(intent.getAction().contains(BroadcastKeys.KA50_PUI800)) {
                String PUI800_Panel = intent.getExtras().getString(BroadcastKeys.KA50_PUI800);
                //Log.d("Ka80 PUI800", PUI800_Panel);

                if(!PUI800_Panel.isEmpty()){

                    // On séparre les variables
                    String[] panel_data = PUI800_Panel.split(";");

                    // Les switchs
                    mPUI800MasterArmSelectorUp.setVisibility(panel_data[0].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800MasterArmSelectorDown.setVisibility(panel_data[0].equals("0") ? View.VISIBLE : View.INVISIBLE);

                    mPUI800ArmJettisonSelectorUp.setVisibility(panel_data[1].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800ArmJettisonSelectorDown.setVisibility(panel_data[1].equals("0") ? View.VISIBLE : View.INVISIBLE);

                    mPUI800JettisonATGMSelectorUp.setVisibility(panel_data[2].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800JettisonATGMSelectorDown.setVisibility(panel_data[2].equals("0") ? View.VISIBLE : View.INVISIBLE);

                    mPUI800RateSelectorUp.setVisibility(panel_data[3].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800RateSelectorDown.setVisibility(panel_data[3].equals("0") ? View.VISIBLE : View.INVISIBLE);

                    mPUI800RoundSelectorUp.setVisibility(panel_data[4].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800RoundSelectorDown.setVisibility(panel_data[4].equals("0") ? View.VISIBLE : View.INVISIBLE);

                    panel_data[5] = String.valueOf(Math.round(Float.parseFloat(panel_data[5])*10));
                    mPUI800BurstSelectorUp.setVisibility(panel_data[5].equals("2") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800BurstSelectorMid.setVisibility(panel_data[5].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800BurstSelectorDown.setVisibility(panel_data[5].equals("0") ? View.VISIBLE : View.INVISIBLE);

                    mPUI800ModSelectorUp.setVisibility(panel_data[8].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800ModSelectorDown.setVisibility(panel_data[8].equals("0") ? View.VISIBLE : View.INVISIBLE);


                    // Les leds
                    mPUI800ReadyLed1.setVisibility(panel_data[9].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800ReadyLed2.setVisibility(panel_data[10].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800ReadyLed3.setVisibility(panel_data[11].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800ReadyLed4.setVisibility(panel_data[12].equals("1") ? View.VISIBLE : View.INVISIBLE);

                    mPUI800PresentLed1.setVisibility(panel_data[13].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800PresentLed2.setVisibility(panel_data[14].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800PresentLed3.setVisibility(panel_data[15].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mPUI800PresentLed4.setVisibility(panel_data[16].equals("1") ? View.VISIBLE : View.INVISIBLE);

                    // Les afficheurs 7S
                    mPUI800WeaponTypeTextView.setText(panel_data[17].equals("---") ? "   " : panel_data[17]);
                    mPUI800WeaponRemainTextView.setText(panel_data[18].equals("---") ? "   " : panel_data[18]);
                    mPUI800CanonRemainTextView.setText(panel_data[19].equals("---") ? "   " : panel_data[19]);
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
