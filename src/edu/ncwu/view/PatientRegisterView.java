package edu.ncwu.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ncwu.biz.PatientBiz;
import edu.ncwu.biz.impl.PatientBizImpl;
import edu.ncwu.data.Patient;
import edu.ncwu.util.HospitalUtil;

public class PatientRegisterView extends JFrame {
	private static final long serialVersionUID = 1L;
	
		private JPanel panel_main = null;
		private JPanel name_panel = null;
		private JPanel id_panel = null;
		private JPanel age_sex_panel = null;
		private JPanel phone_panel = null;
		private JPanel confirm_back_panel = null;
		
		private JLabel lb_name = null;
		private JLabel lb_id = null;
		private JLabel lb_age= null;
		private JLabel lb_phone = null;
		private JLabel lb_sex = null;
		
		private JTextField tf_name = null;
		private JTextField tf_id = null;
		private JTextField tf_age = null;
		private JTextField tf_phone = null;
		private JComboBox<String> cb_sex= null;
		private JButton btn_confirm = null;
		private JButton btn_back = null;
		

		
		PatientBiz patientBiz = null;
		
		public PatientRegisterView() {
			patientBiz = new PatientBizImpl();
			init();
			registerListener();
		}
		
		public void init() {
			tf_name = new JTextField(10);
			tf_id = new JTextField(18);
			tf_age = new JTextField(4);
			cb_sex = new JComboBox<String>(new String[] {"男","女"});
			tf_phone = new JTextField(11);
			
			lb_name = new JLabel("姓名:");
			lb_name.setFont(new Font("宋体",Font.BOLD,20));//设置字体
			lb_id = new JLabel("身份证号:");
			lb_id.setFont(new Font("宋体",Font.BOLD,20));
			lb_age = new JLabel("年龄:");
			lb_age.setFont(new Font("宋体",Font.BOLD,20));
			lb_sex = new JLabel("性别:");
			lb_sex.setFont(new Font("宋体",Font.BOLD,20));
			lb_phone = new JLabel("手机号码:");
			lb_phone.setFont(new Font("宋体",Font.BOLD,20));
			btn_confirm = new JButton("确定提交");
			btn_confirm.setFont(new Font("宋体",Font.BOLD,20));
			btn_back = new JButton("退出");
			btn_back.setFont(new Font("宋体",Font.BOLD,20));
			
			panel_main = new JPanel(new GridLayout(7,1,0,10));//主面板
			name_panel = new JPanel();
			id_panel = new JPanel();
			age_sex_panel = new JPanel();
			phone_panel = new JPanel();
			confirm_back_panel = new JPanel();
			
			
			name_panel.add(lb_name);
			name_panel.add(tf_name);
			id_panel.add(lb_id);
			id_panel.add(tf_id);
			age_sex_panel.add(lb_age);
			age_sex_panel.add(tf_age);
			age_sex_panel.add(lb_sex);
			age_sex_panel.add(cb_sex);
			phone_panel.add(lb_phone);
			phone_panel.add(tf_phone);
			confirm_back_panel.add(btn_confirm);
			confirm_back_panel.add(btn_back);
			
			panel_main.add(new JPanel());
			panel_main.add(name_panel);
			panel_main.add(id_panel);
			panel_main.add(age_sex_panel);
			panel_main.add(phone_panel);
			panel_main.add(confirm_back_panel);
			
			this.getContentPane().add(panel_main);
			this.setTitle("病人注册窗口");
			this.setSize(420,360);
			this.setVisible(true);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.getRootPane().setDefaultButton(btn_confirm);//默认获得焦点的按钮
			this.setVisible(true);
		}
		
		private void registerListener() {
			btn_confirm.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					String sex_arr[] = {"男","女"};
					String name = tf_name.getText().trim();
					String id = tf_id.getText().trim();
					String age = tf_age.getText().trim();
					int sex = cb_sex.getSelectedIndex();//0:男  |  1:女
					String phone = tf_phone.getText().trim();
					
					
					if(name.equals("")) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "姓名不能为空!");
	 					return;
	 				}
	 				if(id.equals("")) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "身份证号不能为空!");
	 					return;
	 				}
	 				if(!HospitalUtil.isNumber(id)) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "身份证号只能是数字!");
	 					return;
	 				}
	 				if(!HospitalUtil.isNumber(age)) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "年龄只能是数字!");
	 					return;
	 				}
	 				if(!(new Integer(age) > 0 && new Integer(age) <= 120)) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "请输入正确的年龄!");
	 					return;
	 				}
	 				if(phone.length()!=11) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "请输入正确的手机号码!");
	 					return;
	 				}
	 				if(!HospitalUtil.isNumber(phone)) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "手机号码只能是数字!");
	 					return;
	 				}
	 				int flag = JOptionPane.showConfirmDialog(
	 						PatientRegisterView.this, "是否确定注册账号？","确认信息",
	 						JOptionPane.YES_NO_OPTION);

	 				Patient patient = new Patient(name,id,new Integer(age),sex_arr[sex],phone);
	 				
	 				if(flag == JOptionPane.YES_OPTION) {
	 					
	 					int flag0 = patientBiz.registerUser(patient);
	 					if(flag0 == 0) {
	 						JOptionPane.showMessageDialog(PatientRegisterView.this, "该用户已存在!");
	 					}
	 					if(flag0 == 1) {
	 						JOptionPane.showMessageDialog(PatientRegisterView.this, "注册成功!");
	 						new PatientLoginView();
	 					}
	 					if(flag0 == 2) {
	 						JOptionPane.showMessageDialog(PatientRegisterView.this, "注册失败!");
	 					}
	 				}
				}
			});
			
			btn_back.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					PatientRegisterView.this.dispose();
				}
			});
			
			
		}
		
	

	
}
