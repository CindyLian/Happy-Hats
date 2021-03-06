package hats3;

import hats3.data.*;
import hats3.print.Print;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.*;
import java.util.*;

/**
 * The HappyHatsMain class controls the entire game. It extends JFrame and implements ActionListener.
 * @author Jennifer Cai
 * @author Cindy Lian
 * @version 4.0, June 10, 2013
 */
public class Happy extends JFrame implements ActionListener
{
  /**
   * j - JTextField - Field for entering name if a high score has been achieved.
   */
  JTextField j;
  /**
   * x - int - Used in loop, as well as store the value of x when the loop breaks
   */
  int x;
  /**
   * d - JDialog - Allows user to enter their name if a high score has been achieved.
   */
  JDialog d;
  /**
   * b - JButton - Allows user to enter their name if they get a highscore
   */
  JButton b = new JButton ("Enter");
  /**
   * scores - ArrayList<Score> - Stores the high scores
   */
  ArrayList <Score> scores = new ArrayList <Score> ();
  /**
   * paused - static boolean - Stores whether the game is currently paused or not
   */
  public static boolean paused;
  /**
   * t - static Timer - Timer for the game
   */
  public static hats3.data.Timer t;
  /**
   * quitImage - Image - Image for the quit screen
   */
  Image quitImage;
  /**
   * pauseImage - Image - Image for the paused screen
   */
  Image pauseImage;
  /**
   * intro - Image - Image for the introduction
   */
  Image intro;
  /**
   * mainMenu - JButton - JButton in the intro to go to the main menu
   */
  JButton mainMenu = new JButton ("Main Menu");
  /**
   * instructNum - int - int page of the instructions
   */
  int instructNum;
  /**
   * nItem - JButton - JButton to go to the next page in the instructions
   */
  JButton nItem = new JButton ("Continue");
  /**
   * pItem - JButton - JButton to go to the previous page in the instructions
   */
  JButton pItem = new JButton ("Go Back");
  /**
   * background - Image - Image for all the backgrounds 
   */
  Image background;
  /**
   * nextItem - JButton - Button for going to the next lesson image
   */
  JButton nextItem = new JButton ("Next");
  /**
   * previousItem - JButton - JButton for going to the previous lesson's image
   */
  JButton previousItem = new JButton ("Previous");
  /**
   * sLayout - SpringLayout - SpringLayout for the JPanel
   */
  SpringLayout sLayout = new SpringLayout ();
  /**
   * j - JPanel - Container to put components in
   */
  JPanel panel = new JPanel ();
  /**
   * lessonMenuItem - JButton - JButton to return to the lesson menu
   */
  JButton lessonMenuItem = new JButton("Lesson Menu");
  /**
   * item - JButton [] - JButtons for the lesson menu
   */
  JButton [] item = new JButton [5];
  /**
   * lessonNum - int - Number for Lesson
   */
  static int lessonNum;
  /**
   * gameItem - JButton [] - JButtons for the game menu
   */
  JButton [] gameItems = new JButton [3];
  /**
   * menuItems - JButton [] - JButtons for the menu items
   */
  JButton [] menuItems = new JButton [5];
  /** 
   * backItem - JButton - Creates instance of JButton to create a "Back" button.
   */
  JButton backItem = new JButton ("Back");
  /**
   * pause - JButton - JButton on the game screen to pause the game
   */
  JButton pause = new JButton ("Pause");
  /**
   * quit - JButton - JButton on the game screen to quit the game
   */
  JButton quit = new JButton ("Quit");
  /**
   * resume - JButton - JButton on the paused screen to resume the game
   */
  JButton resume = new JButton ("Resume");
  /**
   * game - JPanel - JPanel for the game screen
   */
  JPanel game;
  /**
   * Sets the JFrame screen, initializes variables, sets the layout to
   * sLayout(SpringLayout), adds ActionListener to various components, shows the
   * splashscreen, and intro.
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * in - BufferedReader - Reads from the hatScores.turt file<br>
   * name - String - Temporary variable used to create a Score, stores the name<br>
   * score - int - Temporary variable used to create a Score, stores the score<br>
   * level - int - Temporary variable used to create a Score, stores the level<br>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < 10/x++<br>
   *  Purpose: loops to store multiple values within the score ArrayList<br>
   *  int x/x < 3/x++<br>
   *  Purpose: loops to add ActionListener to gameItems<br>
   *  int x/x < 5/x++<br>
   *  Purpose: loops to add ActionListener to menuItems and item<br>
   * </p>
   * @exception Exception e
   */
  public Happy()
  {
    super ("Happy Hats III");
    setSize (651, 477);
    setVisible (true);
    setResizable (false);
    
    try
    {
      background = ImageIO.read (new File ("hats3/Images/Backgrounds/Background.png"));
      intro = ImageIO.read (new File ("hats3/Images/Backgrounds/Intro.png"));
      pauseImage = ImageIO.read (new File ("hats3/Images/Backgrounds/pausedBackground.png"));
      quitImage = ImageIO.read (new File ("hats3/Images/Backgrounds/quitBackground.png"));
      BufferedReader in = new BufferedReader (new FileReader ("hats3/tFiles/hatScores.turt"));
      if (in.readLine().equals ("catbunnysnaketurtlepig"))
      {
        for (int x = 0; x < 10; x++)
        {
          String name = in.readLine ();
          int score = Integer.parseInt (in.readLine ());
          int level = Integer.parseInt (in.readLine ());
          scores.add (new Score (name, score, level));
        }
      }
    }
    catch (Exception e)
    {
    }
    
    panel.setLayout (sLayout);
    game = new JPanel ();
    item[0] = new JButton ("1 Colors (F1)");
    item[1] = new JButton ("2 Shapes (F2)");
    item[2] = new JButton ("3 Animals and Sounds (F3)");
    item[3] = new JButton ("4 Pronouns (F4)");
    item[4] = new JButton ("5 Conjugations (F5)");
    
    gameItems[0] = new JButton ("Level 1 (F1)");
    gameItems [1] = new JButton ("Level 2 (F2)");
    gameItems [2] = new JButton ("Level 3 (F3)");
    
    menuItems [0] = new JButton ("Instructions (F1)");
    menuItems [1] = new JButton ("Learn (F2)");
    menuItems [2] = new JButton ("Play (F3)");
    menuItems [3] = new JButton ("High Scores (F4)");
    menuItems [4] = new JButton ("Exit (F5)");
    b.addActionListener (this);
    backItem.addActionListener (this);
    for (int x = 0 ; x<3; x++)
    {
      gameItems[x].addActionListener (this);
    }
    for (int x = 0; x<5; x++)
    {
      menuItems[x].addActionListener (this);
      item [x].addActionListener (this);
    }
    lessonMenuItem.addActionListener (this);
    nextItem.addActionListener(this);
    previousItem.addActionListener (this);
    nItem.addActionListener (this);
    pItem.addActionListener (this);
    mainMenu.addActionListener (this);
    pause.addActionListener (this);
    quit.addActionListener(this);
    splashScreen ();        
    intro();
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
  }
  
