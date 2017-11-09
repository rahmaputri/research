package com.rp.rahmawatiputrianasari.research00.Activity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.rp.rahmawatiputrianasari.research00.R;
import com.rp.rahmawatiputrianasari.research00.model.DatabaseHelper;
import com.rp.rahmawatiputrianasari.research00.model.DbBatteryStatus;
import com.rp.rahmawatiputrianasari.research00.model.DbDataUsage;
import com.rp.rahmawatiputrianasari.research00.model.DbNetworkSc;
import com.rp.rahmawatiputrianasari.research00.receiver.BatteryCheck;
import com.rp.rahmawatiputrianasari.research00.receiver.DataUsage;
import com.rp.rahmawatiputrianasari.research00.service.MyJobService;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends BaseActivity {
    private TextView batteryTxt;
    private TextView upTimeTxt;
    private TextView operTxt;
    private TextView jumsmsTxt;
    private TextView messageTxt;
    private TextView oper;
    private TextView dataUsageTxt;
    private Button btnAllData;
    private Button btnStatusData;
    private Button btnNetworkSource;
    private Button btnDataUsage;
    private Button btnHapus;

    private Handler mHandler = new Handler();
    private long mStartRX = 0;
    private long mStartTX = 0;

    String cdma = "Telkom (Flexi), Indosat (StarOne), Bakrie Telecom (Esia),Smartfren (Smartfren),Sampoerna Telekomunikasi Indonesia (Ceria)";
    String validSMS = "Isi ulang berhasil";
    String operator = "";
    String msgData = "";
    int count = 0;
    int operatorMsg = 0;

    AlarmManager alarmMgr;
    AlarmManager alarmMgr2;
    PendingIntent alarmIntent;
    PendingIntent alarmIntent2;

    DatabaseHelper myDb;
    DbBatteryStatus DbBatteryStat;
    DbNetworkSc DbNetworkSource;
    DbDataUsage DbDataUsage;

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            batteryTxt.setText(String.valueOf(level) + "%");
            long at = SystemClock.elapsedRealtime() / 1000;
            upTimeTxt.setText(convert(at));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        batteryTxt = (TextView) this.findViewById(R.id.batterylevel);
        upTimeTxt = (TextView) this.findViewById(R.id.up_time);
        operTxt = (TextView) this.findViewById(R.id.operator);
        jumsmsTxt = (TextView) this.findViewById(R.id.message);
        messageTxt = (TextView) this.findViewById(R.id.messagenya);
        btnAllData = (Button) this.findViewById(R.id.button);
        btnHapus = (Button) this.findViewById(R.id.btnhapus);
        btnStatusData = (Button) this.findViewById(R.id.statusCharge);
        btnNetworkSource = (Button) this.findViewById(R.id.networkSource);
        btnDataUsage = (Button) this.findViewById(R.id.dataUsage);

        oper = (TextView) this.findViewById(R.id.pre_post);
        dataUsageTxt = (TextView) findViewById(R.id.data);

        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        runServiceBootCompleted();

        myDb = new DatabaseHelper(this);
        DbBatteryStat = new DbBatteryStatus(this);
        DbNetworkSource = new DbNetworkSc(this);
        DbDataUsage = new DbDataUsage(this);

        mStartRX = TrafficStats.getTotalRxBytes();
        mStartTX = TrafficStats.getTotalTxBytes();
        dataUsageTxt.setText(Double.toString(Math.round(mStartRX + mStartTX) / 1024) + " kb");
        if (mStartRX == TrafficStats.UNSUPPORTED || mStartTX == TrafficStats.UNSUPPORTED) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Uh Oh!");
            alert.setMessage("Your device does not support traffic stat monitoring.");
            alert.show();
        } else {
            mHandler.postDelayed(mRunnable, 1000);
        }
        operTxt.setText(checkOperator());
        checkSMS();
//        jumsmsTxt.setText(readBattery());
        jumsmsTxt.setText(Integer.toString(count));
        messageTxt.setText(msgData);
        oper.setText(Integer.toString(operatorMsg));


        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, BatteryCheck.class);
        alarmIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intent, 0);
//        Set the alarm to start at 08.00 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 23);
//        setRepeating() every 7 hours
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                1000 * 60 * 60 * 7, alarmIntent);
                1000 * 60 * 7, alarmIntent);

        alarmMgr2 = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent inn = new Intent(this, DataUsage.class);
        alarmIntent2 = PendingIntent.getBroadcast(getBaseContext(), 1, inn, 0);
//        Set the alarm to start at 23.00 a.m.
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 23);
//        setRepeating() every 22 hours
        alarmMgr2.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
