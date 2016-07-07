package com.lzw.login.in;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.dao.Dao;
import com.dao.model.BookShelf;
import com.lzw.CreateIcon;
import com.lzw.MyDocument;

public class BSAddIFrame extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	private JTextField id;
	private JTextField position;
	
	public BSAddIFrame() {
		super();
		setIconifiable(true);							// ���ô������С������������
		setClosable(true);								// ���ô���ɹرգ���������
		setTitle("������");						// ���ô�����⣭��������
		setBounds(100, 50, 500, 225);

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
		final GridLayout gridLayout = new GridLayout(2, 2);
		gridLayout.setVgap(20);
		panel_1.setLayout(gridLayout);
		panel.add(panel_1);

		final JLabel label_2 = new JLabel();
		label_2.setText("��ܱ�ţ�");
		panel_1.add(label_2);
		id = new JTextField(10);
		id.setDocument(new MyDocument(256));
		panel_1.add(id);
		
		final JLabel label_3 = new JLabel();
		label_3.setText("���λ�ã�");
		panel_1.add(label_3);
		position = new JTextField(10);
		position.setDocument(new MyDocument(256));
		panel_1.add(position);
		
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
	
	
	//�������水ť�����ڲ���
	class ButtonAddListener implements ActionListener {
		ButtonAddListener(JRadioButton button1) {
		}

		public void actionPerformed(final ActionEvent e) {
			
			BookShelf bs = new BookShelf();
			
			if(id.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "��ܱ�Ų���Ϊ��");
				return ;
			}
			if(position.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "���λ�ò���Ϊ��");
				return;
			}
			bs.setId(id.getText().trim());
			bs.setPosition(position.getText().trim());
		
			try {
				if(Dao.insertBSInfo(bs)){
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
					doDefaultCloseAction();
				}
				else {
					JOptionPane.showMessageDialog(null, "���ʧ�ܣ�����Ѵ���");
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
