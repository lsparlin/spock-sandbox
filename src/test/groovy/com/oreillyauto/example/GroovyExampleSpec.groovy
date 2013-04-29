package com.oreillyauto.example

import spock.lang.Specification

class GroovyExampleSpec extends Specification {
    GroovyExample groovyExample = new GroovyExample()
    
    def "getter and setter"() {
        when:
        groovyExample.setName "Test"
        
        then:
        groovyExample.name == groovyExample.getName()
    }    
    
}
