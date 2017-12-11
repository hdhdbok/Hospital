package edu.ncwu.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.ncwu.biz.PatientBiz;
import edu.ncwu.biz.impl.PatientBizImpl;
import edu.ncwu.data.Patient;

public class PatientLoginView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel_main = null;//�����
	private JPanel panel_left = null;//������
	private JPanel panel_right = null;//�Ҳ����
	
	private JLabel lb_uname = null;//�û���ǩ
	private JLabel lb_id = null;//�û�ID
	
	private JTextField tf_uname = null;//�û��ı���
	private JTextField tf_id = null;//ID�ı���
	
	private JLabel lb_img = null;//��ʾͼƬ�ı�ǩ
	
	private JButton btn_login = null;//��¼��ť
	private JButton btn_register = null;//ע�ᰴť
	

	private PatientBiz patientBiz= null;
	
	
	public PatientLoginView() {
		patientBiz = new PatientBizImpl();
		init();
		registerListener();
	}
	
	
	//��ʼ���ؼ��ķ���
	private void init() {
		this.setSize(780,360);//���ô����С
		this.setResizable(false);//�����϶������С
		this.setLocationRelativeTo(null);//���ô��������ʾ
		this.setTitle("���˵�¼����");//���ô������
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//�رմ��幦��
		
		//��ʼ�����
		panel_main = new JPanel(new GridLayout(1,2));//GridLayout(��,��)
		panel_left = new JPanel();
		panel_right =new JPanel(new GridLayout(5,2,0,30));//GridLayout(��,��,ˮƽ��϶,��ֱ��϶)
		
		//��ʼ���ؼ�
		tf_uname = new JTextField(8);//JTextField(�ı�����ʾ�ַ���)
		tf_id = new JTextField(18);//ͬ��

		tf_uname.setFont(new Font("����",Font.BOLD,20));
		tf_id.setFont(new Font("����",Font.BOLD,20));
		
		btn_login = new JButton("��¼");
		btn_register = new JButton("ע��");
		lb_uname = new JLabel("��    ��:",JLabel.CENTER);
		lb_id = new JLabel("���֤��:",JLabel.CENTER);
		
		btn_login.setFont(new Font("����",Font.BOLD,23));
		btn_register.setFont(new Font("����",Font.BOLD,23));
		lb_uname.setFont(new Font("����",Font.BOLD,23));
		lb_id.setFont(new Font("����",Font.BOLD,23));
		
		lb_img = new JLabel(new ImageIcon(
				ClassLoader.getSystemResource("pictures/patient.png")));
		
		//����Ӧ�Ŀؼ��ŵ������ȥ
		panel_left.add(lb_img);
		panel_right.add(new JLabel());
		panel_right.add(new JLabel());
		panel_right.add(lb_uname);
		panel_right.add(tf_uname);
		panel_right.add(lb_id);
		panel_right.add(tf_id);

		panel_right.add(btn_login);
		panel_right.add(btn_register);
		panel_right.add(new JLabel());
		panel_right.add(new JLabel());
		//������з������������
		panel_main.add(panel_left);
		panel_main.add(panel_right);
		
		//�ٰ������ŵ�������
		this.getContentPane().add(panel_main);//��õ�ǰ������������
		//this.pack();//ʹ������Ӧ����
		
		this.setVisible(true);//���ô���ɼ�
	}
	
	
	private void registerListener() {
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PatientRegisterView();//��������ע����ͼ
			}
		});
		
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String uname = tf_uname.getText().trim();
				String uid = tf_id.getText().trim();
				if(uname.equals("")) {
					JOptionPane.showMessageDialog(PatientLoginView.this, "�û�������Ϊ��");
					return;
				}else if(uid.equals("")){
					JOptionPane.showMessageDialog(PatientLoginView.this, "���֤�Ų���Ϊ��");
					return;
				}
				Patient patient = new Patient(uname,uid);
				patient = patientBiz.login(patient);
				if(patient!=null) {
					new PatientMainView(patient);
					PatientLoginView.this.dispose();
				}else {
					JOptionPane.showMessageDialog(PatientLoginView.this, "�û����������֤�Ŵ���");
				}
			}
		});
	}
	
}
