package com.mycugb.app.classroomsearch;

import com.mycugb.app.R;
import com.mycugb.app.ui.base.HorizontalScrollContainer.OnOpenListener;
import com.mycugb.app.utils.Constant;

import android.R.anim;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * 
* @author hendery 1293005282@qq.com
* @time 2013-5-10
* 教室查询界面
 */
public class ClassRoomSearchLayout extends FrameLayout
{

	private OnOpenListener listener;
	private ImageView gobackImageView;
	private TextView headTextView;
	private ImageView headRightImageView;
	private Spinner buildingSpinner;
	private Spinner weekSpinner;
	private Spinner weekDaySpinner;
	private Spinner timeSpinner;
	private ListView searchResultListView;

	public ClassRoomSearchLayout(Context context)
	{
		super(context);
		setUpView();
		System.out.println("hello");
	}

	private void setUpView()
	{
		LayoutInflater inflater = LayoutInflater.from(getContext());
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.classroom_search_layout, null);
		addView(layout);
		gobackImageView = (ImageView)layout.findViewById(R.id.go_back);
		headTextView = (TextView)layout.findViewById(R.id.head_text);
		headRightImageView = (ImageView)layout.findViewById(R.id.head_right);
		buildingSpinner = (Spinner)layout.findViewById(R.id.classroom_search_building);
		weekSpinner = (Spinner)layout.findViewById(R.id.classroom_search_week);
		weekDaySpinner =(Spinner)layout.findViewById(R.id.classroom_search_weekday);
		timeSpinner = (Spinner)layout.findViewById(R.id.classroom_search_time);
		searchResultListView = (ListView)layout.findViewById(R.id.classroom_search_result_list);
		initView();
		setOnListener();
		
	}
	public void setOnOpenListener(OnOpenListener listener)
	{
		this.listener = listener;
	}
	private void initView()
	{
		headTextView.setText(Constant.CLASSROOM_SREACH_STRING);
		headRightImageView.setImageResource(android.R.drawable.ic_menu_search);
		Resources resources = this.getResources();
		String[] building = resources.getStringArray(R.array.classroom_search_building);
		String[] week = resources.getStringArray(R.array.classroom_search_week);
		String[] weekday = resources.getStringArray(R.array.classroom_search_weekday);
		String[] time = resources.getStringArray(R.array.classroom_search_time);
		
		ArrayAdapter<String> buildingAdapter = new ArrayAdapter<String>(getContext(),
				R.layout.spinner_textview_style,building);
		ArrayAdapter<String> weekAdapter = new ArrayAdapter<String>(getContext(),
				R.layout.spinner_textview_style,week);
		ArrayAdapter<String> weekDayAdapter = new ArrayAdapter<String>(getContext(),
				R.layout.spinner_textview_style,weekday);
		ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(getContext(), 
				R.layout.spinner_textview_style,time);
		
		buildingSpinner.setAdapter(buildingAdapter);
		weekSpinner.setAdapter(weekAdapter);
		weekDaySpinner.setAdapter(weekDayAdapter);
		timeSpinner.setAdapter(timeAdapter);
		
	}
	private void setOnListener()
	{
		gobackImageView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(listener!=null)
				{
					listener.open();
				}
			}
		});
		
	}


}
