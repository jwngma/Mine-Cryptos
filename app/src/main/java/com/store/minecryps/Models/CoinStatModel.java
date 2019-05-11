package com.store.minecryps.Models;

import com.google.gson.annotations.SerializedName;

public class CoinStatModel {

    private String Response;
    private String Message;
    @SerializedName("Data")
    private Data data;

    public CoinStatModel(String response, String message, Data data) {
        Response = response;
        Message = message;
        this.data = data;
    }


    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
