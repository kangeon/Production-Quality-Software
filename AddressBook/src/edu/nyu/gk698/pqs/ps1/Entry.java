package edu.nyu.gk698.pqs.ps1;

/**
 * Entry is a class that encapsulates a person's contact information.
 * The contact information includes:
 * <ul>
 * <li> Name
 * <li> Postal Address
 * <li> Phone Number
 * <li> Email Address
 * <li> Note
 * </ul>
 * <p>
 * The Entry class implements the builder pattern to allow for optional 
 * parameters. All the contact information parameters listed above are 
 * optional.
 * 
 * @author Geon Kang
 * @version %I%, %G%
 */
public class Entry {
  private String name;
  private String postalAddress;
  private String phoneNumber;
  private String emailAddress;
  private String note;
  
  /**
   * Static Builder class that is used to construct an entry given a variable 
   * number of parameters.
   */
  public static class Builder {
    // Optional parameters. Null by default.
	private String name = null;
	private String postalAddress = null;
	private String phoneNumber = null;
	private String emailAddress = null;
	private String note = null;
	
	/**
	 * Default Builder constructor for the Builder class.
	 * Is empty because all of the parameters are optional. 
	 */
	public Builder() {	  
	}
	
	/**
	 * Sets the value of the field <code>name</code> of this Builder object to
	 * the specified value <code>val</code>.
	 * 
	 * @param val  value this Builder object's <code>name</code> field 
	 *             will be set to.
	 * @return  this Builder object with field <code>name</code> set to the 
	 *          specified <code>val</code>.
	 */
	public Builder name(String val) {
	  name = val;
	  return this;
	}
	
	/**
	 * Sets the value of the field <code>postalAddress</code> of this Builder 
	 * object to the specified value <code>val</code>.
	 * 
	 * @param val  value this Builder object's <code>postalAddress</code> field 
	 *             will be set to.
	 * @return  this Builder object with field <code>postalAddress</code> set 
	 *          to the specified <code>val</code>.
	 */
	public Builder postalAddress(String val) {
	  postalAddress = val;
	  return this;
	}
	
	/**
	 * Sets the value of the field <code>phoneNumber</code> of this Builder 
	 * object to the specified value <code>val</code>.
	 * 
	 * @param val  value this Builder object's <code>phoneNumber</code> field 
	 *             will be set to.
	 * @return  this Builder object with field <code>phoneNumber</code> set 
	 *          to the specified <code>val</code>.
	 */
	public Builder phoneNumber(String val) {
	  phoneNumber = val;
	  return this;
	}
	
	/**
	 * Sets the value of the field <code>emailAddress</code> of this Builder 
	 * object to the specified value <code>val</code>.
	 * 
	 * @param val  value this Builder object's <code>emailAddress</code> field 
	 *             will be set to.
	 * @return  this Builder object with field <code>emailAddress</code> set 
	 *          to the specified <code>val</code>.
	 */
	public Builder emailAddress(String val) {
	  emailAddress = val;
	  return this;
	}
	
	/**
	 * Sets the value of the field <code>note</code> of this Builder 
	 * object to the specified value <code>val</code>.
	 * 
	 * @param val  value this Builder object's <code>note</code> field will 
	 *             be set to.
	 * @return  this Builder object with field <code>note</code> set 
	 *          to the specified <code>val</code>.
	 */
	public Builder note(String val) {
	  note = val;
	  return this;
	}
	
	/**
	 * Constructs an Entry object with an Entry Constructor using this builder 
	 * object.
	 * <p>
	 * Fields not specified with the setter-like Builder methods are 
	 * <code>null</code> by default.
	 * 
	 * @return  an Entry object constructed from this Builder object.
	 */
	public Entry build() {
	  return new Entry(this);  
	}
  }

  /* Private Entry constructor that creates Entry object given a builder object.
   * Fields not specified with builder methods are null by default.
   * Called by Builder object's build method. 
  */
  private Entry(Builder builder) {
    name = builder.name;
	postalAddress = builder.postalAddress;
	phoneNumber = builder.phoneNumber;
	emailAddress = builder.emailAddress;
	note = builder.note;
  }
  
