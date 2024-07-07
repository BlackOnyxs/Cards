package com.darktech.wallet.loginModule.view;

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
import com.darktech.wallet.loginModule.event.LoginRequest;
import com.darktech.wallet.loginModule.event.UserDto;
import com.darktech.wallet.registerModule.view.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{

    EditText etUsername;
    EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LoginPresenter presenter = new LoginPresenter(this);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        Button btnSubmit = findViewById(R.id.btn_submit);
        TextView signUp = findViewById(R.id.tv_register);

        btnSubmit.setOnClickListener(v -> startLogin(presenter));
        signUp.setOnClickListener(this::startRegister);
    }

    private void startLogin(LoginPresenter presenter) {
        LoginRequest request = new LoginRequest(
                etPassword.getText().toString().trim(),
                etUsername.getText().toString().trim()
        );

        presenter.login(request);
    }

    private void startRegister(View view) {
        Intent intent = new Intent(view.getContext(), RegisterActivity.class);
        startActivity(intent);
    }

    private void startMain(){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void toggleProgress() {

    }

    @Override
    public void onLoginSuccess(String token) {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("x-token");
            editor.putString("x-token", token);
            editor.apply();

            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            startMain();
        }catch (Exception e){
            Toast.makeText(this, "Verify your storage", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}