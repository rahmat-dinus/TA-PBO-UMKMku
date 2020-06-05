package id.owndigital.umkmku.view;

import android.view.View;

import java.util.ArrayList;

import id.owndigital.umkmku.model.UmkmModel;

public interface HomeView {

    void setProcess(boolean onProcess);

    void showOptions(View v);

    void hasData(ArrayList<UmkmModel> umkm);

    void hasError(String error);

    void connectionDone();
}
