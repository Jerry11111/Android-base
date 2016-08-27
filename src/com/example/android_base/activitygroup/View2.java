package com.example.android_base.activitygroup;

import java.util.ArrayList;
import java.util.List;

import com.example.android_base.R;
import com.example.android_base.R.id;
import com.example.android_base.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class View2  extends Activity {
    private ListView listView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view2);
        
        listView  = (ListView)findViewById(R.id.item_list);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
    }
    
    private List<String> getData(){
        List<String> data = new ArrayList<String>(26);
        for (int i=0; i<26; i++) {
            data.add("Item " + (char)('A' + i));
        }
        return data;
    }
}
