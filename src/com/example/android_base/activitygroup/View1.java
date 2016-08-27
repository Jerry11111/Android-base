package com.example.android_base.activitygroup;

import com.example.android_base.R;
import com.example.android_base.R.id;
import com.example.android_base.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class View1 extends Activity {
    
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view1);
        
        editText = (EditText) findViewById(R.id.editText1);
    }
    
    @Override
    protected void onResume() {
        editText.clearFocus();
        super.onResume();
    }
    
}
