package com.example.orai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //kai langas bus skurtas, koki vaizdą jam užkrausime
        setContentView(R.layout.activity_login);

        EditText userName = findViewById(R.id.user_name);
        EditText userPassword = findViewById(R.id.user_password);
        Button loginBtn = findViewById(R.id.btn_login);
        Button registerBtn = findViewById(R.id.btn_register);


        //mygtuko paspaudimas
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //klaidų žurnalo išvalymas
                userName.setError(null);
                userPassword.setError(null);
                //aprašomas kodas mygtuko paspaudimui
//                if(Validation.isUserNameValid(String.valueOf(userName.getText().equals(""))) ){
//                    userName.setError(getString(R.string.empty_field_error));
//                }
                if (Validation.isUserNameValid(userName.getText().toString()) && Validation.isUserPasswordValid(userPassword.getText().toString())) {

                    //keliaujam į naują activity login-> main
                    //sukuriamas Intent pereiti iš LoginActivity į MainActivity
                    Intent goToMainActivity = new Intent(LoginActivity.this,MainActivity.class);
                   //inicijuojamas naujas activity
                    startActivity(goToMainActivity);


                    /*//Toast - popup
                    Toast.makeText(getApplicationContext(),
                            "Login: "+ userName.getText() +
                                    "\nPassword: " + userPassword.getText(),
                            Toast.LENGTH_LONG).show();*/


                }else {

                    userName.setError(getResources().getString(R.string.login_error));
                   userName.requestFocus();

                    userPassword.setError(getResources().getString(R.string.login_error));

                    Toast.makeText(getApplicationContext(),R.string.login_error,Toast.LENGTH_LONG).show();
                }


            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(goToRegisterActivity);
            }
        });



    }

}