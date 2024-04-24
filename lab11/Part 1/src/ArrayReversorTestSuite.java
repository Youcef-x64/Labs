import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class ArrayReversorTestSuite {
    @Test
    public void testReverseArrayWithValidInput(){
        int input[][] = {{1,3},{0},{4,5,9}};
        int[] expectedOutput = {9,5,4,0,3,1};
        mock(ArrayFlattener.class);
        when(ArrayFlattener.flattenArray(input).thenReturn(new int[]{1,3,0,4,5,9});
        assertArrayEquals(ArrayReversor.reverseArray(input),expectedOutput);
    }
    @Test
    public void testReverseArrayWithInvalidInput(){
        int [][] input = null;
        int[] expectedOutput = {};
        ArrayFlattener arrayFlattenerService = mock(ArrayFlattener.class);
        when(arrayFlattenerService.flattenArray(null).thenReturn(expectedOutput);
        assertArrayEquals(ArrayReversor.reverseArray(input),expectedOutput);
        verify(arrayFlattenerService).flattenArray(null);

    }
}
