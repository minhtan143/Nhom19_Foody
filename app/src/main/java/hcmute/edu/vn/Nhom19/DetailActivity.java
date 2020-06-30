package hcmute.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private QuanAn quanAn;
    List<ThucDon> listThucDon;
    TextView txtTimeInTimeOut, txtAddress, txtName, txtPlace, txtOpened, txtDetailDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DetailActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
        }

        int m = getIntent().getIntExtra(Constants.KEY_SHOP_ID, 1);
        quanAn = MainActivity.database.GetQuanAnByID(m);

        initElement(quanAn);

        assert quanAn != null;
        listThucDon = MainActivity.database.GetThucDon(quanAn.getMaQuanAn());

        RecyclerView myRecycler = (RecyclerView) findViewById(R.id.recyclerview_list_food);
        ThucDonAdapter myAdapter = new ThucDonAdapter(this, listThucDon);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        myRecycler.setAdapter(myAdapter);

        findViewById(R.id.btn_back_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_go_to_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MenuActivity.class);
                intent.putExtra(Constants.KEY_SHOP_ID, quanAn.getMaQuanAn());
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_add_wifi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddWifi();
            }
        });
    }

    private void showDialogAddWifi() {
        final Dialog dialog = new Dialog(this, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_add_wifi);
        dialog.setCanceledOnTouchOutside(true);

        final EditText edtName = dialog.findViewById(R.id.edt_wifi_name);
        final EditText edtPass = dialog.findViewById(R.id.edt_wifi_pass);

        dialog.findViewById(R.id.btn_add_wifi_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String pass = edtPass.getText().toString();

                if (!name.isEmpty() && !pass.isEmpty()) {
                    MainActivity.database.InsertWifi(new Wifi(0, name, pass, quanAn.getMaQuanAn()));
                    Toast.makeText(DetailActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(DetailActivity.this, "Không để trống thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    private void initElement(final QuanAn quanAn) {
        txtAddress = (TextView) findViewById(R.id.txt_detail_address);
        txtName = (TextView) findViewById(R.id.txt_detail_name_shop);
        txtPlace = (TextView) findViewById(R.id.txt_detail_place);
        txtTimeInTimeOut = (TextView) findViewById(R.id.txt_time_in_time_out);
        txtOpened = (TextView) findViewById(R.id.txt_opened);
        txtDetailDistance = (TextView) findViewById(R.id.txt_detail_distance);

        txtAddress.setText(quanAn.getDiaChi());
        txtName.setText(quanAn.getTenQuanAn());
        txtTimeInTimeOut.setText(quanAn.getGioMoCua() + " - " + quanAn.getGioDongCua());
        txtOpened.setText(status(quanAn.getGioMoCua(), quanAn.getGioDongCua()));

        txtDetailDistance.setText("...km (Từ vị trí hiện tại)");

        new Thread(new Runnable() {
            public void run() {
                txtDetailDistance.setText(distance(quanAn.getDiaChi()) + "km (Từ vị trí hiện tại)");
            }
        }).start();

        TinhThanh tinh = MainActivity.database.GetTinhThanhByID(quanAn.getMaTinh());
        txtPlace.setText(tinh.getTenTinh());
    }

    private String status(String startTime, String endTime) {
        Calendar c = Calendar.getInstance();
        Time currentTime = new Time(c.getTime().getHours(), c.getTime().getMinutes(), 0);
        Time _startTime = new Time(Integer.parseInt(startTime.split(":")[0]), Integer.parseInt(startTime.split(":")[1]), 0);
        Time _endTime = new Time(Integer.parseInt(endTime.split(":")[0]), Integer.parseInt(endTime.split(":")[1]), 0);

        if (_startTime.before(currentTime) && _endTime.after(currentTime))
            return "ĐANG MỞ CỬA";
        return "CHƯA MỞ CỬA";
    }

    public float distance(String address) {

        getCurrentLocation();
        Location location = getLocation(address);

        if (location != null) {
            float distance = Constants.myLocation.distanceTo(location);
            distance = Math.round(distance / 100) / 10f;
            return distance;
        }

        return 0;
    }

    private void getCurrentLocation() {
        Constants.myLocation.setLatitude(10.850736);
        Constants.myLocation.setLongitude(106.771900);

        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Constants.myLocation.setLatitude(location.getLatitude());
                    Constants.myLocation.setLongitude(location.getLongitude());
                }
            }
        });
    }

    private Location getLocation(String address) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        Location location = null;

        try {
            addresses = geocoder.getFromLocationName(address, 1);
        } catch (Exception exception) {
            return null;
        }
        if (addresses != null && addresses.size() > 0) {
            location = new Location("NA");
            location.setLatitude(addresses.get(0).getLatitude());
            location.setLongitude(addresses.get(0).getLongitude());
        }

        return location;
    }
}
