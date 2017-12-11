package edu.ncwu.inter.impl;

import java.util.ArrayList;
import java.util.List;

import edu.ncwu.data.Medicine;
import edu.ncwu.inter.MedicineInter;

public class MedicionInterImpl extends BaseImpl implements MedicineInter {

	@Override
	public boolean saveMedicine(Medicine medicine) {
		String sql = "insert into medicine(drug_sno,drug_name,number,price,drug_place) values(?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(medicine.getDrug_sno());
		params.add(medicine.getDrug_name());
		params.add(medicine.getNumber());
		params.add(medicine.getPrice());
		params.add(medicine.getDrug_place());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean delMedicion(int drug_sno) {
		String sql = "delete from medicine where drug_sno = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(drug_sno);
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateMedicion(Medicine medicine) {
		String sql = "update medicine set drug_name = ?,number = ?,price = ?,drug_place = ? where drug_sno = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(medicine.getDrug_name());
		params.add(medicine.getNumber());
		params.add(medicine.getPrice());
		params.add(medicine.getDrug_place());
		params.add(medicine.getDrug_sno());
		return this.operUpdate(sql, params);
	}

	@Override
	public Medicine queryMedicionByDrug_name(String drug_name) {
		String sql = "select drug_sno,number,price,drug_place from medicine where drug_name = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(drug_name);
		List<Medicine> dList = null;
		try {
			dList = this.operQuery(sql, params, Medicine.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dList.size() > 0) {
			return dList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Medicine queryMedicionByDrug_sno(int drug_sno) {
		String sql = "select drug_name,number,price,drug_place from medicine where drug_sno = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(drug_sno);
		List<Medicine> dList = null;
		try {
			dList = this.operQuery(sql, params, Medicine.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dList.size()>0) {
			return dList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Medicine> queryAllMedicion() {
		String sql = "select * from medicine";
		List<Object> params = new ArrayList<Object>();
		List<Medicine> dList = null;
		try {
			dList = this.operQuery(sql, params, Medicine.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

}
