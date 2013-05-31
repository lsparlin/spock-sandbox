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
     * STRINGS
     */
    
    def "Strings in Groovy"() {
        def name = 'Groovy'
        def hello = "Hello ${name}"
        
        expect:
        name == "Groovy"
        name.is("Groovy")
        and:
        hello == 'Hello Groovy'
        !hello.is('Hello Groovy')
        and:
        name instanceof String
        hello instanceof GString
    }
    
    /*
     * COLLECTIONS
     */
    
    def "Ranges are new to groovy!"() {
        def range = 1..3
        
        expect:
        range instanceof List
        range.size() == 3
        range.from == 1
        range.to == 3
        range.contains(2)
        range.collect { it * it } == [1, 4, 9]
    }
        
    def "New ways to create and use lists!"() {
        def list = [1, 2]
        list << 3 
        
        expect:
        list instanceof ArrayList
        list[0] == list.first()
        
        and: "sublists"
        list[0..1]  == [1, 2]
        list[0,2] == [1, 3]
        list[0,-1] == [1, 3] // first and last
    }
    
    def "New ways to create and use Maps!"() {
        def map = [1: 'One', 2: 'Two', 'key': 'val']
        
        expect:
        [:] instanceof HashMap
        map instanceof HashMap
        map[1] == 'One'
        map['key'] == 'val'
        map.key == 'val'
    }
    
    /*
     * CLOSURES
     */
    
    def "some Collection methods that accept closures"() {
        def list = ['one', 'two', 'three']
        
        expect:
        list.collect { it.toUpperCase() } == ['ONE', 'TWO', 'THREE']
        and:
        list.findAll { it.startsWith 't'} == ['two', 'three']
        and: 
        list.each { println it }
    }
    
    /*
     * OPERATORS
     */
    
    def "Spread Operator"() {
        expect:
        ["one", "three", "four"]*.length() == [3, 5, 4]
    }
    
    def "Subscript operator"() {
        def string = 'I like Groovy!'
        
        expect:
        string[0] == 'I'
        string[0..5] == 'I like'
        string[-1] == '!'
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
    
    def "Find and Match Operators"() {
        when:
        def string = "I was 1st out of 100 runners"
        def wordMatcher = string =~ /\w+/
        
        then:
        wordMatcher.size() == 7
        wordMatcher[0] == 'I'
        wordMatcher[6] == 'runners'
        and:
        string ==~ /^I was .*/
        ! (string ==~ /I was/)
    }
    
}
