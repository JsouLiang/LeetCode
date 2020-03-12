package Basic;

import java.util.HashMap;
import java.util.Map;

public class LeetCode760 {
    public int[] anagramMappings(int[] A, int[] B) {
        int[] AIndex = new int[A.length];
        if (B == null || B.length == 0) {
            return AIndex;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            indexMap.put(B[i], i);
        }
        for (int i = 0; i < A.length; i++) {
            AIndex[i] = indexMap.get(A[i]);
        }
        return AIndex;
    }
}
