package id.owndigital.umkmku.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Locale;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.model.DetailUmkmModel;
import id.owndigital.umkmku.model.implement.DetailUmkmPresenterImp;
import id.owndigital.umkmku.view.DetailUmkmView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class DetailUmkmActivity extends AppCompatActivity implements DetailUmkmView {

    DetailUmkmPresenterImp presenter;

    private RelativeLayout layoutData;
    private ProgressBar bar;
    private TextView namaUmkm;
    private TextView count;
    private TextView jarak;
    private TextView namaPemilik;
    private TextView error;
    private ImageView fotoUmkm;
    private DetailUmkmModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_umkm);

        presenter = new DetailUmkmPresenterImp(this, this, getIntent().getStringExtra("uid"));
        layoutData = findViewById(R.id.layoutData);
        bar = findViewById(R.id.pBar);
        namaUmkm = findViewById(R.id.namaUmkm);
        count = findViewById(R.id.count);
        jarak = findViewById(R.id.jarakUmkm);
        namaPemilik = findViewById(R.id.namaPemilik);
        TextView edit = findViewById(R.id.editUmkm);
        TextView hapus = findViewById(R.id.hapusUmkm);
        error = findViewById(R.id.error);
        fotoUmkm = findViewById(R.id.fotoUmkm);
        CardView maps = findViewById(R.id.openMaps);
        CardView emailUmkm = findViewById(R.id.emailUmkm);
        CardView hpUmkm = findViewById(R.id.hpUmkm);
        CardView hpPemilik = findViewById(R.id.hpPemilik);
        CardView emailPemilik = findViewById(R.id.emailPemilik);
        presenter.getData();

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openMaps();
            }
        });

        emailUmkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openEmail(data.getEmailUmkm());
            }
        });

        hpUmkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openTelpon(data.getHpUmkm());
            }
        });

        emailPemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openEmail(data.getEmailPemilik());
            }
        });

        hpPemilik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openTelpon(data.getHpPemilik());
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.editData();
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.hapusData();
            }
        });
    }

    @Override
    public void setProcess(boolean onProcess) {
        if (onProcess) {
            bar.setVisibility(View.VISIBLE);
            layoutData.setVisibility(View.GONE);
            error.setVisibility(View.GONE);
        } else {
            bar.setVisibility(View.GONE);
        }
    }

    @SuppressLint("SetTextI18n")
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
        count.setText(data.getCountPopuler());
        jarak.setText(String.format(Locale.getDefault(), "%.2f", data.getJarak()) + " KM");
        namaPemilik.setText(data.getNamaPemilik());
        layoutData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hasError(String error) {
        this.error.setVisibility(View.VISIBLE);
        this.error.setText(error);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1500);
    }
}