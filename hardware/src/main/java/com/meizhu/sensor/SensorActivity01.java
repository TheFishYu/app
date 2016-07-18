package com.meizhu.sensor;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.meizhu.hardware.R;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity01 extends AppCompatActivity {

    ListView listView;
    SensorManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor01);
        listView = (ListView) findViewById(R.id.sensor_activity01_lv);

        //传感器的管理者
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        //保存所有的传感器的名字
        List<String> sensorName = new ArrayList<String>();
        for (Sensor sensor : sensors) {
            switch (sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    sensorName.add("加速度（重力）传感器");
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    sensorName.add("温度传感器");
                    break;
                case Sensor.TYPE_LIGHT:
                    sensorName.add("光感传感器");
                    break;
                //。。。
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SensorActivity01.this,android.R.layout.simple_list_item_1,sensorName);
        listView.setAdapter(adapter);
        Log.i("aaa","手机传感器的个数:"+sensors.size());
    }
}
