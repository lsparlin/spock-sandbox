package com.oreillyauto.example

import spock.lang.Specification

class CodeSampleSpec extends Specification {
    
    def "simple feature"() {
        setup: // setup object under test
        def word = 'apple'
        
        when: // provide stimulus 
        def subWord = word.substring(1)
        
        then: // express expected response to stimulus
        subWord == 'pple'
        
        cleanup: // any cleanup steps (optional)
        def a = "a"
    }

}
