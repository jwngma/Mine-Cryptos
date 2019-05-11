package com.store.minecryps.Adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.store.minecryps.Activities.CoinDetailActivity;
import com.store.minecryps.Models.CoinsModel;
import com.store.minecryps.R;

import java.util.List;

public class CoinsRecyclerviewAdapter extends RecyclerView.Adapter<CoinsRecyclerviewAdapter.CoinsViewHolder> {

    private Context context;
    private List<CoinsModel> list;

    public CoinsRecyclerviewAdapter(Context context, List<CoinsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CoinsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_single_layout, viewGroup, false);
        return new CoinsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CoinsViewHolder coinsViewHolder, final int i) {
        coinsViewHolder.coin_name.setText(list.get(i).getCoin_name());
        Picasso.get().load(list.get(i).getCoin_image()).placeholder(R.drawable.bitcoin).into(coinsViewHolder.coin_image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(list.get(i).getCoin_image()).into(coinsViewHolder.coin_image);
            }
        });

        final int coin_Image=list.get(i).getCoin_image();
        coinsViewHolder.coin_image.setImageResource(list.get(i).getCoin_image());
        coinsViewHolder.coin_value.setText("Value $" + list.get(i).getCoin_value());
        coinsViewHolder.coin_amount.setText("Amount $" + list.get(i).getCoin_amount());
        //final String coinImage=list.get(i).getCoin_image();
        final String coinName=list.get(i).getCoin_name();
        final String coinMined=list.get(i).getCoin_mined();
        final String coinShortName=list.get(i).getCoin_short_name();
        final String coinValue=list.get(i).getCoin_value();
        final String coinAmount=list.get(i).getCoin_amount();
        coinsViewHolder.coin_short_name.setText(coinShortName);


        final CharSequence option[] = new CharSequence[]{"Mine This Coin", "Check This Coin"};

        coinsViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose any");
                builder.setIcon(R.drawable.bitcoin);
                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        if (position == 0) {
                            Toast.makeText(context, "Mine This coin", Toast.LENGTH_SHORT).show();

                        } else if (position == 1) {
                            Toast.makeText(context, "Check This Coin", Toast.LENGTH_SHORT).show();
                            Intent coinDetailIntent= new Intent(context, CoinDetailActivity.class);
                            coinDetailIntent.putExtra("coin_image",coin_Image);
                            coinDetailIntent.putExtra("coinName",coinName);
                            coinDetailIntent.putExtra("coinMined",coinMined);
                            coinDetailIntent.putExtra("coinShortName",coinShortName);
                            coinDetailIntent.putExtra("coinValue",coinValue);
                            coinDetailIntent.putExtra("coinAmount",coinAmount);


                            context.startActivity(coinDetailIntent);
                        }


                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.holo_green_dark);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CoinsViewHolder extends RecyclerView.ViewHolder {

        View mView;
        private ImageView coin_image;
        private TextView coin_name, coin_value, coin_amount,coin_short_name;

        public CoinsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            coin_image = mView.findViewById(R.id.coin_image);
            coin_name = mView.findViewById(R.id.coin_name);
            coin_value = mView.findViewById(R.id.coin_value);
            coin_amount = mView.findViewById(R.id.coin_amount);
            coin_short_name=mView.findViewById(R.id.coin_name_short);
        }


    }



}
