package hcmte.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtChoosePlace;
    Button btnSearch;
    List<Shop> listShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listShop = new ArrayList<>();
        listShop.add(new Shop("Bún thịt nướng Kiều Bào", "Một cái gì đó dài hơn cái tên của nó", R.drawable.bun_thit_nuong_kieu_bao));
        listShop.add(new Shop("Food Cô Nàng", "Một cái gì đó dài hơn cái tên của nó", R.drawable.food_co_nang));
        listShop.add(new Shop("Kazama Restaurant", "Một cái gì đó dài hơn cái tên của nó", R.drawable.kazama_restaurant));
        listShop.add(new Shop("Miu Tea", "Một cái gì đó dài hơn cái tên của nó", R.drawable.miutea));
        listShop.add(new Shop("Osaka Sushi", "Một cái gì đó dài hơn cái tên của nó", R.drawable.osaka_suisi));
        listShop.add(new Shop("Phú Vang", "Một cái gì đó dài hơn cái tên của nó", R.drawable.phuvang));
        listShop.add(new Shop("Quê Nhà quán", "Một cái gì đó dài hơn cái tên của nó", R.drawable.que_nha_quan));
        listShop.add(new Shop("The Coffee", "Một cái gì đó dài hơn cái tên của nó", R.drawable.the_coffee));
        listShop.add(new Shop("Ty Thy", "Một cái gì đó dài hơn cái tên của nó", R.drawable.ty_thy));
        listShop.add(new Shop("Viva Star Coffee", "Một cái gì đó dài hơn cái tên của nó", R.drawable.viva_star_coffee));


        RecyclerView myRecycler =(RecyclerView) findViewById(R.id.recyclerview_list_shop);
        RecyclerAdapter myAdapter = new RecyclerAdapter(this, listShop);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        myRecycler.setAdapter(myAdapter);

        txtChoosePlace = (TextView) findViewById(R.id.txt_choose_place);
        txtChoosePlace.setOnClickListener(this);

        btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()){
                case R.id.txt_choose_place:
                {
                    Intent intent = new Intent(MainActivity.this, PlaceActivity.class);
                    startActivity(intent);
                    break;
                }
                case R.id.btn_search:
                {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        }
        catch (Exception e) {

        }
    }
}
