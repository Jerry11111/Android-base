package com.example.android_base.activitygroup;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.android_base.R;


/**
 * ActivityGroup作用用来切换不同的activity
 * @author Administrator
 *
 */
public class Group1 extends ActivityGroup {
    private static final String TAG = "Group1";
    
    private LinearLayout container = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group1);
        Log.i(TAG, "onCreate");
        
        container = (LinearLayout) findViewById(R.id.container1);
        
        Button btnView1 = (Button) findViewById(R.id.button1);
        btnView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity("View1", View1.class);
            }
        });
        
        Button btnView2 = (Button) findViewById(R.id.button2);
        btnView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity("View2", View2.class);
            }
        });
        
        launchActivity("View1", View1.class);
    }
    private void launchActivity(String id, Class<?> activityClass) {
        container.removeAllViews();
        
        Intent intent =  new Intent(Group1.this, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        
        Window window = getLocalActivityManager().startActivity(id, intent);
        View view = window.getDecorView();
        container.addView(view);
    }
    
}
