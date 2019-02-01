package com.example.dbs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Intent intent;
    Context context=this;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {



            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent=new Intent(context,Main2Activity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    intent=new Intent(context,Main4Activity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    intent=new Intent(context,Main5Activity.class);
                    startActivity(intent);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
