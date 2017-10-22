package fhv.at.mwi.sorting_algorithms;

import fhv.at.mwi.compression.Compression;

public class Main {

    public static void main(String[] args) {


        String someText = "aaabcdddefffffffffffff333!!!";
        Compression textCompression = new Compression();
        String compressedText = textCompression.compress(someText);
        System.out.println("Compressed str: " + compressedText);
        System.out.println("Decompressed str: " + textCompression.decompress(compressedText));
    }


}
