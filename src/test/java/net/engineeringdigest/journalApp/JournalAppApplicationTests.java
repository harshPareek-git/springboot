//package net.engineeringdigest.journalApp;
//
//import net.engineeringdigest.journalApp.repository.UserRepository;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//class JournalAppApplicationTests {
//
//	@Autowired
//	UserRepository userRepository;
//
//	@Test
//	void contextLoads() {
//		assertNotNull(userRepository.findByUserName("root"));
//	}
//
//	@Disabled
//	@Test
//	void contextLoads1() {
//		assertNotNull(userRepository.findByUserName("root"));
//	}
//
//	@ParameterizedTest
//	@CsvSource({
//			"1,2,3",
//			"2,10,12",
//			"3,3,9"
//	})
//	public void test(int a,int b,int expected){
//		assertEquals(expected, a+b);
//	}
//
//	@ParameterizedTest
//	@CsvSource({
//			"1,2,3",
//			"2,10,12",
//			"3,3,9"
//	})
//	public void test1(int a,int b,int expected){
//		assertEquals(expected, a+b);
//	}
//
//}
