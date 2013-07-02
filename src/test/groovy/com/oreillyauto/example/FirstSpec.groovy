package com.oreillyauto.example

import spock.lang.Specification

class FirstSpec extends Specification {
    def list = new ArrayList<String>()
    
    def testList() {
        setup:
        def word = "word"
        
        when:
        list.add(word)
        
        then:
        assert list.size() == 1
    }

}
