package com.example.orai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText userNameRegistration = findViewById(R.id.register_form_user_name);
        EditText userPasswordRegistration = findViewById(R.id.register_form_password);
        EditText userPasswordRepeat = findViewById(R.id.register_form_repeat_password);
        EditText userEmailRegistration = findViewById(R.id.register_form_user_email);
        Button submitRegistrationBtn = findViewById(R.id.register_form_btn);

        submitRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameRegistration.setError(null);
                userPasswordRegistration.setError(null);
                userPasswordRepeat.setError(null);
                userEmailRegistration.setError(null);


                if (Validation.isUserRegistrationNameValid(userNameRegistration.getText().toString())
                        && Validation.isUserRegistrationEmailValid(userEmailRegistration.getText().toString())
                        && Validation.isUserRegistrationPasswordValid(userPasswordRegistration.getText().toString())
                        && userPasswordRegistration.getText().toString().equals(userPasswordRepeat.getText().toString())) {

                    Intent goToLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                    Toast.makeText(getApplicationContext(), getText(R.string.success_registration), Toast.LENGTH_LONG).show();
                    startActivity(goToLoginActivity);

                }  else if (!Validation.isUserRegistrationNameValid(userNameRegistration.getText().toString())) {
                    userNameRegistration.setError(getApplicationContext().getText(R.string.registration_user_name_error));
                    userNameRegistration.requestFocus();

                } else if (!Validation.isUserRegistrationEmailValid(userEmailRegistration.getText().toString())) {
                    userEmailRegistration.setError(getApplicationContext().getText(R.string.registration_email_error));
                    userEmailRegistration.requestFocus();

                } else if (!Validation.isUserRegistrationPasswordValid(userPasswordRegistration.getText().toString())) {
                    userPasswordRegistration.setError(getApplicationContext().getText(R.string.registration_password_error));
                    userPasswordRegistration.requestFocus();

                } else if (!userPasswordRegistration.getText().toString().equals(userPasswordRepeat.getText().toString())) {
                    userPasswordRepeat.setError(getApplicationContext().getText(R.string.registration_password_repeat_error));
                    userPasswordRepeat.requestFocus();

                }


            }
        });


    }
}