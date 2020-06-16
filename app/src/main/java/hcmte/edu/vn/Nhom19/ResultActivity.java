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
    List<QuanAn> listQuanAn;
    private ViewPager pager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        listQuanAn = MainActivity.database.GetQuanAn(1);

        RecyclerView myRecycler =(RecyclerView) findViewById(R.id.recycleview_list_shop_result);
        ResultAdapter myAdapter = new ResultAdapter(this, listQuanAn);
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
