package edu.ncwu.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import edu.ncwu.biz.DoctorBiz;
import edu.ncwu.biz.MedicionBiz;
import edu.ncwu.biz.PatientBiz;
import edu.ncwu.biz.RecordsBiz;
import edu.ncwu.biz.impl.DoctorBizImpl;
import edu.ncwu.biz.impl.MedicionBizImpl;
import edu.ncwu.biz.impl.PatientBizImpl;
import edu.ncwu.biz.impl.RecordsBizImpl;
import edu.ncwu.data.Doctor;
import edu.ncwu.data.Medicine;
import edu.ncwu.data.Patient;
import edu.ncwu.data.Records;

public class PatientOperatorView extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panel_main = null;//�����������ݵ�һ�����
 	private JPanel btn_panel = null;//��ť�����
 	private JPanel content_panel = null;//��ʾ������Ϣ���������    1
 	private JTable content_table = null;//����JTable     2
 	private JPanel table_panel = null;//��ʾ������Ϣ 	,��������JTable��һ�����
 	private JPanel info_panel = null;//��ʾ��ҩ��ҽ�������     3
 	
 	private JPanel sno_name_panel = null;//��ʾ���ź����������
 	private JPanel id_sex__panel = null;//��ʾ���֤�ź��Ա�����
 	private JPanel OK_NO_panel = null;//��ʾȷ����ȡ����ť�����
 	private JPanel age_phone_panel = null;//��ʾ������ֻ��ŵ����
 	
 	private JPanel drug_panel = null;//��ʾ��ҩ�����
 	private JPanel advice_panel = null;//��ʾҽ�������
 	
 	private JButton btn_modify = null;//�޸İ�ť
	private JButton btn_search = null;//��¼��ѯ��ť
	private JButton btn_exit = null;//�˳���ť
 	
	private JLabel lb_sno = null;
	private JLabel lb_name = null;
	private JLabel lb_sex = null;
	private JLabel lb_age = null;
	private JLabel lb_id = null;
	private JLabel lb_phone = null;
	private JButton btn_OK = null;
	private JButton btn_NO = null;
	private JTextField tf_sno = null;
	private JTextField tf_name = null;
	private JTextField tf_sex = null;
	private JTextField tf_age = null;
	private JTextField tf_id = null;
	private JTextField tf_phone = null;
	
	private JTextArea ta_drug = null;
	private JTextArea ta_advice = null;
	
	private MedicionBiz medicineBiz = null;
	private RecordsBiz recordsBiz = null;
	private DoctorBiz doctorBiz = null;
	private PatientBiz patientBiz = null;
	private List<Records> recordsList = null;
	private PatientInforTableModel infoTableModel = null;
 	//��ʼ��������
 	public PatientOperatorView(Patient patient){
 		medicineBiz = new MedicionBizImpl();
 		recordsBiz = new RecordsBizImpl();
 		doctorBiz = new DoctorBizImpl();
 		patientBiz = new PatientBizImpl();
 		init(patient);
 		registerListener(patient);
 	}
 	
 	private void init(Patient patient){
 		this.setTitle("���˲�������");
 		this.setSize(800, 500);
 		this.setIconifiable(true);
 		this.setClosable(true);
 		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 		this.setLayout(new BorderLayout());
 
 		btn_modify = new JButton("�޸���Ϣ");
 		btn_search = new JButton("��¼��ѯ");
 		btn_exit = new JButton("�˳�����");
 		btn_modify.setFont(new Font("����",Font.BOLD,18));
 		btn_search.setFont(new Font("����",Font.BOLD,18));
 		btn_exit.setFont(new Font("����",Font.BOLD,18));
 		
 		
 		panel_main = new JPanel(new BorderLayout());
 		
 		btn_panel = new JPanel(new GridLayout(7,1,10,10));
 		Border bbutton = BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null), "��ѯ����");
 		btn_panel.setBorder(bbutton);
 		this.add(btn_panel, BorderLayout.EAST);
 		
 		btn_panel.add(new JLabel());
 		btn_panel.add(btn_modify);
 		btn_panel.add(new JLabel());
 		btn_panel.add(btn_search);
 		btn_panel.add(new JLabel());
 		btn_panel.add(new JLabel());
 		btn_panel.add(btn_exit);
 		
 		
 		sno_name_panel = new JPanel(new GridLayout(1,4));
 		id_sex__panel = new JPanel(new GridLayout(1,4));
 		age_phone_panel = new JPanel(new GridLayout(1,4));
 		OK_NO_panel = new JPanel(new GridLayout(1,5));
 		content_panel = new JPanel(new GridLayout(4,1));
 		Border bself = BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"������Ϣ");
 		content_panel.setBorder(bself);
 		lb_sno = new JLabel("����",JLabel.CENTER);
 		lb_name = new JLabel("����",JLabel.CENTER);
 		lb_sex = new JLabel("�Ա�",JLabel.CENTER);
 		lb_id = new JLabel("���֤��",JLabel.CENTER);
 		lb_phone = new JLabel("�ֻ���",JLabel.CENTER);
 		lb_age = new JLabel("����",JLabel.CENTER);
 		btn_OK = new JButton("ȷ��");
 		btn_OK.setEnabled(false);//Ĭ�ϲ�����
 		btn_NO = new JButton("ȡ��");
 		btn_NO.setEnabled(false);//Ĭ�ϲ�����
 		
 		tf_sno = new JTextField(""+patient.getSno());
 		tf_sno.setEnabled(false);
 		tf_name = new JTextField(patient.getName());
 		tf_name.setEnabled(false);
 		tf_sex = new JTextField(patient.getSex());
 		tf_sex.setEnabled(false);
 		tf_id = new JTextField(patient.getId());
 		tf_id.setEnabled(false);
 		tf_phone = new JTextField(patient.getPhone());
 		tf_phone.setEnabled(false);
 		tf_age = new JTextField(""+patient.getAge());
 		tf_age.setEnabled(false);
 		
 		sno_name_panel.add(lb_sno);
 		sno_name_panel.add(tf_sno);
 		sno_name_panel.add(lb_name);
 		sno_name_panel.add(tf_name);
 		content_panel.add(sno_name_panel);
 		id_sex__panel.add(lb_id);
 		id_sex__panel.add(tf_id);
 		id_sex__panel.add(lb_sex);
 		id_sex__panel.add(tf_sex);
 		content_panel.add(id_sex__panel);
 		age_phone_panel.add(lb_age);
 		age_phone_panel.add(tf_age);
 		age_phone_panel.add(lb_phone);
 		age_phone_panel.add(tf_phone);
 		content_panel.add(age_phone_panel);
 		OK_NO_panel.add(new JLabel());
 		OK_NO_panel.add(btn_OK);
 		OK_NO_panel.add(new JLabel());
 		OK_NO_panel.add(btn_NO);
 		OK_NO_panel.add(new JLabel());
 		content_panel.add(OK_NO_panel);
 		
 		recordsList = new ArrayList<Records>();
 		content_table = new JTable();
 		
 		//��JLabel������ģ�ͳ�������
 		refreshTable(recordsList);
 		
 		table_panel = new JPanel(new BorderLayout());//�����������
 		Border btable = BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"������¼��ѯ");
 		table_panel.setBorder(btable);
 		table_panel.add(new JScrollPane(content_table), BorderLayout.CENTER);
 		
 		ta_drug = new JTextArea();
 		ta_advice = new JTextArea();
 		ta_drug.setFont(new Font("����",Font.BOLD,18));
 		ta_advice.setFont(new Font("����",Font.BOLD,18));
 		info_panel = new JPanel(new GridLayout(1,2));
 		drug_panel = new JPanel();
 		Border bdrug= BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"����");
 		drug_panel.setBorder(bdrug);
 		advice_panel = new JPanel();
 		Border badvice = BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"ҽ��");
 		advice_panel.setBorder(badvice);
 		drug_panel.add(ta_drug);
 		advice_panel.add(ta_advice);
 		info_panel.add(drug_panel);
 		info_panel.add(advice_panel);
 		
 		panel_main.add(content_panel,BorderLayout.NORTH);
 	 	panel_main.add(table_panel,BorderLayout.CENTER);
 	 	panel_main.add(info_panel,BorderLayout.SOUTH);
 		
 		this.add(panel_main, BorderLayout.CENTER);
 		this.setVisible(true);
 	}
 	
 	public void registerListener(Patient patient) {
 		
 		content_table.addMouseListener(new MouseAdapter() {
 			@Override
 			public void mouseClicked(MouseEvent e) {
 				Records record = new Records();
 				Medicine medicine = new Medicine();
 	 			int row = content_table.getSelectedRow();//�õ���ѡ�е��±�
 	 			String date = content_table.getValueAt(row, 4).toString();
 	 			record = recordsBiz.queryRecordsByIndate(date);
 	 			medicine = medicineBiz.queryMedicionByDrug_sno(new Integer(record.getDrug_sno()));
 	 			ta_drug.setText(""+medicine.getDrug_name());
 	 			ta_advice.setText(""+record.getAdvice());
 			}
 		});
 		
 		btn_search.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				
 				if(recordsList != null) {
 					recordsList.clear();
 				}
 				recordsList = recordsBiz.queryRecordsByPsno(patient.getSno());
 				refreshTable(recordsList);
 				if(recordsList.size() == 0) {
 					JOptionPane.showInternalMessageDialog(
 							PatientOperatorView.this, "û����Ҫ��ѯ������!");
 				}
 				
 			}
 		});
 		
 		
 		btn_modify.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				tf_phone.setEnabled(true);
 				btn_OK.setEnabled(true);
 				btn_NO.setEnabled(true);
 			}
 		});
 		
 		btn_OK.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				String phone = tf_phone.getText().trim();
 				Patient patient1 = new Patient(patient.getName(),patient.getId(),patient.getAge(),patient.getSex(),phone);
 				int flag = JOptionPane.showInternalConfirmDialog(
 						PatientOperatorView.this, "�Ƿ�ȷ�����¸�����Ϣ?","ȷ����Ϣ",
 						JOptionPane.YES_NO_OPTION);
 				if(flag == JOptionPane.YES_OPTION) {
	 				boolean flag1 = patientBiz.updatePatient(patient1);
	 				if(flag1) {
	 					JOptionPane.showInternalMessageDialog(
	 							PatientOperatorView.this, "���³ɹ�!");
	 					tf_phone.setText(patient1.getPhone());
	 					tf_phone.setEnabled(false);
	 	 				btn_OK.setEnabled(false);
	 	 				btn_NO.setEnabled(false);
	 				}else {
	 					JOptionPane.showInternalMessageDialog(
	 							PatientOperatorView.this, "����ʧ��!");
	 				}
	 			}
 			}
 		});
 		
 		btn_NO.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				tf_phone.setText(patient.getPhone());
 				tf_phone.setEnabled(false);
 				btn_OK.setEnabled(false);
 				btn_NO.setEnabled(false);
 			}
 		});
 		
 		btn_exit.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				PatientOperatorView.this.dispose();
 			}
 		});
 		
 	}
 	
 	private class PatientInforTableModel implements TableModel{
 		private List<Records> recordsList = null;
 		public PatientInforTableModel(List<Records> recordsList) {
 			this.recordsList = recordsList;
 		}
 		
 		//JTable��ʾ����������
 		//  API   ���ظ�ģ���е�������
 		@Override
 		public int getRowCount() {
 			return recordsList.size();
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
 				return "���˲���";
 			}else if(columnIndex==1) {
 				return "���ﲡ��";
 			}else if(columnIndex==2) {
 				return "ҽ������";
 			}else if(columnIndex==3) {
 				return "�������";
 			}else if(columnIndex==4) {
 				return "��������";
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
 			Records records = recordsList.get(rowIndex);
 			Doctor doctor = doctorBiz.queryDoctorById(records.getDid());
 			if(columnIndex==0) {
 				return records.getPsno();
 			}else if(columnIndex==1) {
 				return records.getDisease();
 			}else if(columnIndex==2) {
 				return doctor.getName();
 			}else if(columnIndex==3) {
 				return records.getDepart();
 			}else if(columnIndex==4) {
 				return records.getIndate();
 			}else{
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
 	
 	private void refreshTable(List<Records> recordsList) {
 		infoTableModel = new PatientInforTableModel(recordsList);
 		content_table.setModel(infoTableModel);
 	}
 	
 	
 	
}
