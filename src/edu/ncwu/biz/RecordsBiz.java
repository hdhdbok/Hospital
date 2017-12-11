package edu.ncwu.biz;

import java.util.List;

import edu.ncwu.data.Records;

public interface RecordsBiz {
	
	public boolean saveRecord(Records record);//������¼
	
	public boolean updateRecord(Records record);//���¼�¼
	
	public boolean updateRecordNoIndate(Records record);//���¼�¼(������indate)
	
	public List<Records> queryRecords();//��ѯ���м�¼
	
	public List<Records> queryRecordsByPsno(int psno);//���ղ��˵ı�Ų��Ҽ�¼
	
	public List<Records> queryRecordsByDid(String did);//����ҽ��ID���Ҽ�¼
	
	public List<Records> quertRecorddsByDisease(String disease);//���ղ������Ҳ�����Ϣ
	
	public List<Records> queryRecordsByDepart(String depart);//���տ��Ҳ�ѯ��¼
	
	public Records queryRecordsByIndate(String indate);//���չҺ����ڽ��в�ѯ 
	
	public List<Records> queryRecordsByDidIndate(String did, String date);//����ҽ����Һ����ڲ�ѯ��¼
	
}
