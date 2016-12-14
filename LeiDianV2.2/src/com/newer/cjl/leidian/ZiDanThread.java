package com.newer.cjl.leidian;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * 敌机的子弹线程
 * 
 * @author kowloon
 * 
 */
public class ZiDanThread extends Thread {

	private Image img;
	private int x, y;
	private int width, height;
	public boolean isLive = true;// 子弹是否活着
	public Rectangle rect = new Rectangle();

	public ZiDanThread(int a, int b) {
		ImageIcon icon = new ImageIcon("img/bullet01.png");
		img = icon.getImage();

		width = icon.getIconWidth() / 4;
		height = icon.getIconHeight() / 4;

		x = a - width / 2;
		y = b;
		rect.x = x;
		rect.y = y;
		rect.width = width;
		rect.height = height;
	}

	public void run() {
		while (isLive) {
			y += 10;
			rect.y = y;
			pengzhuang();
			// 当子弹离开游戏区域，就没有含义，所以要kill掉
			if (y >= 700) {
				isLive = false;
			}
			// 休眠2ms
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
		if (MyPanel.player.rect.intersects(rect)) {
			MyPanel.player.isLive = false;
			isLive = false;
		}

	}

}
