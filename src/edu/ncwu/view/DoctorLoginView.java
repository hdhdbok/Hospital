package edu.ncwu.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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

public class DoctorLoginView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel panel_main = null;//�����
	private JPanel panel_left = null;//������
	private JPanel panel_right = null;//�Ҳ����
	
	private JLabel lb_uname = null;//�û���ǩ
	private JLabel lb_pass = null;//�û�ID
	private JLabel lb_genre = null;//�û�����
	
	private JTextField tf_uname = null;//�û��ı���
	private JPasswordField pf_pass = null;//�����ı���
	private JComboBox<String> cb_genre = null;//����ѡ��
	
	
	private JLabel lb_img = null;//��ʾͼƬ�ı�ǩ
	
	private JButton btn_login = null;//��¼��ť
	private JButton btn_register = null;//ע�ᰴť
	

	private DoctorBiz doctorBiz= null;
	
	
	public DoctorLoginView() {
		doctorBiz = new DoctorBizImpl();
		init();
		registerListener();
	}
	
	
	//��ʼ���ؼ��ķ���
	private void init() {
		this.setSize(780,360);//���ô����С
		this.setResizable(false);//�����϶������С
		this.setLocationRelativeTo(null);//���ô��������ʾ
		this.setTitle("ҽ����¼����");//���ô������
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//�رմ��幦��
		
		//��ʼ�����
		panel_main = new JPanel(new GridLayout(1,2));//GridLayout(��,��)
		panel_left = new JPanel();
		panel_right =new JPanel(new GridLayout(6,2,0,30));//GridLayout(��,��,ˮƽ��϶,��ֱ��϶)
		
		//��ʼ���ؼ�
		tf_uname = new JTextField(8);//JTextField(�ı�����ʾ�ַ���)
		tf_uname.setFont(new Font("����",Font.BOLD,20));
		pf_pass= new JPasswordField(18);//ͬ��
		pf_pass.setFont(new Font("����",Font.BOLD,20));
		cb_genre = new JComboBox<String>(new String[] {"ҽ��","ҩ������Ա","����Ա"});
		cb_genre.setFont(new Font("����",Font.BOLD,20));
		
		btn_login = new JButton("��¼");
		btn_register = new JButton("ע��");
		lb_uname = new JLabel("��    ��:",JLabel.CENTER);
		lb_pass = new JLabel("��    ��:",JLabel.CENTER);
		lb_genre = new JLabel("��    ��:",JLabel.CENTER);
		
		btn_login.setFont(new Font("����",Font.BOLD,23));
		btn_register.setFont(new Font("����",Font.BOLD,23));
		lb_uname.setFont(new Font("����",Font.BOLD,23));
		lb_pass.setFont(new Font("����",Font.BOLD,23));
		lb_genre.setFont(new Font("����",Font.BOLD,23));
		
		lb_img = new JLabel(new ImageIcon(
				ClassLoader.getSystemResource("pictures/doctor.png")));
		
		//����Ӧ�Ŀؼ��ŵ������ȥ
		panel_left.add(lb_img);
		panel_right.add(new JLabel());
		panel_right.add(new JLabel());
		panel_right.add(lb_uname);
		panel_right.add(tf_uname);
		panel_right.add(lb_pass);
		panel_right.add(pf_pass);
		panel_right.add(lb_genre);
		panel_right.add(cb_genre);

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
				new DoctorRegisterView();//����ҽ��ע����ͼ
				DoctorLoginView.this.dispose();
			}
		});
		
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf_uname.getText().trim();
				String pass = new String(pf_pass.getPassword());
				int genre = cb_genre.getSelectedIndex();
				if(name.equals("")) {
					JOptionPane.showMessageDialog(DoctorLoginView.this, "�û�������Ϊ��");
					return;
				}else if(pass.equals("")){
					JOptionPane.showMessageDialog(DoctorLoginView.this, "���벻��Ϊ��");
					return;
				}
				Doctor doctor = new Doctor(name,pass,genre);
				doctor = doctorBiz.login(doctor);
				if(doctor!=null) {
					if(doctor.getGenre() == 0) {
						new DoctorMainView(doctor);
						DoctorLoginView.this.dispose();//��½�ɹ��󣬹رյ�¼����
					}else if(doctor.getGenre() == 1){ 
						new MedicionMainView(doctor);
						DoctorLoginView.this.dispose();//��½�ɹ��󣬹رյ�¼����
					}else if(doctor.getGenre() == 2){ 
						new AdminMainView(doctor);
						DoctorLoginView.this.dispose();//��½�ɹ��󣬹رյ�¼����
					}
				}else {
					JOptionPane.showMessageDialog(DoctorLoginView.this, "�û��������������");
					return;
				}
			}
		});
	}
}
