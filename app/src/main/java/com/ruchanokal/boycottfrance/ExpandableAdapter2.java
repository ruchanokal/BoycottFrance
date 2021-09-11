package com.ruchanokal.boycottfrance;

import android.app.Activity;
import android.content.Context;
import android.util.JsonToken;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpandableAdapter2 extends BaseExpandableListAdapter {

    private ArrayList<String> groupList;
    private HashMap<String, ArrayList<String>> childList;
    private Activity mContext;
    int c=0;

    public ExpandableAdapter2(ArrayList<String> groupList, HashMap<String, ArrayList<String>> childList, Activity mContext) {
        this.groupList = groupList;
        this.childList = childList;
        this.mContext = mContext;
    }



    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(groupList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childList.get(groupList.get(i)).get(i1);
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
        String childTitle = (String) getChild(i,i1);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_product,null);
        }

        TextView childText = view.findViewById(R.id.productText);
        childText.setText(childTitle);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    public void filterData(String query) {

        query = query.toLowerCase();

        if (query.isEmpty()) {
            Toast.makeText(mContext, "Please, enter any product name you want to find!", Toast.LENGTH_SHORT).show();
        } else{

            for(int a=0;a<groupList.size();a++){

                ArrayList<String> parents = new ArrayList<>();
                parents.add(groupList.get(a));

                for(int x=0; x<childList.get(groupList.get(a)).size(); x++) {

                    ArrayList<String> childs = new ArrayList<>();
                    childs.add(childList.get(groupList.get(a)).get(x));
                    ArrayList<String> newList = new ArrayList<>();

                    if (childs.get(0).toLowerCase().contains(query)) {
                        newList.add(childs.get(0));
                    }

                }

            }


        }

    }
}

