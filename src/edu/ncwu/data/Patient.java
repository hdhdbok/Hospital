package edu.ncwu.data;

public class Patient {
	private int sno;
	private String id;
	private int age;
	private String sex;
	private String phone;
	private String name;
	
	public Patient() {
		super();
	}
	
	public Patient(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	public Patient(String name, String id, int age, String sex, String phone) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
	}
	
	public Patient(int sno, String name, String id, int age, String sex, String phone) {
		super();
		this.sno = sno;
		this.name = name;
		this.id = id;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
	}
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
