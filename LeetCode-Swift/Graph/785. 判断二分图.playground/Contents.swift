
class Solution {
	var marked: [Bool]!
	/// 每个点的颜色
	var bipartiteColor: [Bool]!
	func isBipartite(_ graph: [[Int]]) -> Bool {
		marked = Array(repeating: false, count: graph.count)
		bipartiteColor = Array(repeating: false, count: graph.count)
		var isBipartite:Bool = true
		
		for index in (0..<graph.count) {
			if marked[index] == false {
				bipartiteColor[index] = true
				isBipartite = isBipartite && dfs(graph, currentIndex: index)
				
				if isBipartite == false {
					return isBipartite
				}
			}
		}
		return isBipartite
	}
	//	[[3,4,6],[3,6],[3,6],[0,1,2,5],[0,7,8],[3],[0,1,2,7],[4,6],[4],[]]
	private func dfs(_ graph: [[Int]], currentIndex: Int) -> Bool {
		marked[currentIndex] = true
		for nextPoint in graph[currentIndex] {
			if (marked[nextPoint] == false) {
				bipartiteColor[nextPoint] = !bipartiteColor[currentIndex]
				if (dfs(graph, currentIndex: nextPoint)) {
					continue
				} else {
					return false
				}
			} else if (bipartiteColor[currentIndex] == bipartiteColor[nextPoint]) {
				return false
			}
		}
		return true
	}
}
