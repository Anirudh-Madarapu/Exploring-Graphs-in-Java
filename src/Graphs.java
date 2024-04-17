import java.util.HashMap;
import java.util.Iterator;
import java.util.*;
public class Graphs<T> {
    public HashMap<Vertices<T>, Edges<T>> graph; //the graph hash map has a set of vertices as keys
    //this hashmap contains edges as values
    private double size;
    //the size keeps track of our size
    private dijkstrasProcessor<T>[] array;

    public Graphs(){
        //we are instantiating a new graph that has
        this.graph = new HashMap<Vertices<T>, Edges<T>>();

    }
    public double size(){//getter for size
        return size;
    }
    public HashMap<Vertices<T>, Edges<T>> getGraph(){
        // this getter is so helful for non static methods, isBarpartite
        return this.graph;
    }
    public Vertices<T> addVertex(String label){
        size++;
        //whenever we add a value, we will create a vertex, edge and put them in the Graph's hash Map
        Vertices<T> tempVertex = new Vertices<T>(label);
        //initially the edge is empty
        Edges<T> tempEdge = new Edges<T>();
        //the tempVertex and tempEdge will be key and values respectively
        graph.put(tempVertex,tempEdge);

        //we also want to keep track of added vertices
        //therefore, we have to return the added vertex and store it in a variable
        //we will store the vertices in the main method and use them for testing purposes

        return tempVertex;

    }


    public void addEdge(Vertices<T> v1, Vertices<T> v2, double weight){
        //assuming this graph is non-directional we will add weight and connect these two vertices
        Edges<T> tempedge1 = graph.get(v1);
        //System.out.println(tempedge1);
        tempedge1.put(v2,weight);
        v2.addIncome(v1);

        Edges<T> tempedge2 = graph.get(v2);
        //System.out.println(tempedge2);
        tempedge2.put(v1,weight);
        v1.addIncome(v2);


    }
    public void addDirectEdge(Vertices<T> v1, Vertices<T> v2, double weight){
        //assuming this graph is non-directional we will add weight and connect these two vertices
        Edges<T> tempedge1 = graph.get(v1);
        //System.out.println(tempedge1);
        tempedge1.put(v2,weight);
        v2.addIncome(v1);
        v2.setdirect();
    }

    public String toString(){
      //goes through all the vertices and for each vertex we will go through their connections
       Iterator<HashMap.Entry<Vertices<T>, Edges<T>>> i = graph.entrySet().iterator();
       String s = "";
       while(i.hasNext()){
        //iterator of hashmap
         HashMap.Entry<Vertices<T>, Edges<T>> entry = i.next();
         Vertices<T> key = entry.getKey();
         Edges<T> value = entry.getValue();
         //colon followed by to string of edge
         System.out.println(key.label() + ": ");
         System.out.println(value.toString());
       }
       return s;
    }

    public boolean isConnected(){
        //this iterator is meant to check if every edge has atleast one connection
        Iterator<HashMap.Entry<Vertices<T>, Edges<T>>> it = graph.entrySet().iterator();
        while(it.hasNext()){
            HashMap.Entry<Vertices<T>,Edges<T>> entry = it.next();
            Edges<T> value = entry.getValue();
            if(!value.isEmpty()){
                //the if statment below is used for directed graphs
                //in a directed graph if A -> B but not B->A
                //then we might return false for this type of graph
                //hence we have boolean called direct
                if(!entry.getKey().isDirect())
                  return false;
            }
        }
        return true;
    }

