package com.dicoding.android.fundamental.githubuserapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.dicoding.android.fundamental.githubuserapp.R;
import com.dicoding.android.fundamental.githubuserapp.Receiver.MyReceiver;

import java.util.Calendar;

public class RemainderActivity extends AppCompatActivity {

    private SharedPreferences sharedpref;
    private Switch newswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);

        getSupportActionBar().setTitle(R.string.con_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedpref = PreferenceManager.getDefaultSharedPreferences(this);
        int dailynotif = sharedpref.getInt("user_notification", 0);
        newswitch = findViewById(R.id.newswitch);
        if (dailynotif == 1){
            newswitch.setChecked(true);
        }
        else {
            newswitch.setChecked(false);
        }
        onClickSwitch();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickSwitch(){
        newswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setReminder(getApplicationContext());
                    SharedPreferences.Editor editor = sharedpref.edit();
                    editor.putInt("user_notification",1);
                    editor.commit();
                    Toast.makeText(RemainderActivity.this, R.string.active, Toast.LENGTH_SHORT)
                            .show();
                }
                else {
                    setReminderOff(getApplicationContext());
                    SharedPreferences.Editor editor = sharedpref.edit();
                    editor.putInt("user_notification",0);
                    editor.commit();
                    Toast.makeText(RemainderActivity.this, R.string.deactive, Toast.LENGTH_SHORT)
                            .show();
                }
            }

        });
    }

    private void setReminder(Context appContext) {
        Intent i = new Intent(appContext, MyReceiver.class);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,9);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        AlarmManager manager = (AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pending = PendingIntent.getBroadcast(appContext, 102, i, PendingIntent.FLAG_UPDATE_CURRENT);


        Log.d("newSwitch","newSwitch");
        if (manager != null){
            manager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pending);
        }
    }

    private void setReminderOff(Context appContext) {
        Log.d("newSwitch","newSwitch");
        Intent i = new Intent(RemainderActivity.this, MyReceiver.class);
        AlarmManager manager = (AlarmManager) appContext.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pending = PendingIntent.getBroadcast(appContext, 102, i, PendingIntent.FLAG_UPDATE_CURRENT);

        if (manager != null){
            manager.cancel(pending);
        }
    }
}