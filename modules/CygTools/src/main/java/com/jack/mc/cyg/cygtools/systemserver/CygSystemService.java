package com.jack.mc.cyg.cygtools.systemserver;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.NetworkStatsManager;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.midi.MidiManager;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.DropBoxManager;
import android.os.PowerManager;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.service.wallpaper.WallpaperService;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;

import com.jack.mc.cyg.cygtools.app.CygApplication;

/**
 * 系统服务
 */
public final class CygSystemService {

    private CygSystemService() {
    }

    public static InputMethodManager getInputMethodManager(Context context) {
        if (context == null) {
            context = CygApplication.getInstance();
        }
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static InputMethodManager getInputMethodManager() {
        return getInputMethodManager(null);
    }

    public static AlarmManager getAlarmManager() {
        return (AlarmManager) CygApplication.getInstance().getSystemService(Context.ALARM_SERVICE);
    }

    public static PowerManager getPowerManager() {
        return (PowerManager) CygApplication.getInstance().getSystemService(Context.POWER_SERVICE);
    }

    public static WindowManager getWindowManager() {
        return (WindowManager) CygApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
    }

    public static LayoutInflater getLayoutInflater() {
        return (LayoutInflater) CygApplication.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static AccountManager getAccountManager() {
        return (AccountManager) CygApplication.getInstance().getSystemService(Context.ACCOUNT_SERVICE);
    }

    public static ActivityManager getActivityManager() {
        return (ActivityManager) CygApplication.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
    }

    public static NotificationManager getNotificationManager() {
        return (NotificationManager) CygApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static AccessibilityManager getAccessibilityManager() {
        return (AccessibilityManager) CygApplication.getInstance().getSystemService(Context.ACCESSIBILITY_SERVICE);
    }

    public static CaptioningManager getCaptioningManager() {
        return (CaptioningManager) CygApplication.getInstance().getSystemService(Context.CAPTIONING_SERVICE);
    }

    public static KeyguardManager getKeyguardManager() {
        return (KeyguardManager) CygApplication.getInstance().getSystemService(Context.KEYGUARD_SERVICE);
    }

    public static LocationManager getLocationManager() {
        return (LocationManager) CygApplication.getInstance().getSystemService(Context.LOCATION_SERVICE);
    }

    public static SearchManager getSearchManager() {
        return (SearchManager) CygApplication.getInstance().getSystemService(Context.SEARCH_SERVICE);
    }

    public static SensorManager getSensorManager() {
        return (SensorManager) CygApplication.getInstance().getSystemService(Context.SENSOR_SERVICE);
    }

    public static StorageManager getStorageManager() {
        return (StorageManager) CygApplication.getInstance().getSystemService(Context.STORAGE_SERVICE);
    }

    public static WallpaperService getWallpaperService() {
        return (WallpaperService) CygApplication.getInstance().getSystemService(Context.WALLPAPER_SERVICE);
    }

    public static Vibrator getVibrator() {
        return (Vibrator) CygApplication.getInstance().getSystemService(Context.VIBRATOR_SERVICE);
    }

    public static ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) CygApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static NetworkStatsManager getNetworkStatsManager() {
        return (NetworkStatsManager) CygApplication.getInstance().getSystemService(Context.NETWORK_STATS_SERVICE);
    }

    public static WifiManager getWifiManager() {
        return (WifiManager) CygApplication.getInstance().getSystemService(Context.WIFI_SERVICE);
    }

    public static WifiP2pManager getWifiP2pManager() {
        return (WifiP2pManager) CygApplication.getInstance().getSystemService(Context.WIFI_P2P_SERVICE);
    }

    public static NsdManager getNsdManager() {
        return (NsdManager) CygApplication.getInstance().getSystemService(Context.NSD_SERVICE);
    }

    public static AudioManager getAudioManager() {
        return (AudioManager) CygApplication.getInstance().getSystemService(Context.AUDIO_SERVICE);
    }

    public static FingerprintManager getFingerprintManager() {
        return (FingerprintManager) CygApplication.getInstance().getSystemService(Context.FINGERPRINT_SERVICE);
    }

    public static MediaRouter getMediaRouter() {
        return (MediaRouter) CygApplication.getInstance().getSystemService(Context.MEDIA_ROUTER_SERVICE);
    }

    public static MediaSessionManager getMediaSessionManager() {
        return (MediaSessionManager) CygApplication.getInstance().getSystemService(Context.MEDIA_SESSION_SERVICE);
    }

    public static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) CygApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static SubscriptionManager getSubscriptionManager() {
        return (SubscriptionManager) CygApplication.getInstance().getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
    }

    public static TelecomManager getTelecomManager() {
        return (TelecomManager) CygApplication.getInstance().getSystemService(Context.TELECOM_SERVICE);
    }

    public static CarrierConfigManager getCarrierConfigManager() {
        return (CarrierConfigManager) CygApplication.getInstance().getSystemService(Context.CARRIER_CONFIG_SERVICE);
    }

    public static ClipboardManager getClipboardManager() {
        return (ClipboardManager) CygApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public static TextServicesManager getTextServicesManager() {
        return (TextServicesManager) CygApplication.getInstance().getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);
    }

    public static AppWidgetManager getAppWidgetManager() {
        return (AppWidgetManager) CygApplication.getInstance().getSystemService(Context.APPWIDGET_SERVICE);
    }

    public static DropBoxManager getDropBoxManager() {
        return (DropBoxManager) CygApplication.getInstance().getSystemService(Context.DROPBOX_SERVICE);
    }

    public static DevicePolicyManager getDevicePolicyManager() {
        return (DevicePolicyManager) CygApplication.getInstance().getSystemService(Context.DEVICE_POLICY_SERVICE);
    }

    public static UiModeManager getUiModeManager() {
        return (UiModeManager) CygApplication.getInstance().getSystemService(Context.UI_MODE_SERVICE);
    }

    public static DownloadManager getDownloadManager() {
        return (DownloadManager) CygApplication.getInstance().getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public static BatteryManager getBatteryManager() {
        return (BatteryManager) CygApplication.getInstance().getSystemService(Context.BATTERY_SERVICE);
    }

    public static NfcManager getNfcManager() {
        return (NfcManager) CygApplication.getInstance().getSystemService(Context.NFC_SERVICE);
    }

    public static BluetoothManager getBluetoothManager() {
        return (BluetoothManager) CygApplication.getInstance().getSystemService(Context.BLUETOOTH_SERVICE);
    }

    public static UsbManager getUsbManager() {
        return (UsbManager) CygApplication.getInstance().getSystemService(Context.USB_SERVICE);
    }

    public static InputManager getInputManager() {
        return (InputManager) CygApplication.getInstance().getSystemService(Context.INPUT_SERVICE);
    }
    public static DisplayManager getDisplayManager() {
        return (DisplayManager) CygApplication.getInstance().getSystemService(Context.DISPLAY_SERVICE);
    }

    public static UserManager getUserManager() {
        return (UserManager) CygApplication.getInstance().getSystemService(Context.USER_SERVICE);
    }

    public static LauncherApps getLauncherApps() {
        return (LauncherApps) CygApplication.getInstance().getSystemService(Context.LAUNCHER_APPS_SERVICE);
    }

    public static RestrictionsManager getRestrictionsManager() {
        return (RestrictionsManager) CygApplication.getInstance().getSystemService(Context.RESTRICTIONS_SERVICE);
    }

    public static AppOpsManager getAppOpsManager() {
        return (AppOpsManager) CygApplication.getInstance().getSystemService(Context.APP_OPS_SERVICE);
    }

    public static CameraManager getCameraManager() {
        return (CameraManager) CygApplication.getInstance().getSystemService(Context.CAMERA_SERVICE);
    }

    public static PrintManager getPrintManager() {
        return (PrintManager) CygApplication.getInstance().getSystemService(Context.PRINT_SERVICE);
    }

    public static ConsumerIrManager getConsumerIrManager() {
        return (ConsumerIrManager) CygApplication.getInstance().getSystemService(Context.CONSUMER_IR_SERVICE);
    }

    public static TvInputManager getTvInputManager() {
        return (TvInputManager) CygApplication.getInstance().getSystemService(Context.TV_INPUT_SERVICE);
    }

    public static UsageStatsManager getUsageStatsManager() {
        return (UsageStatsManager) CygApplication.getInstance().getSystemService(Context.USAGE_STATS_SERVICE);
    }

    public static JobScheduler getJobScheduler() {
        return (JobScheduler) CygApplication.getInstance().getSystemService(Context.JOB_SCHEDULER_SERVICE);
    }

    public static MediaProjectionManager getMediaProjectionManager() {
        return (MediaProjectionManager) CygApplication.getInstance().getSystemService(Context.MEDIA_PROJECTION_SERVICE);
    }

    public static MidiManager getMidiManager() {
        return (MidiManager) CygApplication.getInstance().getSystemService(Context.MIDI_SERVICE);
    }

}
