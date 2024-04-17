public class dijkstrasProcessor<T> {
    //this class is basically collects information of each vetex

    //it stores three parameters of each vertex
    private Vertices<T> vertex;
    //the vertex variables stores the vertex which this class holds
    private boolean processed;
    //the processed boolean value tells if the vertex in this class object is actually processed or not
    private int distance;
    //the distance variable tells us what is the shortest distance found for this vertex so far

    //once the object has been inititated
    public dijkstrasProcessor(){

    }

    //this is a setter method
    public void add(Vertices<T> v){
        //once the user passes a parameter vertex, the variables vertex, processed, and distance will be set to that vertex's parameters
        this.vertex = v;
        this.processed = v.isProcessed();
        //we should add a cast so that we don't create double, int conversion errors
        this.distance = (int)v.distance();
    }

    //this is a getter method
    public Vertices<T> vertex(){
        //the unprocessed() method will use this method to return the vertex
        return vertex;
    }

    public boolean isProcessed(){
        //this is another getter method for the boolean processed
        //this will tell the unprocessed() method if the vertex is processed or not
        return this.processed;
    }

    public int distance(){
        //this is another getter method for the boolean processed
        //this is used to determine shortest distance found so far for this vertex
        return this.distance;
    }


}
