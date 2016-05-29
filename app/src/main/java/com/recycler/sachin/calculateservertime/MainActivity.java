package com.recycler.sachin.calculateservertime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.net.InetAddress;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    //By Sachin
    public static final String TIME_SERVER = "http://www.facebook.com";
    private static TextView textView;


    public static void calculate() {

        NTPUDPClient timeClient = new NTPUDPClient();
        TimeInfo timeInfo;
        try {
            InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
            timeInfo = timeClient.getTime(inetAddress);
            long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();   //server time
            Date time = new Date(returnTime);
            //------------------------------------------------------------//
            Log.d("TAG", "Time from " + TIME_SERVER + ": " + time);
            textView.setText(String.valueOf(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        calculate();
    }
}
