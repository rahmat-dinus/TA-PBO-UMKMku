package id.owndigital.umkmku.model.implement;

import android.app.Activity;
import android.util.Log;

import id.owndigital.umkmku.core.datasource.SPData;
import id.owndigital.umkmku.core.tools.LocationHandler;
import id.owndigital.umkmku.page.HomeActivity;
import id.owndigital.umkmku.page.RequestLocationActivity;
import id.owndigital.umkmku.page.auth.MasukActivity;
import id.owndigital.umkmku.presenter.SplashPresenter;
import id.owndigital.umkmku.view.SplashView;

public class SplashPresenterImp implements SplashPresenter {

    private SplashView view;
    private Activity activity;

    public SplashPresenterImp(SplashView view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }


    @Override
    public void loadData() {
        if (SPData.getInstance(activity).isLoggedIn()) {
            LocationHandler location = new LocationHandler(activity);
            if (location.isLocationGranted() && location.isGpsOn()) {
                view.nextActivity(HomeActivity.class);
            } else {
                view.nextActivity(RequestLocationActivity.class);
            }
        } else {
            view.nextActivity(MasukActivity.class);
        }

    }
}
