package edu.ncwu.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import edu.ncwu.biz.PatientBiz;
import edu.ncwu.biz.RecordsBiz;
import edu.ncwu.biz.impl.PatientBizImpl;
import edu.ncwu.biz.impl.RecordsBizImpl;
import edu.ncwu.data.Doctor;
import edu.ncwu.data.Patient;
import edu.ncwu.data.Records;

public class DoctorQueryView extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel btn_panel = null;
	private JPanel main_panel = null;
	private JTable main_table =null;
	private JButton btn_query = null;
	private JButton btn_exit = null;
	
	private RecordsBiz recordsBiz = null;
	private PatientBiz patientBiz = null;
	private List<Records> recordsList = null;
	private RecordsinfoTableModel infoTableModel = null;
	public DoctorQueryView(Doctor doctor) {
		recordsBiz = new RecordsBizImpl();
		patientBiz = new PatientBizImpl();
		init();
		registerListener(doctor);
	}
	
	public void init() {
		this.setTitle("看病记录查询");
		this.setSize(800, 500);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		btn_query = new JButton("查询记录");
		btn_query.setFont(new Font("宋体",Font.BOLD,20));
		btn_exit = new JButton("退出窗口");
		btn_exit.setFont(new Font("宋体",Font.BOLD,20));
		btn_panel = new JPanel(new GridLayout(6,1,0,15));
		btn_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"快捷功能区"));
		btn_panel.add(new JLabel());
		btn_panel.add(btn_query);
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.add(new JLabel());
		btn_panel.add(btn_exit);
		this.add(btn_panel, BorderLayout.EAST);
		
		main_table = new JTable();
		recordsList = new ArrayList<Records>();
		
		main_panel = new JPanel(new BorderLayout());
		main_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(null, null),"记录查询结果"));
		main_panel.add(new JScrollPane(main_table), BorderLayout.CENTER);
		this.add(main_panel, BorderLayout.CENTER);
		
	}
	
	public void registerListener(Doctor doctor) {
		
		btn_query.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(recordsList != null) {
					recordsList.clear();
				}
				recordsList = recordsBiz.queryRecordsByDid(doctor.getId());
				refreshTable(recordsList);
			}
			
		});
		
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DoctorQueryView.this.dispose();
			}
			
		});
		
	}
	
	private class RecordsinfoTableModel implements TableModel{
 		private List<Records> recordsList = null;
 		public RecordsinfoTableModel(List<Records> recordsList) {
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
 		infoTableModel = new RecordsinfoTableModel(recordsList);
 		main_table.setModel(infoTableModel);
 		
 	}
 	

}
