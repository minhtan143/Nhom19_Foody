package hcmte.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PlaceActivity extends AppCompatActivity {

    List<String> listPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        listPlace = new ArrayList<>();
        listPlace.add("TP. HCM");
        listPlace.add("Hà Nội");
        listPlace.add("Đà Nẵng");
        listPlace.add("Hải Phòng");
        listPlace.add("Cần Thơ");
        listPlace.add("Đồng Nai");
        listPlace.add("Bình Dương");
        listPlace.add("Vũng Tàu");
        listPlace.add("Đắk Lắk");
        listPlace.add("Gia Lai");
        listPlace.add("Lâm Đồng");

        RecyclerView myRecycler = (RecyclerView) findViewById(R.id.recyclerview_list_place);
        PlaceAdapter myAdapter = new PlaceAdapter(this, listPlace);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        myRecycler.setAdapter(myAdapter);
    }



}
