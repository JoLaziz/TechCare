package learning.kaelsoftware.myapplication;

import org.junit.Test;

import learning.kaelsoftware.myapplication.converter.ConverterUtils;

import static org.junit.Assert.assertEquals;

/**
 * Created by locAttack on 16/12/2017.
 */

public class ConverterUtilTest {

    @Test
    public void testConvertFahrenheitToCelsius() {
        float actual = ConverterUtils.convertCelsiusToFahrenheit(100);
        // expected value is 212
        float expected = 212;
        // use this method because float is not precise
        assertEquals("Conversion from celsius to fahrenheit failed", expected, actual, 0.001);
    }

    @Test
    public void testConvertCelsiusToFahrenheit() {
        float actual = ConverterUtils.convertFahrenheitToCelsius(212);
        // expected value is 100
        float expected = 100;
        // use this method because float is not precise
        assertEquals("Conversion from celsius to fahrenheit failed", expected, actual, 0.001);
    }

}
