package com.tris.project_androin.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tris.project_androin.R;
import com.tris.project_androin.api.ApiService;
import com.tris.project_androin.model.Favorite;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private MainActivity mainActivity;
    private ImageButton imageButton ;
    private TextView txtRes;
    private EditText txtSrc;
    private TextView txtTo;
    private TextView txtFrom;
    private TextView txtView11;
    private TextView txtView12;
    TextToSpeech textToSpeech;
    String sl = "en";
    String tl = "vi";
    SQLHelper sqlHelper;
    private ImageButton btnSpeaker;
    private ImageButton btnSpeaker2;
    private ImageButton imgButtonFavorite1;
    private ImageButton imgButtonFavorite2;


    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.home_fragment, container, false);
        mainActivity = (MainActivity) getActivity();
        setOpen();
        return view;
    }

    private void setOpen() {
        sqlHelper = new SQLHelper(getContext(), "translate.app" , null, 1);
        // sqlHelper.QueryData("DROP TABLE IF EXISTS History");
        sqlHelper.QueryData("CREATE TABLE IF NOT EXISTS Favorite(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), mean VARCHAR(200))");
        sqlHelper.QueryData("CREATE TABLE IF NOT EXISTS History(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), mean VARCHAR(200))");

        txtTo = view.findViewById(R.id.txtTo);
        txtFrom = view.findViewById(R.id.txtFrom);
        txtRes = view.findViewById(R.id.txtRes);
        txtSrc = view.findViewById(R.id.txtSrc);
        txtView12 = view.findViewById(R.id.textView12);
        txtView11 = view.findViewById(R.id.textView11);
        btnSpeaker = view.findViewById(R.id.imageButtonSpeak);
        btnSpeaker2 = view.findViewById(R.id.imgBtnSpeak2);
        imgButtonFavorite1 = view.findViewById(R.id.imgButtonFavorite1);
        imgButtonFavorite2 = view.findViewById(R.id.imgButtonFavorite2);
        imageButton = view.findViewById(R.id.switch1);
        textToSpeech = new TextToSpeech(mainActivity, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS){
                    int lang  = textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        btnSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = txtSrc.getText().toString();
                textToSpeech.speak(data,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        btnSpeaker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = txtRes.getText().toString();
                textToSpeech.speak(data,TextToSpeech.QUEUE_FLUSH,null);
            }
        });


        txtSrc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0) {
                    String getTextSource = txtSrc.getText().toString();
                    callApi(sl, tl, getTextSource);
                }
                if(charSequence.length() == 0 ) {
                    txtRes.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        txtSrc.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    saveDB();
                    return true;
                }
                return false;
            }
        });
        imgButtonFavorite1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Cursor favorites = sqlHelper.GetData("SELECT MAX(Id) FROM Favorite");
                int id = 0;
                while (favorites.moveToNext()){
                    id = favorites.getInt(0);
                }
                sqlHelper.QueryData("DELETE FROM Favorite WHERE Id='"+id+"'"); */
                saveDBFavorite();
                Toast.makeText(getContext(), "Favorite Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        imgButtonFavorite2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDBFavorite();
                Toast.makeText(getContext(), "Favorite Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp = sl;
                String txtTmpTo = txtTo.getText().toString();
                txtSrc.setText("");
                txtRes.setText("");
                txtTo.setText(txtFrom.getText().toString());
                txtView12.setText(txtFrom.getText().toString());
                txtFrom.setText(txtTmpTo);
                txtView11.setText(txtTmpTo);
                sl = tl;
                tl = tmp;

            }
        });

    }
    private void callApi(String sl, String tl, String q) {
        ApiService.apiService.translate("gtx",sl, tl,"t", q).enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List> call, Response<List> response) {
                List res = (List) response.body().get(0);
                List res2 = (List) res.get(0);
                for (int i=0;i < res2.size();i++)
                {
                    txtRes.setText(res2.get(0).toString());
                }
            }
            @Override
            public void onFailure(Call<List> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
    public void saveDB() {
        String name = txtSrc.getText().toString();
        String res = txtRes.getText().toString();
        sqlHelper.QueryData("INSERT INTO History VALUES (null, '"+ name +"', '" + res +"')");
    }
    public void saveDBFavorite() {
        String data = txtSrc.getText().toString();
        String res = txtRes.getText().toString();
        sqlHelper.QueryData("INSERT INTO Favorite VALUES (null, '"+ data +"', '" + res +"')");
    }

}
