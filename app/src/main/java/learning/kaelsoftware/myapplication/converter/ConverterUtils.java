package learning.kaelsoftware.myapplication.converter;

/**
 * Created by locAttack on 16/12/2017.
 */

public class ConverterUtils {
    // converts to celsius
    public static float convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }

    // converts to fahrenheit
    public static float convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5) + 32;
    }
}
