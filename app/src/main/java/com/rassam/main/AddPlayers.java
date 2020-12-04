package com.rassam.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rassam.BilliardEntities.GameType;
import com.rassam.ESnooker.Player;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AddPlayers extends AppCompatActivity  {
    private LinearLayout lyt_Names;
    private EditText edtxt_AddName;
    private ArrayList<Player> players;
    private Button btn_AddPlayer;
    private int index;
    private GameType gameType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);
        lyt_Names = (LinearLayout) findViewById(R.id.lyt_names);
        edtxt_AddName = (EditText) findViewById(R.id.edtxt_AddName);
        btn_AddPlayer = (Button) findViewById(R.id.btn_AddPlayer);
        index = 0;

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            gameType= null;
        } else {
            gameType =(GameType) extras.getSerializable("gameType");
        }


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


    public void addPlayer(View view) {
        // TODO: Add tasks to assign new ids to (game, playerInGame, PlayerTotal)

        String name = edtxt_AddName.getText().toString();
        if (!name.trim().equals("")) {
            TextView newTextView = new TextView(getApplicationContext());
            newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, // Width Of The TextView
                    LinearLayout.LayoutParams.WRAP_CONTENT) // Height Of The TextView
            );
            newTextView.setText(name.trim());
            //players.add(new Player());

            edtxt_AddName.setText("");
            newTextView.setTextSize(40);
            lyt_Names.addView(newTextView, index++);
        } else {
            edtxt_AddName.setText("");
            Toast.makeText(getApplicationContext(),"Please add a name", Toast.LENGTH_SHORT).show();
        }
    }

    public void done(View view) {

        if (gameType == GameType.snooker) {
            Intent intent = new Intent(this, GameSnooker.class);

            startActivityForResult(intent, 2001);
        } else
        {
            Intent intent = new Intent(this, testFile.class);
            startActivityForResult(intent, 2001);
        }
    }
}