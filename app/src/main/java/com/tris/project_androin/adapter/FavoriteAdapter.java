package com.tris.project_androin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tris.project_androin.R;
import com.tris.project_androin.activity.FavoriteFragment;
import com.tris.project_androin.activity.HistoryFragment;
import com.tris.project_androin.model.Favorite;
import com.tris.project_androin.model.History;

import java.util.List;

public class FavoriteAdapter extends BaseAdapter {
    private FavoriteFragment fra;
    private Context context ;
    private int layout;
    private List<Favorite> favorites;

    public FavoriteAdapter(Context context, int layout, List<Favorite> favorites, FavoriteFragment fra) {
        this.context = context;
        this.layout = layout;
        this.favorites = favorites;
        this.fra = fra;
    }

    @Override
    public int getCount() {
        return favorites.size();
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
        TextView tvMean;
        ImageView btnDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view  = inflater.inflate(layout , null);
            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            holder.tvMean = (TextView) view.findViewById(R.id.tvMean);
            holder.btnDelete = (ImageView) view.findViewById(R.id.btnDelete);
            view.setTag(holder);
        }
        else {
            holder =(ViewHolder) view.getTag();
        }
        final Favorite favorite = favorites.get(i);
        holder.tvName.setText(favorite.getName());
        holder.tvMean.setText(favorite.getMean());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fra.deleteFavorite(favorite.getId());
            }
        });

        return view;
    }
}
