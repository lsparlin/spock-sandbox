package com.oreillyauto.example.web

import spock.lang.Specification

import com.oreillyauto.example.Example
import com.oreillyauto.example.data.ExampleDao

class ExampleControllerSpec extends Specification {
    ExampleDao dao = Mock()
    ExampleController controller = new ExampleController()
    
    def setup() {
        controller.dao = dao
    }
    
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
    
    def "example/save returns result of dao.save"() {
        Example example = new Example(id: 1)
        
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
        new NullPointerException()  || "NPE"
    }

}
