package id.owndigital.umkmku.core.viewHolders;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Locale;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.model.UmkmModel;
import id.owndigital.umkmku.model.sorter.KategoriUmkm;
import id.owndigital.umkmku.model.sorter.UmkmSorter;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.page.DetailUmkmActivity;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class HomeUmkmListVerticalAdapter extends RecyclerView.Adapter<HomeUmkmListVerticalAdapter.UmkmViewHolder> {

    private final Context context;
    private ArrayList<UmkmModel> umkmModels;
    private Helper helper;
    private KategoriUmkm kategoriUmkm;

    public HomeUmkmListVerticalAdapter(ArrayList<UmkmModel> umkmModels, Activity activity, KategoriUmkm kategoriUmkm) {
        this.umkmModels = umkmModels;
        this.context = activity.getBaseContext();
        this.helper = new Helper(activity);
        this.kategoriUmkm = kategoriUmkm;
    }

    static class UmkmViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoUmkm;
        private TextView namaUmkm, jarakUmkm;
        private CardView itemCard;

        //Initializing Views
        UmkmViewHolder(View itemView) {

            super(itemView);
            fotoUmkm = itemView.findViewById(R.id.fotoUmkm);
            namaUmkm = itemView.findViewById(R.id.namaUmkm);
            jarakUmkm = itemView.findViewById(R.id.jarakUmkm);
            itemCard = itemView.findViewById(R.id.itemCard);

        }
    }


    @NonNull
    @Override
    public UmkmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (this.kategoriUmkm) {
            case DEFAULT:
                break;
            case TERDEKAT_ASC:
                new UmkmSorter(umkmModels).getSortedByLokasiAsc();
                break;
            case TERDEKAT_DESC:
                new UmkmSorter(umkmModels).getSortedByLokasiDesc();
                break;
            case POPULER_ASC:
                new UmkmSorter(umkmModels).getSortedByPopulerAsc();
                break;
            case POPULER_DESC:
                new UmkmSorter(umkmModels).getSortedByPopulerDesc();
                break;
            case TERBARU_ASC:
                new UmkmSorter(umkmModels).getSortedByTerbaruAsc();
                break;
            case TERBARU_DESC:
                new UmkmSorter(umkmModels).getSortedByTerbaruDesc();
                break;
        }
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_umkm_vertical, parent, false);
        return new UmkmViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UmkmViewHolder holder, int postition) {
        final UmkmModel umkm = umkmModels.get(postition);

        Glide.with(context).load(umkm.getFoto())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoUmkm);
        holder.namaUmkm.setText(umkm.getNamaUmkm());
        holder.jarakUmkm.setText(String.format(Locale.getDefault(), "%.2f", umkm.getJarak()) + " KM");
        if (postition != 0) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, helper.dpToPx(16), 0, 0);
            holder.itemCard.setLayoutParams(params);
        }


        holder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailUmkmActivity.class);
                i.putExtra("uid", umkm.getUid());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return umkmModels.size();
    }
}
