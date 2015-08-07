package output;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.zeromq.ZMQ;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI extends JFrame {

	private JPanel contentPane;
	private JLabel outputLabel;
	private static ZMQ.Context context = ZMQ.context(2);
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
				}
					   
					   
					   
					   catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String connectToBusinessLogic() {
		System.out.println("Creating  socket...");
		ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
		subscriber.connect(outputPublishPort);
		System.out.println("Socket Created...");
		
		String filter = "";
		subscriber.subscribe(filter.getBytes());
		
		byte[] publ = subscriber.recv(0);
		String publishedString = new String(publ);
		System.out.println("Received: " + publishedString);
		return publishedString;
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		outputLabel = new JLabel("This is temporary message");
		outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		outputLabel.setBounds(83, 97, 258, 37);
		contentPane.add(outputLabel);
		
		JButton requestButton = new JButton("Request Input String");
		requestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String result = connectToBusinessLogic();
				if (!result.equals(null)){
				outputLabel.setText(result);
			} else {
				System.err.println("Error: Result is Null");
			}
			}
		});
		requestButton.setBounds(116, 146, 184, 25);
		contentPane.add(requestButton);
		
	
		
	
		
		
	}
}
