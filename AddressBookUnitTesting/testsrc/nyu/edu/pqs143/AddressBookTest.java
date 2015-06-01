package nyu.edu.pqs143;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Before;

/**
 * junit tests for AddressBook library
 * 
 * @author Geon Kang
 * @version %I%, %G%
 */
public class AddressBookTest {
  AddressBook testAddressBook;
  
  /* Because equals() is not overloaded for the Entry class,
  there is no direct way of checking if Entry contents are equal.
  This helper method provides a way to compare the equality of two Entries 
  based on their fields for more intuitive testing.
  */
  private boolean entriesEqual(Entry e1, Entry e2) {
    return e1.getName().equals(e2.getName()) && 
        e1.getAddress().equals(e2.getAddress()) &&
        e1.getPhone().equals(e2.getPhone()) &&
        e1.getEmail().equals(e2.getEmail()) &&
        e1.getNote().equals(e2.getNote());
  }
  
  @Before
  public void setup() {
    testAddressBook = AddressBook.createEmptyAddressBook();
  }
  
  @Test
  public void testAddEntry_addAsEntry() {
    Entry testEntry = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    testAddressBook.addEntry(testEntry);
    List<Entry> entryList = testAddressBook.getEntries();
    Entry addedEntry = entryList.get(0);
    Entry expectedEntry = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    assertTrue(entriesEqual(addedEntry, expectedEntry));
  }

