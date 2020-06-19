package id.owndigital.umkmku.presenter;

import android.view.View;
import android.widget.EditText;

public interface EditUmkmPresenter {

    void getData();

    void getLocation();

    void gantiFoto();

    void editData(EditText namaUmkm, EditText hpUmkm, EditText emailUmkm, EditText namaPemilik,
                  EditText hpPemilik, EditText emailPemilik, String jkPemilik, EditText lon, EditText lat, View view);
}
