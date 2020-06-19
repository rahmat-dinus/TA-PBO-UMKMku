package id.owndigital.umkmku.page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

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
    private MaterialBetterSpinner jkPemilik;

    private static final String[] eJK = new String[] {
            "Laki - Laki", "Perempuan"
    };

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
        jkPemilik = findViewById(R.id.jkPemilik);
        lon = findViewById(R.id.longitude);
        lat = findViewById(R.id.latitude);
        Button gpsSkrg = findViewById(R.id.lokSkrg);
        tambah = findViewById(R.id.tambahData);
        pBar = findViewById(R.id.pBar);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, eJK);
        jkPemilik.setAdapter(adapter);

        gpsSkrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getLocation();
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.tambahData(namaUmkm, hpUmkm, emailumkm, namaPemilik, hpPemilik, emailPemilik, jkPemilik.getText().toString(), lon, lat, v);
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