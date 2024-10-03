.. _bandb:


*************************************************************************************************
Branch and Bound
*************************************************************************************************

Theory
=======================================

* `Slides <../_static/slides/02-branch-and-bound.pdf>`_



Project: Branch and Bound, Lagrangian Relaxation
===================================================



The second project is about Branch and Bound (BnB), Lagrangian Relaxation and the `Traveling Salesman Problem <https://en.wikipedia.org/wiki/Travelling_salesman_problem>`_ (TSP).

Your implementation work will be in the in the ``branchandbound`` package. As a preliminary, step, read first the class called ``BranchAndBoundKnapsack`` as this class is a good example of what you will do for the TSP, that is:

1. Implement the state/node representation for the BnB search.
2. Implement a lower-bounding procedure to prune the BnB search.

Gradescope
--------------

On `Gradescope <https://www.gradescope.com/>`_, find the written assignment on BnB where you will learn about the TSP and model it on paper with this technique. 
You can already answer to the Exercises 1, while Exercise 2 and 3 will need to wait until you complete your implementation.

Implementation
---------------

Then, go to your personal LINFO2266 Github repository, where you will specify the classes to model and solve real TSP instances with branch and bound in the package ``branchandbound`` (don't forget to pull to get the latest update).

The implementation work is composed of four steps:

#. Implement the cheapest incident lower bound procedure in the ``CheapestIncidentLowerBound`` class. You can test your result by executing ``CheapestIncidentLowerBoundTestFast``.
#. Implement the one-tree lower bound procedure in the ``OneTreeLowerBound`` class. You can test your result by executing ``OneTreeLowerBoundTestFast``.
#. Implement the branch and bound for the TSP in the ``BranchAndBoundTSP`` class which will use the lower bounding procedures you just implemented earlier. You can check your result by executing the test ``testOneTree`` from ``BranchAndBoundTSPTestFast``.
#. Implement an enhanced bound calculation for the one-tree based on Lagrangian relaxation in the ``HeldKarpLowerBound`` class. You can test your result by executing ``HeldKarpLowerBoundTestFast`` and the remaining tests from ``BranchAndBoundTSPTestFast``.
#. Replace in your branch and bound for the TSP ``BranchAndBoundTSP``, the bound calculation by your new reinforced bound (note that you can even use the ```TSPSuperLowerbound`` that takes the best of the two). You can test your result by executing ``BranchAndBoundTSPTest``.

Once your implementation is ready, don't forget to finish your written assignment, by writing your answer for Exercise 2 and 3!
The exercise on the gap computation for the report will require that you modify the branch and bound it-self or do some printing from there.



.. * `Videos <https://youtube.com/playlist?list=PLq6RpCDkJMyoMPDl66rUcQlkMHSGWENib>`_
.. * `Slides <https://www.icloud.com/keynote/0JO4LJSpQik_9to_JZAys14mQ#02-branch-and-bound>`_

..
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
