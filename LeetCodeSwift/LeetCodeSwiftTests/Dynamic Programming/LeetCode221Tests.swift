//
//  LeetCode221Tests.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/8.
//  Copyright © 2020 imieutan. All rights reserved.
//

import XCTest
@testable import LeetCodeSwift

class LeetCode221Tests: XCTestCase {

    func testExample() throws {
        let leetcode = LeetCode221()
        XCTAssertEqual(4, leetcode.maximalSquare([
            ["1", "0", "1", "0", "0"],
            ["1", "0", "1", "1", "1"],
            ["1", "1", "1", "1", "1"],
            ["1", "0", "0", "1", "0"]
        ]))

        XCTAssertEqual(1, leetcode.maximalSquare([
            ["1"]
        ]))

        XCTAssertEqual(1, leetcode.maximalSquare([
            ["0", "1"]
        ]))

        XCTAssertEqual(1, leetcode.maximalSquare(
            [["0","0","0","0","0"],["1","0","0","0","0"],["0","0","0","0","0"],["0","0","0","0","0"]]
        ))

    }

}
