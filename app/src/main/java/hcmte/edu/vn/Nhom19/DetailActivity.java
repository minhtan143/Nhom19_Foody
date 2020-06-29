package hcmte.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private QuanAn quanAn;
    List<ThucDon> listThucDon;
    TextView txtTimeInTimeOut, txtAddress, txtName, txtPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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

    void showDialogAddWifi() {
        Dialog dialog = new Dialog(this, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_add_wifi);

        dialog.show();
    }

    void initElement(QuanAn quanAn) {
        txtAddress = (TextView) findViewById(R.id.txt_detail_address);
        txtName = (TextView) findViewById(R.id.txt_detail_name_shop);
        txtPlace = (TextView) findViewById(R.id.txt_detail_place);
        txtTimeInTimeOut = (TextView) findViewById(R.id.txt_time_in_time_out);

        txtAddress.setText(quanAn.getDiaChi());
        txtName.setText(quanAn.getTenQuanAn());
        txtTimeInTimeOut.setText(quanAn.getGioMoCua() + " - " + quanAn.getGioDongCua());

        TinhThanh tinh = MainActivity.database.GetTinhThanhByID(quanAn.getMaTinh());
        txtPlace.setText(tinh.getTenTinh());
    }
}
