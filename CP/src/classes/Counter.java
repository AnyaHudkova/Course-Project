package classes;

import java.awt.Component;

import javax.swing.JTextField;

public class Counter implements IfromTo {
	private JTextField textField;
    private int count;
    
    public Counter(final JTextField textField) {
        this.textField = textField;
        this.setCount(0);
    }
    
    private void setCount(final int n) {
        this.count = n;
        this.textField.setText(String.valueOf(this.count));
    }
    
    
    @Override
    public void onIn(final Transaction tr) {
        this.setCount(++this.count);
    }
    
    @Override
    public Component getComponent() {
        return this.textField;
    }
}
