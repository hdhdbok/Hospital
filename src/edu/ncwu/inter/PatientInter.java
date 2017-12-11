package edu.ncwu.inter;

import java.util.List;

import edu.ncwu.data.Patient;

public interface PatientInter {
	public boolean savePatient(Patient patient);//新增病人信息
	
	public boolean delectPatient(String id);//删除病人信息
	
	public boolean updatePatient(Patient patient);//更新病人信息
	
	public Patient queryPatient(Patient patient);//查找病人
	
	public Patient queryPatientBySno(int sno);//按照病人编号查找
	
	public List<Patient> queryPatientByName(String name);//按照姓名查询病人信息
	
	public Patient queryPatientById(String id);//按照身份证号查询病人信息	
	
}