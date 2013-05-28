package com.mycugb.app.treehole;

import com.mycugb.app.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

/** 
 * @author hendery 1293005282@qq.com
 * @time 2013-5-28 下午8:11:00 
 * 公共主页新鲜事、留言块的adapter
 */
public class PageModeAdapter extends BaseAdapter
{

	private LayoutInflater inflater;
	private int[] icons;
	private String[] names;
	private int chooseId;
	public PageModeAdapter(Context context,int[] icons,String[] names,int chooseId)
	{
		inflater = LayoutInflater.from(context);
		this.icons = icons;
		this.names = names;
		this.chooseId = chooseId;
	}
	@Override
	public int getCount()
	{
		return icons.length;
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
			convertView = (View)inflater.inflate(R.layout.page_mode_item, null);
			holder.item = (CheckBox) convertView.findViewById(R.id.mode_pop_icon);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.item.setButtonDrawable(icons[position]);
		holder.item.setText(names[position]);
		if(position==chooseId)//当前选择的项目显示为蓝色, 
			holder.item.setChecked(true);
		else {
			holder.item.setChecked(false);
		}
		return convertView;
	}
	class ViewHolder
	{
		CheckBox item;
	}

}
