.. _lp:


*************************************************************************************************
Linear Programming
*************************************************************************************************

Theory
=======================================

.. * `Videos <https://youtube.com/playlist?list=PLq6RpCDkJMyoSSeucDx7FyUpMDjhc-Kyf>`_

* `Slides <../_static/slides/04-linear-programming.pdf>`_



Project: Linear Programming and Maximum-Flows
===================================================


In this project, you will model and solve a maximum flow problem and a maximum matching problem with a linear programming solver.
It means that for these two problems must be encoded into the form of

.. math::
    \max \quad & cx \\
    \text{subject to} \quad & Ax \leq b \\
    & x \ge 0

that can be used by the simplex algorithm.

Implementation
---------------

All the files related to this project are in the package ``linearprogramming``. You have to modify three classes:


#. ``BigMSimplex.java`` 
#. ``FlowMatrices.java`` 
#. ``MatchingMatrices.java`` 

BigMSimplex
~~~~~~~~~~~~~~

In case the the :math:``b`` vector contains negative entries, the simplex method cannot start directly.
A first phase, using the simplex on a transformed problem is needed to find a basic feasible solution.
Once this basic feasible solution is found, the second phase purse, also with the simplex on the the original objective.
This is the method implemented in the ``TwoPhaseSimplex.java`` that is provided.
In the ``TwoPhaseSimplex.java``, the initialization is done 
by adding new variables :math:`x_a`, called *artificial variables* so that 
those new variables compose the first basic feasible solution.
The simplex can then start pivoting operations while minimizing the sum of those artificial variables.
More exactly, if the original problem is

.. math::
    \max \quad & cx \\
    \text{subject to} \quad & Ax + s = b \\
    & s \ge 0 \\
    & x \ge 0

where :math:`s` are the slack variables, then the transformed problem for the first phase is:

.. math::
    \max \quad & - \mathbf{1}^\top x_a  \\
    \text{subject to} \quad & Ax + s + x_a = b \\
    & s \ge 0 \\
    & x \ge 0 \\
    & x_a \ge 0

In the case where a row includes a negative :math:`b_i`, the row is multiplied by :math:`-1` (except for the term :math:`x_a`). 
Thus, we can assume :math:`b \ge 0` in all cases and solve the problem using :math:`x_a` for the base.
Once the objective of the first phase has reached zero after pivoting operations,
it means that all artificial variables are set to 0, and the optimization can switches back to the original objective: :math:`\max cx`.


This ``TwoPhase`` simplex method is is very popular.
We propose that you implement and test an alternative method, called ``BigMSimplex.java`` to cope with possibly negative value for :math:`b_i` entries.
This method does not use two phases but still introduces artificial variables.
Instead of switching between two objectives, only one objective is used. 
This method uses a large constant :math:`M >> 0` (called big-M), and solve following problem (in tableau form):


.. math::
    \max \quad & cx - M \, (\mathbf{1}^\top x_a) \\
    \text{subject to} \quad & Ax + s + x_a = b \\
    & s \ge 0 \\
    & x \ge 0 \\
    & x_a \ge 0

Given that the second term is much larger than the first, this will enforce a lexicographic optimization of the two objectives: first, minimizing the use of artificial variables (:math:`\max \, (\mathbf{1}^\top x_a)`) then maximize the original objective (:math:`\max cx`) as a tie-breaker.
Similar to the two-phase simplex method, a first basic feasible solution is readily available with :math:`x_a` as the base.
Your implementation should be completed within ``BigMSimplex.java``.

You can test your code by running the example in ``DietProblem.java``, that solves the `Diet problem <https://en.wikipedia.org/wiki/Stigler_diet>`_ .

Once your code is ready, you can submit it onto inginious and work on the report.


FlowMatrices
~~~~~~~~~~~~~~

Given a FlowNetwork instance, you must compute the coefficient A, b, c for solving the maximum flow problem with the simplex implementation. 
To retrieve your solution depending on your matrices, you must also fill in the function ``assignFlow`` in addition to the constructor.

MatchingMatrices
~~~~~~~~~~~~~~~~~~~

Given a bipartite graph, you must compute the coefficient A, b, c for solving the maximum matching problem with the simplex implementation. 
To retrieve your solution depending on your matrices, you must also fill in the function ``isEdgeSelected`` in addition to the constructor.

Gradescope
--------------

On `Gradescope <https://www.gradescope.com/>`_, find the written assignment for the project 3.
Part of your assignment requires to report experimental results under the form of a graph.


..
	Exercises
	=======================================

	Finding a basic feasible solution and pivoting
	"""""""""""""""""""""""""""""""""""""""

	Given the following linear program:

	.. math::
	    \min \quad & 2 x_1 + 3 x_2 \\
	    \text{subject to} \quad & x_3 = 2 + x_1 - x_2 \\
	    & x_4 = -3 + 2x_1 + 3x_2 \\
	    & x_1, x_2, x_3, x_4 \ge 0

	#. Find a *basic feasible solution* (BFS) to initialize the simplex algorithm. Is it trivial to find it or do you need to create and solve the auxiliary problem?
	#. Find the optimal solution of the problem.

	Standard, slack forms and pivoting
	"""""""""""""""""""""""""""""""""""""""

	Given the following linear program:

	.. math::
	    \max \quad & x_1 + 3 x_2 \\
	    \text{subject to} \quad & x_1 - x_2 \le 8 \\
	    & x_1 + x_2 \ge 3 \\
	    & -x_1 + 4x_2 = 2 \\
	    & x_1 \ge 0

	#. Transform it in *standard form* (only :math:`\le` inequalities and all variables must have a positivity constraint).
	#. Transform the standard form of the problem in *slack form* (only equalities and all variables must have a positivity constraint).
	#. Find a BFS to initialize the simplex algorithm. Is it trivial to find it or do you need to create and solve the auxiliary problem?
	#. Find the optimal solution of the problem.

	.. note:: If you are training yourself on other linear programs, it is always useful to verify your solution with online solvers like `this one <https://linprog.com/en/main-simplex-method>`_ which provide all the steps to reach the solution.
