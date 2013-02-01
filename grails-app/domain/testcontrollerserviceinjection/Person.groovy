package testcontrollerserviceinjection

class Person {
  
  transient springSecurityService
	
  String person
  String password
  
  static constraints = {
	person blank:false
	password blank:false
  }
  
  def beforeInsert() {
	encodePassword()
  }

  def beforeUpdate() {
	if (isDirty('password')) {
	  encodePassword()
	}
  }

  protected void encodePassword() {
	// password = password // works with this
	springSecurityService.encodePassword(password) // fails with this
  }

}
