import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Author: Haytham Teng
 * Date: 14-12-29
 * Time: 13:34
 * Email: hevlhayt@foxmail.com
 */
public class GraphMatrix {

    private int[][] a;
    private ImageIcon icon;
    private BufferedImage bi;

    public GraphMatrix(ImageIcon ico) {
        this.icon = ico;
        //System.out.println(icon.getIconHeight());
        a = new int[icon.getIconWidth()][icon.getIconHeight()];
        this.bi = new BufferedImage(icon.getIconWidth(),icon.getIconHeight()
            , BufferedImage.TYPE_INT_BGR);

        Graphics2D g2D = (Graphics2D) bi.getGraphics();
        g2D.drawImage(icon.getImage(), 0, 0, icon.getImageObserver());

        for (int j1 = bi.getMinY(); j1 < bi.getHeight(); j1++) {
            for (int j2 = bi.getMinX(); j2 < bi.getWidth(); j2++) {
                a[j2][j1] = bi.getRGB(j2, j1);
                //System.out.println(a[j2][j1]);
            }
        }
    }

    public int getRGB(int r, int c) {
        return a[r][c];
    }

    public int getR(int a) { return (a & 0xff0000) >> 16; }
    public int getG(int a) { return (a & 0xff00) >> 8; }
    public int getB(int a) { return (a & 0xff); }
    public int getWidth() { return icon.getIconWidth(); }
    public int getHeight() { return icon.getIconHeight(); }

    public void setRGB(int r, int g, int b, int i, int j) {
        Color color = new Color(r, g, b);
        bi.setRGB(i, j, color.getRGB());
    }


    public int show(int r, int g, int b) {
        Color color = new Color(r,g,b);
        return color.getRGB() ;
    }

    public ImageIcon newIcon() {
        ImageIcon icon = new ImageIcon(bi);
        return icon;
    }

    public static void main(String[] args) {
//        ImageIcon ico = new ImageIcon("d:/2.jpg");
//        GraphMatrix gm = new GraphMatrix(ico);
//        int a = gm.getRGB(200,210);
//        System.out.println(a);
//        System.out.println(gm.show(gm.getR(a),gm.getG(a),gm.getB(a)));
//        System.out.println(gm.getR(a)+" "+gm.getG(a)+" "+gm.getB(a));
//        System.out.println(0xff&0xf0);
    }
}
