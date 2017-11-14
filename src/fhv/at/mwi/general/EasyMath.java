package fhv.at.mwi.general;

public class EasyMath {


    public EasyMath(){

    }

    /**
     * Simple power function for integer values
     * @param base Base
     * @param expo Exponent
     * @return base^expo
     */
    public static int power(int base, int expo){
        int result = 1;
        for(int i = 0; i < expo; i++){
            result *= base;
        }
        return result;
    }

    /**
     * Simple method to check if a number is a prime number.
     * @param num Integer number to check for prime property
     * @return true if num is a prime, else false
     */
    public static boolean isPrime(int num){
        if((num % 2) == 0 || (num % 3) == 0){
            return false;
        }
        for(int i = 5; i < Math.sqrt(num); i+=2){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}


