private void print2dArrayRecursive(int[][] arr, int count) {
    if (arr.length == 0) {
        System.out.println("[]");
        return;
    }
    count++;
    if (count != arr.length) print2dArrayRecursive(arr, count);
    System.out.println(Arrays.toString(arr[count - 1]));
}

print2dArrayRecursive(new int[][]{{0, 0, 0, 0}, {1, 1, 1, 1}, {2, 2, 2, 2}, {3, 3, 3, 3}}, 0);