package edu.ncwu.data;

public class Doctor {
	private String name;
	private String id;
	private int age;
	private String sex;
	private String phone;
	private String pass;
	private int genre;
	private String depart;

	public Doctor() {
		super();
	}

	public Doctor(String name, String pass, int genre) {
		super();
		this.name = name;
		this.pass = pass;
		this.genre = genre;
	}
	
	public Doctor(String name, String id, int age, String sex, String phone, String pass, int genre, String depart) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
		this.pass = pass;
		this.genre = genre;
		this.depart = depart;
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getGenre() {
		return genre;
	}
	public void setGenre(int genre) {
		this.genre = genre;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	
}
