//
//  LeetCode236Tests.swift
//  LeetCodeSwiftTests
//
//  Created by Liang on 2020/5/10.
//  Copyright © 2020 imieutan. All rights reserved.
//

import XCTest
@testable import LeetCodeSwift

class LeetCode236Tests: XCTestCase {

    func testExample() throws {
        let leetcode = LeetCode236()
        let s = Tree([3,5,1,6,2,0,8,nil,nil,7,4])
        XCTAssertEqual(3, leetcode.lowestCommonAncestor(s.root, TreeNode(5), TreeNode(1))?.val)
    }

}
