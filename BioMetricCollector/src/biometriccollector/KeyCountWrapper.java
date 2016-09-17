/*
 * ‘******************************************************
 * ‘***  KeyCountWrappers
 * ‘***  Author: Erik Clary
 * ‘******************************************************
 * ‘*** Purpose: This class is a wrapper for keyCount in order to use it well with hashmaps and arraylist.
 * ‘******************************************************
 * ‘*** October 16, 2015
 * ‘******************************************************
 * ‘*** Oct 16: Initial code written
 * ‘*** Oct 17: Added compareTo override.
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
public class KeyCountWrapper implements Comparable{
    private int unicode;
    private int count;
    
    /*
    ‘******************************************************
    ‘***  KeyCountWrapper
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method constructs the KeyCountWrapper object.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 16, 2015
    ‘******************************************************
    */
    public KeyCountWrapper(int unicode, int count){
        this.unicode = unicode;
        this.count = count;
    }

    /*
    ‘******************************************************
    ‘***  compareTo
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method overrides object's compareTo and returns the difference between the keyCount's count.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 17, 2015
    ‘******************************************************
    */
    @Override
    public int compareTo(Object t) {
        KeyCountWrapper s = (KeyCountWrapper) t;
        return s.count - this.count;
    }
    
    /*
    ‘******************************************************
    ‘***  getUnicode
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method returns the unicode value of the KeyCount.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 16, 2015
    ‘******************************************************
    */
    public int getUnicode(){
        return unicode;
    }
    
    /*
    ‘******************************************************
    ‘***  getCount
    ‘***  Author: Erik Clary
    ‘******************************************************
    ‘*** Purpose: This method returns the count value of the KeyCount.
    ‘*** Method Inputs:
    ‘*** N/A
    ‘*** Return value:
    ‘*** N/A
    ‘******************************************************
    ‘*** October 16, 2015
    ‘******************************************************
    */
    public int getCount(){
        return count;
    }
        
    
}
