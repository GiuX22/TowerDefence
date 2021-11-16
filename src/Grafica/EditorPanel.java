package Grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import Grafica.Button.EditorPanelButton.BackgroundButton;
import Grafica.Button.EditorPanelButton.BaseButton;
import Grafica.Button.EditorPanelButton.EraseButton;
import Grafica.Button.EditorPanelButton.GeneratorButton;
import Grafica.Button.EditorPanelButton.HomeButtonEditor;
import Grafica.Button.EditorPanelButton.InfoButton;
import Grafica.Button.EditorPanelButton.SaveButton;
import Grafica.Button.EditorPanelButton.StartGameButton;
import Grafica.Button.EditorPanelButton.StreetButton;
import Grafica.Button.EditorPanelButton.TestButton;
import Grafica.Button.EditorPanelButton.WebCamButton;
import PlugIn.LoadMapWebCam.LoaderMapCam;
import logicaOffline.Manager.GameManager;
import logicaOffline.common.AbstractStaticObject;
import logicaOffline.utility.AePlayWave;
import logicaOffline.utility.BackgroudSelector;
import logicaOffline.utility.ScreenshotMaker;
import logicaOffline.utility.TestPath;
import logicaOffline.utility.WorldsLinker;

public class EditorPanel extends JPanel {

    // Dichiarazione Variabili
    MainFrame mainFrame;
    GameManager gameManager;
    ImageProvider imageProvider;

    // Plug-in WorldFromWebCam
    private LoaderMapCam loaderMapCam;

    public int matrixHeight;// Inizializzati nel PaintComponent Per avere le
    // Dimensioni effettive del pannello
    public int matrixWidth;//

    private static int rows = 15;
    private static int columns = 25;

    private int cellWidth;
    private int cellHeight;

    private int enterRow;
    private int enterColumns;
    private int selectedRow;
    private int selectedColumn;
    private int[][] matrix = new int[rows][columns];

    // Lista Bottoni Pannello laterale
    private List<JButton> buttonGroupMenu;

    private StreetButton buttonStreet;
    private GeneratorButton buttonGenerator;
    private BaseButton buttonBase;
    private EraseButton buttonErase;
    private EraseButton buttonEraseAll;
    private SaveButton buttonSave;
    private TestButton buttonTest;
    private StartGameButton buttonStart;
    private BackgroundButton buttonBG;
    private Image backgroundImage;

    private boolean isImageValid;
    private BackgroudSelector bgSelector;
    private ScreenshotMaker screenshot;

    private boolean enableGrid;
    private boolean enableBgImage;

    // Lista Bottoni Pannello di sotto
    private List<JButton> buttonGroupUtility;

    private HomeButtonEditor homeButton;
    private InfoButton infoButton;
    private WebCamButton webcamButton;

    // Pannello per i mondi presenti! :D

    private DefaultListModel<String> listModel;
    private JScrollPane worldScrollList;
    File folder;
    File[] listOfFiles;

    JList<String> worldList;

    // IMMAGINI
    Image street;
    Image generator;
    Image base;
    Image rubber;
    Image invalidStreetImage;
    Image warningStreet;

    Image messageFrame;

    // Variabili MOUSE
    private int mouseX;
    private int mouseY;
    private boolean streetIsPressed;
    private boolean generatorIsPressed;
    private boolean placedGenerator;
    private boolean baseIsPressed;
    private boolean placedBase;
    private boolean rubberIsPressed;

    public boolean invalidStreet;

    private boolean testPass;

    private TestPath testPath;

    private String backgroundNames;


    private int checkPath;
    // Variabili Utility
    private JLabel messageBox;
    private Grid gridPanel;
    // Variabili per salvare/caricare il world
    final JFileChooser fileChooser = new JFileChooser();
    public List<AbstractStaticObject> solitaryPieces;
    public List<AbstractStaticObject> tightCurves;

    @Override
    protected void paintComponent(Graphics g) {

        // TODO Trovare il modo di stampare gli abbellimenti laterali

    }

