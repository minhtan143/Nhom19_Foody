package hcmute.edu.vn.Nhom19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Database database;
    private static int placeId = 15;
    TextView txtChoosePlace;
    EditText edt_search_shop;
    List<QuanAn> listQuanAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this, "Foody.sqlite", null, 1);

        txtChoosePlace = (TextView) findViewById(R.id.txt_choose_place);
        edt_search_shop = findViewById(R.id.edt_search_shop);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            placeId = bundle.getInt(Constants.KEY_PLACE_ID);
        }
        try {
            txtChoosePlace.setText(database.GetTinhThanhByID(placeId).getTenTinh());
            listQuanAn = database.GetQuanAn(placeId);
            show();
        }
        catch (Exception ignored) { }

        txtChoosePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlaceActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEvent();
            }
        });

        edt_search_shop.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                searchEvent();
            }
        });
    }

    private void show() {
        RecyclerView myRecycler =(RecyclerView) findViewById(R.id.recyclerview_list_shop);
        QuanAnAdapter myAdapter = new QuanAnAdapter(this, listQuanAn);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        myRecycler.setAdapter(myAdapter);
    }

    private void showResult() {
        RecyclerView myRecycler = findViewById(R.id.recyclerview_list_shop);
        ResultAdapter myAdapter = new ResultAdapter(this, listQuanAn);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        myRecycler.setAdapter(myAdapter);
    }

    private void searchEvent() {
        String searchString = edt_search_shop.getText().toString();

        try {
            if (!searchString.isEmpty()) {
                listQuanAn = database.GetQuanAn(placeId, searchString);
                showResult();
            } else {
                listQuanAn = database.GetQuanAn(placeId);
                show();
            }
        } catch (Exception ignored) { }
    }
}
