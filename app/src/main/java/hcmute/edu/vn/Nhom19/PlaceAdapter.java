package hcmute.edu.vn.Nhom19;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder> {

    private Context context;
    private List<TinhThanh> listTinhThanh;

    public PlaceAdapter (Context context, List<TinhThanh> listTinhThanh) {
        this.context = context;
        this.listTinhThanh = listTinhThanh;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater =LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.item_place, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.txtPlaceName.setText(listTinhThanh.get(position).getTenTinh());
        holder.txtPlaceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(Constants.KEY_PLACE_ID, listTinhThanh.get(position).getMaTinh());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTinhThanh.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtPlaceName;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtPlaceName = (TextView) itemView.findViewById(R.id.txt_name_place);
        }
    }
}
