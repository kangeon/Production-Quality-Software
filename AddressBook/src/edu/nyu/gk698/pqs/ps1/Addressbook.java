package edu.nyu.gk698.pqs.ps1;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassCastException;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * The Addressbook class encapsulates a list of Entry objects and provides 
 * methods to manage stored Entries.
 * <p>
 * The list of Entries within an Addressbook object is implemented as an
 * ArrayList of Entry objects. 
 * 
 * @author Geon Kang
 * @version %I%, %G%
 */
public class Addressbook {
  /* referenced code from: 
   * http://hanoo.org/index.php?article=how-to-generate-logs-in-java
   * for logging
   */
  private final static Logger logger = Logger.getLogger(Addressbook.class
      .getName());
  private ArrayList<Entry> entryList = null;
  
  /**
   * Constructor used to create a new Addressbook object.
   */
  public Addressbook() {
    entryList = new ArrayList<Entry>();
  }

  /**
   * Adds a specified Entry object to this Addressbook's Entry list.
   * 
   * @param entryToAdd  Entry object to be added
   */
  public void addEntry(Entry entryToAdd) {
    entryList.add(entryToAdd);
  }
  
  /**
   * Removes an Entry at the specified index from 
   * this Addressbook's Entry list.
   * 
   * @param i  the index of the Entry to be removed
   * @throws IndexOutofBoundsException  if specified index is not within the
   *                                    bounds of the Entry list
   */
  public void removeEntry(int i) {
	try {
	  entryList.remove(i);	
	} catch (IndexOutOfBoundsException e) {
		logger.log(Level.WARNING, "Error removing entry", e);
	}
  }
  
  /**
   * Searches each Entry in the Entry list and checks if any field of the Entry
   * matches the specified <code>String</code>. This method will return all
   * Entries that has at least one field that matches the String as a list of
   * SearchResult objects. 
   * <p>
   * Uses the contains method provided in the Entry class.
   * 
   * @param criteria <code>String</code> to be searched for
   * @return an <code>ArrayList</code> of SearchResult objects consisting
   *         of Entries that have at least one field that matches the search
   *         criteria
   * @see SearchResult
   */
  public ArrayList<SearchResult> searchEntry(String criteria) {
    ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();
    for (int i = 0; i < entryList.size(); i++) {
      if (entryList.get(i).contains(criteria)) {
        SearchResult searchHit = new SearchResult(i, entryList.get(i));
        searchResults.add(searchHit);
        searchHit = null;
      }
    }
    return searchResults;
  }

  /**
   * Saves the Entry list of this Addressbook object as an XML file 
   * at the specified file path.
   * <p>
   * Uses xstream to serialize the Entry list into XML format.
   * 
   * @param filePath  file path where the XML file will be saved
   * @throws IOException  if specified file path is invalid
   * @see com.thoughtworks.xstream.XStream
   * @see com.thoughtworks.xstream.fromXML
   */
  public void saveAddressBookToFile(String filePath) {
    try {
      File saveFile = new File(filePath);
      if (!saveFile.exists()) {
        saveFile.createNewFile();
      }
      /* referenced code from:
       * http://xstream.codehaus.org/tutorial.html
       * for using xstream to read and write objects to and from xml files.
       */
      XStream xstream = new XStream(new DomDriver());
      FileWriter fwriter = new FileWriter(saveFile);
      BufferedWriter bwriter = new BufferedWriter(fwriter);
      bwriter.write(xstream.toXML(entryList));
      bwriter.close();
      fwriter.close();
    } catch (IOException e) {
      logger.log(Level.WARNING, "Error saving to XML File", e);
    }
  }
  
  /**
   * Reads an Entry list from an XML file at the specified file path.
   * <p>
   * Uses xstream to deserialize from XML format to object.
   * The object returned from <code>xstream.fromXML</code> is explicitly cast
   * into an <code>ArrayList</code> of Entry objects. 
   * This may result in an unchecked cast warning, which is 
   * explicitly suppressed. In case of erroneous casts, the method catches
   * <code>ClassCastException</code>.
   * 
   * @param filePath file path where the XML file to be read from is located
   * @throws ClassCastException  if the object returned from 
   *                             <code>xstream.fromXML</code>
   *                             cannot be cast to an <code>ArrayList</code>
   *                             of Entry objects
   * @throws FileNotFoundException  if the specified file does not exist
   * @see com.thoughtworks.xstream.Xstream
   * @see com.thoughtworks.xstream.toXML
   */
  @SuppressWarnings("unchecked")
  public void readAddressBookFromFile(String filePath) {
    XStream xstream = new XStream(new DomDriver());
    try {
      entryList = (ArrayList<Entry>) xstream.fromXML(new FileReader(filePath));
    } catch (FileNotFoundException | ClassCastException e) {
      logger.log(Level.WARNING, "Error reading from XML file", e);
    }
  }
  
  /**
   * Returns every Entry object in this Addressbook's Entry list 
   * as a single <code>String</code>.
   * <p>
   * The <code>String</code> consists of the index of the Entry object in the 
   * list, followed by each field of the Entry object as 
   * given by the Entry class' toString method. The index number and each field
   * is separated with a tab. Each Entry is followed by a newline.
   * <code>StringBuilder</code> is used for efficiency.
   * Overrides default toString method.
   * 
   * @return  the index followed by the fields of each Entry object, 
   *          with the index and each field separated by a tab, and
   *          a newline following each Entry, as a single <code>String</code>.  
   * @see Entry#toString()
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < entryList.size(); i++) {
      sb.append(i + "\t" + entryList.get(i).toString() + "\n");
    }
    return sb.toString();
  }
}
