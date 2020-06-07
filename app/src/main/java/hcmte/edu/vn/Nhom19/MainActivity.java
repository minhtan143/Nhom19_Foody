package hcmte.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static Database database;
    TextView txtChoosePlace;
    Button btnSearch;
    List<QuanAn> listQuanAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this, "Foody.sqlite", null, 1);

        listQuanAn = database.GetQuanAn("ABC");

        RecyclerView myRecycler =(RecyclerView) findViewById(R.id.recyclerview_list_shop);
        RecyclerAdapter myAdapter = new RecyclerAdapter(this, listQuanAn);
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
