package projava;

import java.util.ArrayDeque;

public class TraverseDeep {
    public static void main(String[] args) {
        int[][] map = {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 2, 1},
            {1, 1, 1, 1, 1, 1, 1},
        };
        // traverse(map, 1, 1);
        traverseWithStack(map, 1, 1);
        char[] ch = {'.', '*', 'G', 'o'};
        for (int[] row : map) {
            for (int cell : row) {
                System.out.print(ch[cell]);
            }
            System.out.println();
        }
    }

    static boolean traverse(int[][] map, int curX, int curY) {
        switch(map[curY][curX]) {
            case 0: break; // 通路の続きの処理
            case 2: return true; // ゴール
            default: return false; // 通れない
        }
        map[curY][curX] = 3; // 通った印
        if (traverse(map, curX + 1, curY) ||
            traverse(map, curX - 1, curY) ||
            traverse(map, curX, curY + 1) ||
            traverse(map, curX, curY - 1)) {
            return true;
        }
        map[curY][curX] = 0; // ゴールにたどり着かなかったので印を戻す
        return false;
    }

    static boolean traverseWithStack(int[][] map, int curX, int curY) {
        record Position(int x, int y) {}

        var stack = new ArrayDeque<Position>();
        stack.push(new Position(curX, curY));
        // lastから取り出すと幅優先(queue)
        // for (Position p; (p = stack.pollLast()) != null;) {

        // firstから取り出すと深さ優先(stack)
        for (Position p; (p = stack.pollFirst()) != null;) {
            switch (map[p.y()][p.x()]) {
                case 0: break;
                case 2: return true; // ゴールに到達したら終了
                default: continue;
            }
            map[p.y()][p.x()] = 3;
            stack.push(new Position(p.x() + 1, p.y()));
            stack.push(new Position(p.x() - 1, p.y()));
            stack.push(new Position(p.x(), p.y() + 1));
            stack.push(new Position(p.x(), p.y() - 1));
        }
        return false;
    }
}
