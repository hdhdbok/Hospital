package edu.ncwu.inter.impl;

import java.util.ArrayList;
import java.util.List;

import edu.ncwu.data.Records;
import edu.ncwu.inter.RecordsInter;

public class RecordsInterImpl extends BaseImpl implements RecordsInter {

	@Override
	public boolean saveRecord(Records record) {
		String sql = "insert into records(did,psno,disease,drug_sno,depart,bed,indate,outdate,advice) values(?,?,?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(record.getDid());
		params.add(record.getPsno());
		params.add(record.getDisease());
		params.add(record.getDrug_sno());
		params.add(record.getDepart());
		params.add(record.getBed());
		params.add(record.getIndate());
		params.add(record.getOutdate());
		params.add(record.getAdvice());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateRecord(Records record) {
		String sql = "update records set disease = ?,drug_sno = ?,bed = ?,outdate = ?, advice = ? where psno = ? and indate = ?";
		List<Object> params = new ArrayList<Object>();
		
		params.add(record.getDisease());
		params.add(record.getDrug_sno());
		params.add(record.getBed());
		params.add(record.getOutdate());
		params.add(record.getAdvice());
		params.add(record.getPsno());
		params.add(record.getIndate());
		return this.operUpdate(sql, params);
	}

	@Override
	public List<Records> queryRecords() {
		String sql = "select * from records";
		List<Records> dList = null;
		try {
			dList = this.operQuery(sql, null, Records.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public List<Records> queryRecordsByPsno(int psno) {
		String sql = "select * from records where psno = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(psno);
		List<Records> dList = null;
		try {
			dList = this.operQuery(sql, params, Records.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public List<Records> queryRecordsByDid(String did) {
		String sql = "select psno,disease,drug_sno,depart,bed,indate,outdate,advice from records where did = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(did);
		List<Records> dList = null;
		try {
			dList = this.operQuery(sql, params, Records.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public List<Records> quertRecorddsByDisease(String disease) {
		String sql = "select did,psno,drug_sno,depart,bed,indate,outdate,advice from records where disease = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(disease);
		List<Records> dList = null;
		try {
			dList = this.operQuery(sql, params, Records.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public List<Records> queryRecordsByDepart(String depart) {
		String sql = "select did,psno,disease,drug_sno,bed,indate,outdate,advice from records where depart = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(depart);
		List<Records> dList = null;
		try {
			dList = this.operQuery(sql, params, Records.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public Records queryRecordsByIndate(String indate) {
		String sql = "select * from records where indate = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(indate);
		List<Records> dList = null;
		try {
			dList = this.operQuery(sql, params, Records.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dList.size()>0) {
			return dList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Records> queryRecordsByDidIndate(String did, String date) {
		String sql = "select * from records where did = ? and indate like ?";
		List<Object> params = new ArrayList<Object>();
		params.add(did);
		params.add(date);
		List<Records> dList = null;
		try {
			dList = this.operQuery(sql, params, Records.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public boolean updateRecordNoIndate(Records record) {
		String sql = "update records set did = ?,disease = ?,drug_sno = ?,depart = ?,bed = ?,outdate = ?, advice = ? where psno = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(record.getDid());
		params.add(record.getDisease());
		params.add(record.getDrug_sno());
		params.add(record.getDepart());
		params.add(record.getBed());
		params.add(record.getOutdate());
		params.add(record.getAdvice());
		params.add(record.getPsno());
		return this.operUpdate(sql, params);
	}
}
