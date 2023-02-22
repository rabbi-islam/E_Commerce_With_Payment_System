package com.rabbi.e_commercewithpaymentsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rabbi.e_commercewithpaymentsystem.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

   ActivityLoginBinding binding;
    FirebaseAuth auth;
    ProgressDialog pd;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        pd.setTitle("Already logged In!");
        pd.setCanceledOnTouchOutside(false);



                if (auth.getCurrentUser() != null){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                    pd.dismiss();
                }


        sharedPreferences = getSharedPreferences("onBindingScree",MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);

        if (isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();
            startActivity(new Intent(LoginActivity.this,OnBoardActivity.class));
            finish();
        }



        binding.signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                finish();
            }
        });

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isValidate();
            }
        });
    }

    private void isValidate() {
        if (TextUtils.isEmpty(binding.emailEdittext.getText().toString())){
            Toast.makeText(this, "email must not be empty", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(binding.passwordEdittext.getText().toString())){
            Toast.makeText(this, "password must not be empty", Toast.LENGTH_SHORT).show();
        }else{
            loginUser();
        }
    }

    private void loginUser() {
        pd.setTitle("Login!");
        pd.setMessage("We're matching your credential, please wait..");
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        auth.signInWithEmailAndPassword(binding.emailEdittext.getText().toString(),binding.passwordEdittext.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                            pd.dismiss();
                        }else{
                            Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    }
                });
    }
}