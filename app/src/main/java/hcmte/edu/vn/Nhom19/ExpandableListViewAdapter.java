package hcmte.edu.vn.Nhom19;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private ArrayList<String> _listDataHeader;
    private HashMap<String, ArrayList<ThucDon>> _listDataChild;

    public ExpandableListViewAdapter(Context context, ArrayList<String> listDataHeader, HashMap<String, ArrayList<ThucDon>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return _listDataHeader.get(groupPosition);
    }

    @Override
    public ThucDon getChild(int groupPosition, int childPosititon) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosititon) {
        return childPosititon;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        String data = (String) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater li = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert li != null;
            view = li.inflate(R.layout.item_header, null);
        }

        TextView tvHeader = (TextView) view.findViewById(R.id.item_header);
        tvHeader.setTypeface(null, Typeface.BOLD);
        tvHeader.setText(data);

        return view;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        final ThucDon data = (ThucDon) getChild(groupPosition, childPosition);

        if (view == null) {
            LayoutInflater li = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert li != null;
            view = li.inflate(R.layout.item_menu, null);
        }

        TextView itemName = (TextView) view.findViewById(R.id.item_menu_name);
        TextView itemPrice = (TextView) view.findViewById(R.id.item_menu_price);

        itemName.setText(data.getTenMonAn());
        itemPrice.setText(new DecimalFormat("##,###").format(data.getGia()));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
