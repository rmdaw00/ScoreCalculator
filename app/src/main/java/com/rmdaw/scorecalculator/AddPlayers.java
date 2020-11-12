package com.rmdaw.scorecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AddPlayers extends AppCompatActivity  {
    LinearLayout lyt_Names;
    EditText edtxt_AddName;
    Button btn_AddPlayer;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);
        lyt_Names = (LinearLayout) findViewById(R.id.lyt_names);
        edtxt_AddName = (EditText) findViewById(R.id.edtxt_AddName);
        btn_AddPlayer = (Button) findViewById(R.id.btn_AddPlayer);
        index = 0;
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
        String name = edtxt_AddName.getText().toString();
        if (!name.trim().equals("")) {
            TextView newTextView = new TextView(getApplicationContext());
            newTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, // Width Of The TextView
                    LinearLayout.LayoutParams.WRAP_CONTENT) // Height Of The TextView
            );
            newTextView.setText(name.trim());


            edtxt_AddName.setText("");
            newTextView.setTextSize(40);
            lyt_Names.addView(newTextView, index++);
        } else {
            edtxt_AddName.setText("");
            Toast.makeText(getApplicationContext(),"Please add a name", Toast.LENGTH_SHORT).show();
        }
    }
}