//
//  LeetCode50Tests.swift
//  LeetCodeSwiftTests
//
//  Created by Liang on 2020/5/11.
//  Copyright © 2020 imieutan. All rights reserved.
//

import XCTest
@testable import LeetCodeSwift

class LeetCode50Tests: XCTestCase {

    func testExample() throws {
        let leetcode = LeetCode50()
        XCTAssertEqual(leetcode.myPow(2.0, 10), 1024.00)
//        XCTAssertEqual(leetcode.myPow(2.1, 3), 9.26100)
        XCTAssertEqual(leetcode.myPow(2, -2), 0.25)
    }

}
