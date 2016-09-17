/*
 * ‘******************************************************
 * ‘***  KeyPress
 * ‘***  Author: Erik Clary
 * ‘******************************************************
 * ‘*** Purpose: This class captures the key input from the user.
 * ‘******************************************************
 * ‘*** October 14, 2015
 * ‘******************************************************
 * ‘*** Oct 14: Initial code written
 * ‘*** Oct 16: Added compareTo override
 * ‘******************************************************
 * ‘*** Look at this!
 * ‘*** List all the places in the code where you did something interesting,
 * ‘*** clever or elegant.  If you did good work in this program and you want
 * ‘*** me to consider it in your grade, point it out here.
 * ‘*******************************************************
 */
package biometriccollector;

/**
 *
 * @author Erik
 */
class KeyPress implements Comparable{
    private int unicodeValue;
    private float time;
        
    /*
    ‘******************************************************
    ‘***  KeyPress
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method constructs the keyPress object.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    public KeyPress(){
        unicodeValue = 0;
        time = 0;
    }
    
    /*
    ‘******************************************************
    ‘***  KeyPress
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method constructs the keyPress object, has unicode and time attributes.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    public KeyPress(int unicode, float time){
        this.time = time;
        unicodeValue = unicode;
    }
    
    /*
    ‘******************************************************
    ‘***  KeyPress
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method overrides object's toString and reutrns the keypress' unicode value and time (in nanoseconds) separated by '.' and terminated with '*'.
    ‘***          ex: 65,1.6066984E7*
    ‘***          In this example, 65 is the objects unicode value, followed by
    ‘***          , the separator between the unicode and time value
    ‘***          and finally the time the key was held down in nanoseconds.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    @Override
    public String toString(){
        return "" + unicodeValue + "," + time + "*";
    }
    
    /*
    ‘******************************************************
    ‘***  compareTo
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method compares a keypress' unicodevalue to another.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    public int compareTo(KeyPress other){
        return unicodeValue - other.unicodeValue;
    }

    /*
    ‘******************************************************
    ‘***  compareTo
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method overrides object's compareTo, functionally the same as keypress compareTo.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 16, 2015
    ‘******************************************************
    */
    @Override
    public int compareTo(Object t) {
        KeyPress s = (KeyPress) t; //this method assumes given object t is a KeyPress, should only be used in ArrayList's sort method. Required to implement comparable.
        return  s.unicodeValue - this.unicodeValue;
    }
    
    /*
    ‘******************************************************
    ‘***  getUnicodeValue
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method returns the keypress' unicodevalue.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 14, 2015
    ‘******************************************************
    */
    public int getUnicodeValue(){
        return unicodeValue;
    }
}
