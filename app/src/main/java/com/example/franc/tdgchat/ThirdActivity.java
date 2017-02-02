package com.example.franc.tdgchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.button;

/**
 * Created by franc on 27/01/2017.
 */

public class ThirdActivity extends Activity {
    Intent intent;
    Button b1;
    FirebaseDatabase database;
    EditText t1;
    EditText t2;
    TextView terr1, terr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        intent = getIntent();
        b1 = (Button)findViewById(R.id.button1);
        t1 = (EditText)findViewById(R.id.editText1);
        t2 = (EditText)findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( t1 .getText().length() >= 50 || t1.getText().length() < 10 )
                    SecondActivity.controlloNonValido(t1);
                else
                    SecondActivity.controlloCorretto(t1);
                if ( t2.getText().length() == 10) {
                    try {
                        Long.parseLong(t2.getText().toString());
                        SecondActivity.controlloCorretto(t2);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        SecondActivity.controlloNonValido(t2);
                    }
                }
                else
                    SecondActivity.controlloNonValido(t2);
            }
        });


    }

}
