import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import java.util.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.lang.Math;
import java.util.List;

class GUI {
    private JFrame frame;
    private JPanel board;
    private JLabel info;
    private JRadioButton vonneumann;
    private JRadioButton moore;
    private JRadioButton hexleft;
    private JRadioButton hexright;
    private JRadioButton hexrand;
    private JRadioButton pentrand;
    private JRadioButton periodical;
    private JRadioButton randomly;
    private JRadioButton evenly;
    private JRadioButton randomlyR;
    private JRadioButton onClick;
    private JRadioButton stillDraw;
    private JTextField embryosNumber;
    private JTextField radius;
    private JTextField anySizeX, anySizeY;
    private JButton[][] tab;
    private JButton showBoard;
    private JButton resetBoard;
    private JButton embryosDraw;
    private JRadioButton firstSize;
    private JRadioButton secondSize;
    private JRadioButton thirdSize;
    private JRadioButton fourthSize;
    private Controller controller;
    private int n, m;
    private int global;
    private Map<Integer, Integer> ID = new HashMap<>();
    private Map<Integer, Color> ID_Color = new HashMap<>();
    private List<ColorRGB> colorRGBList = new LinkedList<>();
    Random generator = new Random();

    private MouseListener actionPress = new MouseListener() {

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            if (onClick.isSelected()) {
                String command = ((JButton) e.getSource()).getActionCommand();
                Integer pom = Integer.parseInt(command);
                int a = 0, b = 0;
                int counter = 0;
                int i, j;
                for (i = 0; i < n; i++) {
                    for (j = 0; j < m; j++) {
                        if (counter == pom) {
                            a = i;
                            b = j;
                        }
                        counter++;
                    }
                }
                global++;
                ID.put(global, global);
                controller.ID.put(global, global);
                Object source = e.getSource();
                Color color = randColor(colorRGBList);
                ((Component) source).setBackground(color);
                info.setText(" x: " + a + "   y: " + b);
                ID_Color.put(global, color);
                controller.board[a][b] = global;
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
        }
    };

