package learning.kaelsoftware.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import learning.kaelsoftware.myapplication.ui.MyCompassView;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {
    private static SensorManager sensorService;
    private MyCompassView compassView;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compassView = new MyCompassView(this);
        setContentView(compassView);

        sensorService = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if (sensor != null) {
            sensorService.registerListener(this, sensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
            Log.i("Compass MainActivity", "Registerered for ORIENTATION Sensor");
        } else {
            Log.e("Compass MainActivity", "Registerered for ORIENTATION Sensor");
            Toast.makeText(this, "ORIENTATION Sensor not found",
                    Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float azimuth = sensorEvent.values[0];
        compassView.updateData(azimuth);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensor != null) {
            sensorService.unregisterListener(this);
        }
    }
}
