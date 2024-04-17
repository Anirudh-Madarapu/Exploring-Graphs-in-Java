import java.util.*;

public class Vertices<T> {
   //each vertex will have a String label
   private String label;

   //these variables are used by dijkstra's Alrogithm
   private double distance;//the shortest distance found by dijkstra's Alrogithm
   private boolean processed;//determine if its processed or not
   private Vertices<T> dijParent;//parent will store the neighbour vertex which will lead to starting vertex

   //these are the variables used for page rank
   private double rank;//stores current rank of the page
   private double prevrank;//stores previously assigned rank of the page
   private LinkedList<Vertices<T>> incoming;//stores set of incoming vertices

   //gives the number of direct edges pointing
   private int incomesize;
   //in a directed graph, if atleast one vertex point to this vertex, then this boolean will be set to true
   //this boolean is used in isCyclic for directed graph
   private boolean isdirect;

   public Vertices(String label){//instantiating a class called vertices
         //the label will be assigned to string label
         this.label = label;
         //the distance of every vertex is infinity
         this.distance = 2147483647;
         //every node is unprocessed by default
         this.processed = false;
         //the dij parent is also null by default
         this.dijParent = null;
         //the rank and prevrank is 0 be default
         this.rank = 0;
         this.prevrank = 0;
         //every income array has size of 10 by default
         this.incoming = (LinkedList<Vertices<T>>) new LinkedList<Vertices<T>>();
         //byefault size of the array is 10 this may vary based on incoming vertices
         this.incomesize = 0;
         //income size is 0 by default
         this.isdirect = false;

   }

   public void addVertex(String label, T value){//this method will take a parameter and assigns a vertex to it
    //to handle string labels, we are using a String called Label
   }

   public String label(){
      //returns the label of the string
      return this.label;
   }

   public void markInitial(){
      //marks this vertex as the inital vertex and used by dijAlgorithm
      this.distance  = 0;
   }

   public void setDijParent(Vertices<T> v){
      //sets the dijParent to the given vertex
      //this method is used by Dijkstra's algorithm
      this.dijParent = v;
   }
   public Vertices<T> dijParent(){
      //this method will return the parent of the vertex
      return this.dijParent;
   }

   public void setdirect(){
      //this method is used by isConnected method()
      //If we have an edge from A -> B but not from B -> A, and if there is not other path to A from B,
      this.isdirect = true;
   }
   public boolean isDirect(){//this is for directed edges
      //when the vertex has atleast one directed edge it turns to true
      return this.isdirect;
   }

   public double distance(){
      //returns the distance of the vertex
      //initially this is set to infinity
      return this.distance;
   }

   //the below methods are pagerank methods

   public void setprevrank(double i){
      //sets pevrank to a certain double

      this.prevrank = i;
   }
   public double getprevrank(){
      //fetches the current prevrank of the vertex
      return this.prevrank;
   }
   public void setRank(double i){
      //sets rank to a certain double
      this.rank = i;
   }
   public double getRank(){
      //fetches the current rank of the vertex
      return this.rank;
   }

   public void addIncome(Vertices<T> v){
      //adds a vertex to the income vertices array
      this.incoming.add(v);
      //increments the size of income integer
      incomesize++;
   }
   public LinkedList<Vertices<T>> getIncome(){
      //this method just gives the array of all incoming vertices
      //this method is used by page rank algorithm
      return this.incoming;
   }

   public void setDistance(double distance){
      //this is used by Dijkstras Alrogithm and sets distance of the vertex
      this.distance = distance;

   }


   public void processed(){
      //marks this vertex as porcessed
      this.processed = true;
   }

   public void unprocess(){
      //this is another method used by dismissDij() to mark every vertex as unprocess
      this.processed = false;
   }

   public void setBackDist(){
      //this is useful when me call dismissDij() method
      //this will set back every vertex's distance to infinity
      this.distance = 2147483647;
   }

   //determines whether the vertex is processed or not
   public boolean isProcessed(){
      return this.processed;
   }
}
