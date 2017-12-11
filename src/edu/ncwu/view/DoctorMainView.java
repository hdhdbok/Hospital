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

public class DoctorMainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel main_panel = null;//�����
	private JPanel wel_panel = null;//��ӭ���
	private JPanel btn_panel = null;//��ť�����
	private JDesktopPane funcDesktop = null;//������壬�ɷ���Ƕ����
	
	private JButton btn_diagnosis =null;//������ϰ�ť
	private JButton btn_record = null;//��¼��ѯ��ť
	private JButton btn_exit = null;//�˳���ť
	private JLabel lb_welcome = null;//��ӭ����
	private JLabel deskLabel = null;//���ͼƬ��Label
	
	private Doctor doctor = null;
		
		public DoctorMainView(Doctor doctor) {
			this.doctor=doctor;
			init();
			registerListener();
		}
	
	
	private void init() {
		main_panel = new JPanel(new BorderLayout());
		btn_panel = new JPanel(new GridLayout(7,1,0,35));
		btn_diagnosis = new JButton("�������");
		btn_diagnosis.setFont(new Font("����",Font.BOLD,20));
		btn_record = new JButton("��¼��ѯ");
		btn_record.setFont(new Font("����",Font.BOLD,20));
		btn_exit = new JButton("�˳�����");
		btn_exit.setFont(new Font("����",Font.BOLD,20));
		
		//�������ı�ǩ�ؼ�
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.add(btn_diagnosis);
		btn_panel.add(btn_record);
		btn_panel.add(btn_exit);
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		
		//�������ı߿����
		btn_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(),"��ݹ�����"));
		
		//��ʼ����ӭ���
		wel_panel = new JPanel();
		lb_welcome = new JLabel("��  ӭ "+doctor.getName()+" ʹ  ��  ��  ��  ϵ  ͳ");
		lb_welcome.setFont(new Font("����",Font.BOLD,23));
		lb_welcome.setForeground(Color.BLUE);
		wel_panel.add(lb_welcome);
		
		//��ʼ���������
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
		
		this.setTitle("ҽ�����ϵͳ");
		this.getContentPane().add(main_panel);
		this.setSize(1000,650);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
	
	private void registerListener() {
		
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DoctorMainView.this.dispose();
			}
		});
		
		btn_diagnosis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DoctorOperatorView adv = new DoctorOperatorView(doctor);
				funcDesktop.add(adv);//��ָ����ͼ���ӵ�����������
				adv.toFront();//��ͼ��ʾ����ǰ��
			}
		});
		
		btn_record.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DoctorQueryView qdv = new DoctorQueryView(doctor);
				funcDesktop.add(qdv);//��ָ����ͼ���ӵ�����������
				qdv.toFront();//��ͼ��ʾ����ǰ��
			}
		});
	}
	
	
	/**
	 * ����һ���߳��࣬ר���������жԻ�ӭҳ���Label��ǩ�����ƶ���
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