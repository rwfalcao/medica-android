package com.example.android.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.android.pi2.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class QuantidadeAdapter extends BaseExpandableListAdapter {

    Context ctx;
    List<String> list;
    HashMap<String, List<String>> hashMap;

    public QuantidadeAdapter(Context ctx, List<String> list, HashMap<String, List<String>> hashMap) {
        this.ctx = ctx;
        this.list = list;
        this.hashMap = hashMap;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return hashMap.get(list.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return hashMap.get(list.get(i)).get(i1);
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
        View v = LayoutInflater.from(ctx).inflate(R.layout.qtd_diario_header, viewGroup, false);

        TextView txtView = (TextView) v.findViewById(R.id.qtdHeader);
        txtView.setText(list.get(i));

        return v;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.qtd_diario_content, viewGroup, false);

        TextView txtView = (TextView) v.findViewById(R.id.qtdContent);
        txtView.setText(hashMap.get(list.get(i)).get(i1));

        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
