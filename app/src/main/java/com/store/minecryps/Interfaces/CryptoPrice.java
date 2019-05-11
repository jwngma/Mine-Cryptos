package com.store.minecryps.Interfaces;

import com.store.minecryps.Models.CrytoPriceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CryptoPrice {

  /*  //https://api.coinmarketcap.com/v1/ticker/
    @GET("v1/ticker")*//* posts is the relative url, the relative url will be placed in the other part*//*
    Call<List<CrytoPriceModel>> getCryptoPrice(@Query("id") String[] id);
*/

    //https://api.coinmarketcap.com/v1/ticker/
    @GET("v1/ticker")/* posts is the relative url, the relative url will be placed in the other part*/
    Call<List<CrytoPriceModel>> getCryptoPrice(@Query("name") List<String> array );

    @GET("v1/ticker")/* posts is the relative url, the relative url will be placed in the other part*/
    Call<List<CrytoPriceModel>> getPost(@Query("id") String[] id,
                                        @Query("_sort") String rank
    );

    @GET("v1/ticker")/* posts is the relative url, the relative url will be placed in the other part*/
    Call<List<CrytoPriceModel>> getPost(@Query("id") Integer[] id
    );

    @GET("v1/ticker")/* posts is the relative url, the relative url will be placed in the other part*/
    Call<List<CrytoPriceModel>> getPost(@Query("id") String id,
                                        @Query("id") String id1,
                                        @Query("id") String id2,
                                        @Query("id") String id3,
                                        @Query("id") String id4
    );

}
