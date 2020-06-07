package id.owndigital.umkmku.page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.model.implement.EditUmkmPresenterImp;
import id.owndigital.umkmku.model.implement.TambahUmkmPresenterImp;
import id.owndigital.umkmku.presenter.TambahUmkmPresenter;
import id.owndigital.umkmku.view.TambahUmkmView;

public class TambahUmkmActivity extends AppCompatActivity implements TambahUmkmView {

    TambahUmkmPresenter presenter;

    private EditText namaUmkm, hpUmkm, emailumkm, namaPemilik, hpPemilik, emailPemilik, lon, lat;
    private Button tambah;
    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_umkm);

        presenter = new TambahUmkmPresenterImp(this, this);
        namaUmkm = findViewById(R.id.namaUmkm);
        hpUmkm = findViewById(R.id.hpUmkm);
        emailumkm = findViewById(R.id.emailUmkm);
        namaPemilik = findViewById(R.id.namaPemilik);
        hpPemilik = findViewById(R.id.hpPemilik);
        emailPemilik = findViewById(R.id.emailPemilik);
        lon = findViewById(R.id.longitude);
        lat = findViewById(R.id.latitude);
        Button gpsSkrg = findViewById(R.id.lokSkrg);
        tambah = findViewById(R.id.tambahData);
        pBar = findViewById(R.id.pBar);

        gpsSkrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getLocation();
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.tambahData(namaUmkm, hpUmkm, emailumkm, namaPemilik, hpPemilik, emailPemilik, lon, lat, v);
            }
        });
    }

    @Override
    public void setError(EditText field, String error) {

        field.requestFocus();
        field.setError(error);
    }

    @Override
    public void setProcess(boolean onProcess) {

        if (onProcess) {
            pBar.setVisibility(View.VISIBLE);
            tambah.setVisibility(View.GONE);
        } else {
            pBar.setVisibility(View.GONE);
            tambah.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void locationHandler(double lon, double lat) {

        this.lon.setText(String.valueOf(lon));
        this.lat.setText(String.valueOf(lat));
    }
}