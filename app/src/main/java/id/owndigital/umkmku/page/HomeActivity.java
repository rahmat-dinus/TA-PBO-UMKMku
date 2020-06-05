package id.owndigital.umkmku.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.datasource.SPData;
import id.owndigital.umkmku.core.mInterface.RecyclerTouchListener;
import id.owndigital.umkmku.model.UmkmModel;
import id.owndigital.umkmku.model.implement.HomePresenterImp;
import id.owndigital.umkmku.model.sorter.KategoriUmkm;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.core.tools.LocationHandler;
import id.owndigital.umkmku.core.viewHolders.HomeUmkmListHorizontalAdapter;
import id.owndigital.umkmku.core.viewHolders.HomeUmkmListVerticalAdapter;
import id.owndigital.umkmku.presenter.HomePresenter;
import id.owndigital.umkmku.view.HomeView;

public class HomeActivity extends AppCompatActivity implements HomeView {

    HomePresenter presenter;

    private TextView tvHolder;

    private ArrayList<UmkmModel> listUmkm;
    private LinearLayout lData;
    private RecyclerView.Adapter<?> aTerdekat, aPopuler, aTerbaru, aLainnya;
    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView nama = findViewById(R.id.nama);
        TextView alamat = findViewById(R.id.alamat);
        tvHolder = findViewById(R.id.tvHolder);
        TextView keluar = findViewById(R.id.keluar);
        lData = findViewById(R.id.layoutData);
        pBar = findViewById(R.id.pBar);
        FloatingActionButton tambahData = findViewById(R.id.tambahData);
        listUmkm = new ArrayList<>();
        presenter = new HomePresenterImp(this, this);

        nama.setText(new Helper(this).greetingText());
        alamat.setText(new LocationHandler(this).getAddress());
        alamat.setSelected(true);
        tvHolder.setVisibility(View.GONE);
        presenter.getData();

        RecyclerView rTerdekat = findViewById(R.id.rTerdekat);
        rTerdekat.setHasFixedSize(true);
        LinearLayoutManager lTerdekat = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        aTerdekat = new HomeUmkmListHorizontalAdapter(listUmkm, this, KategoriUmkm.TERDEKAT_ASC);
        rTerdekat.setAdapter(aTerdekat);
        rTerdekat.setLayoutManager(lTerdekat);

        rTerdekat.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rTerdekat, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.onClick(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {
                presenter.onLongClick(view, position);
            }
        }));

        RecyclerView rPopuler = findViewById(R.id.rPopuler);
        rPopuler.setHasFixedSize(true);
        LinearLayoutManager lPopuler = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        aPopuler = new HomeUmkmListHorizontalAdapter(listUmkm, this, KategoriUmkm.POPULER_ASC);
        rPopuler.setAdapter(aPopuler);
        rPopuler.setLayoutManager(lPopuler);

        rPopuler.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rPopuler, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.onClick(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {
                presenter.onLongClick(view, position);
            }
        }));

        RecyclerView rTerbaru = findViewById(R.id.rTerbaru);
        rTerbaru.setHasFixedSize(true);
        LinearLayoutManager lTerbaru = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        aTerbaru = new HomeUmkmListHorizontalAdapter(listUmkm, this, KategoriUmkm.TERBARU_ASC);
        rTerbaru.setAdapter(aTerbaru);
        rTerbaru.setLayoutManager(lTerbaru);

        rTerbaru.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rTerbaru, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.onClick(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {
                presenter.onLongClick(view, position);
            }
        }));

        RecyclerView rLainnya = findViewById(R.id.rLainnya);
        rLainnya.setHasFixedSize(true);
        LinearLayoutManager lLainnya = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        aLainnya = new HomeUmkmListVerticalAdapter(listUmkm, this, KategoriUmkm.DEFAULT);
        rLainnya.setAdapter(aLainnya);
        rLainnya.setLayoutManager(lLainnya);

        rLainnya.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rLainnya, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.onClick(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {
                presenter.onLongClick(view, position);
            }
        }));

        tambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.tambahData();
            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onOptionClick(v);
            }
        });
    }

    @Override
    public void setProcess(boolean onProcess) {
        if (onProcess) {
            pBar.setVisibility(View.VISIBLE);
            lData.setVisibility(View.GONE);
            tvHolder.setVisibility(View.GONE);
        } else {
            pBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showOptions(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_home, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.keluar) {
                    SPData.getInstance(HomeActivity.this).logout();
                    finish();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void hasData(ArrayList<UmkmModel> umkm) {
        lData.setVisibility(View.VISIBLE);
        listUmkm.addAll(umkm);
    }

    @Override
    public void hasError(String error) {
        tvHolder.setVisibility(View.VISIBLE);
        tvHolder.setText(error);
    }

    @Override
    public void connectionDone() {
        aTerdekat.notifyDataSetChanged();
        aPopuler.notifyDataSetChanged();
        aTerbaru.notifyDataSetChanged();
        aLainnya.notifyDataSetChanged();
    }
}