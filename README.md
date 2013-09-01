AccelerometerSimulateur
=======================

These is a android project using the accelerometer and the magnetic.


Image Result
-----

![Capture Project](http://img839.imageshack.us/img839/4835/device20130609150217.png)


Usage
-----

You can retrieve instances of your components using SensorManager :
```java
SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

// ACCELEROMETER
sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));

// MAGNETIC_FIELD
sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
```

It should nevertheless check whether these components are present on your device :
```java
// CHECK ACCELEROMETER
boolean accelSuported = sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

// CHECK MAGNETIC_FIELD
boolean magneticSuported = sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
```
