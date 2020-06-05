package hcmte.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    List<Food> listFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        listFood = new ArrayList<>();
        listFood.add(new Food("Bún thịt nướng", R.drawable.bun_thit_nuong_kieu_bao));
        listFood.add(new Food("Trà sữa", R.drawable.miutea));
        listFood.add(new Food("Hủ tiếu", R.drawable.phuvang));
        listFood.add(new Food("Coffee", R.drawable.viva_star_coffee));
        listFood.add(new Food("Bánh khoọt", R.drawable.que_nha_quan));

        RecyclerView myRecycler = (RecyclerView) findViewById(R.id.recyclerview_list_food);
        DetailAdapter myAdapter = new DetailAdapter(this, listFood);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        myRecycler.setAdapter(myAdapter);

    }
}
