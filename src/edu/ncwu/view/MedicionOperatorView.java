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
	private JPanel btn_panel = null;//��ť���
	private JPanel main_panel = null;//ʢװJTable���
	private JPanel bottom_panel = null;//�ײ��������
	private JTable main_table = null;//��ʾ��Ϣ��
	
	private JComboBox<String> cb_genre = null;//��ѯ���������б��
	private JTextField tf_input = null;//�����
	private JButton btn_query = null;//��ѯ��ť
	private JButton btn_add = null;//��Ӱ�ť
	private JButton btn_update = null;//���°�ť
	private JButton btn_del = null;//ɾ����ť
	private JButton btn_exit = null;//�˳�����
	
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
		this.setTitle("ҩƷ����ϵͳ");
		this.setSize(800, 500);
		this.setVisible(true);
		this.setResizable(false);
		this.setIconifiable(true);
 		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		btn_panel = new JPanel(new GridLayout(8,1,0,10));
		Border bbutton = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null,null),"���ѡ��");
		btn_panel.setBorder(bbutton);
		cb_genre = new JComboBox<String>(new String[] {"ȫ��ҩƷ","ҩƷ���","ҩƷ����"});
		cb_genre.setFont(new Font("����",Font.BOLD,18));
		tf_input = new JTextField();
		tf_input.setEditable(false);
		tf_input.setFont(new Font("����",Font.BOLD,18));
		btn_query = new JButton("��ѯ");
		btn_query.setFont(new Font("����",Font.BOLD,18));
		btn_add = new JButton("���");
		btn_add.setFont(new Font("����",Font.BOLD,18));
		btn_update = new JButton("����");
		btn_update.setEnabled(false);
		btn_update.setFont(new Font("����",Font.BOLD,18));
		btn_del = new JButton("ɾ��");
		btn_del.setEnabled(false);
		btn_del.setFont(new Font("����",Font.BOLD,18));
		btn_exit = new JButton("�˳�����");
		btn_exit.setFont(new Font("����",Font.BOLD,18));
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
		
		//��JLabel������ģ�ͳ�������
		refreshTable(medicineList);
		
		main_panel = new JPanel(new BorderLayout());
		Border btable = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null,null),"ҩƷ��Ϣ");
		main_panel.setBorder(btable);
		main_panel.add(new JScrollPane(main_table), BorderLayout.CENTER);
		
		
		bottom_panel = new JPanel(new GridLayout(2,5));
		lb_sno = new JLabel("ҩƷ���");
		lb_sno.setFont(new Font("����",Font.BOLD,15));
		lb_name = new JLabel("ҩƷ����");
		lb_name.setFont(new Font("����",Font.BOLD,15));
		lb_count = new JLabel("ҩƷ����");
		lb_count.setFont(new Font("����",Font.BOLD,15));
		lb_price = new JLabel("ҩƷ�۸�");
		lb_price.setFont(new Font("����",Font.BOLD,15));
		lb_place = new JLabel("���λ��");
		lb_place.setFont(new Font("����",Font.BOLD,15));
		tf_sno = new JTextField();
		tf_sno.setFont(new Font("����",Font.BOLD,15));
		tf_name = new JTextField();
		tf_name.setFont(new Font("����",Font.BOLD,15));
		tf_count = new JTextField();
		tf_count.setFont(new Font("����",Font.BOLD,15));
		tf_price = new JTextField();
		tf_price.setFont(new Font("����",Font.BOLD,15));
		tf_place = new JTextField();
		tf_place.setFont(new Font("����",Font.BOLD,15));
		
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
						MedicionOperatorView.this, "�Ƿ�ȷ������ҩƷ��","ȷ����Ϣ",
 						JOptionPane.YES_NO_OPTION);
 				if(flag==JOptionPane.YES_OPTION) {
 					boolean flag0 = medicineBiz.updateMedicion(new Medicine(Integer.parseInt(sno),name,Scount,new BigDecimal(price),place));
 					if(flag0) {
						JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "���³ɹ�!");
					}else {
						JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "����ʧ��!");
					}
 				}
			}
		});
		
		btn_del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sno = new Integer(tf_sno.getText());
				int flag = JOptionPane.showInternalConfirmDialog(
						MedicionOperatorView.this, "�Ƿ�ȷ��ɾ��ҩƷ��","ȷ����Ϣ",
 						JOptionPane.YES_NO_OPTION);
 				if(flag==JOptionPane.YES_OPTION) {
 					boolean flag0 = medicineBiz.delMedicion(sno);
 					if(flag0) {
						JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "ɾ���ɹ�!");
					}else {
						JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "ɾ��ʧ��!");
					}
 				}
			}
		});
		
		main_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//ѡ��һ�к󣬸��º�ɾ����ť����
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
					JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "ҩƷ��Ϣ����Ϊ��!");
					return;
				}
				int flag = JOptionPane.showInternalConfirmDialog(
						MedicionOperatorView.this, "�Ƿ�ȷ�����ҩƷ��","ȷ����Ϣ",
 						JOptionPane.YES_NO_OPTION);
 				if(flag==JOptionPane.YES_OPTION) {
 					Medicine medicine = medicineBiz.queryMedicionByDrug_name(name);
 					
 					if(medicine != null) {
 						
 	 					int Scount = medicine.getNumber()+new Integer(count);
 						int flag0 = JOptionPane.showInternalConfirmDialog(
 								MedicionOperatorView.this, "��¼���Ѿ����ڴ�ҩ���Ƿ���£�","ȷ����Ϣ",
 		 						JOptionPane.YES_NO_OPTION);
 		 				if(flag0==JOptionPane.YES_OPTION) {
 		 					boolean flag1 = medicineBiz.updateMedicion(new Medicine(Integer.parseInt(sno),name,Scount,new BigDecimal(price),place));
 							if(flag1) {
 								JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "���³ɹ�!");
 							}else {
 								JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "����ʧ��!");
 							}
 							return;
 		 				}
 					}
 					System.out.println(sno+','+name+','+count+','+price+','+place);
 					boolean flag1 = medicineBiz.saveMedicine(new Medicine(Integer.parseInt(sno),name,new Integer(count),new BigDecimal(price),place));
						if(flag1) {
							JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "��ӳɹ�!");
						}else {
							JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "���ʧ��!");
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
					JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "��ѯ���ݲ���Ϊ��");
					return;
				}
				if(index == 0) {
					medicineList = medicineBiz.queryAllMedicion();
				}else if(index == 1) {
					if(!HospitalUtil.isNumber(content)) {
						JOptionPane.showInternalMessageDialog(MedicionOperatorView.this, "ҩƷ���ֻ�����������!");
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
				if(item.equals("ȫ��ҩƷ")) {
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
 		
 		//JTable��ʾ����������
 		//  API   ���ظ�ģ���е�������
 		@Override
 		public int getRowCount() {
 			return medicineList.size();
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
 				return "ҩƷ���";
 			}else if(columnIndex==1) {
 				return "ҩƷ����";
 			}else if(columnIndex==2) {
 				return "�������";
 			}else if(columnIndex==3) {
 				return "ҩƷ�۸�";
 			}else if(columnIndex==4) {
 				return "���λ��";
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
	private void refreshTable(List<Medicine> medicineList) {
		infoTableModel = new MedicineinfoTableModel(medicineList);
		main_table.setModel(infoTableModel);
	}
	
	
}
