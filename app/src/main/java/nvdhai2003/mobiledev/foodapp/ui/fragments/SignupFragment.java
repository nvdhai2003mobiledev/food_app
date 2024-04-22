package nvdhai2003.mobiledev.foodapp.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import nvdhai2003.mobiledev.foodapp.R;

public class SignupFragment extends Fragment {
    private FirebaseAuth mAuth;
    private EditText edtEmail, edtPassword, edtConfirmPassword;
    private TextView tvSignup;
    private CircularProgressIndicator progressBar;
    private ViewPager2 viewPager2;
    private ConstraintLayout btnSignup;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        mAuth = FirebaseAuth.getInstance();
        edtEmail = view.findViewById(R.id.edt_email_res);
        edtPassword = view.findViewById(R.id.edt_password_res);
        edtConfirmPassword = view.findViewById(R.id.edt_confirm_password);
        tvSignup = view.findViewById(R.id.tv_signup);
        progressBar = view.findViewById(R.id.progressBar);
        btnSignup = view.findViewById(R.id.btn_signup);
        viewPager2 = getActivity().findViewById(R.id.viewpager2);
        btnSignup.setOnClickListener(v -> {
            signUpWithEmail(edtEmail.getText().toString(), edtPassword.getText().toString(), edtConfirmPassword.getText().toString());
        });
        return view;
    }

    private void signUpWithEmail(String email, String password, String confirmPassword) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(getContext(), "Please fill the required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidEmail(email)) {
            Toast.makeText(getContext(), "Invalid email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidPassword(password)) {
            Toast.makeText(getContext(), "Password must be at least 8 characters, contain at least one lowercase letter, one uppercase letter, one number and one special character", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!confirmPassword.equals(password)) {
            Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            tvSignup.setVisibility(View.GONE);
                            progressBar.setVisibility(View.VISIBLE);
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(getContext(), "Please check your registered email", Toast.LENGTH_SHORT).show();
                                                viewPager2.setCurrentItem(0);
                                            }
                                        }
                                    });
                        } else {
                            progressBar.setVisibility(View.GONE);
                            tvSignup.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "Sign up failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static boolean isValidPassword(String password) {
        String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return password.matches(passwordPattern);
    }

}