    //let us check if our graph is cyclic
    public boolean isCyclic(){
       Set<Vertices<T>> s1 = (Set<Vertices<T>>)new HashSet<Vertices<T>>();
        //is cyclic adds every vertex visited to the set
       Iterator<HashMap.Entry<Vertices<T>,Edges<T>>> cyclic = graph.entrySet().iterator();
       HashMap.Entry<Vertices<T>,Edges<T>> entry = cyclic.next();

       //we will use the iterator to go through all vertices of the graph and them to the set

       Vertices<T> parent = entry.getKey();
       //the parent has been used by me to test
       //it was used in a print statment to test

       Edges<T> value = entry.getValue();

       double tempcount  = -1;

       while(cyclic.hasNext()){
             tempcount++;//for testing purpose

             parent = entry.getKey();
             value = entry.getValue();
             //we will return true if there is any vertex that is already in the set
             if(value.isCyclic(s1)){
                   return true;
             }
            //move to next cycle
            entry = cyclic.next();

       }

       return false;
    }
    public void copy(){
        //this method is specifically designed for dijkstrasAlrogithm
       //for dijkstrasAlrogithm, we want to store all unprocessed vertices inside an array
        array = (dijkstrasProcessor<T>[]) new dijkstrasProcessor<?>[(int)size];
        //we will instantiate an array with the size of the hashmap

        Iterator<HashMap.Entry<Vertices<T>,Edges<T>>> cyclic = graph.entrySet().iterator();
        //the array will have few null elements as dijkstrasAlrogithm goes forward
        //we will have a pointer
        double point = 0;

        while(cyclic.hasNext()){
            HashMap.Entry<Vertices<T>,Edges<T>> entry = cyclic.next();
            Vertices<T> key = entry.getKey();
            dijkstrasProcessor<T> d1 = new dijkstrasProcessor<T>();
            //first we will put every vertex into dijkstrasProcessor
            d1.add(key);

            if(!d1.isProcessed()){
                //if the vertex is unprocessed, then we put that in the array
                array[(int)point] = d1;
                System.out.println(array[(int)point].vertex().label() +" ident");
                point++;
            }

        }

    }
    public Vertices<T> unprocessed(){
        //this method is specifically designed for dijkstrasAlrogithm
        Iterator<HashMap.Entry<Vertices<T>,Edges<T>>> cyclic = graph.entrySet().iterator();
        HashMap.Entry<Vertices<T>,Edges<T>> entry = cyclic.next();
        // we will iterate through entire vertices set

        Vertices<T> key = entry.getKey();
        Vertices<T> min = new Vertices<T>("Temporary");

        copy();
        //we will put all the unproceesed vertices into an array
        double minimum=0;
        //once we got all the vertices into an array, we put find the minimum distance vertex
        if(array[0]!=null){
            //if the first element itself is not null, then we will consider this is the minimum
            minimum = array[0].distance();
        }
         //later, we will traverse throught the array and find the least distance vertex
        for(double i=0;i<array.length;i++){
           if(array[0]==null){
            //if first element is null break
            break;
           }
           if(array[(int)i].distance()<=minimum){
            //replace if you find a less distance one
             min = array[(int)i].vertex();
             minimum = array[(int)i].distance();
           }

           if(array[(int)i+1]==null){
            //if the next element is null, need not go for the next round so break
             break;
           }
        }
        //we want to change the entry set to next
        cyclic = graph.entrySet().iterator();
        entry = cyclic.next();

        return min;
    }
    public void dijkstrasAlrogithm(Vertices<T> start){
        //first we will iterate through all vertices
        Iterator<HashMap.Entry<Vertices<T>,Edges<T>>> cyclic = graph.entrySet().iterator();
        HashMap.Entry<Vertices<T>,Edges<T>> entry = cyclic.next();

        //for each iteration, We will store our keys and values in variables
        Vertices<T> key = entry.getKey();
        Edges<T> value = entry.getValue();

        //the user want to calculate shortest distance from vertex start
        //start has been given by the user in the form of parameter

        while(start!=key){
        //until we wind the start vertex, we will iterate through the vertices
            key = entry.getKey();
            value = entry.getValue();
            entry = cyclic.next();

        }

        //by the end of the loop, we have found the vertices
        //once we find the vertex, we will mark it as initial

        key.markInitial();
        //we will also call the dijkstrasAlrogithm() that is in the edge class
        value.dijkstrasAlrogithm(start,this);
        //once we are done with dijkstrasAlrogithm() we will mark the vertex as processed
        key.processed();
        //once every vertex has been processed, we will mark it as processed



        cyclic = graph.entrySet().iterator();
        entry = cyclic.next();

        key = entry.getKey();
        //we will iterate through all the vertices
        unprocessed();
        //the method unprocessed() basically arranges all the unprocessed vertices and gives the vertex with least distance
        while(array[0]!=null){
            //the array has all the unprocessed vertices
            key = unprocessed();
            //as we mentioned above, unprocessed will give vertex with least amount of distance
            Edges<T> tempedge3 = graph.get(key);
            //we will also fetch the edge corresponding to the key

            if(tempedge3==null){
                 //if the edge is null, then it means we are left with the vertex with distance 0 and it is not connected with any vertex
                // this would be the time to break from the loop
               break;
            }
            //once we fetch the nonempty edge, we will pass the parent key into it and the graph
            //we will then call the dijkstrasAlrogithm and mark the corresponding parent key as processed
            tempedge3.dijkstrasAlrogithm(key,this);
            key.processed();
        }
        //this statement is not really required, but I just added it incase we have an edge case where entire graph is empty and the loop will not execute
        //in that case, atleast the start edge has to be marked as initial
        start.markInitial();

    }
    public void dismissDij(){
        Iterator<HashMap.Entry<Vertices<T>,Edges<T>>> cyclic = graph.entrySet().iterator();
        HashMap.Entry<Vertices<T>,Edges<T>> entry = cyclic.next();
        //unprocess all verticies and change the distance of every vertex to infinity


        while(cyclic.hasNext()){
            //if there is another vertex, then we will fetch it and set its distance to infinity
            entry = cyclic.next();
            Vertices<T> key = entry.getKey();
            key.setBackDist();
            key.unprocess();

        }
    }

