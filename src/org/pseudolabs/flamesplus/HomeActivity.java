package org.pseudolabs.flamesplus;

import java.util.Timer;
import com.apperhand.device.android.AndroidSDKProvider;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;

public class HomeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AndroidSDKProvider.initSDK(this,false);
        
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.rll);
       
        rl.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
	            HomeActivity.this.finish();
	            startActivity(myIntent);
	            
				return false;
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
}
