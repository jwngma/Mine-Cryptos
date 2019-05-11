package com.store.minecryps.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.JsonObject;
import com.store.minecryps.Interfaces.RetrofitApi;
import com.store.minecryps.Models.CoinStatModel;
import com.store.minecryps.Models.Data;
import com.store.minecryps.R;
import com.store.minecryps.Utils.GetTimeAgo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BitcoinFragment extends Fragment {
    private static final String TAG = "BitcoinFragment";

    private TextView CoinName, coinPrice, coinHighPrice, coinOpen, coinLow, coinVolume, coinLastTradeid, coin_latsUpdate;
    private String coinname, coinprice, coinhighprice, coinopen, coinlow, coinvolume, coinlasttradeid, coinlastupdate;
    PieChart pieChart;
    LineChart lineChart;
    BarChart barChart;

    String uurl = "https://min-api.cryptocompare.com/data/top/exchanges/full?fsym=BTC&tsym=USD";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bitcoin, container, false);

        initAll(view);
        initBTCData();


        return view;
    }




    private void initAll(View view) {
        CoinName = view.findViewById(R.id.coinstat_coin_name);
        coinPrice = view.findViewById(R.id.coinstat_price);
        coinHighPrice = view.findViewById(R.id.coinstat_high_price);
        coinOpen = view.findViewById(R.id.coinstat_open);
        coinLow = view.findViewById(R.id.coinstat_low);
        coinVolume = view.findViewById(R.id.coinstat_volume);
        coinLastTradeid = view.findViewById(R.id.last_trade_id);
        coin_latsUpdate = view.findViewById(R.id.last_update);
        pieChart = view.findViewById(R.id.pieChart);
        lineChart = view.findViewById(R.id.lineChart);
        barChart = view.findViewById(R.id.barchart);
    }


    public void DisplayGraph(String totalCoin, String totalCoinMined) {
        ArrayList<PieEntry> yValue = new ArrayList<>();
        yValue.add(new PieEntry(Float.parseFloat("99999999"), "Total Coin Mined"));
        yValue.add(new PieEntry(Float.parseFloat(coinvolume), "Total Coin"));

        PieDataSet pieDataSet = new PieDataSet(yValue, "");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(249, 147, 81));
        colors.add(Color.rgb(109, 33, 79));

        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.setDrawHoleEnabled(false);
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.BLACK);

        //
    }
    private void initBTCData() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://min-api.cryptocompare.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

        //

        Call<CoinStatModel> call = retrofitApi.getBTCCoinStat();
        call.enqueue(new Callback<CoinStatModel>() {
            @Override
            public void onResponse(Call<CoinStatModel> call, Response<CoinStatModel> response) {

                if (!response.isSuccessful()) {
                    CoinName.setText(response.code());
                    return;
                } else {


                    CoinStatModel coinStatModel = response.body();
                    coinname = response.body().getData().getCoinInfo().getFullName();
                    coinprice = response.body().getData().getAggregatedData().getpRICE();
                    coinhighprice = response.body().getData().getAggregatedData().gethIGHDAY();
                    coinopen = response.body().getData().getAggregatedData().getoPENDAY();
                    coinlow = response.body().getData().getAggregatedData().getlOWDAY();
                    coinvolume=response.body().getData().getAggregatedData().getsUPPLY();
                    coinlasttradeid=response.body().getData().getAggregatedData().getlASTTRADEID();
                    coinlastupdate=response.body().getData().getAggregatedData().getlASTUPDATE();

                    CoinName.setText(coinname);

                    coinPrice.setText("Price: "+coinprice);
                    coinHighPrice.setText("High: "+coinhighprice);
                    coinOpen.setText("Open: "+coinopen);
                    coinLow.setText("Low: "+coinlow);
                    coinVolume.setText("Volume: "+coinvolume);
                    coinLastTradeid.setText("Last Trade id: "+coinlasttradeid);

                    //
                    GetTimeAgo getTimeAgo = new GetTimeAgo();
                    long lastTime = Long.parseLong(coinlastupdate);
                    String lastupdated = getTimeAgo.getTimeAgo(lastTime, getContext());
                    coin_latsUpdate.setText("Last Updated At:" + lastupdated);


                    DisplayGraph(coinprice, coinvolume);






                }

            }

            @Override
            public void onFailure(Call<CoinStatModel> call, Throwable t) {

                Toast.makeText(getContext(), "Error in onFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();
                CoinName.setText(t.getMessage());

            }
        });


    }

}
