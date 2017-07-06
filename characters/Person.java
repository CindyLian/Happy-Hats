package hats3.characters;

import hats3.hats.*;

import java.util.ArrayList;
import javax.swing.*;
/**
 * This class creates a Person that contains the hats in the game.
 * @author Cindy Lian
 * @author Jennifer Cai
 * @version 4.0, June 10, 2013
 */
public class Person extends ImageIcon
{
  /**
   * allHats - ArrayList <Hat> - Stores the list of hats that a person can get.
   */
  public ArrayList <Hat> allHats = new ArrayList < Hat > ();
  /**
   * myHats - ArrayList <Hat> - Stores the list of hats that a person has.
   */
  public ArrayList <Hat> myHats = new ArrayList < Hat > ();
  
  /**
   * Creates a person on an ImageIcon. Adds all the different has to the allHats array.
   */
  public Person ()
  {
    super ("hats3/Images/GameImages/insertnamehere.png");
    allHats.add(new BucketHat());
    allHats.add(new PartyHat());
    allHats.add(new PropellerHat());
    allHats.add(new SantaHat());
    allHats.add(new SombreroHat());
    allHats.add(new TopHat());
    allHats.add (new TeemoHat ());
  }
  
  /**
   * Adds a teemohat to the myHats arraylist.
   */
  public void addTeemo ()
  {
    myHats.add (allHats.get (allHats.size ()-1));
  }
  
  /**
   * Adds a random hat to myHats (not including the teemo hat).
   */
  public void addHat ()
  {
      int x = (int)(Math.random()*6);
      myHats.add(allHats.get(x));
  }
  
  /**
   * Gets the hat at the int index.
   * @param index int of the index of the array
   */
  public Hat getHat(int index)
  {
      return (myHats.get(index));
  }
  
  /**
   * Returns the ArrayList of Hats
   * @return ArrayList <Hat> hats 
   */
  public ArrayList <Hat> getHats ()
  {
    return myHats;
  }
  
  /**
   * Removes the hat at int index.
   * @param index int
   */
  public void removeHat (int index)
  {
    myHats.remove(index);
  }
}
