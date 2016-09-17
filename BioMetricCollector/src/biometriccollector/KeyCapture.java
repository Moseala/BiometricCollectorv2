/*
 * ‘******************************************************
 * ‘***  KeyCapture
 * ‘***  Author: Erik Clary
 * ‘******************************************************
 * ‘*** Purpose: This class captures the key input from the user.
 * ‘******************************************************
 * ‘*** October 14, 2015
 * ‘******************************************************
 * ‘*** Oct 14: Initial code written
 * ‘*** Oct 16: Added getTop10 method
 * ‘******************************************************
 * ‘*** Look at this!
 * ‘*** List all the places in the code where you did something interesting,
 * ‘*** clever or elegant.  If you did good work in this program and you want
 * ‘*** me to consider it in your grade, point it out here.
 * ‘*******************************************************
 */
package biometriccollector;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Erik
 */
public class KeyCapture implements KeyListener{
    private final int MINNUMACCEPTED = 100;
    private HashMap<Integer, Integer> keyCount = new HashMap<>();
    private ArrayList<KeyPress> keystrokesAL = new ArrayList<>();
    private long startTime, endTime = 0;
    private int every10 = 0;
    
    /*
    ‘******************************************************
    ‘***  keyTyped
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method calls updateAndOrganize on every key type.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    @Override
    public void keyTyped(KeyEvent ke) {
        updateAndOrganize();
    }

    /*
    ‘******************************************************
    ‘***  keyPressed
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method starts the timer when a key is depressed.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    @Override
    public void keyPressed(KeyEvent ke) {
        startTime = System.nanoTime();
    }

    /*
    ‘******************************************************
    ‘***  keyReleased
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method ends the timer when a key is released, and adds the time difference to the ArrayList.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    @Override
    public void keyReleased(KeyEvent ke) {
        endTime = System.nanoTime();
        keystrokesAL.add(new KeyPress(ke.getKeyCode(),(endTime-startTime)));
    }
    
    /*
    ‘******************************************************
    ‘***  updateAndOrganize
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method updates and organizes the keystrokes arraylist. This is done to make sure the keystrokes cant be re-created.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    private void updateAndOrganize(){
        if(++every10 >10){
            keystrokesAL.sort(null);
            for(KeyPress x : keystrokesAL){ //this loop goes over all entries in keystrokesAL (the user's inputs) and re-calculates the 
                Integer added = new Integer(keyCount.get(x.getUnicodeValue())+1);//literally: for every key pressed, add its unicode value to the hashmap (null returned from get), if found, add 1 to its hash value.
                keyCount.put(x.getUnicodeValue(), added);
            }
            every10 = 0;
        }
    }
    
    /*
    ‘******************************************************
    ‘***  testFinished
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method checks to see the the test has finished.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    ‘*** This methods has depreciated, testFinished inside of MainBMGUI is used instead.
    ‘******************************************************
    */
    private boolean testFinished(){
        int totalOverMIN = 0;
        Iterator<Map.Entry<Integer, Integer>> iMap = keyCount.entrySet().iterator();
        while(iMap.hasNext()){
            if(iMap.next().getValue()>=MINNUMACCEPTED)//i hope that works >> next returns each key?
                totalOverMIN++;
        } //code adapted from http://examples.javacodegeeks.com/core-java/util/hashmap/java-hashmap-example/
        return totalOverMIN >= 10;
    }
    
    /*
    ‘******************************************************
    ‘***  getTop10
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method returns an arraylist sorted by the number of keystrokes.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 16, 2015
    ‘******************************************************
    */
    public ArrayList getTop10(){
        ArrayList<KeyCountWrapper> top10  = new ArrayList<>();
        Iterator<Map.Entry<Integer, Integer>> iMap = keyCount.entrySet().iterator();
        while(iMap.hasNext()){
            Map.Entry<Integer, Integer> x = iMap.next();
            top10.add(new KeyCountWrapper(x.getKey(), x.getValue()));
        }
        top10.sort(null);
        return top10;
    }
    
}
