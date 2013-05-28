package com.mycugb.app.treehole;

import com.mycugb.app.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/** 
 * @author hendery 1293005282@qq.com
 * @time 2013-5-28 下午8:11:24 
 * 类说明 
 */
public class PageActAdapter extends BaseAdapter
{

	private Context context;
	private String[] names;
	private LayoutInflater inflater;
	public PageActAdapter(Context context,String[] names)
	{
		this.context = context;
		this.names = names;
		inflater = LayoutInflater.from(this.context);
	}
	@Override
	public int getCount()
	{
		return names.length;
	}

	@Override
	public Object getItem(int position)
	{
		return null;
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
			holder = new ViewHolder();
			convertView = (View)inflater.inflate(R.layout.page_act_item, null);
			holder.textView = (TextView) convertView.findViewById(R.id.page_act_name);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setText(names[position]);
		return convertView;
	}
	class ViewHolder
	{
		TextView textView;
	}

}
