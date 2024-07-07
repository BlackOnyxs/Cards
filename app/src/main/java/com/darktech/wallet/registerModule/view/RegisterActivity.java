package com.darktech.wallet.registerModule.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.darktech.wallet.MainActivity;
import com.darktech.wallet.R;
import com.darktech.wallet.loginModule.event.UserDto;
import com.darktech.wallet.loginModule.view.LoginActivity;
import com.darktech.wallet.registerModule.RegisterPresenter;
import com.darktech.wallet.registerModule.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    EditText etFullName;
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RegisterPresenter presenter = new RegisterPresenter(this);

        etFullName = findViewById(R.id.et_fullName);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);


        Button btnSubmit = findViewById(R.id.btn_submit);
        TextView tvLogin = findViewById(R.id.tv_login);

        btnSubmit.setOnClickListener(v -> startRegistration(presenter));
        tvLogin.setOnClickListener(this::startLogin);
    }

    private void startRegistration(RegisterPresenter presenter) {
        UserDto user = new UserDto(
                etUsername.getText().toString().trim(),
                etPassword.getText().toString().trim(),
                etFullName.getText().toString().trim()
        );
        System.out.print(etFullName.getText().toString().trim()+
                etUsername.getText().toString().trim()+
                etPassword.getText().toString().trim());
        presenter.register(user);
    }
    private void startLogin(View view) {
        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void startMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRegisterSuccess(String token) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("x-token");
            editor.putString("x-token", token);
            editor.apply();

            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            startMain(getBaseContext());
        }catch (Exception e){
            Toast.makeText(this, "Verify your storage", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRegisterError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}