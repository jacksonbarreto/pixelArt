package com.jacksonbarreto.pixelArt;

import java.util.Arrays;

public class Color {
    private static final String REGEX_HEXADECIMAL_COLOR = "^((0x)?|#?)([a-fA-F0-9]{6})$|^(#?)([a-fA-F0-9]{3})$";
    private static final int RGB_SIZE = 3;
    private static final int MAX_RGB_COLOR = 255;
    private static final int MIN_RGB_COLOR = 0;
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;
    private static final int HEXADECIMAL_FORMAT = 16;
    private static final int COLOR_LENGTH_IN_HEXADECIMAL = 6;
    private static final int LENGTH_OF_A_COLOR_COMPONENT_IN_HEXADECIMAL = 2;

    private String hexadecimalColor;
    private final short[] RGBColor;

    public Color(String hexadecimalColor) {
        if (hexadecimalColor == null || !hexadecimalColor.matches(REGEX_HEXADECIMAL_COLOR))
            throw new IllegalArgumentException();
        this.hexadecimalColor = normalizeHexadecimalString(hexadecimalColor);
        this.RGBColor = hexadecimalColorToRGBColor(this.hexadecimalColor);
    }

    public Color(int red, int green, int blue) {
        if (RGBIsInvalid(red, green, blue))
            throw new IllegalArgumentException();
        this.RGBColor = new short[RGB_SIZE];
        assignsValuesRGB(red, green, blue);
        this.hexadecimalColor = RGBColorToHexadecimalColor(this.RGBColor);
    }

    public Color(short[] RGBColor) {
        if (RGBColor == null || RGBIsInvalid(RGBColor[RED], RGBColor[GREEN], RGBColor[BLUE]))
            throw new IllegalArgumentException();
        this.RGBColor = new short[RGB_SIZE];
        assignsValuesRGB(RGBColor[RED], RGBColor[GREEN], RGBColor[BLUE]);
        this.hexadecimalColor = RGBColorToHexadecimalColor(this.RGBColor);
    }

    public Color(Color color) {
        if (color == null)
            throw new IllegalArgumentException();
        this.RGBColor = color.getRGBColor();
        this.hexadecimalColor = color.getHexadecimalColor();
    }

    public Color(CommonColors commonColors) {
        if (commonColors == null)
            throw new IllegalArgumentException();
        this.hexadecimalColor = commonColors.getHexadecimalColor();
        this.RGBColor = hexadecimalColorToRGBColor(commonColors.getHexadecimalColor());
    }

    public String getHexadecimalColor() {
        return this.hexadecimalColor;
    }

    public void setHexadecimalColor(String hexadecimalColor) {
        if (hexadecimalColor == null || !hexadecimalColor.matches(REGEX_HEXADECIMAL_COLOR))
            throw new IllegalArgumentException();

        this.hexadecimalColor = normalizeHexadecimalString(hexadecimalColor);
        short[] NewRGBColor = hexadecimalColorToRGBColor(this.hexadecimalColor);
        assignsValuesRGB(NewRGBColor[RED], NewRGBColor[GREEN], NewRGBColor[BLUE]);
    }

    public short[] getRGBColor() {
        return RGBColor.clone();
    }

    public void setRGBColor(short[] RGBColor) {
        if (RGBColor == null)
            throw new IllegalArgumentException();
        this.setRGBColor(RGBColor[RED], RGBColor[GREEN], RGBColor[BLUE]);
    }

    public void setRGBColor(int red, int green, int blue) {
        if (RGBIsInvalid(red, green, blue))
            throw new IllegalArgumentException();

        assignsValuesRGB(red, green, blue);
        this.hexadecimalColor = RGBColorToHexadecimalColor(this.RGBColor);
    }

