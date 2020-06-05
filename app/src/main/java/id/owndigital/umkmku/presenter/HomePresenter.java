package id.owndigital.umkmku.presenter;

import android.view.View;


public interface HomePresenter {

    void onClick(View v, int position);

    void onLongClick(View v, int position);

    void onOptionClick(View v);

    void getData();

    void tambahData();

}