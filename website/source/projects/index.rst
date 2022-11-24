.. _projects:


*************************************************************************************************
Projects
*************************************************************************************************

Projects will usually consist of two parts: theoretical questions or exercises to be solved by hand and a programming assignment to put everything you have learned in practice.
To that end, the course will use two different platforms: Gradescope and Inginious.
Below are some instructions on how to set up and use these two tools, followed by the project descriptions.

Gradescope
===================================================

#. Go to `Gradescope <https://www.gradescope.com/>`_ and connect with your UCLouvain account.
#. Add the LINFO2266 course by clicking on **Enroll in Course** and entering the code given in class.
#. You will find the theoretical part of the projects there, which consist of a PDF document with a few questions.
#. Answer these questions either by printing the document or filling it on your computer. *If you follow the instructions for the Inginious part, you will find the .tex files in the* ``tex/`` *folder of the repository so you can fill them directly.*
#. Do not forget to submit your answers on Gradescope when you are done.

Inginious
===================================================

#. Go to `Inginious <https://inginious.info.ucl.ac.be/>`_ and connect with your UCLouvain account.
#. Find LINFO2266 in the course list (**[LINGI 2266] Advanced Algorithms for Optimization**) and add it by clicking on **Enroll in the course**.
#. Go to the task called **Create your repository** and enter your Github username. This will create a Github repository with the projects of the course that you can clone on your computer.
#. This repository contains a Maven project that you can easily open with  `IntelliJ <https://www.jetbrains.com/idea/>`_ (or `Visual Studio Code <https://code.visualstudio.com/>`_ with the recommended Java extensions).
#. For each project, a package is already created in the ``src/main/java/`` folder with some Java classes.
#. Follow the instructions of each project on how to fill some of those classes.
#. Once you think you have something working, you can run the tests provided in the corresponding ``src/test/java/`` folder.
#. Finally, submit your code on Inginious on the task dedicated to each project.

Project 1: Dynamic Programming
===================================================

If not done yet, follow the instructions given above to enroll to the Gradescope and Inginious courses, as well as retrieving the source code of the projects.

The first project is about Dynamic Programming (DP) and the `Traveling Salesman Problem <https://en.wikipedia.org/wiki/Travelling_salesman_problem>`_ (TSP).
It consists of 2 parts:

* On `Gradescope <https://www.gradescope.com/>`_, find the assignment on DP where you will learn about the TSP and model it on paper with this technique. Submit your answers when you are ready.
* Then, go to your personal LINFO2266 Github repository, where you will specify your model and solve real TSP instances.
    #. In the ``dynamicprogramming`` package, you will find a file called ``DynamicProgramming.java``. This file contains three parameterized classes ``Model``, ``State`` and ``Transition`` which allow to specify the components of any DP model. Moreover, you will find a class called ``DynamicProgramming`` that will compute the optimal solution of a given DP problem. The **first exercise** is to implement the functions ``getSolution``, ``getValueForState`` and ``rebuildSolution`` in this class.
    #. You can verify your implementation by running the tests for the Knapsack problem, which is already implemented in ``Knapsack.java``.
    #. Now it is your turn to implement a DP problem. The *second exercise* concerns the files ``TSP.java`` and ``TSPState.java`` where you need to fill the class representing a state of the TSP model and then implement functions specifying the recurrence you imagined in the first part of the project. Take a look at ``Knapsack.java`` and ``KnapsackState.java`` if you need an example.
    #. Run the tests for the TSP to see if your implementation is correct and fast enough.
    #. When you are done, do not forget to *commit* and *push* your changes before submitting your work on Inginious.

Project 2: Branch and Bound, Lagrangian Relaxation
===================================================


The second project is about Branch and Bound (BnB), Lagrangian Relaxation and the `Traveling Salesman Problem <https://en.wikipedia.org/wiki/Travelling_salesman_problem>`_ (TSP).

Your implementation work will be in the in the ``branchandbound`` package. As a preliminary, step, read first the class called ``BranchAndBoundKnapsack`` as this class is a good example of what you will do for the TSP, that is:

1. Implement the state/node representation for the BnB search.
2. Implement a lower-bounding procedure to prune the BnB search.

Gradescope
--------------

On `Gradescope <https://www.gradescope.com/>`_, find the written assignment on BnB where you will learn about the TSP and model it on paper with this technique. Submit your answers when you are ready.

Implementation
---------------

