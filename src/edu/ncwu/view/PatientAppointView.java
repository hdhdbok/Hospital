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
	private JPanel btn_panel = null;//按钮面板
	private JTable main_table = null;//查询结果表格
	private JPanel main_panel = null;//用来保存main_table
	private JPanel bottom_panel = null;//确认信息面板
	private JPanel panel_0 = null;
	private JPanel panel_1 = null;
	private JPanel panel_2 = null;
	private JPanel panel_3 = null;
	private JPanel panel_4  = null;
	
	private JComboBox<String> cb_way = null;//查询方式下拉列表框
	private JTextField tf_input = null;//输入要查询的医生姓名
	private JComboBox<String> cb_depart = null;//科室下拉列表框
	private JButton btn_search = null;//查询按钮
	private JButton btn_OK = null;//确定挂号按钮
	private JButton btn_exit = null;//退出按钮
	
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
		this.setTitle("病人操作界面");
 		this.setSize(800, 500);
 		this.setIconifiable(true);
 		this.setClosable(true);
 		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 		this.setLayout(new BorderLayout());
 		this.setVisible(true);
 		
 		this.setLayout(new BorderLayout());
 		
 		btn_panel = new JPanel(new GridLayout(7,1,0,10));
 		Border bbutton = BorderFactory.createTitledBorder(
 				BorderFactory.createEtchedBorder(null, null), "查询条件");
 		btn_panel.setBorder(bbutton);
 		
 		cb_way = new JComboBox<String>(new String[] {"全部医生","医生姓名","科室"});
 		cb_way.setFont(new Font("宋体",Font.BOLD,18));
 		tf_input = new JTextField(10);
 		tf_input.setEditable(false);
 		tf_input.setFont(new Font("宋体",Font.BOLD,18));
 		cb_depart = new JComboBox<String>(new String[] {"外科","内科","皮肤科","儿科","骨科","耳鼻喉科","心脑科","神经科"});
 		cb_depart.setFont(new Font("宋体",Font.BOLD,18));
 		btn_search = new JButton("查询");
 		btn_search.setFont(new Font("宋体",Font.BOLD,18));
 		btn_OK = new JButton("确定挂号");
 		btn_OK.setEnabled(false);
 		btn_OK.setFont(new Font("宋体",Font.BOLD,18));
 		btn_exit = new JButton("退出窗口");
 		btn_exit.setFont(new Font("宋体",Font.BOLD,18));
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
 				BorderFactory.createEtchedBorder(null,null),"医生信息表");
 		main_panel.setBorder(btable);
 		main_panel.add(new JScrollPane(main_table), BorderLayout.CENTER);
 		
 		lb_name = new JLabel("姓名",JLabel.CENTER);
 		lb_name.setFont(new Font("宋体",Font.BOLD,15));
 		lb_sex = new JLabel("性别",JLabel.CENTER);
 		lb_sex.setFont(new Font("宋体",Font.BOLD,15));
 		lb_age = new JLabel("年龄",JLabel.CENTER);
 		lb_age.setFont(new Font("宋体",Font.BOLD,15));
 		lb_depart = new JLabel("科室",JLabel.CENTER);
 		lb_depart.setFont(new Font("宋体",Font.BOLD,15));
 		lb_date = new JLabel("日期",JLabel.CENTER);
 		lb_date.setFont(new Font("宋体",Font.BOLD,15));
 		
 		tf_name = new JTextField();
 		tf_name.setFont(new Font("宋体",Font.BOLD,15));
 		tf_sex = new JTextField();
 		tf_sex.setFont(new Font("宋体",Font.BOLD,15));
 		tf_age = new JTextField();
 		tf_age.setFont(new Font("宋体",Font.BOLD,15));
 		tf_depart = new JTextField();
 		tf_depart.setFont(new Font("宋体",Font.BOLD,15));
 		tf_date = new JTextField();
 		tf_date.setFont(new Font("宋体",Font.BOLD,15));
 		
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
		
		cb_way.addItemListener(new ItemListener() {//下拉列表框监视事件
 			@Override
 			public void itemStateChanged(ItemEvent e) {
 				String item = e.getItem().toString();
 				tf_input.setText("");//先清空输入框中的内容
 				if(item.equals("医生姓名")) {
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
					JOptionPane.showInternalMessageDialog(PatientAppointView.this, "挂号成功!");
				}else {
					JOptionPane.showInternalMessageDialog(PatientAppointView.this, "挂号失败!");
				}
			}
		});
		
		main_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//选中一行后，挂号按钮可用
				if(main_table.getSelectedRow() != -1) {
					btn_OK.setEnabled(true);
				}
				int row = main_table.getSelectedRow();//得到所选中的行的下标
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
				String depart[] = {"外科","内科","皮肤科","儿科","骨科","耳鼻喉科","心脑科","神经科"};
				int index_way = cb_way.getSelectedIndex();
				String content = tf_input.getText().trim();
				int index_depart = cb_depart.getSelectedIndex();
				if(index_way == 1 && content.equals("")) {
					JOptionPane.showInternalMessageDialog(
							PatientAppointView.this, "查询内容不能为空!");
					return;
				}
				//先清除数据，防止数据累加
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
							PatientAppointView.this, "没有你要查询的医生!");
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
 		
 		//JTable显示的数据行数
 		//  API   返回该模型中的行数。
 		@Override
 		public int getRowCount() {
 			return doctorList.size();
 		}
 		
 		//JTable显示的数据列数
 		//  API  返回该模型中的列数。
 		@Override
 		public int getColumnCount() {
 			return 5;
 		}
 		
 		//设置JTable显示的列名
 		//  API   返回 columnIndex 位置的列的名称。
 		@Override
 		public String getColumnName(int columnIndex) {
 			if(columnIndex==0) {
 				return "姓名";
 			}else if(columnIndex==1) {
 				return "性别";
 			}else if(columnIndex==2) {
 				return "年龄";
 			}else if(columnIndex==3) {
 				return "科室";
 			}else if(columnIndex==4) {
 				return "手机号码";
 			}else{
 				return "出错";
 			}
 		}
 		
 		//JTable列的数据类型
 		//  API  针对列中所有的单元格值，返回最具体的超类。
 		@Override
 		public Class<?> getColumnClass(int columnIndex) {
 			return String.class;
 		}
 		
 		//设置单元格是否可编辑
 		//  API  如果 rowIndex 和 columnIndex 位置的单元格是可编辑的，则返回 true。
 		@Override
 		public boolean isCellEditable(int rowIndex,int columnIndex) {
 			return false;
 		}
 		
 		//获取JTable中指定行指定单元的数据
 		//  API  返回 columnIndex 和 rowIndex 位置的单元格值。
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
 				return "出错";
 			}
 		}
 		
 		//  API  如果设置单元格可编辑，当单元格内容变化时就会调用此方法
 		@Override
 		public void setValueAt(Object aValue, int rowIndex,int columnIndex ) {
 			
 		}
 		
 		//  API  每当数据模型发生更改时，就将一个侦听器添加到被通知的列表中。
 		@Override
 		public void addTableModelListener(TableModelListener l) {
 			
 		}
 		
 		//  API  每当数据模型发生更改时，就从被通知的列表中移除一个侦听器。
 		@Override
 		public void removeTableModelListener(TableModelListener l) {
 			
 		}
 		
 		
 	}
 	
 	//刷新JTabel并显示数据
	private void refreshTable(List<Doctor> doctorList) {
		infoTableModel = new DoctorinfoTableModel(doctorList);
		main_table.setModel(infoTableModel);
	}
}