    public static<T> boolean isBarpartite(Graphs<T> g){
        //let us create two sets
        Set<Vertices<T>> b1 = (Set<Vertices<T>>) new HashSet<Vertices<T>>();
        Set<Vertices<T>> b2 = (Set<Vertices<T>>) new HashSet<Vertices<T>>();

        Iterator<HashMap.Entry<Vertices<T>,Edges<T>>> cyclic  = g.getGraph().entrySet().iterator();
        HashMap.Entry<Vertices<T>,Edges<T>> entry = cyclic.next();

        //iterate through graph and add them to set

        while(cyclic.hasNext()){

           Vertices<T> key = entry.getKey();
           Edges<T> ed = entry.getValue();

           //if b1.size is 0 it means we still didn't add anything to our set, so we will start adding
           if(b1.size()==0){
              b1.add(key);
              ed.iterateAdd(b2);
           }
           else if(ed.contains(b1) || ed.contains(b2)){
              Edges<T> tempedge = entry.getValue();
            //if ed edge have elemnts present in either set

              if(ed.contains(b1)){
                //if vertex is present in both sets we will return false
                if(ed.contains(b2)){
                   return false;
                }
                b2.add(key);

              }
              else if(ed.contains(b2)){
                if(ed.contains(b1)){
                   return false;
                }
                b1.add(key);

              }
            }
            //IF it doesnt have any neigbours in either set then add to any of them
            else{
                b1.add(key);
            }
            entry = cyclic.next();

        }
        double temp = 1;
        while(temp==1){
           temp--;
           Vertices<T> key = entry.getKey();
           Edges<T> ed = entry.getValue();


           if(ed.contains(b1) || ed.contains(b2)){
            //if either graphs has the vertex we will check


              if(ed.contains(b1)){
                if(ed.contains(b2)){
                   return false;
                }
                b2.add(key);

              }
              else if(ed.contains(b2)){
                if(ed.contains(b1)){
                   return false;
                }
                b1.add(key);

              }

                //  System.out.println("BadMash 2 " + cycle2.hasNext()+" "+ entry2.getKey().label());

                //    while(cycle2.hasNext()){

                //       System.out.println("BadMash 3");

                //        if(b1.contains(entry2.getKey())){
                //           return false;
                //        }
                //        entry2 = cycle2.next();

                //    }
            }
            //   else if(ed.contains(b2)){
            //      cycle2 = entry.getValue().getHashMap().entrySet().iterator();
            //      while(cycle2.hasNext()){
            //          if(b2.contains(entry2.getKey())){
            //             return false;
            //          }
            //          entry2 = cycle2.next();
            //      }
            //   }
            else{
                b1.add(key);
            }
        }
        return true;
    }



     public String shortPath(Vertices<T> v){
        // the label of this temp vertex doesn't really matter
        Vertices<T> temp = new Vertices<T>("Temp");
        String a = "";
        while(v!=null){
            if(v.dijParent()==null){
                a = v.label() + "-> " + a;
                break;
            }
            //instead of doign a+=, its better to do v.label + -> + a will give us path from starting vertex to target vertex
            a = v.label() + "-> " + a;
            v = v.dijParent();
            System.out.println(temp.label());



        }
        return a;

     }

