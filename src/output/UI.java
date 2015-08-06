package output;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.zeromq.ZMQ;

public class UI extends JFrame {

	private JPanel contentPane;
	private JLabel outputLabel;
	private static ZMQ.Context context = ZMQ.context(1);
	private static String outputPublishPort = "tcp://localhost:5556";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);	
					
					
				//	connectToBusinessLogic();
				}
					   
					   
					   
					   catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void connectToBusinessLogic() {
		System.out.println("Creating subscription socket...");
		ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
		subscriber.connect(outputPublishPort);
		
		System.out.println("Setting Filter...");
		String filter = "";
		subscriber.subscribe(filter.getBytes());
		
		   while (!Thread.currentThread().isInterrupted()) {
		
		

		

		byte[] publ = subscriber.recv();
		String publishedString = new String(publ);
		System.out.println("Received: " + publishedString);

//	outputLabel.setText(publishedString);
}
		   subscriber.close();
		   context.term();
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		outputLabel = new JLabel("This is temporary message");
		outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		outputLabel.setBounds(84, 99, 258, 35);
		contentPane.add(outputLabel);
		
	
		
	
		
		
	}

}
