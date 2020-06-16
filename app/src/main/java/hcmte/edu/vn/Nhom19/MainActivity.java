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

public class MainActivity extends AppCompatActivity {

    public static Database database;
    TextView txtChoosePlace;
    Button btnSearch;
    List<QuanAn> listQuanAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this, "Foody.sqlite", null, 1);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            listQuanAn = database.GetQuanAn(bundle.getInt(Constants.KEY_PLACE_ID));
        } else {
            listQuanAn = database.GetQuanAn();
        }

        RecyclerView myRecycler =(RecyclerView) findViewById(R.id.recyclerview_list_shop);
        QuanAnAdapter myAdapter = new QuanAnAdapter(this, listQuanAn);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        myRecycler.setAdapter(myAdapter);

        txtChoosePlace = (TextView) findViewById(R.id.txt_choose_place);
        txtChoosePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlaceActivity.class);
                startActivity(intent);
            }
        });
    }
}
