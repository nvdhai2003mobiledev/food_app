package nvdhai2003.mobiledev.foodapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import nvdhai2003.mobiledev.foodapp.R;
import nvdhai2003.mobiledev.foodapp.ui.activities.MenuActivity;
import nvdhai2003.mobiledev.foodapp.ui.activities.OrdersActivity;


public class HomeFragment extends Fragment {
    private ImageView ivCart, ivMenu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ivCart = view.findViewById(R.id.iv_cart);
        ivMenu = view.findViewById(R.id.iv_menu);
        ivCart.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), OrdersActivity.class));
        });

        ivMenu.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), MenuActivity.class));
        });
        return view;
    }
}