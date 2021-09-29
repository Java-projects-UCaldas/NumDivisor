/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demodivisors;

/**
 *
 * @author Lucas
 */
public class MyTask implements Runnable{

    private Thread theThread = null;
    private OnChange theDelegate = null;
    private long number = 0;
    private long from = 0;
    private long to = 0;
    private long numDivisors = 0;
    
    public MyTask(long newNumber, long newFrom, long newTo, OnChange newDelegate){
        theDelegate = newDelegate;
        theThread = new Thread(this);
        number = newNumber;
        from = newFrom;
        to = newTo;
    }
    
    public Thread start(){
        if(getThread() == null){
            theThread = new Thread(this);
            getThread().start();
        }
        getThread().start();
        
        return getThread();
    }

    /**
     * @return the theThread
     */
    public Thread getThread() {
        return theThread;
    }
    
        /**
     * @return the numDivisors
     */
    public long getNumDivisors() {
        return numDivisors;
    }

    /**
     * @param numDivisors the numDivisors to set
     */
    public void setNumDivisors(long numDivisors) {
        this.numDivisors = numDivisors;
    }    
    
    @Override
    public void run(){
        
        numDivisors = Divisor.numOfPosDivisors(number, from, to);
    }
    
    public interface OnChange{
        public abstract void show(int id, int value);
    }

}
