package com.mycugb.app.treehole;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mycugb.app.R;
import com.mycugb.app.ui.MyCugbActivity;
import com.mycugb.app.ui.base.HorizontalScrollContainer.OnOpenListener;
import com.mycugb.app.utils.Constant;

/**
 * 
* @author hendery 1293005282@qq.com
* @time 2013-5-10
* 人人树洞界面
 */
public class TreeHoleLayout extends FrameLayout
{
	private OnOpenListener listener;
	private ImageView gobackImageView;
	private ImageView headRightImageView;//右侧显示留言、刷新的按钮
	private TextView modeTextView;
	private RelativeLayout pageInfo_modeLayout;//切换新鲜事、留言板的布局
	private PopupWindow pageInfoMode;//显示新鲜事、留言板等的弹出菜单
	private PopupWindow pageInfoAct;//右侧操作公共主页的弹出菜单
	private ListView modeListView;//展现新鲜事的ListView
	private ListView actListView;//展现操作的ListView
	private View modeView;
	private View actView;
	private int modeChooseId = 0;
	private MyCugbActivity context;
	
	public TreeHoleLayout(MyCugbActivity context)
	{
		
		super(context);
		this.context = context;
		setUpView();
	}
	private void setUpView()
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.treehole_layout, null);
		addView(layout);
		gobackImageView = (ImageView)layout.findViewById(R.id.go_back);
		modeTextView = (TextView)layout.findViewById(R.id.pageinfo_mode_text);
		headRightImageView = (ImageView)layout.findViewById(R.id.head_right);
		pageInfo_modeLayout = (RelativeLayout)layout.findViewById(R.id.pageinfo_mode_layout);
		
		
		modeView = (View)inflater.inflate(R.layout.page_mode_layout, null);
		actView = (View)inflater.inflate(R.layout.page_act_layout, null);
		
		//全屏显示弹出窗口
		pageInfoMode = new PopupWindow(modeView, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		pageInfoMode.setBackgroundDrawable(new BitmapDrawable());
		pageInfoMode.setFocusable(true);//此行代码解决modeListView中的Item点击无反应的bug
		pageInfoMode.setAnimationStyle(R.style.ModePopupAnimation);
		
		//半屏显示弹出窗口
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels/2;//这行代码必须在上一行之后
		pageInfoAct = new PopupWindow(actView, width, LayoutParams.WRAP_CONTENT,true);
		pageInfoAct.setFocusable(true);
		pageInfoAct.setBackgroundDrawable(new BitmapDrawable());
		pageInfoAct.setAnimationStyle(R.style.ModePopupAnimation);
		
		modeListView = (ListView) modeView.findViewById(R.id.mode_list);
		actListView = (ListView) actView.findViewById(R.id.act_list);
		
		gobackImageView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				listener.open();
			}
		});
		pageInfo_modeLayout.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				initPageInfoMode();
			}
		});
		headRightImageView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				initPageInfoAct();
			}
		});
		
		modeListView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
			{
				modeChooseId = position;
				pageInfoMode.dismiss();
				modeTextView.setText(Constant.PageModeName[position]);
			}
		});
		actListView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				
			}
		});
	
	}
	public void setOnOpenListener(OnOpenListener listener)
	{
		this.listener = listener;
	}
	
	private void initPageInfoMode()
	{
		PageModeAdapter adapter = new PageModeAdapter(context,Constant.PageModeIcon,
				Constant.PageModeName,modeChooseId);
		modeListView.setAdapter(adapter);
		if(pageInfoMode==null)
		{
			pageInfoMode = new PopupWindow(modeView, LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT,true);
			pageInfoMode.setBackgroundDrawable(new BitmapDrawable());
			pageInfoMode.setAnimationStyle(R.style.ModePopupAnimation);
		}
		if(pageInfoMode.isShowing())
		{
			pageInfoMode.dismiss();
		}
		else {
			pageInfoMode.showAsDropDown(pageInfo_modeLayout, 0, 0);//以pageInfo_modelayout为锚展开
		}
	}
	private void initPageInfoAct()
	{
		PageActAdapter adapter = new PageActAdapter(context, Constant.PAGE_ACT_NAMES);
		actListView.setAdapter(adapter);
		if(pageInfoAct==null)
		{
			DisplayMetrics metrics = new DisplayMetrics();
			context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
			int width = metrics.widthPixels/2;
			pageInfoAct = new PopupWindow(actView, width, LayoutParams.WRAP_CONTENT,true);
			pageInfoAct.setBackgroundDrawable(new BitmapDrawable());
			pageInfoAct.setAnimationStyle(R.style.ModePopupAnimation);
		}
		if(pageInfoAct.isShowing())
		{
			pageInfoAct.dismiss();
		}
		else {
			pageInfoAct.showAsDropDown(headRightImageView,0,0);
		}
	}

}
