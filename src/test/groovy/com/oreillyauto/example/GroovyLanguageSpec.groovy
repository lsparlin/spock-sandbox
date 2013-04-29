package com.oreillyauto.example

import spock.lang.Specification

/***
 * Various tests proving differences in the Groovy language
 * @author lsparlin
 *
 */
class GroovyLanguageSpec extends Specification {
    
    def "Property initialization shorthand"() {
        expect:
        new Example().name == null
        
        and:
        new Example(name: "Test").name == "Test"
    }
    
    def "Safe Navigation"() {
        given:
        def example = new Example()
        
        when:
        example.name.trim
        then:
        thrown(NullPointerException)
        
        when:
        def trimName = example.name?.trim
        then:
        notThrown(NullPointerException)
        trimName == null
    }
    
    def "Lists"() {
        expect:
        [1, 2, 3] instanceof ArrayList
    }
    
}
