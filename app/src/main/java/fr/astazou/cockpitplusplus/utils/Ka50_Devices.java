package fr.astazou.cockpitplusplus.utils;

/**
 * Created by Margouill on 11/11/2020.
 *
 */
public enum Ka50_Devices {

    ELEC_INTERFACE(2),
    FUELSYS_INTERFACE(3),
    ENGINE_INTERFACE(4),
    HYDRO_SYS_INTERFACE(5),
    EJECT_SYS_INTERFACE(6),
    HUD(7),
    SHKVAL(8),
    ABRIS(9),
    EKRAN_32_03(10),
    LASERRANGER(11),
    WEAP_INTERFACE(12),
    VMS(13),
    SYST_CONTROLLER(14),
    C061K(15),
    DISS_32_28(16),
    KI_13(17),
    ID_6(18),
    SNS(19),
    PVI(20),
    PUI_800(21),
    UV_26(22),
    HELMET(23),
    PADLOCK(24),
    DATALINK(25),
    CVM_N(26),
    CVM_B(27),
    NAV_INTERFACE(28),
    CLOCK(29),
    HSI(30),
    ADI(31),
    PPK(32),
    AUTOPILOT(33),
    CPT_MECH(34),
    GUN(35),
    LASER_WARNING_SYSTEM(36),
    MISSILE_TURRET(37),
    RADAR_ALTIMETER(38),
    BLINK_TIMER(39),
    FIRE_EXTING_INTERFACE(40),
    MISC_SYSTEMS_INTERFACE(41),
    IFF(42),
    LIMITERS(43),
    SPOTLIGHT_SYSTEM(44),
    NAVLIGHT_SYSTEM(45),
    ARK_22(46),
    MRP(47),
    R_800(48),
    R_828(49),
    SPU_9(50),
    ILLUMINATION_INTERFACE(51),
    SIGNAL_FLARE_DISPENSER(52),
    MLWS(53),
    ARCADE(54),
    STBY_ADI(55),
    X52Pro(56),
    PShK_7(57),
    ZMS_3(58),
    K041(59),
    MACROS(60),
    ACCELEROMETER(61),
    AVIONICS_PROXY(62);

    private final int mCode;

    Ka50_Devices(int pCode) {
        mCode = pCode;
    }

    public int getCode() {
        return mCode;
    }
}