     //lets make the page algorithm
     public void pageRank(int rounds){
        Set<Vertices<T>> s = (Set<Vertices<T>>)new HashSet<Vertices<T>>();
        Iterator<HashMap.Entry<Vertices<T>, Edges<T>>> cycle = graph.entrySet().iterator();
        HashMap.Entry<Vertices<T>, Edges<T>> entry = cycle.next();
         //the basic idea would be to have a set of vertices infront of us
         //from set we will start at random vertex
        //for the first vertex, in first round, we will just calculate the outgoing flow and ditribute it to rest of the vertices uniformly
        Vertices<T> v = entry.getKey();
        Vertices<T>[] vertarray = (Vertices<T>[])new Vertices<?>[graph.size()];
        int tempind = 0;
        while(cycle.hasNext()){
            v = entry.getKey();
            vertarray[tempind]=v;

            tempind++;
            entry = cycle.next();
        }

        //the iterator is not adding the last element so therefore, let us add the last element as well

        v = entry.getKey();
        vertarray[tempind]=v;
        tempind++;

        double rank = 0;
        double prevrank = 0;

        for(int i = 0; i<vertarray.length;i++){
            //goes through all vetices and assigns them ranks
            rank = 1.0/(graph.size());
            vertarray[i].setRank(rank);
             //all vertices will have prevrank and current rank
             //the more outgoing vertices it has the more worse it performs
            for(int j = 0;j<rounds;j++){
                for(int k=0;k<vertarray.length;k++){
                    vertarray[k].setprevrank(vertarray[k].getRank());

                }
                for(int l=0;l<vertarray.length;l++){
                    vertarray[l].setRank(0);
                    LinkedList<Vertices<T>> inarr = vertarray[l].getIncome();
                    for(Vertices<T> t:inarr){
                        //for every element in the incoming vertex

                        double u = (t.getprevrank()/(graph.get(t).size())-1);

                       vertarray[l].setRank(vertarray[l].getRank()+ u);
                       //System.out.println(vertarray[l].getRank()+" " + vertarray[l].label());
                       //System.out.println(graph.get(t).toString());
                       //System.out.println(" -->"+graph.get(t).size());


                    }
                }
            }
        }
    }



