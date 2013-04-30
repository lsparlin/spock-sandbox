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
    
    /*
     * COLLECTIONS
     */
        
    def "Lists"() {
        expect:
        [1, 2, 3] instanceof ArrayList
    }
    
    def "Ranges"() {
        when:
        def range = 1..3
        
        then:
        range.size() == 3
        range.from == 1
        range.to == 3
    }
    
    def "Maps"() {
        expect:
        [1: "One"] instanceof HashMap
    }
    
    /*
     * OPERATORS
     */
    
    def "Spread Operator"() {
        when:
        def lengths = ["one", "three", "four"]*.length()
        
        then:
        lengths == [3, 5, 4]
    }
    
    def "Safe Navigation Operator"() {
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
    
    /*
     * REGEX
     */
    
    def "Find and Matche Operators"() {
        when:
        def string = "I was 1st out of 100 runners"
        def wordMatcher = string =~ /\w+/
        
        then:
        wordMatcher.size() == 7
        string =~ /was \d/
        string ==~ /^I was .*/
        ! (string ==~ /I was/)
    }
    
}
