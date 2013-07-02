package com.oreillyauto.example.web

import java.sql.SQLException

import spock.lang.Specification
import spock.lang.Unroll

import com.oreillyauto.example.Example
import com.oreillyauto.example.data.ExampleDao

class ExampleControllerSpec extends Specification {
    ExampleDao dao = Mock()
    ExampleController controller = new ExampleController()
    
    def setup() {
        controller.dao = dao
    }
    
    def "mocks vs stubs"() {
	setup:
	ExampleDao mockDao = Mock()
	ExampleDao stubDao = Stub()
	
	expect:
	mockDao.getAll() == null
	stubDao.getAll() == []
    }
    
    @Unroll
    def "index yeilds list of Examples"() {
        given:
        1 * dao.getAll() >> examples
        
        when:
        def response = controller.index()
        
        then:
        response == expectedResponse
        
        where:
        examples                         | expectedResponse
        null                             | "Empty"
        []                               | "Empty"
        [new Example(name: "First"),
            new Example(name: "Second")] | "Example [name=First], Example [name=Second]"
    }
    
    @Unroll
    def "example/save returns result of dao.save"() {
	given:
    Example example = new Example(id: 1, name: "string")
    
    when: "use case"
    String response = controller.save(example)

    then:
    1 * dao.save(_ as Example) >> new Example()
    response == "Success"
    
    when: "exception cases"
    response = controller.save(example)

    then:
    1 * dao.save(_ as Example) >> { throw exception }
    response == expectedResp
    
    where:
    exception                   || expectedResp
    new IllegalStateException() || "Illegal State"
    new SQLException()          || "SQL Error"
    }

}
