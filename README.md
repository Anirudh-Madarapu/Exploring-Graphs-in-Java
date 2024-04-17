# For isConnected()

I am going through each edge of the graph and seeing if there is atleast
one edge that is empty. Edge is a class that consits of vertices and weights. Each edge class is paired with a vertex within a hashmap of graph. If any edge is empty, that means that its corresponding vertex is not connected with any other vertex. If any edge is empty then it means the vertices corresponding to that are not paired with any other vertices. This violates the necessary condition for the isConnected(), hence our method will return false. If we didn't find any empty vertex, it means every vertex is connected with atleast one other vertex, hence we return true.

    Time complexity: O(n)
     As the method goes through all edges and finds edges.isEmpty()

    Space Complexity: O(n)
     As we are using an iterator to go through all the elements of the class


# For isCyclic()

For isCyclic, I have created a Hash Set which stores all adjacent vertices of a given vertex. I also have an iterator which goes through all vertices in graph and adds edges(vertices connected to parent vertex) to our set. While adding to set, we will check if the vertex already exists in the set or not. If it does, then we will return true. If we have gone through all vertices and added all their adjacent vertices to the set, but still didn't find any overlaps, then we will return false.

    Time complexity: O(n)

     As we going through all the vertices and adding them to the vertex

    Space Complexity: O(n)

     As we creating a new set and also using a new iterator. Which sums upto O(2n) time complexity.

# For Question 2

For question 2, I have created a seperate class called differentGraphs. This is in my src folder. The main method in Graphs class also have few types of graphs.
In Graphs' main method I am checking other supporter methods such as unprocessed(), copy(),and a few print statements to debug etc that are required to support my Dijkstra’s algorithm, isBarpartite, and isConnected question. The differentGraphs class is especially created for second question to clearly describe my graphs and their variations. This class has several types of graphs with their diagrams. This class has directed graphs and undirected graphs. I also ensured that all methods are working correctly for all the graphs that are described in the class. From line 6 to line 198, there are undirected graphs. From 199 till end line, there are directed graphs. For each graph, I have  clearly specified print statements with comments around the code lines. There are also several types of graphs coded in main method of Graph class. Therefore, I also request to review those graphs and graphs in differentGraphs class for grading purposes.




# For Question 3

For Dijkstra’s algorithm, I am using a few variables and methods.
Each vertex will have variables called dijParent, processed, and distance. Each vertex's distance should be marked as infinity. However, Java only 2147483647(2^31 - 1). This is the bydefault distance of every vertex. I am also using methods called dijkstrasAlrogithm(Vertex V),unprocessed(), and copy(). I am also using a seperate class called dijkstrasProcessor. The whole point of having these classes and methods is to find shortest distance. First, the user calls dijkstrasAlrogithm(Vertex V) the algorithm finds that particular vertex and marks that vertex as Initial. Any vertex that is marked as initial will have its distance 0. Later for every vertex, we call a method called dijkstrasAlrogithm() of the edge class corresponding to that iteration vertex. This ensures that we are calling dijkstrasAlrogithm() for all neighbours. The dijkstrasAlrogithm() of the edge class takes a key as paramenter. It will see if the current distance of the vertex is greater than distance from start vertex plus the weight between start vertex and current vertex. If the current distance is more that  than distance from start vertex plus the weight between start vertex and current vertex, then the distance will be replaced with the sum of weight between start vertex and current vertex and start vertex's weight. After we do this for the neighbors of the start vertex, we will mark the start vertex as processed. We will pick the vertex that has not been processed and has minimum distance among all the vertices. This is where copy() method and dijkstrasProcessor class comes into play. The copy method creates an array for dijkstrasProcessor elements and iterates through all vertices of the graph. It keep on adds unprocessed vertices to the array until it reaches the end. Once copy method is done, we will call unprocessed() method. This method bascially picks the unprocessed vertex with least distance. Once we pick unprocessed vertex with least distance, we will call the dijkstrasAlrogithm() for its neighbours. Once it is done, we will again pick unprocessed vertex with least distance, and call the dijkstrasAlrogithm() for its neighbours. This way until all vertices are marked processed, we will continue the process.

