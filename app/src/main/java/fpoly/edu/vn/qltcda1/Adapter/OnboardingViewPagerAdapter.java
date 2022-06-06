package fpoly.edu.vn.qltcda1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fpoly.edu.vn.qltcda1.fragment.OnboardingFragment1;
import fpoly.edu.vn.qltcda1.fragment.OnboardingFragment2;
import fpoly.edu.vn.qltcda1.fragment.OnboardingFragment3;

public class OnboardingViewPagerAdapter extends FragmentStatePagerAdapter {

    public OnboardingViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return  new OnboardingFragment1();

            case 1 : return  new OnboardingFragment2();

            case 2 : return  new OnboardingFragment3();

            default: return  new OnboardingFragment1();

        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
