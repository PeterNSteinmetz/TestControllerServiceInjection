package testcontrollerserviceinjection

/**
 * Simple implementation of a security service to provide encodePassword.
 * 
 * @author peter
 */
class FakeSecurityService {

	/**
	 * Method which takes both a String and the optional salt.
	 * @param password
	 * @return
	 */
    String encodePassword(String password, salt = null) {
		return "Encoded version of " + password + " with salt = " + salt
    }
}
