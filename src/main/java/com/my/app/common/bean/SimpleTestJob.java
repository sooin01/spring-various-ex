package com.my.app.common.bean;

import javax.annotation.PostConstruct;

@TestJob
public class SimpleTestJob {

	@PostConstruct
	public void init() {
		System.out.println("Simple test job init.");
	}

}
