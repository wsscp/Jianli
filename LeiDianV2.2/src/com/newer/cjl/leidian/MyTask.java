package com.newer.cjl.leidian;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * ��ʱ���� TimerTask��Threadһ���ǲ���ִ�е�
 */
public class MyTask extends TimerTask {
	// ����һ��������ŵл�����Ķ�̬����
	public ArrayList<DiJiThread> dijiList = new ArrayList<DiJiThread>();
	int i = 1;
	int num = 0;

	public void run() {
		num = 0;
		if (i % 5 == 0 || i % 6 == 0) {
			num = 1;
		}
		if (i % 20 == 0) {
			num = 2;
		}
		// �����л��̶߳�������������Ҫ����л�����
		// ����this��ʾ��ǰMyTask����
		DiJiThread dj = new DiJiThread(num);
		dj.start();
		// ���л�������붯̬����
		dijiList.add(dj);
		i++;
	}

}
