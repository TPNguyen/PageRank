package assignment3;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class MatrixTest {

	@Test
	public void checkDotVector() {
		double result = 0.0;
		result = Matrix.dotVector(Matrix.makeVector(5,0),Matrix.makeVector(5,0));
		assertEquals(new Double(0),new Double(result));
		result = Matrix.dotVector(Matrix.makeVector(5,1),Matrix.makeVector(5,1));
		assertEquals(new Double(5),new Double(result));
		result = Matrix.dotVector(Matrix.makeVector(5,1),Matrix.makeVector(5,2));
		assertEquals(new Double(10),new Double(result));
	}

}
