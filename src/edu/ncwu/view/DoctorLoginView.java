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
	
	private JPanel panel_main = null;//主面板
	private JPanel panel_left = null;//左侧面板
	private JPanel panel_right = null;//右侧面板
	
	private JLabel lb_uname = null;//用户标签
	private JLabel lb_pass = null;//用户ID
	private JLabel lb_genre = null;//用户类型
	
	private JTextField tf_uname = null;//用户文本框
	private JPasswordField pf_pass = null;//密码文本框
	private JComboBox<String> cb_genre = null;//类型选择
	
	
	private JLabel lb_img = null;//显示图片的标签
	
	private JButton btn_login = null;//登录按钮
	private JButton btn_register = null;//注册按钮
	

	private DoctorBiz doctorBiz= null;
	
	
	public DoctorLoginView() {
		doctorBiz = new DoctorBizImpl();
		init();
		registerListener();
	}
	
	
	//初始化控件的方法
	private void init() {
		this.setSize(780,360);//设置窗体大小
		this.setResizable(false);//不可拖动窗体大小
		this.setLocationRelativeTo(null);//设置窗体居中显示
		this.setTitle("医生登录窗口");//设置窗体标题
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//关闭窗体功能
		
		//初始化面板
		panel_main = new JPanel(new GridLayout(1,2));//GridLayout(行,列)
		panel_left = new JPanel();
		panel_right =new JPanel(new GridLayout(6,2,0,30));//GridLayout(行,列,水平间隙,垂直间隙)
		
		//初始化控件
		tf_uname = new JTextField(8);//JTextField(文本框显示字符数)
		tf_uname.setFont(new Font("宋体",Font.BOLD,20));
		pf_pass= new JPasswordField(18);//同上
		pf_pass.setFont(new Font("宋体",Font.BOLD,20));
		cb_genre = new JComboBox<String>(new String[] {"医生","药房管理员","管理员"});
		cb_genre.setFont(new Font("宋体",Font.BOLD,20));
		
		btn_login = new JButton("登录");
		btn_register = new JButton("注册");
		lb_uname = new JLabel("姓    名:",JLabel.CENTER);
		lb_pass = new JLabel("密    码:",JLabel.CENTER);
		lb_genre = new JLabel("类    型:",JLabel.CENTER);
		
		btn_login.setFont(new Font("宋体",Font.BOLD,23));
		btn_register.setFont(new Font("宋体",Font.BOLD,23));
		lb_uname.setFont(new Font("宋体",Font.BOLD,23));
		lb_pass.setFont(new Font("宋体",Font.BOLD,23));
		lb_genre.setFont(new Font("宋体",Font.BOLD,23));
		
		lb_img = new JLabel(new ImageIcon(
				ClassLoader.getSystemResource("pictures/doctor.png")));
		
		//把相应的控件放到面板中去
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
				new DoctorRegisterView();//弹出医生注册视图
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
					JOptionPane.showMessageDialog(DoctorLoginView.this, "用户名不能为空");
					return;
				}else if(pass.equals("")){
					JOptionPane.showMessageDialog(DoctorLoginView.this, "密码不能为空");
					return;
				}
				Doctor doctor = new Doctor(name,pass,genre);
				doctor = doctorBiz.login(doctor);
				if(doctor!=null) {
					if(doctor.getGenre() == 0) {
						new DoctorMainView(doctor);
						DoctorLoginView.this.dispose();//登陆成功后，关闭登录界面
					}else if(doctor.getGenre() == 1){ 
						new MedicionMainView(doctor);
						DoctorLoginView.this.dispose();//登陆成功后，关闭登录界面
					}else if(doctor.getGenre() == 2){ 
						new AdminMainView(doctor);
						DoctorLoginView.this.dispose();//登陆成功后，关闭登录界面
					}
				}else {
					JOptionPane.showMessageDialog(DoctorLoginView.this, "用户名或者密码错误！");
					return;
				}
			}
		});
	}
}
