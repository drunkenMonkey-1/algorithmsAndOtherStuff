package fhv.at.mwi.algorithm_tests;

public class Compression {

    public Compression(){

    }

    /**
     * Used to encode numbers and repeated chars
     * Numbers as input char will be encoded to Hex 0x00 - 0x09
     * @param in Input char to be encoded
     * @param num How often the char is repeated
     * @return Encoded array of chars
     */
    private char[] encode(char in, int num){
        String numAsChar = "";
        char[] out = new char[1];
        if(num > 0) {
            numAsChar = String.valueOf(num);
            out = new char[numAsChar.length() + 1];
        }

        char outChar = in;
        if(in <= '9' && in >= '0'){
            outChar = (char)((int) in - 48);
        }
        out[0] = outChar;
        for(int i = 1; i < out.length; i++) {
            out[i] = numAsChar.charAt(i-1);
        }
        return out;
    }

    /**
     * For compressing a text string containing numbers, signs and chars
     * @param input Input String to be compressed
     * @return Compressed String
     */
    public String compress(String input){
        StringBuilder compressedString = new StringBuilder();
        int numberOfSameChars = 0;
        int strLen = input.length();

        for(int i = 0; i < strLen; i++){

            if(i < (strLen-1) && input.charAt(i) == input.charAt(i+1)){
                numberOfSameChars++;
            } else {
                char[] encodedChars = encode(input.charAt(i), numberOfSameChars);
                for (int j = 0; j < encodedChars.length; j++) {
                    compressedString.append(encodedChars[j]);
                }
                numberOfSameChars = 0;
            }
        }
        return compressedString.toString();
    }

    /**
     * Check if a char is Number in ASCII
     * @param asciiChar Input char
     * @return true if the char is a number
     */
    private boolean isAsciiNum(char asciiChar){
        if(asciiChar <= '9' && asciiChar >= '0'){
            return true;
        }
        return false;
    }

    /**
     * Decode numbers for decompression
     * @param input input char to be decoded
     * @return Decoded char
     */
    private char decodeNumbers(char input){
        if(input <= 9){
            return ((char)((int)input + 48));
        }
        return input;
    }

    /**
     * Decompress a string previously compressed with the function in this class
     * @param input Input compressed string
     * @return Decompressed text
     */
    public String decompress(String input){
        StringBuilder decompressedString = new StringBuilder();
        String numOfChars = "";
        int strLen = input.length();

        for(int i = 0; i < strLen; i++){
            if(i < (strLen-1) && isAsciiNum(input.charAt(i+1))) {
                numOfChars += input.charAt(i+1);
            } else {
                int intNumOfChars;
                if(numOfChars.isEmpty()){
                    intNumOfChars = 0;
                } else {
                    intNumOfChars = Integer.parseInt(numOfChars);
                }
                for(int j = 0; j < intNumOfChars+1; j++){
                    decompressedString.append(decodeNumbers(input.charAt(i-numOfChars.length())));
                }
                numOfChars = "";
            }
        }
        return decompressedString.toString();
    }


}
