package com.oreillyauto.example

import spock.lang.Specification

class BasicSpec extends Specification {
    HashMap<String, String> map = new HashMap<String, String>()
    
    def "HashMap accepts null key"() {
        setup: // optional - setup: or given: is implied, use by preference
        def map = new HashMap<String, String>()
        
        when:
        map.put(null, 'value')
        
        then:
        notThrown(Exception)
        map.get(null) == 'value'
    }
    
    def "Concatenating int and String returns String"() {
        expect: // shortcut for when:, then:
        1 + "" instanceof String
    }
    
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
        key     | value     || mapSize
        'three' | 'THREE'   || 3
        'two'   | 'TWO'     || 2
    }

    def "HashMap can only have one value per unique key - more concise"() {
        def map = new HashMap<String, String>(['one': 'ONE', 'two': 'TWO'])
        
        when:
        map.put(key, value)
        then:
        map.size() - old(map.size()) == sizeDif
        
        where:
        key     | value     || sizeDif
        'three' | 'THREE'   || +1
        'two'   | '2'       || 0
        'two'   | '2'       || +1
    }
    
}
