package edu.ncwu.inter;

import java.util.List;

import edu.ncwu.data.Doctor;

public interface DoctorInter {
	 public boolean saveDoctor(Doctor doctor);//新增医生信息
	 
	 public boolean delDoctor(String id);//删除医生信息
	 
	 public boolean updateDoctor(Doctor doctor);//更新医生信息
	 
	 public Doctor queryDoctor(Doctor doctor);//查询用户
	 
	 public List<Doctor> queryDoctorByName(String name);//按名字查询医生
	 
	 public Doctor queryDoctorById(String id);//按身份证号查询医生
	 
	 public List<Doctor> queryDoctorByDepart(String depart, int genre);//按照科室查询医生
	 
	 public List<Doctor> queryAllDoctor();//查询所有的医生
	
	 public Doctor queryDoctorByNameDepart(String name, String depart);//按照医生的科室和姓名查询医生
	 
}
