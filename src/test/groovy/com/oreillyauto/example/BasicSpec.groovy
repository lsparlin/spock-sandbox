package com.oreillyauto.example

import java.util.HashMap;

import spock.lang.Specification;


class BasicSpec extends Specification {
    HashMap<String, String> map = new HashMap<String, String>()
    
    // expect
    def "Adding int and String returns String"() {
        expect: 
        1 + "" instanceof String
    }
    
    // when, then, thrown()
    def "getUserByTmNumber will throw exception if not found"() {
        when:
        TeamNetUser.getUserByTmNumber(null)
        
        then:
        thrown(NoUsersFoundException)
    }
    
    // setup, when, then, notThrown()
    def "HashMap accepts null key"() {
        setup: // optional - setup: or given: is implied, use by preference
        def map = new HashMap<String, String>()

        when:
        map.put(null, 'value')

        then:
        notThrown(Exception)
        map.get(null) == 'value'
    }

    // setup, when, then, where
    def "HashMap can only have one value per unique key"() {
        setup:
        def map = new HashMap<String, String>()
        map.put('one', 'ONE')
        map.put('two', 'TWO')

        when:
        map.put(key, value)
        
        then:
        map.size() == mapSize

        where:
        key     << ['three', 'two']
        value   << ['THREE', 'TWO']
        mapSize << [3, 2]
    }

    // when, then, where, old()
    def "HashMap can only have one value per unique key - more concise"() {
        def map = ['one': 'ONE', 'two': 'TWO']

        when:
        map.put(key, value)
        
        then:
        map.size() - old(map.size()) == sizeDif

        where:
        key     | value     | sizeDif
        'three' | 'THREE'   | +1
        'two'   | '2'       | 0
        //'two'   | '2'       | +1
    }
}
