package com.store.minecryps.Models;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("CoinInfo")
    private CoinInfo coinInfo;
     @SerializedName("AggregatedData")
    private  AggregatedData aggregatedData;

    public Data(CoinInfo coinInfo, AggregatedData aggregatedData) {
        this.coinInfo = coinInfo;
        this.aggregatedData = aggregatedData;
    }

    public CoinInfo getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfo coinInfo) {
        this.coinInfo = coinInfo;
    }

    public AggregatedData getAggregatedData() {
        return aggregatedData;
    }

    public void setAggregatedData(AggregatedData aggregatedData) {
        this.aggregatedData = aggregatedData;
    }
}
