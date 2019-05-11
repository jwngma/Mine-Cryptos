package com.store.minecryps.Interfaces;

import com.store.minecryps.Models.CoinStatModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {
    String path = "https://min-api.cryptocompare.com/data/top/exchanges/full?fsym=ETH&tsym=USD";

    @GET("data/top/exchanges/full?fsym=BTC&tsym=USD")
    Call<CoinStatModel> getBTCCoinStat();
    @GET("data/top/exchanges/full?fsym=ETH&tsym=USD")
    Call<CoinStatModel> getEthCoinStat();
    @GET("data/top/exchanges/full?fsym=LTC&tsym=USD")
    Call<CoinStatModel> getLTCCoinStat();
    @GET("data/top/exchanges/full?fsym=DOGE&tsym=USD")
    Call<CoinStatModel> getDOGECoinStat();
    @GET("data/top/exchanges/full?fsym=XRP&tsym=USD")
    Call<CoinStatModel> getXRPCoinStat();
    @GET("data/top/exchanges/full?fsym=DASH&tsym=USD")
    Call<CoinStatModel> getDashCoinStat();
    @GET("data/top/exchanges/full?fsym=XMR&tsym=USD")
    Call<CoinStatModel> getMNRCoinStat();
    @GET("data/top/exchanges/full?fsym=BCN&tsym=USD")
    Call<CoinStatModel> getBCNCoinStat();
}
