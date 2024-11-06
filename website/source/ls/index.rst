.. _ls:


*************************************************************************************************
Local Search
*************************************************************************************************

Theory
=======================================


* `Slides <../_static/slides/06-local-search.pdf>`_


Project : TSP (Work in progress)
=======================================
Your goal is to implement methods used in a local search to solve the Traveler Salesman Problem. In the base code given, a solution is represented by a list of integers representing cities. For instance the list ``[2,0,1]`` represents the tour going like this : ``2 -> 0 -> 1 -> 2``. It is important to differentiate the operations applied index-wise and city-wise. For instance, the ``twoOpt`` method in ``Candidate`` class is applied index-wise, while the distance method in ``TSPInstance`` class is applied city-wise.

Implementation
---------------

All the files related to this project are in the package ``localsearch``. You have to modify six classes:


#. ``Candidate.java`` 
#. ``BestSelection.java`` 
#. ``KOptSelection.java`` 
#. ``BestWithTabuSelection.java`` 
#. ``BeamSearchInitialization.java`` 
#. ``PilotInitialization.java`` 


Two Opt
~~~~~~~~~~~~~~

The first method to implement is the ``twoOpt`` method in ``Candidate.java``. This method takes two indices (``index1`` and ``index2``) and reverse the cities in the tour from ``index1 + 1`` and ``index2``. For instance, if the tour is ``[2,0,1,3,4]`` and ``index1 = 1`` and ``index2 = 3``, the new tour will be ``[2,0,3,1,4]``. This way, a twoOpt between index1 and index2 places the city at ``index2`` as the successor of the city at ``index1``. Note : this methods also updates the total distance (cost) of the tour.

Best Selection
~~~~~~~~~~~~~~

The most important part of a local search is the neighbor selection. The class ``FirstSelection.java`` contains the method ``getNeighbor`` that returns the first improving neighbor found among all ``twoOpt`` movements possible. You are asked to implement the same method in the class ``BestSelection.java``. This method should return the best improving neighbor found among all ``twoOpt`` movements possible. The best neighbor is the one that minimizes the total distance of the tour. If no improving neighbor is found, the method should return the ``Candidate`` given in argument.

KOpt Selection
~~~~~~~~~~~~~~

In the ``KOptSelection``, a twoOpt is applied repeatly. During ``k`` iterations, the best neighbor is selected and the candidate is saved. The method ``getNeighbor`` should return the best candidate found after k iterations. Note : the best candidate is the one that minimizes the total distance of the tour not the last obtained.

Tabu
~~~~~~~~

Tabu is a common metaheuristic used in local search. The idea is to avoid returning to a previous state by forbidding a given move for a given number of iterations. In the ``BestWithTabuSelection.java`` class, you are asked to implement the ``getNeighbor`` method that returns the best improving neighbor found among all ``twoOpt`` movements possible that is not obtained through a tabu move. Once a movement is applied it should be added to the tabu list. Note : the movement tabu is a movement that would reset the solution to it's previous state. In our case, if we set the city2 to be the successor of city1, the tabu movement is to set the former successor of city1 to be it's new successor.

BeamSearch Initialization
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Another important component of the local search is the initialization of the solution. In the ``BeamSearchInitialization.java`` class, you are asked to implement the ``getInitialSolution`` method that returns an initial candidate. To obtain the initial candiate, you need to construct a tour by starting at city 0 and selecting the best city to add to the partial solution. At each iteration you need to only keep k best partial solution. 

Pilot Initialization
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

A common initialization method is the greedy method. You start at a city then iteratively add the closest city to the current tour. The Pilot initialization is a method that builds the solution by adding one city at a time but by considering the cost of the partial solution to be the cost of the solution if it was completed using a greedy method. This creates a direct relationship between the Beamsearch initialization and the Pilot initialization as the Pilot initialization is a specialization of the Beamsearch initialization with ``k = 1``. Implement the ``PilotInitialization.java`` class.

Gradescope
---------------

On `Gradescope <https://www.gradescope.com/>`_, find the written assignment for the project.
Part of your assignment requires to report experimental results under the form of a graph.


..
    Traveling Salesman Problem: 2-Opt
    """""""""""""""""""""""""""""""""""""""

    You are given a suboptimal solution: [1,2,3,4,5] (list of the visited nodes).
    If the optimal solution is [4,1,3,2,5], what is the minimal sequence of 2-Opt moves to reach the solution?

    Vehicle Routing Problem: Clark-Wright Savings Algorithm
    """""""""""""""""""""""""""""""""""""""

    Given the following demands and distances between the depot and the customers, find an initial solution to the Vehicle Routing Problem with a **maximum capacity of 50** using the `Clark-Wright Savings Algorithm <http://web.mit.edu/urban_or_book/www/book/chapter6/6.4.12.html>`_.

    ======== == == == == ==
    Customer 1  2  3  4  5
    Demand   15 10 15 20 30
    ======== == == == == ==

    ========= ===== == == == == ==
    Distances depot 1  2  3  4  5
    depot           15 10 20 10 25
    1                  5  15 15 5
    2                     25 10 10
    3                        5  5
    4                           20
    5
    ========= ===== == == == == ==

