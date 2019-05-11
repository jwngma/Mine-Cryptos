package com.store.minecryps.Models;

public class CoinsModel {
    private String coin_name, coin_value,coin_amount,coin_mined,coin_short_name;
    private int coin_image;

    public CoinsModel() {
    }

    public CoinsModel(String coin_name, String coin_value, String coin_amount, String coin_mined, String coin_short_name, int coin_image) {
        this.coin_name = coin_name;
        this.coin_value = coin_value;
        this.coin_amount = coin_amount;
        this.coin_mined = coin_mined;
        this.coin_short_name = coin_short_name;
        this.coin_image = coin_image;
    }

    public String getCoin_name() {
        return coin_name;
    }

    public void setCoin_name(String coin_name) {
        this.coin_name = coin_name;
    }

    public String getCoin_value() {
        return coin_value;
    }

    public void setCoin_value(String coin_value) {
        this.coin_value = coin_value;
    }

    public String getCoin_amount() {
        return coin_amount;
    }

    public void setCoin_amount(String coin_amount) {
        this.coin_amount = coin_amount;
    }

    public String getCoin_mined() {
        return coin_mined;
    }

    public void setCoin_mined(String coin_mined) {
        this.coin_mined = coin_mined;
    }

    public String getCoin_short_name() {
        return coin_short_name;
    }

    public void setCoin_short_name(String coin_short_name) {
        this.coin_short_name = coin_short_name;
    }

    public int getCoin_image() {
        return coin_image;
    }

    public void setCoin_image(int coin_image) {
        this.coin_image = coin_image;
    }
}
