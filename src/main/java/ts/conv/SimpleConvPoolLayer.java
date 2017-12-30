package ts.conv;

import java.sql.SQLOutput;

class SimpleConvPoolLayer extends Layer {
    private ConvLayerConfig config;

    public SimpleConvPoolLayer(ConvLayerConfig config) {
        this.config = config;
    }

    public MatrixSet<Integer> pass(Matrix<Integer> input) {
        Matrix<Integer> outputSize = this.config.outputSize(input.getHeight(), input.getWidth());
        MatrixSet<Integer> output = new MatrixSet<Integer>(
            this.config.getConvCount(), 
            Integer.class, 
            outputSize.getHeight(), 
            outputSize.getWidth()
        );
        // TODO
        System.out.println("SimpleConvPoolLayer::pass");
        return output;
    }
}