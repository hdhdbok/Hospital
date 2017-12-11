package edu.ncwu.biz;

import java.util.List;

import edu.ncwu.data.Patient;

public interface PatientBiz {
	public boolean savePatient(Patient patient);//����������Ϣ
	
	public boolean delectPatient(String id);//ɾ��������Ϣ
	
	public boolean updatePatient(Patient patient);//���²�����Ϣ
	
	public Patient queryPatient(Patient patient);//���Ҳ���
	
	public Patient queryPatientBySno(int sno);//���ղ��˱�Ų���
	
	public List<Patient> queryPatientByName(String name);//����������ѯ������Ϣ
	
	public Patient queryPatientById(String id);//��������֤�Ų�ѯ������Ϣ	
	
	public int Appointment(int psno,String id);//����ҽ�������ҺŹ���
	
	public Patient login(Patient patient);//�û���¼�����صľ��ǵ�¼�û�����Ϣ������
			
	public int registerUser(Patient patient);//ע���û�
	
}