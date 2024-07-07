package com.darktech.wallet.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.darktech.wallet.AuthResponse;
import com.darktech.wallet.MainActivity;
import com.darktech.wallet.R;
import com.darktech.wallet.loginModule.view.LoginActivity;
import com.darktech.wallet.splash.view.VerifyPresenter;
import com.darktech.wallet.splash.view.VerifyView;

public class SplashActivity extends AppCompatActivity implements VerifyView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        VerifyPresenter presenter = new VerifyPresenter(this);

        checkToken(presenter);

    }

    private void checkToken(VerifyPresenter presenter) {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("x-token", null);

        if (token != null) {
            presenter.verify(token);
        } else {
            Log.d("Token","No hay token xd");
            redirectToLogin();
        }
    }

    private void proceedToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void redirectToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onVerifySuccess(AuthResponse response) {
        proceedToMainActivity();
    }

    @Override
    public void onVerifyError(String message) {
        redirectToLogin();
    }
}