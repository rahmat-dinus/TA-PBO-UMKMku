package id.owndigital.umkmku.page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.core.tools.LocationHandler;
import id.owndigital.umkmku.core.tools.PermissionsHandler;

public class RequestLocationActivity extends AppCompatActivity {

    private TextView nyalaGps;
    private Button tOn, openSetting, keluar;
    private PermissionsHandler permissions;
    private Helper helper;
    private LocationHandler location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_location);

        initState();

        tOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!location.isLocationGranted()) {
                    permissions.getPermissions();
                } else if (!location.isGpsOn()) {
                    location.openGpsSetting();
                }
            }
        });

        openSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!location.isLocationGranted()) {
                    helper.openSettingsOfApp();
                }
                if (!location.isGpsOn()) {
                    location.openGpsSetting();
                }
            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initState() {
        Activity activity = RequestLocationActivity.this;
        permissions = new PermissionsHandler(activity);
        location = new LocationHandler(activity);
        helper = new Helper(activity);

        nyalaGps = findViewById(R.id.textLocation);
        tOn = findViewById(R.id.tOn);
        openSetting = findViewById(R.id.openSetting);
        keluar = findViewById(R.id.keluar);


        if (!location.isLocationGranted()) {
            nyalaGps.setText(R.string.reqLoc);
        } else if (!location.isGpsOn()) {
            nyalaGps.setText(R.string.nyalaGps);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (location.isLocationGranted() && location.isGpsOn()) {
            startActivity(new Intent(RequestLocationActivity.this, HomeActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == this.permissions.reqGetLocation) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (location.isGpsOn()) {
                    startActivity(new Intent(RequestLocationActivity.this, HomeActivity.class));
                } else {
                    nyalaGps.setText(R.string.nyalaGps);
                }
            } else {
                Toast.makeText(RequestLocationActivity.this, "Ijin Di Tolak!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
