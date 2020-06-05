package id.owndigital.umkmku.model.implement;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.AppController;
import id.owndigital.umkmku.core.datasource.BaseApi;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.core.tools.LocationHandler;
import id.owndigital.umkmku.model.UmkmModel;
import id.owndigital.umkmku.page.TambahUmkmActivity;
import id.owndigital.umkmku.presenter.HomePresenter;
import id.owndigital.umkmku.view.HomeView;

public class HomePresenterImp implements HomePresenter {

    private Activity activity;
    private HomeView view;
    private ArrayList<UmkmModel> listUmkm;
    private LocationHandler location;

    public HomePresenterImp(Activity activity, HomeView view) {
        this.activity = activity;
        this.view = view;
        this.listUmkm = new ArrayList<>();
        this.location = new LocationHandler(activity);
    }


    @Override
    public void onClick(View v, int position) {

    }

    @Override
    public void onLongClick(View v, int position) {

    }

    @Override
    public void onOptionClick(View v) {
        view.showOptions(v);
    }

    @Override
    public void getData() {
        final Resources res = activity.getResources();
        view.setProcess(true);

        StringRequest strReq = new StringRequest(Request.Method.GET, BaseApi.loadData, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    if (data.length() > 0) {
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject productObject = data.getJSONObject(i);
                            listUmkm.add(new UmkmModel(
                                    productObject.getString("uid"),
                                    productObject.getString("nama_umkm"),
                                    productObject.getString("hp_umkm"),
                                    productObject.getString("email_umkm"),
                                    productObject.getString("longitude"),
                                    productObject.getString("latitude"),
                                    productObject.getString("count_populer"),
                                    productObject.getString("foto"),
                                    productObject.getString("created_at"),
                                    location.hitungJarak(
                                            Double.parseDouble(productObject.getString("latitude")),
                                            Double.parseDouble(productObject.getString("longitude"))
                                    )
                            ));
                        }
                        view.hasData(listUmkm);
                    } else if (data.length() == 0) {
                        view.hasError(res.getString(R.string.tdkAdaData));
                    } else {
                        view.hasError(res.getString(R.string.gagalMuat));
                    }
                } catch (JSONException e) {
                    view.hasError(e.getMessage());
                    e.printStackTrace();
                }
                view.connectionDone();
                view.setProcess(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.hasError(error.getMessage());
                view.connectionDone();
                view.setProcess(false);
            }
        });

        AppController.getInstance().addToRequestQueue(strReq, "json_obj_req");
    }

    @Override
    public void tambahData() {
        new Helper(activity).newPage(TambahUmkmActivity.class);
    }
}
