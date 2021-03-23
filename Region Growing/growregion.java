import java.util.ArrayList;
public class growregion
{
    public static void main(String[] args)
    {
        String tempString = "test1.pgm"; // (String)args[0]
        int tempInt = 255; // Integer.parseInt(args[1])
        Image imageObj = new Image(tempString, tempInt);
        ArrayList<Integer> regionCounts = new ArrayList<Integer>();
        int maxRows = imageObj.getNumRows(); // double check this doesn't get confused later
        int maxCols = imageObj.getNumColumns();
        for(int i = 0; i < maxCols; i++)
        {
            for(int j = 0; j < maxRows; j++)
            {
                if(!imageObj.imageMatrix[i][j].getVisited()) // assumes Pixel's color member is true if it matches the passed RGB value
                {
                    if(!imageObj.imageMatrix[i][j].getColor())
                        imageObj.imageMatrix[i][j].setVisited(true); // only this statement should set any of the opposite RGB pixels to visited
                    else
                    {
                        Region regionObj = new Region(maxCols, maxRows);
                        regionObj.searchRegion(imageObj, i, j);
                        regionCounts.add(regionObj.getPixelCount());
                    }
                } // else: do nothing
            } // end inner for
        } // end outer for
        System.out.println("Zachary Garris");
        System.out.println(tempString);
        for(int i = 0; i < regionCounts.size(); i++)
        {
            System.out.print(regionCounts.get(i));
            System.out.print(", ");
        }
    }
}

/* plan so far:
    create int array (later going to refactor to Region array for myself) of each region's pixel counts matching passed RGB in growregion
    create Image Object in growregion using passed filename and rgb value
        Image constructor reads pgm file and fills a matrix of Pixel objects with each one instantiated with coordinates (might change later) and the pixel's rgb value
            each Pixel object then has an empty parent member and child node array member
    nested loop in growregion iterates through each pixel in the Image object
        if the pixel matches passed RGB && is unvisited, instantiate new Region object
            Region object creates quadtree where child pixels are pixels to the north, east, south, and west (in that order) of its parent
                if potential child pixel lies beyond the image boundaries, has been visited, or is the wrong RGB value, child is set to NULL
                else child node added and treated as new parent - branches until all children are NULL
                    Count member variable of region incremented and pixel node marked as visited
            when region is finished branching, save count member to array in growregion
        elif RGB matches but it's been visited, pass
        else mark as visited

*/