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
import android.widget.TextView;

import fr.margouill.cockpitpp.R;
import fr.margouill.cockpitpp.utils.BroadcastKeys;
import fr.margouill.cockpitpp.utils.Ka50_Commands;
import fr.margouill.cockpitpp.utils.UDPSender;

import static android.view.Gravity.CENTER;


public class Ka50_Overhead extends Fragment {

    private ImageView mBackgroundView;
    private LinearLayout mContainer;
   // private TextView mPUI800CanonRemainTextView;


    // Overhead Switchs
    private Button mOverheadNavSelector;
    private ImageView mOverheadNavSelectorLeft;
    private ImageView mOverheadNavSelectorMid;
    private ImageView mOverheadNavSelectorRight;
    private ImageView mOverheadNavSelectorDown;

    private Button mOverheadWiperSelector;
    private ImageView mOverheadWiperSelectorLeft;
    private ImageView mOverheadWiperSelectorMid;
    private ImageView mOverheadWiperSelectorRight;
    private ImageView mOverheadWiperSelectorDown;

    private Button mOverheadPitotSelector;
    private ImageView mOverheadPitotSelectorUp;
    private ImageView mOverheadPitotSelectorDown;

    private Button mOverheadStaticSelector;
    private ImageView mOverheadStaticSelectorUp;
    private ImageView mOverheadStaticSelectorDown;

    private Button mOverheadRotorAntiiceSelector;
    private ImageView mOverheadRotorAntiiceSelectorUp;
    private ImageView mOverheadRotorAntiiceSelectorDown;

    private Button mOverheadEngDeiceSelector;
    private ImageView mOverheadEngDeiceSelectorUp;
    private ImageView mOverheadEngDeiceSelectorMid;
    private ImageView mOverheadEngDeiceSelectorDown;

    // Overhead warnings
    // Right side
    private TextView mOverheadLed1;
    private TextView mOverheadLed2;
    private TextView mOverheadLed3;
    private TextView mOverheadLed4;
    private TextView mOverheadLed5;
    private TextView mOverheadLed6;
    private TextView mOverheadLed7;
    private TextView mOverheadLed8;
    private TextView mOverheadLed9;
    private TextView mOverheadLed10;
    private TextView mOverheadLed11;
    private TextView mOverheadLed12;
    private TextView mOverheadLed13;
    private TextView mOverheadLed14;
    private TextView mOverheadLed15;
    private TextView mOverheadLed16;
    private TextView mOverheadLed17;
    private TextView mOverheadLed18;
    private TextView mOverheadLed19;
    private TextView mOverheadLed20;
    private TextView mOverheadLed21;
    private TextView mOverheadLed22;
    private TextView mOverheadLed23;
    private TextView mOverheadLed24;
    private TextView mOverheadLed25;

    //Left side
    private TextView mOverheadLed26;
    private TextView mOverheadLed27;
    private TextView mOverheadLed28;
    private TextView mOverheadLed29;
    private TextView mOverheadLed30;
    private TextView mOverheadLed31;
    private TextView mOverheadLed32;
    private TextView mOverheadLed33;
    private TextView mOverheadLed34;
    private TextView mOverheadLed35;
    private TextView mOverheadLed36;
    private TextView mOverheadLed37;
    private TextView mOverheadLed38;
    private TextView mOverheadLed39;
    private TextView mOverheadLed40;

    //Panel caution warnings
    private TextView mPanelLed1;
    private TextView mPanelLed2;
    private TextView mPanelLed3;
    private TextView mPanelLed4;
    private TextView mPanelLed5;
    private TextView mPanelLed6;
    private TextView mPanelLed7;
    private TextView mPanelLed8;
    private TextView mPanelLed9;




