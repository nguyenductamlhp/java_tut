package com.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BufferReader {
	
	private static void docBanphim() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, "utf8"));
		System.out.println("Nhập vào nội dung: ");
		String str = bufferedReader.readLine();
		System.out.println("Nội dung vừa nhập: " + str);
	}
	
	private static void docFile(String filename)  {
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(filename));
			String str;
			while ((str = bufferedReader.readLine()) != null) {
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file");
			e.printStackTrace();
		} catch (IOException io) {
			System.out.println("Không thể đọc file");
		}
				
	}

	public static void main(String[] args) throws IOException {
		docBanphim();
		System.out.println("----------------------");
		System.out.println("Nhập tên file: "); 
		String filename;
		Scanner scanner = new Scanner(System.in);
		filename = scanner.nextLine();
		docFile(filename);
	}

}
