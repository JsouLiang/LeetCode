//
//  DFATests.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/26.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation


import XCTest
@testable import LeetCodeSwift

class DFATests: XCTestCase {

    func testExample() throws {
        
        let dfa = LeetCode65()
        
        
        XCTAssertEqual(dfa.isNumber("32.e-80123"), true)
        XCTAssertEqual(dfa.isNumber("32.e+80123"), true)
        XCTAssertEqual(dfa.isNumber("3.e3"), true)
        XCTAssertEqual(dfa.isNumber("3.e3+"), false)
        XCTAssertEqual(dfa.isNumber("3.e3.2"), false)
        XCTAssertEqual(dfa.isNumber("3.5e+"), false)
        XCTAssertEqual(dfa.isNumber("3.5e+3"), true)
        XCTAssertEqual(dfa.isNumber("3.5e+3.5e+3.5"), false)
        XCTAssertEqual(dfa.isNumber("3."), true)
        XCTAssertEqual(dfa.isNumber("3. "), true)
        
        XCTAssertEqual(dfa.isNumber("0  "), true)
        XCTAssertEqual(dfa.isNumber("0"), true)
        XCTAssertEqual(dfa.isNumber(" 0.1 "), true)
        XCTAssertEqual(dfa.isNumber("abc"), false)
        XCTAssertEqual(dfa.isNumber("1 a"), false)
        XCTAssertEqual(dfa.isNumber("2e10"), true)
        XCTAssertEqual(dfa.isNumber("2ea10"), false)
        XCTAssertEqual(dfa.isNumber("-90e3"), true)
        XCTAssertEqual(dfa.isNumber("-1e"), false)
        XCTAssertEqual(dfa.isNumber("1 e"), false)
        XCTAssertEqual(dfa.isNumber("+1e "), false)
        XCTAssertEqual(dfa.isNumber("e3"), false)
        XCTAssertEqual(dfa.isNumber("6e-1"), true)
        XCTAssertEqual(dfa.isNumber(" 99e2.5"), false)
        XCTAssertEqual(dfa.isNumber("53.5e93"), true)
        XCTAssertEqual(dfa.isNumber(" --6"), false)
        XCTAssertEqual(dfa.isNumber("+-3"), false)
        XCTAssertEqual(dfa.isNumber("95a54e53"), false)
    }

}
