package input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.zeromq.ZMQ;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class InputUI extends JFrame {

	private JPanel contentPane;
	private ZMQ.Context context = ZMQ.context(1);
	private String inputAddress = "ipc://input:1111";
	private JFormattedTextField integerOneTextField;
	private JFormattedTextField integerTwoTextField;
	private JLabel lblOperationToSend;
	private String mathProblem;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputUI frame = new InputUI();
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
	public InputUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    pushToBusinessLogic();
			}


		});
		submitButton.setBounds(165, 213, 117, 25);
		contentPane.add(submitButton);
		
		integerOneTextField = new JFormattedTextField();
		integerOneTextField.setBounds(66, 29, 114, 19);
		contentPane.add(integerOneTextField);
		integerOneTextField.setColumns(10);
		
		JLabel lblInteger1 = new JLabel("Integer 1");
		lblInteger1.setBounds(66, 12, 70, 15);
		contentPane.add(lblInteger1);
		
		JLabel lblInteger2 = new JLabel("Integer 2");
		lblInteger2.setBounds(263, 12, 70, 15);
		contentPane.add(lblInteger2);
		
		integerTwoTextField = new JFormattedTextField();
		integerTwoTextField.setBounds(263, 29, 114, 19);
		contentPane.add(integerTwoTextField);
		integerTwoTextField.setColumns(10);
		
		JButton additionButton = new JButton("+");
		additionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			setUpEquation(integerOneTextField.getText(), integerTwoTextField.getText(), "+");
				
			}
		});
		additionButton.setBounds(92, 86, 44, 25);
		contentPane.add(additionButton);
		
		JLabel lblOperations = new JLabel("Operations");
		lblOperations.setBounds(180, 59, 87, 15);
		contentPane.add(lblOperations);
		
		JButton subtractButton = new JButton("-");
		subtractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUpEquation(integerOneTextField.getText(), integerTwoTextField.getText(), "-");
			}
		});
		subtractButton.setBounds(163, 86, 44, 25);
		contentPane.add(subtractButton);
		
		JButton multiplyButton = new JButton("x");
		multiplyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUpEquation(integerOneTextField.getText(), integerTwoTextField.getText(), "*");
			}
		});
		multiplyButton.setBounds(242, 86, 44, 25);
		contentPane.add(multiplyButton);
		
		JButton divisionButton = new JButton("/");
		divisionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUpEquation(integerOneTextField.getText(), integerTwoTextField.getText(), "/");
			}
		});
		divisionButton.setBounds(317, 86, 44, 25);
		contentPane.add(divisionButton);
		
		JLabel lblExpressionToCalculate = new JLabel("Expression to Calculate:");
		lblExpressionToCalculate.setBounds(133, 145, 181, 19);
		contentPane.add(lblExpressionToCalculate);
		
		lblOperationToSend = new JLabel("ex: Integer 1 + Integer 2");
		lblOperationToSend.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperationToSend.setBounds(35, 176, 378, 19);
		contentPane.add(lblOperationToSend);
	}			
	
	private void pushToBusinessLogic() {
		
			if (mathProblem == null){
				System.err.println("Error: Please enter Equation to submit");
				lblOperationToSend.setForeground(Color.red);
				lblOperationToSend.setText("Error: No equation set up to submit");
				
			} else {	
				System.out.println("Creating Socket ");
				ZMQ.Socket sender = context.socket(ZMQ.PUSH);
				
				System.out.println("Connecting to: " + inputAddress);
				sender.connect(inputAddress);
				
				System.out.println("Sending: " + mathProblem);
				sender.send(mathProblem.getBytes(), 0);
				
			}
			}
	
	private void setUpEquation(String i1, String i2, String op){
		try{
		
			Integer.parseInt(i1);
			Integer.parseInt(i2);
			lblOperationToSend.setForeground(Color.black);
			lblOperationToSend.setText(i1 + " " + op + " " + i2);
			mathProblem = i1 + " " + i2 + " " + op;
		} catch (NumberFormatException e){
			
			System.err.println("NumberFormatException Error: Please input integers for Integer1 and Integer2 text fields");
			lblOperationToSend.setForeground(Color.red);
			lblOperationToSend.setText("Set proper integers into Integer 1 and Integer 2");
			
		} 
		
		
		
	}
}
