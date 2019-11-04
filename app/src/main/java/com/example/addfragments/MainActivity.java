package com.example.addfragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {
    private int count;
    public Map<Integer, Fragment> fragmentMap = new HashMap<>();
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    Intent intent;
    //Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        intent = new Intent(this, FragmentsListActivity.class);
        final TextView textView = findViewById(R.id.countFragments);
        final Button btnMin = findViewById(R.id.btnMinus);

        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = Integer.parseInt(textView.getText().toString());
                if (count > 1) {
                    fragmentMap.remove(count);
                    count--;
                }
                if (count == 1) {
                    btnMin.setVisibility(View.GONE);
                }
                textView.setText("" + count);
            }
        });
        Button btnPlus = findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = Integer.parseInt(textView.getText().toString());
                count++;
                String mes = "Create fragment " + count;
                fragmentMap.put(count, new BlankFragment(mes));
                if (count > 1) {
                    btnMin.setVisibility(View.VISIBLE);
                }
                textView.setText("" + count);
                if (intent.hasExtra("map")) {
                    intent.removeExtra("map");
                }
                intent.putExtra("map", (Serializable) fragmentMap);
                fragmentTransaction.add(R.id.linLayout, fragmentMap.get(count));
                fragmentTransaction.addToBackStack("" + count);
                fragmentTransaction.commit();
            }
        });
    }

    public void createFragment(View view) {
        TextView tv = findViewById(R.id.countFragments);
        Integer count = Integer.parseInt(tv.getText().toString());
        String mes = "Create fragment " + count;
        /*
        fragmentTransaction = fragmentManager.beginTransaction();
        if (!fragmentMap.containsKey(count)) {
            fragmentMap.put(count, new BlankFragment(mes));
            fragment = fragmentMap.get(count);
            if (fragmentTransaction == null) {
                fragmentTransaction.add(R.id.linLayout, fragmentMap.get(count));
            } else {
                fragmentTransaction.replace(R.id.linLayout, fragmentMap.get(count));
                fragment = fragmentMap.get(count);
            }
        } else {
            fragmentTransaction.replace(R.id.linLayout, fragmentMap.get(count));
            fragment = fragmentMap.get(count);
        }

        Toast toast = Toast.makeText(this, mes, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 150);
        toast.show();
        fragmentTransaction.addToBackStack(mes);
        fragmentTransaction.commit();
        */
        if (!intent.hasExtra("map")) {
            if (fragmentMap == null) {
                fragmentMap.put(count, new BlankFragment(mes));
            }
            intent.putExtra("map", (Serializable) fragmentMap);
            startActivity(intent);
        }
    }
}
