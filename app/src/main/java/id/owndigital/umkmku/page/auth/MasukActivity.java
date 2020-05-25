package id.owndigital.umkmku.page.auth;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.core.tools.LocationHandler;
import id.owndigital.umkmku.core.datasource.SPData;
import id.owndigital.umkmku.core.datasource.ApiClient;
import id.owndigital.umkmku.core.datasource.ApiService;
import id.owndigital.umkmku.core.models.MasukModel;
import id.owndigital.umkmku.page.HomeActivity;
import id.owndigital.umkmku.page.RequestLocationActivity;
import retrofit2.Call;
import retrofit2.Callback;

public class MasukActivity extends AppCompatActivity {

    private EditText email, password;
    private Button masuk;
    private ProgressBar pBar;
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        helper = new Helper(MasukActivity.this);
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
                startActivity(new Intent(MasukActivity.this, DaftarActivity.class));
            }
        });

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.closeKeyboard();
                String mEmail = email.getText().toString();
                String mPassword = password.getText().toString();
                Resources res = getResources();

                if (mEmail.isEmpty()) {
                    email.requestFocus();
                    email.setError(res.getString(R.string.isiKolom));
                } else if (mPassword.isEmpty()) {
                    password.requestFocus();
                    password.setError(res.getString(R.string.isiKolom));
                } else {
                    userMasuk(mEmail, mPassword, v);
                }
            }
        });
    }

    private void userMasuk(String email, String password, final View v) {
        masuk.setVisibility(View.INVISIBLE);
        pBar.setVisibility(View.VISIBLE);

        ApiService service = ApiClient.getClient().create(ApiService.class);

        retrofit2.Call<MasukModel> call = service.masuk(
                email,
                password
        );

        call.enqueue(new Callback<MasukModel>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<MasukModel> call, @NonNull retrofit2.Response<MasukModel> response) {
                masuk.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);
                if (response.code() == 200 && response.isSuccessful()) {
                    if (Objects.requireNonNull(response.body()).getSuccess() == 1) {
                        SPData.getInstance(MasukActivity.this).userLogin(
                                Objects.requireNonNull(response.body()).getUid(),
                                Objects.requireNonNull(response.body()).getNama(),
                                Objects.requireNonNull(response.body()).getEmail(),
                                Objects.requireNonNull(response.body()).getHp(),
                                Objects.requireNonNull(response.body()).getFoto()
                        );

                        Snackbar.make(v, "Berhasil Masuk", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                LocationHandler location = new LocationHandler(MasukActivity.this);
                                if (location.isLocationGranted() && location.isGpsOn()) {
                                    startActivity(new Intent(MasukActivity.this, HomeActivity.class));
                                    finish();
                                } else {
                                    startActivity(new Intent(MasukActivity.this, RequestLocationActivity.class));
                                    finish();
                                }
                            }
                        }, 2000L);
                    } else {
                        Snackbar.make(v, "Gagal Masuk", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(v, "Terjadi Kesalahan", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MasukModel> call, @NonNull Throwable t) {
                masuk.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);
                Snackbar.make(v, Objects.requireNonNull(t.getMessage()), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
