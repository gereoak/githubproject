package com.javatechie.spring.soap.api.bean;

import org.springframework.stereotype.Component;

//@Component
public class LimitConfiguraion {

	
	private int minimun;
	private int maximum;
	
	public LimitConfiguraion() {
		
	}
	
	public LimitConfiguraion(int minimun, int maximum) {
		this.minimun = minimun;
		this.maximum = maximum;
	}
	public int getMinimun() {
		return minimun;
	}
	public void setMinimun(int minimun) {
		this.minimun = minimun;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
	
}
