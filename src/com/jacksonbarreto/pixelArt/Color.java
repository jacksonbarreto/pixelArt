package com.jacksonbarreto.pixelArt;

import java.util.Arrays;

public class Color {
    static final String REGEX_HEXADECIMAL_COLOR = "^((0x)?|#?)([a-fA-F0-9]{6})$|^(#?)([a-fA-F0-9]{3})$";
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
        this.RGBColor = new short[3];
        assignsValuesRGB(red, green, blue);
        this.hexadecimalColor = RGBColorToHexadecimalColor(this.RGBColor);
    }

    public Color(short[] RGBColor) {
        if (RGBColor == null || RGBIsInvalid(RGBColor[0], RGBColor[1], RGBColor[2]))
            throw new IllegalArgumentException();
        this.RGBColor = new short[3];
        assignsValuesRGB(RGBColor[0], RGBColor[1], RGBColor[2]);
        this.hexadecimalColor = RGBColorToHexadecimalColor(this.RGBColor);
    }

    public Color(Color color) {
        this.RGBColor = color.getRGBColor();
        this.hexadecimalColor = color.getHexadecimalColor();
    }

    public Color(CommonColors commonColors) {
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
        assignsValuesRGB(NewRGBColor[0], NewRGBColor[1], NewRGBColor[2]);
    }

    public short[] getRGBColor() {
        return RGBColor.clone();
    }

    public void setRGBColor(short[] RGBColor) {
        if (RGBColor == null)
            throw new IllegalArgumentException();
        this.setRGBColor(RGBColor[0], RGBColor[1], RGBColor[2]);
    }

    public void setRGBColor(int red, int green, int blue) {
        if (RGBIsInvalid(red, green, blue))
            throw new IllegalArgumentException();

        assignsValuesRGB(red, green, blue);
        this.hexadecimalColor = RGBColorToHexadecimalColor(this.RGBColor);
    }

    public String toString() {
        return "Color: HEX(" + hexadecimalColor + "), RGB(" + RGBColor[0] + ", " + RGBColor[1] + ", " + RGBColor[2] + ")";
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Color color = (Color) obj;
        return (this.hexadecimalColor.equals(color.getHexadecimalColor()) && Arrays.equals(this.RGBColor, color.getRGBColor()));
    }

    public Color clone() {
        return new Color(this);
    }

    private void assignsValuesRGB(int red, int green, int blue) {
        this.RGBColor[0] = (short) red;
        this.RGBColor[1] = (short) green;
        this.RGBColor[2] = (short) blue;
    }

    private boolean RGBIsInvalid(int red, int green, int blue) {
        return ((red < 0 || red > 255) || (green < 0 || green > 255) || (blue < 0 || blue > 255));
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
        short[] RGBColor = new short[3];

        for (int i = 0, j = 6, k = 4; i < 3; i++, j -= 2, k -= 2) {
            RGBColor[i] = Short.parseShort(hexadecimalColor.substring(hexadecimalColor.length() - j, hexadecimalColor.length() - k), 16);
        }
        return RGBColor;
    }

    private String RGBColorToHexadecimalColor(short[] RGBColor) {
        StringBuilder hexadecimalColor = new StringBuilder("#");

        for (int i = 0; i < 3; i++) {
            hexadecimalColor.append(Integer.toHexString(RGBColor[i]).toUpperCase());
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
