package com.newer.cjl.leidian;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 背景类，继承Thread类
 * 
 * @author kowloon
 * 
 */
public class BgThread extends Thread {

	// 创建一个图标对象
	private ImageIcon icon = new ImageIcon("img/bg01.jpg");
	// 将图标转换成图片对象
	private Image img = icon.getImage();
	private Image img2 = icon.getImage();

	private int x = 0, y = 0;
	private int x2 = 0, y2 = -700;

	// 重写run方法：线程的入口函数
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

			// 休眠100ms
			try {
				Thread.sleep(100);
			} catch (Exception ef) {
				ef.printStackTrace();
			}
		}
	}

	// 绘制背景的方法
	public void draw(Graphics g) {
		// 将图片绘制出来
		g.drawImage(img, x, y, 450, 700, null);
		g.drawImage(img2, x2, y2, 450, 700, null);
	}

}
