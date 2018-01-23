// LeetCode 72, 字符串的相似算法
// Levenshtein Distance

var minDistance = function(word1, word2) {
    let m = word1.length
    let n = word2.length
	console.log(`m: ${m} n: ${n}`)

    let dp = new Array();             //声明一维数组        
    for(var x = 0;x <= m;x++){
        dp[x] = new Array();         //声明二维数组
        for(var y=0;y <= n;y++){
            dp[x][y]=0;              //数组初始化为0
        }
    }

    for (let i = 0; i <= n; i++) {
        dp[0][i] = i
    }
    for (let i = 0; i <= m; i++) {
        dp[i][0] = i
    }

    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            if (word1[i] == word2[j]) {
                dp[i][j] = dp[i - 1][j - 1]
            } else {
                dp[i][j] = Math.min(
                    dp[i - 1][j] + 1,       // Insertions
                    dp[i][j - 1] + 1,       // Deletions
                    dp[i - 1][j - 1] + 1,   // Substitution
                )
            }
        }
    }
    return dp[m][n]
};