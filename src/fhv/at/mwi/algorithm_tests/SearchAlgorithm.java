package fhv.at.mwi.algorithm_tests;

import java.util.Random;

public class SearchAlgorithm {

    private Random rnd = new Random();

    /**
     * Simple iterative binary search
     * @param values Sorted array with integer values
     * @param key Integer key to find in values
     * @return Array index of key in values
     */
    public int binarySearch(int[] values, int key){
        int start = 0, end = values.length - 1;
        int i = -1;
        while(i == -1 && start <= end){
            int mid = (start + end) / 2;
            if(values[mid] == key){
                i = mid;
            } else if(key < values[mid]){
                end = mid -1 ;
            } else if(key > values[mid]){
                start = mid + 1;
            }
        }
        return i;
    }

    /**
     * Search string by char starting from the left side of the string
     * @param searchStr String to be searched
     * @param key Character to be found in searchStr
     * @return Index of key in searchStr
     */
    public int findCharLeft(String searchStr, char key){
        int pos = -1;

        for(int i = 0; i < searchStr.length(); i++){
            if(searchStr.charAt(i) == key){
                pos = i;
                break;
            }
        }
        return pos;
    }

    /**
     * Search string starting from the right side of the string
     * @param searchStr String to be searched
     * @param key Character to be found in searchStr
     * @return Index of key in searchStr
     */
    public int findCharRight(String searchStr, char key){
        int pos = -1;

        for(int i = searchStr.length()-1; i >= 0; i--){
            if(searchStr.charAt(i) == key){
                pos = i;
                break;
            }
        }
        return pos;
    }

    /**
     * Find char starting at a random position
     * @param searchStr String to be searched
     * @param key Character to be found in searchStr
     * @return Index of key in searchStr
     */
    public int findCharRandom(String searchStr, char key){

        int rndPos = rnd.nextInt(searchStr.length()-1);
        int i = rndPos;
        System.out.println("rnd pos: " + rndPos);
        while(searchStr.charAt(i) != key){
            i++;
            i%=searchStr.length();
            if(i == rndPos){
                i = -1;
                break;
            }
        }
        return i;
    }


}
