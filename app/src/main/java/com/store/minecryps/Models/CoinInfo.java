package com.store.minecryps.Models;

import com.google.gson.annotations.SerializedName;

public class CoinInfo {

    @SerializedName("Id")
    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("FullName")
    private String fullName;
    @SerializedName("Internal")
    private String internal;
    @SerializedName("ImageUrl")
    private String imageUrl;
    @SerializedName("Url")
    private String url;
    @SerializedName("Algorithm")
    private String algorithm;
    @SerializedName("ProofType")
    private String proofType;
    @SerializedName("TotalCoinsMined")
    private String totalCoinsMined;
    @SerializedName("BlockNumber")
    private String blockNumber;
    @SerializedName("NetHashesPerSecond")
    private String netHashesPerSecond;
    @SerializedName("BlockReward")
    private String blockReward;
    @SerializedName("BlockTime")
    private String blockTime;
    @SerializedName("TotalVolume24H")
    private String totalVolume24H;


    public CoinInfo(String id, String name, String fullName, String internal, String imageUrl, String url, String algorithm, String proofType, String totalCoinsMined, String blockNumber, String netHashesPerSecond, String blockReward, String blockTime, String totalVolume24H) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.internal = internal;
        this.imageUrl = imageUrl;
        this.url = url;
        this.algorithm = algorithm;
        this.proofType = proofType;
        this.totalCoinsMined = totalCoinsMined;
        this.blockNumber = blockNumber;
        this.netHashesPerSecond = netHashesPerSecond;
        this.blockReward = blockReward;
        this.blockTime = blockTime;
        this.totalVolume24H = totalVolume24H;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInternal() {
        return internal;
    }

    public void setInternal(String internal) {
        this.internal = internal;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType;
    }

    public String getTotalCoinsMined() {
        return totalCoinsMined;
    }

    public void setTotalCoinsMined(String totalCoinsMined) {
        this.totalCoinsMined = totalCoinsMined;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getNetHashesPerSecond() {
        return netHashesPerSecond;
    }

    public void setNetHashesPerSecond(String netHashesPerSecond) {
        this.netHashesPerSecond = netHashesPerSecond;
    }

    public String getBlockReward() {
        return blockReward;
    }

    public void setBlockReward(String blockReward) {
        this.blockReward = blockReward;
    }

    public String getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(String blockTime) {
        this.blockTime = blockTime;
    }

    public String getTotalVolume24H() {
        return totalVolume24H;
    }

    public void setTotalVolume24H(String totalVolume24H) {
        this.totalVolume24H = totalVolume24H;
    }
}
