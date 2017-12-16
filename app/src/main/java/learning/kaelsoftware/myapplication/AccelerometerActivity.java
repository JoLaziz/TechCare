package learning.kaelsoftware.myapplication;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private boolean color = false;
    private View view;
    private long lastUpdate;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        view = findViewById(R.id.layout);
        textView = findViewById(R.id.textView);
        view.setBackgroundColor(Color.GREEN);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

        listSensor();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            getGyroscope(event);
        }
    }

    private void getGyroscope(SensorEvent event) {
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            switch (accuracy) {
                case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                    Toast.makeText(this, "SENSOR_STATUS_ACCURACY_LOW", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                    Toast.makeText(this, "SENSOR_STATUS_ACCURACY_MEDIUM", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                    Toast.makeText(this, "SENSOR_STATUS_ACCURACY_HIGH", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case SensorManager.SENSOR_STATUS_UNRELIABLE:
                    Toast.makeText(this, "SENSOR_STATUS_UNRELIABLE", Toast.LENGTH_SHORT)
                            .show();
                default:
                    break;
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    ///////////////////////////////////////////

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = event.timestamp;
        if (accelationSquareRoot >= 2) //
        {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
//            Toast.makeText(this, "Device was shuffed", Toast.LENGTH_SHORT)
//                    .show();
//            Snackbar.make(view, "Device was shuffed", Snackbar.LENGTH_LONG)
////                    .setAction("Action", null)
//                    .show();
            if (color) {
                view.setBackgroundColor(Color.GREEN);
            } else {
                view.setBackgroundColor(Color.RED);
            }
            color = !color;
        }
    }


    /**
     * List existing sensors
     * <p>
     * Find list of existing sensors, find one or multiple sensors by type.
     */
    private void listSensor() {
        // Find all device sensors :
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        // Describe each sensor
        StringBuffer sensorDesc = new StringBuffer();
        for (Sensor sensor : sensors) {
            sensorDesc.append("New sensor detected : \r\n");
            sensorDesc.append("\tName: " + sensor.getName() + "\r\n");
            sensorDesc.append("\tType: " + getType(sensor.getType()) + "\r\n");
            sensorDesc.append("Version: " + sensor.getVersion() + "\r\n");
            sensorDesc.append("Resolution (in the sensor unit): " + sensor.getResolution() + "\r\n");
            sensorDesc.append("Power in mA used by this sensor while in use" + sensor.getPower() + "\r\n");
            sensorDesc.append("Vendor: " + sensor.getVendor() + "\r\n");
            sensorDesc.append("Maximum range of the sensor in the sensor's unit." + sensor.getMaximumRange() + "\r\n");
            sensorDesc.append("Minimum delay allowed between two events in microsecond" + " or zero if this sensor only returns a value when the data it's measuring changes. " + sensor.getMinDelay() + "\r\n");
            sensorDesc.append("\n\n");
        }

        // Faire quelque chose de cette liste
//        Toast.makeText(this, sensorDesc.toString(), Toast.LENGTH_LONG).show();
        textView.setText(sensorDesc);

//        // Pour trouver un capteur spécifique&#160;:
//        Sensor gyroscopeDefault = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//        // Pour trouver tous les capteurs d'un type fixé :
//        List<Sensor> gyroscopes = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
    }

    private String getType(int type) {
        String strType;
        switch (type) {
            case Sensor.TYPE_ACCELEROMETER:
                strType = "TYPE_ACCELEROMETER";
                break;
            case Sensor.TYPE_GRAVITY:
                strType = "TYPE_GRAVITY";
                break;
            case Sensor.TYPE_GYROSCOPE:
                strType = "TYPE_GYROSCOPE";
                break;
            case Sensor.TYPE_LIGHT:
                strType = "TYPE_LIGHT";
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                strType = "TYPE_LINEAR_ACCELERATION";
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                strType = "TYPE_MAGNETIC_FIELD";
                break;
            case Sensor.TYPE_ORIENTATION:
                strType = "TYPE_ORIENTATION";
                break;
            case Sensor.TYPE_PRESSURE:
                strType = "TYPE_PRESSURE";
                break;
            case Sensor.TYPE_PROXIMITY:
                strType = "TYPE_PROXIMITY";
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                strType = "TYPE_ROTATION_VECTOR";
                break;
            case Sensor.TYPE_TEMPERATURE:
                strType = "TYPE_TEMPERATURE";
                break;
            default:
                strType = "TYPE_UNKNOW";
                break;
        }
        return strType;
    }

    /////////////////////////////////////////

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
