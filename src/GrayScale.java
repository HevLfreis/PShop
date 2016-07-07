import javax.swing.*;
import java.util.Stack;

/**
 * Author: Haytham Teng
 * Date: 14-12-29
 * Time: 13:07
 * Email: hevlhayt@foxmail.com
 */
public class GrayScale {

    /**
     * all the methods below use the same parameter icon
     * @param icon the picture for processing
     * @return the new icon
     */

    public static ImageIcon inversion(ImageIcon icon) {
        GraphMatrix gm = new GraphMatrix(icon);
        int w = gm.getWidth();
        int h = gm.getHeight();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int a = gm.getRGB(i, j);
                gm.setRGB(255-gm.getR(a), 255-gm.getG(a), 255-gm.getB(a),
                        i, j);

            }

        }
        return gm.newIcon();
    }

    public static ImageIcon toGray(ImageIcon icon) {
        GraphMatrix gm = new GraphMatrix(icon);
        int w = gm.getWidth();
        int h = gm.getHeight();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int a = gm.getRGB(i, j);
                int alpha = a & 0xff000000;
                int gray = (gm.getR(a) * 77 + gm.getG(a) * 151 + gm.getB(a) * 28) >> 8;
                gm.setRGB(gray, gray, gray, i, j);
            }

        }
        return gm.newIcon();
    }

    public static ImageIcon binaryzation(ImageIcon icon) {
        GraphMatrix gm = new GraphMatrix(icon);
        int w = gm.getWidth();
        int h = gm.getHeight();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int a = gm.getRGB(i, j);

                int gray = (gm.getR(a) * 77 + gm.getG(a) * 151 + gm.getB(a) * 28) >> 8;
                int b = 128;
                gm.setRGB((gm.getR(a)>b ? 255:0), (gm.getG(a)>b ? 255:0), (gm.getB(a)>b ? 255:0), i, j);
            }

        }
        return gm.newIcon();
    }

    /**
     * @param r exponential constant
     */

    public static ImageIcon power(ImageIcon icon, double r) {
        GraphMatrix gm = new GraphMatrix(icon);
        int w = gm.getWidth();
        int h = gm.getHeight();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int a = gm.getRGB(i, j);
                int nr = (int) Math.pow(gm.getR(a),r);
                int ng = (int) Math.pow(gm.getG(a),r);
                int nb = (int) Math.pow(gm.getB(a),r);

                gm.setRGB(nr>255?255:nr, ng>255?255:ng,
                        nb>255?255:nb, i, j);
            }
        }
        return gm.newIcon();

    }

    /**
     * @param r the intensify of noise
     */
    public static ImageIcon noise(ImageIcon icon, double r) {
        GraphMatrix gm = new GraphMatrix(icon);
        int w = gm.getWidth();
        int h = gm.getHeight();
        int p = w*h;
        Stack<Double> s = new Stack<Double>();


        for (int i = 0; i < p*r; i++) {
            s.push(Math.random()*(p-1)) ;
        }

        for (int i = 0; i < p*r; i++) {
            double a = s.pop() ;

            int b = (int) a;
            gm.setRGB(0, 0, 0, b%w, b/w);
        }
        return gm.newIcon();

    }



}
