public class Pixel
{
    // saving coordinates is totally unnecessary here and you should probably remove them as well as from any Pixel instantiation calls elsewhere
        // only keeping it for now to refactor later - mostly to be able to print the regions in new images later
    private int xIndex;
    private int yIndex;
    private boolean visited = false; // initialized to False before being added to Image object
    private boolean baseColor;

    public Pixel(int x, int y, boolean RGB)
    {
        this.xIndex = x; // mostly using 'this' because it's more Pythonic and I feel better
        this.yIndex = y;
        this.baseColor = RGB;
    }
    public int getColIndex()
    {
        return xIndex;
    }
    public int getRowIndex()
    {
        return yIndex;
    }
    public boolean getColor()
    {
        return baseColor;
    }
    public void setVisited(boolean flag)
    {
        this.visited = flag;
    }
    public boolean getVisited()
    {
        return visited;
    }
}