package com.mycugb.app.utils;

import android.app.Application;

/**
 * Ӧ�ó����Application��洢һЩȫ���ļ�����Դ
 * @time 2013-5-10
 * @author hendery 1293005282@qq.com
 * 
 */
public class MyApplication extends Application
{

	private static MyApplication myApplication;

	@Override
	public void onCreate()
	{
		super.onCreate();
		myApplication = this;
	}


	public static MyApplication getInstance()
	{
		return myApplication;
	}

}
