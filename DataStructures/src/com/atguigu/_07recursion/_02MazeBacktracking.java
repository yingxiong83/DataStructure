package com.atguigu._07recursion;

/**
 * 迷宫回溯问题，8行7列
 *      0代表未走过
 *      1代表墙
 *      2代表已走过
 *      3代表走此步不可达终点
 *
 *      整体思路：
 *          第一步初始化迷宫
 *          第二步从（i，j)开始走迷宫
 *              ①如果已达终点则返回true，即maze[6][5]=2
 *              ②否则，开始走迷宫
 *                  若下一点为墙（1）、已走过（2）、终点不可达（3），直接返回false
 *                  否则，按策略走：先往下走、往下走不通往右走、往右走不通往上走、往上走不通往左走、往左走不通说明这是个死点置3返回false
 */
public class _02MazeBacktracking {
    public static void main(String[] args) {
        //1.创建基础迷宫框架，8行7列
        int[][] maze = createMaze();
        //2.定制化迷宫
        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[1][2] = 1;
        maze[2][2] = 1;
        printMaze(maze);

        System.out.println("=================================================");

        //3.走迷宫,maze[6][5]是终点，maze[i][j]是起点
        boolean success = go(maze, 1, 1);
        printMaze(maze);
        System.out.println("是否成功：" + success);
    }

    private static boolean go(int[][] maze, int i, int j) {
        if (maze[6][5] == 2) {
            //maze[6][5]置为2，说明已走到终点
            return true;
        } else {
            //为走到终点
            if (maze[i][j] == 0) {
                //此点之前未走过，置2
                maze[i][j] = 2;
                //定义走的策略：先往下走、往下走不通往右走、往右走不通往上走、往上走不通往左走、往左走不通说明这是个死点置3
                if (go(maze, i + 1, j)) {
                    return true;
                } else if (go(maze, i, j + 1)) {
                    return true;
                } else if (go(maze, i - 1, j)) {
                    return true;
                } else if (go(maze, i, j - 1)) {
                    return true;
                } else {
                    //此点往下、右、上、左均走不可达终点，标记未死点3
                    maze[i][j] = 3;
                    return false;
                }
            } else {
                //此点是墙（1）或已走过（2）或终点不可达（3），直接返回false
                return false;
            }
        }
    }

    private static void printMaze(int[][] maze) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(maze[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private static int[][] createMaze() {
        int[][] maze = new int[8][7];
        for (int i = 0; i < 7; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        for (int i = 1; i < 7; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
        }
        return maze;
    }
}


