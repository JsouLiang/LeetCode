
//
//  LeetCode20.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/16.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

public class LeetCode25 {
    /// 0 1 2 3 4 5 6 7 8 9 10
    /// 0 3 2 1
    /// 0 3 2 1 6 4 5
    ///
    func reverseKGroup(_ head: ListNode?, _ k: Int) -> ListNode? {
        let dummyNode = ListNode(-1, next: head)
        var prev = dummyNode
        var start: ListNode? = head
        while true {
            var count = 0
            while count < k && start != nil{
                start = start?.next
                count += 1
            }
            if count == k {
                /// 此时 prev 的 next 在 reverse 后会变成尾部
                let next = prev.next
                reverseList(start: prev.next!, startPrevNode: prev, k: k)
                /// 下一次的 prev 是 reverse 后的尾部
                prev = next!
            } else {
                break
            }
        }
        return dummyNode.next
    }

    private func reverseList(start: ListNode, startPrevNode: ListNode, k: Int)  {
        var currentCount = 0
        var nodePointer: ListNode? = start
        var newNextHandle: ListNode = startPrevNode
        var prevNextHandle: ListNode? = nil
        while currentCount < k {
            guard let node = nodePointer else {
                break
            }
            prevNextHandle = node.next
            node.next = newNextHandle
            newNextHandle = node

            nodePointer = prevNextHandle
            currentCount += 1
        }
        start.next = prevNextHandle
        startPrevNode.next = newNextHandle

    }
}
