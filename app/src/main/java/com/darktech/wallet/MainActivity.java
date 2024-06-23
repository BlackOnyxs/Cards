package com.darktech.wallet;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private Map<Integer, Class<? extends Fragment>> fragmentMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.home_menu, HomeFragment.class);
        fragmentMap.put(R.id.cards_menu, HomeFragment.class);
        fragmentMap.put(R.id.transaction_menu, HomeFragment.class);
        fragmentMap.put(R.id.account_menu, HomeFragment.class);

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_navigation);
        bottomNavigationView.setItemIconTintList(ContextCompat.getColorStateList(this,
                R.color.bottom_navigation_colors));
        bottomNavigationView.setItemTextColor(ContextCompat.getColorStateList(this,
                R.color.bottom_navigation_colors));
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Class<? extends Fragment> fragmentClass = fragmentMap.get(item.getItemId());
        if (fragmentClass != null) {
            try {
                Fragment fragment = fragmentClass.newInstance();
                return loadFragment(fragment);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment == null) {
            return false;
        }

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment != null && currentFragment.getClass().equals(fragment.getClass())) {
            return false;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        return true;
    }
}