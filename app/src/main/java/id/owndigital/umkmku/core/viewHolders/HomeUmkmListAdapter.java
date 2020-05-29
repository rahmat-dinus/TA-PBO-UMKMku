package id.owndigital.umkmku.core.viewHolders;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;
import java.util.Locale;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.models.UmkmModel;
import id.owndigital.umkmku.core.tools.LocationHandler;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class HomeUmkmListAdapter extends RecyclerView.Adapter<HomeUmkmListAdapter.UmkmViewHolder> {

    private final Context context;
    private final List<UmkmModel> umkmModels;
    private LocationHandler location;

    public HomeUmkmListAdapter(List<UmkmModel> umkmModels, Activity activity) {
        this.umkmModels = umkmModels;
        this.context = activity.getBaseContext();
        this.location = new LocationHandler(activity);
    }


    static class UmkmViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoUmkm;
        private TextView namaUmkm, jarakUmkm;

        //Initializing Views
        UmkmViewHolder(View itemView) {

            super(itemView);
            fotoUmkm = itemView.findViewById(R.id.fotoUmkm);
            namaUmkm = itemView.findViewById(R.id.namaUmkm);
            jarakUmkm = itemView.findViewById(R.id.jarakUmkm);

        }
    }

    @NonNull
    @Override
    public UmkmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_umkm_horizontal, parent, false);
        return new UmkmViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UmkmViewHolder holder, int postition) {
        UmkmModel umkm = umkmModels.get(postition);

        Glide.with(context).load(umkm.getFoto())
                .thumbnail(0.5f)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoUmkm);
        holder.namaUmkm.setText(umkm.getNamaUmkm());
        holder.jarakUmkm.setText(String.format(Locale.getDefault(), "%.2f", location.hitungJarak(Double.parseDouble(umkm.getLatitude()), Double.parseDouble(umkm.getLongitude())))+ " KM");
    }

    @Override
    public int getItemCount() {
        return umkmModels.size();
    }
}
