package ts.conv;

/**
 * Class was created since set of matrix is usable in neural network
 */
public class MatrixSet<T> {
    private Matrix<T>[] container;

    public MatrixSet(int count, Class<T> type, int height, int width) {
        this.container = (Matrix<T>[]) new Matrix[count];
        for (int i = 0; i < count; i++) {
            this.container[i] = new Matrix<T>(type, height, width);
        }
    }

    public MatrixSet(int count) {
        this.container = new Matrix[count];
    }

    public int count() {
        return this.container.length;
    }

    public void set(int i, Matrix<T> val) {
        this.container[i] = val;
    }

    public Matrix<T> get(int i) {
        return this.container[i];
    }

    public T get(int n, int i, int j) {
        return this.container[n].get(i, j);
    }

    public void set(int n, int i, int j, T val) {
        this.container[n].set(i, j, val);
    }
}
