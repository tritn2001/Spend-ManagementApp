package fpoly.edu.vn.qltcda1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import fpoly.edu.vn.qltcda1.LoginActivity;
import fpoly.edu.vn.qltcda1.R;
import fpoly.edu.vn.qltcda1.dangkiActivity;


public class OnboardingFragment1 extends Fragment {
private Button btnDK;
private TextView tvlogin;
private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_onboarding1, container, false);
        btnDK=view.findViewById(R.id.onboarding_dang_ky);
        tvlogin=view.findViewById(R.id.onboarding_dang_nhap);
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), dangkiActivity.class);
                getActivity().startActivity(intent);
            }
        });
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}