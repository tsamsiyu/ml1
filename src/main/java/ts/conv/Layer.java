package ts.conv;

abstract class Layer {
    abstract public MatrixSet<Integer> pass(Matrix<Integer> input);
}