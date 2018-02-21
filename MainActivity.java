package com.medigain.aswinbaskar.medigain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText name;
    EditText pass;
    Button reg_btn;
    Button lgn_btn;
    private FirebaseAuth mAuth;
    TextView wel_text;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.register_name);
        email = (EditText) findViewById(R.id.register_email);
        pass = (EditText) findViewById(R.id.register_pass);
        lgn_btn = (Button) findViewById(R.id.login_btn);
        reg_btn = (Button) findViewById(R.id.register_btn);
        mAuth = FirebaseAuth.getInstance();

    }

    public void createAccount(View view) {

        String Email = email.getText().toString();
        String Pass = pass.getText().toString();

        mAuth.createUserWithEmailAndPassword(Email, Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("login", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this,"Welcome  " + name.getText().toString(),
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,LoginActivity.class));


                        } else {

                            Log.w("login", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this,task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    public void Login(View view){

        startActivity(new Intent(MainActivity.this,LoginActivity.class));


    }

}
