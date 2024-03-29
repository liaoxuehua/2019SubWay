package main.com.demo;

public class test {
    public static final int M = 10000; //代表正无穷
    public static void main(String args[]){
        //二维数组每一行分别是A、B、C、D、E各点到其余点的距离
        //A->A距离为0，常量M为正无穷。
        int [][] weight1 = {
                {0,4,M,2,M},
                {4,0,4,1,M},
                {M,4,0,1,3},
                {2,1,1,0,7},
                {M,M,3,7,0}
        };
        int start = 0;

        int[] shortPath = dijstra(weight1, start);

        for (int i = 0; i < shortPath.length; i++)
            System.out.println("从" + getChar(start) + "出发到" + getChar(i) + "的最短距离为：" + shortPath[i]);
    }
    public static char getChar(int i){
        // 0 -> A
        // 1-> B
        return  (char)(65+i);
    }
    public static int[] dijstra(int[][] weight,int start){
        // 接受一个有向图的权重矩阵，和一个起点编号start（从0编号，顶点存在数组中）
        // 返回一个int[] 数组，表示从start到它的最短路径长度
        int n = weight.length; // 顶点个数
        int[] shortPath = new int[n]; // 保存start到其他各点的最短路径
        String[] path = new String[n]; // 保存start到其他各点最短路径的字符串表示

        for (int i = 0; i < n; i++)
            path[i] = new String(start + "-->" + i);

        int[] visited = new int[n]; // 标记当前该顶点的最短路径是否已经求出,1表示已求出

        // 初始化，第一个顶点已经求出
        shortPath[start] = 0;
        visited[start] = 1;  //本身到本身距离为0，初始设置start这个点的最短路径且标识该点被访问过。

        for (int count = 1; count < n; count++) { // 要加入n-1个顶点
            int k = -1; // 选出一个距离初始顶点start最近的未标记顶点
            int dmin = Integer.MAX_VALUE;
            //在一个循环中求出未被加入的点中，最短的！
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0 && weight[start][i] < dmin) {
                    dmin = weight[start][i];
                    k = i;
                }
            }
            //求出当前未被访问的点中最短路径点后，将该点标记为访问过，且设置start到其的最短路径。
            // 将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
            shortPath[k] = dmin;
            visited[k] = 1;

            // 以k为中间点，修正从start到未访问各点的距离
            for (int i = 0; i < n; i++) {
                //如果 '起始点到当前点距离' + '当前点到某点距离' < '起始点到某点距离', 则更新
                if (visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) {
                    weight[start][i] = weight[start][k] + weight[k][i];
                    path[i] = path[k] + "-->" + i;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println("从" + start + "出发到" + i + "的最短路径为：" + path[i]);
        }
        System.out.println("=====================================");
        return shortPath;
    }
    //测试DataBuilder
}
