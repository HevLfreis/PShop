import javax.swing.*;
import java.util.Arrays;

/**
 * Author: Haytham Teng
 * Date: 14-12-29
 * Time: 13:12
 * Email: hevlhayt@foxmail.com
 */
public class Smoothing {

    /**
     * all the methods below use the same parameter icon
     * @param icon the picture for processing
     * @param n the size of the template
     * @return the new icon
     */

    public static ImageIcon mean(ImageIcon icon, int n) {

        if ( n % 2==0 ) n++;
        GraphMatrix gm = new GraphMatrix(icon);
        int half = n/2;
        int w = gm.getWidth() + half*2;
        int h = gm.getHeight() + half*2;
        int[][] graph = new int[w][h];
        int[][] ngraph = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                graph[i][j] = 255;
                ngraph[i][j] = 255;
            }
        }

        for (int i = half; i < w-half; i++) {
            for (int j = half; j < h-half; j++) {
                graph[i][j] = gm.getR(gm.getRGB(i-half , j-half));

            }
        }

        for (int i = half; i < w-half; i++) {
            for (int j = half; j < h-half; j++) {
                int sum = 0;
                for (int k = i-half; k < i+half+1; k++) {
                    for (int l = j-half; l < j+half+1; l++) {
                        //System.out.println(k+" "+l);
                        sum += graph[k][l];
                    }

                }
                sum  = sum / (n*n);
                ngraph[i][j] = sum;
            }
        }

        for (int i = half; i < w-half; i++) {
            for (int j = half; j < h-half; j++) {
               gm.setRGB(ngraph[i][j],ngraph[i][j],ngraph[i][j],i-half,j-half);

            }

        }
        return gm.newIcon();
    }

    public static ImageIcon mid(ImageIcon icon, int n) {

        if ( n % 2==0 ) n++;
        GraphMatrix gm = new GraphMatrix(icon);
        int half = n/2;
        int w = gm.getWidth() + half*2;
        int h = gm.getHeight() + half*2;
        int[][] graph = new int[w][h];
        int[][] ngraph = new int[w][h];


        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                graph[i][j] = 255;
                ngraph[i][j] = 255;
            }
        }

        for (int i = half; i < w-half; i++) {
            for (int j = half; j < h-half; j++) {
                graph[i][j] = gm.getR(gm.getRGB(i-half , j-half));

            }
        }

        for (int i = half; i < w-half; i++) {
            for (int j = half; j < h-half; j++) {
                int[] sortp = new int[n*n];
                int c = 0;
                for (int k = i-half; k < i+half+1; k++) {
                    for (int l = j-half; l < j+half+1; l++) {
                        sortp[c] = graph[k][l];
                        c++;
                    }

                }
                Arrays.sort(sortp);
                ngraph[i][j] = sortp[n*n/2];
            }
        }

        for (int i = half; i < w-half; i++) {
            for (int j = half; j < h-half; j++) {
                gm.setRGB(ngraph[i][j],ngraph[i][j],ngraph[i][j],i-half,j-half);

            }

        }
        return gm.newIcon();
    }

    public static ImageIcon geo(ImageIcon icon, int n) {
        if ( n % 2==0 ) n++;
        GraphMatrix gm = new GraphMatrix(icon);
        int half = n/2;
        int w = gm.getWidth() + half*2;
        int h = gm.getHeight() + half*2;
        int[][] graph = new int[w][h];
        int[][] ngraph = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                graph[i][j] = 255;
                ngraph[i][j] = 255;
            }
        }

        for (int i = half; i < w-half; i++) {
            for (int j = half; j < h-half; j++) {
                graph[i][j] = gm.getR(gm.getRGB(i-half , j-half));

            }
        }

        for (int i = half; i < w-half; i++) {
            for (int j = half; j < h-half; j++) {
                int sum = 0;
                for (int k = i-half; k < i+half+1; k++) {
                    for (int l = j-half; l < j+half+1; l++) {
                        //System.out.println(k+" "+l);
                        sum += 1.0/graph[k][l];
                    }

                }
                ngraph[i][j] = n*n/sum;
            }
        }

        for (int i = half; i < w-half; i++) {
            for (int j = half; j < h-half; j++) {
                gm.setRGB(ngraph[i][j],ngraph[i][j],ngraph[i][j],i-half,j-half);

            }

        }
        return gm.newIcon();
    }

}
