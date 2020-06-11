//
//  LeetCode84Tests.swift
//  LeetCodeSwiftTests
//
//  Created by Liang on 2020/5/30.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

import Foundation

import XCTest
@testable import LeetCodeSwift

class LeetCode84Tests: XCTestCase {

    func testExample() throws {
        let leetCode = LeetCode84()
        XCTAssertEqual(leetCode.largestRectangleArea([1]), 1)
        XCTAssertEqual(leetCode.largestRectangleArea([2, 1, 5, 6, 2, 3]), 10)
        XCTAssertEqual(leetCode.largestRectangleArea([1, 2, 3, 4, 5]), 9)
    }

}