  /**
   * Gets the value of this Entry object's 
   * <code>private</code> <code>name</code> field.  
   * 
   * @return  value of the <code>String</code> field <code>name</code> of 
   *          this Entry object. 
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the value of this Entry object's 
   * <code>private</code> <code>postalAddress</code> field.  
   * 
   * @return  value of the <code>String</code> field 
   *          <code>postalAddress</code> of this Entry object. 
   */
  public String getPostalAddress() {
    return postalAddress;
  }
  
  /**
   * Gets the value of this Entry object's 
   * <code>private</code> <code>phoneNumber</code> field.  
   * 
   * @return  value of the <code>String</code> field 
   *          <code>phoneNumber</code> of this Entry object. 
   */	  
  public String getPhoneNumber() {
    return phoneNumber;  
  }
  
  /**
   * Gets the value of this Entry object's 
   * <code>private</code> <code>emailAddress</code> field.  
   * 
   * @return  value of the <code>String</code> field 
   *          <code>emailAddress</code> of this Entry object. 
   */	  
  public String getEmailAddress() {
    return emailAddress;
  }
  
  /**
   * Gets the value of this Entry object's 
   * <code>private</code> <code>notes</code> field.  
   * 
   * @return  value of the <code>String</code> field 
   *          <code>note</code> of this Entry object. 
   */	
  public String getNote() {
    return note;	  
  }
  
  /**
   * Sets this Entry object's <code>private</code> <code>name</code> field 
   * to the specified <code>String</code>.
   * 
   * @param name <code>String</code> that this Entry object's 
   *             <code>name</code> field will be set as.
   */   
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Sets this Entry object's <code>private</code> <code>postalAddress</code> 
   * field to the specified <code>String</code>.
   * 
   * @param name <code>String</code> that this Entry object's 
   *             <code>postalAddress</code> field will be set as.
   */  
  public void setPostalAddress(String postalAddress) {
	this.postalAddress = postalAddress;  
  }
  
  /**
   * Sets this Entry object's <code>private</code> <code>phoneNumber</code> 
   * field to the specified <code>String</code>.
   * 
   * @param name <code>String</code> that this Entry object's 
   *             <code>phoneNumber</code> field will be set as.
   */  
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;	  
  }
  
  /**
   * Sets this Entry object's <code>private</code> <code>emailAddress</code> 
   * field to the specified <code>String</code>.
   * 
   * @param name <code>String</code> that this Entry object's 
   *             <code>emailAddress</code> field will be set as.
   */ 
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;	  
  }
  
  /**
   * Sets this Entry object's <code>private</code> <code>note</code> 
   * field to the specified <code>String</code>.
   * 
   * @param name <code>String</code> that this Entry object's 
   *             <code>note</code> field will be set as.
   */ 
  public void setNote(String note) {
    this.note = note;	  
  }
  
  /**
   * Checks if the value of any of this Entry object's fields is equal to the 
   * specified <code>String</code>.  
   * 
   * @param criteria  the <code>String</code> value to be searched for in this 
   *                  Entry object's fields. 
   * @return  <code>true</code> if the value of any field is equal to the 
   *          specified <code>String</code>; <code>false</code> otherwise. 
   */	
  protected boolean contains(String criteria) {
    if(name.equals(criteria) || postalAddress.equals(criteria) ||
        phoneNumber.equals(criteria) || emailAddress.equals(criteria) ||
		note.equals(criteria)) {
	  return true;
	}
	else {
	  return false;	  
	}
  }
  
  /**
   * Returns fields of this Entry object as a single <code>String</code>.
   * Each field is separated by a tab within the <code>String</code>.
   * Overrides default toString method.
   * @return  the fields of this Entry object, each separated by a tab, 
   *          as a single <code>String</code>.  
   */
  @Override public String toString() {
    return name + "\t" + postalAddress + "\t" + phoneNumber + "\t" 
      + emailAddress + "\t" + note;           
  }
}
