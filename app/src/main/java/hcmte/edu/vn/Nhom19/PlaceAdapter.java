package hcmte.edu.vn.Nhom19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder> {

    private Context context;
    private List<String> listPlace;

    public PlaceAdapter (Context context, List<String> listPlace) {
        this.context = context;
        this.listPlace = listPlace;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater =LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.item_place, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtPlaceName.setText(listPlace.get(position));
    }

    @Override
    public int getItemCount() {
        return listPlace.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtPlaceName;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtPlaceName = (TextView) itemView.findViewById(R.id.txt_name_place);
        }
    }
}
