package id.owndigital.umkmku.view;

import id.owndigital.umkmku.model.DetailUmkmModel;

public interface DetailUmkmView {

    void setProcess(boolean onProcess);

    void hasData(DetailUmkmModel detailUmkmModel);

    void hasError(String error);

    void showToast(String msg);
}
