package id.owndigital.umkmku.presenter;

import androidx.annotation.NonNull;

public interface RequestLocationPresenter {
    void turnOn();

    void openSetting();

    void keluar();

    void onResume();

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                    @NonNull int[] grantResults);
}
