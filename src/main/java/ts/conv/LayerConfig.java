package ts.conv;

abstract class LayerConfig {
    abstract public boolean checkInputSize(int height, int width);

    abstract public Matrix<Integer> outputSize(int height, int width);
}