package com.cblue.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cblue.hardware.R;

/**
 * 使用加速度传感器，实现摇一摇的原理
 */
public class SensorActivity02 extends AppCompatActivity {

    SensorManager sensorManager;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor02);
        sensorManager =(SensorManager)getSystemService(SENSOR_SERVICE);
        vibrator =(Vibrator)getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    SensorEventListener sensorEventListener = new SensorEventListener() {
        //当传感器执行的时候
        @Override
        public void onSensorChanged(SensorEvent event) {
            //传感器获得的值
          float []values =   event.values;
          float x =   values[0];
            float y = values[1];
            float z = values[2];
            int midvalue = 19;  //三星的i925 它的最大加速度不会超过20
            //绝对值
            if(Math.abs(x)>19||Math.abs(y)>19||Math.abs(z)>19){
                //发生了加速度运动
                vibrator.vibrate(2*1000);
                Log.i("aaa","手机发生了加速度运动");
                //声音 + 动画
            }
        }

        //当传感器的精度发生的时候
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
