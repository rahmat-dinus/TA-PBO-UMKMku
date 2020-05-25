package id.owndigital.umkmku.core.tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

import java.util.Objects;

public class LocationHandler {
    private Activity activity;

    public LocationHandler(Activity activity) {
        this.activity = activity;
    }

    public boolean isLocationGranted() {
        return ContextCompat.checkSelfPermission(this.activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isGpsOn() {
        LocationManager manager = (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);
        return Objects.requireNonNull(manager).isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public void openGpsSetting() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        this.activity.startActivity(intent);

    }
}
