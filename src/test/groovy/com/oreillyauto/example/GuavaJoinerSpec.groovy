package com.oreillyauto.example

import spock.lang.Specification

import com.google.common.base.Joiner

class GuavaJoinerSpec extends Specification {
    def joiner = Joiner.on(", ")  // no need for setup() method - fields are re-initialized for each test
    
    def "joins object's toString on seperator"() {
        expect:
        joinResult == joiner.join(input)

        where:
        input                               | joinResult
        ["one", "two", "three"]             | "one, two, three"
        ["", "four"]                        | ", four"
        [ new Example(name: "First"),  
            new Example(name: "Second")]    | "Example [name=First], Example [name=Second]"
    }
    
    def "null inputs yeild Exceptions.  Unless skipNulls() is invoked"() {
        when:
        joiner.join(input)
        
        then:
        thrown(NullPointerException)
        
        where:
        input << [["one", null], null]  
    }
}
