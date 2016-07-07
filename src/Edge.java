import javax.swing.*;

/**
 * Author: Haytham Teng
 * Date: 14-12-29
 * Time: 13:15
 * Email: hevlhayt@foxmail.com
 */
public class Edge {

    /**
     * all the methods below use the same parameter icon
     * @param icon the picture for processing
     * @param threshold
     * @return the new icon
     */

    public static ImageIcon prewitt(ImageIcon icon,int threshold) {
        GraphMatrix gm = new GraphMatrix(icon);
        int w = gm.getWidth();
        int h = gm.getHeight();
        int[][] graph = new int[w][h];
        int[][] ngraph = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                graph[i][j] = 255;
                ngraph[i][j] = 255;
            }
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                graph[i][j] = gm.getR(gm.getRGB(i , j));

            }
        }

        for (int i = 1; i < w-1; i++) {
            for (int j = 1; j < h-1; j++) {
                int sum1;
                int sum2;

                int a1 = gm.getR(gm.getRGB(i - 1, j - 1));
                int a2 = gm.getR(gm.getRGB(i, j - 1));
                int a3 = gm.getR(gm.getRGB(i + 1, j - 1));
                int a4 = gm.getR(gm.getRGB(i - 1, j));
                int a5 = gm.getR(gm.getRGB(i, j));
                int a6 = gm.getR(gm.getRGB(i + 1, j));
                int a7 = gm.getR(gm.getRGB(i - 1, j + 1));
                int a8 = gm.getR(gm.getRGB(i, j + 1));
                int a9 = gm.getR(gm.getRGB(i + 1, j + 1));

                sum1 = -a1-a2-a3+a7+a8+a9;
                sum2 = -a1+a3-a4+a6-a7+a9;

                if (sum1+sum2 < threshold) {
                    ngraph[i][j] = a5;
                    //System.out.println(ngraph[i][j]);
                }

            }
        }

        for (int i = 1; i < w-1; i++) {
            for (int j = 1; j < h-1; j++) {

                gm.setRGB(ngraph[i][j],ngraph[i][j],ngraph[i][j],i,j);

            }

        }
        return gm.newIcon();

    }

    public static ImageIcon sobel(ImageIcon icon,int threshold) {
        GraphMatrix gm = new GraphMatrix(icon);
        int w = gm.getWidth();
        int h = gm.getHeight();
        int[][] graph = new int[w][h];
        int[][] ngraph = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                graph[i][j] = 255;
                ngraph[i][j] = 255;
            }
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                graph[i][j] = gm.getR(gm.getRGB(i , j));

            }
        }

        for (int i = 1; i < w-1; i++) {
            for (int j = 1; j < h-1; j++) {
                int sum1 = 0;
                int sum2 = 0;

                int a1 = gm.getR(gm.getRGB(i - 1, j - 1));
                int a2 = gm.getR(gm.getRGB(i, j - 1));
                int a3 = gm.getR(gm.getRGB(i + 1, j - 1));
                int a4 = gm.getR(gm.getRGB(i - 1, j));
                int a5 = gm.getR(gm.getRGB(i, j));
                int a6 = gm.getR(gm.getRGB(i + 1, j));
                int a7 = gm.getR(gm.getRGB(i - 1, j + 1));
                int a8 = gm.getR(gm.getRGB(i, j + 1));
                int a9 = gm.getR(gm.getRGB(i + 1, j + 1));

                sum1 = -a1-2*a2-a3+a7+2*a8+a9;
                sum2 = -a1+a3-2*a4+2*a6-a7+a9;

                if (sum1+sum2 < threshold) {
                    ngraph[i][j] = a5;
                    //System.out.println(ngraph[i][j]);
                }

            }
        }

        for (int i = 1; i < w-1; i++) {
            for (int j = 1; j < h-1; j++) {

                gm.setRGB(ngraph[i][j],ngraph[i][j],ngraph[i][j],i,j);

            }

        }
        return gm.newIcon();
    }

    public  static ImageIcon laplas(ImageIcon icon,int threshold) {
        GraphMatrix gm = new GraphMatrix(icon);
        int w = gm.getWidth();
        int h = gm.getHeight();
        int[][] graph = new int[w][h];
        int[][] ngraph = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                graph[i][j] = 255;
                ngraph[i][j] = 255;
            }
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                graph[i][j] = gm.getR(gm.getRGB(i , j));

            }
        }

        for (int i = 1; i < w-1; i++) {
            for (int j = 1; j < h-1; j++) {
                int sum1 = 0;

                int a1 = gm.getR(gm.getRGB(i - 1, j - 1));
                int a2 = gm.getR(gm.getRGB(i, j - 1));
                int a3 = gm.getR(gm.getRGB(i + 1, j - 1));
                int a4 = gm.getR(gm.getRGB(i - 1, j));
                int a5 = gm.getR(gm.getRGB(i, j));
                int a6 = gm.getR(gm.getRGB(i + 1, j));
                int a7 = gm.getR(gm.getRGB(i - 1, j + 1));
                int a8 = gm.getR(gm.getRGB(i, j + 1));
                int a9 = gm.getR(gm.getRGB(i + 1, j + 1));

                sum1 = 4*a5-a2-a4-a6-a8;

                if (sum1 < threshold) {
                    ngraph[i][j] = a5;
                    //System.out.println(ngraph[i][j]);
                }

            }
        }

        for (int i = 1; i < w-1; i++) {
            for (int j = 1; j < h-1; j++) {

                gm.setRGB(ngraph[i][j],ngraph[i][j],ngraph[i][j],i,j);

            }

        }
        return gm.newIcon();
    }

}
