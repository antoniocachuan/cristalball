package com.antoniocachuan.esferadelfuturo;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antoniocachuan.esferadelfuturo.R;
import com.antoniocachuan.esferadelfuturo.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {
	public static final String TAG = MainActivity.class.getSimpleName();//para darle la clase que estamos al log
	
	private CrystalBall mCrystalBall=new CrystalBall();
	private SensorManager mSensorManager;
	private Sensor mAccelometer;
	private ShakeDetector mShakeDetector;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Declarando View variables
		final TextView answerlabel = (TextView) findViewById(R.id.textView1);
		
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mShakeDetector = new ShakeDetector(new OnShakeListener() {
			
			@Override
			public void onShake() {
				// TODO Auto-generated method stub
				handleNewAnswer(answerlabel);
			}
		});
		
		//mSensorManager.registerListener(mShakeDetector, mAccelometer, SensorManager.SENSOR_DELAY_UI);
		
		Button getAnswerButton = (Button) findViewById(R.id.button1);
		getAnswerButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {				
				handleNewAnswer(answerlabel);
			}
		});
		
		Toast welcomeToast = Toast.makeText(this, "Bienvenidos =) \n Descubre tu futuro de manera divertida...", Toast.LENGTH_LONG);
		welcomeToast.setGravity(Gravity.TOP, 0, 0);//ubicacion del mensaje en la pantalla
		welcomeToast.show();
		
		Log.d(TAG, "Estoy en el log desde onCreate()");

	}
	
	@Override
	public void onResume(){
		super.onResume();
		mSensorManager.registerListener(mShakeDetector, mAccelometer, SensorManager.SENSOR_DELAY_UI);
		
	}
	
	@Override
	public void onPause(){
		super.onPause();
		mSensorManager.unregisterListener(mShakeDetector);
	}
	
	private void animateCristalBall(){
		ImageView crystallBallImage =(ImageView) findViewById(R.id.imageView1);
		crystallBallImage.setImageResource(R.drawable.ball_animation);
		AnimationDrawable ballAnimation = (AnimationDrawable) crystallBallImage.getDrawable();
		if(ballAnimation.isRunning()){
			ballAnimation.stop();
		}
		ballAnimation.start();
	}
	
	private void playSound(){
		MediaPlayer	player = MediaPlayer.create(this, R.raw.crystal_ball);
		player.start();
		player.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mp.release();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void handleNewAnswer(final TextView answerlabel) {
		String answer = mCrystalBall.getAnswer();
		playSound();
		animateCristalBall();
		answerlabel.setText(answer);
	}
}
