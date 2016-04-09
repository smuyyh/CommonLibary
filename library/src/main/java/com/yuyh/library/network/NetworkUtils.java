package com.yuyh.library.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.yuyh.library.log.LogUtils;

import java.lang.reflect.Method;

/**
 * @author yuyuhang.
 * @date 16/4/9.
 */
public class NetworkUtils {
    private static final String TAG = Network.class.getSimpleName();

    public enum NetType {
        None(1, "无网络连接"),
        Mobile(2, "蜂窝移动网络"),
        Wifi(4, "Wifi网络"),
        Other(8, "未知网络");

        NetType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int value;
        public String desc;
    }

    public enum NetWorkType {
        UnKnown(-1, "未知网络"),
        Wifi(1, "Wifi网络"),
        Net2G(2, "2G网络"),
        Net3G(3, "3G网络"),
        Net4G(4, "4G网络");

        NetWorkType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int value;
        public String desc;
    }

    /**
     * 获取ConnectivityManager
     */
    public static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * 获取TelephonyManager
     */
    public static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * 判断网络是否连接(包含MobileNet，Wifi)
     *
     * @return
     */
    public static boolean isConnected(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        return net != null && net.isConnected();
    }

    /**
     * 判断有无网络正在连接中或已连接（查找网络、校验、获取IP等）(包含MobileNet，Wifi)
     *
     * @return
     */
    public static boolean isConnectedOrConnecting(Context context) {
        NetworkInfo[] nets = getConnectivityManager(context).getAllNetworkInfo();
        if (nets != null) {
            for (NetworkInfo net : nets) {
                if (net.isConnectedOrConnecting()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取当前网络类型(Wifi或移动网络)
     *
     * @param context
     * @return
     */
    public static NetType getConnectedType(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        if (net != null) {
            switch (net.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    return NetType.Wifi;
                case ConnectivityManager.TYPE_MOBILE:
                    return NetType.Mobile;
                default:
                    return NetType.Other;
            }
        }
        return NetType.None;
    }

    /**
     * 是否存在有效的WIFI连接
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        return net != null && net.getType() == ConnectivityManager.TYPE_WIFI && net.isConnected();
    }

    /**
     * 是否存在有效的移动连接
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        return net != null && net.getType() == ConnectivityManager.TYPE_MOBILE && net.isConnected();
    }

    /**
     * 检测当前网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isAvailable(Context context) {
        return isWifiAvailable(context) || (isMobileAvailable(context) && isMobileEnabled(context));
    }

    /**
     * 判断是否有可用状态的Wifi，以下情况返回false：
     * 1. 设备wifi开关关掉;
     * 2. 已经打开飞行模式；
     * 3. 设备所在区域没有信号覆盖；
     * 4. 设备在漫游区域，且关闭了网络漫游。
     *
     * @param context
     * @return boolean wifi为可用状态（不一定成功连接，即Connected）即返回ture
     */
    public static boolean isWifiAvailable(Context context) {
        NetworkInfo[] nets = getConnectivityManager(context).getAllNetworkInfo();
        if (nets != null) {
            for (NetworkInfo net : nets) {
                if (net.getType() == ConnectivityManager.TYPE_WIFI) {
                    return net.isAvailable();
                }
            }
        }
        return false;
    }

    /**
     * 判断有无可用状态的移动网络，注意关掉设备移动网络直接不影响此函数。
     * 也就是即使关掉移动网络，那么移动网络也可能是可用的(彩信等服务)，即返回true。
     * 以下情况它是不可用的，将返回false：
     * 1. 设备打开飞行模式；
     * 2. 设备所在区域没有信号覆盖；
     * 3. 设备在漫游区域，且关闭了网络漫游。
     *
     * @param context
     * @return boolean
     */
    public static boolean isMobileAvailable(Context context) {
        NetworkInfo[] nets = getConnectivityManager(context).getAllNetworkInfo();
        if (nets != null) {
            for (NetworkInfo net : nets) {
                if (net.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return net.isAvailable();
                }
            }
        }
        return false;
    }

    /**
     * 设备是否打开蜂窝移动网络开关
     *
     * @param context
     * @return
     */
    public static boolean isMobileEnabled(Context context) {
        try {
            Method getMobileDataEnabledMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled");
            getMobileDataEnabledMethod.setAccessible(true);
            return (Boolean) getMobileDataEnabledMethod.invoke(getConnectivityManager(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;// 反射失败，默认开启
    }

    /**
     * 打印当前各种网络状态
     *
     * @return boolean
     */
    public static boolean printNetworkInfo(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    LogUtils.i(TAG, "NetworkInfo[" + i + "]isAvailable : " + info[i].isAvailable());
                    LogUtils.i(TAG, "NetworkInfo[" + i + "]isConnected : " + info[i].isConnected());
                    LogUtils.i(TAG, "NetworkInfo[" + i + "]isConnectedOrConnecting : " + info[i].isConnectedOrConnecting());
                    LogUtils.i(TAG, "NetworkInfo[" + i + "]: " + info[i]);
                }
                LogUtils.i(TAG, "\n");
            } else {
                LogUtils.i(TAG, "getAllNetworkInfo is null");
            }
        }
        return false;
    }

    /**
     * get connected network type by {@link ConnectivityManager}
     * <p/>
     * such as WIFI, MOBILE, ETHERNET, BLUETOOTH, etc.
     *
     * @return {@link ConnectivityManager#TYPE_WIFI}, {@link ConnectivityManager#TYPE_MOBILE},
     * {@link ConnectivityManager#TYPE_ETHERNET}...
     */
    public static int getConnectedTypeINT(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        if (net != null) {
            return net.getType();
        }
        return -1;
    }

    /**
     * 获取网络连接类型
     * <p/>
     * GPRS    2G(2.5) General Packet Radia Service 114kbps
     * EDGE    2G(2.75G) Enhanced Data Rate for GSM Evolution 384kbps
     * UMTS    3G WCDMA 联通3G Universal Mobile Telecommunication System 完整的3G移动通信技术标准
     * CDMA    2G 电信 Code Division Multiple Access 码分多址
     * EVDO_0  3G (EVDO 全程 CDMA2000 1xEV-DO) Evolution - Data Only (Data Optimized) 153.6kps - 2.4mbps 属于3G
     * EVDO_A  3G 1.8mbps - 3.1mbps 属于3G过渡，3.5G
     * 1xRTT   2G CDMA2000 1xRTT (RTT - 无线电传输技术) 144kbps 2G的过渡,
     * HSDPA   3.5G 高速下行分组接入 3.5G WCDMA High Speed Downlink Packet Access 14.4mbps
     * HSUPA   3.5G High Speed Uplink Packet Access 高速上行链路分组接入 1.4 - 5.8 mbps
     * HSPA    3G (分HSDPA,HSUPA) High Speed Packet Access
     * IDEN    2G Integrated Dispatch Enhanced Networks 集成数字增强型网络 （属于2G，来自维基百科）
     * EVDO_B  3G EV-DO Rev.B 14.7Mbps 下行 3.5G
     * LTE     4G Long Term Evolution FDD-LTE 和 TDD-LTE , 3G过渡，升级版 LTE Advanced 才是4G
     * EHRPD   3G CDMA2000向LTE 4G的中间产物 Evolved High Rate Packet Data HRPD的升级
     * HSPAP   3G HSPAP 比 HSDPA 快些
     *
     * @return {@link  NetWorkType}
     */
    public static NetWorkType getNetworkType(Context context) {
        int type = getConnectedTypeINT(context);
        switch (type) {
            case ConnectivityManager.TYPE_WIFI:
                return NetWorkType.Wifi;
            case ConnectivityManager.TYPE_MOBILE:
            case ConnectivityManager.TYPE_MOBILE_DUN:
            case ConnectivityManager.TYPE_MOBILE_HIPRI:
            case ConnectivityManager.TYPE_MOBILE_MMS:
            case ConnectivityManager.TYPE_MOBILE_SUPL:
                int teleType = getTelephonyManager(context).getNetworkType();
                switch (teleType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        return NetWorkType.Net2G;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        return NetWorkType.Net3G;
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        return NetWorkType.Net4G;
                    default:
                        return NetWorkType.UnKnown;
                }
            default:
                return NetWorkType.UnKnown;
        }
    }

}
