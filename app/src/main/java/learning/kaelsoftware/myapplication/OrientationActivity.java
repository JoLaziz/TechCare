package learning.kaelsoftware.myapplication;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class OrientationActivity extends Activity implements SensorEventListener {

    float currentX = -1;
    TextView textView;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    //    private final float[] mAccelerometerReading = new float[3];
//    private final float[] mMagnetometerReading = new float[3];
//
//    private final float[] mRotationMatrix = new float[9];
//    private final float[] mOrientationAngles = new float[3];
    private Sensor magnetic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        textView = findViewById(R.id.textView);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
        // You must implement this callback in your code.
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get updates from the accelerometer and magnetometer at a constant rate.
        // To make batch operations more efficient and reduce power consumption,
        // provide support for delaying updates to the application.
        //
        // In this example, the sensor reporting delay is small enough such that
        // the application receives an update before the system checks the sensor
        // readings again.
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, magnetic,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Don't receive any more updates from either sensor.
        mSensorManager.unregisterListener(this);
    }

    // Get readings from accelerometer and magnetometer. To simplify calculations,
    // consider storing these readings as unit vectors.
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if (event.values[0] != currentX) {
                currentX = event.values[0];
                textView.append(event.values[0] + "," + event.values[1] + "," + event.values[2]);
            }
//            System.arraycopy(event.values, 0, mAccelerometerReading, 0, mAccelerometerReading.length);
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
//            System.arraycopy(event.values, 0, mMagnetometerReading,
//                    0, mMagnetometerReading.length);
        }
    }

    // Compute the three orientation angles based on the most recent readings from
    // the device's accelerometer and magnetometer.
   /* public void updateOrientationAngles() {
        // Update rotation matrix, which is needed to update orientation angles.
//        mSensorManager.getRotationMatrix(mRotationMatrix, null,
//                mAccelerometerReading, mMagnetometerReading);

        // "mRotationMatrix" now has up-to-date information.

        float[] orientation = mSensorManager.getOrientation(mRotationMatrix, mOrientationAngles);

        // "mOrientationAngles" now has up-to-date information.
        String text = "";
        for (int i = 0; i < orientation.length; i++) {
            text += orientation[i];
            if (i < orientation.length - 1)
                text += ",";
        }
        text += "\n";
        textView.append(text);
    }

    public void updateOrientationAngles(View view) {
        updateOrientationAngles();
    }*/
}