import java.util.Arrays;

public class ArrayFlattener {
    public static void main(String[] args) {
        int result [] = (flattenArray(new int[][]{{1, 3}, {0}, {4, 5, 9}}));
        for (int a:result
             ) {
            System.out.print(a + ",");
        }
    }


    static int[] flattenArray(int arr[][]) {
        if(arr == null) return new int []{};
        int[] result = Arrays.stream(arr).flatMapToInt(Arrays::stream).toArray();
        return result;
    }
}