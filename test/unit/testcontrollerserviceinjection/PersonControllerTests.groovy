package testcontrollerserviceinjection



import org.junit.*
import grails.test.mixin.*

@TestFor(PersonController)
@Mock(Person)
class PersonControllerTests {


    def populateValidParams(params) {
      assert params != null
      params["person"] = 'test name'
	  params['password'] = 'test pw'
    }

    void testIndex() {
        controller.index()
        assert "/person/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.personInstanceList.size() == 0
        assert model.personInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.personInstance != null
    }

    void testSave() {
		Person.metaClass.getSpringSecurityService = { return new FakeSecurityService() }
		
        controller.save()

        assert model.personInstance != null
        assert view == '/person/create'

        response.reset()
		
		// in this approach there is no bean in the context
		try {
			applicationContext.getBean("springSecurityService")
			fail "should have thrown exception for non-existant bean"
		} catch (Exception e) {
		}
		
		// show that a getSpringSecurityMethod is defined on the Person
		assertNotNull Person.metaClass.getMethods().contains("getSpringSecurityService")
		
        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/person/show/1'
        assert controller.flash.message != null
        assert Person.count() == 1
    }

}
