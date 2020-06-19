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
import id.owndigital.umkmku.core.tools.LocationHandler;
import id.owndigital.umkmku.model.DetailUmkmModel;
import id.owndigital.umkmku.presenter.EditUmkmPresenter;
import id.owndigital.umkmku.view.EditUmkmView;

public class EditUmkmPresenterImp implements EditUmkmPresenter {

    private Activity activity;
    private EditUmkmView view;
    private String uid;
    private DetailUmkmModel detailUmkmModel;

    public EditUmkmPresenterImp(Activity activity, EditUmkmView view, String uid) {
        this.activity = activity;
        this.view = view;
        this.uid = uid;
    }

    @Override
    public void getData() {
        final Resources res = activity.getResources();
        view.setProcess(true);

        StringRequest strReq = new StringRequest(Request.Method.GET, BaseApi.loadDetail + uid,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {                            JSONObject data = new JSONObject(response);
                            if (data.getInt("success") == 1) {
                                detailUmkmModel = new DetailUmkmModel(
                                        data.getString("count_populer"),
                                        data.getString("uid"),
                                        data.getString("nama_umkm"),
                                        data.getString("hp_umkm"),
                                        data.getString("email_umkm"),
                                        data.getString("nama_pemilik"),
                                        data.getString("hp_pemilik"),
                                        data.getString("email_pemilik"),
                                        data.getString("jk_pemilik"),
                                        data.getString("longitude"),
                                        data.getString("latitude"),
                                        data.getString("foto"),
                                        data.getString("created_at"),
                                        data.getString("updated_at"),
                                        new LocationHandler(activity).hitungJarak(
                                                Double.parseDouble(data.getString("latitude")),
                                                Double.parseDouble(data.getString("longitude"))
                                        )
                                );
                                view.hasData(detailUmkmModel);
                            } else {
                                view.hasError(res.getString(R.string.gagalMuat));
                            }
                        } catch (JSONException e) {
                            view.hasError(e.getMessage());
                            e.printStackTrace();
                        }
                        view.setProcess(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.hasError(error.getMessage());
                view.setProcess(false);
            }
        });

        AppController.getInstance().addToRequestQueue(strReq, "json_obj_req");
    }

    @Override
    public void getLocation() {
        GPSTracker gps = new GPSTracker(activity);
        view.locationHandler(gps.getLongitude(), gps.getLatitude());
    }

    @Override
    public void gantiFoto() {
        view.showToast("On Going");
    }

    @Override
    public void editData(EditText namaUmkm, EditText hpUmkm, EditText emailUmkm,
                         EditText namaPemilik, EditText hpPemilik, EditText emailPemilik,
                         String jkPemilik, EditText lon, EditText lat, View v) {
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
            reqEditData(mNamaUmkm, mEmailUmkm, mTelponUmkm, mNamaPemilik, mEmailPemilik,
                    mTelponPemilik, jkPemilik, mLon, mLat);
        }
    }

    private void reqEditData(final String mNamaUmkm, final String mEmailUmkm, final String mTelponUmkm,
                             final String mNamaPemilik, final String mEmailPemilik, final String mTelponPemilik,
                             final String jkPemilik, final String mLon, final String mLat) {

        view.setProcess(true);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseApi.editData + uid,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("res", response);
                        try {
                            JSONObject jObj = new JSONObject(response);
                            int success = jObj.getInt("success");
                            if (success == 1) {

                                view.showToast("Berhasil Edit");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        activity.finish();
                                    }
                                }, 2000L);
                            } else {
                                view.showToast("Gagal Edit");
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
                params.put("jk_pemilik", jkPemilik);
                params.put("longitude", mLon);
                params.put("latitude", mLat);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, "json_obj_req");
    }
}
