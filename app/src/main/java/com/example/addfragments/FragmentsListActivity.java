package com.example.addfragments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

class FragmentsListActivity extends FragmentActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragments);
        Intent intent = getIntent();
        Map<Integer, Fragment> fragmentMap = (Map<Integer, Fragment>) intent.getSerializableExtra("map");

        ListView lv = findViewById(R.id.lv);
        for (Integer key: fragmentMap.keySet()) {
            lv.addFooterView(fragmentMap.get(key).getView());
        }
    }
}
