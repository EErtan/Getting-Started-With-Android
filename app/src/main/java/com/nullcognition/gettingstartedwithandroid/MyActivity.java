package com.nullcognition.gettingstartedwithandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity{

	ImageView tape;
	EditText  editTextName;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);

		tapeFadeinAnimation();
		onSoftKeyDone();
	}

	public void onSoftKeyDone(){
		editTextName = (EditText) findViewById(R.id.editTextName);
		editTextName.setOnEditorActionListener(new TextView.OnEditorActionListener(){
			@Override
			public boolean onEditorAction(TextView v,int actionId,KeyEvent event){
				if(actionId == EditorInfo.IME_ACTION_DONE){
					editTextSpinOutAnimation();
				}
				return false;
			}
		});
		// new task
	}

	public void tapeFadeinAnimation(){
		Animation animFadeIn = AnimationUtils.loadAnimation(this,R.anim.fadein);
		tape = (ImageView) findViewById(R.id.imageViewTape);
		tape.setVisibility(View.VISIBLE);
		animFadeIn.setAnimationListener(new Animation.AnimationListener(){
			@Override
			public void onAnimationStart(Animation animation){
				WelcomeToast();
			}

			@Override
			public void onAnimationEnd(Animation animation){
				editTextSlideDownAnimation();
			}

			@Override
			public void onAnimationRepeat(Animation animation){

			}
		});
		tape.startAnimation(animFadeIn);
	}

	public void WelcomeToast(){
		Toast.makeText(this,"Welcome!",Toast.LENGTH_SHORT).show();
	}

	public void editTextSlideDownAnimation(){
		Animation animFadeIn = AnimationUtils.loadAnimation(this,R.anim.slidedown);
		editTextName.setVisibility(View.VISIBLE);
		editTextName.startAnimation(animFadeIn);
		animFadeIn.setAnimationListener(new Animation.AnimationListener(){
			@Override
			public void onAnimationStart(Animation animation){

			}

			@Override
			public void onAnimationEnd(Animation animation){

			}

			@Override
			public void onAnimationRepeat(Animation animation){

			}
		});
	}


	public void editTextSpinOutAnimation(){
		Animation animSpinOut = AnimationUtils.loadAnimation(this,R.anim.spinout);
		animSpinOut.setAnimationListener(new Animation.AnimationListener(){
			@Override
			public void onAnimationStart(Animation animation){
				editTextName.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
				//flag ignore or setHaptic... must be globally set not just per view
				editTextName.setFocusable(false);
			}

			@Override
			public void onAnimationEnd(Animation animation){
				editTextName.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation){

			}
		});
		editTextName.startAnimation(animSpinOut);
	}

}
