package id.owndigital.umkmku.page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.model.implement.RequestLocationImp;
import id.owndigital.umkmku.presenter.RequestLocationPresenter;
import id.owndigital.umkmku.view.RequestLocationView;

public class RequestLocationActivity extends AppCompatActivity implements RequestLocationView {

    RequestLocationPresenter presenter;

    private TextView nyalaGps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_location);

        nyalaGps = findViewById(R.id.textLocation);
        Button tOn = findViewById(R.id.tOn);
        Button openSetting = findViewById(R.id.openSetting);
        Button keluar = findViewById(R.id.keluar);

        presenter = new RequestLocationImp(this, this);

        tOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.turnOn();
            }
        });

        openSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openSetting();
            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.keluar();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void setKeterangan(String keterangan) {
        nyalaGps.setText(keterangan);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
