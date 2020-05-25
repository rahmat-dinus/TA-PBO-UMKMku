package id.owndigital.umkmku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import id.owndigital.umkmku.core.tools.LocationHandler;
import id.owndigital.umkmku.core.datasource.SPData;
import id.owndigital.umkmku.page.HomeActivity;
import id.owndigital.umkmku.page.RequestLocationActivity;
import id.owndigital.umkmku.page.auth.MasukActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        toNextActivity();
    }

    private void toNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SPData.getInstance(SplashActivity.this).isLoggedIn()) {
                    LocationHandler location = new LocationHandler(SplashActivity.this);
                    if (location.isLocationGranted() && location.isGpsOn()) {
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashActivity.this, RequestLocationActivity.class));
                        finish();
                    }
                } else {
                    startActivity(new Intent(SplashActivity.this, MasukActivity.class));
                    finish();
                }
            }
        }, 2000);
    }
}
