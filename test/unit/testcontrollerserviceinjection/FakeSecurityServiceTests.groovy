package testcontrollerserviceinjection



import grails.test.mixin.*
import org.junit.*


@TestFor(FakeSecurityService)
class FakeSecurityServiceTests {

    void testSomething() {
        assert service.encodePassword("testPw").contains("Encoded version of")
    }
}
