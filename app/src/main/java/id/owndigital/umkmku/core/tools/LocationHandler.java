package id.owndigital.umkmku.core.tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.provider.Settings;

import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class LocationHandler {
    private Activity activity;
    private Geocoder geocoder;
    private LocationManager locationManager;
    private GPSTracker tracker;

    public LocationHandler(Activity activity) {
        this.activity = activity;
        this.tracker = new GPSTracker(this.activity);
        locationManager = (LocationManager) this.activity.getSystemService(Context.LOCATION_SERVICE);
        geocoder = new Geocoder(this.activity, Locale.getDefault());
    }

    public boolean isLocationGranted() {
        return ContextCompat.checkSelfPermission(this.activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isGpsOn() {
        return Objects.requireNonNull(locationManager).isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public void openGpsSetting() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        this.activity.startActivity(intent);
    }

    public String getAddress() {
        try {
            List<Address> addresses = geocoder.getFromLocation(tracker.getLatitude(), tracker.getLongitude(), 1);
            return addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
