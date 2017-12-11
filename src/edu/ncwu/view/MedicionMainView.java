package edu.ncwu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ncwu.data.Doctor;

public class MedicionMainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel main_panel = null;//主面板
	private JPanel wel_panel = null;//欢迎面板
	private JPanel btn_panel = null;//按钮组面板
	private JDesktopPane funcDesktop = null;//桌面面板，可放内嵌窗体
	
	private JButton btn_medicion_oper =null;//药品管理按钮，增删改
	private JButton btn_medicion_out = null;//取药按钮
	private JButton btn_exit = null;//退出按钮
	private JLabel lb_welcome = null;//欢迎标题
	private JLabel deskLabel = null;//存放图片的Label
	
	private Doctor doctor = null;
		
		public MedicionMainView(Doctor doctor) {
			this.doctor=doctor;
			init();
			registerListener();
		}
	
	
	private void init() {
		main_panel = new JPanel(new BorderLayout());
		btn_panel = new JPanel(new GridLayout(7,1,0,10));
		btn_medicion_oper = new JButton("药品管理操作");
		btn_medicion_oper.setFont(new Font("宋体",Font.BOLD,20));
		btn_medicion_out = new JButton("取药操作");
		btn_medicion_out.setFont(new Font("宋体",Font.BOLD,20));
		btn_exit = new JButton("退出窗口");
		btn_exit.setFont(new Font("宋体",Font.BOLD,20));
		
		//用来填充的标签控件
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.add(btn_medicion_oper);
		btn_panel.add(new JLabel());
		btn_panel.add(btn_medicion_out);
		btn_panel.add(new JLabel());
		btn_panel.add(btn_exit);
		
		
		
		//设置面板的边框外观
		btn_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(),"快捷功能区"));
		
		//初始化欢迎面板
		wel_panel = new JPanel();
		lb_welcome = new JLabel("欢  迎 "+doctor.getName()+" 使  用  药  房  系  统");
		lb_welcome.setFont(new Font("宋体",Font.BOLD,23));
		lb_welcome.setForeground(Color.BLUE);
		wel_panel.add(lb_welcome);
		
		//初始化桌面面板
		funcDesktop = new JDesktopPane();
		ImageIcon image = new ImageIcon("src/image/login.jpg");
		deskLabel = new JLabel(image);
		deskLabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		funcDesktop.add(deskLabel,new Integer(Integer.MIN_VALUE));
		
		main_panel.add(btn_panel,BorderLayout.EAST);
		main_panel.add(wel_panel,BorderLayout.NORTH);
		main_panel.add(funcDesktop,BorderLayout.CENTER);
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Thread(new DynaminThread()).start();	
			}
			
		});
		
		this.setTitle("药房系统");
		this.getContentPane().add(main_panel);
		this.setSize(1000,650);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
	
	private void registerListener() {
		btn_medicion_oper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicionOperatorView adv = new MedicionOperatorView();
				funcDesktop.add(adv);//把指定视图添加到桌面容器中
				adv.toFront();//视图显示在最前面
			}
		});
		
		btn_medicion_out.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicineGetView qdv = new MedicineGetView();
				funcDesktop.add(qdv);//把指定视图添加到桌面容器中
				qdv.toFront();//视图显示在最前面
			}
		});
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicionMainView.this.dispose();
			}
		});
		
	}
	
	
	/**
	 * 这是一个线程类，专门用来进行对欢迎页面的Label标签进行移动的
	 */
	
	private class DynaminThread implements Runnable{

		@Override
		public void run() {
			while(true) {
				for(int i = 1000;i>-750;i--)
				{
					try {
						Thread.sleep(10);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					lb_welcome.setLocation(i,5);
				}
				
			}
			
		}
			
	}
}
