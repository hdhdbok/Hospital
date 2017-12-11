package edu.ncwu.biz.impl;

import java.util.List;

import edu.ncwu.biz.DoctorBiz;
import edu.ncwu.data.Doctor;
import edu.ncwu.inter.DoctorInter;
import edu.ncwu.inter.impl.DoctorInterImpl;

public class DoctorBizImpl implements DoctorBiz {
	private DoctorInter doctorInter = null;
	public DoctorBizImpl() {
		doctorInter = new DoctorInterImpl();
	}

	@Override
	public Doctor login(Doctor doctor) {
		return doctorInter.queryDoctor(doctor);
	}

	@Override
	public int registerUser(Doctor doctor) {
			boolean res = doctorInter.saveDoctor(doctor);
			if(res) {
				return 0;//×¢²á³É¹¦
			}else {
				return 1;//×¢²áÊ§°Ü
			}
	}
	
	@Override
	public boolean saveDoctor(Doctor doctor) {
		return doctorInter.saveDoctor(doctor);
	}
	 
	@Override
	public boolean delDoctor(String id) {
		return doctorInter.delDoctor(id);
	}
		
	@Override
	public boolean updateDoctor(Doctor doctor) {
		return doctorInter.updateDoctor(doctor);
	}
	
	@Override
	public Doctor queryDoctor(Doctor doctor) {
		return doctorInter.queryDoctor(doctor);
	}
		
	@Override
	public List<Doctor> queryDoctorByName(String name) {
		return doctorInter.queryDoctorByName(name);
	}
		
	@Override
	public Doctor queryDoctorById(String id) {
		return doctorInter.queryDoctorById(id);
	}
		
	@Override
	public List<Doctor> queryDoctorByDepart(String depart,int genre){
		return doctorInter.queryDoctorByDepart(depart, genre);
	}

	@Override
	public List<Doctor> queryAllDoctor() {
		
		return doctorInter.queryAllDoctor();
	}

	@Override
	public Doctor queryDoctorByNameDepart(String name, String depart) {
		return doctorInter.queryDoctorByNameDepart(name, depart);
	}

}
