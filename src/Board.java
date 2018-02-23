public class Board {
    private int length;
    private int width;
    private int height;
    private int time;
    private Block[][][][] board;
    private int bombs;

    public Board(int length, int width, int height, int time, int bombs) {
        board = new Block[length][width][height][time];
        this.length = length;
        this.width = width;
        this.height = height;
        this.time = time;
        this.bombs = bombs;

        int bombsLeftToPlace = bombs;
        do {
            int lengthIndex = (int)(Math.random() * length);
            int widthIndex = (int)(Math.random() * width);
            int heightIndex = (int)(Math.random() * height);
            int timeIndex = (int)(Math.random() * time);
            if (board[lengthIndex][widthIndex][heightIndex][timeIndex] == null) {
                board[lengthIndex][widthIndex][heightIndex][timeIndex] = new Bomb();
                bombsLeftToPlace--;
            }
        } while (bombsLeftToPlace > 0);

        /*
        //places all bombs
        for (int x = 0; x < bombs; x++) {
            int[] coords = new int[4];
            for (int i = 0; i < 4; i++) {
                coords[i] = (int)(Math.random() * i == 0 ? length - 1 : i == 1 ? width - 1 : i == 2 ? height - 1 : time - 1);
            }
            if (board[coords[0]][coords[1]][coords[2]][coords[3]] == null) {
                board[coords[0]][coords[1]][coords[2]][coords[3]] = new Bomb();
            } else {
                x--;
            }
        }
        //end of bomb placement
        */

        //places all empty blocks
        for (int l = 0; l < length; l++) {
            for (int w = 0; w < width; w++) {
                for (int h = 0; h < height; h++) {
                    for (int t = 0; t < time; t++) {
                        if (board[l][w][h][t] == null) {
                            board[l][w][h][t] = new Empty(findBombNumber(l, w, h, t));
                        }
                    }
                }
            }
        }
    }

    public int findBombNumber(int length, int width, int height, int time) {
        int number = 0;
        for (int l = length - 1; l <= length + 1; l++) {
            for (int w = width - 1; w <= width + 1; w++) {
                for (int h = height - 1; h <= height + 1; h++) {
                    for (int t = time - 1; t <= time + 1; t++) {
                        try {
                            if (board[l][w][h][t].isBomb()) {
                                number++;
                            }
                        } catch (NullPointerException | IndexOutOfBoundsException ignored) {
                        }
                    }
                }
            }
        }
        return number;
    }

    public void print2DSample() {
        for (int l = 0; l < length; l++) {
            for (int w = 0; w < width; w++) {
                if (board[l][w][0][0].isBomb()) {
                    System.out.print("B");
                } else {
                    System.out.print("E");
                }
            }
            System.out.println();
        }
    }
}
