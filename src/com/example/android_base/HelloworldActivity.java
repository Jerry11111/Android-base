package com.example.android_base;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HelloworldActivity extends Activity {
	private static final String TAG = "HelloworldActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Activity activity = this;
		SharedPreferences sharedPref = activity
				.getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt("key1", 1);
		editor.commit();
		int rv = sharedPref.getInt("key1", 0);
		Log.i(TAG, String.format("[SharedPreferences] [%s]", rv));
		getSys();
		bluetooth();
		// toast msg
		Button toastBtn = (Button)findViewById(R.id.false_button);
		toastBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Toast.makeText(HelloworldActivity.this, "Hello World!", Toast.LENGTH_SHORT).show();
			}
		});
		Button dialogBtn = (Button)findViewById(R.id.btn_diaNormal);
		dialogBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
			    //AlertDialog.Builder normalDialog=new AlertDialog.Builder(getApplicationContext());  
		        AlertDialog.Builder normalDia=new AlertDialog.Builder(HelloworldActivity.this);  
		        normalDia.setIcon(R.drawable.ic_launcher);  
		        normalDia.setTitle("普通的对话框");  
		        normalDia.setMessage("普通对话框的message内容");  
		          
		        normalDia.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
		            @Override  
		            public void onClick(DialogInterface dialog, int which) {  
		                Toast.makeText(HelloworldActivity.this, "你选择的是: "+"确定", Toast.LENGTH_SHORT).show();  
		            }  
		        });  
		        normalDia.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
		            @Override  
		            public void onClick(DialogInterface dialog, int which) {  
		            	Toast.makeText(HelloworldActivity.this, "你选择的是: " + "取消", Toast.LENGTH_SHORT).show();  
		            }  
		        });  
		        normalDia.create().show();  
			}
		});
	}
	
	
	//必须要配置 <uses-permission android:name="android.permission.READ_PHONE_STATE" />
	public  void getSys(){
		TelephonyManager mTm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String imei = mTm.getDeviceId();
		String imsi = mTm.getSubscriberId();
		String model = android.os.Build.MODEL; // 手机型号
		String release = android.os.Build.VERSION.RELEASE;
		@SuppressWarnings("deprecation")
		String sdk = android.os.Build.VERSION.SDK;
		String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
		Log.d(TAG, String.format("[%s %s %s %s] [%s %s]", imei, imsi, model, numer, release, sdk));
	}
	
	public void bluetooth(){
		BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		if(adapter != null){
			if(!adapter.isEnabled()){
				// 打开蓝牙
				Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivity(intent);
			}
			// 所有已经配对的蓝牙适配器对象
			Set<BluetoothDevice> devices = adapter.getBondedDevices();
			if(devices.size()>0){
				for(Iterator<BluetoothDevice> iterator = devices.iterator();iterator.hasNext();){
					BluetoothDevice device = iterator.next();
					Log.d(TAG,String.format("[%s %s]", device.getName(), device.getAddress()));
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Intent i = new Intent(this, PollService.class);
		// 携带参数
		Bundle bundle = new Bundle();  
	    bundle.putString("param", "oper1");  
	    i.putExtras(bundle);
		this.startService(i);
		try{
			Thread.sleep(TimeUnit.SECONDS.toMillis(10));
		}catch(Exception e){
			e.printStackTrace();
		}
		this.startService(i);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
