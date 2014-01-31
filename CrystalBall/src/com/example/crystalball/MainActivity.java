package com.example.crystalball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private CrystalBall mCrystalBall=new CrystalBall();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Delarando View variables
		final TextView answerlabel = (TextView) findViewById(R.id.textView1);
		Button getAnswerButton = (Button) findViewById(R.id.button1);
		getAnswerButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {				
				String answer = mCrystalBall.getAnswer();
				answerlabel.setText(answer);
				animateCristalBall();
			}
		});	
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
