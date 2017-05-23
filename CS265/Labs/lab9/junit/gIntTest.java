/* JUnit 4 test suite for Calculator.java */

import org.junit.*;

/* this allows us to use assertion methods without typing 'Assert.' every time */
import static org.junit.Assert.*;

public class gIntTest {

    static gIntTest gInt;
	private gInt gInt1;
	private gInt gInt2;

    @BeforeClass
    public static void setUpClass() {
            // (Optional) Code to run before any tests begin goes here.
    }

    @AfterClass
    public static void tearDownClass() {
            // (Optional) Code to run after all tests finish goes here.
    }

    @Before
    public void setUp() {
            // (Optional) Code to run before each test case goes here.
	  gInt1= new gInt(1,2);
	  gInt2= new gInt(3,4);
    }

    @After
    public void tearDown() {
            // (Optional) Code to run after each test case goes here.
    }

    /* The @Test annotation tells JUnit that this method should be run as a test */
    @Test
    public void addTest(){
	  gInt expected = new gInt( 4, 6 );
	  gInt result = gInt1.add( gInt2 );
	  assertEquals( expected.r, result.r );
	  assertEquals( expected.i, result.i );
    }

    /* This test will fail! */
    @Test
    public void testMultiply(){
	  gInt expected= new gInt( -5, 10 );
	  gInt result = gInt1.multiply( gInt2 );
	  assertEquals( expected.r, result.r );
	  assertEquals( expected.i, result.i );
    }
	
	@Test
	public void testNorm(){
	  float expected = (float)2.236068;
	  float result = gInt1.norm();
	  assertEquals( expected, result, 0 );
	}
}