//                1000 * 60 * 60 * 22, alarmIntent);
                1000 * 60 * 5, alarmIntent2);


        btnAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Empty Database", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id : " + res.getString(0) + "\n");
                    buffer.append("Date : " + res.getString(1) + "\n");
                    buffer.append("Battery : " + res.getString(2) + "\n\n");
                }
                // Show all data
                showMessage("Data", buffer.toString());
            }
        });
        btnStatusData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DbBatteryStat.getAllData();
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Empty Database", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id : " + res.getString(0) + "\n");
                    buffer.append("Date : " + res.getString(1) + "\n");
                    buffer.append("Battery : " + res.getString(2) + "\n\n");
                }
                // Show all data
                showMessage("Data", buffer.toString());
            }
        });
        btnNetworkSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DbNetworkSource.getAllData();
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Empty Database", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id : " + res.getString(0) + "\n");
                    buffer.append("Date : " + res.getString(1) + "\n");
                    buffer.append("DATA : " + res.getString(2) + "\n\n");
                }
                // Show all data
                showMessage("Data", buffer.toString());
            }
        });
        btnDataUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DbDataUsage.getAllData();
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Empty Database", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :" + res.getString(0) + "\n");
                    buffer.append("Date : " + res.getString(1) + "\n");
                    buffer.append("MOBILE :" + res.getString(2) + "\n");
                    buffer.append("WIFI : " + res.getString(3) + "\n");
                    buffer.append("TOTAL : " + res.getString(4) + "\n\n");
                }
                // Show all data
                showMessage("Data", buffer.toString());
            }
        });
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });


    }
    public void runServiceBootCompleted(){
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(getApplicationContext()));

        Bundle myExtrasBundle = new Bundle();

        myExtrasBundle.putString("some_key", "some_value");

        Job myJob = dispatcher.newJobBuilder()
                // the JobService that will be called
                .setService(MyJobService.class)
                // uniquely identifies the job
                .setTag("my-unique-tag-test")
                // one-off job
                .setRecurring(true)
                // don't persist past a device reboot
                .setLifetime(Lifetime.FOREVER)
                // start between 0 and 60 seconds from now
                .setTrigger(Trigger.executionWindow(0, 60))
                // don't overwrite an existing job with the same tag
                .setReplaceCurrent(false)
                // retry with exponential backoff
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                // constraints that need to be satisfied for the job to run
                .setExtras(myExtrasBundle)

                .build();

        dispatcher.mustSchedule(myJob);
    }

    public void addData() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        boolean isInserted = myDb.insertData(formattedDate,
                batteryTxt.getText().toString());
        if (isInserted)
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();

    }

    public void deleteData() {
        myDb.deleteData();
        DbBatteryStat.deleteData();
        DbNetworkSource.deleteData();
        DbDataUsage.deleteData();
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    private final Runnable mRunnable = new Runnable() {
        public void run() {
            TextView RX = (TextView) findViewById(R.id.rx);
            TextView TX = (TextView) findViewById(R.id.tx);
            long rxBytes = TrafficStats.getTotalRxBytes();
            long rxBytes1 = TrafficStats.getMobileRxBytes();
            RX.setText(Long.toString(rxBytes1));
            long txBytes = TrafficStats.getTotalTxBytes();
            long txBytes1 = TrafficStats.getMobileTxBytes();
//            long aa =rxBytes-rxBytes1;
            TX.setText(Long.toString(txBytes1));
            mHandler.postDelayed(mRunnable, 1000);
        }
    };

    public int checkSMS() {
        String[] result = validSMS.split(" ");
        boolean valid = true;
        Uri inboxURI = Uri.parse("content://sms/inbox");

        String[] reqCols = new String[]{"_id", "address", "body"};

        ContentResolver cr = getContentResolver();

        Cursor c = cr.query(inboxURI, reqCols, null, null, null);

        if (c.moveToFirst()) { // must check the result to prevent exception
            do {
                count++;
                valid = true;
                if (c.getString(1).equalsIgnoreCase(operator)) {
                    operatorMsg++;
                    for (int aa = 0; aa < result.length; aa++) {
                        if (!c.getString(2).contains(result[aa])) {
                            valid = false;
                        }
                    }
                    if (valid) {
                        for (int idx = 0; idx < c.getColumnCount(); idx++) {
                            msgData += c.getColumnName(idx) + " : " + c.getString(idx) + "\n";
                            if (idx == c.getColumnCount() - 1) {
                                msgData += "\n\n";
                            }
                        }
                    }
                }
            }
            while (c.moveToNext());
        } else {
            msgData = "no sms";
        }

        return 0;

    }


    public String checkOperator() {
        TelephonyManager manager = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        String result = "";
        if (cdma.toLowerCase().contains(manager.getNetworkOperatorName())) {
            result = "Postpaid " + manager.getNetworkOperatorName();
            operator = manager.getNetworkOperatorName();
        } else {
            result = "Prepaid " + manager.getNetworkOperatorName();
            operator = manager.getNetworkOperatorName();
        }

        return result;
    }


    private String convert(long t) {
        int s = (int) (t % 60);
        int m = (int) ((t / 60) % 60);
        int h = (int) ((t / 3600));

        return h + ":" + pad(m) + ":" + pad(s);
    }

    private String pad(int n) {
        if (n >= 10) {
            return String.valueOf(n);
        } else {
            return "0" + String.valueOf(n);
        }
    }

    @Override
    protected String[] getPermissionNeedCheck() {
        return new String[0];
    }

}
