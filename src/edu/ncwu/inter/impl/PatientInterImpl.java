package edu.ncwu.inter.impl;

import java.util.ArrayList;
import java.util.List;

import edu.ncwu.data.Patient;
import edu.ncwu.inter.PatientInter;

public class PatientInterImpl extends BaseImpl implements PatientInter {

	@Override
	public boolean savePatient(Patient patient) {
		String sql = "insert into patient(name,id,age,sex,phone) values(?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(patient.getName());
		params.add(patient.getId());
		params.add(patient.getAge());
		params.add(patient.getSex());
		params.add(patient.getPhone());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean delectPatient(String id) {
		String sql = "delect from patient where id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updatePatient(Patient patient) {
		String sql = "update patient set name  = ?,age = ?,sex = ?,phone = ? where id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(patient.getName());
		params.add(patient.getAge());
		params.add(patient.getSex());
		params.add(patient.getPhone());
		params.add(patient.getId());
		return this.operUpdate(sql, params);
	}
	@Override
	public Patient queryPatient(Patient patient) {
		String sql = "select * from patient where name = ? and id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(patient.getName());
		params.add(patient.getId());
		List<Patient> dList = null;
		try {
			dList = this.operQuery(sql, params, Patient.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dList.size()>0) {
			return dList.get(0);
		}
		return null;
	}
	
	@Override
	public Patient queryPatientBySno(int sno) {
		String sql = "select * from patient where sno = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(sno);
		List<Patient> dList = null;
		try {
			dList = this.operQuery(sql, params, Patient.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dList.size()>0) {
			return dList.get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public List<Patient> queryPatientByName(String name) {
		String sql = "select * from patient where name = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		List<Patient> dList = null;
		try {
			dList = this.operQuery(sql, params, Patient.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public Patient queryPatientById(String id) {
		String sql = "select sno,name,age,sex,phone from patient where id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Patient> dList = null;
		try {
			dList = this.operQuery(sql, params, Patient.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dList.size()>0) {
			return dList.get(0);
		}else {
			return null;
		}
		
	}

}
