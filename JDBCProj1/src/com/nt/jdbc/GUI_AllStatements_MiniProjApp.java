package com.nt.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



/*CREATE TABLE "SYSTEM"."ALL_STUDENT" 
(	"SNO" NUMBER NOT NULL ENABLE, 
	"SNAME" VARCHAR2(20 BYTE), 
	"M1" NUMBER(3,0), 
	"M2" NUMBER(3,0), 
	"M3" NUMBER(3,0), 
	 CONSTRAINT "ALL_STUDENT_PK" PRIMARY KEY ("SNO"));
*/

/*CREATE OR REPLACE PROCEDURE FIND_PASS_FAIL 
(
  M1 IN NUMBER 
, M2 IN NUMBER 
, M3 IN NUMBER 
, RESULT OUT VARCHAR2 
) AS 
BEGIN
   IF(M1<35 or M2<35 or M3 <35)THEN
     RESULT:='FAIL';
    ELSE
      RESULT:='PASS';
  END IF;  

END FIND_PASS_FAIL;
*/

public class GUI_AllStatements_MiniProjApp extends  JFrame implements ActionListener{
	private static final String GET_STUDENT_DETAILS_BY_SNO="SELECT SNAME,M1,M2,M3 FROM ALL_STUDENT WHERE SNO=?";
	private static final String CALL_PROCEDURE="{CALL FIND_PASS_FAIL(?,?,?,?)} ";
	private static final String  GET_ALL_SNOS="SELECT SNO FROM ALL_STUDENT";
	private  JLabel lno,lname,lm1,lm2,lm3,lresult;
	 private  JTextField tname,tm1,tm2,tm3,tresult;
	 private  JComboBox<Integer> cbno;
	 private  JButton  bDetails,bResult;
	 private Connection con;
	 private Statement st;
	 private PreparedStatement ps;
	 private CallableStatement cs;
	 private ResultSet rs1,rs2;
	 
	 public GUI_AllStatements_MiniProjApp() {
		 System.out.println("0-param constructor");
		 setTitle("AllStatatements _MiniProject");
		 setBackground(Color.GRAY);
		 setLayout(new FlowLayout());
		 setSize(400,300);
		 //add comps
		 lno=new JLabel("student number::");
		 add(lno);
		 cbno=new JComboBox();
		 add(cbno);
		 bDetails=new JButton("details");
		 add(bDetails);
		 bDetails.addActionListener(this);
		 lname=new JLabel("student name::");
		 add(lname);
		 tname=new JTextField(10);
		 add(tname);
		 lm1=new JLabel("Marks1::");
		 add(lm1);
		 tm1=new JTextField(10);
		 add(tm1);
		 lm2=new JLabel("Marks2::");
		 add(lm2);
		 tm2=new JTextField(10);
		 add(tm2);
		 lm3=new JLabel("Marks3::");
		 add(lm3);
		 tm3=new JTextField(10);
		 add(tm3);
		 bResult=new JButton("result");
		 add(bResult);
		 bResult.addActionListener(this);
		 lresult=new JLabel("Result is");
		 add(lresult);
		 tresult=new JTextField(10);
		 add(tresult);
		 //disable editing of textfields
		 tname.setEditable(false);
		 tm1.setEditable(false);
		 tm2.setEditable(false);
		 tm3.setEditable(false);
		 tresult.setEditable(false);
		 
		 
		 setVisible(true);
		 initializeJdbc();
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 this.addWindowListener(new WindowAdapter() {
			  @Override
			public void windowClosing(WindowEvent e) {
				  System.out.println("windowClosing(-)");
				//close jdbc objs
				 try {
					 if(rs1!=null)
						 rs1.close();
				 }
				 catch(SQLException se) {
					 se.printStackTrace();
				 }
				 try {
					 if(rs2!=null)
						 rs2.close();
				 }
				 catch(SQLException se) {
					 se.printStackTrace();
				 }
				 try {
					 if(cs!=null)
						 cs.close();
				 }
				 catch(SQLException se) {
					 se.printStackTrace();
				 }
				 try {
					 if(ps!=null)
						 ps.close();
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
	}//constructor
	
	 
	 private  void  initializeJdbc() {
		 System.out.println("GUI_AllStatements_MiniProjApp.initializeJdbc()");
		 try {
			 //register JDBC driver s/w
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			 //create Simple Statement object
			 st=con.createStatement();
			 //create PreparedStatment obj
			 ps=con.prepareStatement(GET_STUDENT_DETAILS_BY_SNO);
			 //create CallableStatement object
			 cs=con.prepareCall(CALL_PROCEDURE);
			 //regsitering OUT param with JDBC type
			 cs.registerOutParameter(4,Types.VARCHAR);
			 //load snos into ComboBox during Application startup
			 rs1=st.executeQuery(GET_ALL_SNOS);
			 while(rs1.next()) {
				 cbno.addItem(rs1.getInt(1));
			 }
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
		System.out.println("GUI_AllStatements_MiniProjApp.main()");
		new GUI_AllStatements_MiniProjApp();

	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("GUI_AllStatements_MiniProjApp::actionPerformed(-)");
		int no=0;
		int m1=0,m2=0,m3=0;
		String result=null;
		 if(ae.getSource()==bDetails) {
			 System.out.println("Details Button is clicked");
			 try {
				 //get Selected item from ComboBox
				 no=(int) cbno.getSelectedItem();
				 //set the above number as query param value
				 ps.setInt(1,no);
				 //execute the Query
				 rs2=ps.executeQuery();
				 //set Resulset record values to text boxes
				 if(rs2.next()) {
					 tname.setText(rs2.getString(1));
					 tm1.setText(rs2.getString(2));
					 tm2.setText(rs2.getString(3));
					 tm3.setText(rs2.getString(4));
					}
			 }//try
			 catch(SQLException se) {
				 se.printStackTrace();
			 }
			 catch(Exception e) {
				 e.printStackTrace();
			 }
			 
		 }//if
		 else {
			 System.out.println("result Button is clicked");
			 try {
				 //read m1,m2,m3 values from text boxes
				 m1=Integer.parseInt(tm1.getText());
				 m2=Integer.parseInt(tm2.getText());
				 m3=Integer.parseInt(tm3.getText());
				 //set values to PL/SQL procedure IN params
				 cs.setInt(1,m1);
				 cs.setInt(2,m2);
				 cs.setInt(3,m3);
				 //call PL/SQL procedure
				 cs.execute();
				 //gather result from OUT param
				 result=cs.getString(4);
				 //set result to text box
				 tresult.setText(result);
			 }//try
			 catch(SQLException se) {
				 se.printStackTrace();
			 }
			 catch(Exception e) {
				 e.printStackTrace();
			 }
		 }//else
	}//actionPerformed(-)
}//class
