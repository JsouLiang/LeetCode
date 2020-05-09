//
//  LeetCode69Tests.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/9.
//  Copyright © 2020 imieutan. All rights reserved.
//


import XCTest
@testable import LeetCodeSwift

class LeetCode69Tests: XCTestCase {

    func testExample() throws {
        let leetcode = LeetCode69()
        XCTAssertEqual(leetcode.mySqrt(8), 2)
        XCTAssertEqual(leetcode.mySqrt(1), 1)
        XCTAssertEqual(leetcode.mySqrt(6), 2)
    }

}

