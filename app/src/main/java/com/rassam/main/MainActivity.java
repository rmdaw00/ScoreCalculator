package com.rassam.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.rassam.BilliardEntities.GameType;
import com.rassam.data.Data;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    private Data statisticsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PlayFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_newgame);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Settings()).commit();
                return true;
            case R.id.share:
                int id = item.getItemId();
                if(id == R.id.share){
                    ApplicationInfo api = getApplicationContext().getApplicationInfo();
                    String apkpath = api.sourceDir;
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setDataAndType(Uri.parse(apkpath),
                            "application/vnd.android.package-archive");
                    startActivity(Intent.createChooser(intent, "ShareView"));
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_newgame:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PlayFragment()).commit();
                break;
            case R.id.nav_statistics:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Statistics(), "STAT_FRAGMENT").commit();
                break;
            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new History()).commit();
                break;
            case R.id.nav_discover:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Discover()).commit();
                break;
            case R.id.nav_email:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactUs()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * search result
     * @param view
     */
    public void searchPlayer(View view){

        Statistics statFragment = (Statistics)getSupportFragmentManager().findFragmentByTag("STAT_FRAGMENT");
        if (statFragment != null && statFragment.isVisible()) {

            EditText textPlayerName = statFragment.getView().findViewById(R.id.textPlayerName);
            String playerName = textPlayerName.getText().toString();

            Data data = new Data(this);
            data.loadSample(this);

            TextView textSearchResult = statFragment.getView().findViewById(R.id.textSearchResult);
            textSearchResult.setText(data.getHistory(playerName));
        }
    }

    public void startNew(View view) {
        GameType gameType = null;
        switch(view.getId()) {
            case R.id.M_playPool:
                gameType = GameType.Ball8;
                break;

            case R.id.M_playSnooker:
                gameType = GameType.snooker;
                break;
        }
        Intent intent = new Intent(this, AddPlayers.class);
        intent.putExtra("gameType", gameType);
        startActivityForResult(intent, 2000);

    }
}