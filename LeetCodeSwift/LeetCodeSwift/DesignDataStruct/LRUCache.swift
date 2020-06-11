//
//  LRU.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/25.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

/**
 146. LRU缓存机制
 设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 写入数据 put(key, value) -如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。
 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 
 */
class LRUCache {
    private let soulution: DoubleLinkSolution
    init(_ capacity: Int) {
        soulution = DoubleLinkSolution(capacity)
    }
    
    func get(_ key: Int) -> Int {
        return soulution.get(key)
    }
    
    func put(_ key: Int, _ value: Int) {
        soulution.put(key, value)
    }
}


/// 使用双向链表---- 双向链表方便增删
/// key 直接映射到链表节点，方便查找
private class DoubleLinkSolution {

    class Link {
        class Node {
            var value: Int
            var key: Int
            var prev: Node?
            var next: Node?
            init(_ value: Int, key: Int = Int.min) {
                self.value = value
                self.key = key
            }
        }
        
        let head: Node
        let tail: Node
        var length: Int
        
        init() {
            head = Node(Int.max)
            tail = Node(Int.max)
            head.next = tail
            tail.prev = head
            head.prev = nil
            tail.next = nil
            length = 0
        }
        
        func deletedNode(_ node: Node) {
            node.prev?.next = node.next
            node.next?.prev = node.prev
            length -= 1
        }
        
        func deleteLastNode() -> Node? {
            if length > 0 {
                let lastNode = tail.prev!
                deletedNode(lastNode)
                return lastNode
            }
            return nil
        }
        
        func insertNodeAtHead(_ node: Node) {

            node.next = head.next
            node.prev = head
            
            head.next?.prev = node
            head.next = node
            
            length += 1
        }
        
        func resetNodeToHead(_ node: Node) {
            deletedNode(node)
            insertNodeAtHead(node)
        }
    }
    
    typealias Node = Link.Node
    private let capacity: Int
    private let cache: Link
    
    private var key2Node: [Int: Node]
    
    init(_ capacity: Int) {
        self.capacity = capacity
        
        self.cache = Link()
        self.key2Node = [:]
    }
    
    func get(_ key: Int) -> Int {
        guard let node = key2Node[key] else {
            return -1
        }
        cache.resetNodeToHead(node)
        return node.value
    }
    
    func put(_ key: Int, _ value: Int) {
        var node = key2Node[key]
        if node == nil && cache.length == capacity {
            let lastNode = cache.deleteLastNode()
            if let lastNodeKey = lastNode?.key {
                key2Node[lastNodeKey] = nil
            }
        }
        if node == nil {
            node = Node(value, key: key)
            key2Node[key] = node!
            cache.insertNodeAtHead(node!)
        } else {
            node!.value = value
            cache.resetNodeToHead(node!)
        }
    }
    
}

/// 超时
/// 使用 Map 来存储 key 和 caches 数组中下标的映射
/// 问题：每次 get 数据后，都需要对数组进行重拍，同时更新 Map 中key 和下标的关系
private class ArraySolution {

    private struct CachedItem {
        var value: Int
        var key: Int
    }
    
    private var caches: [CachedItem]
    private let capacity: Int
    /// key -> index
    private var keyIndexMap: [Int: Int]
    
    init(_ capacity: Int) {
        self.caches = []
        self.capacity = capacity
        self.keyIndexMap = [:]
    }
    
    func get(_ key: Int) -> Int {
        if hasValue(key) {
            return getValueForKey(key)
        }
        return -1
    }
    
    func put(_ key: Int, _ value: Int) {
        let hasValue = get(key) != -1
        if caches.count == capacity && !hasValue {
            let lastValue = caches.removeLast()
            keyIndexMap[lastValue.key] = nil
        }
        /// 存在, 删除原数据
        if hasValue {
            let index = keyIndexMap[key]!
            caches.remove(at: index)
        }
        let cachedItem = CachedItem(value: value, key: key)
        caches.insert(cachedItem, at: 0)
        /// 更新位置信息
        updateIndexMapping()
    }
    
    private func hasValue(_ key: Int) -> Bool {
        return keyIndexMap[key] != nil
    }
    
    private func getValueForKey(_ key: Int) -> Int {
        let index = keyIndexMap[key]!
        let cachedItem = caches[index]
        caches.remove(at: index)
        caches.insert(cachedItem, at: 0)
        updateIndexMapping()
        return cachedItem.value
    }
    
    private func updateIndexMapping() {
        for (currentIndex, cachedValue) in caches.enumerated() {
            let currentKey = cachedValue.key
            keyIndexMap[currentKey] = currentIndex
        }
    }
}
