.. _bandb:


*************************************************************************************************
Branch and Bound
*************************************************************************************************

Theory
=======================================


Slides and video (keynote)

* `Introduction <https://www.icloud.com/keynote/0jTHGv9VcBJNqr701X0LiSSeQ#part1-intro>`_
* `Séance Intermédiaire <https://www.icloud.com/keynote/037KCYIeXbULVFGRo7xLiY8fA#part1-exercices>`_
* `Restructuration <https://www.icloud.com/keynote/0C9qyvWomr8eHMmHUELTMbC7A#part1-bilan>`_



Exercises
=======================================

Knapsack Problem
"""""""""""""""""""""""""""""""""""""""

Given the following knapsack instance:

.. math::
    \max \quad & 25 x_1 + 18 x_2 + 36 x_3 \\
    \text{subject to} \quad & 5 x_1 + 6 x_2 + 4 x_3 \leq 11 \\
    & x_i \in \{0, 1\}

#. Draw the brute-force search tree for this problem, with the given variable ordering and left (resp. right) corresponding to 0 (resp. 1) assignments.
#. Compute the linear relaxation of the problem at each search node.
#. What nodes can be pruned if we use this relaxation as a bounding procedure and traverse the search tree starting with the left-children? And if we traverse the search tree starting with the right-children?
#. Do the same exercise but with the variable ordering :math:`x_3, x_1, x_2`. Did the performances change? Why?
