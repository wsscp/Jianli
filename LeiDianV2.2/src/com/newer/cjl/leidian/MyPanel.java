package com.newer.cjl.leidian;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 自定义一个面板类，继承JPanel，给面板添加一个背景图片
 * 
 * @author kowloon
 * 
 */

public class MyPanel extends JPanel {

	// 创建背景对象
	BgThread bt = new BgThread();
	// 创建一个定时器
	Timer t = new Timer();
	// 创建定时任务
	public static MyTask task = new MyTask();

	public static Player player = new Player();

	// 定义一个存放敌机子弹的动态数组
	public static ArrayList<ZiDanThread> zidanList = new ArrayList<ZiDanThread>();

	public static ArrayList<PlayerZiDanThread> playerZidanList = new ArrayList<PlayerZiDanThread>();

	int i = 0;

	// 重写构造方法，在构造方法中启动背景线程
	public MyPanel() {
		// 启动背景线程
		bt.start();

		// 开启定时任务,task会自动执行run方法
		t.schedule(task, 0, 1500);

		player.start();

		this.addMouseMotionListener(player);

	}

	// 重写绘制的方法
	// 当面板大小发生改变需要绘制的时候会自动调用此方法
	// paint方法是用来绘制面板本身以及面板上的数据
	public void paint(Graphics g) {
		// 绘制面板本身
		super.paint(g);

		// 绘制背景
		bt.draw(g);

		if (player.isLive) {
			player.draw(g);

			i++;
			if (i % 200 == 0) {
				PlayerZiDanThread playerZidan = player.fire();
				playerZidanList.add(playerZidan);
			}
		}
		// 绘制自己的子弹
		for (int i = 0; i < playerZidanList.size(); i++) {
			PlayerZiDanThread zidan = playerZidanList.get(i);
			// 判断子弹是否活着，活着就绘制，否则从动态数组中移除
			if (zidan.isLive) {
				zidan.draw(g);
			} else {
				playerZidanList.remove(i);
			}
		}

		// 从定时任务的动态数组中取出敌机并绘制
		for (int i = 0; i < task.dijiList.size(); i++) {
			// 取得一架敌机
			DiJiThread diji = task.dijiList.get(i);
			// 判断飞机是否活着，活着就绘制，否则就从动态数组中移除
			if (diji.isLive) {
				// 绘制
				diji.draw(g);
			} else {
				task.dijiList.remove(i);
			}

		}

		// 绘制敌机的子弹
		for (int i = 0; i < zidanList.size(); i++) {
			ZiDanThread zidan = zidanList.get(i);
			// 判断子弹是否活着，活着就绘制，否则从动态数组中移除
			if (zidan.isLive) {
				zidan.draw(g);
			} else {
				zidanList.remove(i);
			}
		}

		// 刷新面板
		repaint();
	}

}
