package com.mikhaellopez.simulateur;

import java.text.DecimalFormat;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	private boolean accelSuported;
	private boolean magneticSuported;

	private TextView tvAX;
	private TextView tvAY;
	private TextView tvAZ;
	private TextView tvMX;
	private TextView tvMY;
	private TextView tvMZ;
	
	private DecimalFormat df = new DecimalFormat("00.00"); 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tvAX = (TextView)findViewById(R.id.tvAX);
		tvAY = (TextView)findViewById(R.id.tvAY);
		tvAZ = (TextView)findViewById(R.id.tvAZ);

		tvMX = (TextView)findViewById(R.id.tvMX);
		tvMY = (TextView)findViewById(R.id.tvMY);
		tvMZ = (TextView)findViewById(R.id.tvMZ);
	}

	@Override
	protected void onStart() {
		super.onStart();
		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		accelSuported = sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		if(!accelSuported)
			sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
		magneticSuported = sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
		if(!magneticSuported)
			sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
	}

	@Override
	protected void onStop() {
		super.onStop();
		sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		switch(accuracy) {
		case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
		case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
		case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
		case SensorManager.SENSOR_STATUS_UNRELIABLE:
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		switch(event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			onAccelerometerChanged(event);
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			onMagneticFieldChanged(event);
			break;
		}
	}

	private void onAccelerometerChanged(SensorEvent event) {
		float x, y, z;
		x = event.values[0];
		y = event.values[1];
		z = event.values[2];

		tvAX.setText("X:"+df.format(x));
		tvAY.setText("Y:"+df.format(y));
		tvAZ.setText("Z:"+df.format(z));
	}

	private void onMagneticFieldChanged(SensorEvent event) {
		float x, y, z;
		x = event.values[0];
		y = event.values[1];
		z = event.values[2];

		tvMX.setText("X:"+df.format(x));
		tvMY.setText("Y:"+df.format(y));
		tvMZ.setText("Z:"+df.format(z));
	}
}

