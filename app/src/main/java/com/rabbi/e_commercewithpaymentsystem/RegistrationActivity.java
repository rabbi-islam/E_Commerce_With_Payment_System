package com.rabbi.e_commercewithpaymentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rabbi.e_commercewithpaymentsystem.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;
    FirebaseAuth auth;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);



        binding.signInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                finish();
            }
        });

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isValidate();
            }
        });


    }

    private void isValidate() {
        if (TextUtils.isEmpty(binding.nameEdittext.getText().toString())){
            Toast.makeText(this, "name must not be empty", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(binding.emailEdittext.getText().toString())){
            Toast.makeText(this, "email must not be empty", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(binding.passwordEdittext.getText().toString())){
            Toast.makeText(this, "password must not be empty", Toast.LENGTH_SHORT).show();
        }else if (binding.passwordEdittext.getText().toString().trim().length() < 6){
            Toast.makeText(this, "password required least 6 character", Toast.LENGTH_SHORT).show();
        }else{
            registerUser();
        }
    }

    private void registerUser() {
        pd.setTitle("Registering!");
        pd.setMessage("We're creating your account, please wait..");
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        auth.createUserWithEmailAndPassword(binding.emailEdittext.getText().toString(),binding.passwordEdittext.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                            finish();
                            pd.dismiss();
                        }else{
                            Toast.makeText(RegistrationActivity.this, "Registration Failed!"+task.getException(), Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }
                });
    }
}