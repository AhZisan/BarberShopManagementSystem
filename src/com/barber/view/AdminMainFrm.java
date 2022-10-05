package com.barber.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.barber.dao.AppointDao;
import com.barber.model.Appoint;
import com.barber.util.DbUtil;
import com.github.lgooddatepicker.components.CalendarPanel;
import javax.swing.ImageIcon;

public class AdminMainFrm extends JFrame {

	private JPanel contentPane;
	private JTable AppointTable;
	private DbUtil dbUtil = new DbUtil();
	private AppointDao appointDao = new AppointDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainFrm frame = new AdminMainFrm();
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
	public AdminMainFrm() {
		setTitle("Admin Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 621);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmApproveAppointment = new JMenuItem("Approve appointment");
		mnMenu.add(mntmApproveAppointment);

		JMenu mnEmployee = new JMenu("Employee");
		mnMenu.add(mnEmployee);

		JMenuItem mntmNewEmployee = new JMenuItem("New employee");
		mnEmployee.add(mntmNewEmployee);

		JMenuItem mntmRemoveEmployee = new JMenuItem("Manage employee");
		mnEmployee.add(mntmRemoveEmployee);

		JMenu mnGallary = new JMenu("Gallary");
		mnMenu.add(mnGallary);

		JMenuItem mntmAllPictures = new JMenuItem("All pictures");
		mnGallary.add(mntmAllPictures);

		JMenuItem mntmUploadPictures = new JMenuItem("Upload pictures");
		mnGallary.add(mntmUploadPictures);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnMenu.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		CalendarPanel calendarPanel = new CalendarPanel();
		calendarPanel.setBounds(17, 5, 287, 262);
		calendarPanel.getNextYearButton().setBackground(new Color(112, 128, 144));
		calendarPanel.getNextMonthButton().setBackground(new Color(119, 136, 153));
		calendarPanel.getNextMonthButton().setForeground(new Color(0, 0, 0));
		calendarPanel.getPreviousMonthButton().setBackground(new Color(119, 136, 153));
		calendarPanel.getPreviousYearButton().setBackground(new Color(112, 128, 144));

		JLabel lblTodaysAvailableEmployees = new JLabel("Today's available employees");
		lblTodaysAvailableEmployees.setBounds(322, 5, 234, 18);
		lblTodaysAvailableEmployees.setFont(new Font("Dialog", Font.BOLD, 15));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.setLayout(null);
		contentPane.add(calendarPanel);
		contentPane.add(lblTodaysAvailableEmployees);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 245, 238));
		panel.setBounds(0, 268, 878, 301);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblAllAppointments = new JLabel("All appointments");
		lblAllAppointments.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});
		lblAllAppointments.setBounds(370, 16, 137, 15);
		panel.add(lblAllAppointments);
		lblAllAppointments.setFont(new Font("Dialog", Font.BOLD, 15));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(9, 40, 860, 250);
		panel.add(scrollPane_1);

		AppointTable = new JTable();
		AppointTable.setRowSelectionAllowed(false);
		AppointTable.setBorder(null);
		AppointTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Customer Name", "Tel Number", "Date", "Day", "Time", "Employee ID" }));
		AppointTable.getColumnModel().getColumn(0).setPreferredWidth(129);
		AppointTable.getColumnModel().getColumn(1).setPreferredWidth(175);
		AppointTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		AppointTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		AppointTable.getColumnModel().getColumn(4).setPreferredWidth(111);
		AppointTable.getColumnModel().getColumn(5).setPreferredWidth(103);
		scrollPane_1.setViewportView(AppointTable);
		
		JPanel jpRoss = new JPanel();
		jpRoss.setLayout(null);
		jpRoss.setBackground(Color.LIGHT_GRAY);
		jpRoss.setBounds(328, 50, 100, 122);
		contentPane.add(jpRoss);
		
		JLabel lblRoss = new JLabel("Ross");
		lblRoss.setBounds(0, 107, 34, 15);
		jpRoss.add(lblRoss);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(-69, -18, 169, 140);
		lblNewLabel_1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Ross.png"))
				.getImage().getScaledInstance(200, 106, Image.SCALE_SMOOTH)));
		jpRoss.add(lblNewLabel_1);
		
		JPanel jpEmmy = new JPanel();
		jpEmmy.setLayout(null);
		jpEmmy.setBackground(Color.LIGHT_GRAY);
		jpEmmy.setBounds(440, 50, 100, 122);
		contentPane.add(jpEmmy);
		
		JLabel lblEmmy = new JLabel("Emmy");
		lblEmmy.setBounds(0, 107, 40, 15);
		jpEmmy.add(lblEmmy);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(-38, -4, 138, 108);
		label_1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Emmy.jpg"))
				.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH)));
		jpEmmy.add(label_1);
		
		JPanel jpSelmon = new JPanel();
		jpSelmon.setLayout(null);
		jpSelmon.setBackground(Color.LIGHT_GRAY);
		jpSelmon.setBounds(552, 50, 100, 122);
		contentPane.add(jpSelmon);
		
		JLabel lblSelmon = new JLabel("Selmon");
		lblSelmon.setBounds(0, 107, 52, 15);
		jpSelmon.add(lblSelmon);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(-30, 0, 150, 106);
		jpSelmon.add(lblNewLabel_2);

		lblNewLabel_2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Selmon.jpg")).getImage()
				.getScaledInstance(200, 105, Image.SCALE_SMOOTH)));

		// -------------

		fillTable(new Appoint());
	}

	private void fillTable(Appoint appoint) {
		DefaultTableModel dtm = (DefaultTableModel) AppointTable.getModel();
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			ResultSet rs = appointDao.apList(con, appoint);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getInt(7));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
