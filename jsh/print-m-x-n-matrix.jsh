var arr = new int[Integer.parseInt(args[0])][Integer.parseInt(args[1])];
Stream.of(arr).map(Arrays::toString).forEach(System.out::println);
//System.out.println(Arrays.deepToString(arr).replace("[[", "[").replace("]]", "]").replace("], [", "]\n["));