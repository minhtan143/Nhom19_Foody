package hcmte.edu.vn.Nhom19;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder>  {

    private Context context;
    private List<QuanAn> listQuanAn;

    public ResultAdapter(Context context, List<QuanAn> listQuanAn) {
        this.context = context;
        this.listQuanAn = listQuanAn;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.cardview_item_shop_result, parent, false);
        getCurrentLocation();
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtDistanceShop.setText(distance(listQuanAn.get(position).getDiaChi()) + "km");
        holder.txtAddressShop.setText(listQuanAn.get(position).getDiaChi());
        holder.txtNameShop.setText(listQuanAn.get(position).getTenQuanAn());
        holder.txtRateShop.setText(Float.toString(new Random().nextInt(101) / 10f));
        holder.txtCommentCount.setText(Integer.toString(new Random().nextInt(100)));
        holder.txtPictureCount.setText(Integer.toString(new Random().nextInt(100)));

        byte[] img = listQuanAn.get(position).getHinhAnh();
        holder.imgShop.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Constants.KEY_SHOP_ID, listQuanAn.get(position).getMaQuanAn());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listQuanAn.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtNameShop, txtRateShop, txtAddressShop, txtDistanceShop, txtCommentCount, txtPictureCount;
        ImageView imgShop;
        ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtNameShop = itemView.findViewById(R.id.txt_result_name_shop);
            txtAddressShop = itemView.findViewById(R.id.txt_result_address_shop);
            txtDistanceShop = itemView.findViewById(R.id.txt_result_distance_shop);
            txtRateShop = itemView.findViewById(R.id.txt_result_rate_shop);
            imgShop = itemView.findViewById(R.id.image_result_shop);
            txtCommentCount = itemView.findViewById(R.id.txt_result_comment);
            txtPictureCount = itemView.findViewById(R.id.txt_result_picture);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position,boolean isLongClick);
    }

    public float distance(String address) {

        Location location = getLocation(address);

        if (location != null) {
            float distance = Constants.myLocation.distanceTo(location);
            distance = Math.round(distance / 100) / 10f;
            return distance;
        }

        return 0;
    }

    private void getCurrentLocation() {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(context);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Constants.myLocation.setLatitude(location.getLatitude());
                    Constants.myLocation.setLongitude(location.getLongitude());
                }
            }
        });
    }

    private Location getLocation(String address) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        Location location = null;

        try {
            addresses = geocoder.getFromLocationName(address, 1);
        } catch (Exception exception) {
            return null;
        }
        if (addresses != null && addresses.size() > 0) {
            location = new Location("NA");
            location.setLatitude(addresses.get(0).getLatitude());
            location.setLongitude(addresses.get(0).getLongitude());
        }

        return location;
    }
}
