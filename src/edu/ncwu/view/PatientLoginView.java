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
	private JPanel panel_main = null;//主面板
	private JPanel panel_left = null;//左侧面板
	private JPanel panel_right = null;//右侧面板
	
	private JLabel lb_uname = null;//用户标签
	private JLabel lb_id = null;//用户ID
	
	private JTextField tf_uname = null;//用户文本框
	private JTextField tf_id = null;//ID文本框
	
	private JLabel lb_img = null;//显示图片的标签
	
	private JButton btn_login = null;//登录按钮
	private JButton btn_register = null;//注册按钮
	

	private PatientBiz patientBiz= null;
	
	
	public PatientLoginView() {
		patientBiz = new PatientBizImpl();
		init();
		registerListener();
	}
	
	
	//初始化控件的方法
	private void init() {
		this.setSize(780,360);//设置窗体大小
		this.setResizable(false);//不可拖动窗体大小
		this.setLocationRelativeTo(null);//设置窗体居中显示
		this.setTitle("病人登录窗口");//设置窗体标题
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//关闭窗体功能
		
		//初始化面板
		panel_main = new JPanel(new GridLayout(1,2));//GridLayout(行,列)
		panel_left = new JPanel();
		panel_right =new JPanel(new GridLayout(5,2,0,30));//GridLayout(行,列,水平间隙,垂直间隙)
		
		//初始化控件
		tf_uname = new JTextField(8);//JTextField(文本框显示字符数)
		tf_id = new JTextField(18);//同上

		tf_uname.setFont(new Font("宋体",Font.BOLD,20));
		tf_id.setFont(new Font("宋体",Font.BOLD,20));
		
		btn_login = new JButton("登录");
		btn_register = new JButton("注册");
		lb_uname = new JLabel("姓    名:",JLabel.CENTER);
		lb_id = new JLabel("身份证号:",JLabel.CENTER);
		
		btn_login.setFont(new Font("宋体",Font.BOLD,23));
		btn_register.setFont(new Font("宋体",Font.BOLD,23));
		lb_uname.setFont(new Font("宋体",Font.BOLD,23));
		lb_id.setFont(new Font("宋体",Font.BOLD,23));
		
		lb_img = new JLabel(new ImageIcon(
				ClassLoader.getSystemResource("pictures/patient.png")));
		
		//把相应的控件放到面板中去
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
		//主面板中放左右两个面板
		panel_main.add(panel_left);
		panel_main.add(panel_right);
		
		//再把主面板放到窗体中
		this.getContentPane().add(panel_main);//获得当前窗体的内容面板
		//this.pack();//使窗体适应内容
		
		this.setVisible(true);//设置窗体可见
	}
	
	
	private void registerListener() {
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PatientRegisterView();//弹出病人注册视图
			}
		});
		
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String uname = tf_uname.getText().trim();
				String uid = tf_id.getText().trim();
				if(uname.equals("")) {
					JOptionPane.showMessageDialog(PatientLoginView.this, "用户名不能为空");
					return;
				}else if(uid.equals("")){
					JOptionPane.showMessageDialog(PatientLoginView.this, "身份证号不能为空");
					return;
				}
				Patient patient = new Patient(uname,uid);
				patient = patientBiz.login(patient);
				if(patient!=null) {
					new PatientMainView(patient);
					PatientLoginView.this.dispose();
				}else {
					JOptionPane.showMessageDialog(PatientLoginView.this, "用户名或者身份证号错误！");
				}
			}
		});
	}
	
}
