package fhv.at.mwi.search_tree;

public class Decoder {

    public Decoder(){

    }

    /**
     * Simply decode a binary string by a given tree strucutre (Huffman like)
     * @param decodingTree Root node of your binary tree
     * @param bits Encoded bits as string
     * @return Decoded bits as string
     */
    public String decode(BinaryTree<Character> decodingTree, String bits){
        StringBuilder resultStr = new StringBuilder();
        int bitIndex = 0;
        for(int i = 0; i < bits.length(); i+=(bitIndex-i)) {
            bitIndex = i;
            BinaryTree<Character> root = decodingTree;
            while (root.getValue() == '+' && bitIndex < bits.length()) {
                if (bits.charAt(bitIndex) == '0') {
                    root = root.getRight();
                }
                if (bits.charAt(bitIndex) == '1') {
                    root = root.getLeft();
                }
                bitIndex++;
            }
            if (root != null) {
                resultStr.append(root.getValue());
            }
        }
        return resultStr.toString();
    }


}
