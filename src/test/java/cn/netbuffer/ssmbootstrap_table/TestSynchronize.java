package cn.netbuffer.ssmbootstrap_table;

import cn.netbuffer.ssmbootstrap_table.model.User;

public class TestSynchronize {
	
	public static void main(String[] args) {
		final User u=new User();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程:"+Thread.currentThread().getName()+"调用syn");
					//synchronized方法锁住u这个对象，其它的同步方法调用也同时被锁住
					u.syn();
				}
			}).start();
		}
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程:"+Thread.currentThread().getName()+"调用syn2");
					u.syn2();
				}
			}).start();
		}
		
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("线程:"+Thread.currentThread().getName()+"调用init");
					u.init();
				}
			}).start();
		}
	}
}
 