public class Main {

    public static void main(String[] args) {
        SearchAlgorithm test = new SearchAlgorithm();
        int[] someValues = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Bin. search: " + test.binarySearch(someValues, 3));

        System.out.println("left: " + test.findCharLeft("abcdefg", 'h'));
        System.out.println("right: " + test.findCharRight("abcdefg", 'h'));
        System.out.println("rnd: " + test.findCharRandom("abcdefg", 'c'));

        int[] unsortedValues = {3,6, 1, 2, 9, 4};
        int[] sortedValues = test.selectionSort(unsortedValues);
        for(int i = 0; i < sortedValues.length; i++){
            System.out.printf("%d, \n", sortedValues[i]);
        }

        String someText = "aaabcdddefffffffffffff333!!!";
        Compression textCompression = new Compression();
        String compressedText = textCompression.compress(someText);
        System.out.println("Compressed str: " + compressedText);
        System.out.println("Decompressed str: " + textCompression.decompress(compressedText));
    }


}
