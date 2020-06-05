package hcmte.edu.vn.Nhom19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

public class ExpandableFoodGroupAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<FoodGroup> listFoodGroup;

    public ExpandableFoodGroupAdapter(Context context, List<FoodGroup> listFoodGroup) {
        this.context = context;
        this.listFoodGroup = listFoodGroup;
    }

    @Override
    public int getGroupCount() {
        return listFoodGroup.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listFoodGroup.get(i).getListFood().size();
    }

    @Override
    public Object getGroup(int i) {
        return listFoodGroup.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listFoodGroup.get(i).getListFood().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String foodGroupName = ((FoodGroup) getGroup(i)).getName();

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.item_food_group, null);
        }

        TextView tvName = (TextView) view.findViewById(R.id.txt_name_food_group);
        tvName.setText(foodGroupName);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String foodName = ((Food) getChild(i, i1)).getName();

        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.item_food, null);
        }

        TextView tvName = (TextView) view.findViewById(R.id.txt_item_food);
        tvName.setText(foodName);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
