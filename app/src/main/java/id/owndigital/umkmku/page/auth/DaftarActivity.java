package id.owndigital.umkmku.page.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.model.implement.DaftarPresnterImp;
import id.owndigital.umkmku.presenter.DaftarPresenter;
import id.owndigital.umkmku.view.DaftarView;

public class DaftarActivity extends AppCompatActivity implements DaftarView {

    DaftarPresenter presenter;
    private EditText nama, email, telpon, password, kPassword;
    private Button daftar;
    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        presenter = new DaftarPresnterImp(this, this);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        telpon = findViewById(R.id.hp);
        password = findViewById(R.id.password);
        kPassword = findViewById(R.id.kPassword);
        daftar = findViewById(R.id.btnDaftar);
        pBar = findViewById(R.id.pBar);
        TextView masuk = findViewById(R.id.masuk);

        daftar.setVisibility(View.VISIBLE);
        pBar.setVisibility(View.GONE);

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.masuk();
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.daftar(nama, email, telpon, password, kPassword, v);
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
            daftar.setVisibility(View.GONE);
        } else {
            pBar.setVisibility(View.GONE);
            daftar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showSnackbar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }
}
