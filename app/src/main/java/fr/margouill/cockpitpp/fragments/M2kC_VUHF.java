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

import java.util.concurrent.RunnableScheduledFuture;

import fr.margouill.cockpitpp.R;
import fr.margouill.cockpitpp.utils.BroadcastKeys;
import fr.margouill.cockpitpp.utils.M2KC_Commands;
import fr.margouill.cockpitpp.utils.UDPSender;

import static android.view.Gravity.CENTER;

/**
 *
 */
public class M2kC_VUHF extends Fragment {

    private ImageView mBackgroundView;
    private LinearLayout mContainer;

    private int Mode;        // 0 = presets et 1 = freq manuelle
    private int LastPower;
    private int Power;       // de 0 à 6
    private int LowHigh = 5; // Low (5) ou High (20)

    private boolean Squelsh = true;
    private boolean Guard = true;

    private boolean Conf = false;

    private boolean EditMode = false;
    private String NewFreq = "";

    private String Freq = "";
    private String FreqGuard;
    private int Chan; // de 1 à 20

    private int[] EtatLeds = new int[28]; // Tableau d'état de toutes mes leds
    private ImageView[] LesViews = new ImageView[28]; // Tableau de tous mes ImageView

    private ImageView mVHFRotG;
    private ImageView mVHFRotD;

    private Button mBT0;
    private Button mBT1;
    private Button mBT2;
    private Button mBT3;
    private Button mBT4;
    private Button mBT5;
    private Button mBT6;
    private Button mBT7;
    private Button mBT8;
    private Button mBT9;
    private Button mBT1b; // READ
    private Button mBT2b; // SQL
    private Button mBT3b; // GR
    private Button mBT4b;
    private Button mBT5b; // LOW
    private Button mBT6b; // TONE
    private Button mBT7b;
    private Button mBT8b;
    private Button mBT9b; // ZERO
    private Button mBTmem;
    private Button mBTclr;
    private Button mBTvld;
    private Button mBTxfr;
    private Button mBTconf;

    private Button mBTRot1;
    private Button mBTRot2;
    private Button mBTRot3;
    private Button mBTRot4;

    private TextView mDisplay;

