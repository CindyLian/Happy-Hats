package hats3.print;

import java.awt.print.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

/**
 * HighScorePrinter implements Printable contains the content that is going to be printed.
 * @author Cindy Lian
 * @author Jennifer Cai
 * @version 4.0, June 10, 2013
 */
public class HighScorePrinter implements Printable 
{
  /**
   * print () method is overriden from the Printable interface. Looks for the high scores file, and prints out the 
   * information in the file.
   * <p>
   * Variable Dictionary<br>
   * g2d - Graphics2D - 2d graphics of graphics g in parameter<br>
   * in - BufferedReader - reads in the file for the high scores.<br>
   * i - BufferedImage - reads in the image of the company logo<br>
   * x - int - loop trace<br>
   * </p>
   * @param g Graphics
   * @param pf PageFormat
   * @param page int
   * @return int print
   * @throws PrinterException
   * @exception IOException
   * @exception Exception
   */
  public int print (Graphics g, PageFormat pf, int page) throws PrinterException 
  {
    if (page > 0)
    {
      return NO_SUCH_PAGE;
    }
    
    Graphics2D g2d = (Graphics2D) g;
    g2d.translate(pf.getImageableX(), pf.getImageableY());

    try
    {
      BufferedReader in = new BufferedReader (new FileReader ("hats3/tFiles/hatScores.turt"));
      if (in.readLine ().equals ("catbunnysnaketurtlepig"))
      {
        BufferedImage i = ImageIO.read (new File ("hats3/Images/Printing/SHRBunnyLogo.png"));
        g.drawImage (i, 0,0,null);
        g.drawString ("Happy Hats III High Scores", 0, 120);
        try
        {
          for (int x = 0; x< 10; x++)
          {
            g.drawString (in.readLine (), 0, 150 + x * 15);
            g.drawString (in.readLine (), 100, 150 + x * 15);
            g.drawString (in.readLine (), 200, 150 + x * 15);
          }
        }
        catch (Exception e)
        {
        }
      }
    }
    catch (IOException e)
    {
      
    }
    return PAGE_EXISTS;
  }
}