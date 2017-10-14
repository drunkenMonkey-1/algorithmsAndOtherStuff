package fhv.at.mwi.algorithm_tests;

public class SortingAlgorithm {
    private AlgorithmAnalysis analysis;

    public SortingAlgorithm(){
        analysis = new AlgorithmAnalysis(0);
    }

    /**
     * Sorting an int array in ascending order
     * @param input Input Array
     * @return Sorted array in ascending order
     */
    public int[] selectionSort(int[] input){
        analysis = new AlgorithmAnalysis(input.length);
        for(int i = 0; i < input.length-1; i++){
            int min = i;
            for(int j = i+1; j < input.length; j++){
                if(input[j] < input[min]){
                    min = j;
                }
                analysis.incrementComparisonOperations(1);
            }
            int temp = input[i];
            input[i] = input[min];
            input[min] = temp;
            analysis.incrementMemoryOperations(1);
        }
        return input;
    }

    /**
     * Insertion Sort for integer arrays
     * @param input Input int array
     * @return Sorted array in ascending order
     */
    public int[] insertionSort(int[] input){
        analysis = new AlgorithmAnalysis(input.length);
        for(int i = 1; i < input.length; i++){
            int unsortedElement = input[i];
            int j = i;
            while(j > 0 && input[j-1] > unsortedElement){
                input[j] = input[j-1];
                j--;
                analysis.incrementMemoryOperations(1);
            }
            analysis.incrementComparisonOperations(1);
            input[j] = unsortedElement;
        }
        return input;
    }




    public int[] quickSort(int[] values, int lb, int ub){
        partition(values, 0, values.length-1);

        if(lb > ub){
            //return
        }
        return null;

    }

    public int partition(int[] inputValues, int lowerBound, int upperBound){
        int len = inputValues.length;
        int pivot = inputValues[(lowerBound+upperBound) / 2];
        int foundElementLeft, foundElementRight;

        while(lowerBound < upperBound) {
            while (inputValues[lowerBound] < pivot) {
                lowerBound++;
            }
            while(inputValues[upperBound] > pivot){
                upperBound--;
            }
            if(lowerBound < upperBound){
                int temp = inputValues[lowerBound];
                inputValues[lowerBound] = inputValues[upperBound];
                inputValues[upperBound] = temp;
                lowerBound++;
                upperBound--;
            }
        }
        return lowerBound;
    }

    /**
     * Get number of operations from the last executed algorithm
     * @return fhv.at.mwi.algorithm_tests.AlgorithmAnalysis
     */
    public AlgorithmAnalysis getAnalysis() {
        return analysis;
    }

}
