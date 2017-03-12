package com.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.model.entity.Student;

public class StudentManager {

	private static void printMainMenu() {
		System.out.println("======== STUDENT MANAGER =======");
		System.out.println("1. Danh sách học sinh tăng dần theo ID");
		System.out.println("2. Danh sách học sinh giàm dần theo ID");
		System.out.println("3. Danh sách học sinh tăng dần theo điểm");
		System.out.println("4. Danh sách học sinh giảm dần theo điểm");
		System.out.println("5. Thêm học sinh");
		System.out.println("6. Sửa thông tin học sinh");
		System.out.println("7. Xóa học sinh");
		System.out.println("8. Nhập danh sách học sinh từ file csv");
		System.out.println("9. Xuất danh sách học sinh ra file csv");
		System.out.println("10. Thoát");
		System.out.println("====== Mời bạn chọn chức năng: ");
	}
	
	public static void printStudent(Student st) {
		if (st != null) {
			System.out.println(st.getID() + "|" + st.getName() + "|" + st.getMark() + "|" + st.getAddress() + "|" + st.getNote());
		}
	}
	private static boolean isExist(List<Student> list, String id) {
		for (Student s : list) {
			if (s.getID().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		List<Student> listStudent = new ArrayList<Student>();
		Scanner scanner = new Scanner(System.in);
		String select = "";
		boolean exit = false;
		while (!exit) {
			select = "";
			printMainMenu();
			if (scanner.hasNextLine()) {
				select = scanner.nextLine();
			}
			
			switch (select) {
			case "1": // In danh sách học sinh tăng theo ID
				for (int i = 0; i < listStudent.size() - 1; i++) {
					for (int j = i; j < listStudent.size(); j++) {
						if (listStudent.get(i).getID().compareTo(listStudent.get(j).getID()) > 0) {
							Collections.swap(listStudent, i, j);
						}
					}
				}
				for (Student student : listStudent) {
					printStudent(student);
				}
				break;
			case "2": // In danh sách học sinh giảm theo ID
				for (int i = 0; i < listStudent.size() - 1; i++) {
					for (int j = i; j < listStudent.size(); j++) {
						if (listStudent.get(i).getID().compareTo(listStudent.get(j).getID()) < 0) {
							Collections.swap(listStudent, i, j);
						}
					}
				}
				for (Student student : listStudent) {
					printStudent(student);
				}
				scanner.reset();
				break;
			case "3": // In danh sách học sinh tang theo diem
				for (int i = 0; i < listStudent.size() - 1; i++) {
					for (int j = i; j < listStudent.size(); j++) {
						if (listStudent.get(i).getMark() > listStudent.get(j).getMark()) {
							Collections.swap(listStudent, i, j);
						}
					}
				}
				for (Student student : listStudent) {
					printStudent(student);
				}
				scanner.reset();
				break;
			case "4": // In danh sách học sinh giảm theo diem
				for (int i = 0; i < listStudent.size() - 1; i++) {
					for (int j = i; j < listStudent.size(); j++) {
						if (listStudent.get(i).getMark() < listStudent.get(j).getMark()) {
							Collections.swap(listStudent, i, j);
						}
					}
				}
				for (Student student : listStudent) {
					printStudent(student);
				}
				scanner.reset();
				break;
			case "5": // Them hoc sinh
				try {
					Student student = new Student();
					System.out.println("________________ Thêm học sinh: _________________");
					System.out.println("=> Nhập mã học sinh: ");
					if (scanner.hasNextLine()) {
						student.setID(scanner.nextLine());
					}
					System.out.println("=> Nhập tên học sinh: ");
					if (scanner.hasNextLine()) {
						student.setName(scanner.nextLine());
					}
					System.out.println("=> Nhập hình ảnh: ");
					if (scanner.hasNextLine()) {
						student.setImage(scanner.nextLine());
					}
					System.out.println("=> Nhập địa chỉ: ");
					if (scanner.hasNextLine()) {
						student.setAddress(scanner.nextLine());
					}
					System.out.println("Nhập ghi chú: ");
					if (scanner.hasNextLine()) {
						student.setNote(scanner.nextLine());
					}
					System.out.println("=> Nhập điểm: ");
					if (scanner.hasNextLine()) {
						student.setMark(scanner.nextDouble());
					}
					
					if (isExist(listStudent, student.getID())) {
						System.out.println("ERROR: Đã tồn tại học sinh này! Thêm không thành công");
					}
					else {
						listStudent.add(student);
					}
				}
				catch (Exception e) {
					System.out.println("Error");
				}
				
				scanner.reset();
				break;
			case "6": // Sua hoc sinh
				try {
					String id = "";
					System.out.println("Nhập id học sinh cần sửa");
					id = scanner.nextLine();
					for (Student edit_student : listStudent) {
						if (edit_student.getID().equals(id)) {
							System.out.println("Nhập lại tên: ");
							edit_student.setName(scanner.nextLine());
							System.out.println("Nhập lại địa chỉ: ");
							edit_student.setAddress(scanner.nextLine());
							System.out.println("Nhập lại ghi chú: ");
							edit_student.setNote(scanner.nextLine());
							System.out.println("Nhập lại điểm: ");
							edit_student.setMark(scanner.nextDouble());
							break;
						}
					}
				}
				catch (Exception e) {
					System.out.println("Error!");
				}
				scanner.reset();
				break;
			case "7": // Xoa hoc sinh
				try {
					System.out.println("Nhập id học sinh cần xóa: ");
					String delete_id = "";
					if (scanner.hasNextLine()){
						delete_id = scanner.nextLine();
					}
					for (Student delete_student : listStudent) {
						if (delete_student.getID().equals(delete_id)) {
							listStudent.remove(delete_student);
							System.out.println("Removed");
							break;
						}
					}
					scanner.reset();
				}
				catch (Exception e) {
					System.out.println("Error!");
				}
				break;
			case "8": // Import from csv
				try {
					String path = "";
					System.out.println("Nhập đường dẫn file: ");
					path = scanner.nextLine();
					BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
					String line_import = bufferedReader.readLine();
					while (line_import != null) {
						Student tmp_studnet = new Student(line_import);
						if (!isExist(listStudent, tmp_studnet.getID())) {
							listStudent.add(tmp_studnet);
							printStudent(tmp_studnet);
							
						}
						else {
							System.out.println("Đã tồn tại hoc sinh có ID " + tmp_studnet.getID());
						}
						line_import = bufferedReader.readLine();
					}
					bufferedReader.close();
				}
				catch (FileNotFoundException e) {
					System.out.println("Không tìm thấy file");
				}
				break;
			case "9": //Export to csv
				try {
					System.out.println("Nhập đường dẫn file: ");
					String out_path = scanner.nextLine();
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(out_path)));
					for (Student export_student : listStudent) {
						String line_export = "";
						line_export += export_student.getID() + ",";
						line_export += export_student.getName() + ",";
						line_export += Double.toString(export_student.getMark()) + ",";
						line_export += export_student.getImage() + ",";
						line_export += export_student.getAddress() + ",";
						line_export += export_student.getNote() + "\n";
						bufferedWriter.write(line_export);
					}
					bufferedWriter.close();
				} 
				catch (Exception e) {
					System.out.println("Error!");
				}
				break;
			case "10":
				exit = true;
				scanner.reset();
				break;
			}
		}
		System.out.println("======= GOODBYE! SEE YOU AGAIN! ======");
		scanner.close();
	}

}