    public static void main(String[] args){
        //this main method is used for testing purposes
        Graphs<String> g = new Graphs<String>();
        Vertices<String> v = g.addVertex("one");
        Vertices<String> v1 = g.addVertex("two");
        Vertices<String> v3 = g.addVertex("three");
        Vertices<String> v4 = g.addVertex("four");
        Vertices<String> v5 = g.addVertex("five");

        g.addEdge(v,v1,10);//the map takes a couple of edges and adds weight in between them
        g.addEdge(v1,v3,100);
        g.addEdge(v3,v4,20);

        g.addEdge(v3,v5,30);
        g.addEdge(v5,v,30);


        g.toString();

        //let us test the isCyclic method

        g.isConnected();

        System.out.println(g.isConnected());
        //the above statement prdoubles true

        //let us make our graph non-cyclic and see if it returns true

        System.out.println(g.isConnected());

        System.out.println(g.isCyclic());

        //so far I tested the above graph by adding and dropping few edges
        // so far isCyclic is working  fine, I want to make some complicated graphs and test if isCyclic works for them or not

        Graphs<String> g1 = new Graphs<String>();

        Vertices<String> a1 = g1.addVertex("one");
        Vertices<String> a2 = g1.addVertex("two");
        Vertices<String> a3 = g1.addVertex("thre");
        Vertices<String> a4 = g1.addVertex("four");
        Vertices<String> a5 = g1.addVertex("five");
        Vertices<String> a6 = g1.addVertex("six");
        Vertices<String> a7 = g1.addVertex("seven");
        Vertices<String> a8 = g1.addVertex("eight");
        Vertices<String> a9 = g1.addVertex("nine");
        Vertices<String> a10 = g1.addVertex("ten");

        /*
          1---2---3---4--5
              |       |
              6       10
              |       |
              7---8---9
         */

        g1.addEdge(a1,a2,1);
        g1.addEdge(a2,a3,1);
        g1.addEdge(a2,a6,7);
        g1.addEdge(a3,a4,2);
        g1.addEdge(a4,a5,124);
        g1.addEdge(a4,a10,84);
        g1.addEdge(a6,a7,19);
        g1.addEdge(a7,a8,2);
        g1.addEdge(a8,a9,8);
        g1.addEdge(a9,a10,41);


        System.out.println(g1.isCyclic());

        System.out.println(a6.distance());
        //g1.dijkstrasAlrogithm(a2);

        //System.out.println(a2.isProcessed());
        //System.out.println(g1.unprocessed().label());
        //System.out.println(a9.distance());
        //g1.copy();
        //System.out.println(a8.distance());
        g1.dijkstrasAlrogithm(a3);
        System.out.println(a8.distance());
        g1.dismissDij();
        System.out.println(a3.distance());
        System.out.println(a5.distance());
        System.out.println(a4.distance());

        //our dismiss dijkstra is working properly

        g.dijkstrasAlrogithm(v4);
        System.out.println(v3.distance());
        System.out.println(v.distance());
        System.out.println(v1.distance());

        g.dismissDij();

        System.out.println(v3.distance() + " " +v3.isProcessed() );
        System.out.println(v.distance()+ " " +v.isProcessed());
        System.out.println(v1.distance()+ " " +v1.isProcessed() );

        //our Dijkstra's algorithm is working fin
        //we are also doing good on unprocessing all verticies and marking their distance maximum

        Graphs<String> g2 = new Graphs<String>();

        Vertices<String> q1 = g2.addVertex("one");
        Vertices<String> q2 = g2.addVertex("two");
        Vertices<String> q3 = g2.addVertex("three");
        Vertices<String> q4 = g2.addVertex("four");
        Vertices<String> q5 = g2.addVertex("five");
        Vertices<String> q6 = g2.addVertex("six");
        Vertices<String> q7 = g2.addVertex("seven");
        Vertices<String> q8 = g2.addVertex("eight");
        Vertices<String> q9 = g2.addVertex("nine");

        g2.addEdge(q1,q2,10);
        g2.addEdge(q1,q3,10);
        g2.addEdge(q1,q4,10);
        g2.addEdge(q1,q5,10);
        g2.addEdge(q4,q5,10);
        g2.addEdge(q7,q6,10);
        g2.addEdge(q7,q8,10);
        g2.addEdge(q8,q9,10);

        //System.out.println(g2.isBarpartite());
        //g2.addEdge(q3,q5,10);
        System.out.println(isBarpartite(g2));

        //our isBarpartite is working fine

        System.out.println(g1.isCyclic());

        //let us make a completely directed graph and check our algorithm for that

        Graphs<String> g3 = new Graphs<String>();
        Vertices<String> r1 = g3.addVertex("one");
        Vertices<String> r2 = g3.addVertex("two");
        Vertices<String> r3 = g3.addVertex("three");
        Vertices<String> r4 = g3.addVertex("four");
        Vertices<String> r5 = g3.addVertex("five");
        Vertices<String> r6 = g3.addVertex("six");
        Vertices<String> r7 = g3.addVertex("seven");

        g3.addDirectEdge(r1,r2,30);
        g3.addDirectEdge(r2,r3,30);
        //g3.addDirectEdge(r3,r2,30);
        g3.addDirectEdge(r4,r5,30);
        g3.addDirectEdge(r5,r6,30);

        //g3.addDirectEdge(r6,r7,30);

        System.out.println(g3.isConnected());
        System.out.println(g3.isCyclic());
        System.out.println(g2.isCyclic());
        System.out.println(g1.isCyclic());
        System.out.println(g.isCyclic());

         /*
          1---2---3---4--5
              |       |
              6       10
              |       |
              7---8---9
         */

         g1.dijkstrasAlrogithm(a3);
         System.out.println(a3.dijParent());
         System.out.println(g1.shortPath(a5));



         //let's make a directed graph and make a pagerank
         Graphs<String> g4 = new Graphs<String>();
         Vertices<String> c1 = g4.addVertex("one");
         Vertices<String> c2 = g4.addVertex("two");
         Vertices<String> c3 = g4.addVertex("three");
         Vertices<String> c4 = g4.addVertex("four");
         Vertices<String> c5 = g4.addVertex("five");
         //Vertices<String> c6 = g4.addVertex("six");
         //Vertices<String> c7 = g4.addVertex("seven");
         /*
                    c3<-------c2
                  / / \     /   \
                 / /   \   /     \
                c4      c5       c1

          */

         g4.addDirectEdge(c1,c2,0.2);
         g4.addDirectEdge(c2,c3,0.2);
         g4.addDirectEdge(c3,c4,0.2);
         g4.addDirectEdge(c3,c5,0.1);
         g4.addDirectEdge(c4,c3,0.1);
         g4.addDirectEdge(c5,c2,0.2);
         //g4.addDirectEdge(c5,c1,0.2);


 //--------------------------------
         g4.pageRank(8);

        // Vertices<String>[] testar = c3.getIncome();
         //System.out.println(testar[0].label());

         System.out.println(c4.getRank());
         System.out.println(c3.getprevrank());
         System.out.println(c5.getRank());
         System.out.println(c2.getprevrank());
         System.out.println(c1.getprevrank());
         System.out.println(c1.getRank()+" one");
         System.out.println(c2.getRank());
         System.out.println(c3.getRank());
         System.out.println(c4.getRank());
         System.out.println(c5.getRank());
         System.out.println(c1.getRank()+c2.getRank()+c3.getRank()+c4.getRank()+c5.getRank());
         System.out.println(c1.getprevrank()+c2.getprevrank()+c3.getprevrank()+c4.getprevrank()+c5.getprevrank());


    }
}
