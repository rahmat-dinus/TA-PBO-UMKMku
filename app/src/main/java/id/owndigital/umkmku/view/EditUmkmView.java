package id.owndigital.umkmku.view;

import android.widget.EditText;

import id.owndigital.umkmku.model.DetailUmkmModel;

public interface EditUmkmView {
    void setError(EditText field, String error);

    void setProcess(boolean onProcess);

    void hasData(DetailUmkmModel detailUmkmModel);

    void hasError(String error);

    void showToast(String msg);

    void locationHandler(double lon, double lat);
}
