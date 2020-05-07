//
//  LeetCode572Tests.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/7.
//  Copyright © 2020 imieutan. All rights reserved.
//

import XCTest
@testable import LeetCodeSwift

class LeetCode572Tests: XCTestCase {

    func testExample() throws {
        let leetcode = LeetCode572()
        var s = Tree([3,4,5,1,2])
        var t = Tree([4,1,2])
        XCTAssertTrue(leetcode.isSubtree(s.root, t.root))

        s = Tree([3, 4, 5, 1, 2,nil, nil,nil, nil, 0])
        XCTAssertFalse(leetcode.isSubtree(s.root, t.root))

        s = Tree([1, 1])
        t = Tree([1])
        XCTAssertTrue(leetcode.isSubtree(s.root, t.root))
    }

}