    // Costruttore Editor Principale
    public EditorPanel(MainFrame mainFrame) {

        super(new BorderLayout());

        // Inizializzazione Variabili Costruttore
        this.mainFrame = mainFrame;
        this.imageProvider = this.mainFrame.getImageProvider();

        // Precaricamento Immagini
        street = imageProvider.getImage("Street.png");
        generator = imageProvider.getImage("Generator.png");
        base = imageProvider.getImage("Base.png");
        rubber = imageProvider.getImage("./ImgEditor/Rubber.png");
        invalidStreetImage = imageProvider.getImage("StreetInvalid.png");
        warningStreet = imageProvider.getImage("StreetWarning.png");
        messageFrame = mainFrame.getImageProvider().getImage("./ImgEditor/MessageBox.png");


        invalidStreet = false;
        solitaryPieces = new ArrayList<AbstractStaticObject>();
        tightCurves = new ArrayList<AbstractStaticObject>();

        screenshot = new ScreenshotMaker();
        testPass = false;
        testPath = new TestPath();
        enableGrid = true;
        enableBgImage = false;

        isImageValid = false;

        inizializeMatrix();

        setBackground(Color.RED);
        gridPanel = new Grid(this);
        add(gridPanel, BorderLayout.CENTER);
        add(new ItemsMenu(this), BorderLayout.EAST);
        add(new UtilityMenu(this), BorderLayout.SOUTH);
        loaderMapCam = new LoaderMapCam(this);
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    private void inizializeMatrix() {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    // Altre Classi Panel Di supporto per BorderLayout
    public class Grid extends JPanel {

        boolean isOnCell;

        // COSTR GRIGLIA
        public Grid(EditorPanel editorPanel) {

            isOnCell = false;
            placedBase = false;
            placedGenerator = false;

            addMouseMotionListener(new MouseMotionListener() {

                @Override
                public void mouseMoved(MouseEvent e) {

                    cellWidth = matrixWidth / columns;
                    cellHeight = matrixHeight / rows;

                    if (e.getX() < (cellWidth * 25) && e.getY() < (cellHeight * 15)) {
                        selectedColumn = (int) e.getX() / cellWidth;
                        selectedRow = (int) e.getY() / cellHeight;
                        isOnCell = true;
                        repaint();
                    }

                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    putPiece(e);
                }

            });

            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {

                    if(invalidStreet)
                    {
                        correctMatrix();
                        invalidStreet = false;
                    }

                    putPiece(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);

                    repaint();
                }

            });
        }

        protected void putPiece(MouseEvent e) {

            cellWidth = matrixWidth / columns;
            cellHeight = matrixHeight / rows;

            selectedColumn = (int) e.getX() / cellWidth;
            selectedRow = (int) e.getY() / cellHeight;

            correctMatrix();

            if (e.getX() < (cellWidth * 25) && e.getY() < (cellHeight * 15)) {
                if (streetIsPressed) {
                    if (matrix[selectedRow][selectedColumn] != 2
                            && matrix[selectedRow][selectedColumn] != 3) {
                        matrix[selectedRow][selectedColumn] = 1;
                    } else {
                        messageBox.setText("<html><b>Per Eliminare questo pezzo usa la 'Gomma'</b></html>");
                    }

                    repaint();
                }

                if (baseIsPressed) {
                    if (!placedBase) {
                        if (matrix[selectedRow][selectedColumn] != 2) {
                            matrix[selectedRow][selectedColumn] = 3;
                            placedBase = true;
                        }
                        else
                        {
                            messageBox.setText("<html><b>Per Eliminare questo pezzo usa la 'Gomma'</b></html>");
                        }

                    }
                    else
                    {
                        messageBox.setText("<html><b>Si Puň avere Solo un pezzo 'Base' per Livello!</b></html>");
                    }

                    repaint();

                }

                if (generatorIsPressed) {

                    if (!placedGenerator) {
                        if (matrix[selectedRow][selectedColumn] != 3) {
                            matrix[selectedRow][selectedColumn] = 2;
                            placedGenerator = true;
                        }
                        else
                        {
                            messageBox.setText("<html><b>Per Eliminare questo pezzo usa la 'Gomma'</b></html>");
                        }

                    }
                    else
                    {
                        messageBox.setText("<html><b>Si Puň avere Solo un pezzo 'Generatore' per Livello!</b></html>");                    }
                    repaint();
                }
                if (rubberIsPressed) {

                    if (matrix[selectedRow][selectedColumn] == 3) {
                        placedBase = false;
                    }
                    if (matrix[selectedRow][selectedColumn] == 2) {
                        placedGenerator = false;

                    }
                    matrix[selectedRow][selectedColumn] = 0;



                    repaint();
                }
            }

        }

        public void correctMatrix() {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if(matrix[i][j] == 5 || matrix[i][j] == 6)
                    {
                        matrix[i][j]  = 1;
                    }
                }
            }

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Color bgColor = new Color(61, 36, 154);

