package hcmte.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtChoosePlace;
    List<Shop> listShop;
    private ViewPager pager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        listShop = new ArrayList<>();
        listShop.add(new Shop("Bún thịt nướng Kiều Bào", R.drawable.bun_thit_nuong_kieu_bao, 4.5, "484 Lê Văn Việt, Quận 9, TP. HCM", 4.5));
        listShop.add(new Shop("Food Cô Nàng",R.drawable.food_co_nang, 6.5, "484 Lê Văn Việt, Quận 9, TP. HCM", 5));
        listShop.add(new Shop("Kazama Restaurant",R.drawable.kazama_restaurant, 5.0, "484 Lê Văn Việt, Quận 9, TP. HCM", 2));
        listShop.add(new Shop("Miu Tea",R.drawable.miutea, 4.7, "484 Lê Văn Việt, Quận 9, TP. HCM", 1));
        listShop.add(new Shop("Osaka Sushi",R.drawable.osaka_suisi, 5.5, "484 Lê Văn Việt, Quận 9, TP. HCM", 12.3));
        listShop.add(new Shop("Phú Vang", R.drawable.phuvang, 6.0, "484 Lê Văn Việt, Quận 9, TP. HCM", 3.3));
        listShop.add(new Shop("Quê Nhà quán",R.drawable.que_nha_quan,5.5, "484 Lê Văn Việt, Quận 9, TP. HCM", 10));
        listShop.add(new Shop("The Coffee",R.drawable.the_coffee, 3.3, "484 Lê Văn Việt, Quận 9, TP. HCM", 11));
        listShop.add(new Shop("Ty Thy", R.drawable.ty_thy, 5.0, "484 Lê Văn Việt, Quận 9, TP. HCM", 7));
        listShop.add(new Shop("Viva Star Coffee", R.drawable.viva_star_coffee, 6.5, "484 Lê Văn Việt, Quận 9, TP. HCM", 5));

        RecyclerView myRecycler =(RecyclerView) findViewById(R.id.recycleview_list_shop_result);
        ResultAdapter myAdapter = new ResultAdapter(this, listShop);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        myRecycler.setAdapter(myAdapter);

        txtChoosePlace = (TextView) findViewById(R.id.txt_choose_place);
        txtChoosePlace.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()){
                case R.id.txt_choose_place:
                {
                    Intent intent = new Intent(ResultActivity.this, PlaceActivity.class);
                    startActivity(intent);
                }
            }
        }
        catch (Exception e) {

        }
    }
}
