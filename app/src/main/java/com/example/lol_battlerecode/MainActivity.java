package com.example.lol_battlerecode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lol_battlerecode.model.SummonerIDInfo;

import javax.crypto.Mac;

public class MainActivity extends AppCompatActivity {

    MainActivityviewmodel viewModel;
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

        viewModel = ViewModelProviders.of(this).get(MainActivityviewmodel.class);

        viewModel.getSummonerIDInfoLiveData().observe(this, new Observer<SummonerIDInfo>() {
            @Override
            public void onChanged(SummonerIDInfo summonerIDInfo) {
                if (summonerIDInfo == null){
                    Toast notExistToast = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);
                    notExistToast.show();
                }
            }
        });
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
                viewModel.searchSummoner(et_TextSummoner.getText().toString());
            }
        });
    }
}