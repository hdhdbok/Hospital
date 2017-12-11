package edu.ncwu.biz;

import java.util.List;

import edu.ncwu.data.Records;

public interface RecordsBiz {
	
	public boolean saveRecord(Records record);//新增记录
	
	public boolean updateRecord(Records record);//更新记录
	
	public boolean updateRecordNoIndate(Records record);//更新记录(不更新indate)
	
	public List<Records> queryRecords();//查询所有记录
	
	public List<Records> queryRecordsByPsno(int psno);//按照病人的编号查找记录
	
	public List<Records> queryRecordsByDid(String did);//按照医生ID查找记录
	
	public List<Records> quertRecorddsByDisease(String disease);//按照病名查找病人信息
	
	public List<Records> queryRecordsByDepart(String depart);//按照科室查询记录
	
	public Records queryRecordsByIndate(String indate);//按照挂号日期进行查询 
	
	public List<Records> queryRecordsByDidIndate(String did, String date);//按照医生与挂号日期查询记录
	
}
