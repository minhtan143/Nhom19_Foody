package hcmte.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private QuanAn quanAn;
    List<ThucDon> listThucDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int m = getIntent().getIntExtra(Constants.KEY_SHOP_ID, 1);
        quanAn = MainActivity.database.GetQuanAnByID(m);

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

    }
}
