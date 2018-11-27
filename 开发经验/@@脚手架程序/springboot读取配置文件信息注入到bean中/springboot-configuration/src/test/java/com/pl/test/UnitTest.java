package com.pl.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pl.util.ConfigurationUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {

	@Autowired
	private ConfigurationUtil configurationUtil;

	@Test
	public void test() {
		System.out.println(configurationUtil);
	}

	@Test
	public void testMapPrint() {
		Map<String, List<String>> map = configurationUtil.getMapList();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			System.out.println(entry.getKey());
			for (String item : entry.getValue()) {
				System.out.println("  " + item);
			}
		}
	}
}
