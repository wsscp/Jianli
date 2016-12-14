package com.newer.cjl.leidian;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player extends Thread implements MouseMotionListener {

	ArrayList<Image> playerImg = new ArrayList<Image>();
	int x, y;
	int width, height;
	boolean isLive = true;
	boolean canMove = false;
	public Rectangle rect = new Rectangle();

	Image currentImg = new ImageIcon("img/player1.png").getImage();
	int i = 0;

	public Player() {
		for (int i = 1; i <= 10; i++) {
			ImageIcon icon = new ImageIcon("img/player" + i + ".png");
			playerImg.add(icon.getImage());
			if (width == 0) {
				width = icon.getIconWidth() / 2;
				height = icon.getIconHeight() / 2;
			}
		}

		x = (450 - width) / 2;
		y = 700 - height - 100;
		rect.x = x;
		rect.y = y;
		rect.width = width;
		rect.height = height;
	}

	public void run() {
		while (isLive) {
			currentImg = playerImg.get(i);
			i++;
			if (i == playerImg.size()) {
				i = 0;
			}
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		currentImg = playerImg.get(0);
	}

	public void draw(Graphics g) {
		g.drawImage(currentImg, x, y, width, height, null);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x0 = e.getX();
		int y0 = e.getY();
		if (x0 > x && x0 < x + width && y0 > y && y0 < y + height) {
			canMove = true;
		} else {
			canMove = false;
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (canMove) {
			x = e.getX() - width / 2;
			y = e.getY() - height / 2;
			rect.x = x;
			rect.y = y;
		}
	}

	// 飞机开火的方法
	public PlayerZiDanThread fire() {
		// 创建子弹线程
		PlayerZiDanThread zidan = new PlayerZiDanThread(x + width / 2, y);
		// 启动子弹线程
		zidan.start();
		return zidan;
	}

}
