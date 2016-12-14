package com.newer.cjl.leidian;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * ��ҵ��ӵ��߳� 
 */
public class PlayerZiDanThread extends Thread {

	private Image img;
	private int x, y;
	private int width, height;
	public boolean isLive = true;// �ӵ��Ƿ����
	public Rectangle rect = new Rectangle();

	public PlayerZiDanThread(int a, int b) {
		ImageIcon icon = new ImageIcon("img/bullet01.png");
		img = icon.getImage();

		width = icon.getIconWidth() / 4;
		height = icon.getIconHeight() / 4;

		x = a - width / 2;
		y = b - height;

		rect.x = x;
		rect.y = y;
		rect.width = width;
		rect.height = height;

	}

	public void run() {
		while (isLive) {
			y -= 10;
			rect.y = y;
			pengzhuang();
			// ���ӵ��뿪��Ϸ���򣬾�û�к��壬����Ҫkill��
			if (y <= 0) {
				isLive = false;
			}
			// ����2ms
			try {
				Thread.sleep(10);
			} catch (Exception ef) {
				ef.printStackTrace();
			}

		}
	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

	public void pengzhuang() {
		for (int i = 0; i < MyPanel.task.dijiList.size(); i++) {
			DiJiThread diji = MyPanel.task.dijiList.get(i);
			if (diji.rect.intersects(rect)) {
				diji.isLive = false;
				isLive = false;
			}
		}

	}

}
