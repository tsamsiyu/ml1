package ts.conv;

import java.util.concurrent.*;

public class ConvPool {
    private int count;
    private int size;
    private int stride;
    private Conv[] convs;

    public ConvPool(int size, int stride, int count) {
        this.size = size;
        this.stride = stride;
        this.count = count;
        this.convs = new Conv[this.count];
        for (int i = 0; i < this.count; i++) {
            this.convs[i] = new Conv(this.size);
        }
    }

    private class SlideThread implements Callable<Matrix<Float>> {
        private Conv conv;
        private Matrix<Integer> matrix;
        private int stride;

        public SlideThread(Conv conv, Matrix<Integer> matrix, int stride) {
            this.conv = conv;
            this.matrix = matrix;
            this.stride = stride;
        }

        public float slide(int xMatrix, int yMatrix) {
            int size = this.conv.getSize();
            float result = 0;
            for (int i = 0, w = xMatrix; i < size; w++, i++) {
                for (int j = 0, h = yMatrix; j < size; h++, j++) {
                    result += this.matrix.get(w, h) * this.conv.getWeight(i, j);
                }
            }
            return result;
        }

        public Matrix<Float> call() {
            int matrixSize = this.matrix.getSize();
            int lastMatrixPosition = matrixSize - (this.conv.getSize() - this.stride);
            Matrix<Float> map = new Matrix<Float>(Float.class, lastMatrixPosition / this.stride);
            for (int xMatrix = 0, x = 0; xMatrix < lastMatrixPosition; xMatrix += this.stride, x++) {
                for (int yMatrix = 0, y = 0; yMatrix < lastMatrixPosition; yMatrix += this.stride, y++) {
                    Float result = this.slide(xMatrix, yMatrix);
                    map.set(x, y, result);
                }
            }
            return map;
        }
    }

    public MatrixSet<Float> slide(Matrix<Integer> matrix) throws Exception {
        int rest = matrix.getSize() - this.size;
        int mod = rest % this.stride;

        if (mod > 0 && rest > 0) {
            throw new Exception(
                    "Conv with size " + this.size +
                    " cannot cover matrix with size " + matrix.getSize()
            );
        }

        ExecutorService service = Executors.newCachedThreadPool();
        Future<Matrix<Float>>[] tasks = new Future[this.count];
        MatrixSet<Float> result = new MatrixSet<Float>(this.count);

        for (int i = 0; i < this.count; i++) {
            SlideThread task = new SlideThread(this.convs[i], matrix, this.stride);
            tasks[i] = service.submit(task);
        }

        service.shutdown();

        try {
            if (!service.awaitTermination(1, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
        }

        for (int i = 0; i < this.count; i++) {
            Matrix<Float> taskResult= tasks[i].get();
            result.set(i, taskResult);
        }

        return result;
    }

}