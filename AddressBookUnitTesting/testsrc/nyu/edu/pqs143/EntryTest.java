package nyu.edu.pqs143;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * junit tests for Entry class used in AddressBook library
 * 
 * @author Geon Kang
 * @version %I%, %G%
 */
public class EntryTest {
  /* Because equals() is not overloaded for the Entry class,
     there is no direct way of checking if the toEntry methods actually 
     converts a String into a correct Entry. This helper method 
     provides a way to compare the equality of two Entries based on their 
     fields for more intuitive testing.
  */
  private boolean entriesEqual(Entry e1, Entry e2) {
    return e1.getName().equals(e2.getName()) && 
        e1.getAddress().equals(e2.getAddress()) &&
        e1.getPhone().equals(e2.getPhone()) &&
        e1.getEmail().equals(e2.getEmail()) &&
        e1.getNote().equals(e2.getNote());
  }

  @Test
  public void testToString() {
    Entry testEntry = new Entry.Builder("Sam").address("100 West 4th St.")
        .phone("123-346-2346").email("samsamsam@gmail.com").note("Sam NYU")
        .build();
    String testEntryAsString = testEntry.toString();
    assertEquals(testEntryAsString,"Sam,100 West 4th St.,123-346-2346,"
        + "samsamsam@gmail.com,Sam NYU");
  }

  @Test
  public void testToString_addressEmpty() {
    Entry testEntry = new Entry.Builder("Sam").phone("123-346-2346")
        .email("samsamsam@gmail.com").note("Sam NYU").build();
    String testEntryAsString = testEntry.toString();
    assertEquals(testEntryAsString,"Sam,,123-346-2346,"
        + "samsamsam@gmail.com,Sam NYU"); 
  }
  
  @Test
  public void testToString_allFieldsExceptNameEmpty() {
    Entry testEntry = new Entry.Builder("Sam").build();
    String testEntryAsString = testEntry.toString();
    assertEquals(testEntryAsString,"Sam,,,,"); 
  }
  
  @Test
  public void testToEntry_onlyStringArgument() {
    String testEntryAsString = "Sam,100 West 4th St.,123-346-2346,"
        + "samsamsam@gmail.com,Sam NYU";
    Entry testEntry = Entry.toEntry(testEntryAsString);
    Entry expectedEntry = new Entry.Builder("Sam").address("100 West 4th St.")
        .phone("123-346-2346").email("samsamsam@gmail.com").note("Sam NYU")
        .build();
    assertTrue(entriesEqual(testEntry, expectedEntry));
  }
  
  @Test
  public void testToEntry_StringArgumentAndDelimiterArgument() {
    String testEntryAsString = "Sam:100 West 4th St.:123-346-2346:"
        + "samsamsam@gmail.com:Sam NYU";
    Entry testEntry = Entry.toEntry(testEntryAsString, ":");
    Entry expectedEntry = new Entry.Builder("Sam").address("100 West 4th St.")
        .phone("123-346-2346").email("samsamsam@gmail.com").note("Sam NYU")
        .build();
    assertTrue(entriesEqual(testEntry, expectedEntry));
  }
  
  @Test
  public void testToEntry_DelimiterGivenButStringContainsDefaultDelimiter() {
    String testEntryAsString = "Sam:100 West 4th St., New York, NY"
        + ":123-346-2346:samsamsam@gmail.com:Sam NYU";
    Entry testEntry = Entry.toEntry(testEntryAsString, ":");
    Entry expectedEntry = new Entry.Builder("Sam")
        .address("100 West 4th St., New York, NY").phone("123-346-2346")
        .email("samsamsam@gmail.com").note("Sam NYU").build();
    assertTrue(entriesEqual(testEntry, expectedEntry));
  }
  
  @Test
  public void testToEntry_StringWithNoDelimiter() {
    String testEntryAsString = "Sam:100 West 4th St.:123-346-2346:"
        + "samsamsam@gmail.com:Sam NYU";
    Entry testEntry = Entry.toEntry(testEntryAsString);
    assertEquals(testEntry, null);
  }
  
  @Test
  public void testToEntry_StringWithEmptyOptionalMiddleFields() {
    String testEntryAsString = "Sam:100 West 4th St.:::a";
    Entry testEntry = Entry.toEntry(testEntryAsString, ":");
    Entry expectedEntry = new Entry.Builder("Sam")
        .address("100 West 4th St.").note("a").build();
    assertTrue(entriesEqual(testEntry, expectedEntry));
  }
  
  //Error: toEntry() fails if last field is empty.
  @Test
  public void testToEntry_StringWithEmptyLastField() {
    String testEntryAsString = "Sam,100 West 4th St.,123-346-2346,"
        + "samsamsam@gmail.com,";
    Entry testEntry = Entry.toEntry(testEntryAsString);
    Entry expectedEntry = new Entry.Builder("Sam").address("100 West 4th St.")
        .phone("123-346-2346").email("samsamsam@gmail.com").build();
    assertTrue(entriesEqual(testEntry, expectedEntry));
  }
  
  @Test
  public void testToEntry_tooManyDelimiters() {
    String testEntryAsString = "Sam:100 West 4th St.:123-346-2346:"
        + "523-748-3426:samsamsam@gmail.com:Sam NYU";
    Entry testEntry = Entry.toEntry(testEntryAsString);
    assertEquals(testEntry, null);
  }
}