  /**
   * Sets the location and width of a JComponent using the values passed.
   * @param b JComponent the component to set location/width
   * @param x int the x coordinate for the JComponent to be set
   * @param y int the y coordinate for the JComponent to be set
   * @param width int the width of the JComponent to be set
   */
  public void setLoc(JComponent b, int x,int y, int width)
  {
    SpringLayout.Constraints z = sLayout.getConstraints (b);
    z.setX (Spring.constant (x));
    z.setY (Spring.constant (y));
    if (width!=0)
      z.setWidth(Spring.constant(width));  
    panel.add (b);
  }
  
  /**
   * Introduces the game to the user and waits for them to click a button to continue.
   */
  public void intro ()
  {
    remove (panel);
    panel =new JPanel (){
      /**
       * Draws the background on the JPanel.
       * @param g Graphics 
       */
      public void paintComponent (Graphics g) 
      {
        super.paintComponent(g);
        g.drawImage (intro, 0, 0, null);
      }
    };
    
    panel.setLayout (sLayout);
    setLoc(mainMenu,500,385,0);
    add (panel);
    refresh ();
  }
  
  /**
   * Returns the Image required for the background based on class variable lessonNum
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * i - Image - Image to be returned<br>
   * </p>
   * @return Image i lessons image
   * @exception IOException e
   */
  public Image image ()
  {
    Image i;
    try
    {
      i = ImageIO.read (new File ("hats3/Images/Lessons/" + lessonNum + ".png"));
    }
    catch (IOException e)
    {
      return null;
    }
    return i;
  }
  
