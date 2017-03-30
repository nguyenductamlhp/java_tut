package com.ui.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.model.dict.Dictionary;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class DictionaryUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEn;
	private JTextField txtVi;

	String login = "";
	String password = "";
	
	String word = "";
	String mean = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictionaryUI frame = new DictionaryUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DictionaryUI() {
		setFont(new Font("Times New Roman", Font.PLAIN, 13));
		setTitle("Dictionary - nguyenductamlhp");
		
		// Initial dictionary
		Dictionary dictionary_en = new Dictionary("src/com/data/dict/Anh_Viet.xml");
		Dictionary dictionary_vi = new Dictionary("src/com/data/dict/Viet_Anh.xml");
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 428);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmFavouriteWords = new JMenuItem("Favourite Words");
		mntmFavouriteWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> listFavour = dictionary_en.getFavouriteWords();
			}
		});
		mnTools.add(mntmFavouriteWords);
		
		JMenuItem mntmStatistic = new JMenuItem("Statistic");
		mntmStatistic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Map<String, Integer> mapLog = dictionary_en.getStatistic("20100101", "20181201");
				JOptionPane.showMessageDialog(null, mapLog);
			}
		});
		mnTools.add(mntmStatistic);
		
		JMenuItem mntmReset = new JMenuItem("Reset");
		mntmReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnTools.add(mntmReset);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmIntrduce = new JMenuItem("Introduce");
		mnAbout.add(mntmIntrduce);
		
		JMenuItem mntmLicense = new JMenuItem("License");
		mnAbout.add(mntmLicense);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		menuBar.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEn = new JTextField();
		txtEn.setBounds(10, 50, 120, 20);
		contentPane.add(txtEn);
		txtEn.setColumns(10);
		
		txtVi = new JTextField();
		txtVi.setBounds(310, 50, 120, 20);
		contentPane.add(txtVi);
		txtVi.setColumns(10);
		
		JTextArea txtrTransEn = new JTextArea();
		txtrTransEn.setEditable(false);
		txtrTransEn.setBounds(10, 90, 290, 200);
		contentPane.add(txtrTransEn);
		
		JTextArea txtTransVi = new JTextArea();
		txtTransVi.setEditable(false);
		txtTransVi.setBounds(310, 90, 290, 200);
		contentPane.add(txtTransVi);
		
		JButton btnTransen = new JButton("Eng to Vi");
		btnTransen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				word = txtEn.getText();
				mean = dictionary_en.getMeaning(word);
				if (mean == null) {
					txtrTransEn.setText("This word does not exist!");
				}
				else {
					txtrTransEn.setText(mean);
					dictionary_en.addLog(word);
				}
			}
		});
		btnTransen.setBounds(150, 50, 100, 25);
		contentPane.add(btnTransen);
		
		JButton btnTransvi = new JButton("Vi to Eng");
		btnTransvi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				word = txtVi.getText();
				mean = dictionary_vi.getMeaning(word);
				if (mean == null) {
					txtTransVi.setText("This word does not exist!");
				}
				else {
					txtTransVi.setText(mean);
					dictionary_vi.addLog(word);
				}
			}
		});
		btnTransvi.setBounds(450, 50, 100, 25);
		contentPane.add(btnTransvi);
		
		JButton btnAddToFavourite = new JButton("Add To Favourite");
		btnAddToFavourite.setBounds(39, 302, 222, 25);
		btnAddToFavourite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dictionary_en.addToFavourite(txtEn.getText());
				JOptionPane.showMessageDialog(null, "Added to Favourite Words");
			}
		});
		contentPane.add(btnAddToFavourite);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(284, 302, 117, 25);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtEn.setText("");
				txtVi.setText("");
				txtTransVi.setText("");
				txtrTransEn.setText("");
			}
		});
		contentPane.add(btnClear);
	}
}

