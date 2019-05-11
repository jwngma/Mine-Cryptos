package com.store.minecryps.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.store.minecryps.Adapters.CoinsRecyclerviewAdapter;
import com.store.minecryps.Authentication.AuthenticationActivity;
import com.store.minecryps.Fragments.BitcoinFragment;
import com.store.minecryps.Fragments.BytecoinFragment;
import com.store.minecryps.Fragments.DashFragment;
import com.store.minecryps.Fragments.DogeCoinFragment;
import com.store.minecryps.Fragments.EtheriumFragment;
import com.store.minecryps.Fragments.LitecoinFragment;
import com.store.minecryps.Fragments.MoneroFragment;
import com.store.minecryps.Fragments.RippleFragment;
import com.store.minecryps.Interfaces.CryptoPrice;
import com.store.minecryps.Models.CoinsModel;
import com.store.minecryps.Models.CrytoPriceModel;
import com.store.minecryps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference mUsersRef;

    private List<CoinsModel> list = new ArrayList<>();
    private RecyclerView coinRecyclerview;
    CoinsRecyclerviewAdapter coinsRecyclerviewAdapter;
    private LinearLayoutManager linearLayoutManager;

    private Toolbar mtoolbar;
    private NavigationView navigation;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;

    private Spinner mine_coin_spinner;
    private String mining_coin;
    private ArrayAdapter<CharSequence> arrayAdapter;
    private TextView coin_selected;
    private RelativeLayout replace_layout;
    private AppBarLayout app_bar_layout;

    // Navigatoion header
    private TextView header_profile_name, header_profile_email;
    private ImageView header_profile_image;
    private TextView currency_earned;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Main Activity is vcreayed");
        FirebaseApp.initializeApp(this);
        mUsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        mUsersRef.keepSynced(true);
        setUpFirebaseAuth();
        initAll();
        initGetCryptoPrice();
        addToList();
        initToolbar();
        initDrawerLayout();

        coinRecyclerview = findViewById(R.id.coin_recyclerview);

        coinsRecyclerviewAdapter = new CoinsRecyclerviewAdapter(this, list);
        linearLayoutManager = new GridLayoutManager(this, 2);
        coinRecyclerview.setHasFixedSize(true);
        coinRecyclerview.setLayoutManager(linearLayoutManager);
        coinRecyclerview.setAdapter(coinsRecyclerviewAdapter);

    }

    private void initGetCryptoPrice() {

       /* Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        CryptoPrice cryptoPrice= retrofit.create(CryptoPrice.class);

        List<String> stringList= new ArrayList<>();
        stringList.add("bitcoin");
        stringList.add("name");
        Call<List<CrytoPriceModel>> call= cryptoPrice.getCryptoPrice(stringList);
        call.enqueue(new retrofit2.Callback<List<CrytoPriceModel>>() {
            @Override
            public void onResponse(Call<List<CrytoPriceModel>> call, Response<List<CrytoPriceModel>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Result code:"+response.body(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<CrytoPriceModel> crytoPriceModelList=response.body();



                for (CrytoPriceModel crytoPriceModelList1:crytoPriceModelList){
                    String content = "";
                    content += "ID: " + crytoPriceModelList1.getId() + "\n";
                    content += "User Id: " + crytoPriceModelList1.getName() + "\n";
                    content += "Title: " + crytoPriceModelList1.getPrice_btc() + "\n";
                    content += "Body: " + crytoPriceModelList1.getPrice_usd() + "\n\n";


                    Toast.makeText(MainActivity.this, "The price of bitcoin"+content, Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<List<CrytoPriceModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this, " Onfailure code is runned", Toast.LENGTH_SHORT).show();

            }
        });
*/
        //query array Strings title

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CryptoPrice cryptoPrice= retrofit.create(CryptoPrice.class);

        Call<List<CrytoPriceModel>> call = cryptoPrice.getPost("bitcoin","ripple","litcoin","dogecoin","etherium");
        call.enqueue(new retrofit2.Callback<List<CrytoPriceModel>>() {
            @Override
            public void onResponse(Call<List<CrytoPriceModel>> call, Response<List<CrytoPriceModel>> response) {
                if (!response.isSuccessful()) {
                    currency_earned.setText("Error code:" + response.code());
                    return;
                }


                List<CrytoPriceModel> posts = response.body();
                for (CrytoPriceModel post : posts) {
                    String content = "";
                    content += "Coin name: " + post.getName() + "\n";
                    content += "coin Price: " + post.getPrice_usd() + "\n\n";
                    currency_earned.append(content);


                }

            }

            @Override
            public void onFailure(Call<List<CrytoPriceModel>> call, Throwable t) {
                currency_earned.setText(t.getMessage());

            }
        });



    }

    private void setUpFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                checkCurrentUser(user);
            }
        };

    }

    private void checkCurrentUser(FirebaseUser user) {
        if (user == null) {
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
        }
    }

    private void initAll() {

        mine_coin_spinner = findViewById(R.id.mine_spinner);
        coin_selected = findViewById(R.id.coin_selected);
        replace_layout = findViewById(R.id.replace_layout);
        app_bar_layout = findViewById(R.id.app_bar_layout);
        currency_earned=findViewById(R.id.nameee);


        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.crypto_coin, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mine_coin_spinner.setAdapter(arrayAdapter);
        mine_coin_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mining_coin = parent.getItemAtPosition(position).toString();
                coin_selected.setText(mining_coin);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void initToolbar() {
        mtoolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Mine Cryps");

    }

    private void initDrawerLayout() {
        navigation = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mtoolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        View navView = navigation.inflateHeaderView(R.layout.header_layout);
        header_profile_image=navView.findViewById(R.id.profile_image);
        header_profile_email=navView.findViewById(R.id.profile_email);
        header_profile_name=navView.findViewById(R.id.profile_name);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        closeDrawer();
                        break;
                    case R.id.nav_btc:
                        setFragment(new BitcoinFragment());
                        replace_layout.setAlpha(0);
                        closeDrawer();
                        break;
                    case R.id.nav_eth:
                        setFragment(new EtheriumFragment());
                        replace_layout.setAlpha(0);
                        closeDrawer();
                        break;
                    case R.id.nav_ltc:
                        setFragment(new LitecoinFragment());
                        replace_layout.setAlpha(0);
                        closeDrawer();
                        break;
                    case R.id.nav_doge:
                        setFragment(new DogeCoinFragment());
                        replace_layout.setAlpha(0);
                        closeDrawer();
                        break;
                    case R.id.nav_xrp:
                        setFragment(new RippleFragment());
                        replace_layout.setAlpha(0);
                        closeDrawer();
                        break;
                    case R.id.nav_dash:
                        setFragment(new DashFragment());
                        replace_layout.setAlpha(0);
                        closeDrawer();
                        break;
                    case R.id.nav_mnr:
                        setFragment(new MoneroFragment());
                        replace_layout.setAlpha(0);
                        closeDrawer();
                        break;
                    case R.id.nav_bcn:
                        setFragment(new BytecoinFragment());
                        replace_layout.setAlpha(0);
                        closeDrawer();
                        break;
                }
                return true;
            }
        });


    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    private void addToList() {
        list.add(new CoinsModel("Bitcoin", "0", "0", "23", "BTC", R.drawable.btc));
        list.add(new CoinsModel("Ethereum", "0", "0", "23", "ETH", R.drawable.eth));
        list.add(new CoinsModel("Litecoin", "0", "0", "23", "LTC", R.drawable.ltc));
        list.add(new CoinsModel("Dogecoin", "0", "0", "23", "DOGE", R.drawable.doge));
        list.add(new CoinsModel("Ripple", "0", "0", "23", "RPL", R.drawable.xrp));
        list.add(new CoinsModel("Dash", "0", "0", "23", "DASH", R.drawable.dash));
        list.add(new CoinsModel("Monero", "0", "0", "23", "MNR", R.drawable.xmr));
        list.add(new CoinsModel("Bytecoin", "0", "0", "23", "BCN", R.drawable.bcn));


    }


    public void setFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.settings:
               sendToSettings();
                break;

            case R.id.about:
                final AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setTitle("About us");
                builder.setMessage("Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla Bla");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
                builder.show();
                break;
        }
        return true;
    }

    private void sendToSettings() {
        Intent signOutIntent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(signOutIntent);
    }

    private void sendToAuth() {
        mAuth.signOut();
        Intent signOutIntent = new Intent(MainActivity.this, AuthenticationActivity.class);
        signOutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(signOutIntent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            sendToStart();
        } else {
            checkUserDatabase();
        }
    }

    private void sendToStart() {
        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startIntent);
        finish();
    }

    private void checkUserDatabase() {

        final String current_user_id = mAuth.getCurrentUser().getUid();
        mUsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(current_user_id)) {
                    sendToSetup();
                } else {

                    if (current_user_id != null) {
                        mUsersRef.child(current_user_id).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    if (dataSnapshot.hasChild("fullname")) {
                                        String nav_namee = dataSnapshot.child("fullname").getValue().toString();
                                        header_profile_name.setText(nav_namee);
                                    }
                                    if (dataSnapshot.hasChild("email")) {
                                        String nav_emaill = dataSnapshot.child("email").getValue().toString();
                                        header_profile_email.setText(nav_emaill);
                                    }
                                    if (dataSnapshot.hasChild("image")) {
                                        final String nav_image = dataSnapshot.child("image").getValue().toString();

                                        Picasso.get().load(nav_image).placeholder(R.drawable.person).networkPolicy(NetworkPolicy.OFFLINE).into(header_profile_image, new Callback() {
                                            @Override
                                            public void onSuccess() {
                                            }

                                            @Override
                                            public void onError(Exception e) {
                                                Picasso.get().load(nav_image).into(header_profile_image);
                                            }
                                        });
                                        //Glide.with(MainActivity.this).load(nav_image).into(nav_profile_image);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }

    private void sendToSetup() {
        Intent startIntent = new Intent(MainActivity.this, SetupActivityActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startIntent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            mAuth.removeAuthStateListener(authStateListener);

        }
    }
}
