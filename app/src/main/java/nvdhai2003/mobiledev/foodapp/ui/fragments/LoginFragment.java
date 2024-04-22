package nvdhai2003.mobiledev.foodapp.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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

import nvdhai2003.mobiledev.foodapp.R;
import nvdhai2003.mobiledev.foodapp.ui.activities.MainActivity;


public class LoginFragment extends Fragment {
    private EditText edtEmail, edtPassword;
    private TextView tvForgot, tvLogin;
    private ConstraintLayout btnLogin;
    private FirebaseAuth mAuth;
    private CircularProgressIndicator progressBar;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        edtEmail = view.findViewById(R.id.edt_email);
        edtPassword = view.findViewById(R.id.edt_password);
        tvForgot = view.findViewById(R.id.tv_forgot);
        btnLogin = view.findViewById(R.id.btn_login);
        progressBar = view.findViewById(R.id.progressBarLog);
        tvLogin = view.findViewById(R.id.tv_login);
        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(v -> {
            logInWithEmail(edtEmail.getText().toString(), edtPassword.getText().toString());
        });
        return view;
    }

    private void logInWithEmail(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Please fill the required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        tvLogin.setVisibility(View.GONE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), MainActivity.class));
                            getActivity().finish();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            tvLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "Email or password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

