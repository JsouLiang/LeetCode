//
//  LeetCode179.swift
//  LeetCodeSwiftTests
//
//  Created by Liang on 2020/5/20.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

import XCTest
@testable import LeetCodeSwift

class LeetCode179Tests: XCTestCase {

    func testExample() throws {
        let leetCode179 = LeetCode179()
        XCTAssertEqual(leetCode179.sortNumber([3,30,34,5,9]), [9, 5, 34, 3, 30])
        XCTAssertEqual(leetCode179.largestNumber([3,30,34,5,9]), "9534330")
    }

}
