package com.newer.cjl.leidian;

/**
 * �л�������߳�
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
			// ����
			ZiDanThread zt = diji.fire();
			// ����Ҫ���ӵ����󱣴��ڶ�̬�����У�������ʾ����Ļ��
			MyPanel.zidanList.add(zt);

			try {
				Thread.sleep(800);
			} catch (Exception ef) {
				ef.printStackTrace();
			}

		}
	}
}
