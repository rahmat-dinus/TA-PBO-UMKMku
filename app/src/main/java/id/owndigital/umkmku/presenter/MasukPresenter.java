package id.owndigital.umkmku.presenter;

import android.view.View;
import android.widget.EditText;

public interface MasukPresenter {
    void masuk(EditText email, EditText password, View view);

    void daftar();
}
