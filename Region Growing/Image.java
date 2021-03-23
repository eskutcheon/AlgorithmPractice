import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Image // saves pgm image to binary matrix
{
    private int numColumns;
    private int numRows;
    private int maxVal; // allowed rgb - not sure why this is needed but directions mention it - later project might need checking if in range [0,65536]
    public Pixel[][] imageMatrix; // make private with accessor methods later
    public Image(String filename, int baseColor)
    {
        try
        {
            // referenced: https://stackoverflow.com/questions/3639198/how-to-read-pgm-images-in-java
            FileInputStream reader = new FileInputStream(filename);
            Scanner scan = new Scanner(reader);
            scan.next(); // tosses magic number - assuming all are P2, not P5 since instructions said ASCII
            // hardcoding it this time since all headers are in form MagicNum-width-height-maxVal
            numColumns = scan.nextInt();
            //System.out.println(numColumns);
            numRows = scan.nextInt();
            //System.out.println(numRows);
            maxVal = scan.nextInt();
            //System.out.println(maxVal);
            imageMatrix = new Pixel[numRows][numColumns];
            int rowIndex = 0;
            int colIndex = 0;
            while(scan.hasNextInt())
            {
                if(colIndex >= numColumns)
                {
                    colIndex = 0;
                    rowIndex++;
                }
                int newRGB = scan.nextInt();
                //System.out.println(newRGB);
                if(newRGB == baseColor)
                    imageMatrix[rowIndex][colIndex] = new Pixel(rowIndex, colIndex, true);
                else
                    imageMatrix[rowIndex][colIndex] = new Pixel(rowIndex, colIndex, false);
                colIndex++;
            }
            reader.close();
            scan.close();
        }
        catch(IOException ex) // add nested try-catch later to handle general FIO exceptions
        {
            ex.printStackTrace(System.out);
        }
    }

    public int getNumRows()
    {
        return this.numRows;
    }
    public int getNumColumns()
    {
        return this.numColumns;
    }
}