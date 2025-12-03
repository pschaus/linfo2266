.. _mdd:


*************************************************************************************************
DP with A* based search
*************************************************************************************************

Theory
=======================================
* `Slides <../_static/slides/09-astar.pdf>`_


Project : A*
===================================================

In the 6th project, you will optimize TSP using A* like algorithms.
You are asked to implement: a dynamic programming model for TSP and two A* variants:
the Weighted A* and the Anytime Weighted A*.

Gradescope
---------------

On `Gradescope <https://www.gradescope.com/>`_, find the written assignment on A*.
You first need to complete the implementation part to be able to answer to the exercises.

Modeling the TSP Problem
-----------------------------------------

To get started with you must complete the implementation of the TSP model in `astar.problems` package. 
You can take example on the Knapsack model provided in the same package and from your DP model in the `dynamicprogramming` package.
You also need to implement the heuristic evaluation function `h` in the `TSP` class.
For this you need to take inspiration from the `CheapestIncidentLowerBound` you implemented in the Branch and Bound project.

Solver implementation
---------------------------

Once you are done with the model, you must implement the two A* variants in the `astar.solver` package.
For both solvers you should take inspiration from the provided A* implementation in the `Astar` class.
Look at the pseudo-code given in the slides to guide your implementation.
Be careful with the Anytime Weighted A* : a time limit is imposed on the solver, so make sure that it is able to stop when this limit is reached.



