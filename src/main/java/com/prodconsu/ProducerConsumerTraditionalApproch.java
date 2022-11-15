package com.prodconsu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerTraditionalApproch {

	public static void main(String[] args) throws InterruptedException {

		Problem problem = new Problem();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					problem.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					problem.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t2.start();
		t1.start();

	}
}

class Problem {

	LinkedList<Integer> list = new LinkedList();

	public synchronized void produce() throws InterruptedException {
		System.out.println("p..");
		for (int i = 0; i < 100; i++) {
			if (list.size() == 10) {
				notifyAll();
				wait();
			}
			list.add(i);
			System.out.println("Added : "+i);
		}
	}

	public synchronized void consume() throws InterruptedException {
		System.out.println("c..");
		int i=0;
		while (i<100) {
			
			Integer remove = list.removeFirst();
			System.out.println("Consumed : "+remove);
			if(list.size()==0) {
				System.out.println("c..c");
				notifyAll();
				wait();
			}
			i++;
		}
		
		

	}

}
