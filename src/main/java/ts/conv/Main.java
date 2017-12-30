package ts.conv;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        NetConfig config = new NetConfig(300, 300);
        config.addLayer(new ConvLayerConfig(6, 5, 2));
        Net net = new Net(config);

        String path = "/home/tsamsiyu/Code/Projects/ml/images/test2.jpg";
        RGBMatrix rgb = ImageReader.read(path, config.getHeight(), config.getWidth());

        long start = System.currentTimeMillis();
        net.predict(rgb);
        System.out.println("Comleted in " + String.valueOf(System.currentTimeMillis() - start) + " milliseconds");
    }

    // public static void main2(String[] args) throws Exception {
    //     MatrixSet<Integer> imgMatrix = getImage("/home/tsamsiyu/Code/Projects/ml/images/test2.jpg", 300);

    //     int layerConvsCount = 5; // for example 5 convs
    //     int inputLayersCount = 3; // R,G,B
    //     int convSize = 6;
    //     int stride = 2;

    //     long start = System.currentTimeMillis();

    //     MatrixSet<Float>[] featureLayer = new MatrixSet[inputLayersCount];

    //     for (int depth = 0; depth < inputLayersCount; depth++) {
    //         ConvPool convPool = new ConvPool(convSize, stride, layerConvsCount);
    //         MatrixSet<Float> maps = convPool.slide(imgMatrix.get(depth));
    //         featureLayer[depth] = maps;
    //     }

    //     System.out.println("end in " + String.valueOf(System.currentTimeMillis() - start));
    // }
}
