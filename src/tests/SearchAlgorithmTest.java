package tests;

import fhv.at.mwi.algorithm_tests.SearchAlgorithm;
import fhv.at.mwi.algorithm_tests.SortingAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class SearchAlgorithmTest {

    private SortingAlgorithm sortingTest = new SortingAlgorithm();
    private SearchAlgorithm searchTest = new SearchAlgorithm();

    // Test Arrays
    private int[] randomArray = {3, 6, 1, 2, 9, 4};
    private int[] sortedArrayAsc = {1, 2, 3, 4, 6, 9};
    private int[] sortedArrayDesc= {9, 6, 4, 3, 2, 1};
    private int[] expectedValues = {1, 2, 3, 4, 6, 9};



    @Test
    void selectionSort() {

        // Test 1
        int[] sortedValues = sortingTest.selectionSort(randomArray);
        Assertions.assertArrayEquals(expectedValues, sortedValues);

        // Additional information for test 1
        System.out.println("Random array: \t" + sortingTest.getAnalysis().toString());

        // Test 2
        sortedValues = sortingTest.selectionSort(sortedArrayAsc);
        Assertions.assertArrayEquals(expectedValues, sortedValues);

        // Additional information for test 2
        System.out.println("Best case: \t\t" + sortingTest.getAnalysis().toString());

        // Test 3
        sortedValues = sortingTest.selectionSort(sortedArrayDesc);
        Assertions.assertArrayEquals(expectedValues, sortedValues);

        // Additional information for test 3
        System.out.println("Worst case: \t" + sortingTest.getAnalysis().toString());
    }

    @Test
    void insertionSort() {

        // Test 1
        int[] sortedValues = sortingTest.insertionSort(randomArray);
        Assertions.assertArrayEquals(expectedValues, sortedValues);
        // Additional information for test 1
        System.out.println("Random array: \t" + sortingTest.getAnalysis().toString());

        // Test 2
        sortedValues = sortingTest.insertionSort(sortedArrayAsc);
        Assertions.assertArrayEquals(expectedValues, sortedValues);
        // Additional information for test 2
        System.out.println("Best case: \t\t" + sortingTest.getAnalysis().toString());

        // Test 3
        sortedValues = sortingTest.insertionSort(sortedArrayDesc);
        Assertions.assertArrayEquals(expectedValues, sortedValues);
        // Additional information for test 3
        System.out.println("Worst case: \t" + sortingTest.getAnalysis().toString());
    }

    @Test
    void binarySearch(){
        int[] someValues = {1, 2, 3, 4, 5, 6, 7};

        // Test 1
        int actualValue = searchTest.binarySearch(sortedArrayAsc, 3);
        int expectedValue = 2;
        Assertions.assertEquals(expectedValue, actualValue);

        // Test 2
        actualValue = searchTest.binarySearch(randomArray, 8);
        expectedValue = -1;
        Assertions.assertEquals(expectedValue, actualValue);
    }


}