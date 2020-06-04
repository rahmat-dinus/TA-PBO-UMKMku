package id.owndigital.umkmku.model.implement;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.core.tools.LocationHandler;
import id.owndigital.umkmku.core.tools.PermissionsHandler;
import id.owndigital.umkmku.page.HomeActivity;
import id.owndigital.umkmku.presenter.RequestLocationPresenter;
import id.owndigital.umkmku.view.RequestLocationView;

public class RequestLocationImp implements RequestLocationPresenter {

    private Activity activity;
    private RequestLocationView view;
    private LocationHandler location;
    private PermissionsHandler permissions;
    private Helper helper;

    public RequestLocationImp(Activity activity, RequestLocationView view) {
        this.activity = activity;
        this.view = view;
        location = new LocationHandler(activity);
        permissions = new PermissionsHandler(activity);
        helper = new Helper(activity);
        view.setKeterangan(keterangan());
    }

    @Override
    public void turnOn() {
        if (!location.isLocationGranted()) {
            permissions.getPermissions();
        } else if (!location.isGpsOn()) {
            location.openGpsSetting();
        }
    }

    @Override
    public void openSetting() {
        if (!location.isLocationGranted()) {
            helper.openSettingsOfApp();
        }
        if (!location.isGpsOn()) {
            location.openGpsSetting();
        }
    }

    @Override
    public void keluar() {
        activity.finish();
    }

    @Override
    public void onResume() {
        if (location.isLocationGranted() && location.isGpsOn()) {
            helper.movePage(HomeActivity.class);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == this.permissions.reqGetLocation) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (location.isGpsOn()) {
                    helper.movePage(HomeActivity.class);
                } else {
                    view.setKeterangan(activity.getResources().getString(R.string.nyalaGps));
                }
            } else {
                view.showToast("Ijin Di Tolak!");
            }
        }
    }

    private String keterangan() {
        Resources res = activity.getResources();
        if (!location.isLocationGranted()) {
            return res.getString(R.string.reqLoc);
        } else if (!location.isGpsOn()) {
            return res.getString(R.string.nyalaGps);
        } else {
            return "";
        }
    }
}
