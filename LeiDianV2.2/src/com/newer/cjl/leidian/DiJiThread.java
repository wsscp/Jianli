package com.newer.cjl.leidian;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * �л����߳���
 * 
 * @author kowloon
 * 
 */
public class DiJiThread extends Thread {

	private Image img;// �л�ͼƬ
	private int x, y;// ���Ͻ�����
	private int width, height;// ��Ⱥ͸߶�
	public boolean isLive = true;// �ɻ��ǲ��ǻ���
	public int type=0;//�ɻ�����0 С�ɻ�  1�зɻ�  2BOSS
	
	public Rectangle rect = new Rectangle();

	// �ڹ��췽����ȷ���л�ͼƬ��λ��
	public DiJiThread(int num) {
		// ���һ��1~10֮����������֣���ʾ�л�
		int type = (int) (Math.random() * 5) + 1;
		if (num == 1) {
			type = (int) (Math.random() * 3) + 6;
		} else if (num == 2) {
			type = (int) (Math.random() * 2) + 9;
		} else {
			type = (int) (Math.random() * 5) + 1;
		}
		ImageIcon icon = new ImageIcon("img/enemy" + type + ".png");

		img = icon.getImage();
		width = icon.getIconWidth() / 2;
		height = icon.getIconHeight() / 2;

		y = -height;
		x = (int) (Math.random() * (450 - width));
		
		rect.x=x;
		rect.y = y;
		rect.width = width;
		rect.height = height;
	}

	public void run() {
		// ����
		FireThread ft = new FireThread(this);
		ft.start();

		while (isLive) {
			y++;
			rect.y = y;
			// ���ɻ��뿪��Ϸ���򣬾�û�к��壬����ʧȥ����
			if (y >= 700) {
				isLive = false;
			}

			// ����100ms
			try {
				Thread.sleep(20);
			} catch (Exception ef) {
				ef.printStackTrace();
			}

		}
	}

	// ���Ʊ����ķ���
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

	// �ɻ�����ķ���
	public ZiDanThread fire() {
		// �����ӵ��߳�
		ZiDanThread zidan = new ZiDanThread(x + width / 2, y + height);
		// �����ӵ��߳�
		zidan.start();
		return zidan;
	}

}
