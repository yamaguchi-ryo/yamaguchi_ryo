/**
 *
 */
package fizzbuzz;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author お客様
 *
 */
public class FizzBuzzTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass//①
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass//⑧
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before//②⑤
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After//④⑦
	public void tearDown() throws Exception {
	}

	/**
	 * {@link fizzbuzz.FizzBuzz#fizzBuzzCheck(int)}
	 * のためのテスト・メソッド。(引数が0)
	 */
	@Test
	public void testFizzBuzzCheck_01() {
		System.out.println("testFizzBuzzCheck_01");
		assertEquals("FizzBuzz", FizzBuzz.fizzBuzzCheck(15));
	}

	@Test
	public void testFizzBuzzCheck_02() {
		System.out.println("testFizzBuzzCheck_02");
		assertEquals("Buzz", FizzBuzz.fizzBuzzCheck(5));
	}

	@Test
	public void testFizzBuzzCheck_03() {
		System.out.println("testFizzBuzzCheck_03");
		assertEquals("Fizz", FizzBuzz.fizzBuzzCheck(3));
	}

	@Test
	public void testFizzBuzzCheck_04() {
		System.out.println("testFizzBuzzCheck_04");
		assertEquals("FizzBuzz", FizzBuzz.fizzBuzzCheck(30));
	}

	@Test
	public void testFizzBuzzCheck_05() {
		System.out.println("testFizzBuzzCheck_05");
		assertEquals("Buzz", FizzBuzz.fizzBuzzCheck(10));
	}

	@Test
	public void testFizzBuzzCheck_06() {
		System.out.println("testFizzBuzzCheck_06");
		assertEquals("Fizz", FizzBuzz.fizzBuzzCheck(6));
	}

	@Test
	public void testFizzBuzzCheck_07() {
		System.out.println("testFizzBuzzCheck_07");
		assertEquals("4", FizzBuzz.fizzBuzzCheck(4));
	}

	@Test
	public void testFizzBuzzCheck_08() {
		System.out.println("testFizzBuzzCheck_08");
		assertEquals("8", FizzBuzz.fizzBuzzCheck(8));
	}
}
