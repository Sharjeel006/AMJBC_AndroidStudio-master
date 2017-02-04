package com.local.amjbc;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class Splash extends Activity implements SensorEventListener {

	private static final int SPLASH_DURATION = 6500;
    private Handler startMain;
    private boolean isBackButtonPressed;
    private TextView tv, tv2, hangoutTvOne, hangoutTvTwo, hangoutTvThree;
	private ObjectAnimator waveOneAnimator, waveTwoAnimator, waveThreeAnimator;
    Animation animFadein;
    MediaPlayer mp;
    Location lm;
    String emailid;

	private GifImageView gifImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		AccountManager am = AccountManager.get(this);
		Account[] accounts = am.getAccounts();
		for (Account account : accounts) {
		    if (account.type.compareTo("com.google") == 0)
		    {
		        emailid = account.name;
		        break;
		    }             
		}

		setContentView(R.layout.splash_2);

		gifImageView = (GifImageView)findViewById(R.id.gifImageView);

		try
		{
			InputStream inputStream = getAssets().open("giphy4.gif");
			byte[] bytes = IOUtils.toByteArray(inputStream);

			gifImageView.setBytes(bytes);
			gifImageView.startAnimation();

		}
		catch (IOException ex)
		{

		}


		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Splash.this.startActivity(new Intent(Splash.this, MainActivity.class));
				Splash.this.finish();
			}
		}, SPLASH_DURATION);



	    }
	
	public void waveAnimation() {
		PropertyValuesHolder tvOne_Y = PropertyValuesHolder.ofFloat(hangoutTvOne.TRANSLATION_Y, -40.0f);
		PropertyValuesHolder tvOne_X = PropertyValuesHolder.ofFloat(hangoutTvOne.TRANSLATION_X, 0);
		waveOneAnimator = ObjectAnimator.ofPropertyValuesHolder(hangoutTvOne, tvOne_X, tvOne_Y);
		waveOneAnimator.setRepeatCount(-1);
		waveOneAnimator.setRepeatMode(ValueAnimator.REVERSE);
		waveOneAnimator.setDuration(300);
		waveOneAnimator.start();

		PropertyValuesHolder tvTwo_Y = PropertyValuesHolder.ofFloat(hangoutTvTwo.TRANSLATION_Y, -40.0f);
		PropertyValuesHolder tvTwo_X = PropertyValuesHolder.ofFloat(hangoutTvTwo.TRANSLATION_X, 0);
		waveTwoAnimator = ObjectAnimator.ofPropertyValuesHolder(hangoutTvTwo, tvTwo_X, tvTwo_Y);
		waveTwoAnimator.setRepeatCount(-1);
		waveTwoAnimator.setRepeatMode(ValueAnimator.REVERSE);
		waveTwoAnimator.setDuration(300);
		waveTwoAnimator.setStartDelay(100);
		waveTwoAnimator.start();

		PropertyValuesHolder tvThree_Y = PropertyValuesHolder.ofFloat(hangoutTvThree.TRANSLATION_Y, -40.0f);
		PropertyValuesHolder tvThree_X = PropertyValuesHolder.ofFloat(hangoutTvThree.TRANSLATION_X, 0);
		waveThreeAnimator = ObjectAnimator.ofPropertyValuesHolder(hangoutTvThree, tvThree_X, tvThree_Y);
		waveThreeAnimator.setRepeatCount(-1);
		waveThreeAnimator.setRepeatMode(ValueAnimator.REVERSE);
		waveThreeAnimator.setDuration(300);
		waveThreeAnimator.setStartDelay(200);
		waveThreeAnimator.start();
	}

	@Override
	    public void onBackPressed() {
	        isBackButtonPressed = true;
	        super.onBackPressed();
	    }

	 
	   @Override
	   public void onDestroy() {
	   	   
	   	super.onDestroy();
	   }
	   
	@Override
	public void onSensorChanged(SensorEvent event) {
		
		
	}

	
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
		
}



