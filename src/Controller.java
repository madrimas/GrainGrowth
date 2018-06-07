import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Controller {
    private int n, m;
    int[][] board;
    Map<Integer, Integer> ID = new HashMap<>();
    private int[][] tab = new int[3][3];
    Random generator = new Random();

    Controller(int n, int m) {
        this.n = n;
        this.m = m;
        this.board = new int[this.n][this.m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    private int moore(int x, int y, boolean w) {
        if (w) {
            tab = period(x, y);
        } else {
            tab = noperiod(x, y);
        }
        int[] pom = proximity(tab);
        int max = 0;
        int max2 = 0;
        for (int i = 0; i < ID.size(); i++)//sprawdzamy jakich sasiadow ma najwiecej i takie id przyjmie dane pole
        {
            if (pom[i] > max && i != 0) {
                max = pom[i];
                max2 = i;
            }
        }
        return max2;

    }

    private int neumann(int x, int y, boolean w) {
        if (w) {
            tab = period(x, y);
        } else {
            tab = noperiod(x, y);
        }
        tab[0][0] = 0;
        tab[0][2] = 0;
        tab[2][0] = 0;
        tab[2][2] = 0;
        int[] pom = proximity(tab);
        int max = 0;
        int max2 = 0;
        for (int i = 0; i < ID.size(); i++) {
            if (pom[i] > max && i != 0) {
                max = pom[i];
                max2 = i;
            }
        }
        return max2;
    }

    private int hexleft(int x, int y, boolean w) {
        if (w) {
            tab = period(x, y);
        } else {
            tab = noperiod(x, y);
        }
        tab[0][2] = 0;
        tab[2][0] = 0;
        int[] pom = proximity(tab);
        int max = 0;
        int max2 = 0;
        for (int i = 0; i < ID.size(); i++) {
            if (pom[i] > max && i != 0) {
                max = pom[i];
                max2 = i;
            }
        }
        return max2;
    }

    private int hexright(int x, int y, boolean w) {
        if (w) {
            tab = period(x, y);
        } else {
            tab = noperiod(x, y);
        }
        tab[0][0] = 0;
        tab[2][2] = 0;
        int[] pom = proximity(tab);
        int max = 0;
        int max2 = 0;
        for (int i = 0; i < ID.size(); i++) {
            if (pom[i] > max && i != 0) {
                max = pom[i];
                max2 = i;
            }
        }
        return max2;
    }

    private int hexrand(int x, int y, boolean w) {
        if (w) {
            tab = period(x, y);
        } else {
            tab = noperiod(x, y);
        }
        int a = generator.nextInt(2);
        if (a == 0) {
            tab[0][2] = 0;
            tab[2][0] = 0;
        } else if (a == 1) {
            tab[0][0] = 0;
            tab[2][2] = 0;
        }
        int[] pom = proximity(tab);
        int max = 0;
        int max2 = 0;
        for (int i = 0; i < ID.size(); i++) {
            if (pom[i] > max && i != 0) {
                max = pom[i];
                max2 = i;
            }
        }
        return max2;

    }

    private int pentrand(int x, int y, boolean w) {
        if (w) {
            tab = period(x, y);
        } else {
            tab = noperiod(x, y);
        }
        int a = generator.nextInt(4);
        if (a == 0) {
            tab[0][0] = 0;
            tab[1][0] = 0;
            tab[2][0] = 0;
        } else if (a == 1) {
            tab[0][2] = 0;
            tab[1][2] = 0;
            tab[2][2] = 0;
        } else if (a == 2) {
            tab[0][2] = 0;
            tab[0][1] = 0;
            tab[0][2] = 0;
        } else if (a == 3) {
            tab[2][0] = 0;
            tab[2][1] = 0;
            tab[2][2] = 0;
        }
        int[] pom = proximity(tab);
        int max = 0;
        int max2 = 0;
        for (int i = 0; i < ID.size(); i++) {
            if (pom[i] > max && i != 0) {
                max = pom[i];
                max2 = i;
            }
        }
        return max2;
    }

    private int[] proximity(int[][] tab) {
        int[] idSize = new int[ID.size()];
        for (int i = 0; i < ID.size(); i++) {
            idSize[i] = 0;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != 1 || j != 1)  {
                    for (int k = 0; k < ID.size(); k++) {
                        if (tab[i][j] == ID.get(k)) {
                            idSize[k]++;
                        }
                    }
                }
            }
        }
        return idSize;
    }

    private int[][] period(int x, int y) {
        //warunki periodczyne
        if (x == 0 && y == 0) {
            tab[0][0] = this.board[n - 1][m - 1];
            tab[0][1] = this.board[n - 1][y];
            tab[0][2] = this.board[n - 1][y + 1];
            tab[1][0] = this.board[x][m - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][m - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x == 0 && y == this.m - 1) {
            tab[0][0] = this.board[n - 1][y - 1];
            tab[0][1] = this.board[n - 1][y];
            tab[0][2] = this.board[n - 1][0];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][0];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][0];
        } else if (x == this.n - 1 && y == 0) {
            tab[0][0] = this.board[x - 1][m - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][m - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[0][m - 1];
            tab[2][1] = this.board[0][y];
            tab[2][2] = this.board[0][y + 1];
        } else if (x == this.n - 1 && y == this.m - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][0];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][0];
            tab[2][0] = this.board[0][y - 1];
            tab[2][1] = this.board[0][y];
            tab[2][2] = this.board[0][0];
        } else if (x == 0 && y > 0 && y < this.m - 1) {
            tab[0][0] = this.board[n - 1][y - 1];
            tab[0][1] = this.board[n - 1][y];
            tab[0][2] = this.board[n - 1][y + 1];
            ;
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x > 0 && x < this.n - 1 && y == 0) {
            tab[0][0] = this.board[x - 1][m - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][m - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][m - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x == this.n - 1 && y > 0 && y < this.m - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[0][y - 1];
            tab[2][1] = this.board[0][y];
            tab[2][2] = this.board[0][y + 1];
        } else if (y == this.m - 1 && x > 0 && x < this.n - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][0];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][0];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][0];
        } else {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        }
        return tab;
    }

    private int[][] noperiod(int x, int y) {
        if (x == 0 && y == 0) {
            tab[0][0] = 0;
            tab[0][1] = 0;
            tab[0][2] = 0;
            tab[1][0] = 0;
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = 0;
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x == 0 && y == this.m - 1) {
            tab[0][0] = 0;
            tab[0][1] = 0;
            tab[0][2] = 0;
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = 0;
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = 0;
        } else if (x == this.n - 1 && y == 0) {
            tab[0][0] = 0;
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = 0;
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = 0;
            tab[2][1] = 0;
            tab[2][2] = 0;
        } else if (x == this.n - 1 && y == this.m - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = 0;
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = 0;
            tab[2][0] = 0;
            tab[2][1] = 0;
            tab[2][2] = 0;
        } else if (x == 0 && y > 0 && y < this.m - 1) {
            tab[0][0] = 0;
            tab[0][1] = 0;
            tab[0][2] = 0;
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x > 0 && x < this.n - 1 && y == 0) {
            tab[0][0] = 0;
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = 0;
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = 0;
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        } else if (x == this.n - 1 && y > 0 && y < this.m - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = 0;
            tab[2][1] = 0;
            tab[2][2] = 0;
        } else if (y == this.m - 1 && x > 0 && x < this.n - 1) {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = 0;
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = 0;
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = 0;
        } else {
            tab[0][0] = this.board[x - 1][y - 1];
            tab[0][1] = this.board[x - 1][y];
            tab[0][2] = this.board[x - 1][y + 1];
            tab[1][0] = this.board[x][y - 1];
            tab[1][1] = this.board[x][y];
            tab[1][2] = this.board[x][y + 1];
            tab[2][0] = this.board[x + 1][y - 1];
            tab[2][1] = this.board[x + 1][y];
            tab[2][2] = this.board[x + 1][y + 1];
        }

        return tab;
    }

    void count(int a, boolean b) {
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        int[][] apt = new int[this.n][this.m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (this.board[i][j] == 0) {
                    if (a == 1) {
                        apt[i][j] = moore(i, j, b);
                    } else if (a == 2) {
                        apt[i][j] = neumann(i, j, b);
                    } else if (a == 3) {
                        apt[i][j] = hexleft(i, j, b);
                    } else if (a == 4) {
                        apt[i][j] = hexright(i, j, b);
                    } else if (a == 5) {
                        apt[i][j] = hexrand(i, j, b);
                    } else if (a == 6) {
                        apt[i][j] = pentrand(i, j, b);
                    }
                } else {
                    apt[i][j] = this.board[i][j];
                }

            }
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(apt[i], 0, this.board[i], 0, m);
        }
        long executionTime = System.currentTimeMillis() - millisActualTime; // czas wykonania programu w milisekundach.
        System.out.println("Czas count: " + executionTime);
    }


}
