package ts.conv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class NetConfig {
    private int height;
    private int width;
    private List<LayerConfig> layerConfigs = new ArrayList<LayerConfig>();
    private List<Matrix<Integer>> layerOutputsSize = new ArrayList<Matrix<Integer>>();

    public NetConfig(int height, int width) {
        this.height = height;
        this.width = width;
        Matrix<Integer> netInput = new Matrix<Integer>(Integer.class, height, width);
        layerOutputsSize.add(netInput);
    }

    public NetConfig addLayer(LayerConfig config) throws Exception {
        this.layerConfigs.add(config);
        Matrix<Integer> prevOutput = layerOutputsSize.get(layerOutputsSize.size() - 1);
        boolean isSizeCorrent = config.checkInputSize(prevOutput.getHeight(), prevOutput.getWidth());
        if (!isSizeCorrent) {
            throw new Exception("Layer is not fit under input of " + prevOutput.getHeight() + "x" + prevOutput.getWidth());
        }
        Matrix<Integer> outputSize = config.outputSize(prevOutput.getHeight(), prevOutput.getWidth());
        this.layerOutputsSize.add(outputSize);
        return this;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public List<LayerConfig> getLayerConfigs() {
        return this.layerConfigs;
    }
}