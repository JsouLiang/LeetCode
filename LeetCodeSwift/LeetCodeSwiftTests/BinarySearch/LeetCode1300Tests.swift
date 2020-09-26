//
//  LeetCode1300Tests.swift
//  LeetCodeSwiftTests
//
//  Created by Liang on 2020/6/14.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

import XCTest
@testable import LeetCodeSwift

class LeetCode1300Tests: XCTestCase {

    func testExample() throws {
        let leetCode1300 = LeetCode1300()
//        XCTAssertEqual(leetCode1300.findBestValue([3,4,9], 10), 3)
//        XCTAssertEqual(leetCode1300.findBestValue([2, 3, 5], 10), 5)
//        XCTAssertEqual(leetCode1300.findBestValue([60864,25176,27249,21296,20204], 56803), 11361)
        XCTAssertEqual(leetCode1300.findBestValue([48772,52931,14253,32289,75263], 40876), 8175)
        
        
        
        XCTAssertEqual(leetCode1300.lowerBound(target: 3, nums: [1, 2, 3, 3, 4]), 2)
        XCTAssertEqual(leetCode1300.lowerBound(target: 2, nums: [1, 2]), 1)
        XCTAssertEqual(leetCode1300.lowerBound(target: 2, nums: [1, 2, 2]), 1)
        XCTAssertEqual(leetCode1300.lowerBound(target: 1, nums: [1, 1, 1, 2, 2]), 0)
    }

}
