package hcmte.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    List<ThucDon> listThucDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        listThucDon = MainActivity.database.GetThucDon(1);

        RecyclerView myRecycler = (RecyclerView) findViewById(R.id.recyclerview_list_food);
        DetailAdapter myAdapter = new DetailAdapter(this, listThucDon);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        myRecycler.setAdapter(myAdapter);

    }
}
