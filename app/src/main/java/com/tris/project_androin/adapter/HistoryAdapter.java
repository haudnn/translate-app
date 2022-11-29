package com.tris.project_androin.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tris.project_androin.R;
import com.tris.project_androin.activity.DetailActivity;
import com.tris.project_androin.activity.HistoryFragment;
import com.tris.project_androin.model.History;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    private HistoryFragment fra;
    private Context context ;
    private int layout;
    private List<History> histories;

    public HistoryAdapter(Context context, int layout, List<History> histories, HistoryFragment fra) {
        this.context = context;
        this.layout = layout;
        this.histories = histories;
        this.fra = fra;
    }

    @Override
    public int getCount() {
        return histories.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvName;
        ImageView btnDelete;
        RelativeLayout layoutItem;
    }

    private void onClickGoToDetail(History History) {
        Intent intent  = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", History);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view  = inflater.inflate(layout , null);
            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            holder.btnDelete = (ImageView) view.findViewById(R.id.btnDelete);
            holder.layoutItem = (RelativeLayout) view.findViewById(R.id.layout_item);
            view.setTag(holder);
        }
        else {
            holder =(ViewHolder) view.getTag();
        }
        final History history = histories.get(i);
        holder.tvName.setText(history.getName());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fra.deleteHistory(history.getId());
            }
        });
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(history);
            }
        });

        return view;
    }
}
