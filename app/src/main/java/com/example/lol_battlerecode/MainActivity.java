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
import com.example.lol_battlerecode.model.SummonerRankInfo;

import java.util.Locale;

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
                    Toast notExistToast = Toast.makeText(getApplicationContext(),"Summoner name does not exist,",Toast.LENGTH_SHORT);
                    notExistToast.show();
                }
            }
        });

        viewModel.getSummonerRankInfoLiveData().observe(this, new Observer<SummonerRankInfo>() {
            @Override
            public void onChanged(SummonerRankInfo summonerRankInfo) {
                if(summonerRankInfo != null){
                    input_layout.setVisibility(View.GONE);
                    setRankInfo(summonerRankInfo);
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

    private  void setRankInfo(SummonerRankInfo summonerRankInfo){
        setTierEmblem(summonerRankInfo.getTier());
        tv_summoner_name.setText(summonerRankInfo.getSummonerName());
        String tierRank = summonerRankInfo.getTier() + "" + summonerRankInfo.getRank();
        tier.setText(tierRank);
        if (summonerRankInfo.getTier().equals("UNRANKED")){
            game.setText("");
            lp.setText("");
            win_rate.setText("");
            win_lose.setText("");
        }
        else{
            game.setText(summonerRankInfo.getQueueType());
            String point = String.valueOf(summonerRankInfo.getLeaguePoints()) + "LP";
            lp.setText(String.valueOf(point));

            double rate = (double) summonerRankInfo.getWins() / (double) (summonerRankInfo.getLosses() + summonerRankInfo.getWins()) * 100;
            win_rate.setText(String.format(Locale.getDefault(), "%.2f%%", rate));


            String winAndLosses = summonerRankInfo.getWins()
                    +getResources().getString(R.string.win) + " "
                    + summonerRankInfo.getLosses()
                    +getResources().getString(R.string.defeat);
            win_lose.setText(winAndLosses);

        }
        info_layout.setVisibility(View.VISIBLE);


    }
    private void setTierEmblem(String tier){
        switch (tier){
            case "UNRANKED" :
                dia.setImageResource(R.drawable.emblem_unranked);
            case "IRON":
            dia.setImageResource(R.drawable.emblem_iron);
                break;
            case  "BRONZE":
            dia.setImageResource(R.drawable.emblem_bronze);
                break;
            case  "SILVER":
            dia.setImageResource(R.drawable.emblem_silver);
                break;
            case  "GOLD":
            dia.setImageResource(R.drawable.emblem_gold);
                break;
            case  "PLATINUM":
            dia.setImageResource(R.drawable.emblem_platinum);
                break;
            case  "DIAMOND":
            dia.setImageResource(R.drawable.emblem_diamond);
                break;
            case  "MASTER":
            dia.setImageResource(R.drawable.emblem_master);
                break;
            case  "GRANDMASTER":
            dia.setImageResource(R.drawable.emblem_grandmaster);
                break;
            case  "CHALLENGER":
            dia.setImageResource(R.drawable.emblem_challenger);
                break;
        }
    }
}