            if(!enableBgImage)
            {
                setBackground(bgColor);
            }
            else
            {
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
            }
            printWorld(g);

            matrixWidth = this.getWidth();
            matrixHeight = this.getHeight();

            cellWidth = (int) matrixWidth / columns;
            cellHeight = (int) matrixHeight / rows;

            g.setColor(Color.WHITE);

            if(enableGrid)
            {

                for (int i = 0; i <= rows; i++) {
                    g.drawLine(0, i * cellHeight, matrixWidth
                            - (matrixWidth - (cellWidth * columns)), i * cellHeight);

                }

                for (int j = 0; j <= columns; j++) {
                    g.drawLine(j * cellWidth, 0, j * cellWidth, matrixHeight
                            - (matrixHeight - (cellHeight * rows)));
                }

            }
//            if (isOnCell && (selectedColumn < (cellWidth * 25))
//                    && (selectedRow < (cellHeight * 15))) {
//                g.setColor(Color.BLACK);
//                g.drawRect(selectedColumn * cellWidth,
//                        selectedRow * cellHeight, cellWidth, cellHeight);
//            }
//            else
//            {
//                this.repaint();
//            }


            if (isOnCell && streetIsPressed) {
                g.drawImage(street, selectedColumn * cellWidth, selectedRow
                        * cellHeight, cellWidth, cellHeight, null);
                isOnCell = false;
            }
            if (isOnCell && baseIsPressed) {
                g.drawImage(base, selectedColumn * cellWidth, selectedRow
                        * cellHeight, cellWidth, cellHeight, null);
                isOnCell = false;
            }
            if (isOnCell && generatorIsPressed) {

                g.drawImage(generator, selectedColumn * cellWidth, selectedRow
                        * cellHeight, cellWidth, cellHeight, null);
                isOnCell = false;
            }
            if (isOnCell && rubberIsPressed) {

                g.drawImage(rubber, selectedColumn * cellWidth, selectedRow
                        * cellHeight, cellWidth, cellHeight, null);
                isOnCell = false;

            }

        }

        private void printWorld(Graphics g) {

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {

                    if (matrix[i][j] == 1) {




                        g.drawImage(street, j * cellWidth, i * cellHeight,cellWidth, cellHeight, null);
                    }

                    if (matrix[i][j] == 3) {
                        g.drawImage(base, j * cellWidth, i * cellHeight,cellWidth, cellHeight, null);
                    }

                    if (matrix[i][j] == 2) {
                        g.drawImage(generator, j * cellWidth, i * cellHeight,cellWidth, cellHeight, null);
                    }
                    if (matrix[i][j] == 5) {
                        g.drawImage(invalidStreetImage, j * cellWidth, i* cellHeight, cellWidth, cellHeight, null);
                    }
                    if (matrix[i][j] == 6) {
                        g.drawImage(warningStreet, j * cellWidth, i* cellHeight, cellWidth, cellHeight, null);
                    }

                }
            }
        }
    }

    // CLASSE PER IL MENU LATERALE DESTRO
    public class ItemsMenu extends JPanel implements MouseListener,
    MouseMotionListener {




        public ItemsMenu(EditorPanel editorPanel) {
            this.setPreferredSize(new Dimension(180, editorPanel.matrixHeight)); // Da

            GridBagLayout layout = new GridBagLayout();
            this.setLayout(layout);
            GridBagConstraints c = new GridBagConstraints();

            streetIsPressed = false;
            generatorIsPressed = false;
            baseIsPressed = false;
            rubberIsPressed = false;
            buttonGroupMenu = new ArrayList<JButton>();

            buttonStreet = new StreetButton("streetButton", 65, 65, mainFrame);
            buttonGroupMenu.add(buttonStreet);
            buttonGenerator = new GeneratorButton("generatorButton", 65, 65, mainFrame);
            buttonGroupMenu.add(buttonGenerator);
            buttonBase = new BaseButton("baseButton", 65, 65, mainFrame);
            buttonGroupMenu.add(buttonBase);
            buttonErase = new EraseButton("eraseButton", 65, 140, mainFrame,"GommaNormal");
            buttonGroupMenu.add(buttonErase);
            buttonSave = new SaveButton("saveButton", 65, 140, mainFrame);
            buttonGroupMenu.add(buttonSave);
            buttonTest = new TestButton("testButton", 65, 140, mainFrame);
            buttonGroupMenu.add(buttonTest);
            buttonStart = new StartGameButton("startGameButton", 65, 140, mainFrame);
            buttonGroupMenu.add(buttonStart);
            buttonEraseAll = new EraseButton("clearAll", 65, 140, mainFrame, "ClearAll");
            buttonGroupMenu.add(buttonEraseAll);
            buttonBG = new BackgroundButton("backgroundButton", 65, 140, mainFrame);
            buttonGroupMenu.add(buttonBG);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 2;
            c.gridheight = 1;
            c.weighty = 1;
            layout.setConstraints(buttonStreet, c);
            this.add(buttonStreet, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 1;
            this.add(buttonGenerator, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 1;
            this.add(buttonBase, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            c.gridwidth = 2;
            c.gridheight = 1;
            layout.setConstraints(buttonErase, c);
            this.add(buttonErase, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 3;
            c.gridwidth = 2;
            c.gridheight = 1;
            layout.setConstraints(buttonEraseAll, c);
            this.add(buttonEraseAll, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 4;
            c.gridwidth = 2;
            c.gridheight = 1;
            layout.setConstraints(buttonSave, c);
            this.add(buttonSave, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 5;
            c.gridwidth = 2;
            c.gridheight = 1;
            layout.setConstraints(buttonTest, c);
            this.add(buttonTest, c);


            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 6;
            c.gridwidth = 2;
            c.gridheight = 1;
            layout.setConstraints(buttonBG, c);
            this.add(buttonBG, c);


            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 7;
            c.gridwidth = 2;
            c.gridheight = 1;
            c.weighty = 3;
            layout.setConstraints(buttonStart, c);
            this.add(buttonStart, c);

            for (JButton button : buttonGroupMenu) {
                button.addMouseListener(this);
            }
        }


        // PAINTCOMPONENT MENU DESTRO!!!
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            printWorld(g);

        }

        private void printWorld(Graphics g) {
            Image sfondoMenu;
            sfondoMenu = imageProvider.getImage("SfondoMenu.png");
            g.drawImage(sfondoMenu, 0, 0, this.getWidth(), this.getHeight(), null);
        }

        @Override
        public void mouseDragged(MouseEvent e) {}
        @Override
        public void mouseMoved(MouseEvent me) {
            mouseX = me.getX();
            mouseY = me.getY();
        }
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e)
        {
            Object event = e.getSource();

            if (event instanceof JButton) {

                String key = ((JButton) event).getName();

                switch (key) {

                case "streetButton":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    break;
                case "generatorButton":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    break;
                case "baseButton":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    break;
                case "eraseButton":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    buttonErase.PressButton();
                    this.repaint();
                    break;
                case "clearAll":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    buttonEraseAll.PressButton();
                    this.repaint();
                    break;
                case "saveButton":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    buttonSave.PressButton();
                    this.repaint();
                    break;
                case "testButton":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    buttonTest.PressButton();
                    this.repaint();
                    break;
                case "backgroundButton":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    buttonBG.PressButton();
                    this.repaint();
                    break;
                case "startGameButton":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    buttonStart.PressButton();
                    this.repaint();
                    break;
                }
            }
        }
        @Override
        public void mouseExited(MouseEvent e)
        {
            Object event = e.getSource();

            if (event instanceof JButton) {

                String key = ((JButton) event).getName();

                switch (key) {

                case "streetButton":

                    break;
                case "generatorButton":

                    break;
                case "baseButton":

                    break;
                case "eraseButton":
                    if(!rubberIsPressed)
                    {
                        buttonErase.ReleaseButton();
                        this.repaint();
                    }
                    break;
                case "clearAll":
                    buttonEraseAll.ReleaseButton();
                    this.repaint();
                    break;
                case "saveButton":
                    buttonSave.ReleaseButton();
                    this.repaint();
                    break;
                case "testButton":
                    buttonTest.ReleaseButton();
                    this.repaint();
                    break;
                case "backgroundButton":
                    buttonBG.ReleaseButton();
                    this.repaint();
                    break;
                case "startGameButton":
                    buttonStart.ReleaseButton();
                    this.repaint();
                    break;
                }
            }
        }



        @Override
        public void mousePressed(MouseEvent e) {

            Object event = e.getSource();
            if(SwingUtilities.isRightMouseButton(e))
            {

                disableAllButton();
            }
            else

                if (event instanceof JButton) {

                    String key = ((JButton) event).getName();

                    switch (key) {
                    case "streetButton":
                        new AePlayWave("sound/magicClick.wav").start();
                        if (streetIsPressed) {
                            buttonStreet.ReleaseButton();
                            streetIsPressed = false;
                        } else {
                            disableAllButton();
                            buttonStreet.PressButton();
                            streetIsPressed = true;
                        }
                        break;
                    case "generatorButton":
                        new AePlayWave("sound/magicClick.wav").start();
                        if (generatorIsPressed) {
                            buttonGenerator.ReleaseButton();
                            generatorIsPressed = false;
                        } else {
                            disableAllButton();
                            buttonGenerator.PressButton();
                            generatorIsPressed = true;
                        }
                        break;

                    case "baseButton":
                        new AePlayWave("sound/magicClick.wav").start();
                        if (baseIsPressed) {
                            buttonBase.ReleaseButton();
                            baseIsPressed = false;
                        } else {
                            disableAllButton();
                            buttonBase.PressButton();
                            baseIsPressed = true;
                        }
                        break;
                    case "eraseButton":
                        new AePlayWave("sound/magicClick.wav").start();
                        if (rubberIsPressed) {
                            buttonErase.ReleaseButton();
                            rubberIsPressed = false;
                        } else {
                            disableAllButton();
                            buttonErase.PressButton();
                            rubberIsPressed = true;
                        }
                        break;
                    case "clearAll":
                        new AePlayWave("sound/magicClick.wav").start();
                        testPass = false;


                        placedBase = false;
                        placedGenerator = false;
                        disableAllButton();
                        inizializeMatrix();
                        gridPanel.repaint();
                        break;
                    case "saveButton":
                        new AePlayWave("sound/magicClick.wav").start();
                        try {

                            saveWorld();
                            testPass = false;

                        } catch (IOException e2) {
                            //Se non riesco a salvare gestire IOE TODO
                            e2.printStackTrace();
                        }
                        repaint();
                        break;


                    case "testButton":
                        new AePlayWave("sound/magicClick.wav").start();
                        gridPanel.correctMatrix();


                        testPass = false;
                        tightCurves.clear();
                        solitaryPieces.clear();

                        testPath.initTest(matrix);
                        checkPath = testPath.checkPath();

                        gridPanel.repaint();

                        switch (checkPath) {
                        case 0:
                            messageBox.setText("<html><b>Test Passato!<br><br>"
                                    + "Il percorso da te inserito è corretto!<br>"
                                    +"Procedi Premendo il tasto 'Save'</b> </html>");
                            testPass = true;
                            break;
                        case 1:
                            messageBox
                            .setText("<html><b>Test Fallito!<br>"+"Non sono presenti abbastanza pezzi strada<br>"
                                    +"Oppure hai inserito percorsi troppo corti!<br>"
                                    + "Premi il tasto'?' Per avere più informazioni</b></html>");
                            break;
                        case 2:
                            messageBox.setText("<html><b>Test Fallito!<br>"+"Non è presente il pezzo 'Generatore'<br>"
                                    + "Premi il tasto'?' Per avere più informazioni</b></html>");
                            break;
                        case 3:
                            messageBox.setText("<html><b>Test Fallito!<br>"+"Non è presente il pezzo 'Base'<br>"
                                    + "Premi il tasto'?' Per avere più informazioni</b></html>");
                            break;
                        case 4:
                            messageBox
                            .setText("<html><b>Test Fallito!<br>"+"Mancano i seguenti pezzi:<br>"
                                    +"'Base' ,'Generatore' e ci sono pochi pezzi 'Strada' <br>" +"Premi il tasto'?' Per avere più informazioni</b></html>");
                            break;
                        case 5:
                            messageBox
                            .setText("<html><b>Test Fallito!<br>"+"Non è presente il pezzo 'Generatore' e ci sono pochi pezzi 'Strada'<br>"
                                    + "Premi il tasto'?' Per avere più informazioni</b></html>");
                            break;
                        case 6:
                            messageBox
                            .setText("<html><b>Test Fallito!<br>"+"Non è presente il pezzo 'Base' e ci sono pochi pezzi 'Strada'<br>"
                                    + "Premi il tasto'?' Per avere più informazioni</b></html>");
                            break;
                        case 7:
                            messageBox.setText("<html><b>Test Fallito!<br>"+"Non sono presenti i pezzi 'Generatore' e 'Base'<br>"
                                    + "Premi il tasto'?' Per avere più informazioni</b></html>");
                            break;
                        case 8:
                            messageBox
                            .setText("<html><b>Test Passato!<br>"+"Ma sono presenti alcuni pezzi 'Extra'<br>"
                                    + "Sono quelli con il triangolo Giallo<br>"
                                    + "Puoi scegliere se lasciarli o no e poi premi 'Save' </b></html>");

                            solitaryPieces = testPath.getPiecesSolitary();

                            for (int i = 0; i < solitaryPieces.size(); i++) {

                                matrix[(int) solitaryPieces.get(i).getX()][(int) solitaryPieces.get(i).getY()] = 6;

                            }
                            testPass = true;
                            break;
                        case 9:

                            messageBox.setText("<html><b>Test Fallito!<br>"+"Sono Presenti curve strette!<br>"
                                    +"Elimina i pezzi 'Strada' segnati in Rosso e ripeti il Test<br>"
                                    +"Premi il tasto'?' Per avere più informazioni</b></html>");
                            // attivo pezzi rossi
                            invalidStreet = true;

                            tightCurves = testPath.getTightCurves();
                            for (int i = 0; i < tightCurves.size(); i++) {
                                matrix[(int) tightCurves.get(i).getX()][(int) tightCurves.get(i).getY()] = 5;
                            }

                            break;
                        case 10:
                            messageBox.setText("<html><b>Test Fallito!<br>"+"Sono Presenti Curve Strette e Pezzi Extra<br>"
                                    +"Elimina almeno i pezzi segnati in Rosso e tipeti il Test <br>"
                                    +"Premi il tasto'?' Per avere più informazioni</b></html>");
                            invalidStreet = true;
                            solitaryPieces = testPath.getPiecesSolitary();
                            tightCurves = testPath.getTightCurves();
                            for (int i = 0; i < solitaryPieces.size(); i++) {

                                matrix[(int) solitaryPieces.get(i).getX()][(int) solitaryPieces.get(i).getY()] = 6;

                            }

                            for (int i = 0; i < tightCurves.size(); i++) {

                                matrix[(int) tightCurves.get(i).getX()][(int) tightCurves.get(i).getY()] = 5;

                            }

                            break;


                        case 11:
                            messageBox.setText("<html><b>Test Fallito!<br>"+"Deve esserci almeno un Percorso disponibile!<br>"
                                    + "Premi il tasto'?' Per avere più informazioni</b></html>");
                        }

                        break;


                    case "backgroundButton":

                        new AePlayWave("sound/magicClick.wav").start();

                        try {
                            bgSelector = new BackgroudSelector(mainFrame,this);


                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        MainFrame.getIstanceMainframe().setEnabled(false);
                        enableBgImage = false;
                        break;

                    case "startGameButton":
                        new AePlayWave("sound/magicClick.wav").start();
                        MainFrame.getIstanceMainframe().switchTo(
                                new WorldSelectionPanel(mainFrame));
                        break;
                    }
                }


            // System.exit(0);

        }


        void disableAllButton() {


            buttonBase.ReleaseButton();
            buttonGenerator.ReleaseButton();
            buttonStreet.ReleaseButton();
            buttonErase.ReleaseButton();


            streetIsPressed = false;
            baseIsPressed = false;
            generatorIsPressed = false;
            rubberIsPressed = false;
        }

        public void setBackgroundImage (Image img, String name)
        {
            backgroundNames = name;
            backgroundImage = img;
            enableBgImage = true;
            repaint();
        }

        private void saveWorld() throws IOException {


            disableAllButton();

            if(testPass == true)
            {

                if( tightCurves.isEmpty())
                {
                    convertMatrix();  //tolgo i warning

                    screenshot.setRectangle(gridPanel.getBounds());

                    enableGrid = false;
                    gridPanel.repaint();

                    
                    
                    

                    String worldName = JOptionPane.showInputDialog(null, "Inserisci Il Nome Livello : ","Save Level", 1);


                    if( worldName != null)
                    {

                        if ( worldName.isEmpty())
                        {
                            worldName = "DEFAULT";
                        }

                        screenshot.createScreenshot();
                        screenshot.saveScreenshot(worldName);

                        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("./FILEWORLD/"+worldName+".txt")));

                        if(isImageValid)
                        {
                        WorldsLinker.setWorld((worldName) , backgroundNames);
                        }
                        else
                        {
                            WorldsLinker.setWorld((worldName) , "SfondoClassico.jpg");
                        }
                        worldScrollList = new JScrollPane(worldList);
                        for(int i = 0; i < matrix.length; i++){
                            for(int j = 0; j < matrix[i].length; j++){
                                out.print(matrix[i][j]);
                            }
                            out.println();
                        }
                        out.close();
                    }
                    listModel.addElement(worldName);
                    worldScrollList.validate();
                    enableGrid = true;
                    gridPanel.repaint();



                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Non Puoi salvare percorsi con curve strette");
                }
            }


            else
            {
                messageBox.setText("<html><b>Per salvare devi prima passare 'Test'</b></html>");
            }

        }

        private void convertMatrix() {

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++)
                {
                    if(matrix [i][j] != 0 && matrix [i][j] != 1 && matrix [i][j] != 2 && matrix [i][j] != 3)
                        matrix [i][j] = 1;
                }
            }

        }


        // Classe per definire i filtri
        private class TxtFileFilter extends FileFilter {

            public boolean accept(File file) {
                if (file.isDirectory())
                    return true;
                String fname = file.getName().toLowerCase();
                return fname.endsWith("txt");
            }

            public String getDescription() {
                return "File di testo";
            }
        }


        public void bgIsValid(boolean b) {

            isImageValid = b;

        }



    }

    // CLASSE PER L'UTILITY DI SOTTO
    public class UtilityMenu extends JPanel implements MouseMotionListener,
    MouseListener {
        public UtilityMenu(EditorPanel editorPanel) {

            this.setPreferredSize(new Dimension(editorPanel.matrixWidth, 120)); // Da
            // Settare
            // Meglio
            // e
            // più
            // sicuro
            GridBagLayout layout = new GridBagLayout();
            this.setLayout(layout);
            GridBagConstraints c = new GridBagConstraints();

            JLabel worldText = new JLabel("<html><b>Livelli Presenti</b></html>");

            worldText.setPreferredSize(new Dimension(100, 20));
            worldText.setForeground(Color.white);
            worldText.setOpaque(false);
            worldText.setHorizontalAlignment( JLabel.CENTER );
            worldText.setFont(new Font("Comic Sans", Font.ITALIC, 20));

            folder = new File("./FILEWORLD/");
            listOfFiles = folder.listFiles();





            String[] worldName = new String[listOfFiles.length];

            for (int i = 0; i < listOfFiles.length; i++)
            {

                if (listOfFiles[i].getName().toLowerCase().endsWith(".txt"))
                    worldName[i] = listOfFiles[i].getName();
            }

            listModel = new DefaultListModel();
            worldList = new JList(listModel);

            for (int i = 0; i < worldName.length; i++) {
                listModel.addElement(worldName[i]);
            }



            worldScrollList = new JScrollPane(worldList);
            worldScrollList.setBorder(null);
            worldScrollList.setPreferredSize(new Dimension(180, 80));
            worldList.setOpaque(false);
            worldScrollList.setOpaque(false);
            worldScrollList.getViewport().setOpaque(false);
            worldScrollList.setForeground(Color.black);



            worldList.addMouseListener(new MouseAdapter() {
                String worldSelected;

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    if (e.getClickCount() == 2 && !e.isConsumed()) {
                        e.consume();

                        worldSelected = (String) worldList.getSelectedValue();

                        BufferedReader br;
                        try {

                            br = new BufferedReader(new FileReader("FILEWORLD/"+ worldSelected));
                            String buffer;
                            int row = 0;

                            try {
                                while ((buffer = br.readLine()) != null) {

                                    for (int col = 0; col < buffer.length(); col++) {
                                        matrix[row][col] = Integer.parseInt(buffer.substring(col,col + 1));

                                    }

                                    row++;
                                }
                                gridPanel.repaint();

                            } finally {
                                br.close();
                                placedBase = true;
                                placedGenerator = true;
                            }

                        } catch (Exception e1) {
                            System.out.println("Non leggo questo"+worldSelected);
                            throw new RuntimeException("File not found");

                        }

                    }

                }

            });

            worldScrollList.addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO
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
            });

            messageBox = new JLabel("<html><b>Benvenuto Nell'Editor<br>"
                    + "Premi il tasto'?' Per avere più informazioni</b></html>");

            messageBox.setPreferredSize(new Dimension(300, 100));

            messageBox.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
            messageBox.setForeground(Color.white);
            messageBox.setOpaque(false);
            buttonGroupUtility = new ArrayList<JButton>();

            homeButton = new HomeButtonEditor("homeButton", 100, 100, mainFrame);
            buttonGroupUtility.add(homeButton);
            infoButton = new InfoButton("infoButton", 100, 100, mainFrame);
            buttonGroupUtility.add(infoButton);
            webcamButton = new WebCamButton("webcamButton", 100, 100, mainFrame);
            buttonGroupUtility.add(webcamButton);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 2;
            c.weightx = 0;
            layout.setConstraints(homeButton, c);
            this.add(homeButton, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 2;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 2;
            c.weightx = 1;
            layout.setConstraints(messageBox, c);
            this.add(messageBox, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 3;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 2;
            c.weightx = 1;
            layout.setConstraints(infoButton, c);
            this.add(infoButton, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 4;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 2;
            c.weightx = 1;
            layout.setConstraints(webcamButton, c);
            this.add(webcamButton, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 5;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.5;
            layout.setConstraints(worldText, c);
            this.add(worldText, c);

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 5;
            c.gridy = 2;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.5;
            layout.setConstraints(worldScrollList, c);
            this.add(worldScrollList, c);

            for (JButton button : buttonGroupUtility) {
                button.addMouseListener(this);
            }

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image utilityMenuImage;
            utilityMenuImage = imageProvider.getImage("UtilityBar.png");
            g.drawImage(utilityMenuImage, 0, 0, this.getWidth(),this.getHeight(), null);
            g.drawImage(messageFrame, 115, 8, 460, 110, null);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            Object event = e.getSource();
            if (event instanceof JButton)
            {

                String key = ((JButton) event).getName();

                switch (key) {
                case "homeButton":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    homeButton.PressButton();
                    break;
                case "infoButton":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    infoButton.PressButton();                   
                    
                    break;
                case "webcamButton":
                    new AePlayWave("sound/buttonEnteredMenu.wav").start();
                    webcamButton.PressButton();
                    break;
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            Object event = e.getSource();
            if (event instanceof JButton)
            {

                String key = ((JButton) event).getName();

                switch (key) {
                case "homeButton":
                    homeButton.ReleaseButton();
                    break;
                case "infoButton":
                    infoButton.ReleaseButton();
                    break;
                case "webcamButton":
                    webcamButton.ReleaseButton();
                    break;
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {


            Object event = e.getSource();
            if (event instanceof JButton) {

                String key = ((JButton) event).getName();
                switch (key) {
                case "homeButton":
                    new AePlayWave("sound/magicClick.wav").start();
                    mainFrame.switchTo(new MenuPanel(mainFrame));
                    break;
                case "infoButton":
                    new AePlayWave("sound/magicClick.wav").start();
                    messageBox.setText("<html><b>REGOLE BASE:<br>"
                    		+ "Devono essere presenti 1 Base ed 1 Generatore<br>"
                    		+ "Ci devono essere almeno 15 pezzi Strada<br>"
                    		+ "Sono Possibili Percorsi multipli e Warning<br>"
                    		+ "Non sono possibili pezzi segnati in rosso!<br></b></html>");
                    
                    break;
                case "webcamButton":
                    new AePlayWave("sound/magicClick.wav").start();
                    loaderMapCam.loadMap();
                    break;
                }
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }

}