    public M2kC_VUHF() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(this.mBroadCastNewMessage, new IntentFilter(BroadcastKeys.M2KC_VUHF));
    }

    public void RefreshPower(){
        if (Power != LastPower){
            LastPower = Power;
            // On test l'état de la radio
            switch (Power){
                case 0:
                    for (int i = 0; i < EtatLeds.length; i++){
                        EtatLeds[i] = 0;
                    }
                    mDisplay.setText("");

                    // On met tout à zero
                    Conf = false;
                    LowHigh = 5;
                    NewFreq = "";




                    break; // Power Off
                case 2: // HQ
                    for (int i = 0; i < EtatLeds.length; i++){
                        EtatLeds[i] = 0;
                    }
                    mDisplay.setText("********");
                    break;
                case 3: // SV
                    for (int i = 0; i < EtatLeds.length; i++){
                        EtatLeds[i] = 0;
                    }
                    mDisplay.setText("********");
                    break;
                case 4: // DL
                    for (int i = 0; i < EtatLeds.length; i++){
                        EtatLeds[i] = 0;
                    }
                    mDisplay.setText("********");
                    break;
                case 6: // EN
                    for (int i = 0; i < EtatLeds.length; i++){
                        EtatLeds[i] = 0;
                    }

                    mDisplay.setText("********");
                    break;

                case 1: // Power On
                    for (int i = 0; i <= 9; i++){
                        EtatLeds[i] = 1;
                    }

                    // le squelsh et la guard
                    EtatLeds[20] = 1;
                    EtatLeds[21] = 1;

                    // le CLR/VLD ou MEM/XFR
                    if(EditMode){
                        EtatLeds[22] = 0;
                        EtatLeds[23] = 1;
                        EtatLeds[25] = 0;
                        EtatLeds[24] = 1;
                    }else{
                        EtatLeds[22] = 1;
                        EtatLeds[23] = 0;
                        EtatLeds[25] = 1;
                        EtatLeds[24] = 0;
                    }

                    // le conf
                    EtatLeds[26] = 1;

                    RefreshDisplay();
                    break;
                case 5: // Mode Guard
                    for (int i = 0; i <= 9; i++){
                        EtatLeds[i] = 1;
                    }

                    // le squelsh et la guard
                    EtatLeds[20] = 1;
                    EtatLeds[21] = 1;

                    // le CLR/VLD ou MEM/XFR
                    if(EditMode){
                        EtatLeds[22] = 0;
                        EtatLeds[23] = 1;
                        EtatLeds[25] = 0;
                        EtatLeds[24] = 1;
                    }else{
                        EtatLeds[22] = 1;
                        EtatLeds[23] = 0;
                        EtatLeds[25] = 1;
                        EtatLeds[24] = 0;
                    }

                    EtatLeds[26] = 1;


                    RefreshDisplay();
                    break;
            }
        }
    }

    public void RefreshDisplay(){

        if (!EditMode){
            if(Mode==0){
                mDisplay.setText("FF P " + String.format("%03d", Chan));
            }else{
                mDisplay.setText("FF " + Freq);
            }
        }


    }

    public void DisplayError(){

        Edition(false);
        //try { Thread.sleep(100); } catch (InterruptedException e) { }
        //mDisplay.setText("ERROR");
        //



        //RefreshDisplay();
    }

    public void RefreshRadio(){
        // On fait les modifs qui conviennent

        for (int i = 0; i < LesViews.length; i++){
            LesViews[i].setVisibility(EtatLeds[i] == 1 ? View.VISIBLE : View.INVISIBLE);
        }


        //RefreshDisplay();
    }


    public void ModeConf() {

        if (Conf) {
            // On est déjà en mode configuration
            // On re passe en mode normal
            Conf = false;

            for (int i = 0; i <= 9; i++) {
                EtatLeds[i] = 1;
            }

            for (int i = 11; i <= 19; i++) {
                EtatLeds[i] = 0;
            }

            // Le 20
            if (LowHigh == 20){
                EtatLeds[27] = 1;
            }else{
                EtatLeds[27] = 0;
            }

        } else {
            // On passe en mode configuration
            Conf = true;

            // On etteint les digits
            for (int i = 0; i <= 9; i++) {
                EtatLeds[i] = 0;
            }

            // On allume le texte
            for (int i = 11; i < 19; i++) {
                EtatLeds[i] = 1;
            }

            // Le 5 ou le 20
            if (LowHigh == 5){
                EtatLeds[5] = 1;
                EtatLeds[27] = 0;
            }else{
                EtatLeds[5] = 0;
                EtatLeds[27] = 1;
            }
        }

        EtatLeds[15] = 0; // On etteint le LOW en 5b



    }

    public void LowHigh(){
        // On change le parametre
        if(Conf){
            if(LowHigh==5) LowHigh=20; else LowHigh=5;

            // On change l'affichage
            if (LowHigh == 5){
                EtatLeds[5] = 1;
                EtatLeds[27] = 0;
            }else{
                EtatLeds[5] = 0;
                EtatLeds[27] = 1;
            }
        }


        RefreshRadio();
    }

    public void Squelsh(){
        if(Conf){
            if(Squelsh) Squelsh=false; else Squelsh=true;
        }

        if (Squelsh){
            EtatLeds[20] = 1;
        }else{
            EtatLeds[20] = 0;
        }
        RefreshRadio();
    }

    public void Guard(){
        if(Conf){
            if(Guard) Guard=false; else Guard=true;
        }

        if (Guard){
            EtatLeds[21] = 1;
        }else{
            EtatLeds[21] = 0;
        }
        RefreshRadio();
    }

    private void Edition(boolean value){
        if(value) {
            // On passe l'affichage en mode édition
            EtatLeds[22] = 0;
            EtatLeds[23] = 1;
            EtatLeds[25] = 0;
            EtatLeds[24] = 1;

            EditMode = true;

        }else{
            // On sort du mode édition
            NewFreq = "";

            EtatLeds[22] = 1;
            EtatLeds[23] = 0;
            EtatLeds[25] = 1;
            EtatLeds[24] = 0;

            EditMode = false;
        }

    }

    public void NewDigit(String digit) {
        if(!Conf) {

            if (NewFreq == "") {
                // C'est la premiere étape
                // On passe l'affichage en mode édition
                Edition(true);
                NewFreq = NewFreq + digit;

            } else if (NewFreq.length() < 5) {
                NewFreq = NewFreq + digit;

            } else {
                // trop de digits ont été cliqués, on repasse en normal
                Edition(false);
            }
            mDisplay.setText(NewFreq);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_m2kc_vuhf2, container, false);
        mBackgroundView = (ImageView)view.findViewById(R.id.layout);
        mContainer = (LinearLayout) view.findViewById(R.id.container);

        mDisplay = (TextView) view.findViewById(R.id.m2kc_vuhf_display);

        // On initialise les ImageView
        LesViews[0]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_0);
        LesViews[1]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_1);
        LesViews[2]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_2);
        LesViews[3]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_3);
        LesViews[4]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_4);
        LesViews[5]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_5);
        LesViews[6]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_6);
        LesViews[7]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_7);
        LesViews[8]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_8);
        LesViews[9]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_9);
        LesViews[10]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_10);       // Jamais utilisé
        LesViews[11]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_1b);		// READ
        LesViews[12]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_2b);		// SQL
        LesViews[13]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_3b);		// GR
        LesViews[14]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_4b);
        LesViews[15]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_5b);       // LOW
        LesViews[16]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_6b);		// TONE
        LesViews[17]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_7b);
        LesViews[18]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_8b);       // TOD
        LesViews[19]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_9b);       // ZERO

        LesViews[20]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_2pt);		// Point 2
        LesViews[21]   = (ImageView) view.findViewById(R.id.m2kc_vuhf_3pt);		// Point 3

        LesViews[22]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_mem);
        LesViews[23]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_clr);
        LesViews[24]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_vld);
        LesViews[25]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_xfr);
        LesViews[26]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_conf);

        LesViews[27]  = (ImageView) view.findViewById(R.id.m2kc_vuhf_20);

        // On initialise l'état de chaque imageView
        for (int i = 0; i < LesViews.length; i++){
            EtatLeds[i] = 0;
        }


        mVHFRotG  = (ImageView) view.findViewById(R.id.m2kc_vuhf_rotg);
        mVHFRotD  = (ImageView) view.findViewById(R.id.m2kc_vuhf_rotd);

        mBT0 = (Button) view.findViewById(R.id.m2kc_vuhf_bt_0);
        mBT1 = (Button) view.findViewById(R.id.m2kc_vuhf_bt_1);
        mBT2 = (Button) view.findViewById(R.id.m2kc_vuhf_bt_2);
        mBT3 = (Button) view.findViewById(R.id.m2kc_vuhf_bt_3);
        mBT4 = (Button) view.findViewById(R.id.m2kc_vuhf_bt_4);
        mBT5 = (Button) view.findViewById(R.id.m2kc_vuhf_bt_5);
        mBT6 = (Button) view.findViewById(R.id.m2kc_vuhf_bt_6);
        mBT7 = (Button) view.findViewById(R.id.m2kc_vuhf_bt_7);
        mBT8 = (Button) view.findViewById(R.id.m2kc_vuhf_bt_8);
        mBT9 = (Button) view.findViewById(R.id.m2kc_vuhf_bt_9);
        mBTmem = (Button) view.findViewById(R.id.m2kc_vuhf_bt_mem);
        mBTvld = (Button) view.findViewById(R.id.m2kc_vuhf_bt_vld);
        mBTconf = (Button) view.findViewById(R.id.m2kc_vuhf_bt_conf);

        mBTRot1 = (Button) view.findViewById(R.id.m2kc_vuhf_rot1);
        mBTRot2 = (Button) view.findViewById(R.id.m2kc_vuhf_rot2);
        mBTRot3 = (Button) view.findViewById(R.id.m2kc_vuhf_rot3);
        mBTRot4 = (Button) view.findViewById(R.id.m2kc_vuhf_rot4);

        mBT0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(M2KC_Commands.VHFBtn0);
                NewDigit(String.valueOf(0));
            }
        });
        mBT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(M2KC_Commands.VHFBtn1);
                NewDigit(String.valueOf(1));
            }
        });
        mBT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(M2KC_Commands.VHFBtn2);
                Squelsh();
                NewDigit(String.valueOf(2));
            }
        });
        mBT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(M2KC_Commands.VHFBtn3);
                Guard();
                NewDigit(String.valueOf(3));
            }
        });
        mBT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(M2KC_Commands.VHFBtn4);
                NewDigit(String.valueOf(4));
            }
        });
        mBT5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(M2KC_Commands.VHFBtn5);
                LowHigh();
                NewDigit(String.valueOf(5));
            }
        });
        mBT6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(M2KC_Commands.VHFBtn6);
                NewDigit(String.valueOf(6));
            }
        });
        mBT7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(M2KC_Commands.VHFBtn7);
                NewDigit(String.valueOf(7));
            }
        });
        mBT8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(M2KC_Commands.VHFBtn8);
                NewDigit(String.valueOf(8));
            }
        });
        mBT9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(M2KC_Commands.VHFBtn9);
                NewDigit(String.valueOf(9));
            }
        });
        mBTmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { sendCommand(M2KC_Commands.VHFBtnMEM); }
        });
        mBTvld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Conf){
                    if(EditMode){
                        // On est en mode edition de freq/preset

                        if(NewFreq.length() == 2 && Integer.parseInt(NewFreq) <= 99){
                            // C'est un preset valable
                            sendCommand(M2KC_Commands.VHFBtnVLD);




                            // On met à jour le channel
                            if (Integer.parseInt(NewFreq) <=20 && Integer.parseInt(NewFreq) > 0){

                                Chan = Integer.parseInt(NewFreq);

                                switch (Chan){
                                    case 1:  sendCommand(M2KC_Commands.VHFChanMode, "0.05"); break;
                                    case 2:  sendCommand(M2KC_Commands.VHFChanMode, "0.1"); break;
                                    case 3:  sendCommand(M2KC_Commands.VHFChanMode, "0.15"); break;
                                    case 4:  sendCommand(M2KC_Commands.VHFChanMode, "0.20"); break;
                                    case 5:  sendCommand(M2KC_Commands.VHFChanMode, "0.25"); break;
                                    case 6:  sendCommand(M2KC_Commands.VHFChanMode, "0.30"); break;
                                    case 7:  sendCommand(M2KC_Commands.VHFChanMode, "0.35"); break;
                                    case 8:  sendCommand(M2KC_Commands.VHFChanMode, "0.40"); break;
                                    case 9:  sendCommand(M2KC_Commands.VHFChanMode, "0.45"); break;
                                    case 10: sendCommand(M2KC_Commands.VHFChanMode, "0.50"); break;
                                    case 11: sendCommand(M2KC_Commands.VHFChanMode, "0.55"); break;
                                    case 12: sendCommand(M2KC_Commands.VHFChanMode, "0.60"); break;
                                    case 13: sendCommand(M2KC_Commands.VHFChanMode, "0.65"); break;
                                    case 14: sendCommand(M2KC_Commands.VHFChanMode, "0.70"); break;
                                    case 15: sendCommand(M2KC_Commands.VHFChanMode, "0.75"); break;
                                    case 16: sendCommand(M2KC_Commands.VHFChanMode, "0.80"); break;
                                    case 17: sendCommand(M2KC_Commands.VHFChanMode, "0.85"); break;
                                    case 18: sendCommand(M2KC_Commands.VHFChanMode, "0.90"); break;
                                    case 19: sendCommand(M2KC_Commands.VHFChanMode, "0.95"); break;
                                    case 20: sendCommand(M2KC_Commands.VHFChanMode, "0"); break;

                                }
                            }



                            // On passe en mode Preset
                            Mode = 0;

                            //mDisplay.setText("PP 0" + NewFreq);

                            // On sort du mode edition de frequence
                            Edition(false);

                        }
                        else if(NewFreq.length()==5) {
                            // La frequence insérée a un bon format

                            if (Integer.parseInt(NewFreq) >= 11800 && Integer.parseInt(NewFreq) <= 14995) {
                                // C'est une frequence VHF
                                sendCommand(M2KC_Commands.VHFBtnVLD);

                                // On sort du mode edition de frequence
                                Edition(false);

                                // On change l'affichage du display
                                mDisplay.setText("FF " + Freq);


                            }else if (Integer.parseInt(NewFreq) >= 22500 && Integer.parseInt(NewFreq) <= 39995) {
                                // C'est une fréquence UHF
                                sendCommand(M2KC_Commands.VHFBtnVLD);

                                // On sort du mode edition de frequence
                                Edition(false);

                                // On change l'affichage du display
                                mDisplay.setText("FF " + Freq);
                            }else {
                                // C'est une erreur
                                sendCommand(M2KC_Commands.VHFBtnVLD);

                                DisplayError();
                            }

                            // On passe en mode Freq
                            Mode = 1;

                        }else {
                            //DisplayError();
                        }


                    }else{
                        // On est pas en mode édition donc on change juste le mode d'affichage
                        sendCommand(M2KC_Commands.VHFBtnVLD);
                        if(Mode==0) Mode=1; else Mode=0;

                    }


                    // On raffraichi l'affichage
                    RefreshDisplay();
                    RefreshRadio();
                }




            }
        });
        mBTconf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(M2KC_Commands.VHFBtnCONF);
                ModeConf();
                //RefreshRadio();
            }
        });

        mBTRot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Math.round(mVHFRotG.getRotation())){
                    case 0: sendCommand(M2KC_Commands.VHFSelectorMode, "0.6"); break;
                    case 33: sendCommand(M2KC_Commands.VHFSelectorMode, "0"); break;
                    case 70: sendCommand(M2KC_Commands.VHFSelectorMode, "0.1"); break;
                    case 110: sendCommand(M2KC_Commands.VHFSelectorMode, "0.2"); break;
                    case 150: sendCommand(M2KC_Commands.VHFSelectorMode, "0.3"); break;
                    case 185: sendCommand(M2KC_Commands.VHFSelectorMode, "0.4"); break;
                    case 223: sendCommand(M2KC_Commands.VHFSelectorMode, "0.5"); break;
                }
                if (Power == 0){ Power = 6; } else { Power = Power - 1; }

                RefreshPower();
                RefreshRadio();


            }
        });
        mBTRot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Math.round(mVHFRotG.getRotation())){
                    case 0: sendCommand(M2KC_Commands.VHFSelectorMode, "0.1"); break;
                    case 33: sendCommand(M2KC_Commands.VHFSelectorMode, "0.2"); break;
                    case 70: sendCommand(M2KC_Commands.VHFSelectorMode, "0.3"); break;
                    case 110: sendCommand(M2KC_Commands.VHFSelectorMode, "0.4"); break;
                    case 150: sendCommand(M2KC_Commands.VHFSelectorMode, "0.5"); break;
                    case 185: sendCommand(M2KC_Commands.VHFSelectorMode, "0.6"); break;
                    case 223: sendCommand(M2KC_Commands.VHFSelectorMode, "0"); break;
                }
                if (Power == 6){ Power = 0; } else { Power = Power + 1; }

                RefreshPower();
                RefreshRadio();
            }
        });
        mBTRot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Math.round(mVHFRotD.getRotation())){
                    case 0: sendCommand(M2KC_Commands.VHFChanMode, "0.95"); break;
                    case 18: sendCommand(M2KC_Commands.VHFChanMode, "0"); break;
                    case 36: sendCommand(M2KC_Commands.VHFChanMode, "0.05"); break;
                    case 54: sendCommand(M2KC_Commands.VHFChanMode, "0.10"); break;
                    case 72: sendCommand(M2KC_Commands.VHFChanMode, "0.15"); break;
                    case 90: sendCommand(M2KC_Commands.VHFChanMode, "0.20"); break;
                    case 108: sendCommand(M2KC_Commands.VHFChanMode, "0.25"); break;
                    case 126: sendCommand(M2KC_Commands.VHFChanMode, "0.30"); break;
                    case 144: sendCommand(M2KC_Commands.VHFChanMode, "0.35"); break;
                    case 162: sendCommand(M2KC_Commands.VHFChanMode, "0.40"); break;
                    case 180: sendCommand(M2KC_Commands.VHFChanMode, "0.45"); break;
                    case 198: sendCommand(M2KC_Commands.VHFChanMode, "0.50"); break;
                    case 216: sendCommand(M2KC_Commands.VHFChanMode, "0.55"); break;
                    case 234: sendCommand(M2KC_Commands.VHFChanMode, "0.60"); break;
                    case 252: sendCommand(M2KC_Commands.VHFChanMode, "0.65"); break;
                    case 270: sendCommand(M2KC_Commands.VHFChanMode, "0.70"); break;
                    case 288: sendCommand(M2KC_Commands.VHFChanMode, "0.75"); break;
                    case 306: sendCommand(M2KC_Commands.VHFChanMode, "0.80"); break;
                    case 324: sendCommand(M2KC_Commands.VHFChanMode, "0.85"); break;
                    case 342: sendCommand(M2KC_Commands.VHFChanMode, "0.90"); break;

                }
                if (Chan == 1){ Chan = 20; } else { Chan = Chan - 1; }

                RefreshDisplay();
                RefreshRadio();
            }
        });
        mBTRot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Math.round(mVHFRotD.getRotation())) {
                    case 0: sendCommand(M2KC_Commands.VHFChanMode, "0.05"); break;
                    case 18: sendCommand(M2KC_Commands.VHFChanMode, "0.1"); break;
                    case 36: sendCommand(M2KC_Commands.VHFChanMode, "0.15"); break;
                    case 54: sendCommand(M2KC_Commands.VHFChanMode, "0.20"); break;
                    case 72: sendCommand(M2KC_Commands.VHFChanMode, "0.25"); break;
                    case 90: sendCommand(M2KC_Commands.VHFChanMode, "0.30"); break;
                    case 108: sendCommand(M2KC_Commands.VHFChanMode, "0.35"); break;
                    case 126: sendCommand(M2KC_Commands.VHFChanMode, "0.40"); break;
                    case 144: sendCommand(M2KC_Commands.VHFChanMode, "0.45"); break;
                    case 162: sendCommand(M2KC_Commands.VHFChanMode, "0.50"); break;
                    case 180: sendCommand(M2KC_Commands.VHFChanMode, "0.55"); break;
                    case 198: sendCommand(M2KC_Commands.VHFChanMode, "0.60"); break;
                    case 216: sendCommand(M2KC_Commands.VHFChanMode, "0.65"); break;
                    case 234: sendCommand(M2KC_Commands.VHFChanMode, "0.70"); break;
                    case 252: sendCommand(M2KC_Commands.VHFChanMode, "0.75"); break;
                    case 270: sendCommand(M2KC_Commands.VHFChanMode, "0.80"); break;
                    case 288: sendCommand(M2KC_Commands.VHFChanMode, "0.85"); break;
                    case 306: sendCommand(M2KC_Commands.VHFChanMode, "0.90"); break;
                    case 324: sendCommand(M2KC_Commands.VHFChanMode, "0.95"); break;
                    case 342: sendCommand(M2KC_Commands.VHFChanMode, "0"); break;

                }
                if (Chan == 20){ Chan = 1; } else { Chan = Chan + 1; }

                RefreshDisplay();
                RefreshRadio();
            }
        });










        return view;
    }


    BroadcastReceiver mBroadCastNewMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().contains(BroadcastKeys.M2KC_VUHF)) {
                String VUHF_panel = intent.getExtras().getString(BroadcastKeys.M2KC_VUHF);

                if(!VUHF_panel.isEmpty()){
                    String[] panel_data = VUHF_panel.split(";");






                    //Rotacteur gauche
                    panel_data[1] = String.valueOf(Math.round(Float.parseFloat(panel_data[1])*10));
                    switch (Integer.parseInt(panel_data[1])){
                        case 0: mVHFRotG.setRotation(0); Power = 0; break;
                        case 1: mVHFRotG.setRotation(33); Power = 1; break;
                        case 2: mVHFRotG.setRotation(70); Power = 2; break;
                        case 3: mVHFRotG.setRotation(110); Power = 3; break;
                        case 4: mVHFRotG.setRotation(150); Power = 4; break;
                        case 5: mVHFRotG.setRotation(185); Power = 5; break;
                        case 6: mVHFRotG.setRotation(223); Power = 6; break;

                    }

                    //Rotacteur droit
                    panel_data[2] = String.valueOf(Math.round(Math.round(Float.parseFloat(panel_data[2])*100)));

                    switch (Integer.parseInt(panel_data[2])){
                        case 0: mVHFRotD.setRotation(0); Chan = 1; break;
                        case 5: mVHFRotD.setRotation(18); Chan = 2; break;
                        case 10: mVHFRotD.setRotation(36); Chan = 3; break;
                        case 15: mVHFRotD.setRotation(54); Chan = 4; break;
                        case 20: mVHFRotD.setRotation(72); Chan = 5; break;
                        case 25: mVHFRotD.setRotation(90); Chan = 6; break;
                        case 30: mVHFRotD.setRotation(108); Chan = 7; break;
                        case 35: mVHFRotD.setRotation(126); Chan = 8; break;
                        case 40: mVHFRotD.setRotation(144); Chan = 9; break;
                        case 45: mVHFRotD.setRotation(162); Chan = 10; break;
                        case 50: mVHFRotD.setRotation(180); Chan = 11; break;
                        case 55: mVHFRotD.setRotation(198); Chan = 12; break;
                        case 60: mVHFRotD.setRotation(216); Chan = 13; break;
                        case 65: mVHFRotD.setRotation(234); Chan = 14; break;
                        case 70: mVHFRotD.setRotation(252); Chan = 15; break;
                        case 75: mVHFRotD.setRotation(270); Chan = 16; break;
                        case 80: mVHFRotD.setRotation(288); Chan = 17; break;
                        case 85: mVHFRotD.setRotation(306); Chan = 18; break;
                        case 90: mVHFRotD.setRotation(324); Chan = 19; break;
                        case 95: mVHFRotD.setRotation(342); Chan = 20; break;

                    }

                    RefreshPower();
                    RefreshRadio();

                    Freq = panel_data[0];

                    //Retour de pression des boutons
                    String clr = String.valueOf(Math.round(Float.parseFloat(panel_data[3])));
                    String vld = String.valueOf(Math.round(Float.parseFloat(panel_data[4])));
                    String b1  = String.valueOf(Math.round(Float.parseFloat(panel_data[5])));
                    String b2  = String.valueOf(Math.round(Float.parseFloat(panel_data[6])));
                    String b3  = String.valueOf(Math.round(Float.parseFloat(panel_data[7])));
                    String b4  = String.valueOf(Math.round(Float.parseFloat(panel_data[8])));
                    String b5  = String.valueOf(Math.round(Float.parseFloat(panel_data[9])));
                    String b6  = String.valueOf(Math.round(Float.parseFloat(panel_data[10])));
                    String b7  = String.valueOf(Math.round(Float.parseFloat(panel_data[11])));
                    String b8  = String.valueOf(Math.round(Float.parseFloat(panel_data[12])));
                    String b9  = String.valueOf(Math.round(Float.parseFloat(panel_data[13])));
                    String b0  = String.valueOf(Math.round(Float.parseFloat(panel_data[14])));
                    String conf = String.valueOf(Math.round(Float.parseFloat(panel_data[15])));

                    //Log.d("M2kC VUHF", clr+vld+b1+b2+b3+b4+b5+b6+b7+b8+b9+b0+conf);





                    //mRotatorLeft.setRotation(getDegreesLeftKnob(Float.valueOf(table_data[0])) - OFFSSET_ROTATOR_LEFT);
                    //mRotatorRight.setRotation(getDegreesRightKnob(Float.parseFloat(table_data[1])) - OFFSSET_ROTATOR_RIGHT);
                }
            }

        }
    };














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

    private void sendCommand(M2KC_Commands pCommand) {
        sendCommand(pCommand, "1");
    }

    private void sendCommand(M2KC_Commands pCommand, String pValue) {
        UDPSender.getInstance().sendToDCS(pCommand.getTypeButton().getCode(),pCommand.getDevice().getCode(),pCommand.getCode(), pValue,getContext());
    }
}
