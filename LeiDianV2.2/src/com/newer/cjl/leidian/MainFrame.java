package com.newer.cjl.leidian;

import javax.swing.JFrame;

/**
 * 主窗口类
 * 
 * @author kowloon
 * 
 */
public class MainFrame {

	public static void main(String[] args) {

		JFrame jf = new JFrame();
		jf.setTitle("雷电");
		jf.setSize(450, 700);
		// 参数必须是 0：什么都不做
		// 1：隐藏，窗口对象保存在内存中，默认是1
		// 2：隐藏并从内存移除窗口对象
		// 3：退出整个程序
		jf.setDefaultCloseOperation(3);
		// 添加一个面板
		MyPanel gamePanel = new MyPanel();
		// 设置面板的背景颜色
//		gamePanel.setBackground(new Color(255, 0, 0));
		jf.add(gamePanel);
		jf.setVisible(true);

		

	}
}