    public Ka50_Overhead() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(this.mBroadCastNewMessage, new IntentFilter(BroadcastKeys.KA50_OVERHEAD));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ka50_overhead, container, false);
        mBackgroundView = (ImageView) view.findViewById(R.id.layout);
        mContainer = (LinearLayout) view.findViewById(R.id.container);

        // Overhead Switchs
        mOverheadNavSelector = (Button) view.findViewById(R.id.ka50_overhead_nav_sw);
        mOverheadNavSelectorLeft = (ImageView) view.findViewById(R.id.ka50_overhead_nav_sw_10);
        mOverheadNavSelectorRight = (ImageView) view.findViewById(R.id.ka50_overhead_nav_sw_30);
        mOverheadNavSelectorMid = (ImageView) view.findViewById(R.id.ka50_overhead_nav_sw_up);
        mOverheadNavSelectorDown = (ImageView) view.findViewById(R.id.ka50_overhead_nav_sw_down);

        mOverheadNavSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(mOverheadNavSelectorLeft.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.OVERHEADNav, "0.2");
                } else if(mOverheadNavSelectorRight.getVisibility() == View.VISIBLE){
                    sendCommand(Ka50_Commands.OVERHEADNav, "0.3");
                } else if(mOverheadNavSelectorDown.getVisibility() == View.VISIBLE){
                    sendCommand(Ka50_Commands.OVERHEADNav, "0.0");
                } else {
                    sendCommand(Ka50_Commands.OVERHEADNav,"0.1");
                }
                }
        });

        mOverheadWiperSelectorLeft = (ImageView) view.findViewById(R.id.ka50_overhead_wiper_sw_left);
        mOverheadWiperSelectorRight = (ImageView) view.findViewById(R.id.ka50_overhead_wiper_sw_right);
        mOverheadWiperSelectorMid = (ImageView) view.findViewById(R.id.ka50_overhead_wiper_sw_center);
        mOverheadWiperSelectorDown = (ImageView) view.findViewById(R.id.ka50_overhead_wiper_sw_down);
        mOverheadWiperSelector = (Button) view.findViewById(R.id.ka50_overhead_wiper_sw);
        mOverheadWiperSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(mOverheadWiperSelectorLeft.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.OVERHEADWiper, "0.3");
                } else if(mOverheadWiperSelectorRight.getVisibility() == View.VISIBLE){
                    sendCommand(Ka50_Commands.OVERHEADWiper, "0");
                } else if(mOverheadWiperSelectorDown.getVisibility() == View.VISIBLE){
                    sendCommand(Ka50_Commands.OVERHEADWiper, "0.2");
                } else {
                    sendCommand(Ka50_Commands.OVERHEADWiper,"0.1");
                }

            }
        });

        mOverheadPitotSelectorUp = (ImageView) view.findViewById(R.id.ka50_overhead_pitotram_sw_up);
        mOverheadPitotSelectorDown = (ImageView) view.findViewById(R.id.ka50_overhead_pitotram_sw_down);
        mOverheadPitotSelector = (Button) view.findViewById(R.id.ka50_overhead_pitotram_sw);
        mOverheadPitotSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOverheadPitotSelectorUp.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.OVERHEADPitot, "0");
                } else {
                    sendCommand(Ka50_Commands.OVERHEADPitot, "1");
                }
            }
        });

        mOverheadStaticSelectorUp = (ImageView) view.findViewById(R.id.ka50_overhead_pitotstatic_sw_up);
        mOverheadStaticSelectorDown = (ImageView) view.findViewById(R.id.ka50_overhead_pitotstatic_sw_down);
        mOverheadStaticSelector = (Button) view.findViewById(R.id.ka50_overhead_pitotstatic_sw);
        mOverheadStaticSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOverheadStaticSelectorUp.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.OVERHEADStatic, "0");
                } else {
                    sendCommand(Ka50_Commands.OVERHEADStatic, "1");
                }
            }
        });

        mOverheadRotorAntiiceSelectorUp = (ImageView) view.findViewById(R.id.ka50_overhead_rotor_sw_up);
        mOverheadRotorAntiiceSelectorDown = (ImageView) view.findViewById(R.id.ka50_overhead_rotor_sw_down);
        mOverheadRotorAntiiceSelector = (Button) view.findViewById(R.id.ka50_overhead_rotor_sw);
        mOverheadRotorAntiiceSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOverheadRotorAntiiceSelectorUp.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.OVERHEADRotorAntiice, "0");
                } else {
                    sendCommand(Ka50_Commands.OVERHEADRotorAntiice, "1");
                }
            }
        });

        mOverheadEngDeiceSelectorUp = (ImageView) view.findViewById(R.id.ka50_overhead_eng_sw_up);
        mOverheadEngDeiceSelectorMid = (ImageView) view.findViewById(R.id.ka50_overhead_eng_sw_mid);
        mOverheadEngDeiceSelectorDown = (ImageView) view.findViewById(R.id.ka50_overhead_eng_sw_down);
        mOverheadEngDeiceSelector = (Button) view.findViewById(R.id.ka50_overhead_eng_sw);
        mOverheadEngDeiceSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(Ka50_Commands.OVERHEADEngDeice);

                if(mOverheadEngDeiceSelectorUp.getVisibility() == View.VISIBLE) {
                    sendCommand(Ka50_Commands.OVERHEADEngDeice, "0.5");
                } else if(mOverheadEngDeiceSelectorMid.getVisibility() == View.VISIBLE){
                    sendCommand(Ka50_Commands.OVERHEADEngDeice, "0");
                } else {
                    sendCommand(Ka50_Commands.OVERHEADEngDeice,"1");
                }

            }
        });

        //Overhead Leds
        mOverheadLed1 = (TextView) view.findViewById(R.id.ka50_overhead_masterarm_tv);
        mOverheadLed2 = (TextView) view.findViewById(R.id.ka50_overhead_compdiag_tv);
        mOverheadLed3 = (TextView) view.findViewById(R.id.ka50_overhead_lhantiice_tv);
        mOverheadLed4 = (TextView) view.findViewById(R.id.ka50_overhead_rhantiice_tv);
        mOverheadLed5 = (TextView) view.findViewById(R.id.ka50_overhead_fwtkpump_tv);
        mOverheadLed6 = (TextView) view.findViewById(R.id.ka50_overhead_afttankpump_tv);
        mOverheadLed7 = (TextView) view.findViewById(R.id.ka50_overhead_weapontrain_tv);
        mOverheadLed8 = (TextView) view.findViewById(R.id.ka50_overhead_compfail_tv);
        mOverheadLed9 = (TextView) view.findViewById(R.id.ka50_overhead_lhdust_tv);
        mOverheadLed10 = (TextView) view.findViewById(R.id.ka50_overhead_rhdust_tv);
        mOverheadLed11 = (TextView) view.findViewById(R.id.ka50_overhead_lhvalve_tv);
        mOverheadLed12 = (TextView) view.findViewById(R.id.ka50_overhead_rhvalve_tv);
        mOverheadLed13 = (TextView) view.findViewById(R.id.ka50_overhead_hmsfail_tv);
        mOverheadLed14 = (TextView) view.findViewById(R.id.ka50_overhead_inverter_tv);
        mOverheadLed15 = (TextView) view.findViewById(R.id.ka50_overhead_lhpower_tv);
        mOverheadLed16 = (TextView) view.findViewById(R.id.ka50_overhead_rhpower_tv);
        mOverheadLed17 = (TextView) view.findViewById(R.id.ka50_overhead_lhouter_tv);
        mOverheadLed18 = (TextView) view.findViewById(R.id.ka50_overhead_rhouter_tv);
        mOverheadLed19 = (TextView) view.findViewById(R.id.ka50_overhead_hud_tv);
        mOverheadLed20 = (TextView) view.findViewById(R.id.ka50_overhead_shkvaltv2);
        mOverheadLed21 = (TextView) view.findViewById(R.id.ka50_overhead_rtrantiice_tv);
        mOverheadLed22 = (TextView) view.findViewById(R.id.ka50_overhead_heat_tv);
        mOverheadLed23 = (TextView) view.findViewById(R.id.ka50_overhead_lhinner_tv);
        mOverheadLed24 = (TextView) view.findViewById(R.id.ka50_overhead_rhinner_tv);

        mOverheadLed25 = (TextView) view.findViewById(R.id.ka50_overhead_vide_tv);
        mOverheadLed26 = (TextView) view.findViewById(R.id.ka50_overhead_enrnav_tv);
        mOverheadLed27 = (TextView) view.findViewById(R.id.ka50_overhead_acpos_tv);
        mOverheadLed28 = (TextView) view.findViewById(R.id.ka50_overhead_xfeed_tv);
        mOverheadLed29 = (TextView) view.findViewById(R.id.ka50_overhead_ralt_tv);
        mOverheadLed30 = (TextView) view.findViewById(R.id.ka50_overhead_enrcours_tv);
        mOverheadLed31 = (TextView) view.findViewById(R.id.ka50_overhead_weaparm_tv);
        mOverheadLed32 = (TextView) view.findViewById(R.id.ka50_overhead_turbo_tv);
        mOverheadLed33 = (TextView) view.findViewById(R.id.ka50_overhead_autohover_tv);
        mOverheadLed34 = (TextView) view.findViewById(R.id.ka50_overhead_nextwp_tv);
        mOverheadLed35 = (TextView) view.findViewById(R.id.ka50_overhead_cannon_tv);
        mOverheadLed36 = (TextView) view.findViewById(R.id.ka50_overhead_agboilpress_tv);
        mOverheadLed37 = (TextView) view.findViewById(R.id.ka50_overhead_autodescent_tv);
        mOverheadLed38 = (TextView) view.findViewById(R.id.ka50_overhead_route_tv);
        mOverheadLed39 = (TextView) view.findViewById(R.id.ka50_overhead_cannon2_tv);
        mOverheadLed40 = (TextView) view.findViewById(R.id.ka50_overhead_slhook_tv);


        return view;
    }

    BroadcastReceiver mBroadCastNewMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //Log.d("OnReceive", intent.getAction());


            if(intent.getAction().contains(BroadcastKeys.KA50_OVERHEAD)) {
                String Overhead_Panel = intent.getExtras().getString(BroadcastKeys.KA50_OVERHEAD);
                //PUI800_Panel.replace("\\n","");
                //Log.d("Ka80 Overhead", Overhead_Panel);

                if(!Overhead_Panel.isEmpty()) {

                    // On séparre les variables
                    String[] panel_data = Overhead_Panel.split(";");


                    // Les switchs
                    panel_data[0] = String.valueOf(Math.round(Float.parseFloat(panel_data[0])*10));
                    mOverheadNavSelectorLeft.setVisibility(panel_data[0].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mOverheadNavSelectorRight.setVisibility(panel_data[0].equals("2") ? View.VISIBLE : View.INVISIBLE);
                    mOverheadNavSelectorMid.setVisibility(panel_data[0].equals("0") ? View.VISIBLE : View.INVISIBLE);
                    mOverheadNavSelectorDown.setVisibility(panel_data[0].equals("3") ? View.VISIBLE : View.INVISIBLE);

                    panel_data[1] = String.valueOf(Math.round(Float.parseFloat(panel_data[1])*10));
                    mOverheadWiperSelectorLeft.setVisibility(panel_data[1].equals("2") ? View.VISIBLE : View.INVISIBLE);
                    mOverheadWiperSelectorRight.setVisibility(panel_data[1].equals("3") ? View.VISIBLE : View.INVISIBLE);
                    mOverheadWiperSelectorMid.setVisibility(panel_data[1].equals("0") ? View.VISIBLE : View.INVISIBLE);
                    mOverheadWiperSelectorDown.setVisibility(panel_data[1].equals("1") ? View.VISIBLE : View.INVISIBLE);

                    mOverheadStaticSelectorUp.setVisibility(panel_data[2].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mOverheadStaticSelectorDown.setVisibility(panel_data[2].equals("0") ? View.VISIBLE : View.INVISIBLE);

                    mOverheadPitotSelectorUp.setVisibility(panel_data[3].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mOverheadPitotSelectorDown.setVisibility(panel_data[3].equals("0") ? View.VISIBLE : View.INVISIBLE);

                    mOverheadRotorAntiiceSelectorUp.setVisibility(panel_data[4].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mOverheadRotorAntiiceSelectorDown.setVisibility(panel_data[4].equals("0") ? View.VISIBLE : View.INVISIBLE);

                    //panel_data[5] = String.valueOf(Math.round(Float.parseFloat(panel_data[5])*10));
                    mOverheadEngDeiceSelectorUp.setVisibility(panel_data[5].equals("1") ? View.VISIBLE : View.INVISIBLE);
                    mOverheadEngDeiceSelectorMid.setVisibility(panel_data[5].equals("0.5") ? View.VISIBLE : View.INVISIBLE);
                    mOverheadEngDeiceSelectorDown.setVisibility(panel_data[5].equals("0") ? View.VISIBLE : View.INVISIBLE);

                    // Les leds
                    mOverheadLed1.setBackgroundResource(panel_data[6].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off);   mOverheadLed1.setTextColor(panel_data[6].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed2.setBackgroundResource(panel_data[7].equals("1") ? R.drawable.ka50_avisos_yellow : R.drawable.ka50_avisos_off);   mOverheadLed2.setTextColor(panel_data[7].equals("1") ? getResources().getColor(R.color.m2kCYellow) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed3.setBackgroundResource(panel_data[8].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off);   mOverheadLed3.setTextColor(panel_data[8].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed4.setBackgroundResource(panel_data[9].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off);   mOverheadLed4.setTextColor(panel_data[9].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed5.setBackgroundResource(panel_data[10].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off);  mOverheadLed5.setTextColor(panel_data[10].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed6.setBackgroundResource(panel_data[11].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off);  mOverheadLed6.setTextColor(panel_data[11].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed7.setBackgroundResource(panel_data[12].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off);  mOverheadLed7.setTextColor(panel_data[12].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed8.setBackgroundResource(panel_data[13].equals("1") ? R.drawable.ka50_avisos_yellow : R.drawable.ka50_avisos_off);  mOverheadLed8.setTextColor(panel_data[13].equals("1") ? getResources().getColor(R.color.m2kCYellow) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed9.setBackgroundResource(panel_data[14].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off);  mOverheadLed9.setTextColor(panel_data[14].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed10.setBackgroundResource(panel_data[15].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed10.setTextColor(panel_data[15].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed11.setBackgroundResource(panel_data[16].equals("1") ? R.drawable.ka50_avisos_yellow : R.drawable.ka50_avisos_off); mOverheadLed11.setTextColor(panel_data[16].equals("1") ? getResources().getColor(R.color.m2kCYellow) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed12.setBackgroundResource(panel_data[17].equals("1") ? R.drawable.ka50_avisos_yellow : R.drawable.ka50_avisos_off); mOverheadLed12.setTextColor(panel_data[17].equals("1") ? getResources().getColor(R.color.m2kCYellow) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed13.setBackgroundResource(panel_data[18].equals("1") ? R.drawable.ka50_avisos_yellow : R.drawable.ka50_avisos_off); mOverheadLed13.setTextColor(panel_data[18].equals("1") ? getResources().getColor(R.color.m2kCYellow) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed14.setBackgroundResource(panel_data[19].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed14.setTextColor(panel_data[19].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed15.setBackgroundResource(panel_data[20].equals("1") ? R.drawable.ka50_avisos_yellow : R.drawable.ka50_avisos_off); mOverheadLed15.setTextColor(panel_data[20].equals("1") ? getResources().getColor(R.color.m2kCYellow) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed16.setBackgroundResource(panel_data[21].equals("1") ? R.drawable.ka50_avisos_yellow : R.drawable.ka50_avisos_off); mOverheadLed16.setTextColor(panel_data[21].equals("1") ? getResources().getColor(R.color.m2kCYellow) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed17.setBackgroundResource(panel_data[22].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed17.setTextColor(panel_data[22].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed18.setBackgroundResource(panel_data[23].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed18.setTextColor(panel_data[23].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed19.setBackgroundResource(panel_data[24].equals("1") ? R.drawable.ka50_avisos_yellow : R.drawable.ka50_avisos_off); mOverheadLed19.setTextColor(panel_data[24].equals("1") ? getResources().getColor(R.color.m2kCYellow) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed20.setBackgroundResource(panel_data[25].equals("1") ? R.drawable.ka50_avisos_yellow : R.drawable.ka50_avisos_off); mOverheadLed20.setTextColor(panel_data[25].equals("1") ? getResources().getColor(R.color.m2kCYellow) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed21.setBackgroundResource(panel_data[26].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed21.setTextColor(panel_data[26].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed22.setBackgroundResource(panel_data[27].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed22.setTextColor(panel_data[27].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed23.setBackgroundResource(panel_data[28].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed23.setTextColor(panel_data[28].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed24.setBackgroundResource(panel_data[29].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed24.setTextColor(panel_data[29].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    //mOverheadLed25.setBackgroundResource(panel_data[30].equals("1") ? R.drawable.ka50_avisos_red : R.drawable.ka50_avisos_off);   mOverheadLed25.setTextColor(panel_data[30].equals("1") ? getResources().getColor(R.color.m2kCRed)   : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed26.setBackgroundResource(panel_data[31].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed26.setTextColor(panel_data[31].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed27.setBackgroundResource(panel_data[32].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed27.setTextColor(panel_data[32].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed28.setBackgroundResource(panel_data[33].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed28.setTextColor(panel_data[33].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed29.setBackgroundResource(panel_data[34].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed29.setTextColor(panel_data[34].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed30.setBackgroundResource(panel_data[35].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed30.setTextColor(panel_data[35].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed31.setBackgroundResource(panel_data[36].equals("1") ? R.drawable.ka50_avisos_red : R.drawable.ka50_avisos_off); mOverheadLed31.setTextColor(panel_data[36].equals("1") ? getResources().getColor(R.color.m2kCRed) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed32.setBackgroundResource(panel_data[37].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed32.setTextColor(panel_data[37].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed33.setBackgroundResource(panel_data[38].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed33.setTextColor(panel_data[38].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed34.setBackgroundResource(panel_data[39].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed34.setTextColor(panel_data[39].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed35.setBackgroundResource(panel_data[40].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed35.setTextColor(panel_data[40].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed36.setBackgroundResource(panel_data[41].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed36.setTextColor(panel_data[41].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed37.setBackgroundResource(panel_data[42].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed37.setTextColor(panel_data[42].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed38.setBackgroundResource(panel_data[43].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed38.setTextColor(panel_data[43].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed39.setBackgroundResource(panel_data[44].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed39.setTextColor(panel_data[44].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));
                    mOverheadLed40.setBackgroundResource(panel_data[45].equals("1") ? R.drawable.ka50_avisos_green : R.drawable.ka50_avisos_off); mOverheadLed40.setTextColor(panel_data[45].equals("1") ? getResources().getColor(R.color.m2kCGreen) : getResources().getColor(R.color.av8bnaText));

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
