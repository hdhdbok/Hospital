package edu.ncwu.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.ncwu.biz.PatientBiz;
import edu.ncwu.data.Doctor;
import edu.ncwu.data.Patient;
import edu.ncwu.data.Records;
import edu.ncwu.inter.DoctorInter;
import edu.ncwu.inter.PatientInter;
import edu.ncwu.inter.RecordsInter;
import edu.ncwu.inter.impl.DoctorInterImpl;
import edu.ncwu.inter.impl.PatientInterImpl;
import edu.ncwu.inter.impl.RecordsInterImpl;

public class PatientBizImpl implements PatientBiz {
	private PatientInter ptInter = null;
	private DoctorInter docInter = null;
	private RecordsInter rdInter = null;
	public PatientBizImpl() {
		ptInter = new PatientInterImpl();
		docInter = new DoctorInterImpl();
		rdInter = new RecordsInterImpl();
	}
	@Override
	public boolean savePatient(Patient patient) {
		return ptInter.savePatient(patient);
	}

	@Override
	public boolean delectPatient(String id) {
		return ptInter.delectPatient(id);
	}

	@Override
	public boolean updatePatient(Patient patient) {
		return ptInter.updatePatient(patient);
	}
	@Override
	public Patient queryPatient(Patient patient) {
		return ptInter.queryPatient(patient);
	}

	@Override
	public Patient queryPatientBySno(int sno) {
		return ptInter.queryPatientBySno(sno);
	}

	@Override
	public List<Patient> queryPatientByName(String name) {
		return ptInter.queryPatientByName(name);
	}

	@Override
	public Patient queryPatientById(String id) {
		return ptInter.queryPatientById(id);
	}

	@Override
	public int Appointment(int psno, String id) {
		Doctor doc = docInter.queryDoctorById(id);//查询是否有此医生
		if(doc == null) {
			return 0;//没有这个医生
		}else {
			Records rec = new Records(doc.getId(),psno,"","",doc.getDepart(),"",new SimpleDateFormat("yyyy-mm-dd").format(new Date()),"","");
			boolean flag = rdInter.saveRecord(rec);
			if(flag) {
				return 1;//挂号成功
			}else {
				return 2;//挂号失败
			}
		}
	}
	
	@Override
	public Patient login(Patient patient) {
		return ptInter.queryPatient(patient);
	}

	@Override
	public int registerUser(Patient patient) {
		if(ptInter.queryPatient(patient)!=null) {
			return 1;//用户名已存在
		}else {
			boolean flag = ptInter.savePatient(patient);
			if(flag) {
				return 2;//注册成功
			}else {
				return 3;//注册失败
			}
		}
	
	}
	
}
