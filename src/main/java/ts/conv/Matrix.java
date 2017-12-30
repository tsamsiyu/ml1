package ts.conv;

import java.lang.reflect.Array;

public class Matrix<T> {
    private int height;
    private int width;
    private T[][] container;

    @SuppressWarnings("unchecked")
    public Matrix(Class<T> type, int height, int width) {
        this.container = (T[][]) Array.newInstance(type, height, width);
        this.height = height;
        this.width = width;
    }

    public Matrix(Class<T> type, int size) {
        new Matrix<T>(type, size, size);
    }

    public T get(int x, int y) {
        return this.container[x][y];
    }

    public void set(int x, int y, T value) {
        this.container[x][y] = value;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}
