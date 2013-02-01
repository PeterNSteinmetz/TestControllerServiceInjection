package testcontrollerserviceinjection


import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.test.mixin.web.ControllerUnitTestMixin

@TestMixin([ControllerUnitTestMixin,DomainClassUnitTestMixin])
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

    void testCreate() {
       def model = controller.create()

       assert model.personInstance != null
    }

    void testSave() {
		defineBeans {
			springSecurityService(FakeSecurityService)
	    }
		mockDomain(Person)
		def controller = testFor(PersonController)

        controller.save()

        assert model.personInstance != null
        assert view == '/person/create'

        response.reset()

        populateValidParams(params)
		// debug stepping into the following shows that while the person and password fields 
		// are set to the value from the params, the springSecurityService is null, despite
		// the defineBeans and mockDomain calls above
        controller.save()

        assert response.redirectedUrl == '/person/show/1'
        assert controller.flash.message != null
        assert Person.count() == 1
    }

}
