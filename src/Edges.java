import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Edges<T>{
    //for this class, we will have a hashMap
    private HashMap<Vertices<T>, Double> edge;
    //each vertex will have an edge as a value inside the hash map of the graph
    //within the edge, we have a set of vertices and Doubles

    private boolean empty;

    public Edges(){
        this.empty = false; // bydefault we want the boolean value to be false
        //we will change this variable to true when we add atleast one key to our hash map
        this.edge = new HashMap<Vertices<T>, Double>();
    }

    public HashMap<Vertices<T>, Double> getHashMap(){// as out hash map is private we want to use getter method
        //the getter method, when called from Graph method, will return the private variable hash map
        return this.edge;
    }

    public void put(Vertices<T> v, double weight){
        this.empty = true; // as we are adding a vertex to our hash map we will change empty to true
        //this map is no longer enpty
        this.edge.put(v,weight);

    }

    public String toString(){
        //we will iterate through the hashmap and every vertex's label to it
        Iterator<HashMap.Entry<Vertices<T>, Double>> i = edge.entrySet().iterator();
        String s = "";


        while(i.hasNext()){
            //the iterator will stop when there is no more entries found
            HashMap.Entry<Vertices<T>, Double> entry = i.next();
            Vertices<T> v = entry.getKey();
            Double weight = entry.getValue();
            //for every vertex we want the label to come first followed by the weight
            s += v.label() + " " + weight + " ";
        }
        // return  has to be a tring
        return s;

    }

    public boolean isEmpty(){
        //this method tells if the map is empty
        //intutively, this method tells us if the parent vertex has any neighbours or not
        return this.empty;
    }



    public boolean containsKey(Vertices<T> v){
        //this method tells us if there is a neighbour v for the parent vertex in the graph
          return edge.containsKey(v);
    }

    public boolean isCyclic(Set<Vertices<T>> v){
        //if at least one edge is empty that means the
        Iterator<HashMap.Entry<Vertices<T>, Double>> cycle = edge.entrySet().iterator();
        double tempCount  = 0;

        double size = edge.size();

        //the basic logic behind this problem is that we will add every vertex to a set
        //if we find a vertex already in the set, that means there is a way to visit the vertex again


        while(cycle.hasNext()){
            //for each parent vertex in the graph this method will be called
            //every time we call this method, a vertex's neigbhours will be added
            HashMap.Entry<Vertices<T>, Double> entry = cycle.next();

            size--;
            tempCount++;
            //we will keep decremenitng the size
            // and increment the tempCount

            Vertices<T> tempv = entry.getKey();

            if(v.contains(tempv)){
                //if there is tere is atleast one vertex that is in both edge and set, then we return true
                return true;
            }
            v.add(tempv);
//-----------------------------------------------------------------------------
//ignore this part
            // if(v.parent()==null){
            //     tempv.setParent(v);
            //     System.out.println(tempv.label()+" magic9");
            //     return tempv;
            // }


            // if(tempv.isVisited()){

            //      if(v.parent()==tempv){
            //         System.out.println("--Test to see if there is a --"+tempv.label()+v.label()+v.parent().label());

            //         tempv.setParent(v);
            //         System.out.println("--Test to see if there is a null--"+tempv.label()+v.label()+v.parent().label());
            //         continue;
            //     }
            //     else if(v.parent()!=tempv){
            //         tempv.setParent(v);
            //         return tempv;
            //     }

            // }
            // else if(!tempv.isVisited()){
            //     System.out.println("Jenda"+tempv.label());
            //     System.out.println(v.label());
            //     tempv.setParent(v);
            //     return tempv;

            // }
//-----------------------------------------------------------------------------
        }
        return false;


    }
    public String show(){
        //this is our toString for edge
        String a = "";
        Iterator<HashMap.Entry<Vertices<T>, Double>> cycle = edge.entrySet().iterator();
        double counter = 0;
        //iterate through all vertices and add them to string
        while(cycle.hasNext()){
            counter++;
            //we will increment the counter
            //the counter is just used for testing purpose
            HashMap.Entry<Vertices<T>, Double> entry = cycle.next();
            //a is the string to which we keep adding all the labels of the key
            a += entry.getKey().label();

        }
        return a;

    }
    public void dijkstrasAlrogithm(Vertices<T> start, Graphs<T> g){
        Iterator<HashMap.Entry<Vertices<T>, Double>> cycle = edge.entrySet().iterator();
        while(cycle.hasNext()){
            HashMap.Entry<Vertices<T>, Double> entry = cycle.next();
            Vertices<T> key = entry.getKey();
            //if we find that current distance is more than parents diatnce plus weight between parent and current vertex
            double mindist = start.distance();
            if(key.distance()>(edge.get(key)+mindist)){
                //the setDijParent assigns start vertex as the parent vertex of every edge in this edge class
                key.setDijParent(start);
                key.setDistance(edge.get(key)+mindist);
            }
        }
      /*   Iterator<HashMap.Entry<Vertices<T>, Double>> cycle2 = edge.entrySet().iterator();
        while(cycle2.hasNext()){
            HashMap.Entry<Vertices<T>, Double> entry = cycle2.next();
            Vertices<T> keys = entry.getKey();
            System.out.println(keys.label());

        }  */

    }

    public void iterateAdd(Set<Vertices<T>> s){
        //itertaively adds vertices to set
        Iterator<HashMap.Entry<Vertices<T>, Double>> cycle = edge.entrySet().iterator();
        while(cycle.hasNext()){
             HashMap.Entry<Vertices<T>, Double> entry = cycle.next();
             //s is the set passed as a parameter
             s.add(entry.getKey());
        }
    }

    public boolean contains(Set<Vertices<T>> s){
        Iterator<HashMap.Entry<Vertices<T>, Double>> cycle = edge.entrySet().iterator();
        //this method is used by isBarpartite method in graph
        //this method will take the set and sees if there is a cycle within it
        while(cycle.hasNext()){
             HashMap.Entry<Vertices<T>, Double> entry = cycle.next();
             //for every entry it checks if it's present in the set
             if(s.contains(entry.getKey())){
                return true;
             }
             //System.out.println(entry.getKey().label() + " hey ");
        }
        //if we didn't find any vertex matching that is in the set, then we return false
        return false;
    }

    public void pageRank(){
        //this page rank does nothing it is just used for testing purpose
        //this will not directly impact the actual page rank problem
        Iterator<HashMap.Entry<Vertices<T>,Double>> cycle = edge.entrySet().iterator();
        while(cycle.hasNext()){
            HashMap.Entry<Vertices<T>,Double> entry = cycle.next();
        }
    }

    public double size(){
        //returns the size of the hashmap
        return this.edge.size();
    }
}