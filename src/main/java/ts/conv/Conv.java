package ts.conv;

import java.util.Random;

public class Conv {
    private float[][] weights;
    private int size;

    public Conv(int size) {
        this.size = size;
        final Random random = new Random();
        this.weights = new float[size][size];
        for (int w = 0; w < size; w++) {
            for (int h = 0; h < size; h++) {
                this.weights[w][h] = random.nextFloat();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public float getWeight(int i, int j) {
        return this.weights[i][j];
    }

    public void setWeight(int i, int j, float value) {
        this.weights[i][j] = value;
    }
}
