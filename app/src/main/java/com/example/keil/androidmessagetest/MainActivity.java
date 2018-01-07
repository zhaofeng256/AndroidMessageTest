package com.example.keil.androidmessagetest;

import android.provider.Telephony;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
    private String TAG = "AndroidMessageTest";
    private Button mButton;
    private int ButtonCount = 0;
    private Thread thread1;
    private MyThread thread2;
    private class MyRunnable implements Runnable {
    @Override
        public void run() {
            int count = 0;
            while(true) {
                count++;
                Log.d(TAG, "thread1 count = " + count );
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            int count = 0;
            while(true) {
                count++;
                Log.d(TAG, "thread2 count = " + count );
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ButtonCount++;
                Log.i(TAG, "ButtonCount = " + ButtonCount);
            }
        });

        thread1 = new Thread(new MyRunnable(), "THREAD1");
        thread1.start();

        thread2 = new MyThread();
        thread2.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
