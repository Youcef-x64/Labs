import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertArrayEquals;

@RunWith(JUnit4.class)
public class ArrayFlattenerTestSuite {
    @Test
    public void testFlattenerArrayWithValidInput(){
        int input[][] = {{1,3},{0},{4,5,9}};
        int[] expectedOutput = {1,3,0,4,5,9};
        assertArrayEquals(ArrayFlattener.flattenArray(input),expectedOutput);
    }
    @Test
    public void testFlattenerArrayWithInvalidInput(){
        int [][] input = null;
        int[] expectedOutput = {};
        assertArrayEquals(ArrayFlattener.flattenArray(input),expectedOutput);

    }
}
