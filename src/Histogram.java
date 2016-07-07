import javax.swing.*;

/**
 * Author: Haytham Teng
 * Date: 14-12-29
 * Time: 13:11
 * Email: hevlhayt@foxmail.com
 */
public class Histogram {

    /**
     * all the methods below use the same parameter icon
     * @param icon the picture for processing
     * @return the new icon
     */

    public static ImageIcon histogram(ImageIcon icon) {
        GraphMatrix gm = new GraphMatrix(icon);
        int w = gm.getWidth();
        int h = gm.getHeight();
        int p = w * h;
        int[] countr = new int[256];
        int[] countg = new int[256];
        int[] countb = new int[256];
        int[] countr2 = new int[256];
        int[] countg2 = new int[256];
        int[] countb2 = new int[256];
        for (int i = 0; i < 255; i++) {
            countr[i] = 0;
            countg[i] = 0;
            countb[i] = 0;
            countr2[i] = 0;
            countg2[i] = 0;
            countb2[i] = 0;
        }


        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int a = gm.getRGB(i, j);
                countr[gm.getR(a)] ++;
                countg[gm.getG(a)] ++;
                countb[gm.getB(a)] ++;
            }
        }
        for (int i = 0; i < 256; i++) {
            int t1 = 0;
            int t2 = 0;
            int t3 = 0;
            for (int j = 0; j < i; j++) {
                t1 += countr[j];
                t2 += countb[j];
                t3 += countg[j];
            }
            //System.out.println(t1);
            countr2[i] = t1 *255 / p;
            countg2[i] = t2 *255 / p;
            countb2[i] = t3 *255 / p;
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int a = gm.getRGB(i, j);

                gm.setRGB(countr2[gm.getR(a)], countg2[gm.getG(a)], countb2[gm.getB(a)], i, j);
            }
        }

        return gm.newIcon();
    }

}
