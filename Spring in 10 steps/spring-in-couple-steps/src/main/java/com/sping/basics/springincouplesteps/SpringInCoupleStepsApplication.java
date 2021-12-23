package com.sping.basics.springincouplesteps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringInCoupleStepsApplication {

	public static void main(String[] args) {
		//BinarySearchImpl binarySearch = 
		//new BinarySearchImpl(new BubbleSortAlgorithm());
		
		ApplicationContext applicationContext = 
				SpringApplication.run(SpringInCoupleStepsApplication.class, args);
		BinarySearchImpl binarySearch = applicationContext.getBean(BinarySearchImpl.class);
		int result = 
				binarySearch.binarySearch(new int[] { 12, 4, 6 }, 3);
		System.out.println(result);
	}
}
