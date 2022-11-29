package com.tris.project_androin.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tris.project_androin.R;
import com.tris.project_androin.adapter.FavoriteAdapter;
import com.tris.project_androin.model.Favorite;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    SQLHelper sqlHelper;
    ListView lv;
    ArrayList<Favorite> arrayList;
    FavoriteAdapter adapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favorite_fragment, container, false);
        setOpen();
        return view;
    }
    public void setOpen() {
        sqlHelper = new SQLHelper(getContext(), "translate.app" , null, 1);
        sqlHelper.QueryData("CREATE TABLE IF NOT EXISTS Favorite(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), mean VARCHAR(200))");
        lv = (ListView) view.findViewById(R.id.lvFavorites);
        arrayList = new ArrayList<>();
        adapter = new FavoriteAdapter(getActivity(), R.layout.item_favorite, arrayList, this);
        lv.setAdapter(adapter);
        actionGetData();
    }

    public void deleteFavorite(int id) {
        sqlHelper.QueryData("DELETE FROM Favorite WHERE Id='"+id+"'");
        actionGetData();
    }
    public void actionGetData(){
        Cursor favorites = sqlHelper.GetData("SELECT * FROM Favorite");
        arrayList.clear();
        while (favorites.moveToNext()){
            String name = favorites.getString(1);
            String mean = favorites.getString(2);
            int id = favorites.getInt(0);
            arrayList.add(new Favorite(id, name, mean));
        }
        adapter.notifyDataSetChanged();
    }
}
