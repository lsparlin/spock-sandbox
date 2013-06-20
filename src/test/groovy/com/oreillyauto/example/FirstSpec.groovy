package com.oreillyauto.example

import spock.lang.Specification

import com.oreillyauto.example.data.ExampleDao
import com.oreillyauto.example.web.ExampleController

class FirstSpec extends Specification {
    
    /*
     * WRITE FEATURE METHOD BY HAND AS FIRST EXAMPLE
     * - Test List.add
     */

    def "List.add increases list size and new element can be accessed with its index"() {
        setup:
        List list = new ArrayList<String>()
        
        when:
        list.add('hello')
        
        then:
        list.size() == 1
        list.get(0) == 'hello'
    }

}
