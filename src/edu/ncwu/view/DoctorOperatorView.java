package edu.ncwu.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import edu.ncwu.biz.MedicionBiz;
import edu.ncwu.biz.PatientBiz;
import edu.ncwu.biz.RecordsBiz;
import edu.ncwu.biz.impl.MedicionBizImpl;
import edu.ncwu.biz.impl.PatientBizImpl;
import edu.ncwu.biz.impl.RecordsBizImpl;
import edu.ncwu.data.Doctor;
import edu.ncwu.data.Patient;
import edu.ncwu.data.Records;

public class DoctorOperatorView extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JPanel main_panel = null;//总面板
 	private JTable main_table = null;//声明JTable
 	private JPanel table_panel = null;//用来保存JTable的一个面板
 	private JPanel btn_panel = null;//按钮区面板
 	private JPanel panelContent = null;//内容面板
 	private JPanel panel_up = null;
 	private JPanel panel_down = null;
 	private JPanel panel_down0 = null;
 	private JPanel panel_down1 = null;
 	private JPanel panel_down2 = null;
	
	private JButton btn_search = null;//查询按钮
	private JButton btn_OK = null;//确定按钮
	private JButton btn_exit = null;//退出按钮
	
	
	private JLabel lb_sno= null;
	private JLabel lb_name = null;
	private JLabel lb_id = null;
	private JLabel lb_disease = null;
	private JLabel lb_indate = null;
	
	private JTextField tf_sno= null;
	private JTextField tf_name = null;
	private JTextField tf_id = null;
	private JTextField tf_disease = null;
	private JTextField tf_indate = null;
	
	private JTextArea ta_0 = null;
	private JTextArea ta_1 = null;
	private JTextArea ta_2 = null;
 	
	
	private List<Records> recordsList = null;
	private RecordsBiz recordsBiz = null;
	private PatientBiz patientBiz = null;
	private MedicionBiz medicineBiz = null;
	private PatientinfoTableModel infoTableModel = null;
 	//初始化构造器
 	public DoctorOperatorView(Doctor doctor){
 		recordsBiz = new RecordsBizImpl();
 		patientBiz = new PatientBizImpl();
 		medicineBiz = new MedicionBizImpl();
 		init();
 		registerListener(doctor);

 	}
 	
 	private void init(){
 		this.setTitle("医生就诊界面");
 		this.setSize(800, 500);
 		this.setIconifiable(true);
 		this.setClosable(true);
 		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 		this.setLayout(new BorderLayout());
 		this.setVisible(true);
 		
 		
 		btn_panel = new JPanel(new GridLayout(7,1,0,10));
 		btn_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createRaisedBevelBorder(),"快捷功能区"));
 		btn_search = new JButton("挂号查询");
 		btn_search.setFont(new Font("宋体",Font.BOLD,20));
 		btn_OK = new JButton("确定");
 		btn_OK.setEnabled(false);
 		btn_OK.setFont(new Font("宋体",Font.BOLD,20));
 		btn_exit = new JButton("退出窗口 ");
 		btn_exit.setFont(new Font("宋体",Font.BOLD,20));
 		btn_panel.add(new JLabel());
 		btn_panel.add(btn_search);
 		btn_panel.add(new JLabel());
 		btn_panel.add(new JLabel());
 		btn_panel.add(btn_OK);
 		btn_panel.add(new JLabel());
 		btn_panel.add(btn_exit);
 		this.add(btn_panel, BorderLayout.EAST);
 		
 		
 		recordsList = new ArrayList<Records>();
 		main_table = new JTable();
 		refreshTable(recordsList);
 		table_panel = new JPanel(new BorderLayout());
 		Border btable = BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"挂号信息表");
 		table_panel.setBorder(btable);
 		table_panel.add(new JScrollPane(main_table), BorderLayout.CENTER);
 		
 		
 		panelContent = new JPanel(new GridLayout(2,1));
 		panelContent.setBorder(BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"开处方区"));
 		panel_up = new JPanel(new GridLayout(2,5));
 		lb_sno = new JLabel("病号");
 		lb_sno.setFont(new Font("宋体",Font.BOLD,15));
 		lb_name = new JLabel("姓名");
 		lb_name.setFont(new Font("宋体",Font.BOLD,15));
 		lb_id = new JLabel("身份证号");
 		lb_id.setFont(new Font("宋体",Font.BOLD,15));
 		lb_disease = new JLabel("病名");
 		lb_disease.setFont(new Font("宋体",Font.BOLD,15));
 		lb_indate = new JLabel("日期");
 		lb_indate.setFont(new Font("宋体",Font.BOLD,15));
 		
 		tf_sno = new JTextField();
 		tf_sno.setEnabled(false);
 		tf_sno.setFont(new Font("宋体",Font.BOLD,15));
 		tf_name = new JTextField();
 		tf_name.setEnabled(false);
 		tf_name.setFont(new Font("宋体",Font.BOLD,15));
 		tf_id = new JTextField();
 		tf_id.setEnabled(false);
 		tf_id.setFont(new Font("宋体",Font.BOLD,15));
 		tf_disease = new JTextField();
 		tf_disease.setFont(new Font("宋体",Font.BOLD,15));
 		tf_indate = new JTextField();
 		tf_indate.setEnabled(false);
 		tf_indate.setFont(new Font("宋体",Font.BOLD,15));
 		
 		panel_up.add(lb_sno);
 		panel_up.add(lb_name);
 		panel_up.add(lb_id);
 		panel_up.add(lb_indate);
 		panel_up.add(lb_disease);
 		panel_up.add(tf_sno);
 		panel_up.add(tf_name);
 		panel_up.add(tf_id);
 		panel_up.add(tf_indate);
 		panel_up.add(tf_disease);
 		
 		ta_0 = new JTextArea();
 		ta_0.setEnabled(true);
 		ta_0.setEditable(true);
 		ta_1 = new JTextArea();
 		ta_2 = new JTextArea();
 		
 		panel_down = new JPanel(new GridLayout(1,3,0,10));
 		panel_down0 = new JPanel(new GridLayout(1,1,0,10));
 		panel_down0.setBorder(BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"开药处"));
 		panel_down0.add(ta_0);
 		panel_down1 = new JPanel(new GridLayout(1,1,0,10));
 		panel_down1.setBorder(BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"医嘱"));
 		panel_down1.add(ta_1);
 		panel_down2 = new JPanel(new GridLayout(1,1,0,10));
 		panel_down2.setBorder(BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null),"病房号"));
 		panel_down2.add(ta_2);
 		
 		panel_down0.add(ta_0);
 		panel_down1.add(ta_1);
 		panel_down2.add(ta_2);
 		panel_down.add(panel_down0);
 		panel_down.add(panel_down1);
 		panel_down.add(panel_down2);
 		
 		panelContent.add(panel_up);
 		panelContent.add(panel_down);
 		
 		main_panel = new JPanel(new GridLayout(2,1));
 		main_panel.add(table_panel);
 		main_panel.add(panelContent);
 		this.add(main_panel, BorderLayout.CENTER);
 	}
 	
 	public void registerListener(Doctor doctor){
 		
 		main_table.addMouseListener(new MouseAdapter() {
 			@Override
 			public void mouseClicked(MouseEvent e) {
 				if(main_table.getSelectedRow() != -1) {
 					btn_OK.setEnabled(true);
 				}
 				int row = main_table.getSelectedRow();
 				String sno = main_table.getValueAt(row, 0).toString();
 				String name = main_table.getValueAt(row, 1).toString();
 				String id = main_table.getValueAt(row, 2).toString();
 				String indate = main_table.getValueAt(row, 5).toString();
 				tf_sno.setText(sno);
 				tf_name.setText(name);
 				tf_id.setText(id);
 				tf_indate.setText(indate);
 			}
 		});
 		
 		btn_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//先清空数据，防止数据混乱
				if(recordsList != null) {
					recordsList.clear();
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String date = formatter.format(new Date())+"%";  
 				recordsList = recordsBiz.queryRecordsByDidIndate(doctor.getId(), date);
 				refreshTable(recordsList);
 				if(recordsList.size() == 0) {
					JOptionPane.showInternalMessageDialog(
							DoctorOperatorView.this, "今天没有挂号的病人!");
					return;
				}
			}
 			
 		});
 		
 		btn_OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sno = tf_sno.getText().trim();
 				String name = tf_name.getText().trim();
 				String id = tf_id.getText().trim();
 				String disease = tf_disease.getText().trim();
 				String indate = tf_indate.getText().trim();
 				String drug = ta_0.getText().trim();
 				String advice = ta_1.getText().trim();
 				String  bed = ta_2.getText().trim();
 				String drug_sno = "";
 				String arr[] = drug.split(" ");
 				for(int i=0;i<arr.length;i++) {
 					drug_sno += medicineBiz.queryMedicionByDrug_name(arr[i]).getDrug_sno();
 					if(i != arr.length-1) {
 						drug_sno += " ";
 					}
 				}
 				
				if(sno.equals("")||name.equals("")||id.equals("")) {
					JOptionPane.showInternalMessageDialog(
							DoctorOperatorView.this, "病人信息不能为空!");
					return;
				}  
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String outdate = formatter.format(new Date());
				Records record = new Records(doctor.getId(),Integer.parseInt(sno),disease,drug_sno,doctor.getDepart(),bed,indate,outdate,advice);
				boolean flag = recordsBiz.updateRecord(record);
				if(flag) {
					JOptionPane.showInternalMessageDialog(
							DoctorOperatorView.this, "看病记录保存成功!");
					return;
				}else {
					JOptionPane.showInternalMessageDialog(
							DoctorOperatorView.this, "信息记录失败!");
					return;
				}
			}
 			
 		});
 		
 		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DoctorOperatorView.this.dispose();
			}
 			
 		});
 	}
 	
 	private class PatientinfoTableModel implements TableModel{
 		private List<Records> recordsList = null;
 		public PatientinfoTableModel(List<Records> recordsList) {
 			this.recordsList = recordsList;
 		}
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}
		@Override
		public int getColumnCount() {
			return 6;
		}
		@Override
		public String getColumnName(int columnIndex) {
			if(columnIndex==0) {
 				return "病号";
 			}else if(columnIndex==1) {
 				return "姓名";
 			}else if(columnIndex==2) {
 				return "身份证号";
 			}else if(columnIndex==3) {
 				return "年龄";
 			}else if(columnIndex==4) {
 				return "性别";
 			}else if(columnIndex==5) {
 				return "挂号日期";
 			}else{
 				return "出错";
 			}
		}
		@Override
		public int getRowCount() {
			return recordsList.size();
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Records record = recordsList.get(rowIndex);
			Patient patient = patientBiz.queryPatientBySno(record.getPsno());
			
 			if(columnIndex==0) {
 				return patient.getSno();
 				
 			}else if(columnIndex==1) {
 				return patient.getName();
 			}else if(columnIndex==2) {
 				return patient.getId();
 			}else if(columnIndex==3) {
 				return patient.getAge();
 			}else if(columnIndex==4) {
 				return patient.getSex();
 			}else if(columnIndex==5) {
 				return record.getIndate();
 			}else{
 				return "出错";
 			}
		}
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
		
		@Override
		public void setValueAt(Object arg0, int arg1, int arg2) {
			
		}
		@Override
		public void addTableModelListener(TableModelListener arg0) {
		
		}
		@Override
		public void removeTableModelListener(TableModelListener arg0) {
			
		}
		
 	}
 	//刷新JTable并显示数据
 	private void refreshTable(List<Records> recordsList) {
 		infoTableModel = new PatientinfoTableModel(recordsList);
 		main_table.setModel(infoTableModel);
 		
 	}
 	
}
