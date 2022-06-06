package fpoly.edu.vn.qltcda1.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import fpoly.edu.vn.qltcda1.Adapter.OnboardingViewPagerAdapter;
import fpoly.edu.vn.qltcda1.R;
import me.relex.circleindicator.CircleIndicator;

public class OnboardingActivity extends AppCompatActivity {
private TextView tvSkip;
private ViewPager viewPager;
private RelativeLayout layoutBottom;
private CircleIndicator circleIndicator;
private LinearLayout layoutNext;
private OnboardingViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        initUI();
        viewPagerAdapter= new OnboardingViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        circleIndicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
             if (position==2){
                 tvSkip.setVisibility(View.GONE);
                 layoutBottom.setVisibility(View.GONE);
             }
             else{
                 tvSkip.setVisibility(View.VISIBLE);
                 layoutBottom.setVisibility(View.VISIBLE);
             }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private void initUI(){
        tvSkip= findViewById(R.id.tvSkip);
        viewPager=findViewById(R.id.onboarding_viewPager);
        layoutBottom=findViewById(R.id.layout_bottom);
        circleIndicator=findViewById(R.id.circle_indicator);
        layoutNext=findViewById(R.id.layout_next);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });
        layoutNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem()<2){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
            }
        });
    }
}