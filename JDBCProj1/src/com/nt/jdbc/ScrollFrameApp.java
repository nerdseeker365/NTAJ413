package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
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



public class ScrollFrameApp extends JFrame implements ActionListener {
	private static final String GET_ALL_STUDENTS="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	private JLabel lno,lname,ladd,lavg;
	private JTextField tno,tname,taddrs,tavg;
	private JButton bFirst,bLast,bPrevious,bNext;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public ScrollFrameApp() {
		System.out.println("ScrollFrameApp:: 0-param constructor");
		setTitle("GUI-ScrollFrameApp");
		setSize(300,300);
		setLayout(new FlowLayout());
		setBackground(Color.gray);
		//add comps
		 lno=new JLabel("sno");
		 add(lno);
		 tno=new JTextField(10);
		 add(tno);
		 lname=new JLabel("sname");
		 add(lname);
		 tname=new JTextField(10);
		 add(tname);
		 ladd=new JLabel("sadd");
		 add(ladd);
		 taddrs=new JTextField(10);
		 add(taddrs);
		 lavg=new JLabel("avg");
		 add(lavg);
		 tavg=new JTextField(10);
		 add(tavg);
		 bFirst=new JButton("First");
		 add(bFirst);
		 bFirst.addActionListener(this);
		 bNext=new JButton("next");
		 add(bNext);
		 bNext.addActionListener(this);
		 bPrevious=new JButton("previous");
		 add(bPrevious);
		 bPrevious.addActionListener(this);
		 bLast=new JButton("Last");
		 add(bLast);
		 bLast.addActionListener(this);
		 setVisible(true);
		 intializeJdbc();
		 //disable editing on Text boxes
		 tno.setEditable(false);
		 tname.setEditable(false);
		 taddrs.setEditable(false);
		 tavg.setEditable(false);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.addWindowListener(new WindowAdapter() {
			  @Override
			public void windowClosing(WindowEvent e) {
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
			}//windowClosing
		}//anynomous inner class
		 ); //method call
	}//constructor
	
	private  void  intializeJdbc() {
		System.out.println("ScrollFrameApp.intializeJdbc()");
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create JDBC Statement object
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					                                     ResultSet.CONCUR_UPDATABLE);
			//create ScrollableResultSet object
			rs=st.executeQuery(GET_ALL_STUDENTS);
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
	
	

	public static void main(String[] args) {
		System.out.println("main(-)");
		 new ScrollFrameApp();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("ScrollFrameApp.actionPerformed()");
		boolean flag=false;
		System.out.println(ae.getActionCommand());
		if(ae.getSource()==bFirst) {
			try {
			System.out.println("First Button");
			rs.first();
			 flag=true;
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
		}
		else if(ae.getSource()==bNext) {
			System.out.println("next button");
			try {
				if(!rs.isLast()) {
				    rs.next();
				   flag=true;
				  }
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
		}
		else if(ae.getSource()==bPrevious) {
			try {
				System.out.println("Previous Button");
				if(!rs.isFirst()) {
			    	rs.previous();
				 flag=true;
				}
			}
				catch(SQLException se) {
					se.printStackTrace();
				}
		}
		else {
			System.out.println("Last Button");
			try {
				rs.last();
				 flag=true;
			}
				catch(SQLException se) {
					se.printStackTrace();
				}
		}//else
		 //read values from ResultSet and set to Text boxes
		 if(flag==true) {
			 try {
			 tno.setText(rs.getString(1));
			 tname.setText(rs.getString(2));
			 taddrs.setText(rs.getString(3));
			 tavg.setText(rs.getString(4));
			 }
			 catch(SQLException se) {
				 se.printStackTrace();
			 }
			 
		 }//if
	}//actionPerformed(-)

}//class
