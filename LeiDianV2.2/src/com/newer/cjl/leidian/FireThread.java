package com.newer.cjl.leidian;

/**
 * 敌机开火的线程
 * 
 * @author kowloon
 * 
 */
public class FireThread extends Thread {

	DiJiThread diji;

	public FireThread(DiJiThread dj) {
		diji = dj;
	}

	public void run() {
		try {
			Thread.sleep(2000);
		} catch (Exception ef) {
			ef.printStackTrace();
		}
		while (diji.isLive) {
			// 开火
			ZiDanThread zt = diji.fire();
			// 必须要将子弹对象保存在动态数组中，才能显示在屏幕上
			MyPanel.zidanList.add(zt);

			try {
				Thread.sleep(800);
			} catch (Exception ef) {
				ef.printStackTrace();
			}

		}
	}
}
