package com.nt.jdbc;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIScrollFrameApp1 {
   private  static final  String  ALL_STUDENTS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Connection con;
	private Statement st;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIScrollFrameApp1 window = new GUIScrollFrameApp1();
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
	public GUIScrollFrameApp1() {
		initialize();
		jdbcInitialize();
	}

	private  void  jdbcInitialize() {
		try {
			//register JDBC driver 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create Statement object
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//create ScrollableResultSet object
			rs=st.executeQuery(ALL_STUDENTS_QUERY);
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//add windowListener support to close jdbc objs
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				System.out.println("GUIScrollFrameApp1::windowClosing()");
			     //close jdbc objs
				try {
					if(rs!=null)
						rs.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(st!=null)
						st.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(con!=null)
						con.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		
		
		JLabel lblNewLabel = new JLabel("sno");
		lblNewLabel.setBounds(41, 23, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(142, 20, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("sname");
		lblNewLabel_1.setBounds(41, 78, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(142, 75, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("sadd");
		lblNewLabel_2.setBounds(41, 137, 56, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(142, 134, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("avg");
		lblNewLabel_3.setBounds(41, 185, 56, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(142, 182, 116, 22);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		//disable editing
		textField.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);
		
		JButton btnNewButton = new JButton("first");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			   try {
				   rs.first();
				   //set the record values text boxes
				   textField.setText(rs.getString(1));
				   textField_1.setText(rs.getString(2));
				   textField_2.setText(rs.getString(3));
				   textField_3.setText(rs.getString(4));
			   }//try
			   catch(SQLException se) {
				   se.printStackTrace();
			   }
			}//try
			
		});
		btnNewButton.setBounds(12, 214, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {
					   if(!rs.isLast()) {
						   rs.next();
					   //set the record values text boxes
					   textField.setText(rs.getString(1));
					   textField_1.setText(rs.getString(2));
					   textField_2.setText(rs.getString(3));
					   textField_3.setText(rs.getString(4));
					   }
				   }//try
				   catch(SQLException se) {
					   se.printStackTrace();
				   }
				
			}
		});
		btnNewButton_1.setBounds(121, 214, 97, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("previous");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {
					 if(!rs.isFirst()) {
					   rs.previous();
					   //set the record values text boxes
					   textField.setText(rs.getString(1));
					   textField_1.setText(rs.getString(2));
					   textField_2.setText(rs.getString(3));
					   textField_3.setText(rs.getString(4));
					 }
				   }//try
				   catch(SQLException se) {
					   se.printStackTrace();
				   }
			}
		});
		btnNewButton_2.setBounds(247, 217, 97, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("last");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  try {
					   rs.last();
					   //set the record values text boxes
					   textField.setText(rs.getString(1));
					   textField_1.setText(rs.getString(2));
					   textField_2.setText(rs.getString(3));
					   textField_3.setText(rs.getString(4));
				   }//try
				   catch(SQLException se) {
					   se.printStackTrace();
				   }
				
			}
		});
		btnNewButton_3.setBounds(356, 214, 101, 25);
		frame.getContentPane().add(btnNewButton_3);
	}
}
