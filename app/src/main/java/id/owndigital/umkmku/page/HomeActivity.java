package id.owndigital.umkmku.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import id.owndigital.umkmku.R;
import id.owndigital.umkmku.core.datasource.SPData;
import id.owndigital.umkmku.core.tools.Helper;
import id.owndigital.umkmku.core.tools.LocationHandler;

public class HomeActivity extends AppCompatActivity {

    private Helper helper;
    private LocationHandler location;
    private TextView nama, alamat, keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initState();

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
    }

    private void initState() {
        Activity activity = HomeActivity.this;
        helper = new Helper(activity);
        location = new LocationHandler(activity);
        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        keluar = findViewById(R.id.keluar);

        nama.setText(helper.greetingText());
        alamat.setText(location.getAddress());
        alamat.setSingleLine();
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_home, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.keluar:
                        SPData.getInstance(HomeActivity.this).logout();
                        finish();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}