    @Override
    public String toString() {
        return "Color: HEX(" + hexadecimalColor + "), RGB(" + RGBColor[RED] + ", " + RGBColor[GREEN] + ", " + RGBColor[BLUE] + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Color color = (Color) obj;
        return (this.hexadecimalColor.equals(color.getHexadecimalColor()) && Arrays.equals(this.RGBColor, color.getRGBColor()));
    }

    @Override
    public Color clone() {
        return new Color(this);
    }

    private void assignsValuesRGB(int red, int green, int blue) {
        this.RGBColor[RED] = (short) red;
        this.RGBColor[GREEN] = (short) green;
        this.RGBColor[BLUE] = (short) blue;
    }

    private boolean RGBIsInvalid(int red, int green, int blue) {
        return ((red < MIN_RGB_COLOR || red > MAX_RGB_COLOR) || (green < MIN_RGB_COLOR || green > MAX_RGB_COLOR) || (blue < MIN_RGB_COLOR || blue > MAX_RGB_COLOR));
    }

    private String normalizeHexadecimalString(String unNormalizedString) {
        if (unNormalizedString.length() == 8 || unNormalizedString.length() == 7) {
            return unNormalizedString.substring(0, unNormalizedString.length() - 6)
                    + unNormalizedString.substring(unNormalizedString.length() - 6).toUpperCase();
        } else if (unNormalizedString.length() == 6) {
            return "#" + unNormalizedString.toUpperCase();
        } else {
            StringBuilder normalizedString = new StringBuilder("#");
            char firstComponent = unNormalizedString.toUpperCase().charAt(unNormalizedString.length() - 3);
            char secondComponent = unNormalizedString.toUpperCase().charAt(unNormalizedString.length() - 2);
            char thirdComponent = unNormalizedString.toUpperCase().charAt(unNormalizedString.length() - 1);
            normalizedString.append(firstComponent).append(firstComponent);
            normalizedString.append(secondComponent).append(secondComponent);
            normalizedString.append(thirdComponent).append(thirdComponent);
            return normalizedString.toString();
        }
    }

    private short[] hexadecimalColorToRGBColor(String hexadecimalColor) {
        short[] RGBColor = new short[RGB_SIZE];

        for (int color = RED, i = COLOR_LENGTH_IN_HEXADECIMAL, j = COLOR_LENGTH_IN_HEXADECIMAL - LENGTH_OF_A_COLOR_COMPONENT_IN_HEXADECIMAL;
             color < RGB_SIZE;
             color++, i -= LENGTH_OF_A_COLOR_COMPONENT_IN_HEXADECIMAL, j -= LENGTH_OF_A_COLOR_COMPONENT_IN_HEXADECIMAL) {
            RGBColor[color] = Short.parseShort(hexadecimalColor.substring(hexadecimalColor.length() - i, hexadecimalColor.length() - j), HEXADECIMAL_FORMAT);
        }
        return RGBColor;
    }

    private String RGBColorToHexadecimalColor(short[] RGBColor) {
        StringBuilder hexadecimalColor = new StringBuilder("#");

        for (int color = RED; color < RGB_SIZE; color++) {
            hexadecimalColor.append(Integer.toHexString(RGBColor[color]).toUpperCase());
        }

        return hexadecimalColor.toString();
    }

    public enum CommonColors {
        WHITE("#FFFFFF"), SILVER("#C0C0C0"), GRAY("#808080"),
        BLACK("#000000"), RED("#FF0000"), MAROON("#800000"),
        YELLOW("#FFFF00"), OLIVE("#808000"), LIME("#00FF00"),
        GREEN("#008000"), AQUA("#00FFFF"), TEAL("#008080"),
        BLUE("#0000FF"), NAVY("#000080"), FUCHSIA("#FF00FF"),
        PURPLE("#800080");

        private final String hexadecimalColor;

        CommonColors(String hexadecimalColor) {
            this.hexadecimalColor = hexadecimalColor;
        }

        public String getHexadecimalColor() {
            return hexadecimalColor;
        }
    }
}
