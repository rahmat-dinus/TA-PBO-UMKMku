package id.owndigital.umkmku.view;

import android.view.View;
import android.widget.EditText;

public interface MasukView {
    void setError(EditText field, String error);

    void setProcess(boolean onProcess);

    void success(Class<?> activity);

    void showSnackbar(View view, String msg);
}
