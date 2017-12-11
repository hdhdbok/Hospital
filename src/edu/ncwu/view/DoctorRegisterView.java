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
		cb_sex = new JComboBox<String>(new String[] {"��","Ů"});
		cb_depart = new JComboBox<String>(new String[] {"���","�ڿ�","Ƥ����","����","�ǿ�","���Ǻ��","���Կ�","�񾭿�","ҩƷ��"});
		btn_confirm = new JButton("ȷ���ύ");
		btn_back = new JButton("�˳�");
		
		lb_name = new JLabel("����:");
		lb_name.setFont(new Font("����",Font.BOLD,15));//��������
		lb_id = new JLabel("���֤��:");
		lb_id.setFont(new Font("����",Font.BOLD,15));
		lb_age = new JLabel("����:");
		lb_age.setFont(new Font("����",Font.BOLD,15));
		lb_phone = new JLabel("�ֻ�����:");
		lb_phone.setFont(new Font("����",Font.BOLD,15));
		lb_init_pass = new JLabel("��ʼ������");
		lb_init_pass.setFont(new Font("����",Font.BOLD,15));
		lb_confirm_pass = new JLabel("ȷ������");
		lb_confirm_pass.setFont(new Font("����",Font.BOLD,15));
		
		panel_main = new JPanel(new GridLayout(7,1));//�����
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
		this.setTitle("ҽ��ע�ᴰ��");
		this.setSize(420,360);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getRootPane().setDefaultButton(btn_confirm);//Ĭ�ϻ�ý���İ�ť
		this.setVisible(true);
	}
	
	private void registerListener() {
		btn_confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int gener = 0;
				String depart_arr[] = {"���","�ڿ�","Ƥ����","����","�ǿ�","���Ǻ��","���Կ�","�񾭿�","ҩƷ��"};
				String sex_arr[] = {"��","Ů"};
				String name = tf_name.getText().trim();
				String id = tf_id.getText().trim();
				String age = tf_age.getText().trim();
				int sex = cb_sex.getSelectedIndex();//0:��  |  1:Ů
				int depart = cb_depart.getSelectedIndex();
				//0:���,1:�ڿ�,2:Ƥ����,3:����,4:�ǿ�,5:���Ǻ��,6:���Կ�,7:�񾭿�,8:ҩƷ��
				String phone = tf_phone.getText().trim();
				String pass = new String(pf_init_pass.getPassword());
				String repass = new String(pf_confirm_pass.getPassword());
				if(depart == 8) {
					gener = 1;
				}
				
				if(name.equals("")) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "��������Ϊ��!");
 					return;
 				}
 				if(id.equals("")) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "���֤�Ų���Ϊ��!");
 					return;
 				}
 				if(!HospitalUtil.isNumber(id)) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "���֤��ֻ��������!");
 					return;
 				}
 				if(pass.equals("")) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "���벻��Ϊ��!");
 					return;
 				}
 				if(!pass.equals(repass)) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "�����������벻ͬ������������!");
 					return;
 				}
 				if(!HospitalUtil.isNumber(age)) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "����ֻ��������!");
 					return;
 				}
 				if(!(new Integer(age) > 0 && new Integer(age) <= 120)) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "��������ȷ������!");
 					return;
 				}
 				if(phone.length()!=11) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "��������ȷ���ֻ�����!");
 					return;
 				}
 				if(!HospitalUtil.isNumber(phone)) {
 					JOptionPane.showMessageDialog(DoctorRegisterView.this, "�ֻ�����ֻ��������!");
 					return;
 				}
 				int flag = JOptionPane.showConfirmDialog(
 						DoctorRegisterView.this, "�Ƿ�ȷ��ע���˺ţ�","ȷ����Ϣ",
 						JOptionPane.YES_NO_OPTION);
 				
 				Doctor doctor = new Doctor(name,id,new Integer(age),sex_arr[sex],phone,pass,gener,depart_arr[depart]);
 				
 				if(flag == JOptionPane.YES_OPTION) {
 					
 					int flag0 = doctorBiz.registerUser(doctor);
 					if(flag0 == 0) {
 						JOptionPane.showMessageDialog(DoctorRegisterView.this, "ע��ɹ�!");
 						new DoctorLoginView();
 						DoctorRegisterView.this.dispose();
 						
 					}
 					if(flag0 == 1) {
 						JOptionPane.showMessageDialog(DoctorRegisterView.this, "ע��ʧ��!");
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
