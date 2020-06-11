//
//  LeetCode65.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/26.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 65. 有效数字
 验证给定的字符串是否可以解释为十进制数字。

 例如:

 "0" => true
 " 0.1 " => true
 "abc" => false
 "1 a" => false
 "2e10" => true
 " -90e3   " => true
 " 1e" => false
 "e3" => false
 " 6e-1" => true
 " 99e2.5 " => false
 "53.5e93" => true
 " --6 " => false
 "-+3" => false
 "95a54e53" => false

 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：

 数字 0-9
 指数 - "e"
 正/负号 - "+"/"-"
 小数点 - "."
 当然，在输入中，这些字符的上下文也很重要。
 */
class LeetCode65 {
    func isNumber(_ s: String) -> Bool {
        let dfa = DFAStateMachine()
        return dfa.scann(string: s)
    }
}

private class Route {
    let checkFunc: (Character)->Bool
    let targetState: State
    init(_ checkFunc: @escaping (Character)->Bool, _ targetState: State) {
        self.checkFunc = checkFunc
        self.targetState = targetState
    }
    
    func accept(_ char: Character) -> Bool {
        return checkFunc(char)
    }
}

private class State {
    let end: Bool
    let currentState: Int
    var routes: [Route]?
    
    init(_ currentState: Int, isEnd: Bool = false, acceptCheckFuncs: [Route]? = nil) {
        self.currentState = currentState
        self.routes = acceptCheckFuncs
        self.end = isEnd
    }
    
    func accept(_ character: Character) -> State? {
        guard let routes = routes else {
            return nil
        }
        for route in routes {
            if route.accept(character) {
                return route.targetState
            }
        }
        return nil
    }
    
    func inserRoute(_ route: Route) -> Void {
        if routes == nil {
            routes = []
        }
        routes!.append(route)
    }
}

private class DFAStateMachine {
    let startState: State
    
    init() {
        startState = State(0)
        let one = State(1)
        let two = State(2, isEnd: true)
        let three = State(3)
        let four = State(4)
        let five = State(5, isEnd: true)
        let six = State(6, isEnd: true)
        let seven = State(7, isEnd: true)
        let spaceState = State(8, isEnd: true)
        
        startState.inserRoute(Route(Character.isSpace, startState))
        startState.inserRoute(Route(Character.isAddOrMinus, one))
        startState.inserRoute(Route(Character.isNumber, two))
        startState.inserRoute(Route(Character.isDot, three))
        spaceState.inserRoute(Route(Character.isSpace, spaceState))
        
        one.inserRoute(Route(Character.isNumber, two))
        one.inserRoute(Route(Character.isDot, three))
        
        two.inserRoute(Route(Character.isNumber, two))
        two.inserRoute(Route(Character.isE, four))
        two.inserRoute(Route(Character.isDot, five))
        two.inserRoute(Route(Character.isSpace, spaceState))
        
        three.inserRoute(Route(Character.isNumber, seven))
        four.inserRoute(Route(Character.isAddOrMinus, three))
        four.inserRoute(Route(Character.isNumber, six))
        
        five.inserRoute(Route(Character.isNumber, seven))
        five.inserRoute(Route(Character.isE, four))
        five.inserRoute(Route(Character.isSpace, spaceState))
        
        
        six.inserRoute(Route(Character.isNumber, six))
        six.inserRoute(Route(Character.isSpace, spaceState))
        
        seven.inserRoute(Route(Character.isNumber, seven))
        seven.inserRoute(Route(Character.isE, four))
        seven.inserRoute(Route(Character.isSpace, spaceState))
        
    }
    
    func scann(string: String) -> Bool {
        var currentState = startState
        
        for character in string {
            if let nextState = currentState.accept(character) {
                currentState = nextState
            } else {
                return false
            }
        }
        
        return currentState.end
    }
}

extension Character {
    
    static func isSpace(_ char: Character) -> Bool {
        return char.isWhitespace
    }
    
    static func isNumber(_ char: Character) -> Bool {
        return char.isNumber
    }
    
    static func isAddOrMinus(_ char: Character) -> Bool {
        return char == "+" || char == "-"
    }
    
    static func isE(_ char: Character) -> Bool {
        return char == "e";
    }
    
    static func isLetter(_ char: Character) -> Bool {
        return char.isLetter
    }
    
    static func isDot(_ char: Character) -> Bool {
        return char == "."
    }
}
