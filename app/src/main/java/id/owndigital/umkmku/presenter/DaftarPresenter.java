package id.owndigital.umkmku.presenter;

import android.view.View;
import android.widget.EditText;

public interface DaftarPresenter {
    void daftar(EditText nama, EditText email, EditText telpon, EditText password,
                EditText cPassword, View view);

    void masuk();
}
