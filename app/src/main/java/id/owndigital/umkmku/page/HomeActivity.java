package id.owndigital.umkmku.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import id.owndigital.umkmku.core.datasource.SPData;
import id.owndigital.umkmku.core.mInterface.RecyclerTouchListener;
import id.owndigital.umkmku.core.models.UmkmModel;
import id.owndigital.umkmku.core.sorter.KategoriUmkm;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.core.tools.LocationHandler;
import id.owndigital.umkmku.core.viewHolders.HomeUmkmListAdapter;

public class HomeActivity extends AppCompatActivity {

    private Helper helper;
    private LocationHandler location;
    private TextView nama, alamat, tvHolder, keluar;

    private ArrayList<UmkmModel> listUmkm;
    private LinearLayout lData;
    private TextView sTerdekat, sPopuler, sTerbaru;
    private RecyclerView.Adapter aTerdekat, aPopuler, aTerbaru;
    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initState();

        nama.setText(helper.greetingText());
        alamat.setText(location.getAddress());
        alamat.setSelected(true);
        tvHolder.setVisibility(View.GONE);

        listUmkm = new ArrayList<>();

        RecyclerView rTerdekat = findViewById(R.id.rTerdekat);
        rTerdekat.setHasFixedSize(true);
        LinearLayoutManager lTerdekat = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        aTerdekat = new HomeUmkmListAdapter(listUmkm, this, KategoriUmkm.TERDEKAT_ASC);
        rTerdekat.setAdapter(aTerdekat);
        rTerdekat.setLayoutManager(lTerdekat);

        rTerdekat.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rTerdekat, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        sTerdekat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        RecyclerView rPopuler = findViewById(R.id.rPopuler);
        rPopuler.setHasFixedSize(true);
        LinearLayoutManager lPopuler = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        aPopuler = new HomeUmkmListAdapter(listUmkm, this, KategoriUmkm.POPULER_ASC);
        rPopuler.setAdapter(aPopuler);
        rPopuler.setLayoutManager(lPopuler);

        rPopuler.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rPopuler, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        sPopuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        RecyclerView rTerbaru = findViewById(R.id.rTerbaru);
        rTerbaru.setHasFixedSize(true);
        LinearLayoutManager lTerbaru = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        aTerbaru = new HomeUmkmListAdapter(listUmkm, this, KategoriUmkm.TERBARU_ASC);
        rTerbaru.setAdapter(aTerbaru);
        rTerbaru.setLayoutManager(lTerbaru);

        rTerbaru.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rTerbaru, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        sTerbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }

    private void initState() {
        Activity activity = HomeActivity.this;
        helper = new Helper(activity);
        location = new LocationHandler(activity);
        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        tvHolder = findViewById(R.id.tvHolder);
        keluar = findViewById(R.id.keluar);
        lData = findViewById(R.id.layoutData);
        sTerdekat = findViewById(R.id.sTerdekat);
        sPopuler = findViewById(R.id.sPopuler);
        sTerbaru = findViewById(R.id.sTerbaru);
        pBar = findViewById(R.id.pBar);
        getUmkm();
    }

    private void getUmkm() {
        pBar.setVisibility(View.VISIBLE);
        lData.setVisibility(View.GONE);
        tvHolder.setVisibility(View.GONE);

        StringRequest strReq = new StringRequest(Request.Method.GET, BaseApi.loadData, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray data = new JSONArray(response);
                    if (data.length() > 0) {
                        lData.setVisibility(View.VISIBLE);
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
                    } else if (data.length() == 0) {
                        tvHolder.setVisibility(View.VISIBLE);
                        tvHolder.setText(R.string.tdkAdaData);
                    } else {
                        tvHolder.setVisibility(View.VISIBLE);
                        tvHolder.setText(R.string.gagalMuat);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                aTerdekat.notifyDataSetChanged();
                aPopuler.notifyDataSetChanged();
                aTerbaru.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvHolder.setVisibility(View.VISIBLE);
                tvHolder.setText(error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        pBar.setVisibility(View.GONE);
        AppController.getInstance().addToRequestQueue(strReq, "json_obj_req");
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_home, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.keluar:
                        SPData.getInstance(HomeActivity.this).logout();
                        finish();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}