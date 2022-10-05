package com.barber.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.barber.dao.UserDao;
import com.barber.model.User;
import com.barber.util.DbUtil;
import com.barber.util.StringUtil;


import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class LogInFrm extends JFrame {

	private JPanel contentPane;
	private JTextField textUserName;
	private JPasswordField UsrPassword;
	private JPasswordField AdminPassword;
	private JTextField textAdminUsrName;
	private JTextField textRegMail;
	private JTextField textRegName;
	private JPasswordField RegPass1;
	private JPasswordField RegPass2;
	
	
	DbUtil dbUtil = new DbUtil();
	UserDao userDao = new UserDao();
	
	
	
	private JPanel panelReg;
	private JPanel panelUsrLogin;
	private JPanel panelAdminLogin;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrm frame = new LogInFrm();
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
	public LogInFrm() {
		setTitle("Log in panel");
		setResizable(false);
		
		//////////////////////////////////////////////////////////////////////
//		panelAdminLogin.setVisible(true);///
//		panelUsrLogin.setVisible(false);//// Making User login panel visible by default
//		panelReg.setVisible(false);/////////
//		/////////////////////////////////////////////////////////////////////
//		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 550, 410);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelAdminLogin = new JPanel();
		panelAdminLogin.setVisible(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(0, -11, 302, 389);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(-582, 12, 1149, 532);
		label.setIcon(new ImageIcon(LogInFrm.class.getResource("/img/Lwall.jpg")));
		panel_1.add(label);
		
		panelUsrLogin = new JPanel();
		panelUsrLogin.setBackground(Color.WHITE);
		panelUsrLogin.setBounds(298, -11, 248, 389);
		contentPane.add(panelUsrLogin);
		
		
		
		textUserName = new JTextField();
		textUserName.setBounds(47, 113, 170, 30);
		textUserName.setColumns(10);
		
		UsrPassword = new JPasswordField();
		UsrPassword.setBounds(47, 173, 170, 30);
		
		JLabel lblLogIn = new JLabel("User log in");
		lblLogIn.setBounds(64, 35, 136, 30);
		lblLogIn.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JButton btnUsrLogin = new JButton("Log in");
		btnUsrLogin.setBackground(new Color(245, 222, 179));
		btnUsrLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);   /////////////////////////////// Login///////////////////////////
				
				
			}
		});
		btnUsrLogin.setBounds(81, 221, 136, 25);
		
		JButton btnRstrPnl = new JButton("Create a new account");
		btnRstrPnl.setBorder(null);
		btnRstrPnl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelUsrLogin.setVisible(false);
				panelReg.setVisible(true);
				panelAdminLogin.setVisible(false);  ///////////////////// Switching Between Jpanels ////////////////////////
				
							
			}
		});
		btnRstrPnl.setBounds(28, 288, 200, 25);
		btnRstrPnl.setFont(new Font("Dialog", Font.BOLD, 11));
		btnRstrPnl.setBackground(Color.WHITE);
		btnRstrPnl.setForeground(Color.BLUE);
		
		JButton btnAdminPnl = new JButton("Admin");
		btnAdminPnl.setBackground(new Color(188, 143, 143));
		btnAdminPnl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelUsrLogin.setVisible(false);
				panelAdminLogin.setVisible(true);
				panelReg.setVisible(false);			///////////////////// Switching Between Jpanels ////////////////////////
			}
		});
		btnAdminPnl.setBounds(159, 364, 77, 25);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(47, 92, 73, 15);
		lblUserName.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblUsrPassword = new JLabel("Password:");
		lblUsrPassword.setBounds(47, 155, 64, 15);
		lblUsrPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelUsrLogin.setLayout(null);
		panelUsrLogin.add(btnAdminPnl);
		panelUsrLogin.add(lblLogIn);
		panelUsrLogin.add(UsrPassword);
		panelUsrLogin.add(textUserName);
		panelUsrLogin.add(btnUsrLogin);
		panelUsrLogin.add(lblUsrPassword);
		panelUsrLogin.add(lblUserName);
		panelUsrLogin.add(btnRstrPnl);
		panelAdminLogin.setLayout(null);
		panelAdminLogin.setBackground(Color.WHITE);
		panelAdminLogin.setBounds(298, -11, 248, 389);
		contentPane.add(panelAdminLogin);
		
		JButton btnUsrPnl = new JButton("User");
		btnUsrPnl.setBackground(new Color(188, 143, 143));
		btnUsrPnl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelAdminLogin.setVisible(false);
				panelUsrLogin.setVisible(true);
				panelReg.setVisible(false);	///////////////////// Switching Between Jpanels ////////////////////////
			}
		});
		btnUsrPnl.setBounds(159, 364, 77, 25);
		panelAdminLogin.add(btnUsrPnl);
		
		JLabel lblLogIn_1 = new JLabel("Admin log in");
		lblLogIn_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLogIn_1.setBounds(47, 35, 170, 30);
		panelAdminLogin.add(lblLogIn_1);
		
		AdminPassword = new JPasswordField();
		AdminPassword.setBounds(47, 173, 170, 30);
		panelAdminLogin.add(AdminPassword);
		
		textAdminUsrName = new JTextField();
		textAdminUsrName.setColumns(10);
		textAdminUsrName.setBounds(47, 113, 170, 30);
		panelAdminLogin.add(textAdminUsrName);
		
		JButton btnAdminLogin = new JButton("Log in");
		btnAdminLogin.setBackground(new Color(245, 222, 179));
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminloginActionPerformed(e);         //////////////////////////// Admin login  ///////////////////////////////////
			
			}
		});
		btnAdminLogin.setBounds(81, 221, 136, 25);
		panelAdminLogin.add(btnAdminLogin);
		
		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPassword_1.setBounds(47, 155, 64, 15);
		panelAdminLogin.add(lblPassword_1);
		
		JLabel lblAdminUsrName = new JLabel("User Name:");
		lblAdminUsrName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAdminUsrName.setBounds(47, 92, 73, 15);
		panelAdminLogin.add(lblAdminUsrName);
		
		panelReg = new JPanel();
		panelReg.setVisible(false);
		panelReg.setBackground(Color.WHITE);
		panelReg.setBounds(298, -11, 248, 389);
		contentPane.add(panelReg);
		
		JLabel lblCreatNewAccount = new JLabel("Creat new account");
		lblCreatNewAccount.setBounds(20, 12, 216, 30);
		lblCreatNewAccount.setFont(new Font("SansSerif", Font.BOLD, 19));
		
		JButton btnReg = new JButton("Create");
		btnReg.setBackground(new Color(176, 224, 230));
		btnReg.setBorder(null);
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rgstrAtionPerformed(e);        //////////////////////////// create button //////////////////////
			}

		});
		btnReg.setBounds(81, 306, 136, 25);
		
		JButton btnBackToLogin = new JButton("Back to log in ");
		btnBackToLogin.setBorder(null);
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelReg.setVisible(false);
				panelUsrLogin.setVisible(true);
				panelAdminLogin.setVisible(false); ///////////////////// Switching Between Jpanels ////////////////////////
			}
		});
		btnBackToLogin.setBounds(36, 343, 200, 25);
		btnBackToLogin.setForeground(Color.BLUE);
		btnBackToLogin.setFont(new Font("Dialog", Font.BOLD, 11));
		btnBackToLogin.setBackground(Color.WHITE);
		panelReg.setLayout(null);
		panelReg.add(lblCreatNewAccount);
		panelReg.add(btnBackToLogin);
		panelReg.add(btnReg);
		
		textRegMail = new JTextField();
		textRegMail.setColumns(10);
		textRegMail.setBounds(47, 138, 170, 30);
		panelReg.add(textRegMail);
		
		JLabel lblRegUserMail = new JLabel("Email:");
		lblRegUserMail.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRegUserMail.setBounds(47, 117, 73, 15);
		panelReg.add(lblRegUserMail);
		
		textRegName = new JTextField();
		textRegName.setColumns(10);
		textRegName.setBounds(47, 78, 170, 30);
		panelReg.add(textRegName);
		
		JLabel lblRegUserName = new JLabel("User Name:");
		lblRegUserName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRegUserName.setBounds(47, 57, 73, 15);
		panelReg.add(lblRegUserName);
		
		RegPass1 = new JPasswordField();
		RegPass1.setBounds(47, 198, 170, 30);
		panelReg.add(RegPass1);
		
		JLabel lblRegUserPass1 = new JLabel("Password:");
		lblRegUserPass1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRegUserPass1.setBounds(47, 180, 64, 15);
		panelReg.add(lblRegUserPass1);
		
		RegPass2 = new JPasswordField();
		RegPass2.setBounds(47, 258, 170, 30);
		panelReg.add(RegPass2);
		
		JLabel lblRegUserPass2 = new JLabel("Re-type password:");
		lblRegUserPass2.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRegUserPass2.setBounds(47, 240, 136, 15);
		panelReg.add(lblRegUserPass2);
	}
	
	///////////////////////////// Login /////////////////////////////////////////////////////////////////////////////////////////////
	
	private void loginActionPerformed(ActionEvent evt) {
		String userName = this.textUserName.getText();
		String password = new String(this.UsrPassword.getPassword());
		String privilege = "coustomer";
			
		
		
		//////////////////// warning for Empty field ////////////////////////
		if(StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "User name can not be empty.");
			return;
		}
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "Password can not be empty.");
			return;
		}
		///////////////////////////////// login method by using bd and user dao/////////////// 
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setPrivilege(privilege);
		
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			User currentUser = userDao.login(con, user);  	// save all fields of currentUser object
			if(currentUser != null) {
				dispose();  								// kill the current window(LogOnFrm)
				new UserMainFrm().setVisible(true);  			// open MainFrm
				JOptionPane.showMessageDialog(null, "Login sucsses");
			}else {
				JOptionPane.showMessageDialog(null, "username and password are incorrect");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/////////////////////////////////////////////// admin login /////////////////////////////////////////////////////////////////	
	
	private void adminloginActionPerformed(ActionEvent evt) {
		
		String userName = this.textAdminUsrName.getText();
		String password = new String(this.AdminPassword.getPassword());
		String privilege = "admin";
			
		
		
		//////////////////// warning for Empty field ////////////////////////
		if(StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "User name can not be empty.");
			return;
		}
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "Password can not be empty.");
			return;
		}
		///////////////////////////////// login method by using bd and user dao/////////////// 
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setPrivilege(privilege);
		
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			User currentUser = userDao.login(con, user);  ///// save all fields of currentUser object
			if(currentUser != null) {
				dispose();                        ///////////// kill the current window(Login page
				new AdminMainFrm().setVisible(true);  /////////// open MainFrm
				JOptionPane.showMessageDialog(null, "Login sucsses");
			}else {
				JOptionPane.showMessageDialog(null, "username and password are incorrect");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	///////////////////////////////////////// Create new Account //////////////////////////////////////////
	
	
	private void rgstrAtionPerformed(ActionEvent evt) {
		
		String userName = this.textRegName.getText();
		String mail=this.textRegMail.getText();
		String privilege = "coustomer";
		String password1 = new String(this.RegPass1.getPassword());
		String password2 = new String(this.RegPass2.getPassword());
		
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(null, "User name can not be empty.");
			return;
		}
		if(StringUtil.isEmpty(password1)){
			JOptionPane.showMessageDialog(null, "Please enter a password");
			return;
		}	
		if(StringUtil.isEmpty(password2)){
			JOptionPane.showMessageDialog(null, "Please re-enter the password");
			return;
		}	
		
//		if(password1 != password2){					//////////////////////////// Cross checking passwords ///////////////////
		if(!password1.equals(password2)){
			JOptionPane.showMessageDialog(null, "The password didn't match");
			return;
		}
		
		
		
		User user = new User();
		user.setUserName(userName);
		user.setMail(mail);
		user.setPassword(password2);
		user.setPrivilege(privilege);
		
		
		Connection con = null;
		
		try{
			con=dbUtil.getCon();
			int n=userDao.add(con, user);
			if(n==1){
				JOptionPane.showMessageDialog(null, "Your Account is ready, Go back to Log in");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "Failed");
			
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed");
			
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}

	private void resetValue() {
		this.textRegName.setText("");
		this.textRegMail.setText("");
		this.RegPass1.setText("");
		this.RegPass2.setText("");
	}
}
