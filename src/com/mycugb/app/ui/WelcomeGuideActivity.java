package com.mycugb.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycugb.app.R;
import com.mycugb.app.utils.Constant;

/** 
 * @author hendery 1293005282@qq.com
 * @time 2013-5-20 下午9:53:56 
 * 类说明 
 */
public class WelcomeGuideActivity extends Activity implements OnClickListener
{
	private ImageView guidePictureImageView;//引导页面不断切换的图片
	private TextView guideTextView;//引导页面的文字
	private Button enterHomeButton;//点击进入主界面按钮
	
	private Drawable firstDrawable;//三张切换的图片
	private Drawable secondDrawable;
	private Drawable thirdDrawable;
	
	private Animation fadeIn;//动画进入
	private Animation fadeScale;//动画放大
	private Animation fadeOut;//动画消失

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_guide);
		init();
	}
	private void init()
	{
		guidePictureImageView = (ImageView)findViewById(R.id.guide_picture);
		guideTextView = (TextView)findViewById(R.id.guide_content);
		enterHomeButton = (Button)findViewById(R.id.enter_home);
		enterHomeButton.setOnClickListener(this);
		initDrawable();
		initAnimation();
		guidePictureImageView.setImageDrawable(firstDrawable);
		guideTextView.setText(Constant.FIRST_GUIDE_STRING);
		guidePictureImageView.startAnimation(fadeIn);
		loadAnimation();
	}
	/*
	 * 初始化三个动画
	 */
	private void initAnimation()
	{
		fadeIn = AnimationUtils.loadAnimation(WelcomeGuideActivity.this, R.anim.guide_welcome_fade_in);
		fadeIn.setDuration(1000);//设置1秒的时间
		fadeScale = AnimationUtils.loadAnimation(WelcomeGuideActivity.this, R.anim.guide_welcome_fade_in_scale);
		fadeScale.setDuration(5000);
		fadeOut = AnimationUtils.loadAnimation(WelcomeGuideActivity.this, R.anim.guide_welcome_fade_out);
		fadeOut.setDuration(1000);
	}
	/*
	 * 初始化三张图片
	 */
	private void initDrawable()
	{
		firstDrawable = getResources().getDrawable(R.drawable.guide_first);
		secondDrawable = getResources().getDrawable(R.drawable.guide_second);
		thirdDrawable = getResources().getDrawable(R.drawable.guide_third);
	}
	private void loadAnimation()
	{
		fadeIn.setAnimationListener(new AnimationListener()
		{
			
			@Override
			public void onAnimationStart(Animation animation)
			{
				
			}
			@Override
			public void onAnimationRepeat(Animation animation)
			{
				
			}
			@Override
			public void onAnimationEnd(Animation animation)
			{
				guidePictureImageView.startAnimation(fadeScale);
			}
		});
		fadeScale.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation)
			{
				
			}
			@Override
			public void onAnimationRepeat(Animation animation)
			{
				
			}
			@Override
			public void onAnimationEnd(Animation animation)
			{
				guidePictureImageView.startAnimation(fadeOut);
			}
		});
		fadeOut.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation)
			{
				
			}
			@Override
			public void onAnimationRepeat(Animation animation)
			{
				
			}
			@Override
			public void onAnimationEnd(Animation animation)
			{
				if(guidePictureImageView.getDrawable().equals(firstDrawable))
				{
					guideTextView.setText(Constant.SECONDE_GUIDE_STRING);
					guidePictureImageView.setImageDrawable(secondDrawable);//切入第二张图片
				}
				else if(guidePictureImageView.getDrawable().equals(secondDrawable))
				{
					guideTextView.setText(Constant.THIRD_GUIDE_STRING);
					guidePictureImageView.setImageDrawable(thirdDrawable);
				}
				else if (guidePictureImageView.getDrawable().equals(thirdDrawable))
				{
					guideTextView.setText(Constant.FIRST_GUIDE_STRING);
					guidePictureImageView.setImageDrawable(firstDrawable);//载入第一张图片从而达到循环的效果
				}
				guidePictureImageView.startAnimation(fadeIn);
			}
		});
		
	}
	@Override
	public void onClick(View v)
	{
		Intent intent = new Intent();
		switch (v.getId())
		{
		case R.id.enter_home:
			intent.setClass(WelcomeGuideActivity.this, MyCugbActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}

}
