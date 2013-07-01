package com.oreillyauto.example

import spock.lang.Specification

class FirstSpec extends Specification {
    def list = new ArrayList<String>()
    
    def setup() {
        println "setting up"
    }
    
    def cleanup() {
        println "cleaning up"
    }
    
    def testList() {
        setup:
        println "setup from method"
        def word = "word"
        
        when:
        println "when"
        list << (word)
        
        then:
        println "then"
        list.size() == 1
    }

}
