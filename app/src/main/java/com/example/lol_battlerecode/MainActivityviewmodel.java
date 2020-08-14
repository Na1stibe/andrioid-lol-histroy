package com.example.lol_battlerecode;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lol_battlerecode.model.MatchHistory;
import com.example.lol_battlerecode.model.Match_List;
import com.example.lol_battlerecode.model.SummonerIDInfo;
import com.example.lol_battlerecode.model.SummonerRankInfo;
import com.example.lol_battlerecode.retro.APIClient;
import com.example.lol_battlerecode.retro.RiotAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityviewmodel extends ViewModel {
    private MutableLiveData<SummonerIDInfo> summonerIDInfoLiveData;
    private MutableLiveData<SummonerRankInfo> summonerRankInfoLiveData;
    private MutableLiveData<HistoryAdapter> historyAdapterLiveData;

    private  String summonerName = "";

    private RiotAPI riotAPI = APIClient.getRiotClient().create(RiotAPI.class);
    private ArrayList<MatchHistory>matchHistories = new ArrayList<>();

    private SummonerIDInfo summonerIDInfo = null;

    public MainActivityviewmodel() {
        summonerIDInfoLiveData = new MutableLiveData<>();
        summonerRankInfoLiveData = new MutableLiveData<>();
        historyAdapterLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<SummonerIDInfo> getSummonerIDInfoLiveData(){
        return summonerIDInfoLiveData;
    }

    public MutableLiveData<SummonerRankInfo> getSummonerRankInfoLiveData() {
        return summonerRankInfoLiveData;
    }

    public MutableLiveData<HistoryAdapter> getHistoryAdapterLiveData(){
        return historyAdapterLiveData;
    }

    public  void searchSummoner(String summonerName) {
        this.summonerName = summonerName;

        matchHistories.clear();

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
                        getSummonerRankInfo(idInfo.getId());
                        getMatchHistoryList(idInfo.getAccountid());
                        Log.d("TESTLOG", "[getSummonerIDInfo] id: " + idInfo.getId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TESTLOG", "[getSummonerIDInfo] exception: " + e);
                        summonerIDInfoLiveData.setValue(null);
                    }
                });

    }
    private  void getSummonerRankInfo(String summonerId){
        riotAPI.getSummonerRankInfo(summonerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<SummonerRankInfo>>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SummonerRankInfo> summonerRankInfos) {
                        setSummonerRankInfo(summonerRankInfos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TESTLOG", "[getSummonerRankInfo] exception:" +e);

                    }
                });

    }

    private void getMatchHistoryList(String accountId){
        riotAPI.getMatchHistoryList(accountId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Match_List>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Match_List match_list) {
                        int count = 0;
                        for (Match_List.Match match : match_list.getMatches()){
                            if (count <15){
                                getMatchHistory(match.getGameId(),accountId);
                                count++;
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TESTLOG","[getMatchHistoryList] exception:" +e);
                    }
                });
    }
    private void getMatchHistory(String matchId, String accountId){
        riotAPI.getMatchHistory(matchId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MatchHistory>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(MatchHistory matchHistory) {
                        matchHistories.add(matchHistory);
                        if (matchHistories.size() > 14){
                            HistoryAdapter historyAdapter
                                    = new HistoryAdapter(matchHistories, accountId);
                            historyAdapterLiveData.setValue(historyAdapter);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TESTLOG", "[getMatchHistory] exception: " +e);
                        historyAdapterLiveData.setValue(null);
                    }
                });
    }
    private void setSummonerRankInfo(List<SummonerRankInfo> summonerRankInfos){
        SummonerRankInfo soloRankInfo = null;
        SummonerRankInfo flexRankInfo = null;
        int soloRanktier= 0;
        int flexRanktier = 0;

        if(summonerRankInfos.isEmpty()){
            SummonerRankInfo unRankInfo = new SummonerRankInfo();
            unRankInfo.setTier("UNRANKED");
            unRankInfo.setRank("");
            unRankInfo.setSummonerName(summonerName);
            summonerRankInfoLiveData.setValue(unRankInfo);
        } else {
            for (SummonerRankInfo info : summonerRankInfos){
                if (info.getQueueType().equals("RANKED_SOLO_5x5")){
                    soloRankInfo = info;
                    soloRanktier = calcTier(info.getTier(), info.getRank(), info.getLeaguePoints());

                }else if(info.getQueueType().equals("RANKED_FLEX_5x5")){
                    flexRankInfo = info;
                    flexRanktier = calcTier(info.getTier(), info.getRank(), info.getLeaguePoints());
                }
                if (soloRanktier < flexRanktier){
                    summonerRankInfoLiveData.setValue((flexRankInfo));
                }
                else {
                    summonerRankInfoLiveData.setValue((soloRankInfo));
                }
            }
        }
    }
    private int calcTier(String tier, String rank, int lp){
        int tierNum = 0;
        switch (tier){
            case "IRON":
            break;
            case  "BRONZE":
                tierNum = 1000;
                break;
            case  "SILVER":
                tierNum = 2000;
                break;
            case  "GOLD":
                tierNum = 3000;
                break;
            case  "PLATINUM":
                tierNum = 4000;
                break;
            case  "DIAMOND":
                tierNum = 5000;
                break;
            case  "MASTER":
                tierNum = 6000;
                break;
            case  "GRANDMASTER":
                tierNum = 7000;
                break;
            case  "CHALLENGER":
                tierNum = 8000;
                break;
        }
        switch (rank){
            case "I":
                tierNum += 700;
                break;
            case "II":
                tierNum += 500;
                break;
            case "III":
                tierNum += 300;
                break;
            case "IV":
                tierNum += 100;
                break;
        }
        tierNum += lp;
        return tierNum;


    }
}
