package com.example.franc.tdgchat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

/**
 * Created by franc on 27/01/2017.
 */

public class SecondActivity extends Activity {
    private static final String TAG = "SecondActivity";
    Activity activity = this;
    Intent intent;
    Button b1;
    boolean f = false;
    FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    TextView terr1;
    TextView terr2;
    TextView terr3;
    TextView terr4;
    TextView terr5;
    EditText t1;
    EditText t2;
    EditText t3;
    EditText t4;
    EditText t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        intent = getIntent();
        b1 = (Button) findViewById(R.id.button1);
        t1 = (EditText) findViewById(R.id.editText1);
        t2 = (EditText) findViewById(R.id.editText2);
        t3 = (EditText) findViewById(R.id.editText3);
        t4 = (EditText) findViewById(R.id.editText4);
        t5 = (EditText) findViewById(R.id.editText5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(SecondActivity.this, "", "Registrazione in corso...", true);
                if (t1.getText().length() >= 30 || t1.getText().length() < 5) {
                    controlloNonValido(t1);
                    f = true;
                }
                else
                    controlloCorretto(t1);
                if ( t2.getText().length() >= 50 || t2.getText().length() < 10 ) {
                    controlloNonValido(t2);
                    f = true;
                }
                else
                    controlloCorretto(t2);
                if ( t3.getText().length() >= 17 || t3.getText().length() < 5 ) {
                    controlloNonValido(t3);
                    f = true;
                }
                else
                    controlloCorretto(t3);
                if ( !((t4.getText().toString()).equals(t3.getText().toString())) ) {
                    controlloNonValido(t4);
                    f = true;
                }
                else
                    controlloCorretto(t4);
                if ( t5.getText().length() == 10) {
                    try {
                        Long.parseLong(t5.getText().toString());
                        controlloCorretto(t5);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        controlloNonValido(t5);
                        f = true;
                    }
                }
                else {
                    controlloNonValido(t5);
                    f = true;
                }
            if ( !f ) {
                String em = t2.getText().toString();
                String pa = t3.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(em, pa).addOnCompleteListener(SecondActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Toast.makeText(SecondActivity.this, R.string.reg_fail, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        } else {
                            mAuth.getInstance().signOut();
                            Intent intent = new Intent(activity,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            } else {
                progressDialog.dismiss();
                Toast.makeText(SecondActivity.this, R.string.reg_err, Toast.LENGTH_SHORT).show();
                f = false;
            }
            }
        });

    }
    public static void controlloNonValido(TextView tw) {
        tw.setTextColor(Color.RED);
    }
    public static void controlloCorretto(TextView tw) {
        tw.setTextColor(Color.GREEN);
    }
}
