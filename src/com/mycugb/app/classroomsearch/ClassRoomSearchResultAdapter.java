package com.mycugb.app.classroomsearch;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycugb.app.R;

/** 
 * @author hendery 1293005282@qq.com
 * @time 2013-5-24 上午12:16:58 
 * 类说明 
 */
public class ClassRoomSearchResultAdapter extends BaseAdapter
{

	private List<String> list;
	private Context context;
	private LayoutInflater inflater;
	public ClassRoomSearchResultAdapter(Context context,List<String> list)
	{
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(this.context);
	}
	@Override
	public int getCount()
	{
		return list.size();
	}

	@Override
	public Object getItem(int position)
	{
		return list.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder;
		if(convertView==null)
		{
			convertView = inflater.inflate(R.layout.classroom_search_result_item_layout,null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView.findViewById(R.id.result_image);
			holder.resultTextView = (TextView)convertView.findViewById(R.id.result_classroom);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		return convertView;
	}
	class ViewHolder
	{
		private ImageView imageView;
		private TextView resultTextView;
	}

}
