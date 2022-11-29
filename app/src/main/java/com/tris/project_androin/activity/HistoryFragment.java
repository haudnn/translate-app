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
import com.tris.project_androin.adapter.HistoryAdapter;
import com.tris.project_androin.model.History;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    SQLHelper sqlHelper;
    ListView lv;
    ArrayList<History> arrayList;
    HistoryAdapter adapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.history_fragment, container, false);
        setOpen();

        return view;
    }
    public void setOpen() {
        sqlHelper = new SQLHelper(getContext(), "translate.app" , null, 1);
        sqlHelper.QueryData("CREATE TABLE IF NOT EXISTS History(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), mean VARCHAR(200))");
        sqlHelper.QueryData("CREATE TABLE IF NOT EXISTS Favorite(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), mean VARCHAR(200))");
        lv = (ListView) view.findViewById(R.id.lvHistories);
        arrayList = new ArrayList<>();
        adapter = new HistoryAdapter(getActivity(), R.layout.item_word, arrayList, this);
        lv.setAdapter(adapter);
        actionGetData();
    }

    public void deleteHistory(int id) {
        sqlHelper.QueryData("DELETE FROM History WHERE Id='"+id+"'");
        actionGetData();
    }
    public void actionGetData(){
        Cursor hitories = sqlHelper.GetData("SELECT * FROM History");
        arrayList.clear();
        while (hitories.moveToNext()){
            String name = hitories.getString(1);
            int id = hitories.getInt(0);
            String mean = hitories.getString(2);
            arrayList.add(new History(id, name, mean));
        }
        adapter.notifyDataSetChanged();
    }
}
