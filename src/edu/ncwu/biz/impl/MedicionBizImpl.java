package edu.ncwu.biz.impl;

import java.util.List;

import edu.ncwu.biz.MedicionBiz;
import edu.ncwu.data.Medicine;
import edu.ncwu.inter.MedicineInter;
import edu.ncwu.inter.impl.MedicionInterImpl;

public class MedicionBizImpl implements MedicionBiz {
	private MedicineInter medicineInter = null;
	public MedicionBizImpl() {
		medicineInter = new MedicionInterImpl();
	}
	@Override
	public Medicine queryMedicionByDrug_name(String drug_name) {
		return medicineInter.queryMedicionByDrug_name(drug_name);
	}

	@Override
	public Medicine queryMedicionByDrug_sno(int drug_sno) {
		return medicineInter.queryMedicionByDrug_sno(drug_sno);
	}
	@Override
	public boolean saveMedicine(Medicine medicine) {
		return medicineInter.saveMedicine(medicine);
	}
	@Override
	public boolean delMedicion(int drug_sno) {
		return medicineInter.delMedicion(drug_sno);
	}
	@Override
	public boolean updateMedicion(Medicine medicine) {
		return medicineInter.updateMedicion(medicine);
	}
	@Override
	public List<Medicine> queryAllMedicion() {
		return medicineInter.queryAllMedicion();
	}

}
