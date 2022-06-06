package fpoly.edu.vn.qltcda1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import fpoly.edu.vn.qltcda1.LoginActivity;
import fpoly.edu.vn.qltcda1.R;


public class OnboardingFragment3 extends Fragment {

private Button btnGetStart;
private View mView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView= inflater.inflate(R.layout.fragment_onboarding3, container, false);
        btnGetStart=mView.findViewById(R.id.btnGetStart);
        btnGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return mView;
    }
}