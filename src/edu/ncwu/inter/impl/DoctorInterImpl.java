package edu.ncwu.inter.impl;

import java.util.ArrayList;
import java.util.List;

import edu.ncwu.data.Doctor;
import edu.ncwu.inter.DoctorInter;

public class DoctorInterImpl extends BaseImpl implements DoctorInter {

	@Override
	public boolean saveDoctor(Doctor doctor) {
		String sql = "insert into doctor(name,id,age,sex,pass,phone,genre,depart) values (?,?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(doctor.getName());
		params.add(doctor.getId());
		params.add(doctor.getAge());
		params.add(doctor.getSex());
		params.add(doctor.getPass());
		params.add(doctor.getPhone());
		params.add(doctor.getGenre());
		params.add(doctor.getDepart());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean delDoctor(String id) {
		String sql = "delet from doctor where id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateDoctor(Doctor doctor) {
		String sql = "update dvds set name = ?,age = ?,sex = ?,pass = ?,phone = ?,genre = ?,depart = ? where id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(doctor.getName());
		params.add(doctor.getAge());
		params.add(doctor.getSex());
		params.add(doctor.getPass());
		params.add(doctor.getPhone());
		params.add(doctor.getGenre());
		params.add(doctor.getDepart());
		params.add(doctor.getId());
		return this.operUpdate(sql, params);
	}
	
	@Override
	public Doctor queryDoctor(Doctor doctor) {
		List<Doctor> dList = null;
		String sql = "select name,id,age,sex,pass,phone,genre,depart from doctor where name = ? and pass = ? and genre = ?";
		
		List<Object> params = new ArrayList<Object>();
		params.add(doctor.getName());
		params.add(doctor.getPass());
		params.add(doctor.getGenre());
		try {
			dList = this.operQuery(sql, params, Doctor.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dList.size()>0) {
			return dList.get(0);
		}
		return null;
	}
	@Override
	public List<Doctor> queryDoctorByName(String name) {
		String sql = "select name,age,sex,depart from doctor where name = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		List<Doctor> dList = null;
		try {
			dList = this.operQuery(sql, params, Doctor.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dList;
		
		
	}

	@Override
	public Doctor queryDoctorById(String id) {
		String sql = "select name,age,sex,depart from doctor where id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Doctor> dList = null;
		try {
			dList = this.operQuery(sql, params, Doctor.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dList.size()>0) {
			return dList.get(0);
		}
		return null;
	}

	@Override
	public List<Doctor> queryDoctorByDepart(String depart, int genre) {
		String sql = "select name,age,sex,depart from doctor where depart = ? and genre = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(depart);
		params.add(genre);
		List<Doctor> dList = null;
		try {
			dList = this.operQuery(sql, params, Doctor.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public List<Doctor> queryAllDoctor() {
		String sql = "select * from doctor where genre = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(0);
		List<Doctor> dList = null;
		try {
			dList = this.operQuery(sql, params, Doctor.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public Doctor queryDoctorByNameDepart(String name, String depart) {
		String sql = "select * from doctor where name = ? and depart = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(name);
		params.add(depart);
		List<Doctor> dList = null;
		try {
			dList = this.operQuery(sql, params, Doctor.class);
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
