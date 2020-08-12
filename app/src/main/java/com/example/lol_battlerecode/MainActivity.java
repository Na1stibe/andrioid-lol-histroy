package com.example.lol_battlerecode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout info_layout;
    ConstraintLayout input_layout;
    ImageView dia;
    TextView tv_summoner_name;
    TextView game;
    TextView tier;
    TextView lp;
    TextView win_rate;
    TextView win_lose;
    EditText et_TextSummoner;
    Button btn_input_summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info_layout = findViewById(R.id.info_layout);
        dia = findViewById(R.id.dia);
        tv_summoner_name = findViewById(R.id.tv_summoner_name);
        game = findViewById(R.id.game);
        tier = findViewById(R.id.tier);
        lp = findViewById(R.id.lp);
        win_rate = findViewById(R.id.win_rate);
        win_lose = findViewById(R.id.win_lose);

        input_layout = findViewById(R.id.input_layout);
        et_TextSummoner = findViewById(R.id.et_input_summoner);
        btn_input_summoner = findViewById(R.id.btn_input_summoner);
        btn_input_summoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}