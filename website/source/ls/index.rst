.. _ls:


*************************************************************************************************
LS
*************************************************************************************************

Theory
=======================================


* `Slides <../_static/slides/06-local-search.pdf>`_


.. * `Slides  <https://www.icloud.com/keynote/0X0mx27SJ79kODLuNqocALfvQ#06-local-search>`_



Read and understand the source code of package minils and the sudoku example on `github <https://github.com/pschaus/linfo2266/tree/master/src/main/java/localsearch/minils>`_.

* Part 2: LS Routing Problems

Exercises
=======================================

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
