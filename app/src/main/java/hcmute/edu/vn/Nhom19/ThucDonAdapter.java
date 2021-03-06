package hcmute.edu.vn.Nhom19;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThucDonAdapter extends RecyclerView.Adapter<ThucDonAdapter.MyViewHolder> {

    private Context context;
    private List<ThucDon> listFood;

    public ThucDonAdapter(Context context, List<ThucDon> listFood) {
        this.context = context;
        this.listFood = listFood;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.cardview_food, parent, false);
        return new ThucDonAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtName.setText(listFood.get(position).getTenMonAn());
        byte[] img = listFood.get(position).getHinhAnh();
        holder.imgFood.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName;
        ImageView imgFood;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_name_food);
            imgFood = (ImageView) itemView.findViewById(R.id.image_food);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }


        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position,boolean isLongClick);
    }
}
