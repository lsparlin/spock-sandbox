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
    
    def "details yeilds Example details"() {
        given:
        dao.findOne(1) >> new Example(name: "Test")
        dao.findOne(_) >> null
        
        when:
        def response = controller.details(exampleId)
        
        then:
        response == expectedResponse
        
        where:
        exampleId   | expectedResponse
        1           | "Example [name=Test]"
        2           | "Not Found"
        3           | "Not Found"
    }
    
    def "post saves Example and sets id"() {
        given:
        def list = []
        saveCalls * dao.save(_) >> {
            it[0].id = 1
            list << it[0]
            return it[0]
        }
        
        when:
        def response = controller.post(example)
        
        then:
        response == expectedResponse
        
        where:
        example                     | expectedResponse  | saveCalls
        new Example(name: "Test")   | "1"               | 1
        new Example(id: 2)          | "Invalid"         | 0
        null                        | "Invalid"         | 0
    }
    
    def "example/save returns result of dao.save"() {
        Example example = new Example(id: 1)
        
        when: "typical use case"
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
