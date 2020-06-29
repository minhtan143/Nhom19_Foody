package hcmte.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private ExpandableListView lvMenu;
    private QuanAn quanAn;
    List<ThucDon> listThucDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        int m = getIntent().getIntExtra(Constants.KEY_SHOP_ID, 1);
        quanAn = MainActivity.database.GetQuanAnByID(m);

        initElement(quanAn);

        assert quanAn != null;
        listThucDon = MainActivity.database.GetThucDon(quanAn.getMaQuanAn());

        RecyclerView myRecycler = (RecyclerView) findViewById(R.id.recyclerview_list_image);
        ThucDonAdapter myAdapter = new ThucDonAdapter(this, listThucDon);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        myRecycler.setAdapter(myAdapter);

        findViewById(R.id.btn_back_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_switch_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_list_image);
                ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);

                Button btnImage = (Button) findViewById(R.id.btn_switch_image);
                btnImage.setBackgroundResource(R.drawable.bg_btn_menu_selected);
                Button btnMenu = (Button) findViewById(R.id.btn_switch_menu);
                btnMenu.setBackgroundResource(R.drawable.bg_btn_menu_unselected);

                recyclerView.setVisibility(View.VISIBLE);
                expandableListView.setVisibility(View.GONE);
            }
        });

        findViewById(R.id.btn_switch_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_list_image);
                ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);

                Button btnImage = (Button) findViewById(R.id.btn_switch_image);
                btnImage.setBackgroundResource(R.drawable.bg_btn_menu_unselected);
                Button btnMenu = (Button) findViewById(R.id.btn_switch_menu);
                btnMenu.setBackgroundResource(R.drawable.bg_btn_menu_selected);

                recyclerView.setVisibility(View.GONE);
                expandableListView.setVisibility(View.VISIBLE);
            }
        });
    }

    void initElement(QuanAn quanAn) {
        TextView txtNameShop = (TextView) findViewById(R.id.txt_menu_name_shop);
        txtNameShop.setText(quanAn.getTenQuanAn());
    }
}
