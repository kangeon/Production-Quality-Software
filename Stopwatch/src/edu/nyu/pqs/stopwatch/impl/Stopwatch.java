package edu.nyu.pqs.stopwatch.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * {@inheritDoc}
 * 
 * @author Geon Kang
 * @version %I%, %G%
 */
public class Stopwatch implements IStopwatch {

  private final String id;
  private Boolean isRunning;
  private long startTime;
  private List<Long> lapTimes;
  private Object lock = new Object();
  
  private long getCurrentTime() {
    return System.currentTimeMillis();
  }
  
  private void addLap(Long lapStartTime) {
    long lapTime = getCurrentTime() - lapStartTime;
    lapTimes.add(lapTime);
    //update startTime for possible next lap
    startTime = getCurrentTime();
  }
  
  /**
   * Constructor used to create Stopwatch object.
   * 
   * @param id
   * @throws IllegalArgumentException
   *           if <code> id </code> is null. 
   */  
  public Stopwatch(String id) {
    if(id == null) {
      throw new IllegalArgumentException("ID cannot be null.");
    }
    else {
      this.id = id;
      this.isRunning = false;
      this.lapTimes = new ArrayList<Long>(); 
    }
  }
  
  @Override
  public String getId() {
    return this.id;
  }
  
  /**
   * {@inheritDoc}
   * In the case where the stopwatch is being restarted from a stop,
   * this method will continue timing of the lap that was stopped.
   */
  @Override
  public void start() {
    synchronized(lock) {
      if(isRunning == true) {
        throw new IllegalStateException("Stopwatch " + id + " is already "
            + "running.");
      }
      else {
        // CASE: start after stop (continue timing the most recent lap)
        if(lapTimes.size() != 0) {
          int mostRecentLapIndex = lapTimes.size() - 1;
          startTime = getCurrentTime() - lapTimes.get(mostRecentLapIndex);
          lapTimes.remove(mostRecentLapIndex);
        }
        else {
          startTime = getCurrentTime();
        }
        isRunning = true;
      }
    }
  }

  @Override
  public void lap() {
    synchronized(lock) {
      if(isRunning == false) {
        throw new IllegalStateException("Stopwatch " + id + " isn't running.");
      }
      else {
        addLap(startTime);
      }
    }
  }

  @Override
  public void stop() {
    synchronized(lock) {
      if(isRunning == false) {
        throw new IllegalStateException("Stopwatch " + id + " isn't running");
      }
      else {
        addLap(startTime);
        isRunning = false;  
      }
    }
  }

  @Override
  public void reset() {
    synchronized(lock) {
      lapTimes.clear();
      isRunning = false;
    }
  }

  @Override
  public List<Long> getLapTimes() {
    synchronized(lock){
      List<Long> immutableLapTimes = Collections.unmodifiableList(lapTimes);
      return immutableLapTimes; 
    }
  }
  
  /**
   * {@inheritDoc}
   * Two Stopwatch objects are considered equal if they have the same ID.
   * 
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Stopwatch)) {
      return false;
    }
    Stopwatch watchToCompare = (Stopwatch) o;
    return watchToCompare.id.equals(this.id); 
  }
  
  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + id.hashCode();
    return result;    
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: " + id);
    if(isRunning == true) {
      sb.append(" State: Running");
    }
    else {
      sb.append(" State: Not Running" + "\n");
    }
    for (int i = 0; i < lapTimes.size(); i++) {
      sb.append("Lap " + (i + 1) + ": " + lapTimes.get(i) + "\n");
    }
    return sb.toString();
  }
}
