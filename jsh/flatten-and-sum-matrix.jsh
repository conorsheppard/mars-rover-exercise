int[][] arr = {{1, 2}, {3, 4}};
Stream.of(arr).flatMapToInt(Arrays::stream).forEach(System.out::println);
var flattenedSum = Stream.of(arr).flatMapToInt(Arrays::stream).sum();
System.out.println(flattenedSum);