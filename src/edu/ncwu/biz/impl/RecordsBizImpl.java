package edu.ncwu.biz.impl;

import java.util.List;

import edu.ncwu.biz.RecordsBiz;
import edu.ncwu.data.Records;
import edu.ncwu.inter.RecordsInter;
import edu.ncwu.inter.impl.RecordsInterImpl;

public class RecordsBizImpl implements RecordsBiz {
	private RecordsInter recordsInter = null;
	public RecordsBizImpl() {
		recordsInter = new RecordsInterImpl();
	}
	@Override
	public List<Records> queryRecords() {
		return recordsInter.queryRecords();
	}

	@Override
	public List<Records> queryRecordsByPsno(int psno) {
		return recordsInter.queryRecordsByPsno(psno);
	}

	@Override
	public List<Records> queryRecordsByDid(String did) {
		return recordsInter.queryRecordsByDid(did);
	}

	@Override
	public List<Records> quertRecorddsByDisease(String disease) {
		return recordsInter.quertRecorddsByDisease(disease);
	}

	@Override
	public List<Records> queryRecordsByDepart(String depart) {
		return recordsInter.queryRecordsByDepart(depart);
	}
	@Override
	public Records queryRecordsByIndate(String indate) {
		return recordsInter.queryRecordsByIndate(indate);
	}
	@Override
	public boolean saveRecord(Records record) {
		return recordsInter.saveRecord(record);
	}
	@Override
	public boolean updateRecord(Records record) {
		return recordsInter.updateRecord(record);
	}
	@Override
	public boolean updateRecordNoIndate(Records record) {
		return recordsInter.updateRecordNoIndate(record);
	}
	@Override
	public List<Records> queryRecordsByDidIndate(String did, String date) {
		return recordsInter.queryRecordsByDidIndate(did, date);
	}

}
