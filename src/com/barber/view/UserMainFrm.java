package com.barber.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.barber.dao.AppointDao;
import com.barber.dao.EmployeeDao;
import com.barber.model.Appoint;
import com.barber.model.Employee;
import com.barber.model.User;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.barber.util.DbUtil;
import com.barber.util.StringUtil;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;

public class UserMainFrm extends JFrame {

	private JPanel contentPane;
	private JTextField textCusName;
	private JTextField textCusTelNum;
	private JComboBox comboBox;
	private JDateChooser dateChooser;
	private TimePicker timePicker;
	private Date date;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdfd = new SimpleDateFormat("EEE");
	private String userDay;
	private String userDate;
	private EmployeeDao employeeDao = new EmployeeDao();

	private DbUtil dbUtil = new DbUtil(); // <--------------
	private AppointDao appointDao = new AppointDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMainFrm frame = new UserMainFrm();
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
	public UserMainFrm() {
		setTitle("Barber management system");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(0, 0, 720, 480);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mnMenu.add(mntmSettings);
		
		JMenuItem mntmAboutUs = new JMenuItem("About Us");
		mnMenu.add(mntmAboutUs);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnMenu.add(mntmExit);
		
		JMenuItem mntmGallary = new JMenuItem("Gallary");
		menuBar.add(mntmGallary);
		
		JMenuItem mntmMyAppointment = new JMenuItem("My appointments");
		menuBar.add(mntmMyAppointment);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 240, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setBounds(412, 0, 304, 448);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Make appointment");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNewLabel.setForeground(new Color(105, 105, 105));
		lblNewLabel.setBounds(49, 12, 206, 29);
		panel.add(lblNewLabel);

		textCusName = new JTextField();
		textCusName.setBackground(new Color(250, 240, 230));
		textCusName.setBounds(138, 90, 154, 24);
		panel.add(textCusName);
		textCusName.setColumns(10);

		JLabel lblYourName = new JLabel("Your Name:");
		lblYourName.setForeground(new Color(0, 0, 0));
		lblYourName.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblYourName.setBounds(44, 96, 81, 15);
		panel.add(lblYourName);

		textCusTelNum = new JTextField();
		textCusTelNum.setBackground(new Color(250, 240, 230));
		textCusTelNum.setBounds(138, 141, 154, 24);
		panel.add(textCusTelNum);
		textCusTelNum.setColumns(10);

		JLabel lblMobileNumber = new JLabel("Mobile Number:");
		lblMobileNumber.setForeground(new Color(0, 0, 0));
		lblMobileNumber.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblMobileNumber.setBounds(12, 147, 113, 15);
		panel.add(lblMobileNumber);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setForeground(new Color(0, 0, 0));
		lblDate.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblDate.setBounds(86, 198, 39, 15);
		panel.add(lblDate);

		JLabel lblTime = new JLabel("Time:");
		lblTime.setForeground(new Color(0, 0, 0));
		lblTime.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblTime.setBounds(86, 249, 39, 15);
		panel.add(lblTime);

		JLabel lblChoseBarber = new JLabel("Chose barber:");
		lblChoseBarber.setForeground(new Color(0, 0, 0));
		lblChoseBarber.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblChoseBarber.setBounds(24, 300, 101, 15);
		panel.add(lblChoseBarber);

		comboBox = new JComboBox();
		comboBox.setBackground(new Color(250, 240, 230));
		comboBox.setBounds(138, 294, 154, 24);
		panel.add(comboBox);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAppointActionPerformed(e);

			}

		});
		btnSubmit.setBackground(new Color(255, 255, 255));
		btnSubmit.setBounds(175, 357, 117, 25);
		panel.add(btnSubmit);

		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setDoubleBuffered(true);
		dateChooser.setBackground(new Color(250, 240, 230));
		dateChooser.setBounds(138, 192, 154, 24);
		panel.add(dateChooser);
		dateChooser.setDate(new Date());
		date = dateChooser.getDate();
		

		timePicker = new TimePicker(); ///////////////////////////////// time picker //////////////////////
		timePicker.getComponentTimeTextField().setBackground(new Color(250, 240, 230));
		timePicker.getComponentTimeTextField().setLocation(138, 0);
		timePicker.setBounds(138, 245, 96, 24);
		panel.add(timePicker);

		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(255, 255, 255));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //////////////////////////////// clear button
															//////////////////////////////// /////////////////////////
				clearactionPerformed(e);

			}

		});
		btnClear.setBounds(46, 357, 117, 25);
		panel.add(btnClear);

		JPanel jpRoss = new JPanel();
		jpRoss.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			} ///////////////////////////// jp click ////////////////
		});
		jpRoss.setBackground(Color.LIGHT_GRAY);
		jpRoss.setBounds(12, 29, 100, 122);
		contentPane.add(jpRoss);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(-69, -18, 169, 140);
		lblNewLabel_1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Ross.png"))
				.getImage().getScaledInstance(200, 106, Image.SCALE_SMOOTH)));
		jpRoss.setLayout(null);
		jpRoss.add(lblNewLabel_1);

		JLabel lblRoss = new JLabel("Ross");
		lblRoss.setBounds(0, 107, 34, 15);
		jpRoss.add(lblRoss);

		JPanel jpEmmy = new JPanel();
		jpEmmy.setBackground(Color.LIGHT_GRAY);
		jpEmmy.setBounds(124, 29, 100, 122);
		contentPane.add(jpEmmy);
		jpEmmy.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Emmy.jpg"))
				.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH)));
		lblNewLabel_2.setBounds(-50, 0, 150, 106);
		jpEmmy.add(lblNewLabel_2);

		JLabel lblEmmy = new JLabel("Emmy");
		lblEmmy.setBounds(0, 107, 40, 15);
		jpEmmy.add(lblEmmy);

		JPanel jpSelmon = new JPanel();
		jpSelmon.setBackground(Color.LIGHT_GRAY);
		jpSelmon.setBounds(238, 29, 100, 122);
		contentPane.add(jpSelmon);
		jpSelmon.setLayout(null);

		JLabel lblSelmon = new JLabel("Selmon");
		lblSelmon.setBounds(0, 107, 52, 15);
		jpSelmon.add(lblSelmon);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Selmon.jpg")).getImage()
				.getScaledInstance(200, 105, Image.SCALE_SMOOTH)));
		label.setBounds(-30, -41, 150, 189);
		jpSelmon.add(label);

		JPanel jpGalRoss = new JPanel();
		jpGalRoss.setBackground(new Color(250, 240, 230));
		jpGalRoss.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jpGalRoss.setBounds(12, 163, 388, 222);
		contentPane.add(jpGalRoss);
		jpGalRoss.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblName.setBounds(37, 22, 45, 15);
		jpGalRoss.add(lblName);
		
		JLabel lblNewLabel_3 = new JLabel("Age:");
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(49, 62, 33, 15);
		jpGalRoss.add(lblNewLabel_3);
		
		JLabel lblSex = new JLabel("Sex:");
		lblSex.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSex.setBounds(52, 102, 30, 15);
		jpGalRoss.add(lblSex);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPrice.setBounds(43, 142, 39, 15);
		jpGalRoss.add(lblPrice);
		
		JLabel lblAvailable = new JLabel("Available:");
		lblAvailable.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblAvailable.setBounds(18, 182, 64, 15);
		jpGalRoss.add(lblAvailable);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(102, 19, 149, 21);
		jpGalRoss.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(102, 59, 149, 21);
		jpGalRoss.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setBounds(102, 99, 149, 21);
		jpGalRoss.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setBounds(102, 139, 149, 21);
		jpGalRoss.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setBounds(102, 179, 149, 21);
		jpGalRoss.add(textPane_4);

		fillEmployee(new Employee()); ////////////////////// Filling C hose barber combo box /////
	}

	//////////////////////////////////////// My code placed here //////////////////////////////////////// //////////////////////////////////////

	private void clearactionPerformed(ActionEvent e) {
		this.textCusName.setText("");
		this.textCusTelNum.setText("");
		this.timePicker.setText("");
	}

	/////////////////////////////////////////// make appointment
	/////////////////////////////////////////// //////////////////////////////////

	private void fillEmployee(Employee employee) {

		Connection con = null;

		try {
			con = dbUtil.getCon();
			ResultSet rs = employeeDao.list(con, employee);

			while (rs.next()) {
				Employee t = new Employee();
				t.setAge(rs.getInt("age"));
				t.setEmpName(rs.getString("empName"));
				t.setEmpSex(rs.getString("empSex"));
				t.setId(rs.getInt("id"));
				t.setPrice(rs.getFloat("price"));
				t.setWorkDay(rs.getString("workDay"));
				comboBox.addItem(t);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addAppointActionPerformed(ActionEvent e) {

		userDate = sdf.format(date); //////////////////// get date 2022-06-18 ////////////////////
//		System.out.println(userDate);

		userDay = sdfd.format(date);
//		System.out.println(userDay);

		String time = timePicker.getText(); /////////////////// 12:00am Time picker ///////////////

		String cusName = this.textCusName.getText();
		String cusTel = this.textCusTelNum.getText();

		

		Employee employee = (Employee) comboBox.getSelectedItem();
		int employeeId = employee.getId();
		System.out.println(employee);

		if (StringUtil.isEmpty(cusName)) {
			JOptionPane.showMessageDialog(null, "Name can not be empty.");
			return;
		}
		if (StringUtil.isEmpty(cusTel)) {
			JOptionPane.showMessageDialog(null, "Please enter your mobile number");
			return;
		}
		if (StringUtil.isEmpty(time)) {
			JOptionPane.showMessageDialog(null, "Please pick a time");
			return;
		}
		Employee empl=(Employee) comboBox.getSelectedItem();  
		int emplId=empl.getId();
		System.out.println(emplId);

		Appoint appoint = new Appoint();
		appoint.setCustName(cusName);
		appoint.setTelNum(cusTel);
		appoint.setDate(userDate);
		appoint.setDay(userDay);
		appoint.setTime(time);
		appoint.setEmpId(emplId);
		
		Connection con = null;
		
		try{
			con=dbUtil.getCon();
			
			int n=appointDao.addApnt(con, appoint);
			if(n==1){
				JOptionPane.showMessageDialog(null, "Appointment has submited");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "Failed");
			
			}
		}catch(Exception e1){
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed");
			
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

//		System.out.println(date.getDay());   // 6  ---- Sat
//		System.out.println(date.getDate());  // 18
//		System.out.println(date.getMonth()); // 5 ----- June
//		System.out.println(date.getYear());  // 122
//		System.out.println(date.toString());  // Sat Jun 18 10:38:21 HKT 2022

	}

	private void resetValue() {
		this.textCusName.setText("");
		this.textCusTelNum.setText("");
		this.timePicker.setText("");
	}
}
