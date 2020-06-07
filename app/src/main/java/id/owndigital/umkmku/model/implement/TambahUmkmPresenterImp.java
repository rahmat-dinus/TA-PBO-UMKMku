package id.owndigital.umkmku.model.implement;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.AppController;
import id.owndigital.umkmku.core.datasource.BaseApi;
import id.owndigital.umkmku.core.tools.GPSTracker;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.presenter.TambahUmkmPresenter;
import id.owndigital.umkmku.view.TambahUmkmView;

public class TambahUmkmPresenterImp implements TambahUmkmPresenter {

    private Activity activity;
    private TambahUmkmView view;

    public TambahUmkmPresenterImp(Activity activity, TambahUmkmView view){
        this.activity = activity;
        this.view = view;
    }
    @Override
    public void getLocation() {
        GPSTracker gps = new GPSTracker(activity);
        view.locationHandler(gps.getLongitude(), gps.getLatitude());
    }

    @Override
    public void tambahData(EditText namaUmkm, EditText hpUmkm, EditText emailUmkm,
                           EditText namaPemilik, EditText hpPemilik, EditText emailPemilik,
                           EditText lon, EditText lat, View v) {
        new Helper(activity).closeKeyboard();
        String mNamaUmkm = namaUmkm.getText().toString();
        String mEmailUmkm = emailUmkm.getText().toString();
        String mTelponUmkm = hpUmkm.getText().toString();
        String mNamaPemilik = namaPemilik.getText().toString();
        String mEmailPemilik = emailPemilik.getText().toString();
        String mTelponPemilik = hpPemilik.getText().toString();
        String mLon = lon.getText().toString();
        String mLat = lat.getText().toString();
        Resources res = activity.getResources();

        if (mNamaUmkm.isEmpty()) {
            view.setError(namaUmkm, res.getString(R.string.isiKolom));
        } else if (mEmailUmkm.isEmpty()) {
            view.setError(emailUmkm, res.getString(R.string.isiKolom));
        } else if (mTelponUmkm.isEmpty()) {
            view.setError(hpUmkm, res.getString(R.string.isiKolom));
        } else if (mNamaPemilik.isEmpty()) {
            view.setError(namaPemilik, res.getString(R.string.isiKolom));
        } else if (mEmailPemilik.isEmpty()) {
            view.setError(emailPemilik, res.getString(R.string.isiKolom));
        } else if (mTelponPemilik.isEmpty()) {
            view.setError(hpPemilik, res.getString(R.string.isiKolom));
        } else if (mLon.isEmpty()) {
            view.setError(lon, res.getString(R.string.isiKolom));
        } else if (mLat.isEmpty()) {
            view.setError(lat, res.getString(R.string.isiKolom));
        } else {
            reqTambahData(mNamaUmkm, mEmailUmkm, mTelponUmkm, mNamaPemilik, mEmailPemilik,
                    mTelponPemilik, mLon, mLat);
        }
    }


    private void reqTambahData(final String mNamaUmkm, final String mEmailUmkm, final String mTelponUmkm,
                             final String mNamaPemilik, final String mEmailPemilik, final String mTelponPemilik,
                             final String mLon, final String mLat) {

        view.setProcess(true);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseApi.tambahData,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("res", response);
                        try {
                            JSONObject jObj = new JSONObject(response);
                            int success = jObj.getInt("success");
                            if (success == 1) {

                                view.showToast("Berhasil Tambah");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        activity.finish();
                                    }
                                }, 2000L);
                            } else {
                                view.showToast("Gagal Tambah");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            view.showToast(e
                                    .getMessage());
                        }
                        view.setProcess(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        view.setProcess(false);
                        view.showToast(Objects.requireNonNull(error.getMessage()));
                    }
                }) {


            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Accept", "application/json; charset=UTF-8");
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nama_umkm", mNamaUmkm);
                params.put("hp_umkm", mTelponUmkm);
                params.put("email_umkm", mEmailUmkm);
                params.put("nama_pemilik", mNamaPemilik);
                params.put("hp_pemilik", mTelponPemilik);
                params.put("email_pemilik", mEmailPemilik);
                params.put("longitude", mLon);
                params.put("latitude", mLat);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, "json_obj_req");
    }
}