Then, go to your personal LINFO2266 Github repository, where you will specify the classes to model and solve real TSP instances with branch and bound in the package ``branchandbound`` (don't forget to pull to get the latest update).

The implementation work is composed of four steps:

#. Implement the simple one-tree based bound procedure in the ``SimpleOneTree`` class. You can test your result by executing ``SimpleOneTreeTestFast``.
#. Implement the branch and bound for the TSP in the ``BranchAndBoundTSP`` class which will use the ``SimpleOneTree`` bound procedure you just implemented earlier. You can test your result by executing ``BranchAndBoundTSPTestFast``.
#. Implement an enhanced bound calculation for the one-tree based on Lagrangian relaxation in the ``HeldKarpOneTree`` class. You can test your result by executing ``HeldKarpOneTreeFast``.
#. Replace in your branch and bound for the TSP ``BranchAndBoundTSP``, the bound calculation by your new reinforced bound. You can test your result by executing ``BranchAndBoundTSPTest``.

Project 3: Linear Programming and Maximum-Flows
===================================================


In this project, you will model and solve a maximum flow problem and a maximum matching problem with a linear programming solver.
It means that for these two problems must be encoded into the form of { max cx : Ax <= b, x >= 0 } that can be used
by the simplex algorithm.

Implementation
---------------


All the files related to this project are in the package ``linearprogramming``. You have to modify two classes


#. ``FlowMatrices.java`` given a FlowNetwork instance, you must compute the coefficient A, b, c for solving the maximum flow problem with the simplex implementation.
#. ``MatchingMatrices.java`` given a bipartite graph, you must compute the coefficient A, b, c for solving the maximum matching problem with the simplex implementation.

Gradescope
--------------

On `Gradescope <https://www.gradescope.com/>`_, find the written assignment for the project 3.
Part of your assignment requires to report experimental results under the form of a graph.


Project 4: Local Search
===================================================

In this project, you will have to develop a local search solver for the Pigment Sequencing Problem (PSP).
It is a Discrete Lot Sizing problem where several items must be produced by a single machine that is able to produce one item per time unit.
Each item must be produced at the latest at its deadline.
Additionally, there are stocking costs and sequence-dependent changeover costs.
The objective is to find a production schedule that respects all deadlines and minimizes the sum of stocking and changeover costs.

Formal definition
------------------

Let :math:`I` be a set of items to be produced and :math:`T` a set of types for those items.
Each item :math:`i \in I` is associated to a deadline :math:`d_i` and a type :math:`t_i \in T`.
We write :math:`p_i` the production period of item :math:`i \in I`.
Each item must be produced at a different time period between 0 and :math:`p_{max}`.
The stocking cost for each item produced is proportional to the number of time units between the deadline and the production period.
Its value for one period of time depends on the item type :math:`S^{t_i}`.
Moreover, a changeover cost :math:`C^{t_i,t_j}` is induced when switching the production of from item type :math:`t_i` to :math:`t_j`.

Let :math:`x_p` denote the item produced at time period :math:`p`.
If :math:`s_p` is the first item produced after period :math:`p` (the machine can be idle at some periods of time), then the PSP can be written as:

$$\\begin{aligned}
\\text{minimize } & \\sum_{p = 0}^{p_{max}-1} S^{t_{x_p}} * (d_{x_p} - p) + C^{t_{x_p},t_{s_p}} & \\\\
\\text{such that } & p \\leq d_{x_p}, & 0 \\leq p < p_{max} \\\\
& x_{p_1} \\neq x_{p_2}, & 0 \\leq p_1 < p_2 < p_{max}, x_{p_1} \\neq IDLE, x_{p_2} \\neq IDLE \\\\
& x_p \\in I \\cup \\{IDLE\\}, & 0 \\leq p < p_{max}
\\end{aligned} $$

Gradescope
---------------

On `Gradescope <https://www.gradescope.com/>`_, find the written assignment for the project about local search.
You will first solve a PSP instance by hand and then report and discuss experimental results.

Implementation
---------------

All the files related to this project are in the package ``localsearch``.

#. In your local search solver, a candidate solution is an array of variables that represent the production schedule :math:`x`. Implement the missing functions in ``ChangeoverCostInvariant.java`` and ``StockingCostInvariant.java`` to compute incrementally the cost of a production schedule after an update.
#. Then, implement the functions in ``PSP.java`` to compute an initial feasible solution of the problem, and check if a swap move (with any number of variables concerned) is feasible.
#. Finally, design your local search solver in ``LocalSearch.java`` that finds the best possible solution under a given time limit. Some features that can be implemented: swap moves with a dynamic number of periods concerned (similar to :math:`k`-opt), random restarts, intensification vs. diversification tradeoff, etc.

.. warning::
    As this task is quite computationally expensive, please test your code locally and only submit on Inginious when you have made substantial improvements to it.

Project 5: Constraint Programming
===================================================

In the 5th project, you will discover Constraint Programming by solving 2 exercises: the Magic Square Problem and the Killer Sudoku Problem.
Those problems are rather hard to solve, and you will use a Constraint Programming solver to tackle them.
But first you have to fill in certain functions to ensure that your solver is ready to be used.

Solver implementation
---------------

Here are the required steps to have your required constraints working:

#. Implement the ``removeAbove`` and ``removeBelow`` methods from the ``Domain`` class. Those methods will remove all values within a domain that are greater / lower than a given threshold.
#. Implement the propagator from the ``Sum`` constraint. This constraint is applied on an array of ``Variable`` :math:`x` and on one expected sum, :math:`y`. It ensures that :math:`\sum x = y`. Your algorithm must be bound-consistent: you only need to update the maximum and minimum values of the variables present within the constraint.

For each of those steps, you will find unit tests to ensure that your solver is working as expected before moving on to the modeling.

Modeling the problems
---------------

There are two problems to model in this project:

#. The Magic Square Problem. Given an square of :math:`n\times n` cells, you need to find an assignment of values to each cell such that

   #. Every value appears once and only once;
   #. The sum of every row, column and of both diagonal within the square are the same.

#. The Killer Sudoku Problem. In this variation of the Sudoku, the cells belong to a cage. The sum of values within the cell belonging to a cage must equal to a given input value. The whole set of constraints in this problem is thus

   #. Each row, column, and subsquare contains each number exactly once;
   #. The sum of all numbers in a cage must match the expected sum of the cage.

The implementation needs to be done within the ``MagicSquareSolver`` and ``KillerSudokuSolver`` files, by completing the TODO's.
In each of those model, you need to give all solutions according to the given input instance by relying on your ``TinyCSP`` solver.
You can also refer to the already implemented ``NQueens`` model if you wish to see how variables should be created, how to add constraints and how to solve a problem.

Gradescope
---------------

On `Gradescope <https://www.gradescope.com/>`_, find the written assignment for the project about constraint programming.
You will first give some details about the modeling of a Magic Square Problem.
Afterwards, you will examine how to derive additional solutions by examining the symmetries within the problem.

Project 6: MDD
===================================================
