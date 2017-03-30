package com.model.dict;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.JOptionPane;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Dictionary {
	
	Map<String, String> dictionary = new HashMap<String, String>();
	
	public Dictionary() {
		
	}
	public Dictionary(String path) {
		
		try {
			File data_file = new File(path);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder;
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(data_file);
			Element dict_node = document.getDocumentElement();
			NodeList records = dict_node.getElementsByTagName("record");
			long lenRecords = records.getLength();
			
			for (int i = 0; i < lenRecords; i++) {
				NodeList lst = records.item(i).getChildNodes();
				String key = lst.item(1).getTextContent();
				String value = lst.item(3).getTextContent();
				dictionary.put(key, value);
			}
		} catch (ParserConfigurationException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		} catch (SAXException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
	}
	
	public String getMeaning(String word) {
		String mean = dictionary.get(word);
		return mean;
	}
	
	public List<String> getFavouriteWords() {
		List<String> listFavourite = new ArrayList<String>();
		try {
			File file = new File("src/com/data/user/favourite.xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder;
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			Element root = document.getDocumentElement();
			NodeList listNodeFavourite = root.getElementsByTagName("word");
			for (int i = 0; i < listNodeFavourite.getLength(); i++) {
				listFavourite.add(listNodeFavourite.item(1).getTextContent());
			}
		}
		catch (Exception e) {
			
		}
		return listFavourite;
	}
	
	public void addToFavourite(String word) {
		try {
			File file = new File("src/com/data/user/favourite.xml");
			if (!file.exists()) {
				file.createNewFile();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder  = documentBuilderFactory.newDocumentBuilder();;
			Document document = documentBuilder.parse(file);
			Element root = document.getDocumentElement();
			if (root == null) {
				root = document.createElement("root");
				document.appendChild(root);
			}
			else {
				NodeList listNode = root.getElementsByTagName("word");
				boolean isExist = false;
				for (int i = 0; i < listNode.getLength(); i++) {
					if (listNode.item(i).getTextContent().equals(word)) {
						isExist = true;
						break;
					}
				}
				if (!isExist) {
					Element element = document.createElement("word");
					element.setTextContent(word);
					System.out.println(element.getTextContent());
					root.appendChild(element);
					Transformer tr = TransformerFactory.newInstance().newTransformer();
					DOMSource src = new DOMSource(document);
					StreamResult re = new StreamResult(file);
					
					tr.transform(src, re);
				}
			}
		}
		catch (Exception e) {
			
		}
	}
	
	public Map<String, Integer> getStatistic(String startDate, String endDate) {
		Map<String, Integer> mapLog = new HashMap<>();
		try {
			File file = new File("src/com/data/dict/log.xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder;
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			Element root = document.getDocumentElement();
			NodeList listNode = root.getElementsByTagName("look");
			int len = listNode.getLength();
			System.out.println(len);
			for (int i = 0; i < len; i++) {
				String word = listNode.item(i).getChildNodes().item(0).getTextContent();
				String str_date = listNode.item(i).getChildNodes().item(1).getTextContent();
				int date = Integer.parseInt(str_date);
				if (date >= Integer.parseInt(startDate) && date <= Integer.parseInt(endDate)) {
					if (mapLog.containsKey(word)) {
						int count = mapLog.get(word);
						count ++;
						mapLog.replace(word, count);
					}
					else if (date >= Integer.parseInt(startDate) && date <= Integer.parseInt(endDate)) {
						mapLog.put(word, 1);
					}
				}
			}
		}
		catch (Exception e) {
			
		}
		return mapLog;
	}
	
	public void addLog(String word) {
		try {
			File file = new File("src/com/data/dict/log.xml");
			if (!file.exists()) {
				file.createNewFile();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder;
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			Element root = document.getDocumentElement();
			if (root == null) {
				root = document.createElement("root");
				document.appendChild(root);
			}
			else {
				Element element = document.createElement("look");
				Element w = document.createElement("word");
				w.setTextContent(word);
				Element d = document.createElement("date");
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Date date = new Date();
				d.setTextContent(dateFormat.format(date));
				element.appendChild(w);
				element.appendChild(d);
				root.appendChild(element);
				Transformer tr = TransformerFactory.newInstance().newTransformer();
				DOMSource src = new DOMSource(document);
				StreamResult re = new StreamResult(file);
				
				tr.transform(src, re);
			}
			
		}
		catch (Exception e) {
			
		}
	}

}
