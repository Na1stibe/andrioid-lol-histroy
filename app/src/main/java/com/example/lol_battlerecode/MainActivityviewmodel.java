package com.example.lol_battlerecode;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lol_battlerecode.model.SummonerIDInfo;
import com.example.lol_battlerecode.retro.APIClient;
import com.example.lol_battlerecode.retro.RiotAPI;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityviewmodel extends ViewModel {
    private MutableLiveData<SummonerIDInfo> summonerIDInfoLiveData;

    private  String summonerName = "";

    private RiotAPI riotAPI = APIClient.getRiotClient().create(RiotAPI.class);

    private SummonerIDInfo summonerIDInfo = null;

    public MainActivityviewmodel() {
        summonerIDInfoLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<SummonerIDInfo> getSummonerIDInfoLiveData(){
        return summonerIDInfoLiveData;
    }
    public  void searchSummoner(String summonerName) {
        this.summonerName = summonerName;

        getSummonerIDInfo();
    }

    private void getSummonerIDInfo(){
        riotAPI.getSummonerInfo(summonerName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SummonerIDInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SummonerIDInfo idInfo) {
                        summonerIDInfo = idInfo;
                        summonerName = idInfo.getName();
                        Log.d("TESTLOG", "[getSummonerIDInfo] id: " + idInfo.getId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TESTLOG", "[getSummonerIDInfo] exception: " + e);
                        summonerIDInfoLiveData.setValue(null);
                    }
                });

    }
}
