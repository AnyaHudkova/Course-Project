package classes;

import javax.swing.JSlider;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Handler extends AbstractWorker
{
	private Counter counter;
	String[] imgs = new String[] { 
			"/other/exam1.png",
			"/other/exam2.png", 
			"/other/exam3.png", 
			"/other/exam4.png"};
	public Handler(final MainGUI gui, final JLabel label, final QueueWithCounter queue, 
			final JSlider TimeSlider, final Counter counter) {
		super(gui, label, queue, TimeSlider);
		this.counter = counter;
	}

	@Override
	public void run() {
		
		while (this.gui.isCreatorWorking() || this.queue.getQueueSize() > 0) {
			synchronized (this.queue) {
				while (this.queue.getQueueSize() <= 0) {
					try {
						this.queue.wait();
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				this.trs = this.queue.removeFirst();
				this.queue.notify();
			}
			final Thread t = this.trs.moveFromTo(this.queue, this);
			try {
				t.join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.showWorking();
			this.trs.moveFromTo(this, this.counter);
			
		}
	}

	public void showWorking() {
		URL u = getClass().getResource("/other/prep.png");
		BufferedImage img=null;
		try {
			img=ImageIO.read(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg1 = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image1 = new ImageIcon(dimg1);
		label.setIcon(image1);

		
		Image img2 = null;
		
		int minTime = this.TimeSlider.getValue()*100;
		int step = minTime/10;
		minTime = (int)(minTime * (10 * Math.random()));
		int i = 0;
		int change_img = (int)(minTime/2);
		while (minTime > 0) {
			try {
				Thread.sleep(step);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(minTime<=change_img) {
			try {
				img2 = ImageIO.read(getClass().getResource(imgs[i]))
						.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			label.setIcon(new ImageIcon(img2));
			i++;
			if(i==imgs.length)i=0;
			}
			minTime -= step;
		}
		
		try {
			img2 = ImageIO.read(getClass().getResource("/other/tbl1.png"))
					.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		label.setIcon(new ImageIcon(img2));
	}
}
