package fr.astazou.cockpitplusplus.utils;

/**
 * Created by astazou on 18/09/2016.
 * Modified by Margouill' on 15/11/2020.
 *
 */
public enum Ka50_Commands {

    //PUI800 Panel
    PUI800MasterArm(3001, Ka50_Devices.WEAP_INTERFACE, TypeButtonCodes.Simple),
    PUI800JettisonAA(3002, Ka50_Devices.WEAP_INTERFACE, TypeButtonCodes.Push),
    PUI800JettisonExt(3003, Ka50_Devices.WEAP_INTERFACE, TypeButtonCodes.Push),
    PUI800JettisonArm(3022, Ka50_Devices.WEAP_INTERFACE, TypeButtonCodes.Simple),
    PUI800Mode(3005, Ka50_Devices.WEAP_INTERFACE, TypeButtonCodes.Simple),
    PUI800Round(3006, Ka50_Devices.WEAP_INTERFACE, TypeButtonCodes.Simple),
    PUI800Burst(3004, Ka50_Devices.WEAP_INTERFACE, TypeButtonCodes.ThreePosition),
    PUI800Rate(3020, Ka50_Devices.WEAP_INTERFACE, TypeButtonCodes.Simple),
    PUI800JettisonATGM(3021, Ka50_Devices.WEAP_INTERFACE, TypeButtonCodes.Simple),

    //Overhead Panel
    OVERHEADNav(3004, Ka50_Devices.NAVLIGHT_SYSTEM, TypeButtonCodes.FourPosition),
    OVERHEADWiper(3006, Ka50_Devices.CPT_MECH, TypeButtonCodes.FourPosition),
    OVERHEADPitot(3009, Ka50_Devices.CPT_MECH, TypeButtonCodes.Simple),
    OVERHEADStatic(3008, Ka50_Devices.CPT_MECH, TypeButtonCodes.Simple),
    OVERHEADRotorAntiice(3013, Ka50_Devices.ENGINE_INTERFACE, TypeButtonCodes.Simple),
    OVERHEADEngDeice(3014, Ka50_Devices.ENGINE_INTERFACE, TypeButtonCodes.Simple),

    //UV26
    UV26Side(3001, Ka50_Devices.UV_26, TypeButtonCodes.ThreePosition),
    UV26Qte(3003, Ka50_Devices.UV_26, TypeButtonCodes.Simple),
    UV26Number(3004, Ka50_Devices.UV_26, TypeButtonCodes.Push),
    UV26Sequence(3005, Ka50_Devices.UV_26, TypeButtonCodes.Push),
    UV26Stop(3009, Ka50_Devices.UV_26, TypeButtonCodes.Push),
    UV26Interval(3006, Ka50_Devices.UV_26, TypeButtonCodes.Push),
    UV26Reset(3008, Ka50_Devices.UV_26, TypeButtonCodes.Push),
    UV26Start(3007, Ka50_Devices.UV_26, TypeButtonCodes.Push),

    //PRTz Datalink Control Panel
    PRTzVehicle(3010, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTzSAM(3011, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTzOther(3012, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTzPoint(3013, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTz1(3006, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTz2(3007, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTz3(3008, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTz4(3009, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTzAll(3005, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTzEmpty(3004, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTzClear(3003, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTzIngress(3002, Ka50_Devices.DATALINK, TypeButtonCodes.Push),
    PRTzSend(3001, Ka50_Devices.DATALINK, TypeButtonCodes.Push),

    //Laser
    LWRReset(3001, Ka50_Devices.LASER_WARNING_SYSTEM, TypeButtonCodes.Push)
    ;





    private final int mCode;
    private final Ka50_Devices mDevice;
    private final TypeButtonCodes mTypeButton;

    Ka50_Commands(int pCode, Ka50_Devices pDevice, TypeButtonCodes pTypeButton) {
        mCode = pCode;
        mDevice = pDevice;
        mTypeButton = pTypeButton;
    }

    public int getCode() {
        return mCode;
    }
    public Ka50_Devices getDevice() {
        return mDevice;
    }
    public TypeButtonCodes getTypeButton() {
        return mTypeButton;
    }
}
