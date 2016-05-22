package com.recycler.sachin.calculateservertime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.net.InetAddress;
import java.util.Date;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class MainActivity extends AppCompatActivity {

    public static final String TIME_SERVER = "http://mapi.indiamart.com/wservce/im/category/";
    private static TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        calculate();
    }

    public static void calculate(){

        NTPUDPClient timeClient = new NTPUDPClient();
        TimeInfo timeInfo = null;
        try {
            InetAddress  inetAddress = InetAddress.getByName(TIME_SERVER);
            timeInfo = timeClient.getTime(inetAddress);
            long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();   //server time
            Date time = new Date(returnTime);
            //------------------------------------------------------------//
            Log.d("TAG","Time from " + TIME_SERVER + ": " + time);
            textView.setText(String.valueOf(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
