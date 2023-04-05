package projava;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class DiffSampleInherit {

    public static void main(String[] args) {
        var f = new JFrame("差分プログラミング");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var img = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        var g = img.createGraphics();
        g.setBackground(Color.WHITE);
        g.clearRect(0, 0, 600, 400);
        g.drawImage(lineImage(), 10, 10, f);
        g.drawImage(rectImage(), 300, 80, f);
        var label = new JLabel(new ImageIcon(img));
        f.add(label);
        f.pack();
        f.setVisible(true);
    }

    // 関数型インタフェースとして使う場合アノテーションを付けるとラムダ式が使えることがわかりやすくなる
    @FunctionalInterface
    interface ImageDrawer {
        default BufferedImage createImage() {
            var image = new BufferedImage( 250, 200, BufferedImage.TYPE_INT_RGB);
            var graphics = image.createGraphics();
            draw(graphics);
            return image;
        }

        void draw(Graphics2D g);
    }

    // ラムダ式を受け取ることを前提とした高階関数
    static BufferedImage createImage(Consumer<Graphics2D> drawer) {
        var image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        var graphics = image.createGraphics();
        drawer.accept(graphics);
        return image;
    }

    // 匿名クラスをラムダ式に置き換え
    static BufferedImage lineImage() {
        return createImage(g -> g.drawLine(10, 10, 220, 180));
    }

    public static BufferedImage rectImage() {
        return new ImageDrawer() {
            @Override
            public void draw(Graphics2D g) {
                g.drawRect(10, 10, 220, 180);
            }
        }.createImage();
    }
}
