public class ArrayReversor {

    public static void main(String[] args) {
        int result[] = (reverseArray(new int[][]{{1, 3}, {0}, {4, 5, 9}}));
        for (int a : result
        ) {
            System.out.print(a + ",");
        }
    }

    public static int[] reverseArray(int[][] arr) {
        if (arr == null) return new int[]{};
        int flattenedArray[] = ArrayFlattener.flattenArray(arr);
        int result[] = reverseArray(flattenedArray);
        return result;
    }

    private static int[] reverseArray(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;

    }
}
