package id.owndigital.umkmku.model.implement;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.util.Objects;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.datasource.ApiClient;
import id.owndigital.umkmku.core.datasource.ApiService;
import id.owndigital.umkmku.core.datasource.SPData;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.core.tools.LocationHandler;
import id.owndigital.umkmku.model.MasukModel;
import id.owndigital.umkmku.page.HomeActivity;
import id.owndigital.umkmku.page.RequestLocationActivity;
import id.owndigital.umkmku.page.auth.DaftarActivity;
import id.owndigital.umkmku.presenter.MasukPresenter;
import id.owndigital.umkmku.view.MasukView;
import retrofit2.Call;
import retrofit2.Callback;

public class MasukPresenterImp implements MasukPresenter {

    private MasukView view;
    private Activity activity;

    public MasukPresenterImp(MasukView view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void masuk(EditText email, EditText password, View v) {
        new Helper(activity).closeKeyboard();
        String mEmail = email.getText().toString();
        String mPassword = password.getText().toString();
        Resources res = activity.getResources();

        if (mEmail.isEmpty()) {
            view.setError(email, res.getString(R.string.isiKolom));
        } else if (mPassword.isEmpty()) {
            view.setError(password, res.getString(R.string.isiKolom));
        } else {
            userMasuk(mEmail, mPassword, v);
        }
    }

    @Override
    public void daftar() {
        new Helper(activity).newPage(DaftarActivity.class);
    }

    private void userMasuk(String email, String password, final View v) {
        view.setProcess(true);

        ApiService service = ApiClient.getClient().create(ApiService.class);

        retrofit2.Call<MasukModel> call = service.masuk(
                email,
                password
        );

        call.enqueue(new Callback<MasukModel>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<MasukModel> call, @NonNull retrofit2.Response<MasukModel> response) {
                view.setProcess(false);
                if (response.code() == 200 && response.isSuccessful()) {
                    if (Objects.requireNonNull(response.body()).getSuccess() == 1) {
                        SPData.getInstance(activity).userLogin(
                                Objects.requireNonNull(response.body()).getUid(),
                                Objects.requireNonNull(response.body()).getNama(),
                                Objects.requireNonNull(response.body()).getEmail(),
                                Objects.requireNonNull(response.body()).getHp(),
                                Objects.requireNonNull(response.body()).getFoto()
                        );
                        view.showSnackbar(v, "Berhasil Masuk");
                        LocationHandler location = new LocationHandler(activity);
                        if (location.isLocationGranted() && location.isGpsOn()) {
                            view.success(HomeActivity.class);
                        } else {
                            view.success(RequestLocationActivity.class);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }, 2000L);
                    } else {
                        view.showSnackbar(v, "Gagal Masuk");
                    }
                } else {
                    view.showSnackbar(v, "Terjadi Kesalahan");
                }
            }

            @Override
            public void onFailure(@NonNull Call<MasukModel> call, @NonNull Throwable t) {
                view.setProcess(false);
                view.showSnackbar(v, Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
