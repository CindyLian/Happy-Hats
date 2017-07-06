package hats3.data;

import hats3.characters.*;
import hats3.Happy;
import hats3.components.*;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
/**
 * This program creates the hat game. It extends JPanel, and is used in Happy
 * @author Jennifer Cai
 * @author Cindy Lian
 * @version 4.0, June 10, 2013
 */
public class HatsGame extends JPanel
{
  /**
   * isTeemo - boolean - stores whether a question is boolean or not
   */
  boolean isTeemo;
  /**
   * t - TeemoQuestions - Creates instance of TeemoQuestions class
   */
  TeemoQuestions t;
  /**
   * d - JDialog - will create an instance of JDialog
   */
  JDialog d;
  /**
   * teemo - JLabel - creates an instance of Teemo within a JLabel
   */
  JLabel teemo = new JLabel(new Teemo());
  /**
   * score - static int - stores the score that the user gets
   */
  public static int score;
  /**
   * coordinates - int[] - will store the coordinates of the 5 blocks
   */
  int [] coordinates = new int [10];
  /**
   * level - static int - stores the level the user is currently on
   */
  public static int level;
  /**
   * q - Questions - creates an instance of Questions
   */
  Questions q;
  /**
   * gameBackground - Image - Creates an instance of Image, will be used to create the game background
   */
  Image gameBackground;
  /**
   * chid - Person - Creates an instance of Person
   */
  private Person chid = new Person();
  /**
   * myHatLabels - ArrayList<JLabel> - Stores a list of hats in JLabels
   */
  private ArrayList <JLabel> myHatLabels = new ArrayList<JLabel>();
  /**
   * blocks - ArrayList<JLabel> - Stores a list of blocks
   */
  private ArrayList <JLabel> blocks = new ArrayList < JLabel > ();
  /**
   * checkpoints - ArrayList<JLabel> - Stores a list of checkpoints
   */
  private ArrayList <JLabel> checkpoints = new ArrayList < JLabel > ();
  /**
   * boosts - ArrayList<JLabel> - Stores a list of time boosts
   */
  private ArrayList <JLabel> boosts = new ArrayList < JLabel > ();
  /**
   * chid - JLabel - Instance of JLabel which will contain the Person ImageIcon
   */
  JLabel chidd;
  /**
   * sLayout - SpringLayout - creates instance of SpringLayout
   */
  public SpringLayout sLayout = new SpringLayout();
  /**
   * you - JLabel - will be used to create your character
   */
  JLabel you;
  /**
   * numHats - JLabel - shows how many hats you have
   */
  JLabel numHats;
  /**
   * scoreLabel - JLabel - shows how many points you have
   */
  JLabel scoreLabel;
  /**
   * Draws the background on this panel, as well as the rectangle for time.
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * gr - Graphics2D - used to access methods to draw the rectangle<br>
   * @param g Graphics 
   */
  public void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    g.drawImage (gameBackground, 0, 0, null);
    Graphics2D gr = (Graphics2D) g;
    gr.fill(Happy.t.getRect());
    gr.draw(Happy.t.getRect());
    repaint();
    validate();
  }
  /**
   * Creates the JPanel, sets layout to sLayout (SpringLayout), and creates the background.<br>
   * Sets the level corresponding to the parameter passed, and initializes many class variables.<br>
   * Gets the input map for the arrow keys, and allow them to move your character. Directs<br>
   * user to one of three levels, depending on the parameter passed. Creates a base for the<br>
   * game.
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * b - ImageIcon - stores a new Block/Checkpoint/TimeBoost<br>
   * a - JLabel - Creates a JLabel out of b<br>
   * topHat - JLabel - Creates a giant top hat icon<br>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < 5/x++<br>
   *  Purpose: loops to add blocks and checkpoints to their corresponding ArrayLists<br>
   *  int x/x < 2/x++<br>
   *  Purpose: loops to add time boosts to its ArrayList<br>
   * </p>
   * @param level int Passes the level that the user wishes to play
   * @exception IOExceptoin e
   */
  public HatsGame (int level)
  {
    super ();
    HatsGame.level = level;
    setLayout(sLayout);
    score = 0;
    try
    {
      gameBackground = ImageIO.read (new File ("hats3/Images/GameImages/GameBackground.png"));
    }
    catch (IOException e)
    {
    }
    
    you = new JLabel (new ImageIcon("hats3/Images/GameImages/you.png"));
    
    for (int x = 0; x<5;x++)
    {
      ImageIcon b = new Block();
      JLabel a = new JLabel (b);
      blocks.add (a);
      b = new Checkpoint();
      a = new JLabel (b);
      checkpoints.add(a);
    }
    for (int x = 0; x<2; x++)
    {
      ImageIcon b = new TimeBoost();
      JLabel a = new JLabel (b);
      boosts.add(a);
    }
    if (level ==1)
    {
      levelOne();
    }
    else if (level == 2)
    {
      levelTwo ();
    }
    else 
    {
      levelThree ();
    }
    
    
    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0,false), "up" );
    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0,false), "down" );
    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0,false), "left" );
    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0,false), "right" );
    getActionMap().put("up",new AbstractAction() {
      /**
       * Moves up.
       * @param ae ActionEvent
       */
      public void actionPerformed(ActionEvent e) {  
        up();   
        repaint();
        validate();
        
      }
    });
    getActionMap().put("down",new AbstractAction() {
      /**
       * Moves down.
       * @param ae ActionEvent
       */
      public void actionPerformed(ActionEvent e) {  
        down();   
        repaint();
        validate();
        
      }
    });
    getActionMap().put("left",new AbstractAction() {
      /**
       * Moves left.
       * @param ae ActionEvent
       */
      public void actionPerformed(ActionEvent e) {   
        left();
        repaint();
        validate();
        
      }
    });
    getActionMap().put("right",new AbstractAction() {
      /**
       * Moves right.
       * @param ae ActionEvent
       */
      public void actionPerformed(ActionEvent e) {     
        right();
        repaint();
        validate();
      }
    }); 
    
    chidd = new JLabel (chid);
    
    setLoc(chidd,0,360);
    
    
    setLoc (new JLabel("Time Remaining:"),505,410);
    setLoc(Happy.t.getLabel(),505,430);
    JLabel topHat = new JLabel(new ImageIcon("hats3/Images/GameImages/bigtophat.png"));
    setLoc(topHat,400,414);
    numHats = new JLabel("x"+chid.myHats.size());
    setLoc(numHats,460,418);
    scoreLabel = new JLabel ("Score: " + score);
    setLoc (scoreLabel, 300, 418);
    
  }
  /*
   * Creates an instance of Question based on level.
   * @exception Exception e
   */
  public void makeQuestions()
  {
    try
    {
      q = new Questions(level + "");
      t = new TeemoQuestions ();
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(this, "The questions couldn't be loaded!", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  /*
   * Creates a dialog for the questions.
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * s - FlowLayout - creates an instance of FlowLayout to set to the dialog<br>
   * question - JLabel - stores the question<br>
   * answerStrings - String[] - stores the possible answers for the corresponding question<br>
   * answers - JButton[] - stores the possible answers within JButtons<br>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int r/r < 0/r++<br>
   *  Purpose: loops to add the corresponding possible answers to its button<br>
   * </p>
   */
  public void dialog()
  {
    d = new JDialog ((JFrame)null,"Question");
    d.setSize (370,200);
    d.setResizable (false);
    FlowLayout s = new FlowLayout ();
    d.setLayout (s);
    JLabel question;
    String[] answerStrings;
    if (isTeemo == true)
    {
      question = new JLabel (t.getQuestion());
      answerStrings= t.getAnswers();
    }
    else
    {
      question = new JLabel (q.getQuestion());
      answerStrings= q.getAnswers();
    }
    JButton[] answers= new JButton[4];
    d.add(question);
    for (int r = 0; r<4; r++)
    {
      answers[r]= new JButton(answerStrings[r]);
      d.add (answers[r]);
    }
    answers[0].addActionListener(new ActionListener()
                                   {
      /**
       * Allows program to know the user has chosen the first answer.
       * @param ae ActionEvent
       */
      public void actionPerformed(ActionEvent e)
      {
        buttonAction(1);
      }
    });
    answers[1].addActionListener(new ActionListener()
                                   {
      /**
       * Allows program to know the user has chosen the second answer.
       * @param ae ActionEvent
       */
      public void actionPerformed(ActionEvent e)
      {
        buttonAction(2);
      }
    });
    answers[2].addActionListener(new ActionListener()
                                   {
      /**
       * Allows program to know the user has chosen the third answer.
       * @param ae ActionEvent
       */
      public void actionPerformed(ActionEvent e)
      {
        buttonAction(3);
      }
    });
    answers[3].addActionListener(new ActionListener()
                                   {
      /**
       * Allows program to know the user has chosen the fourth answer.
       * @param ae ActionEvent
       */
      public void actionPerformed(ActionEvent e)
      {
        buttonAction(4);
      }
    });
    
    d.setLocationRelativeTo (this);
    d.setVisible (true);
  }
  /**
   * Adds hat if the user chose the correct answer, removes hat otherwise<br>
   * (if the hats is not equal to 0). Changes the numHats as well as<br>
   * scoreLabel.
   * @param x int The answer which the user chose
   */
  public void buttonAction(int x)
  {
    d.dispose();
    remove (d);
    if (isTeemo == false)
    {
      if (x == q.getCorrectAnswer())
      {
        addHat();
      }
      else
      {
        if (chid.myHats.size () != 0)
        {
          removeHat ();
        }
      }
    }
    else
    {
      if (x == t.getCorrectAnswer())
      {
        addHat();
      }
      else
      {
        if (chid.myHats.size () != 0)
        {
          removeHat ();
        }
      }
    }
    numHats.setText("x"+chid.myHats.size());
    scoreLabel.setText("Score: " + score);
    isTeemo = false;
  }
  
  
  /**
   * Sets the location of a JComponent using the values passed, and adds it to the panel.
   * @param b JComponent the component to set location
   * @param x int the x coordinate for the JComponent to be set
   * @param y int the y coordinate for the JComponent to be set
   */
  public void setLoc(JComponent b, int x,int y)
  {
    SpringLayout.Constraints z = sLayout.getConstraints (b);
    z.setX (Spring.constant (x));
    z.setY (Spring.constant (y));   
    add (b);
  }
  /**
   * First level of the game, sets level to 1, gets corresponding questions,<br>
   * sets the blocks on screen, as well as set the coordinates of each block.
   */
  public void levelOne ()
  {
    level =1;
    makeQuestions();
    setLoc (you, 40,375);
    setLoc(blocks.get(0),50,50);
    setLoc(blocks.get(1),150,300);
    setLoc(blocks.get(2),250,100);
    setLoc(blocks.get(3),350,250);
    setLoc(blocks.get(4),450,150);
    coordinates [0] = 50;
    coordinates [1] = 50;
    coordinates [2] = 150;
    coordinates [3] = 300;
    coordinates [4] = 250;
    coordinates [5] = 100;
    coordinates [6] = 350;
    coordinates [7] = 250;
    coordinates [8] = 450;
    coordinates [9] = 150;
    allLevel ();
  }  
  
  /**
   * Second level of the game, sets level to 2, gets corresponding questions,<br>
   * sets the blocks on screen, as well as set the coordinates of each block.
   */
  public void levelTwo ()
  {
    level=2;
    makeQuestions();
    setLoc (you, 40,375);
    setLoc(blocks.get(0),75,50);
    setLoc(blocks.get(1),200,300);
    setLoc(blocks.get(2),450,350);
    setLoc(blocks.get(3),50,250);
    setLoc(blocks.get(4),350,150);
    coordinates [0] = 75;
    coordinates [1] = 50;
    coordinates [2] = 200;
    coordinates [3] = 300;
    coordinates [4] = 450;
    coordinates [5] = 350;
    coordinates [6] = 50;
    coordinates [7] = 250;
    coordinates [8] = 350;
    coordinates [9] = 150;
    allLevel ();
  }
  
  
  /**
   * Third level of the game, sets level to 3, gets corresponding questions,<br>
   * sets the blocks on screen, as well as set the coordinates of each block.
   */
  public void levelThree ()
  {
    level=3;
    makeQuestions();
    setLoc(teemo,590,340);
    setLoc (you, 40,375);
    setLoc(blocks.get(0),50,50);
    setLoc(blocks.get(1),150,300);
    setLoc(blocks.get(2),450,100);
    setLoc(blocks.get(3),350,250);
    setLoc(blocks.get(4),250,150);
    coordinates [0] = 50;
    coordinates [1] = 50;
    coordinates [2] = 150;
    coordinates [3] = 300;
    coordinates [4] = 450;
    coordinates [5] = 100;
    coordinates [6] = 350;
    coordinates [7] = 250;
    coordinates [8] = 250;
    coordinates [9] = 150;
    allLevel ();
  }
  /**
   * Makes sure a checkpoint/time boost does not land out of the screen, or on a block.<p>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int i/i < 10/i+=2<br>
   *  Purpose: loops to see if the coordinate would overlap with any blocks<br>
   * @param x int the x coordinate
   * @param y int the y coordinate
   * @return boolean whether the coordinate is in a valid location or not
   */
  public boolean checkLoc (int x, int y)
  {
    for (int i = 0; i < 10; i+=2)
    {
      if (x + 29 > coordinates [i] && x < coordinates [i] + 160 && y + 29 > coordinates [i + 1] && y < coordinates [i + 1] + 24)
      {
        return false;
      }
    }
    if (x <90 && x > 360)
    {
      return false;
    }
    return true;
  }
  /**
   * Adds a checkpoint (based on parameter passed) to the screen, enters a do while <br>
   * loop to make sure it does not go under any blocks.<br>
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * x - int - x coordinate to be randomized, tested, and added<br>
   * y - int - y coordinate to be randomized, tested and added<br>
   * While Loops:<br>
   *  Purpose: loops to check if the position is valid on the screen<br>
   * @param i int index of the checkpoint to be added
   * </p>
   */
  public void addC (int i)
  {
    int x;
    int y;
    do 
    {
      x = (int) (Math.random () * 581) + 40;
      y = (int) (Math.random () * 375);
    }
    while (checkLoc (x,y) == false);
    setLoc(checkpoints.get(i),x,y);
  }
  /**
   * Adds a time boost (based on parameter passed) to the screen, enters a do<br>
   * while loop to make sure it does not go under any blocks.<br>
   * <p>
   * Variable Dictionary:<br>
   * Variable - Type - Purpose<br>
   * x - int - x coordinate to be randomized, tested, and added<br>
   * y - int - y coordinate to be randomized, tested and added<br>
   * While Loops:<br>
   *  Purpose: loops to check if the position is valid on the screen<br>
   * @param i int index of the time boost to be added
   * </p>
   */
  public void addT (int i)
  {
    int x;
    int y;
    do 
    {
      x = (int) (Math.random () * 581) + 40;
      y = (int) (Math.random () * 375);
    }
    while (checkLoc (x,y) == false);
    setLoc(boosts.get(i),x,y);
  }
  /**
   * Sets up the checkpoints and time boosts for each level.<br>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < 5/x++<br>
   *  Purpose: loops to add a checkpoint 5 times<br>
   */
  public void allLevel ()
  {
    for (int x = 0; x<5; x++)
    {
      addC (x);
    }
    addT (0);       
    addT (1); 
  }
  /**
   * Moves your character up by one pixel. Does not move if you are out of time,<br>
   * or if you are on the edge of the screen. Also does not move if you are<br>
   * on a block. Compares your location with those of checkpoints and time boosts,<br>
   * so the corresponding action can occur once close enough.<p>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < blocks.size()/x++<br>
   *  Purpose: loops to compare your location with each block<br>
   *  int x/x < checkpoints.size()/x++<br>
   *  Purpose: loops to compare your location with each checkpoint<br>
   *  int x/x < boosts.size()/x++<br>
   *  Purpose: loops to compare your location with each block<br>
   */
  public void up()
  {
    if (Happy.t.getTime () != 0)
    {
      if (you.getY()<=0)
        return;
      for (int x = 0; x<blocks.size(); x++)
      {
        if (you.getX()<blocks.get(x).getX()+160&&you.getX()+24>blocks.get(x).getX())
        {
          if (you.getY()==blocks.get(x).getY()+24)
          {
            return;
          }
        }
      }
      for (int x = 0; x<checkpoints.size();x++)
      {
        if (you.getX()<checkpoints.get(x).getX()+29&&you.getX()+24>checkpoints.get(x).getX())
        {
          if (you.getY()==checkpoints.get(x).getY()+29)
          {
            checkpointFound(x);
            break;
          }
        }
      }
      for (int x = 0; x<boosts.size();x++)
      {
        if (you.getX()<boosts.get(x).getX()+29&&you.getX()+24>boosts.get(x).getX())
        {
          if (you.getY()==boosts.get(x).getY()+29)
          {
            timeBoostFound(x);
            return;
          }
        }
      }
      remove(you);
      setLoc(you,you.getX(),you.getY()-1);
    }
  }
  /**
   * Moves your character down by one pixel. Does not move if you are out of time,<br>
   * or if you are on the edge of the screen. Also does not move if you are<br>
   * on a block. Compares your location with those of checkpoints, time boosts,<br>
   * and Teemo, so the corresponding action can occur once close enough.<p>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < blocks.size()/x++<br>
   *  Purpose: loops to compare your location with each block<br>
   *  int x/x < checkpoints.size()/x++<br>
   *  Purpose: loops to compare your location with each checkpoint<br>
   *  int x/x < boosts.size()/x++<br>
   *  Purpose: loops to compare your location with each block<br>
   */
  public void down()
  {
    if (Happy.t.getTime () != 0)
    {
      if (you.getY()>=375)
        return;
      if (you.getX()<teemo.getX()+48&&you.getX()+24>teemo.getX())
      {
        if (you.getY()+25==teemo.getY())
        {
          teemoFound ();
        }
      }
      for (int x = 0; x<5; x++)
      {
        if (you.getX()<blocks.get(x).getX()+160&&you.getX()+24>blocks.get(x).getX())
        {
          if (you.getY()+25==blocks.get(x).getY())
          {
            return;
          }
        }
      }
      for (int x = 0; x<checkpoints.size();x++)
      {
        if (you.getX()<checkpoints.get(x).getX()+29&&you.getX()+24>checkpoints.get(x).getX())
        {
          if (you.getY()+25==checkpoints.get(x).getY())
          {
            checkpointFound(x);
            return;
          }
        }
      }
      for (int x = 0; x<boosts.size();x++)
      {
        if (you.getX()<boosts.get(x).getX()+29&&you.getX()+24>boosts.get(x).getX())
        {
          if (you.getY()+25==boosts.get(x).getY())
          {
            timeBoostFound(x);
            return;
          }
        }
      }
      remove(you);
      setLoc(you,you.getX(),you.getY()+1);
    }
  }
  /**
   * Moves your character right by one pixel. Does not move if you are out of time,<br>
   * or if you are on the edge of the screen. Also does not move if you are<br>
   * on a block. Compares your location with those of checkpoints, time boosts,<br>
   * and Teemo, so the corresponding action can occur once close enough.<p>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < blocks.size()/x++<br>
   *  Purpose: loops to compare your location with each block<br>
   *  int x/x < checkpoints.size()/x++<br>
   *  Purpose: loops to compare your location with each checkpoint<br>
   *  int x/x < boosts.size()/x++<br>
   *  Purpose: loops to compare your location with each block<br>
   */
  public void right()
  {
    if (Happy.t.getTime () != 0)
    {
      if (you.getX()+24>=645)
        return;
      if (you.getY()<teemo.getY()+58&&you.getY()+25>teemo.getY())
      {
        if (you.getX()+24==teemo.getX())
        {          
          teemoFound ();
        }
      }
      for (int x = 0; x<5; x++)
      {
        if (you.getY()<blocks.get(x).getY()+24&&you.getY()+25>blocks.get(x).getY())
        {
          if (you.getX()+24==blocks.get(x).getX())
          {
            return;
          }
        }
      }
      for (int x = 0; x<checkpoints.size();x++)
      {
        if (you.getY()<checkpoints.get(x).getY()+29&&you.getY()+25>checkpoints.get(x).getY())
        {
          if (you.getX()+24==checkpoints.get(x).getX())
          {
            checkpointFound(x);
            return;
          }
        }
      }
      for (int x = 0; x<boosts.size();x++)
      {
        if (you.getY()<boosts.get(x).getY()+29&&you.getY()+25>boosts.get(x).getY())
        {
          if (you.getX()+24==boosts.get(x).getX())
          {
            timeBoostFound(x);
            return;
          }
        }
      }
      remove(you);
      setLoc(you,you.getX()+1,you.getY());
    }
  }
  /**
   * Moves your character left by one pixel. Does not move if you are out of time,<br>
   * or if you are on the edge of the screen. Also does not move if you are<br>
   * on a block. Compares your location with those of checkpoints and time boosts,<br>
   * so the corresponding action can occur once close enough.<p>
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < blocks.size()/x++<br>
   *  Purpose: loops to compare your location with each block<br>
   *  int x/x < checkpoints.size()/x++<br>
   *  Purpose: loops to compare your location with each checkpoint<br>
   *  int x/x < boosts.size()/x++<br>
   *  Purpose: loops to compare your location with each block<br>
   */
  public void left ()
  {
    if (Happy.t.getTime () != 0)
    {
      if (you.getX()<=40)
        return;
      for (int x = 0; x<5; x++)
      {
        if (you.getY()<blocks.get(x).getY()+24&&you.getY()+25>blocks.get(x).getY())
        {
          if (you.getX()==blocks.get(x).getX()+160)
          {
            return;
          }
        }
      }
      for (int x = 0; x<checkpoints.size();x++)
      {
        if (you.getY()<checkpoints.get(x).getY()+29&&you.getY()+25>checkpoints.get(x).getY())
        {
          if (you.getX()==checkpoints.get(x).getX()+29)
          {
            checkpointFound(x);
            return;
          }
        }
      }
      for (int x = 0; x<boosts.size();x++)
      {
        if (you.getY()<boosts.get(x).getY()+29&&you.getY()+25>boosts.get(x).getY())
        {
          if (you.getX()==boosts.get(x).getX()+29)
          {
            timeBoostFound(x);
            return;
          }
        }
      }
      remove (you);
      setLoc(you,you.getX()-1,you.getY());
    }
  }
  public void teemoFound ()
  {
    isTeemo = true;
    dialog ();
    remove (teemo);
    setLoc (teemo, 590, 340);
  }
  /**
   * Is called when you are near a time boost. It removes the current time<br>
   * boost and adds a new one. It also gives the user 20 extra seconds.
   * @param x int index of the time boost found
   */
  public void timeBoostFound (int x)
  {
    remove (boosts.get (x));
    boosts.add (new JLabel(new TimeBoost()));
    addT (boosts.size () - 1);
    boosts.remove (x);
    Happy.t.setTime (20);
  }
  /**
   * Is called when you are near a check point. It removes the current<br>
   * checkpoint and adds a new one. It also calls dialog which pops up a<br>
   * question.
   * @param x int index of the checkpoint found
   */
  public void checkpointFound(int x)
  {
    remove (checkpoints.get(x));
    checkpoints.add (new JLabel(new Checkpoint ()));
    addC (checkpoints.size () -1 );
    checkpoints.remove(x);
    dialog ();
  }
  /**
   * Adds a hat to Chid, updates the JLabels of hats, and draws the hat. Also<br>
   * adds the correct score. Depending on your level and the number of hats<br>
   * you have, you can level up and and recieve extra score.
   * For Loops:<br>
   *  Variable/Condition/Increment<br>
   *  int x/x < 5/x++<br>
   *  Purpose: removes all the checkpoints and blocks from the screen so they<br>
   *  be re-added.
   */
  public void addHat()
  {
    if (isTeemo == true)
    {
      chid.addTeemo ();
    }
    else
    {
      chid.addHat();
    }
    myHatLabels.add(new JLabel (chid.getHat(chid.myHats.size()-1)));
    setLoc(myHatLabels.get(myHatLabels.size()-1),0,360-(chid.myHats.size()*10));
    if (chid.myHats.get(chid.myHats.size ()-1)==chid.allHats.get(chid.allHats.size () - 1))
    {
      score+=25;
    }
    else
    {
      score +=10;
    }
    if((level ==1&&chid.myHats.size()==5)||(level ==2&&chid.myHats.size()==10))
    {
      for (int x = 0; x<5;x++)
      {
        remove(checkpoints.get(x));
        remove (blocks.get(x));
      }
      remove (boosts.get(1));
      remove (boosts.get(0));
      remove(you);
      if (level ==2&&chid.myHats.size()==10)
      {
        levelThree();
        score = score+100;
      }
      else
      {
        levelTwo();
        score = score+50;
      }
    }
  }
  /**
   * Removes a hat from chid and the JLabels containing the hats. ALso removes<br>
   * the corresponding score.
   */
  public void removeHat()
  {
    if (isTeemo == true)
    {
      int l = (chid.myHats.size ()-1)/2;
      for (int x = 0; x<=l; x++)
      {
        if (chid.myHats.get(chid.myHats.size ()-1)==chid.allHats.get(chid.allHats.size () - 1))
          score -= 25;
        else
          score -= 10;
        remove (myHatLabels.get(myHatLabels.size()-1));
        myHatLabels.remove (myHatLabels.size ()-1);
        chid.removeHat(chid.myHats.size()-1);
      }
    }
    else
    {
       if (chid.myHats.get(chid.myHats.size ()-1)==chid.allHats.get(chid.allHats.size () - 1))
         score-=25;
       else
         score -= 10;
       remove (myHatLabels.get(myHatLabels.size()-1));
       myHatLabels.remove (myHatLabels.size ()-1);
       chid.removeHat(chid.myHats.size()-1);
    }
    
  }
}
