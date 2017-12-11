package edu.ncwu.inter;

import java.util.List;

import edu.ncwu.data.Doctor;

public interface DoctorInter {
	 public boolean saveDoctor(Doctor doctor);//����ҽ����Ϣ
	 
	 public boolean delDoctor(String id);//ɾ��ҽ����Ϣ
	 
	 public boolean updateDoctor(Doctor doctor);//����ҽ����Ϣ
	 
	 public Doctor queryDoctor(Doctor doctor);//��ѯ�û�
	 
	 public List<Doctor> queryDoctorByName(String name);//�����ֲ�ѯҽ��
	 
	 public Doctor queryDoctorById(String id);//�����֤�Ų�ѯҽ��
	 
	 public List<Doctor> queryDoctorByDepart(String depart, int genre);//���տ��Ҳ�ѯҽ��
	 
	 public List<Doctor> queryAllDoctor();//��ѯ���е�ҽ��
	
	 public Doctor queryDoctorByNameDepart(String name, String depart);//����ҽ���Ŀ��Һ�������ѯҽ��
	 
}
