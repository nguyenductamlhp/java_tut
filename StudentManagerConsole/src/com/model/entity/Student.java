package com.model.entity;

public class Student {

	private String ID;
	private String Name;
	private double Mark;
	private String Image;
	private String Address;
	private String Note;
	
	public Student() {
		
	}
	
	public Student(String str) {
		String [] arr = str.split(",");
		this.ID = arr[0];
		this.Name = arr[1];
		this.Mark = Double.parseDouble(arr[2]);
		this.Image = arr[3];
		this.Address = arr[4];
		this.Note = arr[5];
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getMark() {
		return Mark;
	}

	public void setMark(double mark) {
		Mark = mark;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}
	
	

}
