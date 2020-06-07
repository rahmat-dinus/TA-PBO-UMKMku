package id.owndigital.umkmku.page;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Locale;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.model.DetailUmkmModel;
import id.owndigital.umkmku.model.implement.EditUmkmPresenterImp;
import id.owndigital.umkmku.presenter.EditUmkmPresenter;
import id.owndigital.umkmku.view.EditUmkmView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class EditUmkmActivity extends AppCompatActivity implements EditUmkmView {

    EditUmkmPresenter presenter;

    private ImageView fotoUmkm;
    private EditText namaUmkm, hpUmkm, emailumkm, namaPemilik, hpPemilik, emailPemilik, lon, lat;
    private Button edit;
    private ProgressBar pBar;
    private DetailUmkmModel data;

    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_umkm);

        presenter = new EditUmkmPresenterImp(this, this, getIntent().getStringExtra("uid"));
        fotoUmkm = findViewById(R.id.fotoUmkm);
        namaUmkm = findViewById(R.id.namaUmkm);
        hpUmkm = findViewById(R.id.hpUmkm);
        emailumkm = findViewById(R.id.emailUmkm);
        namaPemilik = findViewById(R.id.namaPemilik);
        hpPemilik = findViewById(R.id.hpPemilik);
        emailPemilik = findViewById(R.id.emailPemilik);
        lon = findViewById(R.id.longitude);
        lat = findViewById(R.id.latitude);
        Button gpsSkrg = findViewById(R.id.lokSkrg);
        edit = findViewById(R.id.editData);
        pBar = findViewById(R.id.pBar);
        presenter.getData();

        gpsSkrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getLocation();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.editData(namaUmkm, hpUmkm, emailumkm, namaPemilik, hpPemilik, emailPemilik, lon, lat, v);
            }
        });

        collapsingToolbarLayout = findViewById(R.id.coll);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                    //  on Collapse
                    collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_VERTICAL);
                    collapsingToolbarLayout.setTitle(data.getNamaUmkm());
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));
                    collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(android.R.color.transparent));
                    collapsingToolbarLayout.setContentScrim(new ColorDrawable(getResources().getColor(R.color.hitamtrans)));
                } else {
                    collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_VERTICAL);
                    collapsingToolbarLayout.setTitle("\t");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.ganti) {
            presenter.gantiFoto();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setError(EditText field, String error) {
        field.requestFocus();
        field.setError(error);
    }

    @Override
    public void setProcess(boolean onProcess) {
        if (onProcess) {
            pBar.setVisibility(View.VISIBLE);
            edit.setVisibility(View.GONE);
        } else {
            pBar.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hasData(DetailUmkmModel detailUmkmModel) {
        data = detailUmkmModel;
        Glide.with(this).load(data.getFoto())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(fotoUmkm);
        namaUmkm.setText(data.getNamaUmkm());
        hpUmkm.setText(data.getHpUmkm());
        emailumkm.setText(data.getEmailUmkm());
        namaPemilik.setText(data.getNamaPemilik());
        hpPemilik.setText(data.getHpPemilik());
        emailPemilik.setText(data.getEmailPemilik());
        lon.setText(data.getLongitude());
        lat.setText(data.getLatitude());
    }

    @Override
    public void hasError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void locationHandler(double lon, double lat) {
        this.lon.setText(String.valueOf(lon));
        this.lat.setText(String.valueOf(lat));
    }
}