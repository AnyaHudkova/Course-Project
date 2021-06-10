package classes;
import java.awt.Component;
import javax.swing.JSlider;
import javax.swing.JLabel;

public abstract class AbstractWorker implements Runnable, IfromTo
{
    protected MainGUI gui;
    protected JLabel label;
    protected JSlider TimeSlider;
    protected QueueWithCounter queue;
    protected Transaction trs;
    
    public AbstractWorker(final MainGUI gui, final JLabel label, final QueueWithCounter queue, final JSlider TimeSlider) {
        this.gui = gui;
        this.label = label;
        this.queue = queue;
        this.TimeSlider = TimeSlider;
    }
    
    protected void showWorking() {
        final int n = 10;
        final int minMilsTime = this.TimeSlider.getValue();
        final int step = minMilsTime / n;
        int milsTime = (int)(minMilsTime * (4.0 * Math.random()));
        milsTime = milsTime / step * step;
        while (milsTime > 0) {
            try {
                Thread.sleep(step);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            milsTime -= step;
        }
    }
    
    @Override
    public void onIn(final Transaction tr) {
    }
    
    @Override
    public Component getComponent() {
        return this.label;
    }
}
