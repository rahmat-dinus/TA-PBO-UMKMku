package id.owndigital.umkmku.view;

import android.view.View;
import android.widget.EditText;

public interface DaftarView {
    void setError(EditText field, String error);

    void setProcess(boolean onProcess);

    void showSnackbar(View view, String msg);
}
