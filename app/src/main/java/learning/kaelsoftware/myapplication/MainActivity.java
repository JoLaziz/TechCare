package learning.kaelsoftware.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoAccelerator(View view) {
        Intent i = new Intent(this, AccelerometerActivity.class);
        startActivity(i);
    }

    public void gotoCompass(View view) {
        Intent i = new Intent(this, CompassActivity.class);
        startActivity(i);
    }

    public void gotoOrientation(View view) {
        Intent i = new Intent(this, OrientationActivity.class);
        startActivity(i);
    }

    public void gotoGyroscope(View view) {
//        Intent i = new Intent(this, GyroscopeActivity.class);
//        startActivity(i);
    }

    public void gotoLight(View view) {
        Intent i = new Intent(this, LightActivity.class);
        startActivity(i);
    }

    public void gotoTemperature(View view) {
//        Intent i = new Intent(this, TemperatureActivity.class);
//        startActivity(i);
    }
}
