package classes;

import java.awt.Component;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.swing.JTextField;

public class QueueWithCounter implements IfromTo {

	private Deque<Transaction> que;
    protected MainGUI gui;
    private JTextField label;
    public QueueWithCounter(final MainGUI gui,final JTextField label) {
        this.gui = gui;
        this.label = label;
        this.que = new ArrayDeque<Transaction>();
    }
    
    public void addLast(final Transaction transaction) {
            this.que.addLast(transaction);
            this.label.setText(String.valueOf(this.que.size()));
    }
    
    public Transaction removeFirst() {
        if (!this.que.isEmpty()) {
            final Transaction x = this.que.removeFirst();
            this.label.setText(String.valueOf(this.que.size()));
            return x;
        }
        return null;
    }
    
    public int getQueueSize() {
        return this.que.size();
    }
    
    @Override
    public void onIn(final Transaction tr) {
        synchronized (this) {
                this.addLast(tr);
                this.notify();
        }
    }
    
    @Override
    public Component getComponent() {
        return this.label;
    }

}
