package classes;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class Creator extends AbstractWorker{
	JTextField StudentCountF;
	int countStudent;
	public Creator(final MainGUI gui, final JLabel label, 
			final QueueWithCounter queue, final JSlider TimeSlider, JTextField StudentCountF) {
		super(gui, label, queue, TimeSlider);
		this.countStudent = Integer.valueOf(StudentCountF.getText());
		this.StudentCountF = StudentCountF;
	}

	@Override
	public void run() {
		while (countStudent>0) {
			--countStudent;
			this.StudentCountF.setText(Integer.toString(countStudent));
			this.showWorking();
			this.trs = new Transaction(this.gui);
			final Thread t = this.trs.moveFromTo(this, this.queue);
			try {
				t.join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
	}
}
