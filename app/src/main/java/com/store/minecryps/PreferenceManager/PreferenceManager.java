package com.store.minecryps.PreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;

import com.store.minecryps.R;

public class PreferenceManager {

    private Context context;
    private SharedPreferences sharedPreferences;


    public PreferenceManager(Context context) {
        this.context = context;

        getSharedPreference();
    }

    private void getSharedPreference() {
        sharedPreferences =context.getSharedPreferences(context.getString(R.string.my_preferenec),Context.MODE_PRIVATE);

    }

    public void writePreference(){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(context.getString(R.string.my_preference_key),"INIT_OK");
        editor.commit();

    }

    public boolean checkPreference(){

        boolean status= false;
        if (sharedPreferences.getString(context.getString(R.string.my_preference_key),"null").equals("null")){
            status=false;
        }
        else {
            status=true;
        }

        return status;
    }

    public void clearPreferenec(){
        sharedPreferences.edit().clear().commit();
    }

}
