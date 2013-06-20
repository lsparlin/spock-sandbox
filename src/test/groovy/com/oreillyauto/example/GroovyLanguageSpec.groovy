package com.oreillyauto.example

import org.junit.Test

import spock.lang.Specification

/***
 * Various tests proving differences in the Groovy language
 * @author lsparlin
 *
 */
class GroovyLanguageSpec extends Specification {
    
    def "Equality is a little different in Groovy"() {
        given:
        Example example1 = new Example()
        Example example2 = new Example()
        
        expect: "equal-equal operator is overloaded with Java's .equals()"
        example1 == example2
        
        and: ".is() is Groovy's identity checker"
        ! example1.is(example2)
    }
    
    /*
     * STRINGS
     */
    
    def "Strings in Groovy"() {
        given:
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
    
    def "New ways to work with lists!"() {
        // list literal
        def list = [1, 2, 3, 4]
        // overloaded left-shift operator
        list << 5
        
        expect:
        list instanceof ArrayList
        list == [1, 2, 3, 4, 5]
        list.first() == 1
        list.last() == 5
    }
    
    def "New ways to create and use Maps!"() {
        // map literal
        def map = [1: 'One', 2: 'Two']
        // alternitive to .put()
        map['key'] = 'val'
        
        
        expect:
        map instanceof HashMap<Object, Object>
        map.size() == 3
        
        and: "different ways of accessing map entries"
        map[1] == 'One'
        map['key'] == 'val'
        map.key == 'val'
    }
    
    def "Ranges are new to groovy!"() {
        def range = 1..3
        
        expect:
        range == [1, 2, 3]
        range instanceof List
        range.size() == 3
        range.from == 1
        range.to == 3
        range.contains(2)
    }
    
    /*
     * OBJECTS
     */
    
    def "Property initialization shorthand"() {
        given: "can set properties during initialization"
        Example example1 = new Example([id: 1, name: 'Example'])
        
        expect: "can access properties with shorthand"
        example1.id == 1
        example1.name == 'Example'
        
        and: "square brackets are optional"
        example1 == new Example(id: 1)
        
    }
    
    
    /*
     * OPERATORS
     */
    
    def "Spread Operator"() {
        given:
        def list = ['one', 'two', 'three', 'four']
        
        expect:
        list*.length() == [3, 3, 5, 4]
        list*.toUpperCase() == ['ONE', 'TWO', 'THREE', 'FOUR']
        
        and:
        [1, 2, 3]*.toString() == ['1', '2', '3']
        
    }
    
    def "Safe Navigation Operator"() {
        given:
        def example = new Example()
        
        when: "NPE thrown when attempting trim()"
        example.name.trim()
        then:
        thrown(NullPointerException)
        
        when:
        def trimName = example.name?.trim()
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
    
    /*
     * REVIEW
     */
    
    def "Groovy Language review"() {
        expect:
        "Groovy" == 'Groovy'
        
        new Example(id: 1).id == 1
        
        ['one', 'two', 'three'] instanceof List
        
        [1: 'one', 2: 'two', 3: 'three'] instanceof Map
        
        1..5 == [1, 2, 3, 4, 5] // Range
    }
    
}
