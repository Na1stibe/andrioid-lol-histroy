package com.example.lol_battlerecode.retro;

public class BaseUrl {
    static final String RIOT_API_BASE_URL = "https://kr.api.riotgames.com/";
    // Riot API Key 입력
    static final String RIOT_API_KEY = "RGAPI-62e9c3e3-62e9-4a05-8422-d5cdc438b04a";

    static final String RIOT_API_GET_SUMMONER_BY_ENCRYPTION_SUMMONER_ID = "lol/summoner/v4/summoners/";
    static final String RIOT_API_GET_SUMMONER = "lol/summoner/v4/summoners/by-name/";
    static final String RIOT_API_GET_RANK = "lol/league/v4/entries/by-summoner/";
    static final String RIOT_API_GET_MATCH_LIST = "lol/match/v4/matchlists/by-account/";
    static final String RIOT_API_GET_MATCH = "lol/match/v4/matches/";

    public static final String RIOT_DATA_DRAGON_GET_CHAMPION_PORTRAIT = "https://ddragon.leagueoflegends.com/cdn/10.16.1/img/champion/";
    public static final String RIOT_DATA_DRAGON_GET_ITEM_IMAGE = "https://ddragon.leagueoflegends.com/cdn/10.16.1/img/item/";
    public static final String RIOT_DATA_DRAGON_GET_SPELL_IMAGE = "https://ddragon.leagueoflegends.com/cdn/10.16.1/img/spell/";
    public static final String RIOT_DATA_DRAGON_GET_RUNE_IMAGE = "https://ddragon.leagueoflegends.com/cdn/img/";
}
