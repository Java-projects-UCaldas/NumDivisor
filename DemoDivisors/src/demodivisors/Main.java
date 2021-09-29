/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demodivisors;

/**
 *
 * @author ceaufres
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int MAX_NUMBER_OF_TEST = 5;
        long num = 19_876_543_937L;
        long startTime = 0;
        long endTime = 0;
        long diffTime = 0;
        long sumTimes = 0;
        float avgTimes = 0;
        float avgTimeInMainProcess = 0;
        long numDivisors = 0;

        System.out.println("Number: " + num);
        System.out.println("[Main process]");

        sumTimes = 0;
        for (int k = 1; k <= MAX_NUMBER_OF_TEST; k++) {
            startTime = System.currentTimeMillis();
            numDivisors = Divisor.numOfPosDivisors(num);
            endTime = System.currentTimeMillis();

            diffTime = (endTime - startTime);
            sumTimes += diffTime;
            System.out.println("Time " + k + ": " + diffTime / 1000f);
        }
        avgTimeInMainProcess = (float) sumTimes / MAX_NUMBER_OF_TEST;
        System.out.println("Average time: " + avgTimeInMainProcess / 1000f);
        System.out.println("Number of divisors: " + numDivisors);
        System.out.println();

        for (int numThreads = 2; numThreads <= 8; numThreads++) {

            System.out.println("Number: " + num);
            System.out.println("[Threads = " + numThreads + "]");

            sumTimes = 0;
            for (int k = 1; k <= MAX_NUMBER_OF_TEST; k++) {
                startTime = System.currentTimeMillis();
                numDivisors = DivisorConc.numOfPosDivisors(num, numThreads);
                endTime = System.currentTimeMillis();

                diffTime = (endTime - startTime);
                sumTimes += diffTime;
                System.out.println("Time " + k + ": " + diffTime / 1000f);
            }
            avgTimes = (float) sumTimes / MAX_NUMBER_OF_TEST;
            System.out.println("Average time: " + avgTimes / 1000f);
            System.out.println("Expected average time: " + avgTimeInMainProcess / numThreads);
            System.out.println("Number of divisors: " + numDivisors);
            System.out.println();
        }
    }

}
