package com.newer.cjl.leidian;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * �����࣬�̳�Thread��
 * 
 * @author kowloon
 * 
 */
public class BgThread extends Thread {

	// ����һ��ͼ�����
	private ImageIcon icon = new ImageIcon("img/bg01.jpg");
	// ��ͼ��ת����ͼƬ����
	private Image img = icon.getImage();
	private Image img2 = icon.getImage();

	private int x = 0, y = 0;
	private int x2 = 0, y2 = -700;

	// ��дrun�������̵߳���ں���
	public void run() {
		while (true) {
			y++;
			y2++;
			if (y >= 700) {
				y = -700;
			}
			if (y2 == 700) {
				y2 = -700;
			}

			// ����100ms
			try {
				Thread.sleep(100);
			} catch (Exception ef) {
				ef.printStackTrace();
			}
		}
	}

	// ���Ʊ����ķ���
	public void draw(Graphics g) {
		// ��ͼƬ���Ƴ���
		g.drawImage(img, x, y, 450, 700, null);
		g.drawImage(img2, x2, y2, 450, 700, null);
	}

}
