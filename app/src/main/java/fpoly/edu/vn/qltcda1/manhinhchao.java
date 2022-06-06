package fpoly.edu.vn.qltcda1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class manhinhchao extends AppCompatActivity {
    private  static  final  String KEY_FIST_INSTALL="KEY_FIST_INSTALL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchao);
        MySharePreference mySharePreference = new MySharePreference(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mySharePreference.getBooleanValue(KEY_FIST_INSTALL)){
                    startActivity(LoginActivity.class);
                }else{
                    startActivity(OnboardingActivity.class);
                    mySharePreference.putBooleanValue(KEY_FIST_INSTALL,true);
                }

            }
        },3000);
    }
    private void startActivity(Class<?>cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
        finish();
    }
}