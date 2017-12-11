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
			cb_sex = new JComboBox<String>(new String[] {"��","Ů"});
			tf_phone = new JTextField(11);
			
			lb_name = new JLabel("����:");
			lb_name.setFont(new Font("����",Font.BOLD,20));//��������
			lb_id = new JLabel("���֤��:");
			lb_id.setFont(new Font("����",Font.BOLD,20));
			lb_age = new JLabel("����:");
			lb_age.setFont(new Font("����",Font.BOLD,20));
			lb_sex = new JLabel("�Ա�:");
			lb_sex.setFont(new Font("����",Font.BOLD,20));
			lb_phone = new JLabel("�ֻ�����:");
			lb_phone.setFont(new Font("����",Font.BOLD,20));
			btn_confirm = new JButton("ȷ���ύ");
			btn_confirm.setFont(new Font("����",Font.BOLD,20));
			btn_back = new JButton("�˳�");
			btn_back.setFont(new Font("����",Font.BOLD,20));
			
			panel_main = new JPanel(new GridLayout(7,1,0,10));//�����
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
			this.setTitle("����ע�ᴰ��");
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
					String sex_arr[] = {"��","Ů"};
					String name = tf_name.getText().trim();
					String id = tf_id.getText().trim();
					String age = tf_age.getText().trim();
					int sex = cb_sex.getSelectedIndex();//0:��  |  1:Ů
					String phone = tf_phone.getText().trim();
					
					
					if(name.equals("")) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "��������Ϊ��!");
	 					return;
	 				}
	 				if(id.equals("")) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "���֤�Ų���Ϊ��!");
	 					return;
	 				}
	 				if(!HospitalUtil.isNumber(id)) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "���֤��ֻ��������!");
	 					return;
	 				}
	 				if(!HospitalUtil.isNumber(age)) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "����ֻ��������!");
	 					return;
	 				}
	 				if(!(new Integer(age) > 0 && new Integer(age) <= 120)) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "��������ȷ������!");
	 					return;
	 				}
	 				if(phone.length()!=11) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "��������ȷ���ֻ�����!");
	 					return;
	 				}
	 				if(!HospitalUtil.isNumber(phone)) {
	 					JOptionPane.showMessageDialog(PatientRegisterView.this, "�ֻ�����ֻ��������!");
	 					return;
	 				}
	 				int flag = JOptionPane.showConfirmDialog(
	 						PatientRegisterView.this, "�Ƿ�ȷ��ע���˺ţ�","ȷ����Ϣ",
	 						JOptionPane.YES_NO_OPTION);

	 				Patient patient = new Patient(name,id,new Integer(age),sex_arr[sex],phone);
	 				
	 				if(flag == JOptionPane.YES_OPTION) {
	 					
	 					int flag0 = patientBiz.registerUser(patient);
	 					if(flag0 == 0) {
	 						JOptionPane.showMessageDialog(PatientRegisterView.this, "���û��Ѵ���!");
	 					}
	 					if(flag0 == 1) {
	 						JOptionPane.showMessageDialog(PatientRegisterView.this, "ע��ɹ�!");
	 						new PatientLoginView();
	 					}
	 					if(flag0 == 2) {
	 						JOptionPane.showMessageDialog(PatientRegisterView.this, "ע��ʧ��!");
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
