package com.example.lol_battlerecode.retro;

import com.example.lol_battlerecode.model.MatchHistory;
import com.example.lol_battlerecode.model.Match_List;
import com.example.lol_battlerecode.model.SummonerIDInfo;
import com.example.lol_battlerecode.model.SummonerRankInfo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RiotAPI {

    @Headers({"Accept: application/json", "X-Riot-Token: " + BaseUrl.RIOT_API_KEY})
    @GET(BaseUrl.RIOT_API_GET_SUMMONER + "{userId}")
    Single<SummonerIDInfo> getSummonerInfo(@Path("userId") String userId);

    @Headers({"Accept: application/json", "X-Riot-Token: " + BaseUrl.RIOT_API_KEY})
    @GET(BaseUrl.RIOT_API_GET_RANK + "{userId}")
    Single<List<SummonerRankInfo>> getSummonerRankInfo(@Path("userId") String userId);

    @Headers({"Accept: application/json", "X-Riot-Token: " + BaseUrl.RIOT_API_KEY})
    @GET(BaseUrl.RIOT_API_GET_MATCH_LIST + "{accountId}")
    Single<Match_List> getMatchHistoryList(@Path("accountId") String accountId);

    @Headers({"Accept: application/json", "X-Riot-Token: " + BaseUrl.RIOT_API_KEY})
    @GET(BaseUrl.RIOT_API_GET_MATCH + "{matchId}")
    Single<MatchHistory> getMatchHistory(@Path("matchId") String matchId);



}
