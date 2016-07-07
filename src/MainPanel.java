import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Author: Haytham Teng
 * Date: 14-12-29
 * Time: 09:52
 * Email: hevlhayt@foxmail.com
 */
public class MainPanel {

    private JPanel mPanel;
    private JButton grayButton;
    private JButton noiseButton;
    private JPanel viewPanel;
    private JLabel viewLabel;
    private JButton meanButton;
    private JButton inversionButton;
    private JButton powerButton;
    private JButton histogramButton;
    private JButton sobelButton;
    private JButton prewittButton;
    private JButton laplasButton;
    private JScrollBar scrollBar1;
    private JTextField intensityTextField;
    private JButton binayButton;
    private JButton midButton;
    private JButton resetButton;
    private JScrollBar scrollBar2;
    private JTextField operatorSizeTextField;

    private ImageIcon icon, backup;
    private boolean isSelected = false;
    private int edgetype = 0;

    public MainPanel() {

        scrollBar1.setMinimum(2);
        scrollBar1.setMaximum(200);
        scrollBar2.setMinimum(3);
        scrollBar2.setMaximum(30);

        viewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jfc = new JFileChooser();
                int result = jfc.showDialog(null, "OK");
                jfc.addChoosableFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        String name = f.getName().toLowerCase();
                        return f.isDirectory()||name.endsWith(".jpg")||name.endsWith(".png")
                                ||name.endsWith(".bmp");
                    }

                    @Override
                    public String getDescription() {
                        return "Image";
                    }
                });

                if (result == JFileChooser.APPROVE_OPTION) {
                    viewLabel.setText("");
                    File f = jfc.getSelectedFile();
                    icon = new ImageIcon(f.getPath());
                    backup  = icon;
                    isSelected = true;
                    icon.setImage(icon.getImage().getScaledInstance(850,450,Image.SCALE_SMOOTH));
                    viewLabel.setIcon(icon);
                }
            }
        });
        grayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    icon = GrayScale.toGray(icon);
                    viewLabel.setIcon(icon);
                }

            }
        });
        binayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    icon = GrayScale.binaryzation(icon);
                    viewLabel.setIcon(icon);
                }

            }
        });
        inversionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    icon = GrayScale.inversion(icon);
                    viewLabel.setIcon(icon);
                }

            }
        });
        powerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    icon = GrayScale.power(icon, 0.9);
                    viewLabel.setIcon(icon);
                }

            }
        });
        noiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    icon = GrayScale.noise(icon, 0.02);
                    viewLabel.setIcon(icon);
                }

            }
        });
        histogramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    icon = Histogram.histogram(icon);
                    viewLabel.setIcon(icon);
                }

            }
        });
        sobelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    icon = Edge.sobel(GrayScale.inversion(GrayScale.toGray(icon)), scrollBar1.getValue());
                    viewLabel.setIcon(icon);
                    edgetype = 0;
                }

            }
        });
        prewittButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    icon = Edge.prewitt(GrayScale.inversion(GrayScale.toGray(icon)), scrollBar1.getValue());
                    viewLabel.setIcon(icon);
                    edgetype = 1;
                }

            }
        });
        laplasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    icon = Edge.laplas(GrayScale.inversion(GrayScale.toGray(icon)), scrollBar1.getValue());
                    viewLabel.setIcon(icon);
                    edgetype = 2;
                }

            }
        });
        meanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    icon = Smoothing.mean(icon,scrollBar2.getValue());
                    viewLabel.setIcon(icon);
                }

            }
        });
        midButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    icon = Smoothing.mid(icon,scrollBar2.getValue());
                    viewLabel.setIcon(icon);
                }

            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                viewLabel.setIcon(backup);
                icon = backup;
                edgetype = 0;
                }

            }
        });
        scrollBar1.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                intensityTextField.setText("intensify : "+
                        String.valueOf(scrollBar1.getValue()));
                if (isSelected) {

                    switch (edgetype) {
                        case 0:
                            icon = backup;
                            icon = Edge.sobel(GrayScale.inversion(GrayScale.toGray(icon)), scrollBar1.getValue());
                            viewLabel.setIcon(icon);
                        case 1:
                            icon = backup;
                            icon = Edge.prewitt(GrayScale.inversion(GrayScale.toGray(icon)), scrollBar1.getValue());
                            viewLabel.setIcon(icon);
                        case 2:
                            icon = backup;
                            icon = Edge.laplas(GrayScale.inversion(GrayScale.toGray(icon)), scrollBar1.getValue());
                            viewLabel.setIcon(icon);

                    }
                }

            }
        });
        scrollBar2.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                operatorSizeTextField.setText("operator size: "+
                        String.valueOf(scrollBar2.getValue()));

            }
        });
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("PShop");

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        final MainPanel c = new MainPanel();
        frame.setContentPane(c.mPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(size.width/2-frame.getWidth()/2, size.height/2-frame.getHeight()/2);
        frame.setResizable(false);



    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
