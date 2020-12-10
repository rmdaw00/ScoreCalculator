package com.rassam.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rassam.BilliardEntities.Game;
import com.rassam.BilliardEntities.GameType;
import com.rassam.ESnooker.PlayerInGame;
import com.rassam.ESnooker.PlayerTotal;
import com.rassam.data.Data;

import java.io.Serializable;
import java.util.regex.Pattern;

public class AddPlayers extends AppCompatActivity  {
    private LinearLayout lyt_Names;
    private EditText edtxt_AddName;

    private Game newGame;
    private int playerCount;
    private GameType gameType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);
        lyt_Names = (LinearLayout) findViewById(R.id.lyt_names);
        edtxt_AddName = (EditText) findViewById(R.id.edtxt_AddName);
        playerCount = 0;

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            gameType= null;
        } else {
            gameType =(GameType) extras.getSerializable("gameType");
        }

        Data.loadData(this);
        newGame = Data.addNewGame(this, gameType);


        edtxt_AddName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    if (containsNewLine(charSequence.toString())) addPlayer(edtxt_AddName);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





    }

    static boolean containsNewLine(String str) {
        Pattern regex = Pattern.compile("^(.*)$", Pattern.MULTILINE);
        return regex.split(str).length > 0;
    }

    /*
    * VERY Important note:
    * before working on any Data.Player functions you have to loadPlayers
    *
    * */
    public void addPlayer(View view) {
        // TODO: Add tasks to assign new ids to (game, playerInGame, PlayerTotal)

        String name = edtxt_AddName.getText().toString().trim();
        if (!name.equals("")) {
            if (Data.getPlayersCount() == 0) Data.loadData(this);

            PlayerTotal playerTotal = Data.findPlayerByName(name,gameType);

            PlayerInGame newPlayer;
            if (playerTotal != null) {
                newPlayer = new PlayerInGame(++playerCount,name,playerTotal);
            } else
            {
                playerTotal = Data.addNewPlayerTotal(this,name,gameType);
                newPlayer = new PlayerInGame(++playerCount,name, playerTotal);
            }
            newGame.getPlayers().add(newPlayer);

            //Creating new TextView
                TextView newTextView = new TextView(getApplicationContext());
                newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, // Width Of The TextView
                        LinearLayout.LayoutParams.WRAP_CONTENT) // Height Of The TextView
                );
                newTextView.setText(name);
                edtxt_AddName.setText("");
                newTextView.setTextSize(40);
                lyt_Names.addView(newTextView, playerCount);
        } else {
            edtxt_AddName.setText("");
            Toast.makeText(getApplicationContext(),"Please add a name", Toast.LENGTH_SHORT).show();
        }
    }

    public void done(View view) {

        if (gameType == GameType.snooker) {
            Intent intent = new Intent(this, GameSnooker.class);
             intent.putExtra("game", newGame);
            startActivityForResult(intent, 2001);
        } else
        {
            Intent intent = new Intent(this, testFile.class);
            startActivityForResult(intent, 2001);
        }
    }
}