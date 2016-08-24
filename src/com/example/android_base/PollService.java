package com.example.android_base;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PollService extends IntentService{
	private static final String TAG = "PollService";

	public PollService(){
		super("PollService");
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// 接受参数
		Bundle bundle = intent.getExtras();
		String param = bundle.getString("param");
		Log.i(TAG, String.format("[Receive intent] [%s]", param));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	}

}
