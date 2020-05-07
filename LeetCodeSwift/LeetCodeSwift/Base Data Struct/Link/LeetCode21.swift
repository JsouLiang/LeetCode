//
//  LeetCode21.swift
//  LeetCode-Swift
//
//  Created by Liang on 2020/5/1.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 21. 合并两个有序链表

 */
public class LeetCode21 {
    func mergeTwoLists(_ l1: ListNode?, _ l2: ListNode?) -> ListNode? {
        var list1 = l1, list2 = l2
        let dummyNode = ListNode(-1)
        var indexNode = dummyNode
        while list1 != nil || list2 != nil {
            if list2 == nil {
                indexNode.next = list1
                break
            }
            if list1 == nil {
                indexNode.next = list2
                break
            }
            if list1!.val > list2!.val {
                indexNode.next = list2
                list2 = list2!.next
            } else {
                indexNode.next = list1
                list1 = list1!.next
            }
            indexNode = indexNode.next!
        }
        return dummyNode.next
    }
}

