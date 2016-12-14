package com.newer.cjl.leidian;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * 敌机的线程类
 * 
 * @author kowloon
 * 
 */
public class DiJiThread extends Thread {

	private Image img;// 敌机图片
	private int x, y;// 左上角坐标
	private int width, height;// 宽度和高度
	public boolean isLive = true;// 飞机是不是活着
	public int type=0;//飞机类型0 小飞机  1中飞机  2BOSS
	
	public Rectangle rect = new Rectangle();

	// 在构造方法中确定敌机图片和位置
	public DiJiThread(int num) {
		// 随机一个1~10之间的任意数字，表示敌机
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
		// 开火
		FireThread ft = new FireThread(this);
		ft.start();

		while (isLive) {
			y++;
			rect.y = y;
			// 当飞机离开游戏区域，就没有含义，所以失去生命
			if (y >= 700) {
				isLive = false;
			}

			// 休眠100ms
			try {
				Thread.sleep(20);
			} catch (Exception ef) {
				ef.printStackTrace();
			}

		}
	}

	// 绘制背景的方法
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

	// 飞机开火的方法
	public ZiDanThread fire() {
		// 创建子弹线程
		ZiDanThread zidan = new ZiDanThread(x + width / 2, y + height);
		// 启动子弹线程
		zidan.start();
		return zidan;
	}

}
