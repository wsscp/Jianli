package com.newer.cjl.leidian;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * �Զ���һ������࣬�̳�JPanel����������һ������ͼƬ
 * 
 * @author kowloon
 * 
 */

public class MyPanel extends JPanel {

	// ������������
	BgThread bt = new BgThread();
	// ����һ����ʱ��
	Timer t = new Timer();
	// ������ʱ����
	public static MyTask task = new MyTask();

	public static Player player = new Player();

	// ����һ����ŵл��ӵ��Ķ�̬����
	public static ArrayList<ZiDanThread> zidanList = new ArrayList<ZiDanThread>();

	public static ArrayList<PlayerZiDanThread> playerZidanList = new ArrayList<PlayerZiDanThread>();

	int i = 0;

	// ��д���췽�����ڹ��췽�������������߳�
	public MyPanel() {
		// ���������߳�
		bt.start();

		// ������ʱ����,task���Զ�ִ��run����
		t.schedule(task, 0, 1500);

		player.start();

		this.addMouseMotionListener(player);

	}

	// ��д���Ƶķ���
	// ������С�����ı���Ҫ���Ƶ�ʱ����Զ����ô˷���
	// paint����������������屾���Լ�����ϵ�����
	public void paint(Graphics g) {
		// ������屾��
		super.paint(g);

		// ���Ʊ���
		bt.draw(g);

		if (player.isLive) {
			player.draw(g);

			i++;
			if (i % 200 == 0) {
				PlayerZiDanThread playerZidan = player.fire();
				playerZidanList.add(playerZidan);
			}
		}
		// �����Լ����ӵ�
		for (int i = 0; i < playerZidanList.size(); i++) {
			PlayerZiDanThread zidan = playerZidanList.get(i);
			// �ж��ӵ��Ƿ���ţ����žͻ��ƣ�����Ӷ�̬�������Ƴ�
			if (zidan.isLive) {
				zidan.draw(g);
			} else {
				playerZidanList.remove(i);
			}
		}

		// �Ӷ�ʱ����Ķ�̬������ȡ���л�������
		for (int i = 0; i < task.dijiList.size(); i++) {
			// ȡ��һ�ܵл�
			DiJiThread diji = task.dijiList.get(i);
			// �жϷɻ��Ƿ���ţ����žͻ��ƣ�����ʹӶ�̬�������Ƴ�
			if (diji.isLive) {
				// ����
				diji.draw(g);
			} else {
				task.dijiList.remove(i);
			}

		}

		// ���Ƶл����ӵ�
		for (int i = 0; i < zidanList.size(); i++) {
			ZiDanThread zidan = zidanList.get(i);
			// �ж��ӵ��Ƿ���ţ����žͻ��ƣ�����Ӷ�̬�������Ƴ�
			if (zidan.isLive) {
				zidan.draw(g);
			} else {
				zidanList.remove(i);
			}
		}

		// ˢ�����
		repaint();
	}

}
