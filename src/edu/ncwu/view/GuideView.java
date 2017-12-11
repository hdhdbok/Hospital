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
		this.setSize(640, 360);//���ô����С
		//this.setResizable(false);//�����϶������С
		this.setLocationRelativeTo(null);//���ô������
		this.setTitle("��������");//���ñ���
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//�رմ��幤��
		
		//��ʼ�����
		panel_main = new JPanel(new GridLayout(1,2));//GridLayout(��,��)
		panel_left = new JPanel();
		panel_right = new JPanel(new GridLayout(7,1,10,0));//GridLayout(��,��,ˮƽ��϶,��ֱ��϶)
		
		//��ʼ���ؼ�
		btn_doc = new JButton("ҽ     ��");
		btn_doc.setFont(new Font("����",Font.BOLD,23));
		btn_pti = new JButton("��     ��");
		btn_pti.setFont(new Font("����",Font.BOLD,23));
		lb_img = new JLabel(new ImageIcon(ClassLoader.getSystemResource("pictures/guide.jpg")));

		//����Ӧ�ؼ�ײ����Ӧ�����ȥ
		panel_left.add(lb_img);
		panel_right.add(new JLabel());
		panel_right.add(new JLabel());
		panel_right.add(btn_doc);
		panel_right.add(new JLabel());
		panel_right.add(btn_pti);
		panel_right.add(new JLabel());

		
		//���������Ž������
		panel_main.add(panel_left);
		panel_main.add(panel_right);
		
		//�������Ž�������
		this.getContentPane().add(panel_main);
		//this.pack();//ʹ������Ӧ����
		
		this.setVisible(true);//���ô���ɼ����ر��������岻��ʾ
		
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
