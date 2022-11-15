package com.synchonization;

public class InterThreadCommunication {
	
	public static void main(String[] args) {
		
		Customer customer=new Customer();
		Runnable r1=new Runnable() {			
			@Override
			public void run() {
				customer.withdraw(1100);
			}
		};
		
		Runnable r2=new Runnable() {	
			@Override
			public void run() {
				customer.addAmount(100);
			}
		};
		
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r2);
		
		t1.start();
		t2.start();
		
		
	}

}

class Customer{
	
	int amount=1000;
	
	synchronized void withdraw(int amount) {
		
		if(this.amount<amount) {
			try {
				System.out.println("Entered amount is greater than actual soo wait for some time...");
				notify();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.amount=this.amount-amount;
			System.out.println("Remaining amount after withdraw is : "+this.amount);
		}
		
	}
	
	synchronized void addAmount(int amount){
		System.out.println("Amount is adding...");
		this.amount=this.amount+amount;
		notify();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Amount after deposite is : "+this.amount);
	}
	
	
	
}
