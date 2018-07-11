package rtsp.example.com.rtspsample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/** Helper to ask permission. */
public final class PermissionHelper {
    private static final int PERMISSIONS_CODE = 0;
    private static final String INTERNET_PERMISSION = Manifest.permission.INTERNET;
    private static final String ACCESS_NETWORK_STATE_PERMISSION = Manifest.permission.ACCESS_NETWORK_STATE;
    private static final String ACCESS_WIFI_STATE_PERMISSION = Manifest.permission.ACCESS_WIFI_STATE;

    /** Check to see we have the necessary permissions for this app. */
    public static boolean havePermissions(Activity activity) {
        return ContextCompat.checkSelfPermission(activity, INTERNET_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
               ContextCompat.checkSelfPermission(activity, ACCESS_NETWORK_STATE_PERMISSION) == PackageManager.PERMISSION_GRANTED &&
               ContextCompat.checkSelfPermission(activity, ACCESS_WIFI_STATE_PERMISSION) == PackageManager.PERMISSION_GRANTED;
    }

    /** Check to see we have the necessary permissions for this app, and ask for them if we don't. */
    public static void requestPermissions(Activity activity) {
        ActivityCompat.requestPermissions(
                activity, new String[] {INTERNET_PERMISSION, ACCESS_NETWORK_STATE_PERMISSION, ACCESS_WIFI_STATE_PERMISSION}, PERMISSIONS_CODE);
    }

    /** Check to see if we need to show the rationale for this permission. */
    public static boolean shouldShowRequestPermissionRationale(Activity activity) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, INTERNET_PERMISSION);
    }

    /** Launch Application Setting to grant permission. */
    public static void launchPermissionSettings(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivity(intent);
    }
}
