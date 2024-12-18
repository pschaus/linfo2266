.. _dp:


*************************************************************************************************
Dynamic Programming
*************************************************************************************************

Theory
=======================================


.. * `Videos <https://youtube.com/playlist?list=PLq6RpCDkJMyr-4iiykzoz6nMb0gEI4tjR>`_

* `Slides <../_static/slides/01-dynamic-programming.pdf>`_


Project: Dynamic Programming
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



..
   Exercises
   =======================================

   Longest-Path in a Directed Acyclic Graph
   """""""""""""""""""""""""""""""""""""""""""

   A *directed acyclic graph* (DAG) is a graph with directed edges (one way) which has no cycles.
   The figure below shows a DAG representing a set of tasks.
   Each of them has a duration and depends on other tasks which have to be completed before the task can be started.
   The longest path of this graph is called the **critical path** because it determines the total time needed to complete all tasks while respecting all dependencies.

   .. figure:: Pert_chart_colored.svg
      :width: 400px

      A PERT chart, a real-life application of DAGs. From `Wikipedia <https://en.wikipedia.org/wiki/Directed_acyclic_graph>`_.

   Given a set of vertices :math:`V = \{1,\ldots,n\}` and set of weighted edges :math:`E` where :math:`(i,j,w) \in E` if vertex :math:`i` has an edge to vertex :math:`j` of weight :math:`w`.
   You are asked to find the longest path connecting :math:`s` to :math:`t`, with :math:`s,t \in V` and provided that at least one such path exists.

   #. Find a dynamic programming model for this problem and formulate the Bellman recurrence equations.
   #. What is the time and space complexity of an algorithm computing these recurrence equations?
   #. What do you need to change to solve the shortest-path problem on a DAG?

   Longest Increasing Subsequence
   """""""""""""""""""""""""""""""""""""""

   Given a sequence of integers, the problem asks to find the longest (strictly) increasing subsequence.
   A *subsequence* is a subset of the elements of a sequence and appearing in the same order.

   Below is a sequence of integers with a longest increasing subsequence in bold:

   .. csv-table::

       **0**,8,4,12,**2**,10,**6**,14,1,**9**,5,**13**,3,11,7,**15**

   #. Find a dynamic programming model for this problem and formulate the Bellman recurrence equations.
   #. Write a pseudocode for your algorithm. What is its time and space complexity?
   #. Can you model your solution as a longest-path problem in a DAG?
   #. (Bonus) Implement and verify your algorithm on `LeetCode <https://leetcode.com/problems/longest-increasing-subsequence/>`_.

   Maximum Height Box-Stacking
   """""""""""""""""""""""""""""""""""""""

   You are given a set of boxes :math:`B = \{(w_1,d_1,h_1),\ldots,(w_n,d_n,h_n)\}`, where :math:`w_i,d_i,h_i` denote respectively the width, depth and height of a box.
   The goal is to use them to build the highest stack possible.

   Box :math:`j` can be placed on top of box :math:`i` if and only if :math:`w_i \ge w_j \land d_i \ge d_j \land h_i \ge h_j`.
   In addition, **the boxes can be rotated** i.e. their dimensions can be permuted.

   #. Find a dynamic programming model for this problem and formulate the Bellman recurrence equations. Do you need to adapt the problem input first?
   #. Write a pseudocode for your algorithm. What is its time and space complexity?
   #. (Bonus) Implement and verify your algorithm on `LeetCode <https://leetcode.com/problems/maximum-height-by-stacking-cuboids/>`_.
