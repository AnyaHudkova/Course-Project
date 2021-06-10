package classes;
import javax.imageio.ImageIO;
import javax.swing.JSlider;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
public class Transaction {
    Graphics g;
    private JSlider stepTimeSlider;
    private int hT;
    private int wT;
    private Color color;
    
    public Transaction(final MainGUI gui) {
        this.hT = 70;
        this.wT = 30;
        this.color = Color.BLACK;
        this.stepTimeSlider = gui.getStepTimeSlider();
        this.g = gui.getPane().getGraphics();
        final Color back = gui.getPane().getBackground();
        final int rgb = back.getRGB() ^ this.color.getRGB();
        this.g.setXORMode(new Color(rgb));
    }
    
    public Thread moveFromTo(final IfromTo from, final IfromTo to) {
        final Thread t = new Thread() {
            @Override
            public void run() {
            	URL url = Transaction.class.getResource("/other/sad_stud.png"); 
            	BufferedImage img = null;
            	try {
					img = ImageIO.read(url);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            	Graphics2D g2d = (Graphics2D) g;
            	
            	
                int xFrom = Transaction.this.pointFrom(from).x;
                int xTo = Transaction.this.pointTo(to).x;
                if (xFrom > xTo) {
                    xFrom = Transaction.this.pointTo(from).x;
                    xTo = Transaction.this.pointFrom(to).x;
                }
                final int lenX = xTo - xFrom;//шлях, шо потрібно пройти по х
                final int yFrom = Transaction.this.pointFrom(from).y;//з
                final int yTo = Transaction.this.pointTo(to).y;//куди
                final int lenY = yTo - yFrom;//шлях, шо потрібно пройти по у
                final int len = (int)Math.round(Math.sqrt(lenX * lenX + lenY * lenY));//довжина
                final int lenT = (Transaction.this.hT + Transaction.this.wT) / 2;//середній розмір транзакції
                final int n = len / lenT + 1;//кількість кроків
                final int dx = lenX / n;//кроки руху
                final int dy = lenY / n;
                int x = xFrom;
                int y = yFrom;
                
            	Image scaledImg = img.getScaledInstance(Transaction.this.wT, Transaction.this.hT, Image.SCALE_REPLICATE);
            	
                for (int i = 0; i < n; ++i) {//рух
                	g2d.drawImage(scaledImg, x, y, null);
                    try {
                        Thread.sleep(Transaction.this.stepTimeSlider.getValue()*100);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                	g2d.drawImage(scaledImg, x, y, null);
                    x += dx;
                    y += dy;
                }
                to.onIn(Transaction.this);
            }
        };
        t.start();
        return t;
    }
    
    public Point pointFrom(final IfromTo ft) {
        final Component c = ft.getComponent();
        final int x = c.getX() + c.getWidth();
        final int y = c.getY() + c.getHeight() / 2;
        return new Point(x, y);
    }
    
    public Point pointTo(final IfromTo ft) {
        final Component c = ft.getComponent();
        final int x = c.getX();
        final int y = c.getY() + c.getHeight() / 2;
        return new Point(x, y);
    }
}
