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

import java.util.List;

public class PlaceActivity extends AppCompatActivity {

    List<TinhThanh> listTinhThanh;
    EditText edt_search_place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        try {
            listTinhThanh = MainActivity.database.GetTinhThanh();
            show();
        } catch (Exception ignored) { }

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

        edt_search_place = findViewById(R.id.edt_search_place);
        edt_search_place.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String searchString = edt_search_place.getText().toString();

                try {
                    if (!searchString.isEmpty()) {
                        listTinhThanh = MainActivity.database.GetTinhThanh(searchString);
                    } else {
                        listTinhThanh = MainActivity.database.GetTinhThanh();
                    }
                    show();
                } catch (Exception ignored) { }
            }
        });
    }

    private void show() {
        RecyclerView myRecycler = (RecyclerView) findViewById(R.id.recyclerview_list_place);
        PlaceAdapter myAdapter = new PlaceAdapter(this, listTinhThanh);
        myRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        myRecycler.setAdapter(myAdapter);
    }
}
