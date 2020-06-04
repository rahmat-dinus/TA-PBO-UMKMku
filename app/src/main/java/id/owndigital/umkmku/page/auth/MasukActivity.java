package id.owndigital.umkmku.page.auth;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.model.implement.MasukPresenterImp;
import id.owndigital.umkmku.presenter.MasukPresenter;
import id.owndigital.umkmku.view.MasukView;

public class MasukActivity extends AppCompatActivity implements MasukView {

    MasukPresenter presenter;

    private EditText email, password;
    private Button masuk;
    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        presenter = new MasukPresenterImp(this, this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        masuk = findViewById(R.id.btnMasuk);
        pBar = findViewById(R.id.pBar);
        TextView daftar = findViewById(R.id.daftar);

        masuk.setVisibility(View.VISIBLE);
        pBar.setVisibility(View.GONE);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.daftar();
            }
        });

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.masuk(email, password, v);
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
        if (onProcess){
            pBar.setVisibility(View.VISIBLE);
            masuk.setVisibility(View.GONE);
        }else{
            pBar.setVisibility(View.GONE);
            masuk.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void success(final Class<?> activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Helper(MasukActivity.this).movePage(activity);
            }
        }, 2000);
    }

    @Override
    public void showSnackbar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }
}
