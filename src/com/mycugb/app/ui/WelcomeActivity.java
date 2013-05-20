package com.mycugb.app.ui;

import com.mycugb.app.R;
import com.mycugb.app.utils.Constant;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
/**
 * 
* @author hendery 1293005282@qq.com
* @time 2013-5-10
* ª∂”≠ΩÁ√Ê
 */
public class WelcomeActivity extends Activity
{

	private Runnable runnable = new Runnable()
	{
		
		@Override
		public void run()
		{
			Intent intent = new Intent();
			intent.setClass(WelcomeActivity.this, WelcomeGuideActivity.class);
			startActivity(intent);
			finish();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		new Handler().postDelayed(runnable,Constant.TIME_DELAY);
	}


}
