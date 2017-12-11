package edu.ncwu.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuideView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel_main = null;
	private JPanel panel_left = null;
	private JPanel panel_right = null;
	
	private JButton btn_doc = null;
	private JButton btn_pti = null;
	private JLabel lb_img = null;
	
	public GuideView() {
		init();
		guideListener();
	}
	
	public void init() {
		this.setSize(640, 360);//设置窗体大小
		//this.setResizable(false);//不可拖动窗体大小
		this.setLocationRelativeTo(null);//设置窗体居中
		this.setTitle("引导界面");//设置标题
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//关闭窗体工能
		
		//初始化面板
		panel_main = new JPanel(new GridLayout(1,2));//GridLayout(行,列)
		panel_left = new JPanel();
		panel_right = new JPanel(new GridLayout(7,1,10,0));//GridLayout(行,列,水平间隙,垂直间隙)
		
		//初始化控件
		btn_doc = new JButton("医     生");
		btn_doc.setFont(new Font("宋体",Font.BOLD,23));
		btn_pti = new JButton("病     人");
		btn_pti.setFont(new Font("宋体",Font.BOLD,23));
		lb_img = new JLabel(new ImageIcon(ClassLoader.getSystemResource("pictures/guide.jpg")));

		//把相应控件撞到对应面板中去
		panel_left.add(lb_img);
		panel_right.add(new JLabel());
		panel_right.add(new JLabel());
		panel_right.add(btn_doc);
		panel_right.add(new JLabel());
		panel_right.add(btn_pti);
		panel_right.add(new JLabel());

		
		//把左右面板放进主面板
		panel_main.add(panel_left);
		panel_main.add(panel_right);
		
		//把主面板放进窗体中
		this.getContentPane().add(panel_main);
		//this.pack();//使窗体适应内容
		
		this.setVisible(true);//设置窗体可见，必备，否则窗体不显示
		
	}
	
	private void guideListener() {
		btn_doc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DoctorLoginView();
				GuideView.this.dispose();
			}
		});
		
		btn_pti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PatientLoginView();
				GuideView.this.dispose();
			}
		});
	
		
		
		
		
		
		
	}
	
	
	
}
