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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    private TextView mUr1;
    private TextView mUr2;
    private TextView mUr3;
    private TextView mUr4;
    private TextView mUr5;
    private TextView mBr1;
    private TextView mBr2;
    private TextView mBr3;
    private TextView mBr4;
    private TextView mBr5;
    private TextView mUr1S;
    private TextView mUr2S;
    private TextView mUr3S;
    private TextView mUr4S;
    private TextView mUr5S;
    private TextView mBr1S;
    private TextView mBr1P;
    private TextView mBr2S;
    private TextView mBr2P;
    private TextView mBr3S;
    private TextView mBr3P;
    private TextView mBr4S;
    private TextView mBr4P;
    private TextView mBr5S;
    private TextView mBr5P;
    private TextView mK1;
    private TextView mK2;
    private ImageView mMasterArmUp;
    private ImageView mMasterArmDown;
    private ImageView mJetti1;
    private ImageView mJetti2;
    private ImageView mJetti3;
    private LinearLayout mKBtn;
    private LinearLayout mBrBtn1;
    private LinearLayout mBrBtn2;
    private LinearLayout mBrBtn3;
    private LinearLayout mBrBtn4;
    private LinearLayout mBrBtn5;

    public M2kC_VUHF() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(this.mBroadCastNewMessage, new IntentFilter(BroadcastKeys.M2KC_VUHF));
    }


    BroadcastReceiver mBroadCastNewMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {



        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_m2kc_vuhf2, container, false);
        mBackgroundView = (ImageView)view.findViewById(R.id.layout);
        mContainer = (LinearLayout) view.findViewById(R.id.container);



        return view;
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

    private void sendCommand(M2KC_Commands pCommand) {
        sendCommand(pCommand, "1");
    }

    private void sendCommand(M2KC_Commands pCommand, String pValue) {
        UDPSender.getInstance().sendToDCS(pCommand.getTypeButton().getCode(),pCommand.getDevice().getCode(),pCommand.getCode(), pValue,getContext());
    }
}
