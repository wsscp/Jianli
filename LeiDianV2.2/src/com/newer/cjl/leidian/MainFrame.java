package com.newer.cjl.leidian;

import javax.swing.JFrame;

/**
 * ��������
 * 
 * @author kowloon
 * 
 */
public class MainFrame {

	public static void main(String[] args) {

		JFrame jf = new JFrame();
		jf.setTitle("�׵�");
		jf.setSize(450, 700);
		// ���������� 0��ʲô������
		// 1�����أ����ڶ��󱣴����ڴ��У�Ĭ����1
		// 2�����ز����ڴ��Ƴ����ڶ���
		// 3���˳���������
		jf.setDefaultCloseOperation(3);
		// ���һ�����
		MyPanel gamePanel = new MyPanel();
		// �������ı�����ɫ
//		gamePanel.setBackground(new Color(255, 0, 0));
		jf.add(gamePanel);
		jf.setVisible(true);

		

	}
}
