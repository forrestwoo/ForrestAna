package com.forrest.utils;

import static java.lang.System.out;

import java.util.List;

public class IPThread extends Thread {
	private List<String> urls;
	private IPPool ipPool;

	public IPThread(List<String> urls, IPPool ipPool) {
		this.urls = urls;
		this.ipPool = ipPool;
	}

	@Override
	public void run() {
		// ����ip��ץȡ
		for (String url : urls) {
			out.println(Thread.currentThread().getName() + "��ȡ�ĵ�ַΪ��" + url);
		}
		ipPool.getIP(urls);
	}
}
