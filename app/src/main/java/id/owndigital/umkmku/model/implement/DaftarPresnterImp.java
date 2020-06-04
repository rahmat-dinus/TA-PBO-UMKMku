package id.owndigital.umkmku.model.implement;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.datasource.ApiClient;
import id.owndigital.umkmku.core.datasource.ApiService;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.model.DaftarModel;
import id.owndigital.umkmku.presenter.DaftarPresenter;
import id.owndigital.umkmku.view.DaftarView;
import retrofit2.Call;
import retrofit2.Callback;

public class DaftarPresnterImp implements DaftarPresenter {

    private Activity activity;
    private DaftarView view;

    public DaftarPresnterImp(Activity activity, DaftarView view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void daftar(EditText nama, EditText email, EditText telpon, EditText password, EditText cPassword, View v) {
        new Helper(activity).closeKeyboard();
        String mNama = nama.getText().toString();
        String mEmail = email.getText().toString();
        String mTelpon = telpon.getText().toString();
        String mPass = password.getText().toString();
        String mKPass = cPassword.getText().toString();
        Resources res = activity.getResources();

        if (mNama.isEmpty()) {
            view.setError(nama, res.getString(R.string.isiKolom));
        } else if (mEmail.isEmpty()) {
            view.setError(email, res.getString(R.string.isiKolom));
        } else if (mTelpon.isEmpty()) {
            view.setError(telpon, res.getString(R.string.isiKolom));
        } else if (mPass.isEmpty()) {
            view.setError(password, res.getString(R.string.isiKolom));
        } else if (mKPass.isEmpty()) {
            view.setError(cPassword, res.getString(R.string.isiKolom));
        } else if (!mPass.equals(mKPass)) {
            view.setError(password, res.getString(R.string.passSama));
            view.setError(cPassword, res.getString(R.string.passSama));
        } else {
            userDaftar(mNama, mEmail, mTelpon, mPass, v);
        }
    }

    @Override
    public void masuk() {
        activity.finish();
    }


    private void userDaftar(String nama, String email, String telpon, String password, final View v) {
        view.setProcess(true);

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
                view.setProcess(false);
                if (response.code() == 200 && response.isSuccessful()) {
                    if (Objects.requireNonNull(response.body()).getSuccess() == 1) {
                        view.showSnackbar(v, "Berhasil Daftar");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                activity.finish();
                            }
                        }, 2000L);
                    } else {
                        view.showSnackbar(v, "Gagal Daftar");
                    }
                } else {
                    view.showSnackbar(v, "Terjadi Kesalahan");
                }
            }

            @Override
            public void onFailure(@NonNull Call<DaftarModel> call, @NonNull Throwable t) {
                view.setProcess(false);
                view.showSnackbar(v, Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
