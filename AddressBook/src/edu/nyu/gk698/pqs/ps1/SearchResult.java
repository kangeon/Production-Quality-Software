package edu.nyu.gk698.pqs.ps1;

/**
 * SearchResult is a class that contains an Entry object and 
 * the Entry object's index in the container it was searched in.
 * <p>
 * The index information is provided to facilitate identifying and removing
 * Entry objects from the actual container. The index field is initialized 
 * to -1 rather than 0 to prevent unintended references to the container's 
 * zero index.
 * @author Geon Kang
 * @version %I%, %G%
 */
public class SearchResult {
  private int resultIndex = -1;
  private Entry resultEntry = null;
  
  /**
   * Constructs SearchResult object given an index and 
   * <code>Entry</code> object. 
   * <p>
   * The index <code>int</code> value must be within the bounds of the
   * container that is being searched.
   * 
   * @param resultIndex  index that the Entry object was found in the 
   *                     searched container.
   * @param resultEntry  Entry object that was found in the searched container
   *                     at index <code>resultIndex</code>.
   */
  public SearchResult(int resultIndex, Entry resultEntry) {
    this.resultIndex = resultIndex;
    this.resultEntry = resultEntry;
  }
  
  /**
   * Returns the value of <code>resultIndex</code> and the fields of the
   * <code>Entry</code> object <code>resultEntry</code> as a single 
   * <code>String</code>. Each field is separated by a tab within the 
   * <code>String</code>.
   * <p>
   * The <code>resultIndex</code> is concatenated with the <code>String</code>
   * returned by <code>Entry</code> class' toString method called by 
   * <code>resultEntry</code>.  
   * Overrides default toString method.
   * 
   * @return  the values of <code>resultIndex</code> and the fields of 
   *          <code>resultEntry</code>, each separated by a tab, 
   *          as a single <code>String</code>.
   * @see Entry#toString()  
   */
  @Override public String toString(){
    return resultIndex + "\t" + resultEntry.toString();
  }
}
