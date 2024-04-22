package nvdhai2003.mobiledev.foodapp.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import nvdhai2003.mobiledev.foodapp.R;
import nvdhai2003.mobiledev.foodapp.databinding.ActivityMainBinding;
import nvdhai2003.mobiledev.foodapp.ui.fragments.FavoriteFragment;
import nvdhai2003.mobiledev.foodapp.ui.fragments.HomeFragment;
import nvdhai2003.mobiledev.foodapp.ui.fragments.NotifyFragment;
import nvdhai2003.mobiledev.foodapp.ui.fragments.UserFragment;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    private HomeFragment homeFragment = new HomeFragment();
    private FavoriteFragment favoriteFragment = new FavoriteFragment();
    private UserFragment userFragment = new UserFragment();
    private NotifyFragment notifyFragment = new NotifyFragment();
    private long backPressedTime;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        replaceFragment(homeFragment);

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.nav_home) {
                replaceFragment(homeFragment);
                return true;
            } else if (item.getItemId() == R.id.nav_favorite) {
                replaceFragment(favoriteFragment);
                return true;
            } else if (item.getItemId() == R.id.nav_user) {
                replaceFragment(userFragment);
                return true;
            } else if (item.getItemId() == R.id.nav_notify) {
                replaceFragment(notifyFragment);
                return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }


        private void replaceFragment (Fragment fragment){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frame_layout, fragment);
            transaction.commit();
        }

    }

