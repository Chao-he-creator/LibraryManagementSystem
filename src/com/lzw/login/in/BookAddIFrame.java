package com.lzw.login.in;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.dao.Dao;
import com.dao.model.Book;
import com.lzw.CreateIcon;
import com.lzw.MyDocument;

public class BookAddIFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField author;
	private JTextField id;
	private JTextField sid;
	private JFormattedTextField in_date;
	private JTextField now_amount;
	private JTextField press;
	private JTextField price;
	private JTextField title;
	private JTextField total;
	
	public BookAddIFrame() {
		super();
		setIconifiable(true);							// ���ô������С������������
		setClosable(true);								// ���ô���ɹرգ���������
		setTitle("�鼮���");						// ���ô�����⣭��������
		setBounds(100, 50, 500, 350);

		//������ͷͼƬ
		final JLabel logoLabel = new JLabel();
		ImageIcon readerAddIcon=CreateIcon.add("tback.jpg");
		logoLabel.setIcon(readerAddIcon);
		logoLabel.setOpaque(true);
		logoLabel.setBackground(Color.white);
		logoLabel.setPreferredSize(new Dimension(400, 60));
		getContentPane().add(logoLabel, BorderLayout.NORTH);

		//����һ�������������
		final JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		getContentPane().add(panel);

		//�������������Ƕ�����1,���ڷ��÷ǰ�ť���
		final JPanel panel_1 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(15);
		gridLayout.setHgap(10);
		panel_1.setLayout(gridLayout);
		panel_1.setPreferredSize(new Dimension(450, 200));
		panel.add(panel_1);

		final JLabel label_2 = new JLabel();
		label_2.setText("��     �ߣ�");
		panel_1.add(label_2);
		author = new JTextField();
		author.setDocument(new MyDocument(256));
		panel_1.add(author);
		
		final JLabel label_3 = new JLabel();
		label_3.setText("��     �ţ�");
		panel_1.add(label_3);
		id = new JTextField();
		id.setDocument(new MyDocument(256));
		panel_1.add(id);
		
		final JLabel label_4 = new JLabel();
		label_4.setText("��ܱ�� ��");
		panel_1.add(label_4);
		sid = new JTextField();
		sid.setDocument(new MyDocument(256));
		panel_1.add(sid);
		
		final JLabel label_5 = new JLabel();
		label_5.setText("������ڣ�");
		panel_1.add(label_5);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		in_date = new JFormattedTextField();
		in_date.setValue("XXXX-XX-XX");
		in_date.addKeyListener(new DateListener());
		panel_1.add(in_date);
		
		final JLabel label_6 = new JLabel();
		label_6.setText("�ִ�����");
		panel_1.add(label_6);
		now_amount = new JTextField();
		now_amount.setDocument(new MyDocument(256));
		panel_1.add(now_amount);
		
		final JLabel label_7 = new JLabel();
		label_7.setText("�����磺");
		panel_1.add(label_7);
		press = new JTextField();
		press.setDocument(new MyDocument(256));
		panel_1.add(press);
		
		final JLabel label_8 = new JLabel();
		label_8.setText("�۸�");
		panel_1.add(label_8);
		price = new JTextField();
		price.setDocument(new MyDocument(256));
		panel_1.add(price);
		
		final JLabel label_9 = new JLabel();
		label_9.setText("������");
		panel_1.add(label_9);
		title = new JTextField();
		title.setDocument(new MyDocument(256));
		panel_1.add(title);
		
		final JLabel label_10 = new JLabel();
		label_10.setText("������");
		panel_1.add(label_10);
		total = new JTextField();
		total.setDocument(new MyDocument(256));
		panel_1.add(total);

		//����������Ƕ��һ�����ڷ��Ű�ť�����
		final JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(450, 100));
		panel.add(panel_2);
		
		final JRadioButton radioButton1 = new JRadioButton();

		//�����������
		final JButton submit = new JButton();
		panel_2.add(submit);
		submit.setText("�ύ");
		submit.addActionListener((ActionListener) new ButtonAddListener(radioButton1));
		
		//�����������
		final JButton back = new JButton();
		panel_2.add(back);
		back.setText("����");
		back.addActionListener(new CloseActionListener());

		setVisible(true);
	}
	
	//ʱ���ʽ����
	class DateListener extends KeyAdapter {
		public void focusGained(FocusEvent e) {
			in_date.setText("");
		}
	}
	
	//�������水ť�����ڲ���
	class ButtonAddListener implements ActionListener {
		ButtonAddListener(JRadioButton button1) {
		}

		public void actionPerformed(final ActionEvent e) {
			
			Book book = new Book();
			
			if(author.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "�������ֲ���Ϊ��");
				return ;
			}
			if(id.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "ͼ���Ų���Ϊ��");
				return;
			}
			if(sid.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "��ܱ���ı��򲻿�Ϊ��");
				return;
			}
			if(in_date.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���ڲ��ܿ�");
				return;
			}
			if(now_amount.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�ִ�������Ϊ��");
				return;
			}
			if(press.getText().length()==0){
				JOptionPane.showMessageDialog(null, "�����̲���Ϊ��");
				return;
			}
			if(price.getText().length()==0){
				JOptionPane.showMessageDialog(null, "���۲���Ϊ��");
				return;
			}
			if (Double.parseDouble(price.getText().trim()) < 0) {
				JOptionPane.showMessageDialog(null, "���۲���Ϊ����");
				price.setText("");
				return;
			}
			if(title.getText().length()==0){
				JOptionPane.showMessageDialog(null, "��������Ϊ��");
				return;
			}
			if (Integer.parseInt(total.getText().trim()) <= 0) {
				JOptionPane.showMessageDialog(null, "����Ӧ>=0�������鼮���������������");
				total.setText("");
				return;
			}
			book.setAuthor(author.getText().trim());
			book.setId(id.getText().trim());
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf.parse(in_date.getText().trim());
				Date sqlDate = new Date(date.getTime());
				book.setIn_date(sqlDate);
				//System.out.println(t);
			} catch (ParseException e2) {
				//System.out.println("in: " + t);
				e2.printStackTrace();
			}
			book.setNow_amount(Integer.parseInt(now_amount.getText().trim()));
			book.setPress(press.getText().trim());
			book.setPrice(Double.parseDouble(price.getText().trim()));
			book.setSid(sid.getText().trim());
			book.setTitle(title.getText().trim());
			book.setTotal(Integer.parseInt(total.getText().trim()));
		
			try {
				if(Dao.insertBookInfo(book, 1)){
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
					doDefaultCloseAction();
				}
			} catch (NumberFormatException e1) {
				String message = e1.getMessage();
				int index = message.lastIndexOf(')');
				message = message.substring(index + 1);
				JOptionPane.showMessageDialog(null, message);
				e1.printStackTrace();
			} 
		}
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
