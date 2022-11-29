package com.tris.project_androin.activity;

import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.tris.project_androin.R;
import com.tris.project_androin.model.History;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    private TextView tv_name;
    private TextView tv_mean;
    TextToSpeech textToSpeech;
    private ImageButton btnSpeaker;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_word_layout);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            return;
        }
        History history =(History) bundle.get("object");
        tv_name = findViewById(R.id.tv_name);
        tv_mean = findViewById(R.id.tv_mean);
        btnSpeaker = findViewById(R.id.btnSpeaker);
        tv_name.setText(history.getName());
        tv_mean.setText(history.getMean());
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
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
                String data = history.getName();
                textToSpeech.speak(data,TextToSpeech.QUEUE_FLUSH,null);
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
}
