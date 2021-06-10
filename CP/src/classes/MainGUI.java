package classes;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainGUI {

	private JFrame frame;
	private JTextField textFieldQue;
	private JLabel Table1;
	private JLabel Table2;
	private JLabel Table3;
	private JButton StartButton;
	private JButton FinishButton;
	private JTextField textFieldCounter;
	private JLabel StudLabel;
	private JSlider CreateSlider;
	private JSlider HandleSlider;

	private Thread thr1;
	private Thread thread1;
	private Thread thread2;
	private Thread thread3;
	private Thread thread4;

	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl4;
	private JTextField StudentCount;
	private JLabel lbl_bg;
	private JLabel lbl3;
	private JLabel Table4;
	private JSlider stepTimeSlider;
	private Sound play;
	private JLabel decor_lbl;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public MainGUI() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setTitle("\u041A\u0443\u0440\u0441\u043E\u0432\u0438\u0439 \u043F\u0440\u043E\u0435\u043A\u0442 \"\u0415\u043A\u0437\u0430\u043C\u0435\u043D\". \u0412\u0438\u043A\u043E\u043D\u0430\u043B\u0438: \u041A\u043E\u043B\u043E\u043C\u0456\u0454\u0446\u044C \u041C.\u042E. \u0442\u0430 \u0413\u0443\u0434\u043A\u043E\u0432\u0430 \u0410.\u042E.");
		frame.setBounds(100, 100, 1016, 769);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		decor_lbl = new JLabel("");
		decor_lbl.setBounds(267, 10, 129, 146);
		frame.getContentPane().add(decor_lbl);
		Image imD = ImageIO.read(MainGUI.class.getResource("/other/decor.png"))
				.getScaledInstance(decor_lbl.getWidth(), decor_lbl.getHeight(), Image.SCALE_SMOOTH);
		decor_lbl.setIcon(new ImageIcon(imD));
		
		JLabel house = new JLabel("");
		house.setBounds(844, 232, 129, 146);
		frame.getContentPane().add(house);
		Image imagehouse = ImageIO.read(MainGUI.class.getResource("/other/go_home.png"))
				.getScaledInstance(house.getWidth(), house.getHeight(), Image.SCALE_SMOOTH);
		house.setIcon(new ImageIcon(imagehouse));
		
		
		JLabel lbl3_2 = new JLabel("\u0414\u043E\u0434\u043E\u043C\u0443");
		lbl3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbl3_2.setBounds(865, 373, 97, 33);
		frame.getContentPane().add(lbl3_2);
		
		JLabel lbl3_1 = new JLabel("\u0427\u0435\u0440\u0433\u0430");
		lbl3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbl3_1.setBounds(179, 300, 84, 33);
		frame.getContentPane().add(lbl3_1);
		
		stepTimeSlider = new JSlider();
		stepTimeSlider.setFont(new Font("Segoe UI", Font.BOLD, 12));
		stepTimeSlider.setValue(6);
		stepTimeSlider.setPaintTicks(true);
		stepTimeSlider.setPaintLabels(true);
		stepTimeSlider.setMinimum(3);
		stepTimeSlider.setMaximum(10);
		stepTimeSlider.setMajorTickSpacing(1);
		stepTimeSlider.setBounds(31, 507, 201, 61);
		frame.getContentPane().add(stepTimeSlider);


		CreateSlider = new JSlider();
		CreateSlider.setFont(new Font("Segoe UI", Font.BOLD, 12));
		CreateSlider.setPaintTicks(true);
		CreateSlider.setPaintLabels(true);
		CreateSlider.setMajorTickSpacing(100);
		CreateSlider.setValue(300);
		CreateSlider.setMaximum(500);
		CreateSlider.setMinimum(100);
		CreateSlider.setBounds(31, 46, 201, 55);
		frame.getContentPane().add(CreateSlider);

		textFieldQue = new JTextField();
		textFieldQue.setText("0");
		textFieldQue.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldQue.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textFieldQue.setBounds(192, 333, 60, 40);
		frame.getContentPane().add(textFieldQue);
		textFieldQue.setColumns(10);

		Table1 = new JLabel("");
		Table1.setBounds(405, 153, 287, 116);
		frame.getContentPane().add(Table1);

		Table2 = new JLabel("");
		Table2.setBounds(405, 275, 287, 116);
		frame.getContentPane().add(Table2);

		Table3 = new JLabel("");
		Table3.setBounds(405, 401, 287, 116);
		frame.getContentPane().add(Table3);
		
		Table4 = new JLabel("");
		Table4.setBounds(405, 526, 287, 116);
		frame.getContentPane().add(Table4);

		StartButton = new JButton("Start");
		StartButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doRun();
			}
		});
		StartButton.setBounds(844, 630, 97, 33);
		frame.getContentPane().add(StartButton);
       
		FinishButton = new JButton("End");
		FinishButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		FinishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainGUI.this.EndOfWork();
				doStopPlay();
			}
		});
		FinishButton.setBounds(844, 673, 97, 33);
		frame.getContentPane().add(FinishButton);

		textFieldCounter = new JTextField();
		textFieldCounter.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textFieldCounter.setText("0");
		textFieldCounter.setColumns(10);
		textFieldCounter.setBounds(891, 405, 50, 40);
		frame.getContentPane().add(textFieldCounter);

		StudLabel = new JLabel("");
		StudLabel.setBounds(31, 262, 60, 129);
		frame.getContentPane().add(StudLabel);
		Image imageStud = ImageIO.read(MainGUI.class.getResource("/other/sad_stud.png"))
				.getScaledInstance(StudLabel.getWidth(), StudLabel.getHeight(), Image.SCALE_SMOOTH);
		StudLabel.setIcon(new ImageIcon(imageStud)); 
		
		Image image1 = ImageIO.read(MainGUI.class.getResource("/other/tbl1.png"))
				.getScaledInstance(Table4.getWidth(), Table4.getHeight(), Image.SCALE_SMOOTH);
		Table1.setIcon(new ImageIcon(image1));
		Image image2 = ImageIO.read(MainGUI.class.getResource("/other/tbl1.png"))
				.getScaledInstance(Table4.getWidth(), Table4.getHeight(), Image.SCALE_SMOOTH);
		Table2.setIcon(new ImageIcon(image2));
		Image image3 = ImageIO.read(MainGUI.class.getResource("/other/tbl1.png"))
				.getScaledInstance(Table4.getWidth(), Table4.getHeight(), Image.SCALE_SMOOTH);
		Table3.setIcon(new ImageIcon(image3));
		Image image4 = ImageIO.read(MainGUI.class.getResource("/other/tbl1.png"))
				.getScaledInstance(Table4.getWidth(), Table4.getHeight(), Image.SCALE_SMOOTH);
		Table4.setIcon(new ImageIcon(image4));

		HandleSlider = new JSlider();
		HandleSlider.setFont(new Font("Segoe UI", Font.BOLD, 12));
		HandleSlider.setPaintTicks(true);
		HandleSlider.setValue(6);
		HandleSlider.setPaintLabels(true);
		HandleSlider.setMinimum(3);
		HandleSlider.setMaximum(10);
		HandleSlider.setMajorTickSpacing(1);
		HandleSlider.setBounds(450, 46, 212, 55);
		frame.getContentPane().add(HandleSlider);
		
		lbl1 = new JLabel("\u0427\u0430\u0441 \u0437\u0434\u0430\u0447\u0456 \u0456\u0441\u043F\u0438\u0442\u0443");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbl1.setBounds(450, 10, 212, 40);
		frame.getContentPane().add(lbl1);
		
		lbl2 = new JLabel("\u0427\u0430\u0441 \u043F\u0440\u0438\u0431\u0443\u0442\u0442\u044F");
		lbl2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setBounds(31, 14, 201, 33);
		frame.getContentPane().add(lbl2);
		
		lbl4 = new JLabel("\u0427\u0430\u0441 \u0440\u0443\u0445\u0443");
		lbl4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbl4.setBounds(31, 469, 201, 40);
		frame.getContentPane().add(lbl4);
		
		StudentCount = new JTextField();
		StudentCount.setHorizontalAlignment(SwingConstants.CENTER);
		StudentCount.setFont(new Font("Segoe UI", Font.BOLD, 18));
		StudentCount.setColumns(10);
		StudentCount.setBounds(102, 184, 60, 47);
	    StudentCount.setText("25");
		frame.getContentPane().add(StudentCount);
		
		lbl3 = new JLabel("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0441\u0442\u0443\u0434\u0435\u043D\u0442\u0456\u0432");
		lbl3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbl3.setBounds(51, 153, 170, 33);
		frame.getContentPane().add(lbl3);
			
		lbl_bg = new JLabel("");
		lbl_bg.setBounds(0, 0, 1002, 732);
		frame.getContentPane().add(lbl_bg);
		Image imageBg = ImageIO.read(MainGUI.class.getResource("/other/bg_1.png"))
				.getScaledInstance(lbl_bg.getWidth(), lbl_bg.getHeight(), Image.SCALE_SMOOTH);
		lbl_bg.setIcon(new ImageIcon(imageBg));
		
		StudentCount.addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if ( ((c < '0') || (c > '9'))) {
			         e.consume(); 
			      }
			   }
			});
	}

	protected void doRun() {
		    StudentCount.setEnabled(false);
			this.StartButton.setEnabled(false);
			final Counter counter = new Counter(this.textFieldCounter);
			final QueueWithCounter queue = new QueueWithCounter(this, this.textFieldQue);
			final Creator creator1 = new Creator(this, this.StudLabel, queue, this.CreateSlider, this.StudentCount);
			final Handler handler1 = new Handler(this, this.Table1, queue, this.HandleSlider, counter);
			final Handler handler2 = new Handler(this, this.Table2, queue, this.HandleSlider, counter);
			final Handler handler3 = new Handler(this, this.Table3, queue, this.HandleSlider, counter);
			final Handler handler4 = new Handler(this, this.Table4, queue, this.HandleSlider, counter);
			(this.thr1 = new Thread(creator1)).start();
			(this.thread1 = new Thread(handler1)).start();
			(this.thread2 = new Thread(handler2)).start();
			(this.thread3 = new Thread(handler3)).start();
			(this.thread4 = new Thread(handler4)).start();
			URL u = this.getClass().getResource("/other/music2.wav");
			this.play = Sound.playSound(u);
	        playMusic();    
	}
	private void playMusic() {
	    if(this.play.isPlaying()==false) {this.play.play();}
	 }
	
	private void doStopPlay() {
	    this.play.stop();
	 }
	
	public Component getPane() {
		return this.frame.getContentPane();
	}

	public boolean isCreatorWorking() {
		return this.thr1.isAlive();
	}
	
	private void EndOfWork() {
        new Thread() {
            @Override
            public void run() {
                try {
                	thr1.stop();
                    StartButton.setEnabled(true);
        		    StudentCount.setEnabled(true);
                    thread1.join();
                    thread2.join();
                    thread3.join();
                    thread4.join();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
	}

	public JButton getBtnNewButton() {
		return StartButton;
	}
	public JLabel getStudLabel() {
		return StudLabel;
	}
	public JSlider getCreateSlider() {
		return CreateSlider;
	}
	public JSlider getHandleSlider_1() {
		return HandleSlider;
	}
	public JSlider getStepTimeSlider() {
		return stepTimeSlider;
	}
	public JTextField getTextField() {
		return StudentCount;
	}
}
