//
//  ListNode.swift
//  LeetCode-Swift
//
//  Created by Liang on 2020/5/1.
//  Copyright © 2020 imieutan. All rights reserved.
//

public class Link {
    public let head: ListNode

    public init(_ nums: [Int]) {
        let dummyNode = ListNode(-1)
        var indexNode = dummyNode
        nums.forEach { (num) in
            let currentNode = ListNode(num)
            indexNode.next = currentNode
            indexNode = currentNode
        }
        head = dummyNode.next!
    }
}

public class ListNode {
    public var val: Int
    public var next: ListNode?
    public init(_ val: Int) {
        self.val = val
        self.next = nil
    }

    public init(_ val: Int, next: ListNode?) {
        self.val = val
        self.next = next
    }
}
