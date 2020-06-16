package hcmte.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

        findViewById(R.id.txt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txt_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
