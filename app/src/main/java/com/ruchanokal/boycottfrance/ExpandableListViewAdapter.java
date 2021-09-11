package com.ruchanokal.boycottfrance;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mGroupList;
    private HashMap<String, List<String>> mProductList;



    public ExpandableListViewAdapter(Context context, List<String> groupList, HashMap<String, List<String>> productList) {
        this.mContext = context;
        this.mGroupList = groupList;
        this.mProductList = productList;

    }

    @Override
    public int getGroupCount() {
        return this.mGroupList.size();

    }

    @Override
    public int getChildrenCount(int i) {
        return this.mProductList.get(mGroupList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.mGroupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.mProductList.get(this.mGroupList.get(i)).get(i1);
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
        String groupTitle = (String) getGroup(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_group,null);


        }
        TextView groupText = view.findViewById(R.id.groupText);
        groupText.setText(groupTitle);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String productTitle = (String) getChild(i,i1);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_product,null);

        }

        TextView productText = view.findViewById(R.id.productText);
        productText.setText(productTitle);


        return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {

        return true;
    }


}
