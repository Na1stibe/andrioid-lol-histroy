package com.example.lol_battlerecode.retro;

import com.example.lol_battlerecode.model.SummonerIDInfo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RiotAPI {

    @Headers({"Accept: application/json", "X-Riot-Token: " + BaseUrl.RIOT_API_KEY})
    @GET(BaseUrl.RIOT_API_GET_SUMMONER + "{userId}")
    Single<SummonerIDInfo> getSummonerInfo(@Path("userId") String userId);
}
