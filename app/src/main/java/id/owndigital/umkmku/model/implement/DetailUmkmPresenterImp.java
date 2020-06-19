package id.owndigital.umkmku.model.implement;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.AppController;
import id.owndigital.umkmku.core.datasource.BaseApi;
import id.owndigital.umkmku.core.tools.LocationHandler;
import id.owndigital.umkmku.model.DetailUmkmModel;
import id.owndigital.umkmku.page.EditUmkmActivity;
import id.owndigital.umkmku.presenter.DetailUmkmPresenter;
import id.owndigital.umkmku.view.DetailUmkmView;

public class DetailUmkmPresenterImp implements DetailUmkmPresenter {

    private DetailUmkmModel detailUmkmModel;
    private Activity activity;
    private DetailUmkmView view;
    private String uid;

    public DetailUmkmPresenterImp(Activity activity, DetailUmkmView view, String uid) {
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
                        try {
                            JSONObject data = new JSONObject(response);
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
    public void openMaps() {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + detailUmkmModel.getLatitude() + "," +
                        detailUmkmModel.getLongitude()));
        activity.startActivity(intent);
    }

    @Override
    public void openEmail(String alamat) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{alamat}); // recipients
        activity.startActivity(intent);
    }

    @Override
    public void openTelpon(String nomor) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + nomor));
        activity.startActivity(intent);
    }

    @Override
    public void editData() {
        Intent i = new Intent(activity, EditUmkmActivity.class);
        i.putExtra("uid", uid);
        activity.startActivity(i);
    }

    @Override
    public void hapusData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false);
        builder.setMessage("Anda Yakin Ingin Menghapus Data?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reqDelData();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void reqDelData() {
        view.setProcess(true);

        StringRequest strReq = new StringRequest(Request.Method.DELETE, BaseApi.hapusData + uid,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);
                            if (data.getInt("success") == 1) {
                                view.showToast("Berhasil Hapus Data!");
                            } else {
                                view.showToast("Gagal Hapus Data!");
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
                view.showToast(error.getMessage());
                view.setProcess(false);
            }
        });

        AppController.getInstance().addToRequestQueue(strReq, "json_obj_req");
    }
}
