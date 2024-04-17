import java.util.*;

public class differentGraphs {

    public static void main(String[] args){
       //we will test various types of graph
       //this class specifically for directed graphs
       //there are edge cases
       //for each graph i have tested all of our methods
       //i have used directed and undirected graphs for this problem
        Graphs<String> t1 = new Graphs<String>();

        Vertices<String> q1 = t1.addVertex("A");
        Vertices<String> q2 = t1.addVertex("B");
        Vertices<String> q3 = t1.addVertex("C");
        Vertices<String> q4 = t1.addVertex("D");

        /*
                q1
              /   \
             /     \
            q3----- q2
            \       /
             \     /
              \   /
               q4
        */

        t1.addEdge(q1,q2,30);
        t1.addEdge(q1,q3,23.4);
        t1.addEdge(q3,q2,30);
        t1.addEdge(q4,q3,30);
        t1.addEdge(q4,q2,30);

        System.out.println("is t1 barpartite? " + t1.isBarpartite(t1));
        System.out.println("is t1 connected? " + t1.isConnected());

        //let us see if our graph can work with dijkstrasAlrogithm

        t1.dijkstrasAlrogithm(q4);
        System.out.println(t1.shortPath(q1));
        System.out.println(q1.distance());
        t1.dismissDij();

        //dismissDij is very important method
        //it marks all processed nodes as unprocessed and changes all distances back to infinity

        //it looks like our algorithm is working fine

        //let us also call our toString method

        t1.toString();



        Graphs<String> t2 = new Graphs<String>();

        Vertices<String> w1 = t2.addVertex("A");
        Vertices<String> w2 = t2.addVertex("B");
        Vertices<String> w3 = t2.addVertex("C");
        Vertices<String> w4 = t2.addVertex("D");

        /*
           w1----w2
                /
               /
              /
            w3----w4

         */


        t2.addEdge(w1,w2,20);
        t2.addEdge(w3,w2,127.9);
        t2.addEdge(w3,w4,27.9);

        System.out.println("is t2 barpartite? " + t2.isBarpartite(t2));
        System.out.println("is t2 connected? " + t2.isConnected());

        t2.dijkstrasAlrogithm(w4);
        System.out.println(t2.shortPath(w1));
        System.out.println(w3.distance());
        t2.dismissDij();

         //dismissDij is very important method as it unmarks all vertices unprocessed

        //it looks like our algorithm is working fine

        //let us also call our toString method

        t2.toString();

        //lets try out some Integer type graphs

        Graphs<Integer> t3 = new Graphs<Integer>();

        Vertices<Integer> e1 = t3.addVertex("A");
        Vertices<Integer> e2 = t3.addVertex("B");
        Vertices<Integer> e3 = t3.addVertex("C");
        Vertices<Integer> e4 = t3.addVertex("D");

        //edge cases

        /*
               e1
                |
                |
                |
               e2        e4         //this is a disconnected graph with
               ||
               ||
               ||
               e3
        */

        t3.addEdge(e1,e2,0.25);
        t3.addEdge(e2,e3,0.35);
        t3.addEdge(e3,e2,0.90);


        System.out.println("is t3 barpartite? " + t3.isBarpartite(t3));
        System.out.println("is t3 connected? " + t3.isConnected());

        t3.dijkstrasAlrogithm(e1);
        System.out.println(t3.shortPath(e3));
        System.out.println(e2.distance());
        t3.dismissDij();

        //lets test the distance for e4
        //if e4 has infinite distance then our algorithm is working fine

        System.out.println(e4.distance());
        //yesss, it out puts 2147483647 which is what we want

        System.out.println(t3.shortPath(e4));
        //the short path is also working fine



        //lets try another edge case
        Graphs<String> t4 = new Graphs<String>();

        Vertices<String> r1 = t4.addVertex("A");
        Vertices<String> r2 = t4.addVertex("B");
        Vertices<String> r3 = t4.addVertex("C");
        Vertices<String> r4 = t4.addVertex("D");
        Vertices<String> r5 = t4.addVertex("E");

        /*

            r1------r2
            | \    / |
            |  \  /  |
            |   r3   |
            |  /  \  |
            | /    \ |
            r4------r5


        */

        t4.addEdge(r1,r2,0.23);
        t4.addEdge(r1,r3,23.4);
        t4.addEdge(r2,r3,879);
        t4.addEdge(r1,r4,98);
        t4.addEdge(r2,r5,300);
        t4.addEdge(r4,r5,0.9830);
        t4.addEdge(r3,r4,23.4);
        t4.addEdge(r3,r5,1223.4);
        //here r1 to r3 is cheaper than r1 to r4
        //however r3 to r5 is much expensive than r1 to r4 to r5
        //this can be a good test for shortest path algorithm

        System.out.println("is t4 barpartite? " + t4.isBarpartite(t4));
        System.out.println("is t4 connected? " + t4.isConnected());

        t4.dijkstrasAlrogithm(r1);

        //let us try our dijkstrasAlrogithm and shortest path
        //this is the graph type where our methods can go wrong

        System.out.println(t4.shortPath(r5));
        //here r1 to r3 is cheaper than r1 to r4
        //however r3 to r5 is much expensive than r1 to r4 to r5
        System.out.println(r5.distance());
        //r5.distance() is out putting shortest distance
        //we have solid evidenence to say that our dijkstrasAlrogithm is working fine

        System.out.println(t4.shortPath(r3));
        System.out.println(r3.distance());

        System.out.println(t4.shortPath(r4));
        System.out.println(r4.distance());

        //lets undo our dijkstrasAlrogithm
        System.out.println(r4.distance());
        //our dismissDij is also working fine


        t4.dismissDij();



//------------Let us try a few types of directed graphs--------------------------



        Graphs<String> t5 = new Graphs<String>();

        Vertices<String> y1 = t5.addVertex("A");
        Vertices<String> y2 = t5.addVertex("B");


        /*

           y1-->y2


        */

        t5.addDirectEdge(y1,y2,0.23);

        System.out.println("is t5 barpartite? " + t5.isBarpartite(t5));
        //this directed edge might not mark y2's edge class as not empty
        System.out.println("is t5 connected? " + t5.isConnected());


        t5.dijkstrasAlrogithm(y1);

        System.out.println(t5.shortPath(y2));
        System.out.println(y2.distance());

        t5.dismissDij();

        //all our methods are working fine this graph


//------------Let us try a few other types of directed graphs---------------------

        Graphs<Integer> t6 = new Graphs<Integer>();

        Vertices<Integer> u1 = t6.addVertex("A");
        Vertices<Integer> u2 = t6.addVertex("B");
        Vertices<Integer> u3 = t6.addVertex("C");
        Vertices<Integer> u4 = t6.addVertex("D");
        Vertices<Integer> u5 = t6.addVertex("E");
        Vertices<Integer> u6 = t6.addVertex("F");


        /*

           u1---->u2
                ↗
               /
              /
             /
           u3--->u4
                ↗
               /
              /
             /
           u5 ---->u6




        */

        t6.addDirectEdge(u1,u2,0.122);
        t6.addDirectEdge(u3,u2,10.984);
        t6.addDirectEdge(u3,u4,35.226);
        t6.addDirectEdge(u5,u4,90.333);
        t6.addDirectEdge(u5,u6,0.9275);

        System.out.println("is t6 barpartite? " + t6.isBarpartite(t5));
        System.out.println("is t6 connected? " + t6.isConnected());

        //excellent our methods are working fine for both

        t6.dijkstrasAlrogithm(u3);
        System.out.println(t6.shortPath(u2));

        //these two vertices have to output small numbers as they are direct neighbours
        System.out.println(u2.distance());
        System.out.println(u4.distance());

        //u1 and u6 have to be infinity
        System.out.println(u1.distance());
        System.out.println(u6.distance());

        t6.dismissDij();

        //all our methods are working fine this graph

//----------------------------let us check a graph with directed and undirected edges---------------------

        Graphs<String> t7 = new Graphs<String>();

        Vertices<String> o1 = t7.addVertex("A");
        Vertices<String> o2 = t7.addVertex("B");
        Vertices<String> o3 = t7.addVertex("C");
        Vertices<String> o4 = t7.addVertex("D");
        Vertices<String> o5 = t7.addVertex("E");
        Vertices<String> o6 = t7.addVertex("F");


        /*
         //this is a directed and undirected graph

           o1----->o2
           |        |
           |        |
           |        |
           o3------>o4
            \       /
             \     /
                o5
                |
                |
                |
                o6

         */

        //

        t7.addDirectEdge(o1,o2,2.122);
        t7.addEdge(o1,o3,2.122);
        t7.addEdge(o2,o4,29.122);
        t7.addDirectEdge(o3,o4,219.122);
        t7.addEdge(o3,o5,29.122);
        t7.addEdge(o4,o5,29.122);
        t7.addEdge(o5,o6,29.122);

        t7.dijkstrasAlrogithm(o5);
        System.out.println(t7.shortPath(o2));

        //these two vertices have to output small numbers as they are direct neighbours
        System.out.println(o2.distance());
        System.out.println(o1.distance());


        t7.dismissDij();





    }

}
