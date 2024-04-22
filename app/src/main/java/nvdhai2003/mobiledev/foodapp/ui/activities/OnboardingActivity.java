package nvdhai2003.mobiledev.foodapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

import nvdhai2003.mobiledev.foodapp.R;
import nvdhai2003.mobiledev.foodapp.databinding.ActivityOnboardingBinding;

public class OnboardingActivity extends BaseActivity {
    ActivityOnboardingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnStart.setOnClickListener(v -> {
            mAuth = FirebaseAuth.getInstance();
            user = mAuth.getCurrentUser();
            if (user != null) {
                startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(OnboardingActivity.this, LoginActivity.class));
                finish();
            }
        });

    }
}