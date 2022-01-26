package com.pjankows.rest.webservices.restfulwebservices.filterin;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(getFilterForFields(Set.of("field1", "field2")));
		
		return mapping;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value12", "value22", "value32"));
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(getFilterForFields(Set.of("field2", "field3")));
		
		return mapping;
	}
	
	private FilterProvider getFilterForFields(Set<String> fields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		return filters;
	}
}
	