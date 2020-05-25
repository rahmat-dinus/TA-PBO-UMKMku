package id.owndigital.umkmku.page.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.core.datasource.ApiClient;
import id.owndigital.umkmku.core.datasource.ApiService;
import id.owndigital.umkmku.core.models.DaftarModel;
import retrofit2.Call;
import retrofit2.Callback;

public class DaftarActivity extends AppCompatActivity {

    private EditText nama, email, telpon, password, kPassword;
    private Button daftar;
    private ProgressBar pBar;
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        helper = new Helper(DaftarActivity.this);
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
                finish();
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.closeKeyboard();
                String mNama = nama.getText().toString();
                String mEmail = email.getText().toString();
                String mTelpon = telpon.getText().toString();
                String mPass = password.getText().toString();
                String mKPass = kPassword.getText().toString();
                Resources res = getResources();

                if (mNama.isEmpty()) {
                    nama.requestFocus();
                    nama.setError(res.getString(R.string.isiKolom));
                } else if (mEmail.isEmpty()) {
                    email.requestFocus();
                    email.setError(res.getString(R.string.isiKolom));
                } else if (mTelpon.isEmpty()) {
                    telpon.requestFocus();
                    telpon.setError(res.getString(R.string.isiKolom));
                } else if (mPass.isEmpty()) {
                    password.requestFocus();
                    password.setError(res.getString(R.string.isiKolom));
                } else if (mKPass.isEmpty()) {
                    kPassword.requestFocus();
                    kPassword.setError(res.getString(R.string.isiKolom));
                } else if (!mPass.equals(mKPass)) {
                    password.requestFocus();
                    password.setError(res.getString(R.string.passSama));
                    kPassword.requestFocus();
                    kPassword.setError(res.getString(R.string.passSama));
                } else {
                    userDaftar(mNama, mEmail, mTelpon, mPass, v);
                }
            }
        });
    }

    private void userDaftar(String nama, String email, String telpon, String password, final View v) {
        daftar.setVisibility(View.INVISIBLE);
        pBar.setVisibility(View.VISIBLE);

        ApiService service = ApiClient.getClient().create(ApiService.class);

        retrofit2.Call<DaftarModel> call = service.daftar(
                nama,
                telpon,
                email,
                password
        );

        call.enqueue(new Callback<DaftarModel>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<DaftarModel> call, @NonNull retrofit2.Response<DaftarModel> response) {
                daftar.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);
                if (response.code() == 200 && response.isSuccessful()) {
                    if (Objects.requireNonNull(response.body()).getSuccess() == 1) {
                        Snackbar.make(v, "Berhasil Daftar", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000L);
                    } else {
                        Snackbar.make(v, "Gagal Daftar", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(v, "Terjadi Kesalahan", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DaftarModel> call, @NonNull Throwable t) {
                daftar.setVisibility(View.VISIBLE);
                pBar.setVisibility(View.GONE);
                Snackbar.make(v, Objects.requireNonNull(t.getMessage()), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