    GUI()//konstruktor GUI
    {
        JButton showHide = new JButton("Show/Hide");

        frame = new JFrame("Grain growth");
        JPanel control = new JPanel();
        board = new JPanel();

        JLabel canvasSizeInfo = new JLabel("Board size");
        ButtonGroup boardSizeButtonGroup = new ButtonGroup();
        firstSize = new JRadioButton("25 x 25");
        secondSize = new JRadioButton("50 x 50");
        thirdSize = new JRadioButton("100 x 100");
        fourthSize = new JRadioButton("150 x 150");
        anySizeX = new JTextField("50");
        anySizeY = new JTextField("50");
        showBoard = new JButton("Start");
        resetBoard = new JButton("Reset");

        JLabel embryosCreationInfo = new JLabel("Embryos creation");
        randomly = new JRadioButton("Randomly");
        evenly = new JRadioButton("Evenly");
        randomlyR = new JRadioButton("Randomly with radius");
        onClick = new JRadioButton("Click");
        stillDraw = new JRadioButton("Still draw");
        ButtonGroup embryosCreationButtonGroup = new ButtonGroup();
        embryosNumber = new JTextField("10");
        radius = new JTextField("5");
        JLabel embryosNumberText = new JLabel("n:");
        JLabel radiusText = new JLabel("r:");
        embryosDraw = new JButton("Draw");

        JLabel periodicityInfo = new JLabel("Periodicity");
        periodical = new JRadioButton("Periodical");
        JRadioButton nonPeriodical = new JRadioButton("Non-periodical");
        ButtonGroup periodicityButtonGroup = new ButtonGroup();

        JLabel proximityInfo = new JLabel("Proximity");
        vonneumann = new JRadioButton("Von Neumann");
        moore = new JRadioButton("Moore");
        hexleft = new JRadioButton("Hexagonal left");
        hexright = new JRadioButton("Hexagonal right");
        hexrand = new JRadioButton("Hexagonal rand");
        pentrand = new JRadioButton("Pentagonal rand");
        ButtonGroup proximityButtonGroup = new ButtonGroup();

        info = new JLabel("Information");

        global = 0;

        canvasSizeInfo.setSize(200, 20);
        canvasSizeInfo.setLocation(450, 20);
        canvasSizeInfo.setFont(new Font(null, Font.BOLD, 14));
        firstSize.setSize(100, 20);
        firstSize.setLocation(450, 40);
        secondSize.setSize(100, 20);
        secondSize.setLocation(450, 60);
        thirdSize.setSize(100, 20);
        thirdSize.setLocation(450, 80);
        fourthSize.setSize(100, 20);
        fourthSize.setLocation(450, 100);
        anySizeX.setSize(60, 20);
        anySizeX.setLocation(450, 120);
        anySizeY.setSize(60, 20);
        anySizeY.setLocation(510, 120);
        boardSizeButtonGroup.add(firstSize);
        boardSizeButtonGroup.add(secondSize);
        boardSizeButtonGroup.add(thirdSize);
        boardSizeButtonGroup.add(fourthSize);

        showBoard.setSize(100, 50);
        showBoard.setLocation(580, 20);
        // TODO Auto-generated method stub
        ActionListener actionCreate = e -> {
            // TODO Auto-generated method stub
            if (firstSize.isSelected()) {
                start(25, 25);
            } else if (secondSize.isSelected()) {
                start(50, 50);
            } else if (thirdSize.isSelected()) {
                start(100, 100);
            } else if (fourthSize.isSelected()) {
                start(150, 150);
            } else {
                n = Integer.parseInt(anySizeX.getText());
                m = Integer.parseInt(anySizeY.getText());
                start(n, m);
            }
            showBoard.setEnabled(false);
            board.setVisible(true);
            firstSize.setEnabled(false);
            secondSize.setEnabled(false);
            thirdSize.setEnabled(false);
            fourthSize.setEnabled(false);
            randomly.setEnabled(true);
            randomlyR.setEnabled(true);
            evenly.setEnabled(true);
            onClick.setEnabled(true);
            stillDraw.setEnabled(true);
            resetBoard.setEnabled(true);
        };
        showBoard.addActionListener(actionCreate);
        resetBoard.setSize(100, 50);
        resetBoard.setLocation(580, 70);
        // TODO Auto-generated method stub
        ActionListener actionReset = e -> {
            // TODO Auto-generated method stub
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    controller.board[i][j] = 0;
                    tab[i][j].setBackground(Color.WHITE);
                }
            }
            global = 0;
            info.setText("-- Information --");
        };
        resetBoard.addActionListener(actionReset);
        resetBoard.setEnabled(false);

        embryosCreationInfo.setSize(200, 20);
        embryosCreationInfo.setLocation(450, 150);
        embryosCreationInfo.setFont(new Font(null, Font.BOLD, 14));
        randomly.setSize(120, 20);
        randomly.setLocation(450, 170);
        ActionListener actionRandomly = e -> {
            embryosNumber.setEnabled(true);
            radius.setEnabled(false);
            embryosDraw.setEnabled(true);
        };
        randomly.addActionListener(actionRandomly);
        randomly.setEnabled(false);
        evenly.setSize(120, 20);
        evenly.setLocation(450, 190);
        // TODO Auto-generated method stub
        ActionListener actionEvenly = e -> {
            // TODO Auto-generated method stub
            embryosNumber.setEnabled(true);
            radius.setEnabled(false);
            embryosDraw.setEnabled(true);
        };
        evenly.addActionListener(actionEvenly);
        evenly.setEnabled(false);
        randomlyR.setSize(120, 20);
        randomlyR.setLocation(450, 210);
        // TODO Auto-generated method stub
        ActionListener actionRandomlyR = e -> {
            // TODO Auto-generated method stub
            embryosNumber.setEnabled(true);
            radius.setEnabled(true);
            embryosDraw.setEnabled(true);
        };
        randomlyR.addActionListener(actionRandomlyR);
        randomlyR.setEnabled(false);
        onClick.setSize(120, 20);
        onClick.setLocation(450, 230);
        onClick.doClick();
        // TODO Auto-generated method stub
        ActionListener actionOnClick = e -> {
            // TODO Auto-generated method stub
            embryosDraw.setEnabled(false);
            embryosNumber.setEnabled(false);
            radius.setEnabled(false);
        };
        onClick.addActionListener(actionOnClick);
        onClick.setEnabled(false);
        stillDraw.setSize(120, 20);
        stillDraw.setLocation(450, 250);
        stillDraw.addActionListener(actionRandomly);
        stillDraw.setEnabled(false);
        embryosNumber.setSize(60, 20);
        embryosNumber.setLocation(600, 170);
        embryosNumber.setEnabled(false);
        embryosNumberText.setSize(60, 20);
        embryosNumberText.setLocation(580, 170);
        radius.setSize(60, 20);
        radius.setLocation(600, 190);
        radius.setEnabled(false);
        radiusText.setSize(60, 20);
        radiusText.setLocation(580, 190);
        embryosDraw.setSize(100, 20);
        embryosDraw.setLocation(580, 210);
        // TODO Auto-generated method stub
        //losowanie zarodków
        //losowanie zarodków poza podanym promieñ
        //losuje dop
        //(x-a)^2-(y-b)^2<r^2
        //losowane zarodki znajduja sie w obrebie kola! blad, nalezy wylosowac nowe punkty
        //jesli znaleziono punkt przerwanie pêtli
        // gdy nie mozna znalezc punktow spelniajacych zalozenia
        //Przerywamy dzialanie poniewaz Za du¿y radius lub za du¿o n
        //rownomierny rozklad ziaren
        ActionListener actionStart = new ActionListener() {
            int a = 0, b = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int numberOfEmbryos;
                if (randomly.isSelected()) //losowanie zarodków
                {
                    numberOfEmbryos = Integer.parseInt(embryosNumber.getText());
                    for (int i = 0; i < numberOfEmbryos; i++) {
                        a = generator.nextInt(n);
                        b = generator.nextInt(m);
                        if (controller.board[a][b] == 0) {
                            global++;
                            System.out.println(a + " " + b);
                            System.out.println("ID: " + global);
                            controller.board[a][b] = global;
                            ID.put(global, global);
                            controller.ID.put(global, global);
                            Color color = randColor(colorRGBList);
                            tab[a][b].setBackground(color);
                            ID_Color.put(global, color);
                        }
                    }
                } else if (randomlyR.isSelected()) //losowanie zarodków poza podanym promieñ
                {
                    int r;
                    numberOfEmbryos = Integer.parseInt(embryosNumber.getText());
                    r = Integer.parseInt(radius.getText());
                    a = generator.nextInt(n);
                    b = generator.nextInt(m);
                    int equation;
                    int[][] tabR = new int[n][2];
                    for (int i = 0; i < numberOfEmbryos; i++) {
                        tabR[i][0] = -1;
                        tabR[i][1] = -1;
                    }
                    int count = 0;
                    while (count < numberOfEmbryos) {
                        if (controller.board[a][b] == 0) {
                            tabR[count][0] = a;
                            tabR[count][1] = b;
                            global++;
                            controller.board[a][b] = global;
                            ID.put(global, global);
                            controller.ID.put(global, global);
                            Color color = randColor(colorRGBList);
                            tab[a][b].setBackground(color);
                            ID_Color.put(global, color);
                            count++;
                            for (int i = 0; i < n; i++) {
                                for (int j = 0; j < m; j++) {
                                    equation = ((int) Math.pow(i - a, 2) + (int) Math.pow(j - b, 2));
                                    if (equation == (int) Math.pow(r, 2)) {
                                        tab[i][j].setBackground(Color.RED);
                                    }
                                }
                            }
                        }
                        boolean embryoCircle = false;
                        int errorCounter = 0;
                        while (true)//losuje dop
                        {
                            a = generator.nextInt(n);
                            b = generator.nextInt(m);
                            for (int i = 0; i < numberOfEmbryos; i++) {
                                equation = ((int) Math.pow(a - tabR[i][0], 2) + (int) Math.pow(b - tabR[i][1], 2));
                                //(x-a)^2-(y-b)^2<r^2
                                if (equation <= (int) Math.pow(r, 2)) {
                                    embryoCircle = true; //losowane zarodki znajduja sie w obrebie kola! blad, nalezy wylosowac nowe punkty
                                }
                            }
                            if (!embryoCircle)//jesli znaleziono punkt przerwanie pêtli
                            {
                                break;
                            }
                            if (errorCounter > 1000000) // gdy nie mozna znalezc punktow spelniajacych zalozenia
                            {
                                break;
                            }
                            embryoCircle = false;
                            errorCounter++;
                        }
                        if (errorCounter > 1000000) //Przerywamy dzialanie poniewaz Za du¿y radius lub za du¿o n
                        {
                            info.setText("Too large radius / too many embryos");
                            break;
                        }
                    }
                } else if (evenly.isSelected())//rownomierny rozklad ziaren
                {
                    numberOfEmbryos = Integer.parseInt(embryosNumber.getText());
                    double l = Math.sqrt(numberOfEmbryos);
                    int ll = (int) l;
                    a = n / (2 * ll);
                    b = m / (2 * ll);
                    for (int i = a; i < n; i = i + 2 * a) {
                        for (int j = b; j < m; j = j + 2 * b) {
                            global++;
                            controller.board[i][j] = global;
                            ID.put(global, global);
                            controller.ID.put(global, global);
                            Color color = randColor(colorRGBList);
                            tab[i][j].setBackground(color);
                            ID_Color.put(global, color);
                        }
                    }
                } else if (stillDraw.isSelected()) {
                    numberOfEmbryos = Integer.parseInt(embryosNumber.getText());
                    int i = 0;
                    int counter = 0;
                    while (i < numberOfEmbryos) {
                        a = generator.nextInt(n);
                        b = generator.nextInt(m);
                        if (controller.board[a][b] == 0) {
                            global++;
                            controller.board[a][b] = global;
                            ID.put(global, global);
                            controller.ID.put(global, global);
                            Color color = randColor(colorRGBList);
                            tab[a][b].setBackground(color);
                            ID_Color.put(global, color);
                            i++;
                        }
                        counter++;
                        if (counter > 1000000) {
                            info.setText("The operation can not be performed");
                            break;
                        }
                    }
                }
            }
        };
        embryosDraw.addActionListener(actionStart);
        embryosDraw.setEnabled(false);
        embryosCreationButtonGroup.add(randomly);
        embryosCreationButtonGroup.add(evenly);
        embryosCreationButtonGroup.add(randomlyR);
        embryosCreationButtonGroup.add(onClick);
        embryosCreationButtonGroup.add(stillDraw);

        periodicityInfo.setSize(200, 20);
        periodicityInfo.setLocation(450, 280);
        periodicityInfo.setFont(new Font(null, Font.BOLD, 14));
        periodical.setSize(120, 20);
        periodical.setLocation(450, 300);
        periodical.doClick();
        nonPeriodical.setSize(120, 20);
        nonPeriodical.setLocation(450, 320);
        periodicityButtonGroup.add(periodical);
        periodicityButtonGroup.add(nonPeriodical);

        proximityInfo.setSize(200, 20);
        proximityInfo.setLocation(450, 350);
        proximityInfo.setFont(new Font(null, Font.BOLD, 14));
        vonneumann.setSize(120, 20);
        vonneumann.setLocation(450, 370);
        moore.setSize(120, 20);
        moore.setLocation(450, 390);
        moore.doClick();
        hexleft.setSize(120, 20);
        hexleft.setLocation(450, 410);
        hexright.setSize(120, 20);
        hexright.setLocation(450, 430);
        hexrand.setSize(120, 20);
        hexrand.setLocation(450, 450);
        pentrand.setSize(120, 20);
        pentrand.setLocation(450, 470);
        proximityButtonGroup.add(vonneumann);
        proximityButtonGroup.add(moore);
        proximityButtonGroup.add(hexleft);
        proximityButtonGroup.add(hexright);
        proximityButtonGroup.add(hexrand);
        proximityButtonGroup.add(pentrand);

        info.setSize(200, 20);
        info.setLocation(50, 20);
        info.setFont(new Font(null, Font.BOLD, 14));

        control.add(canvasSizeInfo);
        control.add(embryosCreationInfo);
        control.add(periodicityInfo);
        control.add(proximityInfo);
        control.add(vonneumann);
        control.add(moore);
        control.add(hexleft);
        control.add(hexright);
        control.add(hexrand);
        control.add(pentrand);
        control.add(periodical);
        control.add(nonPeriodical);
        control.add(randomly);
        control.add(evenly);
        control.add(randomlyR);
        control.add(onClick);
        control.add(embryosNumber);
        control.add(radius);
        control.add(stillDraw);
        control.add(firstSize);
        control.add(secondSize);
        control.add(thirdSize);
        control.add(fourthSize);
        control.add(showBoard);
        control.add(resetBoard);
        control.add(embryosNumberText);
        control.add(radiusText);
        control.add(embryosDraw);
        control.add(info);
        control.add(anySizeX);
        control.add(anySizeY);

        control.setLayout(null);
        control.setSize(800, 1080);
        control.setLocation(1100, 0);
        board.setLayout(null);
        board.setSize(1000, 1000);
        board.setLocation(20, 20);
        //krecac kólkiem spowodujemy rozrost ziaren
        // TODO Auto-generated method stub
        MouseWheelListener mouseWheelListener = e -> {
            long millisActualTime = System.currentTimeMillis(); // pocz?tkowy czas w milisekundach.
            // TODO Auto-generated method stub
            startCount();
            long executionTime = System.currentTimeMillis() - millisActualTime; // czas wykonania programu w milisekundach.
            System.out.println("Czas rysowania+oblicze?: " + executionTime);
        };
        board.addMouseWheelListener(mouseWheelListener);
        frame.setLayout(null);
        frame.add(control);
        frame.setSize(1920, 1080);
        frame.setLocation(0, 0);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void start(int a, int b) {
        if (a > 250) a = 250;
        if (b > 250) b = 250;
        n = a;
        m = b;
        double sizeX = 1000.0 / (double) n;
        double sizeY = 1000.0 / (double) m;
        tab = new JButton[n][m];
        Rectangle[][] rectangles = new Rectangle[n][m];
        controller = new Controller(n, m);
        controller.ID.put(global, global);
        ID.put(global, global);
        ID_Color.put(global, Color.WHITE);
        int helpVariable = 0;

        //Graphics2D graphics2D = (Graphics2D) frame.getGraphics();


        long start, stop;
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tab[i][j] = new JButton();
                rectangles[i][j] = new Rectangle();
                tab[i][j].setSize((int) sizeX, (int) sizeY);
                rectangles[i][j].setSize((int) sizeX, (int) sizeY);
                tab[i][j].setBackground(Color.WHITE);
                tab[i][j].setLocation((int) sizeX * i, (int) sizeY * j);
                tab[i][j].setBorder(null);
                tab[i][j].addMouseListener(actionPress);
                String StringCommand = Integer.toString(helpVariable);
                tab[i][j].setActionCommand(StringCommand);
                board.add(tab[i][j]);
                helpVariable++;

                //graphics2D.fillRect(i, j, 1, 1);
            }
        }
        stop = System.currentTimeMillis();
        System.out.println("Time: " + (stop - start));
        long millisActualTime = System.currentTimeMillis(); // pocz?tkowy czas w milisekundach.
        board.setVisible(false);
        frame.add(board);
        long executionTime = System.currentTimeMillis() - millisActualTime; // czas wykonania programu w milisekundach.
        System.out.println("Czas setVisible i frame: " + executionTime);
    }


    private Color randColor(List<ColorRGB> colorRGBList)//metoda losujaca kolor zarodka
    {
        int r = 0, g = 0, b = 0;
        ColorRGB testColor;
        boolean isExist = true;
        while (isExist) {
            isExist = false;
            r = generator.nextInt(256);
            g = generator.nextInt(256);
            b = generator.nextInt(256);
            testColor = new ColorRGB(r, g, b);
            for (ColorRGB aColorRGBList : colorRGBList) if (testColor.isEqual(aColorRGBList)) isExist = true;
        }
        Color col = new Color(r, g, b);
        colorRGBList.add(new ColorRGB(r, g, b));
        return col;
    }

    private void startCount() {
        long millisActualTime = System.currentTimeMillis(); // pocz?tkowy czas w milisekundach.
        boolean periodicity;
        periodicity = periodical.isSelected();
        if (moore.isSelected()) {
            controller.count(1, periodicity);
        } else if (vonneumann.isSelected()) {
            controller.count(2, periodicity);
        } else if (hexleft.isSelected()) {
            controller.count(3, periodicity);
        } else if (hexright.isSelected()) {
            controller.count(4, periodicity);
        } else if (hexrand.isSelected()) {
            controller.count(5, periodicity);
        } else if (pentrand.isSelected()) {
            controller.count(6, periodicity);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tab[i][j].setBackground(ID_Color.get(ID.get(controller.board[i][j])));
            }
        }
        long executionTime = System.currentTimeMillis() - millisActualTime; // czas wykonania programu w milisekundach.
        System.out.println("Czas startCount: " + executionTime);
    }

}
