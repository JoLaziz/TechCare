package learning.kaelsoftware.myapplication;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class LightActivity extends Activity implements SensorEventListener {
/*********************************************************************/
/** Attribut du capteur***********************************************/
    /*********************************************************************/

    // Valeur courante de la lumière
    float l;

    // Le sensor manager
    SensorManager sensorManager;

    // Le capteur de lumière
    Sensor light;

    float currentLight = -1;
    TextView textView;

/***************************************************************/
/** Gestion du cycle de vie ************************************/
    /***************************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // construire l'IHM
        setContentView(R.layout.activity_accelerometer);
        textView = findViewById(R.id.textView);
        // Instancier le SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Instancier le capteur de lumière
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onPause() {
        // désenregistrer notre écoute du capteur
        sensorManager.unregisterListener(this, light);
        super.onPause();
    }

    @Override
    protected void onResume() {
        // enregistrer notre écoute du capteur
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_GAME);
        super.onResume();
    }

/*****************************************************************/
/** SensorEventListener ******************************************/
    /*****************************************************************/

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Faîtes quelque chose ou pas&#8230;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Mettre à jour uniquement dans le cas de notre capteur
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            // La valeur de la lumière
            l = event.values[0];
            if (l != currentLight) {
                currentLight = l;
                textView.append("new value = " + currentLight);
            }
            // faire autre chose&#8230;
        }
    }
}
