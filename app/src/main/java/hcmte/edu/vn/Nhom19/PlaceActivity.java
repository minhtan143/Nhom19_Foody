package hcmte.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class PlaceActivity extends AppCompatActivity {

    List<TinhThanh> listTinhThanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        listTinhThanh = MainActivity.database.GetTinhThanh();

        RecyclerView myRecycler = (RecyclerView) findViewById(R.id.recyclerview_list_place);
        PlaceAdapter myAdapter = new PlaceAdapter(this, listTinhThanh);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        myRecycler.setAdapter(myAdapter);
    }



}
