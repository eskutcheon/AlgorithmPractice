public class Region
{
    private int maxX;
    private int maxY;
    private int pixelCount = 0;

    public Region(int xBoundary, int yBoundary)
    {
        maxX = xBoundary;
        maxY = yBoundary;
    }

    public void searchRegion(Image imgObj, int rowIndex, int colIndex) // where x,y are the coordinates of the seed value in Image image member
    {
        if(imgObj.imageMatrix[rowIndex][colIndex].getVisited())
            return;
        imgObj.imageMatrix[rowIndex][colIndex].setVisited(true);
        pixelCount++;
        if(!edgeDetected(rowIndex-1, colIndex) && isRightColor(imgObj.imageMatrix[rowIndex-1][colIndex]))
            searchRegion(imgObj, rowIndex-1, colIndex); // north branch
        if(!edgeDetected(rowIndex, colIndex+1) && isRightColor(imgObj.imageMatrix[rowIndex][colIndex+1]))
            searchRegion(imgObj, rowIndex, colIndex+1); // east branch
        if(!edgeDetected(rowIndex+1, colIndex) && isRightColor(imgObj.imageMatrix[rowIndex+1][colIndex]))
            searchRegion(imgObj, rowIndex+1, colIndex); // south branch
        if(!edgeDetected(rowIndex, colIndex-1) && isRightColor(imgObj.imageMatrix[rowIndex][colIndex-1]))
            searchRegion(imgObj, rowIndex, colIndex-1); // west branch
    }
    private boolean edgeDetected(int x, int y)
    {
        return ((x < 0 || x >= maxX) || (y < 0 || y >= maxY));
    }
    private boolean isRightColor(Pixel pObj)
    {
        return pObj.getColor(); // unlike the above, this tests if it is the correct color we are searching for
    }
    public int getPixelCount() // used in growregion
    {
        return this.pixelCount;
    }

}