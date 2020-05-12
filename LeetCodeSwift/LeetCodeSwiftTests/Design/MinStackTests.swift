//
//  MinStackTests.swift
//  LeetCodeSwiftTests
//
//  Created by Liang on 2020/5/12.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

import XCTest
@testable import LeetCodeSwift

class MinStackTests: XCTestCase {

    func testExample() throws {
        let minStack = MinStack()
        minStack.push(-2)
        minStack.push(0)
        minStack.push(-3)
        XCTAssertEqual(minStack.getMin(), -3)
        minStack.pop()
        XCTAssertEqual(minStack.top(), 0)
        XCTAssertEqual(minStack.getMin(), -2)
    }

}
