/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demodivisors;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ceaufres
 */
public class DivisorConc {

    public static long numOfPosDivisors(long num, int numThreads) {
        long count = 0;
        
        MyTask[] tasks = new MyTask[numThreads];
        
        tasks = createTask(tasks, generateLimits(num, numThreads), num);
        
        for (MyTask task : tasks) {
            task.start();
        }
        
        for (MyTask task : tasks) {
            try {
                task.getThread().join();
                count += task.getNumDivisors();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Implement here the code that allows obtaining the number of 
        // positive divisors of [num] distributing the task in [numThreads] 
        // of threads use: Divisor.numOfPosDivisors(num, num, num);
        //
        // Example: numOfPosDivisors(1000, 4); 
        // - Concurrent Task 1: count1 = Divisor.numOfPosDivisors(1000, 1, 250);
        // - Concurrent Task 2: count2 = Divisor.numOfPosDivisors(1000, 260, 500);
        // - Concurrent Task 3: count3 = Divisor.numOfPosDivisors(1000, 501, 750);
        // - Concurrent Task 4: count4 = Divisor.numOfPosDivisors(1000, 751, 1000);
        // total = count1 + count2 + count3 + count4; 
        
        
        
        return count;
    }
    
    
    public static Map<Long, Long> generateLimits(long num, int numThreads){

        long range = Math.round(num / numThreads);
        long init = 1;
        long last = range;
        Map<Long, Long> ranges = new HashMap<>();
        
        for (int task = 0; task < numThreads; task++) {            
            ranges.put(init, last);
            init = last + 1;
            last = init + range;
            if(last > num) last = num;
        }
        
        return ranges;
    }
    
    public static MyTask[] createTask(MyTask[] tasks, Map<Long, Long> ranges, long num){
        
        int i = 0;
        
        for (Map.Entry<Long, Long> entry : ranges.entrySet()) {
            Long key = entry.getKey();
            Long val = entry.getValue();
            tasks[i] = new MyTask(num, key, val, null);
            i += 1;
        }    
        return tasks;
    }
            
        
}
