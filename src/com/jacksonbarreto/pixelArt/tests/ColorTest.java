package com.jacksonbarreto.pixelArt.tests;

import com.jacksonbarreto.pixelArt.Color;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {
    Color c1 = new Color("123");
    Color c2 = new Color("#ffA");
    Color c3 = new Color("A8eB12");
    Color c4 = new Color(17, 34, 51);
    Color c5 = new Color(255, 255, 170);
    Color c6 = new Color(168, 235, 18);
    Color c7 = new Color(Color.CommonColors.FUCHSIA);

    @Test
    void shouldCreateAColorFrom3Characters() {
        assertEquals("#112233", c1.getHexadecimalColor());
        assertEquals("#FFFFAA", c2.getHexadecimalColor());
        assertEquals("#A8EB12", c3.getHexadecimalColor());
    }

    @Test
    void creationFromAnObjectThatShouldThrowAnException(){
        Color nullColor = null;
        Color.CommonColors enumNull = null;
        assertThrows(IllegalArgumentException.class, () -> new Color(nullColor));
        assertThrows(IllegalArgumentException.class, () -> new Color(enumNull));
    }

    @Test
    void hexadecimalCreationsThatShouldThrowAnException() {
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> new Color("#ff000"));
        assertThrows(IllegalArgumentException.class, () -> new Color("#AJKSDD"));
        assertThrows(IllegalArgumentException.class, () -> new Color("blue"));
        assertThrows(IllegalArgumentException.class, () -> new Color(str));
    }

    @Test
    void setHexadecimalColorThatShouldThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> c1.setHexadecimalColor("#ff000"));
        assertThrows(IllegalArgumentException.class, () -> c2.setHexadecimalColor("#AJKSDD"));
        assertThrows(IllegalArgumentException.class, () -> c3.setHexadecimalColor("blue"));
        assertThrows(IllegalArgumentException.class, () -> c3.setHexadecimalColor(null));
    }

    @Test
    void RGBCreationsThatShouldThrowAnException() {
        short[] RGBColor = null;
        assertThrows(IllegalArgumentException.class, () -> new Color(-22, 50, 33));
        assertThrows(IllegalArgumentException.class, () -> new Color(233, 256, 120));
        assertThrows(IllegalArgumentException.class, () -> new Color(0, 255, 322));
        assertThrows(IllegalArgumentException.class, () -> new Color(0, 255, 322));
        assertThrows(IllegalArgumentException.class, () -> new Color(RGBColor));
    }

    @Test
    void setRGBColorThatShouldThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> c1.setRGBColor(-22, 50, 33));
        assertThrows(IllegalArgumentException.class, () -> c2.setRGBColor(233, 256, 120));
        assertThrows(IllegalArgumentException.class, () -> c3.setRGBColor(new short[]{-68, 235, 18}));
        assertThrows(IllegalArgumentException.class, () -> c3.setRGBColor(new short[]{98, 256, 18}));
        assertThrows(IllegalArgumentException.class, () -> c3.setRGBColor(new short[]{98, 174, 256}));
        assertThrows(IllegalArgumentException.class, () -> c3.setRGBColor(null));
    }

    @Test
    void shouldConvertHexadecimalToRGB() {
        assertTrue(Arrays.equals(new short[]{17, 34, 51}, c1.getRGBColor()));
        assertTrue(Arrays.equals(new short[]{255, 255, 170}, c2.getRGBColor()));
        assertTrue(Arrays.equals(new short[]{168, 235, 18}, c3.getRGBColor()));
        assertTrue(Arrays.equals(new short[]{255, 0, 255}, c7.getRGBColor()));
    }

    @Test
    void shouldConvertRGBToHexadecimal() {
        assertEquals("#112233", c4.getHexadecimalColor());
        assertEquals("#FFFFAA", c5.getHexadecimalColor());
        assertEquals("#A8EB12", c6.getHexadecimalColor());
    }

    @Test
    void shouldBeEqual() {
        assertEquals(c1, c4);
        assertEquals(c4, c1);
        assertEquals(c4, c4.clone());
        assertEquals(c1, c4.clone());
        assertEquals(c4.clone(), c1);
    }

}
