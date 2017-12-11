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
import javax.swing.JComboBox;
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
import edu.ncwu.util.HospitalUtil;

public class MedicineGetView extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JPanel main_panel = null;//�����
	private JTable main_table0 = null;//��ʾ�ϰ벿�����ݵ�Table
	private JPanel main_panel0 = null;//�������ϰ벿��
	private JPanel main_panel1 = null;//�������°벿��
	private JPanel main_panel1_top = null;
	private JPanel main_panel1_bot = null;
	private JPanel btn_panel = null;//��ť���
	
	private JComboBox<String> cb_genre = null;//�����б�ѡ��
	private JTextField tf_input = null;//�����
	private JButton btn_query = null;//��ѯ��ť
	private JButton btn_OK = null;//ȷ��ȡҩ��ť
	private JButton btn_exit = null;//�˳���ť
	
	private JLabel lb_psno= null;
	private JLabel lb_pname= null;
	private JLabel lb_dname= null;
	private JLabel lb_date= null;
	
	private JTextField tf_psno= null;
	private JTextField tf_pname= null;
	private JTextField tf_dname= null;
	private JTextField tf_date= null;
	
	private JTextArea ta_drug = null;
	
	private MedicineInforTableModel infoTableModel = null;
	private DoctorBiz doctorBiz = null;
	private PatientBiz patientBiz = null;
	private RecordsBiz recordsBiz = null;
	private MedicionBiz medicineBiz = null;
	private List<Records> recordsList = null;
	public MedicineGetView() {
		doctorBiz = new DoctorBizImpl();
		patientBiz = new PatientBizImpl();
		recordsBiz = new RecordsBizImpl();
		medicineBiz = new MedicionBizImpl();
		init();
		registerListener();
	}
	public void init() {
		this.setTitle("ҩƷ����ϵͳ");
		this.setSize(800, 500);
		this.setVisible(true);
		this.setResizable(false);
		this.setIconifiable(true);
 		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		btn_panel = new JPanel(new GridLayout(7,1,0,10));
		Border bbutton = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null,null),"��ݹ���");
		btn_panel.setBorder(bbutton);
		cb_genre = new JComboBox<String>(new String[] {"����","����"});
		cb_genre.setFont(new Font("����",Font.BOLD,20));
		tf_input = new JTextField();
		tf_input.setFont(new Font("����",Font.BOLD,20));
		btn_query = new JButton("��ѯ");
		btn_query.setFont(new Font("����",Font.BOLD,20));
		btn_OK = new JButton("ȷ��ȡҩ");
		btn_OK.setEnabled(false);
		btn_OK.setFont(new Font("����",Font.BOLD,20));
		btn_exit = new JButton("�˳�����");
		btn_exit.setFont(new Font("����",Font.BOLD,20));
		btn_panel.add(cb_genre);
		btn_panel.add(tf_input);
		btn_panel.add(btn_query);
		btn_panel.add(new JLabel());
		btn_panel.add(btn_OK);
		btn_panel.add(new JLabel());
		btn_panel.add(btn_exit);
		this.add(btn_panel, BorderLayout.EAST);
		
		main_panel = new JPanel(new GridLayout(2,1));
		recordsList = new ArrayList<Records>();
		main_table0 = new JTable();
		//��JLabel������ģ�ͳ�������
		refreshTable(recordsList);
		
		main_panel0 = new JPanel(new BorderLayout());
		Border btable = BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"������¼��ѯ");
		main_panel0.setBorder(btable);
		main_panel0.add(new JScrollPane(main_table0),BorderLayout.CENTER);
		
		main_panel1 = new JPanel(new GridLayout(2,1));
		Border btable1 = BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"ȡҩ����");
		main_panel1.setBorder(btable1);
		
		main_panel1_top = new JPanel(new GridLayout(2,4,0,10));
		main_panel1_bot = new JPanel(new GridLayout(1,1,0,10));
		Border bbot = BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"ҩƷ��Ϣ");
		main_panel1_bot.setBorder(bbot);
		
		lb_psno = new JLabel("���˲���");
		btn_exit.setFont(new Font("����",Font.BOLD,15));
		lb_pname = new JLabel("��������");
		btn_exit.setFont(new Font("����",Font.BOLD,15));
		lb_dname = new JLabel("ҽ������");
		btn_exit.setFont(new Font("����",Font.BOLD,15));
		lb_date = new JLabel("��ҩ����");
		btn_exit.setFont(new Font("����",Font.BOLD,15));
		tf_psno = new JTextField();
		tf_psno.setEditable(false);
		tf_psno.setFont(new Font("����",Font.BOLD,15));
		tf_pname = new JTextField();
		tf_pname.setEditable(false);
		tf_pname.setFont(new Font("����",Font.BOLD,15));
		tf_dname = new JTextField();
		tf_dname.setEditable(false);
		tf_dname.setFont(new Font("����",Font.BOLD,15));
		tf_date = new JTextField();
		tf_date.setEditable(false);
		tf_date.setFont(new Font("����",Font.BOLD,15));
		
		main_panel1_top.add(lb_psno);
		main_panel1_top.add(lb_pname);
		main_panel1_top.add(lb_dname);
		main_panel1_top.add(lb_date);
		main_panel1_top.add(tf_psno);
		main_panel1_top.add(tf_pname);
		main_panel1_top.add(tf_dname);
		main_panel1_top.add(tf_date);
		
		ta_drug = new JTextArea();
		ta_drug.setEditable(false);
		ta_drug.setFont(new Font("����",Font.BOLD,15));
		
		main_panel1_bot.add(ta_drug);
		main_panel1.add(main_panel1_top);
		main_panel1.add(main_panel1_bot);
		
		main_panel.add(main_panel0);
		main_panel.add(main_panel1);
		this.add(main_panel, BorderLayout.CENTER);
	}
	
	public void registerListener() {
		
		btn_OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String drug = ta_drug.getText().trim();
				if(drug.equals("")) {
					JOptionPane.showInternalMessageDialog(
							MedicineGetView.this, "û��ҩƷ��Ҫȡ��!");
					return;
				}
				int count = 0;
				String drug_S[] = drug.split(" ");
				for(int i = 0; i<drug_S.length; i++) {
					Medicine medicine = new Medicine();
					medicine.setNumber(medicine.getNumber()-1);
					if(medicine.getNumber()<0) {
						JOptionPane.showInternalMessageDialog(
								MedicineGetView.this, medicine.getDrug_name()+"  �Ѿ�û�п����!");
						return;
					}
					boolean flag = medicineBiz.updateMedicion(medicine);
					if(!flag) {
						JOptionPane.showInternalMessageDialog(
								MedicineGetView.this, drug_S[i]+"  ȡҩʧ��!");
						return;
					}
					count+=1;
				}
				if(count == drug_S.length) {
					JOptionPane.showInternalMessageDialog(
							MedicineGetView.this, "ȡҩ�ɹ�!");
					return;
				}
			}
		});
		
		main_table0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = main_table0.getSelectedRow();//�õ���ѡ���е��±�
				String psno = main_table0.getValueAt(row, 0).toString();
				String pname = main_table0.getValueAt(row, 1).toString();
				String dname = main_table0.getValueAt(row, 2).toString();
				String date = main_table0.getValueAt(row, 3).toString();
				
				Records record = recordsBiz.queryRecordsByIndate(date);
				Medicine medicine = medicineBiz.queryMedicionByDrug_sno(Integer.parseInt(record.getDrug_sno()));
				tf_psno.setText(psno);
				tf_pname.setText(pname);
				tf_dname.setText(dname);
				tf_date.setText(date);
				ta_drug.setText(medicine.getDrug_name());
				
			}
		});
		
		btn_query.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = cb_genre.getSelectedIndex();
				String psno_pname = tf_input.getText().trim();
				if(recordsList != null) {
					recordsList = null;
				}
				if(psno_pname.equals("")) {
					JOptionPane.showInternalMessageDialog(
							MedicineGetView.this, "��ѯ���ݲ���Ϊ��!");
					return;
				}
				if(index == 0) {
					
					if(HospitalUtil.isNumber(psno_pname)) {
						int sno = Integer.parseInt(psno_pname);
						recordsList = recordsBiz.queryRecordsByPsno(sno);
					}else {
						JOptionPane.showInternalMessageDialog(
								MedicineGetView.this, "����ֻ��������!");
						return;
					}
					
				}else if(index == 1){
					Patient patient0 = patientBiz.queryPatientByName(psno_pname).get(0);
					//System.out.println(patient0.getName());
					recordsList = recordsBiz.queryRecordsByPsno(patient0.getSno());
					//System.out.println(recordsList.get(0));
				}
				
				if(recordsList == null) {
					JOptionPane.showInternalMessageDialog(
							MedicineGetView.this, "û�в�ѯ����Ҫ������!");
				}
				refreshTable(recordsList);
			}
		});
		
		
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicineGetView.this.dispose();
			}
		});
		
	}
	
 	private class MedicineInforTableModel implements TableModel{
 		private List<Records> recordsList = null;
 		public MedicineInforTableModel(List<Records> recordsList) {
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
 				return "��������";
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
 			Patient patient = patientBiz.queryPatientBySno(records.getPsno());
 			Doctor doctor = doctorBiz.queryDoctorById(records.getDid());
 			if(columnIndex==0) {
 				return records.getPsno();
 			}else if(columnIndex==1) {
 				return patient.getName();
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
 		infoTableModel = new MedicineInforTableModel(recordsList);
 		main_table0.setModel(infoTableModel);
 	}
 	
	
}
