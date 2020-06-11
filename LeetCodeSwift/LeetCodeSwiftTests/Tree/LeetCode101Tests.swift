//
//  LeetCode101Tests.swift
//  LeetCodeSwiftTests
//
//  Created by Liang on 2020/5/31.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation


import XCTest
@testable import LeetCodeSwift

class LeetCode101Tests: XCTestCase {

    func testExample() throws {
        let leetcode = LeetCode101()
        var tree = Tree([1,2,2,3,4,4,3])
        
        XCTAssertEqual(true, leetcode.isSymmetric(tree.root))
        tree = Tree([1,2,2,nil,3,nil,3])
        XCTAssertEqual(false, leetcode.isSymmetric(tree.root))
    }

}
