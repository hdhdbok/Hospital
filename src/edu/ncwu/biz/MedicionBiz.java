package edu.ncwu.biz;

import java.util.List;

import edu.ncwu.data.Medicine;

public interface MedicionBiz {
	
	public boolean saveMedicine(Medicine medicine);//����ҩƷ��Ϣ
	
	public boolean delMedicion(int drug_sno);//ɾ��ҩƷ��Ϣ
	
	public boolean updateMedicion(Medicine medicine);//����ҩƷ��Ϣ
	
	public Medicine queryMedicionByDrug_name(String drug_name);//��ҩ����ѯ
	
	public Medicine queryMedicionByDrug_sno(int drug_sno);//��ҩƷ��Ų�ѯ
	
	public List<Medicine> queryAllMedicion();//��ѯ���е�ҩƷ
}
