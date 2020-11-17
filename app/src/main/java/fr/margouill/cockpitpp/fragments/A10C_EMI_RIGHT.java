package fr.margouill.cockpitpp.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import fr.margouill.cockpitpp.R;
import fr.margouill.cockpitpp.custom_views.A10C_EMI_RIGHT_View;
import fr.margouill.cockpitpp.utils.A10C_Commands;
import fr.margouill.cockpitpp.utils.BroadcastKeys;
import fr.margouill.cockpitpp.utils.UDPSender;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@linkk A10C_HSI.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link A10C_EMI_RIGHT#  newInstance} factory method to
 * create an instance of this fragment.
 */
public class A10C_EMI_RIGHT extends Fragment {

    //The container of every view elements which must properly takes the size of the background
    private FrameLayout mContainerLinearLayout;


    private A10C_EMI_RIGHT_View mEMIRightView;
    /**
     * Broadcast receiver to get data from DCS
     */
    BroadcastReceiver mBroadCastNewMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Log.d(intent.getAction(), "onReceive: !!!!!");
            if (intent.getAction().contains(BroadcastKeys.A10C_EMI_RIGHT)) {
                String data = intent.getExtras().getString(BroadcastKeys.A10C_EMI_RIGHT);
                if (data != null && !data.isEmpty()) {
                    mEMIRightView.setData(data);
                    //Log.d(TAG, "onReceive: !!!!!");
                }
            }
        }
    };

    public A10C_EMI_RIGHT() {
        // Required empty public constructor
    }

    /**
     * Register the broadcast receiver
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().registerReceiver(this.mBroadCastNewMessage, new IntentFilter(BroadcastKeys.A10C_EMI_RIGHT));
    }

    /**
     * Bind the elements of the view leading the screen
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.d("asd","onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_a10_c__emi_left, container, false);
        mContainerLinearLayout = (FrameLayout) rootView.findViewById(R.id.a_10_emi_left_containerLinearLayout);
        mEMIRightView = new A10C_EMI_RIGHT_View(getActivity());
        mContainerLinearLayout.addView(mEMIRightView);
        return rootView;
    }

    private void sendCommand(A10C_Commands pCommand, String pValue) {
        UDPSender.getInstance().sendToDCS(pCommand.getTypeButton().getCode(), pCommand.getDevice().getCode(), pCommand.getCode(), pValue, getContext());
    }

    /**
     * Resize properly the elements view in the screen when we open the panel
     */
    @Override
    public void onResume() {
        super.onResume();
        resizeView();
    }

    /**
     * Detect when the user is rotating the phone/tablet
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        resizeView();
    }

    /**
     * Method used to resize the elements in the view to match properly and with good proportion the
     * background image. Very important for the rotations
     */
    private void resizeView() {
        /*mBackgroundImageView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @SuppressLint("NewApi")
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            mBackgroundImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            mBackgroundImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(mBackgroundImageView.getLayoutParams().width, mBackgroundImageView.getLayoutParams().height);
                        layoutParams.gravity = CENTER;

                    }
                });*/
        /*mContainerLinearLayout.setLayoutParams(layoutParams);
                        mContainerLinearLayout.getLayoutParams().height = mBackgroundImageView.getHeight();
                        mContainerLinearLayout.getLayoutParams().width = mBackgroundImageView.getWidth();*/
    }

    /**
     * Unregister the broadcast listener when leaving
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadCastNewMessage);
    }


}
