package edu.ncwu.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import edu.ncwu.biz.DoctorBiz;
import edu.ncwu.biz.RecordsBiz;
import edu.ncwu.biz.impl.DoctorBizImpl;
import edu.ncwu.biz.impl.RecordsBizImpl;
import edu.ncwu.data.Doctor;
import edu.ncwu.data.Patient;
import edu.ncwu.data.Records;

public class PatientAppointView extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JPanel btn_panel = null;//��ť���
	private JTable main_table = null;//��ѯ������
	private JPanel main_panel = null;//��������main_table
	private JPanel bottom_panel = null;//ȷ����Ϣ���
	private JPanel panel_0 = null;
	private JPanel panel_1 = null;
	private JPanel panel_2 = null;
	private JPanel panel_3 = null;
	private JPanel panel_4  = null;
	
	private JComboBox<String> cb_way = null;//��ѯ��ʽ�����б��
	private JTextField tf_input = null;//����Ҫ��ѯ��ҽ������
	private JComboBox<String> cb_depart = null;//���������б��
	private JButton btn_search = null;//��ѯ��ť
	private JButton btn_OK = null;//ȷ���ҺŰ�ť
	private JButton btn_exit = null;//�˳���ť
	
	private JLabel lb_name = null;
	private JLabel lb_sex = null;
	private JLabel lb_age = null;
	private JLabel lb_depart = null;
	private JLabel lb_date = null;
	
	private JTextField tf_name = null;
	private JTextField tf_sex = null;
	private JTextField tf_age = null;
	private JTextField tf_depart = null;
	private JTextField tf_date = null;
	
	private List<Doctor> doctorList = null;
	private DoctorinfoTableModel infoTableModel = null;
	private DoctorBiz doctorBiz = null; 
	private RecordsBiz recordsBiz = null;
	public PatientAppointView(Patient patient) {
		doctorBiz = new DoctorBizImpl();
		recordsBiz = new RecordsBizImpl();
		init();
		registerListener(patient);
	}
	
	
	public void init() {
		this.setTitle("���˲�������");
 		this.setSize(800, 500);
 		this.setIconifiable(true);
 		this.setClosable(true);
 		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 		this.setLayout(new BorderLayout());
 		this.setVisible(true);
 		
 		this.setLayout(new BorderLayout());
 		
 		btn_panel = new JPanel(new GridLayout(7,1,0,10));
 		Border bbutton = BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null), "��ѯ����");
 		btn_panel.setBorder(bbutton);
 		
 		cb_way = new JComboBox<String>(new String[] {"ȫ��ҽ��","ҽ������","����"});
 		cb_way.setFont(new Font("����",Font.BOLD,18));
 		tf_input = new JTextField(10);
 		tf_input.setEditable(false);
 		tf_input.setFont(new Font("����",Font.BOLD,18));
 		cb_depart = new JComboBox<String>(new String[] {"���","�ڿ�","Ƥ����","����","�ǿ�","���Ǻ��","���Կ�","�񾭿�"});
 		cb_depart.setFont(new Font("����",Font.BOLD,18));
 		btn_search = new JButton("��ѯ");
 		btn_search.setFont(new Font("����",Font.BOLD,18));
 		btn_OK = new JButton("ȷ���Һ�");
 		btn_OK.setEnabled(false);
 		btn_OK.setFont(new Font("����",Font.BOLD,18));
 		btn_exit = new JButton("�˳�����");
 		btn_exit.setFont(new Font("����",Font.BOLD,18));
 		btn_panel.add(new JLabel());
 		btn_panel.add(cb_way);
 		btn_panel.add(tf_input);
 		btn_panel.add(cb_depart);
 		btn_panel.add(btn_search);
 		btn_panel.add(btn_OK);
 		btn_panel.add(btn_exit);
 		
 		main_table = new JTable();
 		doctorList = new ArrayList<Doctor>();
 		refreshTable(doctorList);
 		
 		main_panel = new JPanel(new BorderLayout());
 		Border btable = BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null,null),"ҽ����Ϣ��");
 		main_panel.setBorder(btable);
 		main_panel.add(new JScrollPane(main_table), BorderLayout.CENTER);
 		
 		lb_name = new JLabel("����",JLabel.CENTER);
 		lb_name.setFont(new Font("����",Font.BOLD,15));
 		lb_sex = new JLabel("�Ա�",JLabel.CENTER);
 		lb_sex.setFont(new Font("����",Font.BOLD,15));
 		lb_age = new JLabel("����",JLabel.CENTER);
 		lb_age.setFont(new Font("����",Font.BOLD,15));
 		lb_depart = new JLabel("����",JLabel.CENTER);
 		lb_depart.setFont(new Font("����",Font.BOLD,15));
 		lb_date = new JLabel("����",JLabel.CENTER);
 		lb_date.setFont(new Font("����",Font.BOLD,15));
 		
 		tf_name = new JTextField();
 		tf_name.setFont(new Font("����",Font.BOLD,15));
 		tf_sex = new JTextField();
 		tf_sex.setFont(new Font("����",Font.BOLD,15));
 		tf_age = new JTextField();
 		tf_age.setFont(new Font("����",Font.BOLD,15));
 		tf_depart = new JTextField();
 		tf_depart.setFont(new Font("����",Font.BOLD,15));
 		tf_date = new JTextField();
 		tf_date.setFont(new Font("����",Font.BOLD,15));
 		
 		panel_0 = new JPanel(new GridLayout(2,1));
 		panel_0.add(lb_name);
 		panel_0.add(tf_name);
 		panel_1 = new JPanel(new GridLayout(2,1));
 		panel_1.add(lb_sex);
 		panel_1.add(tf_sex);
 		panel_2 = new JPanel(new GridLayout(2,1));
 		panel_2.add(lb_age);
 		panel_2.add(tf_age);
 		panel_3 = new JPanel(new GridLayout(2,1));
 		panel_3.add(lb_depart);
 		panel_3.add(tf_depart);
 		panel_4 = new JPanel(new GridLayout(2,1));
 		panel_4.add(lb_date);
 		panel_4.add(tf_date);
 		
 		bottom_panel = new JPanel(new GridLayout(1,5));
 		bottom_panel.add(panel_0);
 		bottom_panel.add(panel_1);
 		bottom_panel.add(panel_2);
 		bottom_panel.add(panel_3);
 		bottom_panel.add(panel_4);
 		bottom_panel.add(new JPanel());
 		
 		main_panel.add(bottom_panel, BorderLayout.SOUTH);
 		this.add(btn_panel, BorderLayout.EAST);
 		this.add(main_panel, BorderLayout.CENTER);
 		
	}
	
	public void registerListener(Patient patient) {
		
		cb_way.addItemListener(new ItemListener() {//�����б������¼�
 			@Override
 			public void itemStateChanged(ItemEvent e) {
 				String item = e.getItem().toString();
 				tf_input.setText("");//�����������е�����
 				if(item.equals("ҽ������")) {
 					tf_input.setEditable(true);
 				}else {
 					tf_input.setEditable(false);
 				}
 			}
 		});
		
		btn_OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf_name.getText().trim();
				String depart = tf_depart.getText().trim();
				String date = tf_date.getText().trim();
				Doctor doctor = doctorBiz.queryDoctorByNameDepart(name, depart);
				Records record = new Records(doctor.getId(),patient.getSno(),"","",doctor.getDepart(),"",date,"","");
				boolean flag = recordsBiz.saveRecord(record);
				if(flag) {
					JOptionPane.showInternalMessageDialog(PatientAppointView.this, "�Һųɹ�!");
				}else {
					JOptionPane.showInternalMessageDialog(PatientAppointView.this, "�Һ�ʧ��!");
				}
			}
		});
		
		main_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//ѡ��һ�к󣬹ҺŰ�ť����
				if(main_table.getSelectedRow() != -1) {
					btn_OK.setEnabled(true);
				}
				int row = main_table.getSelectedRow();//�õ���ѡ�е��е��±�
				String name = main_table.getValueAt(row, 0).toString();
				String sex = main_table.getValueAt(row, 1).toString();
				String age = main_table.getValueAt(row, 2).toString();
				String depart = main_table.getValueAt(row, 3).toString();
				
				//Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = formatter.format(new Date());  
				tf_name.setText(name);
				tf_sex.setText(sex);
				tf_age.setText(age);
				tf_depart.setText(depart);
				tf_date.setText(date);
			}
		});
		
		btn_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String depart[] = {"���","�ڿ�","Ƥ����","����","�ǿ�","���Ǻ��","���Կ�","�񾭿�"};
				int index_way = cb_way.getSelectedIndex();
				String content = tf_input.getText().trim();
				int index_depart = cb_depart.getSelectedIndex();
				if(index_way == 1 && content.equals("")) {
					JOptionPane.showInternalMessageDialog(
							PatientAppointView.this, "��ѯ���ݲ���Ϊ��!");
					return;
				}
				//��������ݣ���ֹ�����ۼ�
				if(doctorList!=null) {
					doctorList.clear();
				}
				if(index_way == 0) {
					doctorList = doctorBiz.queryAllDoctor();
				}else if(index_way == 1){
					doctorList = doctorBiz.queryDoctorByName(content);
				}else if(index_way == 2) {
					doctorList = doctorBiz.queryDoctorByDepart(depart[index_depart], 0);
				}
				refreshTable(doctorList);
				if(doctorList.size() == 0) {
					JOptionPane.showInternalMessageDialog(
							PatientAppointView.this, "û����Ҫ��ѯ��ҽ��!");
					return;
				}
			}
		});
		
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PatientAppointView.this.dispose();
			}
		});
	}
	
	private class DoctorinfoTableModel implements TableModel{
 		private List<Doctor> doctorList = null;
 		public DoctorinfoTableModel(List<Doctor> doctorList) {
 			this.doctorList = doctorList;
 		}
 		
 		//JTable��ʾ����������
 		//  API   ���ظ�ģ���е�������
 		@Override
 		public int getRowCount() {
 			return doctorList.size();
 		}
 		
 		//JTable��ʾ����������
 		//  API  ���ظ�ģ���е�������
 		@Override
 		public int getColumnCount() {
 			return 5;
 		}
 		
 		//����JTable��ʾ������
 		//  API   ���� columnIndex λ�õ��е����ơ�
 		@Override
 		public String getColumnName(int columnIndex) {
 			if(columnIndex==0) {
 				return "����";
 			}else if(columnIndex==1) {
 				return "�Ա�";
 			}else if(columnIndex==2) {
 				return "����";
 			}else if(columnIndex==3) {
 				return "����";
 			}else if(columnIndex==4) {
 				return "�ֻ�����";
 			}else{
 				return "����";
 			}
 		}
 		
 		//JTable�е���������
 		//  API  ����������еĵ�Ԫ��ֵ�����������ĳ��ࡣ
 		@Override
 		public Class<?> getColumnClass(int columnIndex) {
 			return String.class;
 		}
 		
 		//���õ�Ԫ���Ƿ�ɱ༭
 		//  API  ��� rowIndex �� columnIndex λ�õĵ�Ԫ���ǿɱ༭�ģ��򷵻� true��
 		@Override
 		public boolean isCellEditable(int rowIndex,int columnIndex) {
 			return false;
 		}
 		
 		//��ȡJTable��ָ����ָ����Ԫ������
 		//  API  ���� columnIndex �� rowIndex λ�õĵ�Ԫ��ֵ��
 		@Override
 		public Object getValueAt(int rowIndex,int columnIndex ) {
 			Doctor doctor = doctorList.get(rowIndex);
 			if(columnIndex==0) {
 				return doctor.getName();
 			}else if(columnIndex==1) {
 				return doctor.getSex();
 			}else if(columnIndex==2) {
 				return doctor.getAge();
 			}else if(columnIndex==3) {
 				return doctor.getDepart();
 			}else  if(columnIndex==4) {
 				return doctor.getPhone();
 			}else {
 				return "����";
 			}
 		}
 		
 		//  API  ������õ�Ԫ��ɱ༭������Ԫ�����ݱ仯ʱ�ͻ���ô˷���
 		@Override
 		public void setValueAt(Object aValue, int rowIndex,int columnIndex ) {
 			
 		}
 		
 		//  API  ÿ������ģ�ͷ�������ʱ���ͽ�һ����������ӵ���֪ͨ���б��С�
 		@Override
 		public void addTableModelListener(TableModelListener l) {
 			
 		}
 		
 		//  API  ÿ������ģ�ͷ�������ʱ���ʹӱ�֪ͨ���б����Ƴ�һ����������
 		@Override
 		public void removeTableModelListener(TableModelListener l) {
 			
 		}
 		
 		
 	}
 	
 	//ˢ��JTabel����ʾ����
	private void refreshTable(List<Doctor> doctorList) {
		infoTableModel = new DoctorinfoTableModel(doctorList);
		main_table.setModel(infoTableModel);
	}
}
