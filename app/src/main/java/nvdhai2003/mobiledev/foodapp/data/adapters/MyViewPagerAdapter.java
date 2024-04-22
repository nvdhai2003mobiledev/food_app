package nvdhai2003.mobiledev.foodapp.data.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nvdhai2003.mobiledev.foodapp.ui.fragments.LoginFragment;
import nvdhai2003.mobiledev.foodapp.ui.fragments.SignupFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LoginFragment();
            case 1:
                return new SignupFragment();
            default:
                return new LoginFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