Time complexity: O(n^2)

The dijkstrasAlrogithm() will call unprocessed() and dijkstrasAlrogithm() of neighboursfor each unprocessed vertex. The unprocessed() method calls copy(). Copy() is O(n), later, unprocessed() finds vertex with least distance which is also O(n). These both are on same line so its still O(n). The while loop also calls dijkstrasAlrogithm() and unprocessed() each are O(n). The loop is O(n) and its inside methods dijkstrasAlrogithm() and unprocessed() each are O(n). This creates O(n^2) time.

Space complexity: O(n)

We are creating an additonal array of dijkstrasProcessor class objects.
We are creating n objects of these classes.
Therefore, it is O(n)



About dijkstrasProcessor class:

The dijkstrasProcessor is basically a class that consits of all information of each vertex as one object. This helps us in storing all the vertices in 1 array rather than using mutple arrays for various types such as arrays for booleans, arrays for strings, etc. Each dijkstrasProcessor contains a vertex's information such as vertex istelf, boolean processed, and distance of the vertex. It also contains getters and setters of these variables.


Find shortest path():

I have also created a method called shortest path() which takes a vertex and gives a shortest path to that vertex from the vertex on which dijkstrasAlrogithm() has been called. This is done by keeping track of parent vertex. Everytime we replace current distance of vertex with a least one, we will replace the dijparent variable to the parent vertex that has been passed as a parameter. This method has to be called only after calling
dijkstrasAlrogithm() otherwise it won't give shortest parth



About dismissDij():

The dijkstrasAlrogithm() will mark all find shortest distances of every vertex and marks them processes. However, If we call dijkstrasAlrogithm() again for a
different vertex, we might get errors as all vertices have been already processed. Therefore, dismissDij() will mark all vertices unprocessed and changes all distances to infinity.

Assumtions made:

If we have an edge from A -> B but not from B -> A, and if there is not other path to A from B, then the distance from B to A is -infinity. This is the logic of considering displacement as vector.

# Other shortest path algorithms

Bellman–Ford algorithm

The algorithm over estimates the distance between two nodes and then tries to find the shortest path. By doing this repeatedly we are guaranteed to find shortest path. The bellman ford algorithm can handle negative weights and takes more time than dijkstras Alrogithm. Dijkstras Alrogithm picks the vertex with least distance and unprocessed. However,Bellman–Ford algorithm doesn't pick in this manner and calls Alrogithm for all vetices for V - 1 times instead of just calling for neighbours.

Floyd–Warshall algorithm

It finds shortest path between all pairs of the vertices. This algorithm takes more time than the dijkstras Alrogithm and consumes O(n^3) time. This algorithm takes can be used for undirected or directed edges but not for both. It finds all possible pairs between two vertices and returns the least distance pair. The space complexity of this problem is O(n^2).



# For question 4

For this question, I am creating two sets. We will go through our vertices and then add them to either of the sets. The logic behind this question is that if a vertex is connected to vertices in both sets, then we return false. After iteration, if we failed to return false, then it means that none of the vertices is connected to vertices in both sets. To break it down, first we can add our first vertex randomly to any set. In this case, I am adding it first set. Next, let's say we come across a vertex whose parent is first vertex. Then this vertex will be added to second set as this vertex's neigbours lie in other set. Before adding to any set, we will check if a neighbour vertex is connected to vertices in both sets, to return false. After completing our iteration, if we couldn't return false, then it means that we were able to separate vertices into two disjoint vertices.

Time complexity: O(n)

We are simply going through entire vertices and adding them to sets and also checking to see if there are any cross connections.

Space complexity: O(n)

  We are taking the help of two sets. Therefore, its extra space of 2n


# For the pagerank algorithm



Time complexity O(|V|+|E|)