  /**
   * Returns the Image required for the background based on class variable instructNum
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * i - Image - Image to be returned<br>
   * </p>
   * @return Image i instructions image
   * @exception IOException e
   */
  public Image instructImage ()
  {
    Image i;
    try
    {
      i = ImageIO.read (new File ("hats3/Images/Instructions/Instructions" + instructNum + ".png"));
    }
    catch (IOException e)
    {
      return null;
    }
    return i;
  }
  
  /**
   * Displays the splashscreen adding a JPanel of the splashscreen to the JFrame.
   * A for loop rotates through the Images in the ImageIcon array to be added to the 
   * JPanel. 
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * splashscreen - ImageIcon [] - Array of Image icons for the splashscreen<br>
   * j - JLabel - JLabel of the splashscren gif to be added to the JPanel<br>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < 3/x++<br>
   *  Purpose: loops to add and remove the splashscreen images<br>
   * </p>
   * @exception InterruptedException e
   */
  public void splashScreen()
  {
    ImageIcon [] splashscreen = new ImageIcon [3];
    JLabel j;
    splashscreen [0] =  new ImageIcon ("hats3/Images/SplashScreen/nyannn.gif");
    splashscreen [1] = new ImageIcon ("hats3/Images/SplashScreen/Presents.png");
    splashscreen [2] = new ImageIcon ("hats3/Images/SplashScreen/HappyHats.png");
    
    add (panel);
    for (int x = 0; x < 3; x++)
    {
      j = new JLabel(splashscreen [x]);
      setLoc(j,0,0,0);
      refresh ();
      try 
      {
        Thread.sleep (2500);
      }
      catch (InterruptedException e)
      {
      }
      panel.remove(j);
    }
    refresh ();
  }
  
