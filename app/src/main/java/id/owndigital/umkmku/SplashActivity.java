package id.owndigital.umkmku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.model.implement.SplashPresenterImp;
import id.owndigital.umkmku.presenter.SplashPresenter;
import id.owndigital.umkmku.view.SplashView;

public class SplashActivity extends AppCompatActivity implements SplashView {

    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenterImp(this, this);
        presenter.loadData();
    }


    @Override
    public void nextActivity(final Class<?> activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Helper(SplashActivity.this).movePage(activity);
            }
        }, 2000);
    }
}
