package com.rassam.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.net.URI;

public class ContactUs extends Fragment {

    private EditText mEditTextto;
    private EditText mEditTextSubject;
    private EditText getmEditTextMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contactus, container, false);
        mEditTextto = (EditText) rootView.findViewById(R.id.edit_text_to);
        mEditTextSubject = (EditText) rootView.findViewById(R.id.edit_text_subject);
        getmEditTextMessage = (EditText) rootView.findViewById(R.id.edit_text_Message);
        Button buttonSend = (Button) rootView.findViewById(R.id.button_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        return rootView;
    }

    private void sendMail() {

        String recp = mEditTextto.getText().toString();
        String[] receps = recp.split(",");
        String subject = mEditTextSubject.getText().toString();
        String message = getmEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, receps);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }
}
