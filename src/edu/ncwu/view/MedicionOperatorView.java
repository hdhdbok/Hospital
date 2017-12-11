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
import java.math.BigDecimal;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import edu.ncwu.biz.MedicionBiz;
import edu.ncwu.biz.impl.MedicionBizImpl;
import edu.ncwu.data.Medicine;
import edu.ncwu.util.HospitalUtil;

public class MedicionOperatorView extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JPanel btn_panel = null;//按钮面板
	private JPanel main_panel = null;//盛装JTable面板
	private JPanel bottom_panel = null;//底部输入面板
	private JTable main_table = null;//显示信息表
	
	private JComboBox<String> cb_genre = null;//查询条件下拉列表框
	private JTextField tf_input = null;//输入框
	private JButton btn_query = null;//查询按钮
	private JButton btn_add = null;//添加按钮
	private JButton btn_update = null;//更新按钮
	private JButton btn_del = null;//删除按钮
	private JButton btn_exit = null;//退出窗口
	
	private JLabel lb_sno = null;
	private JLabel lb_name = null;
	private JLabel lb_count = null;
	private JLabel lb_price = null;
	private JLabel lb_place = null;
	private JTextField tf_sno = null;
	private JTextField tf_name = null;
	private JTextField tf_count = null;
	private JTextField tf_price = null;
	private JTextField tf_place = null;
	
	private MedicineinfoTableModel infoTableModel = null;
	private List<Medicine> medicineList =null;
	private MedicionBiz medicineBiz = null; 
	public MedicionOperatorView(){
		medicineBiz = new MedicionBizImpl();
		init();
		registerListener();
	}
	public void init(){
		this.setTitle("药品管理系统");
		this.setSize(800, 500);
		this.setVisible(true);
		this.setResizable(false);
		this.setIconifiable(true);
 		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		btn_panel = new JPanel(new GridLayout(8,1,0,10));
		Border bbutton = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null,null),"快捷选项");
		btn_panel.setBorder(bbutton);
		cb_genre = new JComboBox<String>(new String[] {"全部药品","药品编号","药品名称"});
		cb_genre.setFont(new Font("宋体",Font.BOLD,18));
		tf_input = new JTextField();
		tf_input.setEditable(false);
		tf_input.setFont(new Font("宋体",Font.BOLD,18));
		btn_query = new JButton("查询");
		btn_query.setFont(new Font("宋体",Font.BOLD,18));
		btn_add = new JButton("添加");
		btn_add.setFont(new Font("宋体",Font.BOLD,18));
		btn_update = new JButton("更新");
		btn_update.setEnabled(false);
		btn_update.setFont(new Font("宋体",Font.BOLD,18));
		btn_del = new JButton("删除");
		btn_del.setEnabled(false);
		btn_del.setFont(new Font("宋体",Font.BOLD,18));
		btn_exit = new JButton("退出窗口");
		btn_exit.setFont(new Font("宋体",Font.BOLD,18));
		btn_panel.add(new JLabel());
		btn_panel.add(cb_genre);
		btn_panel.add(tf_input);
		btn_panel.add(btn_query);
		btn_panel.add(btn_add);
		btn_panel.add(btn_del);
		btn_panel.add(btn_update);
		btn_panel.add(btn_exit);
		this.add(btn_panel, BorderLayout.EAST);
		
		medicineList = new ArrayList<Medicine>();
		
		main_table = new JTable();
		
		//让JLabel绑定数据模型呈现数据
		refreshTable(medicineList);
		
		main_panel = new JPanel(new BorderLayout());
		Border btable = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null,null),"药品信息");
		main_panel.setBorder(btable);
		main_panel.add(new JScrollPane(main_table), BorderLayout.CENTER);
		
		
		bottom_panel = new JPanel(new GridLayout(2,5));
		lb_sno = new JLabel("药品编号");
		lb_sno.setFont(new Font("宋体",Font.BOLD,15));
		lb_name = new JLabel("药品名称");
		lb_name.setFont(new Font("宋体",Font.BOLD,15));
		lb_count = new JLabel("药品数量");
		lb_count.setFont(new Font("宋体",Font.BOLD,15));
		lb_price = new JLabel("药品价格");
		lb_price.setFont(new Font("宋体",Font.BOLD,15));
		lb_place = new JLabel("存放位置");
		lb_place.setFont(new Font("宋体",Font.BOLD,15));
		tf_sno = new JTextField();
		tf_sno.setFont(new Font("宋体",Font.BOLD,15));
		tf_name = new JTextField();
		tf_name.setFont(new Font("宋体",Font.BOLD,15));
		tf_count = new JTextField();
		tf_count.setFont(new Font("宋体",Font.BOLD,15));
		tf_price = new JTextField();
		tf_price.setFont(new Font("宋体",Font.BOLD,15));
		tf_place = new JTextField();
		tf_place.setFont(new Font("宋体",Font.BOLD,15));
		
		bottom_panel.add(lb_sno);
		bottom_panel.add(lb_name);
		bottom_panel.add(lb_count);
		bottom_panel.add(lb_price);
		bottom_panel.add(lb_place);
		bottom_panel.add(tf_sno);
		bottom_panel.add(tf_name);
		bottom_panel.add(tf_count);
		bottom_panel.add(tf_price);
		bottom_panel.add(tf_place);	
		main_panel.add(bottom_panel, BorderLayout.SOUTH);
		this.add(main_panel, BorderLayout.CENTER);
	}
	
	public void registerListener(){
		
		btn_update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sno = tf_sno.getText();
				String name = tf_name.getText();
				String count = tf_count.getText();
				String price = tf_price.getText();
				String place = tf_place.getText();
				Medicine medicine = medicineBiz.queryMedicionByDrug_name(name);
					int Scount = medicine.getNumber()+new Integer(count);
				int flag = JOptionPane.showInternalConfirmDialog(
						MedicionOperatorView.this, "是否确定更新药品？","确认信息",
 						JOptionPane.YES_NO_OPTION);
 				if(flag==JOptionPane.YES_OPTION) {
 					boolean flag0 = medicineBiz.updateMedicion(new Medicine(Integer.parseInt(sno),name,Scount,new BigDecimal(price),place));
 					if(flag0) {
						JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "更新成功!");
					}else {
						JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "更新失败!");
					}
 				}
			}
		});
		
		btn_del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sno = new Integer(tf_sno.getText());
				int flag = JOptionPane.showInternalConfirmDialog(
						MedicionOperatorView.this, "是否确定删除药品？","确认信息",
 						JOptionPane.YES_NO_OPTION);
 				if(flag==JOptionPane.YES_OPTION) {
 					boolean flag0 = medicineBiz.delMedicion(sno);
 					if(flag0) {
						JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "删除成功!");
					}else {
						JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "删除失败!");
					}
 				}
			}
		});
		
		main_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//选中一行后，更新和删除按钮可用
				if(main_table.getSelectedRow() != -1) {
					btn_update.setEnabled(true);
					btn_del.setEnabled(true);
				}
				int row = main_table.getSelectedRow();
				String sno = main_table.getValueAt(row, 0).toString();
				String name = main_table.getValueAt(row, 1).toString();
				String count = main_table.getValueAt(row, 2).toString();
				String price = main_table.getValueAt(row, 3).toString();
				String place = main_table.getValueAt(row, 4).toString();
				tf_sno.setText(sno);
				tf_name.setText(name);
				tf_count.setText(count);
				tf_price.setText(price);
				tf_place.setText(place);
			}
		});
	
		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sno = tf_sno.getText().trim(); 
				String name = tf_name.getText().trim();
				String count = tf_count.getText().trim();
				String price = tf_price.getText().trim();
				String place = tf_place.getText().trim();
				
				if(sno.equals("")||name.equals("")||count.equals("")||price.equals("")||place.equals("")) {
					JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "药品信息不能为空!");
					return;
				}
				int flag = JOptionPane.showInternalConfirmDialog(
						MedicionOperatorView.this, "是否确定添加药品？","确认信息",
 						JOptionPane.YES_NO_OPTION);
 				if(flag==JOptionPane.YES_OPTION) {
 					Medicine medicine = medicineBiz.queryMedicionByDrug_name(name);
 					
 					if(medicine != null) {
 						
 	 					int Scount = medicine.getNumber()+new Integer(count);
 						int flag0 = JOptionPane.showInternalConfirmDialog(
 								MedicionOperatorView.this, "记录中已经存在此药，是否更新？","确认信息",
 		 						JOptionPane.YES_NO_OPTION);
 		 				if(flag0==JOptionPane.YES_OPTION) {
 		 					boolean flag1 = medicineBiz.updateMedicion(new Medicine(Integer.parseInt(sno),name,Scount,new BigDecimal(price),place));
 							if(flag1) {
 								JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "更新成功!");
 							}else {
 								JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "更新失败!");
 							}
 							return;
 		 				}
 					}
 					System.out.println(sno+','+name+','+count+','+price+','+place);
 					boolean flag1 = medicineBiz.saveMedicine(new Medicine(Integer.parseInt(sno),name,new Integer(count),new BigDecimal(price),place));
						if(flag1) {
							JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "添加成功!");
						}else {
							JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "添加失败!");
						}
 				}
			}
		});
		
		btn_query.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = cb_genre.getSelectedIndex();
				String content = tf_input.getText().trim();
				if(medicineList != null) {
					medicineList.clear();
				}
				if(index != 0 && content.equals("")) {
					JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "查询内容不能为空");
					return;
				}
				if(index == 0) {
					medicineList = medicineBiz.queryAllMedicion();
				}else if(index == 1) {
					if(!HospitalUtil.isNumber(content)) {
						JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "药品编号只能由数字组成!");
						return;
					}else {
						//int sno = new Integer(content);
						int sno = Integer.parseInt(content);
						Medicine med = medicineBiz.queryMedicionByDrug_sno(sno);
						medicineList.add(med);
					}
					
				}else if(index==2) {
					medicineList.add( medicineBiz.queryMedicionByDrug_name(content));
				}
				refreshTable(medicineList);
				
			}
		});
		
		cb_genre.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String item = e.getItem().toString();
				tf_input.setText("");
				if(item.equals("全部药品")) {
					tf_input.setEditable(false);
				}else {
					tf_input.setEditable(true);
				}
			}
		});
		
		
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicionOperatorView.this.dispose();
			}
		});
		
	/*	btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	*/
	}
	
	
	private class MedicineinfoTableModel implements TableModel{
 		private List<Medicine> medicineList = null;
 		public MedicineinfoTableModel(List<Medicine> medicineList) {
 			this.medicineList = medicineList;
 		}
 		
 		//JTable显示的数据行数
 		//  API   返回该模型中的行数。
 		@Override
 		public int getRowCount() {
 			return medicineList.size();
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
 				return "药品编号";
 			}else if(columnIndex==1) {
 				return "药品名称";
 			}else if(columnIndex==2) {
 				return "库存数量";
 			}else if(columnIndex==3) {
 				return "药品价格";
 			}else if(columnIndex==4) {
 				return "存放位置";
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
 			Medicine medicine = medicineList.get(rowIndex);
 			if(columnIndex==0) {
 				return medicine.getDrug_sno();
 			}else if(columnIndex==1) {
 				return medicine.getDrug_name();
 			}else if(columnIndex==2) {
 				return medicine.getNumber();
 			}else if(columnIndex==3) {
 				return medicine.getPrice();
 			}else  if(columnIndex==4) {
 				return medicine.getDrug_place();
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
	private void refreshTable(List<Medicine> medicineList) {
		infoTableModel = new MedicineinfoTableModel(medicineList);
		main_table.setModel(infoTableModel);
	}
	
	
}
