package id.owndigital.umkmku.presenter;

import android.view.View;
import android.widget.EditText;

public interface TambahUmkmPresenter {
    void getLocation();

    void tambahData(EditText namaUmkm, EditText hpUmkm, EditText emailUmkm, EditText namaPemilik,
                  EditText hpPemilik, EditText emailPemilik, EditText lon, EditText lat, View view);
}
