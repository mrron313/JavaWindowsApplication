
import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

public class Frame1 extends JFrame {

	private JFrame frame;
	private JTextField phoneTxt;
	private JTextField mailTxt;
	private JTextField idTxt;
	private JTextField nameTxt;
	private JLabel lblPhone;
	
	private int personId;
	private String personName;
	private String personPhone;
	private String personMail;
	
	private String connectionUrl = "jdbc:mysql://localhost:3306/userInfo?autoReconnect=true&useSSL=false";
	private String dbUser = "root";
	private String dbPwd = "arif123";
	private Connection conn ;

	private Statement stmt;
	private JLabel lblEmail_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame1() {

		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.DARK_GRAY);
		frame.setSize(new Dimension(300, 250));
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSum = new JButton("Submit");
		
		try
		{
		    // loads com.mysql.jdbc.Driver into memory
		    Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException cnf) 
		{
			String error = "Driver could not be loaded: " + cnf ;
			JOptionPane.showMessageDialog(null,error);
		}
		
		try {
			conn = DriverManager.getConnection(connectionUrl, dbUser , dbPwd );
		}
		catch (SQLException sqle) 
		{
			String error = "SQL Exception thrown: " + sqle ;
			JOptionPane.showMessageDialog(null,error);
		}
		
		
		btnSum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (phoneTxt.getText().equals("") || mailTxt.getText().equals("") || idTxt.getText().equals("") || nameTxt.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Please insert.");
				}
				else {
					personId = Integer.parseInt(idTxt.getText());
					personName = nameTxt.getText();
					personPhone = phoneTxt.getText();
					personMail = mailTxt.getText();

					try {
						stmt = conn.createStatement();
						int result = stmt.executeUpdate("INSERT INTO Person (personId,firstName,email,phone) VALUES ( '"+personId+"', '"+personName+"' , '"+personMail+"' , '"+personPhone+"' )");
						if(result==1) {
							JOptionPane.showMessageDialog(null,"Successfully inserted!");
							frame.dispose();
							Info2 frame2 = new Info2();
							frame2.setVisible(true);
					}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						String error = "SQL Exception thrown: " + e1 ;
						JOptionPane.showMessageDialog(null,error);
					}
					
				}
				
			}
		});
		
		
		btnSum.setBounds(279, 197, 117, 19);
		frame.getContentPane().add(btnSum);
		
		phoneTxt = new JTextField();
		phoneTxt.setBounds(89, 126, 114, 19);
		frame.getContentPane().add(phoneTxt);
		phoneTxt.setColumns(10);
		
		mailTxt = new JTextField();
		mailTxt.setColumns(10);
		mailTxt.setBounds(279, 126, 114, 19);
		frame.getContentPane().add(mailTxt);
		
		idTxt = new JTextField();
		idTxt.setBounds(89, 84, 114, 19);
		frame.getContentPane().add(idTxt);
		idTxt.setColumns(10);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(279, 84, 114, 19);
		frame.getContentPane().add(nameTxt);
		nameTxt.setColumns(10);
		
		JLabel lblEntryForm = new JLabel("Entry Form");
		lblEntryForm.setForeground(Color.WHITE);
		lblEntryForm.setBounds(191, 35, 100, 15);
		frame.getContentPane().add(lblEntryForm);
		
		lblPhone = new JLabel("ID");
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setBounds(28, 86, 70, 15);
		frame.getContentPane().add(lblPhone);
		
		JLabel lblId = new JLabel("Phone");
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(28, 128, 70, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblEmail = new JLabel("Name");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(221, 86, 70, 15);
		frame.getContentPane().add(lblEmail);
		
		lblEmail_1 = new JLabel("Email");
		lblEmail_1.setForeground(Color.WHITE);
		lblEmail_1.setBounds(221, 128, 70, 15);
		frame.getContentPane().add(lblEmail_1);

	}
}
