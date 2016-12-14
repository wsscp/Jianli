package com.newer.cjl.leidian;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * 定时任务 TimerTask和Thread一样是并发执行的
 */
public class MyTask extends TimerTask {
	// 定义一个用来存放敌机对象的动态数组
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
		// 创建敌机线程对象并启动，还需要放入敌机数组
		// 参数this表示当前MyTask对象
		DiJiThread dj = new DiJiThread(num);
		dj.start();
		// 将敌机对象放入动态数组
		dijiList.add(dj);
		i++;
	}

}
