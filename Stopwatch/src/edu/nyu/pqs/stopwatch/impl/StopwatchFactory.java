package edu.nyu.pqs.stopwatch.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects.
 * It maintains references to all created IStopwatch objects and provides a
 * convenient method for getting a list of those objects.
 * Skeleton code provided by Professor Michael Schidlowsky at NYU.
 * 
 * @author Geon Kang
 * @version %I%, %G%
 */
public class StopwatchFactory {

  private static List<IStopwatch> stopwatchList = new ArrayList<IStopwatch>();
  private static Object lock = new Object();
  
  /**
   * Creates and returns a new IStopwatch object.
   * This method uses IStopwatch class' constructor and contains() of 
   * <code> stopwatchList </code> to check if a IStopwatch object with the 
   * specified ID already exists, so the constructor and equals() of the 
   * IStopwatch object class must be correctly implemented. 
   * 
   * @param id The identifier of the new object
   * @return The new IStopwatch object
   * @throws IllegalArgumentException
   *           if <code>id</code> is empty, null, or already taken.
   */
  public static IStopwatch getStopwatch(String id) {
    if(id == null) {
      throw new IllegalArgumentException("ID cannot be null.");
    }
    synchronized(lock) {
      Stopwatch newStopwatch = new Stopwatch(id);
      if(stopwatchList.contains(newStopwatch)) {
        throw new IllegalArgumentException("A stopwatch with that ID already"
            + " exists.");
      }
      else {
        stopwatchList.add(newStopwatch);
        return newStopwatch;
      }
    }
  }

  /**
   * Returns a list of all created stopwatches
   * 
   * @return a List of al creates IStopwatch objects. Returns an empty list if
   *         no IStopwatches have been created.
   */
  public static List<IStopwatch> getStopwatches() {
    List<IStopwatch> immutableStopwatchList = 
        Collections.unmodifiableList(stopwatchList);
    return immutableStopwatchList;
  }
}
