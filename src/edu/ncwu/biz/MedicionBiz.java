package edu.ncwu.biz;

import java.util.List;

import edu.ncwu.data.Medicine;

public interface MedicionBiz {
	
	public boolean saveMedicine(Medicine medicine);//新增药品信息
	
	public boolean delMedicion(int drug_sno);//删除药品信息
	
	public boolean updateMedicion(Medicine medicine);//更新药品信息
	
	public Medicine queryMedicionByDrug_name(String drug_name);//按药名查询
	
	public Medicine queryMedicionByDrug_sno(int drug_sno);//按药品编号查询
	
	public List<Medicine> queryAllMedicion();//查询所有的药品
}
