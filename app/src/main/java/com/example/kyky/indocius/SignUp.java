package com.example.kyky.indocius;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

 EditText a,b ;
 Button daftar;
private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        a = (EditText) findViewById(R.id.email);
        b = (EditText) findViewById(R.id.password);
        daftar = (Button) findViewById(R.id.button);

    daftar.setOnClickListener(this);
    firebaseAuth = FirebaseAuth.getInstance();



    }

    private void registerUser (){
        String email = a.getText().toString().trim();
        String password = b.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            // email is empty
            Toast.makeText(this,"masukkan email anda", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            // password is empty
            Toast.makeText(this,"masukkan password anda", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "Berhasil daftar", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SignUp.this, "Tidak dapat mendaftar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View view) {
    if (view == daftar){
        registerUser();

    }
    }
}
