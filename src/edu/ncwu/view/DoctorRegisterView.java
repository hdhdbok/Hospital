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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.ncwu.biz.DoctorBiz;
import edu.ncwu.biz.impl.DoctorBizImpl;
import edu.ncwu.data.Doctor;
import edu.ncwu.util.HospitalUtil;

public class DoctorRegisterView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel_main = null;
	private JPanel name_panel = null;
	private JPanel id_panel = null;
	private JPanel age_sex_depart_panel = null;
	private JPanel phone_panel = null;
	private JPanel pass_panel = null;
	private JPanel repass_panel = null;
	private JPanel confirm_back_panel = null;
	
	private JLabel lb_name = null;
	private JLabel lb_id = null;
	private JLabel lb_age= null;
	private JLabel lb_phone = null;
	private JLabel lb_init_pass = null;
	private JLabel lb_confirm_pass = null;
	
	private JTextField tf_name = null;
	private JTextField tf_id = null;
	private JTextField tf_age = null;
	private JTextField tf_phone = null;
	private JPasswordField pf_init_pass = null;
	private JPasswordField pf_confirm_pass = null;
	private JButton btn_confirm = null;
	private JButton btn_back = null;
	private JComboBox<String> cb_sex= null;
	private JComboBox<String> cb_depart= null;
	
	DoctorBiz doctorBiz = null;
	
	public DoctorRegisterView() {
		doctorBiz = new DoctorBizImpl();
		init();
		registerListener();
	}
	
	public void init() {
		tf_name = new JTextField(10);
		tf_id = new JTextField(18);
		tf_age = new JTextField(4);
		tf_phone = new JTextField(11);
		pf_init_pass = new JPasswordField(15);
		pf_confirm_pass = new JPasswordField(15);
		cb_sex = new JComboBox<String>(new String[] {"男","女"});
		cb_depart = new JComboBox<String>(new String[] {"外科","内科","皮肤科","儿科","骨科","耳鼻喉科","心脑科","神经科","药品部"});
		btn_confirm = new JButton("确定提交");
		btn_back = new JButton("退出");
		
		lb_name = new JLabel("姓名:");
		lb_name.setFont(new Font("宋体",Font.BOLD,15));//设置字体
		lb_id = new JLabel("身份证号:");
		lb_id.setFont(new Font("宋体",Font.BOLD,15));
		lb_age = new JLabel("年龄:");
		lb_age.setFont(new Font("宋体",Font.BOLD,15));
		lb_phone = new JLabel("手机号码:");
		lb_phone.setFont(new Font("宋体",Font.BOLD,15));
		lb_init_pass = new JLabel("初始化密码");
		lb_init_pass.setFont(new Font("宋体",Font.BOLD,15));
		lb_confirm_pass = new JLabel("确认密码");
		lb_confirm_pass.setFont(new Font("宋体",Font.BOLD,15));
		
		panel_main = new JPanel(new GridLayout(7,1));//主面板
		name_panel = new JPanel();
		id_panel = new JPanel();
		age_sex_depart_panel = new JPanel();
		phone_panel = new JPanel();
		pass_panel = new JPanel();
		repass_panel = new JPanel();
		confirm_back_panel = new JPanel();
		
		
		name_panel.add(lb_name);
		name_panel.add(tf_name);
		id_panel.add(lb_id);
		id_panel.add(tf_id);
		
		age_sex_depart_panel.add(lb_age);
		age_sex_depart_panel.add(tf_age);
		age_sex_depart_panel.add(cb_sex);
		age_sex_depart_panel.add(cb_depart);
		
		phone_panel.add(lb_phone);
		phone_panel.add(tf_phone);
		
		pass_panel.add(lb_init_pass);
		pass_panel.add(pf_init_pass);
		repass_panel.add(lb_confirm_pass);
		repass_panel.add(pf_confirm_pass);
		
		confirm_back_panel.add(btn_confirm);
		confirm_back_panel.add(btn_back);
		
		panel_main.add(name_panel);
		panel_main.add(id_panel);
		panel_main.add(age_sex_depart_panel);
		panel_main.add(phone_panel);
		panel_main.add(pass_panel);
		panel_main.add(repass_panel);
		panel_main.add(confirm_back_panel);
		
		this.getContentPane().add(panel_main);
		this.setTitle("医生注册窗口");
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
				int gener = 0;
				String depart_arr[] = {"外科","内科","皮肤科","儿科","骨科","耳鼻喉科","心脑科","神经科","药品部"};
				String sex_arr[] = {"男","女"};
				String name = tf_name.getText().trim();
				String id = tf_id.getText().trim();
				String age = tf_age.getText().trim();
				int sex = cb_sex.getSelectedIndex();//0:男  |  1:女
				int depart = cb_depart.getSelectedIndex();
				//0:外科,1:内科,2:皮肤科,3:儿科,4:骨科,5:耳鼻喉科,6:心脑科,7:神经科,8:药品部
				String phone = tf_phone.getText().trim();
				String pass = new String(pf_init_pass.getPassword());
				String repass = new String(pf_confirm_pass.getPassword());
				if(depart == 8) {
					gener = 1;
				}
				
				if(name.equals("")) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "姓名不能为空!");
 					return;
 				}
 				if(id.equals("")) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "身份证号不能为空!");
 					return;
 				}
 				if(!HospitalUtil.isNumber(id)) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "身份证号只能是数字!");
 					return;
 				}
 				if(pass.equals("")) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "密码不能为空!");
 					return;
 				}
 				if(!pass.equals(repass)) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "两次密码输入不同，请重新输入!");
 					return;
 				}
 				if(!HospitalUtil.isNumber(age)) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "年龄只能是数字!");
 					return;
 				}
 				if(!(new Integer(age) > 0 && new Integer(age) <= 120)) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "请输入正确的年龄!");
 					return;
 				}
 				if(phone.length()!=11) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "请输入正确的手机号码!");
 					return;
 				}
 				if(!HospitalUtil.isNumber(phone)) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "手机号码只能是数字!");
 					return;
 				}
 				int flag = JOptionPane.showConfirmDialog(
 						DoctorRegisterView.this, "是否确定注册账号？","确认信息",
 						JOptionPane.YES_NO_OPTION);
 				
 				Doctor doctor = new Doctor(name,id,new Integer(age),sex_arr[sex],phone,pass,gener,depart_arr[depart]);
 				
 				if(flag == JOptionPane.YES_OPTION) {
 					
 					int flag0 = doctorBiz.registerUser(doctor);
 					if(flag0 == 0) {
 						JOptionPane.showMessageDialog(DoctorRegisterView.this, "注册成功!");
 						new DoctorLoginView();
 						DoctorRegisterView.this.dispose();
 						
 					}
 					if(flag0 == 1) {
 						JOptionPane.showMessageDialog(DoctorRegisterView.this, "注册失败!");
 					}
 				}
			}
		});
		
		btn_back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				DoctorRegisterView.this.dispose();
			}
		});
		
		
	}
	
}
