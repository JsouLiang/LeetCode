
class Solution {
	let direction: [(Int, Int)] = [(-1, 0), (0, -1), (1, 0), (0, 1)]	 	// 上左下右
	var distance: [[Int]]!
	func longestIncreasingPath(_ matrix: [[Int]]) -> Int {
		if matrix.count == 0 {
			return 0
		}
		var maxCount = 0
		let row = [Int](repeating: -1, count: matrix[0].count)
		distance = [[Int]](repeating: row, count: matrix.count)
		for x in (0..<matrix.count) {
			for y in (0..<matrix[x].count) {
				let value = dfs(matrix, x: x, y: y)
				if maxCount < value {
					maxCount = value
				}
			}
		}
		return maxCount
	}
	
	private func dfs(_ matrix: [[Int]], x: Int, y:Int) -> Int {
		if (distance[x][y] >= 0) {
			return distance[x][y];
		}
		
		var maxCount = 1
		for (xDel, yDel)  in direction {
			let nextX = x + xDel; let nextY = y + yDel
			if  nextX < 0 || nextX >= matrix.count ||
				nextY < 0 || nextY >= matrix[0].count ||
				matrix[nextX][nextY] >= matrix[x][y]{
				continue
			} else {
				maxCount = max(dfs(matrix, x: nextX, y: nextY) + 1, maxCount)
			}
		}
		distance[x][y] = maxCount
		return maxCount
	}
}


