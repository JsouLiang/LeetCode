//
//  LRUCacheTests.swift
//  LeetCodeSwiftTests
//
//  Created by Liang on 2020/5/25.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

import XCTest
@testable import LeetCodeSwift

class LRUCacheTests: XCTestCase {

    func testExample() throws {
        let cache = LRUCache(2)
        cache.put(1, 1)
        cache.put(2, 2)
        XCTAssertEqual(cache.get(1), 1)
        cache.put(3, 3)
        XCTAssertEqual(cache.get(2), -1)
        cache.put(4, 4)
        XCTAssertEqual(cache.get(1), -1)
        XCTAssertEqual(cache.get(3), 3)
        XCTAssertEqual(cache.get(4), 4)
    }
    
    func testExampleII() throws {
        let cache = LRUCache(2)
        cache.put(2, 1)
        cache.put(1, 1)
        XCTAssertEqual(cache.get(2), 1)
        cache.put(4, 1)
        XCTAssertEqual(cache.get(1), -1)
        XCTAssertEqual(cache.get(2), 1)
    }
    
    func testExampleIII() throws {
        let cache = LRUCache(2)
        XCTAssertEqual(cache.get(2), -1)
        cache.put(2, 6)
        XCTAssertEqual(cache.get(1), -1)
        cache.put(1, 5)
        cache.put(1, 2)
        XCTAssertEqual(cache.get(1), 2)
        XCTAssertEqual(cache.get(2), 6)
    }
    
    func testExampleIIII() throws {
        let cache = LRUCache(2)
        cache.put(2, 1)
        cache.put(1, 1)
        cache.put(2, 3)
        cache.put(4, 1)
        XCTAssertEqual(cache.get(1), -1)
        
        XCTAssertEqual(cache.get(2), 3)
        
    }

}
