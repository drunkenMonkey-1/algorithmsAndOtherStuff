package fhv.at.mwi.algorithm_tests;

public class Main {

    public static void main(String[] args) {


        String someText = "aaabcdddefffffffffffff333!!!";
        Compression textCompression = new Compression();
        String compressedText = textCompression.compress(someText);
        System.out.println("Compressed str: " + compressedText);
        System.out.println("Decompressed str: " + textCompression.decompress(compressedText));
    }


}
