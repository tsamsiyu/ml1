package ts.conv;

class ConvLayerConfig extends LayerConfig {
    private int convSize;
    private int convCount;
    private int stride;
    private int padding;

    public ConvLayerConfig(int size, int count, int stride) {
        this(size, count, stride, 0);
    }

    public ConvLayerConfig(int size, int count, int stride, int padding) {
        this.convSize = size;
        this.convCount = count;
        this.stride = stride;
        this.padding = padding;
    }

    public Matrix<Integer> outputSize(int height, int width) {
        int steps, outputHeight, outputWidth;
        steps = height + this.padding + this.padding - this.convSize;
        outputHeight = steps / this.stride + 1;
        steps = width + this.padding + this.padding - this.convSize;
        outputWidth = steps / this.stride + 1;
        return new Matrix<Integer>(Integer.class, outputHeight, outputWidth);
    }

    public boolean checkInputSize(int height, int width) {
        int steps, mod;

        steps = height + this.padding + this.padding - this.convSize;
        mod = steps % this.stride;

        if (mod > 0 || steps < 0) {
            return false;
        }

        steps = width + this.padding + this.padding - this.convSize;
        mod = steps % this.stride;

        if (mod > 0 || steps < 0) {
            return false;
        }

        return true;
    }

    public int getConvSize() {
        return this.convSize;
    }

    public int getConvCount() {
        return this.convCount;
    }

    public int getStride() {
        return this.stride;
    }

    public int getPadding() {
        return this.padding;
    }
}