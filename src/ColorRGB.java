class ColorRGB {
    private int r;
    private int g;
    private int b;

    ColorRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    boolean isEqual(ColorRGB colorRGB) {
        return ( this.r == colorRGB.r && this.g == colorRGB.g && this.b == colorRGB.b );
    }
}
