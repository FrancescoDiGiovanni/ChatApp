package com.example.franc.tdgchat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    public static final String EM = "EMAIL";
    public static final String PA = "PASSWORD";
    private ProgressDialog progressDialog;
    String x = "AUTENTICAZIONE FALLItA";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button login;
    TextView terr,terr1;
    Activity activity = this;
    FirebaseUser utente;
    Button registrati;
    EditText email;
    EditText password;
    TextView dimenticato;
    boolean f = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        login = (Button)findViewById(R.id.button1);
        registrati = (Button)findViewById(R.id.button2);
        email = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        dimenticato = (TextView)findViewById(R.id.textView3);
        utente = mAuth.getCurrentUser();
        if ( utente == null ) {
            registrati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, SecondActivity.class);
                    startActivity(intent);
                }
            });
            dimenticato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, ThirdActivity.class);
                    startActivity(intent);
                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String em = email.getText().toString();
                    String pa = password.getText().toString();
                    progressDialog = ProgressDialog.show(MainActivity.this, "", "Connessione in corso...", true);
                    if (email.getText().length() >= 50 || email.getText().length() < 10) {
                        SecondActivity.controlloNonValido(email);
                        f = true;
                    }
                    else
                        SecondActivity.controlloCorretto(email);
                    if (password.getText().length() >= 17 || password.getText().length() < 5) {
                        SecondActivity.controlloNonValido(password);
                        f = true;
                    }
                    else
                        SecondActivity.controlloCorretto(password);
                    if ( !f ) {
                        mAuth.signInWithEmailAndPassword(em, pa).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    progressDialog.dismiss();
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(MainActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                    Intent intent = new Intent(activity, LoggedInActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, R.string.login_err, Toast.LENGTH_SHORT).show();
                        f = false;
                    }
                }
            });
        } else {
            Intent intent = new Intent(activity,LoggedInActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