  /**
   * This method has the option buttons centered on the screen so that the user can choose
   * what they want to do. 
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < 5/x++<br>
   *  Purpose: loops to create and add multiple menuItems<br>
   * </p>
   * @Exception IOException
   */
  public void mainMenu()
  {
    makePanel();
    
    JLabel f10 = new JLabel ("Press (F10) to access Happy Hats 3 documentation");
    setLoc (f10, 180, 400, 0);
    
    panel.getActionMap().put("a",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {   
        panel.removeAll ();
        refresh ();
        instructions();
      }
    });  
    panel.getActionMap().put("b",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        lessonMenu();
      }
    });  
    panel.getActionMap().put("c",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        gameMenu();
      }
    });  
    panel.getActionMap().put("d",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        highScore();
      }
    });  
    panel.getActionMap().put("e",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        goodBye();
      }
    });  
    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F10,0,false), "Help" );
    panel.getActionMap().put("Help",new AbstractAction() {
      public void actionPerformed(ActionEvent ae) {  
        System.out.println (3);
        try
        {
          Runtime.getRuntime().exec("hh.exe hats3/HappyHatsDocs.chm");
        }
        catch (IOException e)
        {
        }
      }
    });  
    for (int x = 0; x< 5; x++)
    {
      setLoc(menuItems[x],260,150+x*50,130);
    }
    
    add (panel);
    refresh ();
  }
  
  /**
   * Adds the Game menu screen which contains the three level buttons, as well as a back button.
   * <p>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < 3/x++<br>
   *  Purpose: loops to create multiple gameItems<br>
   * </p>
   */
  public void gameMenu()
  {    
    makePanel();
    panel.getActionMap().put("a",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        startGame();
        game = new HatsGame (1); 
        game ();
      }
    });  
    panel.getActionMap().put("b",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        startGame();
        game = new HatsGame (2); 
        game ();
      }
    });  
    panel.getActionMap().put("c",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        startGame();
        game = new HatsGame (3); 
        game ();
      }
    });  
    setLoc(backItem,35,385,0);
    for (int x = 0; x< 3; x++)
    {
      setLoc(gameItems[x],270,170+x*60,110);
    }
    
    add (panel);
    refresh ();
  }
  
  /**
   * Shows instructions for the game on two screens. Includes a continue and a go back button for the 
   * user to navigate between the two screens.
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * z - SpringLayout.Constraints - Constraints for the different components<br>
   * </p>
   */
  public void instructions()
  {     
    remove (panel);
    
    panel = new JPanel (){
      /**
       * Draws the background on the JPanel.
       * @param g Graphics 
       */
      public void paintComponent (Graphics g) 
      {
        super.paintComponent(g);
        g.drawImage (instructImage (), 0, 0, null);
      }
    };
    
    panel.setLayout (sLayout);
    
    SpringLayout.Constraints z = sLayout.getConstraints (backItem);
    z.setX (Spring.constant (35));
    z.setY (Spring.constant (385));
    
    panel.add (backItem);
    
    z = sLayout.getConstraints (nItem);
    z.setWidth (Spring.constant (85));
    z.setX (Spring.constant (535));
    z.setY (Spring.constant (385));
    
    z = sLayout.getConstraints (pItem);
    z.setWidth (Spring.constant (85));
    z.setX (Spring.constant (400));
    z.setY (Spring.constant (385));
    
    if (instructNum ==0)
      panel.add (nItem);
    else
      panel.add (pItem);
    
    add (panel);
    refresh ();
  }
  
  /**
   * Menu for all the lessons provided. Contains a button for each of the lessons.
   * <p>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < 5/x++<br>
   *  Purpose: loops to create multiple buttons<br>
   * </p>
   */
  public void lessonMenu()
  {          
    makePanel();
    panel.getActionMap().put("a",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        lessonNum = 0;
        lesson ();
      }
    });  
    panel.getActionMap().put("b",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        lessonNum = 3;
        lesson();
      }
    });  
    panel.getActionMap().put("c",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        lessonNum = 8;
        lesson ();
      }
    });  
    panel.getActionMap().put("d",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        lessonNum = 11;
        lesson ();
      }
    });  
    panel.getActionMap().put("e",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {  
        panel.removeAll ();
        refresh ();
        lessonNum = 15;
        lesson ();
      }
    });  
    setLoc(backItem,35,385,0);
    for (int x = 0; x<5; x++)
    {
      setLoc(item[x],230,110+x*50,190);
    }
    add (panel);
    refresh ();
  }
  
  /**
   * Displays the lesson based on the class variable lessonNum.
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * z - SpringLayout.Constraints - Constraints for the different components<br>
   * </p>
   */
  public void lesson ()
  {    
    remove (panel);
    
    panel = new JPanel (){
      /**
       * Draws the background on the JPanel.
       * @param g Graphics 
       */
      public void paintComponent (Graphics g) 
      {
        super.paintComponent(g);
        g.drawImage (image (), 0, 0, null);
      }
    };
    panel.setLayout (sLayout);
    
    SpringLayout.Constraints z = sLayout.getConstraints (nextItem);
    z.setWidth (Spring.constant (85));
    z.setX (Spring.constant (535));
    z.setY (Spring.constant (385));
    
    z = sLayout.getConstraints (previousItem);
    z.setWidth (Spring.constant (85));
    z.setX (Spring.constant (400));
    z.setY (Spring.constant (385));
    
    z = sLayout.getConstraints (lessonMenuItem);
    z.setX (Spring.constant (35));
    z.setY (Spring.constant (385));
    
    panel.add (lessonMenuItem);
    if (lessonNum!=18)
      panel.add (nextItem);
    if (lessonNum != 0)
      panel.add (previousItem);
    
    add (panel);
    
    refresh ();
  }
  
  /**
   * Removes the JPanel panel, creates a new one with the default background, and sets the layout to 
   * sLayout (SpringLayout)
   */
  public void makePanel()
  {
    remove (panel);
    
    panel =new JPanel (){
      /**
       * Draws the background on the JPanel.
       * @param g Graphics 
       */
      public void paintComponent (Graphics g) 
      {
        super.paintComponent(g);
        g.drawImage (background, 0, 0, null);
      }
    };
    
    panel.setLayout (sLayout);
    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0,false), "a" );
    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0,false), "b" );
    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0,false), "c" );
    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4,0,false), "d" );
    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0,false), "e" );
  }
  
  /**
   * Displays high scores. Allows the user to print the scores and also reset the high scores.
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * title - JLabel - JLabel for the High Scores title<br>
   * otherTitles - JLabel - JLabel for the Name, Score, and Level<br>
   * j - JLabel - JLabel for the name<br>
   * s - JLabel - JLabel for the score<br>
   * l - JLabel - JLabel for the level<br>
   * reset - JButton - allows the user to reset the high scores<br>
   * print - JButton - allows the uesr to print the high scores<br>
   * n - JOptionPane - for confirming to reset<br>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < 10/x++<br>
   *  Purpose: loops to get scores from the scores ArrayList and puts them on the screen through JLabels<br>
   * </p>
   * @exception IndexOutOfBoundsException e
   * @exception IOException e
   */
  public void highScore()
  {     
    makePanel(); 
    JButton print = new JButton ("Print Scores");
    JButton reset = new JButton ("Reset High Scores");
    JLabel title = new JLabel ("High Scores");
    JLabel otherTitles  = new JLabel ("Name                                  Score            Level");
    setLoc (otherTitles, 235, 75, 0);
    setLoc(title,235,50,0);
    setLoc(backItem,35,385,0);
    setLoc(print,350,385,0);
    setLoc(reset,475,385,0);
    add (panel);
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter ("hats3/tFiles/hatScores.turt"));
      out.println ("catbunnysnaketurtlepig");
      for (int x = 0; x < 10; x++)
      {
        try
        {
          out.println (scores.get(x).getName ());
          out.println (scores.get(x).getScore ());
          out.println (scores.get(x).getLevel ());
        }
        catch (IndexOutOfBoundsException e)
        {
          break;
        }
      }
      out.close ();
    }
    catch (IOException e)
    {
    }
    for (int x = 0; x < scores.size (); x++)
    {
      JLabel j = new JLabel (scores.get (x).getName ());
      JLabel s = new JLabel (scores.get (x).getScore ()+ "");
      JLabel l = new JLabel (scores.get (x).getLevel () + "");
      setLoc(j,235,100+x*25,0);
      setLoc(s,375,100+x*25,0);
      setLoc(l,450,100 + x * 25, 0);
    }
    
    
    reset.addActionListener (new ActionListener () {
      /**
       * Confirms the user wants to reset the scores, then writes a new file with the default scores.
       * @param ae ActionEvent
       */
      public void actionPerformed (ActionEvent ae) {
        int n = JOptionPane.showConfirmDialog (null, "Are you sure you want to reset?", "Reset", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) 
        {
          for (int x = 0; x < 10 ; x++)
          {
            try
            {
              scores.remove (0);
            }  
            catch (Exception e)
            {
            }
          }
          try
          {
            PrintWriter out = new PrintWriter (new FileWriter ("hats3/tFiles/hatScores.turt"));
            out.println ("catbunnysnaketurtlepig");
            out.close ();
          }
          catch (IOException e)
          {
          }
        }
      }
      
    });
    
    print.addActionListener (new ActionListener () {
      /**
       * Creates an instance of the Print Object.
       * @param ae ActionEvent
       */
      public void actionPerformed (ActionEvent ae) {
        new Print ();
      }
    });
    
    refresh ();
  }
  /**
   * Prints the scores into a file. Exits the game with a good bye screen.<br>
   * Thanks the user, and closes the window after the press of a button.
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * out - PrintWriter - Allows program to print to file<br>
   * message1Item - JLabel - JLabel for the first half of the message<br>
   * message2Item - JLabel - JLabel for the second half of the message<br>
   * byeItem - JButton - JButton to close the JFrame<br>
   * </p>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < 10/x++<br>
   *  Purpose: loops to print the scores on to a file from the scores ArrayList<br>
   * </p>
   * @exception IOException e
   * @exception IndexOutOfBoundsException e
   */
  public void goodBye()
  {    
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter ("hats3/tFiles/hatScores.turt"));
      out.println ("catbunnysnaketurtlepig");
      for (int x = 0; x < 10; x++)
      {
        try
        {
          out.println (scores.get(x).getName ());
          out.println (scores.get(x).getScore ());
          out.println (scores.get(x).getLevel ());
        }
        catch (IndexOutOfBoundsException e)
        {
          break;
        }
      }
      out.close ();
    }
    catch (IOException e)
    {
    }
    makePanel();
    JLabel message1Item = new JLabel ("Thank you for playing Happy Hats III! Cindy and Jennifer hope you had fun");
    JLabel message2Item = new JLabel ("and learned basic English. Press the 'Bye!' button to exit the game.");
    JButton byeItem = new JButton ("Bye!");
    
    byeItem.addActionListener (new ActionListener () {
      /**
       * Closes the JFrame.
       * @param ae ActionEvent
       */
      public void actionPerformed (ActionEvent ae) {
        System.exit (0);
      }
    });
    setLoc(message1Item,125,200,0);
    setLoc(message2Item,140,220,0);
    setLoc(byeItem,290,300,0);
    add (panel);
    
    refresh();
  }
  
  /**
   * Refreshes the JFrame.
   */
  private void refresh ()
  {
    validate ();
    repaint ();
  }
  
  /**
   * Shows an error message depending on the parameter.
   * @param message String to be displayed
   */
  private void error (String message)
  {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }
  
  /**
   * Creates the base of every game, sets the quit button and pause button.
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * z - SpringLayout.Constraints - holds the constraints of the quit and pause buttons
   * </p>
   */
  public void game()
  {
    SpringLayout.Constraints z = ((HatsGame)game).sLayout.getConstraints (quit);
    z.setX (Spring.constant (50));
    z.setY (Spring.constant (415));   
    z = ((HatsGame)game).sLayout.getConstraints (pause);
    z.setX (Spring.constant (125));
    z.setY (Spring.constant (415));
    game.add (quit);
    game.add (pause);
    add (game);
    game.requestFocus();
    refresh();
  }
  
  /*
   * Pauses and allows the user to resume the game.
   */
  public void pause ()
  {
    paused = true;
    remove (panel);
    panel =new JPanel (){
      /**
       * Draws the background on the JPanel.
       * @param g Graphics 
       */
      public void paintComponent (Graphics g) 
      {
        super.paintComponent(g);
        g.drawImage (pauseImage, 0, 0, null);
      }
    };
    
    panel.setLayout (sLayout);
    
    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,false), "1" );
    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,false), "2" );
    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,false), "3" );
    panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false), "4" );
    
    setLoc(resume,300,415,0);
    
    resume.addActionListener (new ActionListener () {
      /**
       * @param ae ActionEvent
       */
      public void actionPerformed (ActionEvent ae) {
        paused = false;
        panel.removeAll ();
        game.setVisible (true);
        refresh ();
      }
    });
    
    add (panel);
    refresh ();
  }
  
  /*
   * Panel which the user sees upon quitting the game. It will give the user<br>
   * an option to enter their name if they qualify for a high score, and<br>
   * redirect the user to the high scores.
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * timeScore - int - Stores the bonus score from the amount of time left<br>
   * showPoints - JLabel - Tells the user how many points they have earned.<br>
   * next - JButton - Allows user to take time to read the message, and if the<br>
   * user qualifies for a high score, a dialog will open up <br>
   * </p>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < 10/x++<br>
   *  Purpose: loops to print the scores on to a file from the scores ArrayList<br>
   * </p>
   */
  public void quit ()
  {
    int timeScore = t.getTime()/3;
    remove (panel);
    panel =new JPanel (){
      /**
       * Draws the background on the JPanel.
       * @param g Graphics 
       */
      public void paintComponent (Graphics g) 
      {
        super.paintComponent(g);
        g.drawImage (quitImage, 0, 0, null);
      }
    };
    panel.setLayout (sLayout);
    
    JLabel showPoints = new JLabel ("Your score is: "+HatsGame.score+". You qualify for a time bonus of: "+timeScore+". Your final score is: "+(timeScore+HatsGame.score));
    HatsGame.score+=timeScore;
    
    JButton next = new JButton ("Next");
    setLoc(next,290,415,0);
    setLoc(showPoints,110,330,0);
    next.addActionListener (new ActionListener () {
      /**
       * Allows the user to enter their name if they have achieved a<br>
       * high score.<br>
       * Variable Dictionary:<br>
       * Variable - Type - Purpose<br>
       * s - FlowLayout - Layout used in the dialog.<br>
       * l - JLabel - Label to ask user to enter name.<br>
       *  For Loops:<br>
       *  Variable/Condition/Increment<br>
       *  int x/x < 10/x++<br>
       *  Purpose: loops to compare the user's score with the high scores<br>
       * </p>
       * </p>
       * @param ae ActionEvent
       */
      public void actionPerformed (ActionEvent ae) {
        for (x = 0; x < scores.size () ; x++)
        {
          try
          {
            if (HatsGame.score >= scores.get (x).getScore ())
            {
              break;
            }
          }
          catch (Exception e)
          {
          }
        }
        if (x < 10 || scores.size () == 0)
        {
          if (scores.size () == 0)
            x = 0;
          d = new JDialog ((JFrame)null,"High Score!");
          d.setSize (370,200);
          d.setResizable (false);
          FlowLayout s = new FlowLayout ();
          d.setLayout (s);
          JLabel l = new JLabel ("Enter your name:");
          j = new JTextField (20);
          d.add (l);
          d.add (j);
          d.add (b);
          d.setLocationRelativeTo (panel);
          d.setVisible (true);
        }
        else
        {
          panel.removeAll ();
          highScore ();
        }
      }
    });
    add (panel);
    
  }
  
  /**
   * Starts the game by reseting the score and creating a new instance of Timer.
   */
  public void startGame()
  {
    HatsGame.score = 0;
    t = new hats3.data.Timer ();
    t.start ();
  }
  
  /**
   * Removes everything from the JFrame, then calls corresponding methods depending on what the user has clicked.
   * @param ae when triggered, will pass an ActionEvent which includes the String name of the component which triggered it.
   */
  public void actionPerformed(ActionEvent ae)
  {
    game.setVisible (false);
    panel.removeAll ();
    refresh ();
    
    if (ae.getActionCommand ().equals ("Exit (F5)"))
    {
      goodBye ();
    }
    else if (ae.getActionCommand ().equals ("Play (F3)"))
    {
      gameMenu ();
    }
    else if (ae.getActionCommand().equals ("Level 1 (F1)"))
    {
      startGame();
      game = new HatsGame (1); 
      game ();
    }
    else if (ae.getActionCommand ().equals ("Level 2 (F2)"))
    {
      startGame();
      game = new HatsGame (2);
      game ();
    }
    else if (ae.getActionCommand ().equals ("Level 3 (F3)"))
    {
      startGame();
      game = new HatsGame (3);
      game ();
    }
    else if (ae.getActionCommand ().equals ("Instructions (F1)"))
    {
      instructions ();
    }
    else if (ae.getActionCommand ().equals ("Learn (F2)"))
    {
      lessonMenu ();
    }
    else if (ae.getActionCommand ().equals ("High Scores (F4)"))
    {
      highScore ();
    }
    else if (ae.getActionCommand ().equals ("Back"))
    {
      mainMenu ();
    }
    else if (ae.getActionCommand ().equals ("Next"))
    {
      lessonNum++;
      lesson (); 
    }
    else if (ae.getActionCommand ().equals ("Previous"))
    {
      lessonNum--;
      lesson (); 
    }
    else if (ae.getActionCommand ().equals ("Lesson Menu"))
    {
      lessonMenu ();
    }
    else if (ae.getActionCommand ().equals ("1 Colors (F1)"))
    {
      lessonNum = 0;
      lesson ();
    }
    else if (ae.getActionCommand ().equals ("2 Shapes (F2)"))
    {
      lessonNum = 3;
      lesson ();
    }
    else if (ae.getActionCommand ().equals ("3 Animals and Sounds (F3)"))
    {
      lessonNum = 8;
      lesson ();
    }
    else if (ae.getActionCommand ().equals ("4 Pronouns (F4)"))
    {
      lessonNum = 11;
      lesson ();
    }    
    else if (ae.getActionCommand ().equals ("5 Conjugations (F5)"))
    {
      lessonNum = 15;
      lesson ();
    }
    else if (ae.getActionCommand ().equals ("Continue"))
    {
      instructNum = 1;
      instructions ();
    }
    else if (ae.getActionCommand ().equals ("Go Back"))
    {
      instructNum = 0;
      instructions ();
    }
    else if (ae.getActionCommand ().equals ("Main Menu"))
    {
      mainMenu ();
    }
    else if (ae.getActionCommand ().equals ("Quit"))
    {
      quit ();
    }
    else if (ae.getActionCommand ().equals ("Pause"))
    {
      pause ();
    }
    else if (ae.getActionCommand ().equals ("Enter"))
    {
      if (j.getText ().length () <= 0 || j.getText ().length ()> 20)
      {
        error ("Please enter a name between 0 and 20 characters.");
      }
      else
      {
        if (x == scores.size ())
        {
          scores.add (new Score (j.getText (), HatsGame.score, HatsGame.level));
        }
        else
        {
          scores.add (x, new Score (j.getText (), HatsGame.score, HatsGame.level));
        }
        if (scores.size () == 11)
        {
          scores.remove (10);
        }
        d.dispose ();
        highScore ();
      }
    }
  }
  
  /**
   * This is the main method which will create an instance of Asdf.
   * @param args Takes in arguments passed to the main method when the program runs.
   */
  public static void main (String[]args)
  {
    new Happy();
    
  }
}