package com.mycugb.app.ui;

import com.mycugb.app.classroomsearch.ClassRoomSearchLayout;
import com.mycugb.app.cugbnews.CugbNewsLayout;
import com.mycugb.app.leftmenu.LeftMenuLayout;
import com.mycugb.app.leftmenu.LeftMenuLayout.OnChangeViewListener;
import com.mycugb.app.scoresearch.ScoreSearchLayout;
import com.mycugb.app.treehole.TreeHoleLayout;
import com.mycugb.app.ui.base.HorizontalScrollContainer.OnOpenListener;
import com.mycugb.app.ui.base.HorizontalScrollContainer;
import com.mycugb.app.user.UserLayout;
import com.mycugb.app.utils.Constant;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;


/** 
* @author hendery 1293005282@qq.com
* @time 2013-5-10
* 应用程序主界面类
*/ 
public class MyCugbActivity extends Activity implements OnOpenListener
{
	private LeftMenuLayout leftMenuLayout;
	// private HorizontalScrollContainer scrollContainer;
	private UserLayout userLayout;
	private HorizontalScrollContainer scrollContainer;
	private TreeHoleLayout treeHoleLayout;
	private CugbNewsLayout cugbNewsLayout;
	private ClassRoomSearchLayout classRoomSearchLayout;
	private ScoreSearchLayout scoreSearchLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		scrollContainer = new HorizontalScrollContainer(MyCugbActivity.this);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		scrollContainer.setLayoutParams(params);

		leftMenuLayout = new LeftMenuLayout(this);
		userLayout = new UserLayout(this);
		treeHoleLayout = new TreeHoleLayout(this);
		cugbNewsLayout = new CugbNewsLayout(this);
		classRoomSearchLayout = new ClassRoomSearchLayout(this);
		scoreSearchLayout = new ScoreSearchLayout(this);

		scrollContainer.addView(leftMenuLayout, 0, params);
		scrollContainer.addView(treeHoleLayout, 1, params);
		setContentView(scrollContainer);
		setOnListener();

	}

	private void setOnListener()
	{
		userLayout.setOnOpenListener(this);
		treeHoleLayout.setOnOpenListener(this);
		cugbNewsLayout.setOnOpenListener(this);
		classRoomSearchLayout.setOnOpenListener(this);
		scoreSearchLayout.setOnOpenListener(this);
		leftMenuLayout.setOnChangeViewListener(new OnChangeViewListener()
		{

			@Override
			public void onChangeView(int itemId)
			{
				switch (itemId)
				{
				case Constant.USER_INFO:
					scrollContainer.close(userLayout);
					break;
				case Constant.SHUDONG:
					scrollContainer.close(treeHoleLayout);
					break;
				case Constant.CUGB_NEWS:
					scrollContainer.close(cugbNewsLayout);
					break;
				case Constant.CLASSROOM_SEARCH:
					scrollContainer.close(classRoomSearchLayout);
					break;
				case Constant.SCORE_SEARCH:
					scrollContainer.close(scoreSearchLayout);
				default:
					break;

				}

			}
		});
	}

	// @Override
	// public void onChangeView(int group, int child)
	// {
	// switch (group)
	// {
	// case Constant.NEWS:
	// {
	// switch (child)
	// {
	// case Constant.SHUDONG:
	// Toast.makeText(getApplicationContext(), "hell", Toast.LENGTH_SHORT).show();
	// scrollContainer.close(treeHoleLayout);
	// break;
	// case Constant.CUGB_NEWS:
	// break;
	//
	// default:
	// break;
	// }
	// }
	// case Constant.TOOLS:
	// {
	// switch (child)
	// {
	// case Constant.CLASSROOM_SEARCH:
	// break;
	// case Constant.SCORE_SEARCH:
	// break;
	// }
	// }
	// case Constant.FUN:
	// {
	// switch (child)
	// {
	//
	// }
	// }
	// }
	// }

	@Override
	public void open()
	{
		if (scrollContainer.getScreenState() == HorizontalScrollContainer.SCREEN_STATE_CLOSE)
			scrollContainer.open();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)// 用户点击返回键
		{
			if (scrollContainer.getScreenState() == HorizontalScrollContainer.SCREEN_STATE_CLOSE)
			{
				scrollContainer.open();// 打开左侧面板
			} else
			{
				notice();// 显示退出提醒
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void notice()
	{
		AlertDialog.Builder builder = new Builder(MyCugbActivity.this);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(Constant.EXIT_NOTICE_TITLE);
		builder.setMessage(Constant.EXIT_NOTICE_MESSAGE);
		builder.setPositiveButton(Constant.EXIT_NOTICE_OK, new DialogInterface.OnClickListener()
		{

			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
				finish();
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(0);
			}
		});
		builder.setNegativeButton(Constant.EXIT_NOTICE_CANCEL,
				new DialogInterface.OnClickListener()
				{

					public void onClick(DialogInterface dialog, int which)
					{
						dialog.cancel();
					}
				});
		builder.create().show();
	}

}
