package ts.conv;

import java.util.ArrayList;
import java.util.List;

class Net {
    private NetConfig config;
    private List<Layer> layers = new ArrayList<Layer>();

    public Net(NetConfig config) {
        this.config = config;
        this.dispose();
    }

    public void predict(RGBMatrix rgb) {
        MatrixSet<Integer> inputLayer = rgb.toSet();
        // TODO
        for (Layer layer : layers) {
            for (int i = 0; i < inputLayer.count(); i++) {
                Matrix<Integer> inputSlice = inputLayer.get(i);
                layer.pass(inputSlice);
            }
        }
    }

    private void dispose() {
        for (LayerConfig layerConfig : this.config.getLayerConfigs()) {
            if (layerConfig instanceof ConvLayerConfig) {
                Layer layer = new SimpleConvPoolLayer((ConvLayerConfig)layerConfig);
                layers.add(layer);
            }
        }
    }
}