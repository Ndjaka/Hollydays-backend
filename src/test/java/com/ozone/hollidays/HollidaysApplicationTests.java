package com.ozone.hollidays;

import com.ozone.hollidays.repositories.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class HollidaysApplicationTests {

	@Autowired
	//ImageRepository imageRepository;
	@Test
	void contextLoads() {
		//imageRepository.getAllByHollidays().forEach(System.out::println);
	}

}
