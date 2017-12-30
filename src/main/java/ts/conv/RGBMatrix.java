package ts.conv;

class RGBMatrix {
    private int height;
    private int width;

    private Matrix<Integer> r;
    private Matrix<Integer> g;
    private Matrix<Integer> b;

    public RGBMatrix(int height, int width) {
        this.height = height;
        this.width = width;
        this.r = new Matrix<Integer>(Integer.class, height, width);
        this.g = new Matrix<Integer>(Integer.class, height, width);
        this.b = new Matrix<Integer>(Integer.class, height, width);
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public MatrixSet<Integer> toSet() {
        MatrixSet<Integer> set = new MatrixSet<Integer>(3);
        set.set(0, this.r);
        set.set(1, this.g);
        set.set(2, this.b);
        return set;
    }

    private void setChannel(String channel, Matrix<Integer> matrix) throws Exception {
        if (matrix.getHeight() == this.height && matrix.getWidth() == this.width) {
            if (channel == "r") {
                this.r = matrix;
            } else if (channel == "g") {
                this.g = matrix;
            } else if (channel == "b") {
                this.b = matrix;
            } else {
                throw new Exception("Invalid channel passed");
            }
        } else {
            throw new Exception("There is invalid size of passed matrix");
        }
    }

    public void setR(Matrix<Integer> matrix) throws Exception {
        setChannel("r", matrix);
    }

    public void setG(Matrix<Integer> matrix) throws Exception {
        setChannel("b", matrix);
    }

    public void setB(Matrix<Integer> matrix) throws Exception {
        setChannel("b", matrix);
    }

    public Matrix<Integer> getR() {
        return this.r;
    }

    public Matrix<Integer> getG() {
        return this.g;
    }

    public Matrix<Integer> getB() {
        return this.b;
    }
}