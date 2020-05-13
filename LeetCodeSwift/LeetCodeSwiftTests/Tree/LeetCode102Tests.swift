//
//  LeetCode102Tests.swift
//  LeetCodeSwiftTests
//
//  Created by Liang on 2020/5/13.
//  Copyright © 2020 imieutan. All rights reserved.
//

import XCTest
@testable import LeetCodeSwift

class LeetCode102Tests: XCTestCase {

    func testExample() throws {
        let leetcode = LeetCode102()
        let tree = Tree([3,9,20,nil,nil,15,7])
        
        XCTAssertEqual([[3], [9, 20], [15, 7]], leetcode.levelOrder(tree.root))

    }

}