  @Test
  public void testAddEntry_addMultipleEntries() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> entryList = testAddressBook.getEntries();
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry1);
    expected.add(testEntry2);
    expected.add(testEntry3);
    assertEquals(entryList, expected);
  }
  
  
  @Test
  public void testAddEntry_addEntryAsStringWithCommaDelimiter() {
    String testEntryAsString = "Dan,1 Main St.,123456,dan123@gmail.com,"
        + "Dan from school";
    testAddressBook.addEntry(testEntryAsString);
    List<Entry> entryList = testAddressBook.getEntries();
    Entry addedEntry = entryList.get(0);
    Entry expectedEntry = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    assertTrue(entriesEqual(addedEntry, expectedEntry));
  }

  @Test
  public void testAddEntry_AddEntryAsStringWithDifferentDelimiter() {
    String testEntryAsString = "Dan%1 Main St.%123456%dan123@gmail.com%"
        + "Dan from school";
    testAddressBook.addEntry(testEntryAsString, "%");
    List<Entry> entryList = testAddressBook.getEntries();
    Entry addedEntry = entryList.get(0);
    Entry expectedEntry = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    assertTrue(entriesEqual(addedEntry, expectedEntry));
  }

  //ERROR: Does not check for null strings. NullPointerException Thrown
  @Test
  public void testAddEntry_nullString() {
    String testEntryAsString = null;
    testAddressBook.addEntry(testEntryAsString);
  }

  
  @Test
  public void testAddEntry_nullEntry() {
    Entry testEntry = null;
    testAddressBook.addEntry(testEntry);
    List<Entry> entryList = testAddressBook.getEntries();
    Entry addedEntry = entryList.get(0);
    assertEquals(addedEntry, null);    
  }
  
  @Test
  public void testAddEntry_emptyString() {
    String testEntryAsString = "";
    testAddressBook.addEntry(testEntryAsString);
    List<Entry> entryList = testAddressBook.getEntries();
    Entry addedEntry = entryList.get(0);
    assertEquals(addedEntry, null);
  }
  
  
  @Test
  public void testRemoveEntry() {
    Entry testEntry = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    testAddressBook.addEntry(testEntry);
    List<Entry> entryList = testAddressBook.getEntries();
    assertEquals(entryList.size(), 1);
    testAddressBook.removeEntry(testEntry);
    assertEquals(entryList.size(), 0);
  }

  /* FAILS: method Javadoc states NullPointerException will be thrown if
     a null Entry is attempted to be removed, but one is not thrown.
  */
  @Test (expected = NullPointerException.class)
  public void testRemoveEntry_removeEntryThatIsNull() {
    Entry testEntry = null;
    testAddressBook.addEntry(testEntry);
    testAddressBook.removeEntry(testEntry);
  }
  
  /* FAILS: method Javadoc states NullPointerException will be thrown if
     a null Entry is attempted to be removed, but one is not thrown.
  */
  @Test (expected = NullPointerException.class)
  public void testRemoveEntry_removeNullEntry() {
    testAddressBook.removeEntry(null);
  }
  
  @Test
  public void testSearchEntry() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook.searchEntry("Main");
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry1);
    expected.add(testEntry2);
    assertEquals(searchResults, expected);   
  }
  
  //ERROR: Does not check for null strings. NullPointerException Thrown
  @Test
  public void testSearchEntry_nullStringSearchCriteria() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook.searchEntry(null);
  }
  
  @Test
  public void testSearchEntry_emptyFieldsExceptName() {
    Entry testEntry1 = new Entry.Builder("Dan").build();
    Entry testEntry2 = new Entry.Builder("Mary").build();
    Entry testEntry3 = new Entry.Builder("Mike").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook.searchEntry("Dan");
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry1);
    assertEquals(searchResults, expected);
  }
  
  @Test
  public void testSearchEntrybyName() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Dan").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook.searchEntryByName("Dan");
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry1);
    expected.add(testEntry3);
    assertEquals(searchResults, expected);   
  }
  
  //ERROR: Does not check for null strings. NullPointerException Thrown
  @Test
  public void testSearchEntrybyName_nullStringSearchCriteria() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Dan").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook.searchEntryByName(null);
  }
  
  @Test
  public void testSearchEntrybyAddress() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("danBroadway@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByAddress("Broadway");
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry3);
    assertEquals(searchResults, expected);   
  }
  
  @Test
  public void testSearchEntrybyAddress_someEmptyAddressFields() {
    Entry testEntry1 = new Entry.Builder("Dan")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByAddress("Broadway");
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry3);
    assertEquals(searchResults, expected);   
  }
  
  @Test
  public void testSearchEntrybyAddress_emptyFieldsExceptName() {
    Entry testEntry1 = new Entry.Builder("Dan").build();
    Entry testEntry2 = new Entry.Builder("Mary").build();
    Entry testEntry3 = new Entry.Builder("Mike").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByAddress("Broadway");
    List<Entry> expected = new ArrayList<Entry>();
    assertEquals(searchResults, expected);   
  }
 
  @Test
  public void testSearchEntrybyPhone() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("56 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByPhone("56");
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry1);
    expected.add(testEntry2);
    assertEquals(searchResults, expected);   
  }
  
  @Test
  public void testSearchEntrybyPhone_someEmptyPhoneFields() {
    Entry testEntry1 = new Entry.Builder("Dan")
        .email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByPhone("12");
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry2);
    assertEquals(searchResults, expected);   
  }
  
  @Test
  public void testSearchEntrybyPhone_emptyFieldsExceptName() {
    Entry testEntry1 = new Entry.Builder("Dan").build();
    Entry testEntry2 = new Entry.Builder("Mary").build();
    Entry testEntry3 = new Entry.Builder("Mike").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByAddress("12");
    List<Entry> expected = new ArrayList<Entry>();
    assertEquals(searchResults, expected);   
  }
  
  //ERROR: Does not check for null strings. NullPointerException Thrown
  @Test
  public void testSearchEntrybyPhone_nullStringSearchCriteria() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Dan").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook.searchEntryByName(null); 
  }
  
  @Test
  public void testSearchEntrybyEmail() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("mike123@gmail.com")
        .phone("354235").email("mike123@yahoo.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByEmail("gmail");
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry1);
    expected.add(testEntry2);
    assertEquals(searchResults, expected);   
  }
  
  @Test
  public void testSearchEntrybyEmail_someEmptyEmailFields() {
    Entry testEntry1 = new Entry.Builder("Dan")
        .email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary")
        .phone("127256").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByEmail("mike123");
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry3);
    assertEquals(searchResults, expected);   
  }
  
  @Test
  public void testSearchEntrybyEmail_emptyFieldsExceptName() {
    Entry testEntry1 = new Entry.Builder("Dan").build();
    Entry testEntry2 = new Entry.Builder("Mary").build();
    Entry testEntry3 = new Entry.Builder("Mike").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByEmail("gmail");
    List<Entry> expected = new ArrayList<Entry>();
    assertEquals(searchResults, expected);   
  }
  
  //ERROR: Does not check for null strings. NullPointerException Thrown
  @Test
  public void testSearchEntrybyEmail_nullStringSearchCriteria() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Dan").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook.searchEntryByName(null); 
  }
  
  @Test
  public void testSearchEntrybyNote() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("mike123@gmail.com")
        .phone("354from").email("mike123@yahoo.com").note("This guy").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByNote("from");
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry1);
    expected.add(testEntry2);
    assertEquals(searchResults, expected);   
  }
  
  @Test
  public void testSearchEntrybyNote_someEmptyNoteFields() {
    Entry testEntry1 = new Entry.Builder("Dan")
        .email("dan123@gmail.com").build();
    Entry testEntry2 = new Entry.Builder("Mary")
        .phone("127256").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByNote("work");
    List<Entry> expected = new ArrayList<Entry>();
    expected.add(testEntry2);
    assertEquals(searchResults, expected);   
  }
  
  @Test
  public void testSearchEntrybyNote_emptyFieldsExceptName() {
    Entry testEntry1 = new Entry.Builder("Dan").build();
    Entry testEntry2 = new Entry.Builder("Mary").build();
    Entry testEntry3 = new Entry.Builder("Mike").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook
        .searchEntryByEmail("Mary");
    List<Entry> expected = new ArrayList<Entry>();
    assertEquals(searchResults, expected);   
  }
  
  //ERROR: Does not check for null strings. NullPointerException Thrown
  @Test
  public void testSearchEntrybyNote_nullStringSearchCriteria() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Dan").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    List<Entry> searchResults = testAddressBook.searchEntryByName(null); 
  }

  @Test
  public void testSaveToFileReadFromFile() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").note("This guy").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    testAddressBook.saveToFile("testSaveFile");
    AddressBook readAddressBook = AddressBook.createEmptyAddressBook(); 
    readAddressBook.readFromFile("testSaveFile", ",");
    List<Entry> entriesOfReadAddressBook = readAddressBook.getEntries();
    assertTrue(entriesEqual(entriesOfReadAddressBook.get(0), testEntry1));
    assertTrue(entriesEqual(entriesOfReadAddressBook.get(1), testEntry2));
    assertTrue(entriesEqual(entriesOfReadAddressBook.get(2), testEntry3));
  }
  
  
  @Test 
  public void testReadFromFile_NoOverwriteExisting() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").email("mary123@gmail.com").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").note("This guy").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    testAddressBook.saveToFile("testSaveFile");
    testAddressBook.readFromFile("testSaveFile", ",");
    List<Entry> entriesOfAddressBook = testAddressBook.getEntries();
    assertTrue(entriesEqual(entriesOfAddressBook.get(0), testEntry1));
    assertTrue(entriesEqual(entriesOfAddressBook.get(1), testEntry2));
    assertTrue(entriesEqual(entriesOfAddressBook.get(2), testEntry3));
    assertTrue(entriesEqual(entriesOfAddressBook.get(3), testEntry1));
    assertTrue(entriesEqual(entriesOfAddressBook.get(4), testEntry2));
    assertTrue(entriesEqual(entriesOfAddressBook.get(5), testEntry3));
  }
  
  @Test
  public void testSaveToFileReadFromFile_MissingFields() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").note("This guy").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    testAddressBook.saveToFile("testSaveFile");
    AddressBook readAddressBook = AddressBook.createEmptyAddressBook(); 
    readAddressBook.readFromFile("testSaveFile", ",");
    List<Entry> entriesOfReadAddressBook = readAddressBook.getEntries();
    assertTrue(entriesEqual(entriesOfReadAddressBook.get(0), testEntry1));
    assertTrue(entriesEqual(entriesOfReadAddressBook.get(1), testEntry2));
    assertTrue(entriesEqual(entriesOfReadAddressBook.get(2), testEntry3));
  }
  
  /* ERROR: If the last field of the last Entry (Optional note field) is null,
     ReadFromFile treats it as an invalid Entry and doesn't read the last Entry
     at all. In this test an IndexOutOfBoundsException is thrown because 
     readFromFile() only read in 2 Entries.
  */
  @Test
  public void testSaveToFileReadFromFile_MissingLastFieldOfLastEntry() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = new Entry.Builder("Mary").address("2 Main St.")
        .phone("127256").note("Mary from work")
        .build();
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    testAddressBook.saveToFile("testSaveFile");
    AddressBook readAddressBook = AddressBook.createEmptyAddressBook(); 
    readAddressBook.readFromFile("testSaveFile", ",");
    List<Entry> entriesOfReadAddressBook = readAddressBook.getEntries();
    assertTrue(entriesEqual(entriesOfReadAddressBook.get(0), testEntry1));
    assertTrue(entriesEqual(entriesOfReadAddressBook.get(1), testEntry2));
    assertTrue(entriesEqual(entriesOfReadAddressBook.get(2), testEntry3));
  }
  
  //ERROR: Null Entries can be added to an AddressBook but this 
  //causes a NullPointerException to be thrown during saveToFile()
  @Test
  public void testSaveToFile_InvalidEntry() {
    Entry testEntry1 = new Entry.Builder("Dan").address("1 Main St.")
        .phone("123456").email("dan123@gmail.com").note("Dan from school")
        .build();
    Entry testEntry2 = Entry.toEntry("Invalid Entry");
    Entry testEntry3 = new Entry.Builder("Mike").address("3 Broadway")
        .phone("354235").email("mike123@gmail.com").note("This guy").build();
    testAddressBook.addEntry(testEntry1);
    testAddressBook.addEntry(testEntry2);
    testAddressBook.addEntry(testEntry3);
    testAddressBook.saveToFile("D:/TestSaveFile");
  }
  
  /* Note on exceptions in saveToFile() and readFromFile():
   * Would be better to remove the try-catch blocks in the code
   * so cases can be tested with an expected exception and users of the API
   * can handle them
   */
}


