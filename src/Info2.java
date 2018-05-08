

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.mysql.jdbc.PreparedStatement;

import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;

public class Info2 extends JFrame{

	private JFrame frame;
	private String connectionUrl = "jdbc:mysql://localhost:3306/userInfo?autoReconnect=true&useSSL=false";
	private String dbUser = "root";
	private String dbPwd = "arif123";
	private Connection conn ;

	private java.sql.PreparedStatement stmt;
	private JTable table;
	/**
	 * Create the application.
	 */
	public Info2() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setBackground(Color.DARK_GRAY);
		setSize(new Dimension(450, 300));
		getContentPane().setLayout(null);
		
		JLabel lblInformationDisplay = new JLabel("Information Display");
		lblInformationDisplay.setForeground(Color.WHITE);
		lblInformationDisplay.setBounds(142, 33, 176, 15);
		getContentPane().add(lblInformationDisplay);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 71, 395, 186);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(new Dimension(300, 250));
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		
		try {
			stmt = conn.prepareStatement("Select * from Person;");
			ResultSet rs = stmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e1) {
			 // TODO Auto-generated catch block
			  String error = "SQL Exception thrown: " + e1 ;
			  JOptionPane.showMessageDialog(null,error);
		}
		
	}
}
