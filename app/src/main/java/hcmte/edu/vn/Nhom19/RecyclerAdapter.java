package hcmte.edu.vn.Nhom19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Shop> listShop;

    public RecyclerAdapter (Context context, List<Shop> listShop) {
        this.context = context;
        this.listShop = listShop;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater =LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.cardview_item_shop, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtShopName.setText(listShop.get(position).getName());
        holder.txtShopDetail.setText(listShop.get(position).getDetail());
        holder.imageShop.setImageResource(listShop.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return listShop.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtShopName, txtShopDetail;
        ImageView imageShop;
        CardView cardShop;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtShopName = (TextView) itemView.findViewById(R.id.txt_name_shop);
            txtShopDetail = (TextView) itemView.findViewById(R.id.txt_detail_shop);
            imageShop = (ImageView) itemView.findViewById(R.id.image_shop);
            cardShop = (CardView) itemView.findViewById(R.id.cardview_shop);

        }
    }
}
