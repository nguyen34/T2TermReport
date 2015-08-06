package input;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.zeromq.ZMQ;

public class UI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ZMQ.Context context = ZMQ.context(1);
	private String connectionRequestAddress = "tcp://localhost:5555";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		
		textField = new JTextField();
		textField.setBounds(140, 90, 177, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    System.out.println("Creating Socket ");
				ZMQ.Socket requester = context.socket(ZMQ.REQ);
				
				System.out.println("Connecting to: " + connectionRequestAddress);
				requester.connect(connectionRequestAddress);
				
				String request = textField.getText();
				
				System.out.println("Sending: " + request);
				requester.send(request.getBytes(), 0);
				
				byte[] reply = requester.recv(0);
				System.out.println(new String(reply));
				
			}
		});
		submitButton.setBounds(169, 121, 117, 25);
		contentPane.add(submitButton);
	}

}
