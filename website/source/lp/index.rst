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

#. ``FlowMatrices.java`` : given a FlowNetwork instance, you must compute the coefficient A, b, c for solving the maximum flow problem with the simplex implementation. To retrieve your solution depending on your matrices, you must also fill in the function ``assignFlow`` in addition to the constructor.
#. ``MatchingMatrices.java`` : given a bipartite graph, you must compute the coefficient A, b, c for solving the maximum matching problem with the simplex implementation. To retrieve your solution depending on your matrices, you must also fill in the function ``isEdgeSelected`` in addition to the constructor.
#. ``BigMSimplex.java`` initializes the simplex method, even when negative values for b are given. You need to fill in the simplex tableau to ensure that it finds a correct solution. Here are some inspirations for implementing it:

  - In the class ``LinearProgramming.java`` lies an implementation of the simplex algorithm, without the initialization.
  - In ``TwoPhaseSimplex.java``, the initialization is done by adding new variables x_a, called *artificial variables*, and transforming the objective function. The new objective consists in minimizing the sum of artificial variables: instead of encoding in the simplex tableau


.. math::
    \max \quad & cx \\
    \text{subject to} \quad & Ax + s = b \\
    & s \ge 0 \\
    & x \ge 0

where :math:`s` are the slack variables, the problem is now

.. math::
    \max \quad & - sum(x_a) \\
    \text{subject to} \quad & Ax + s + x_a = b \\
    & s \ge 0 \\
    & x \ge 0 \\
    & x_a \ge 0

In the first formulation, if a :math:`b_i` was negative, the corresponding :math:`x_i` could not be used within the base (because it means that :math:`x_i` should be negative, which is prohibited by the last constraints of the problem). The second formulation thus introduces the artificial variables and use them in the base. In the case where a row includes a negative :math:`b_i`, the row is multiplied by :math:`-1` (except for the term :math:`x_a`). Thus, we can assume :math:`b \ge 0` in all cases and solve the problem using :math:`x_a` as the base.

Once the objective of the second formulation is solved, and that all artificial variables are set to 0, the program switches back to the original objective: :math:`\max cx`.

We ask you to implement a variation of ``TwoPhaseSimplex.java``, that does not work using two phases. Artificial variables are still introduced, but instead of switching between 2 objectives, only one objective is used. The algorithm takes as input a large constant :math:`M >> 0`, and tackles the problem (in tableau form):

.. math::
    \max \quad & cx - M \,sum(x_a)\\
    \text{subject to} \quad & Ax + s + x_a = b \\
    & s \ge 0 \\
    & x \ge 0 \\
    & x_a \ge 0

Given that the second term is much larger than the first, this forces the simplex to do a lexicographic search: it will first minimize the use of artificial variables (:math:`\max - M \, sum(x_a)`) and then maximize the original objective (:math:`\max cx`). Your implementation needs to be done within ``BigMSimplex.java``.

You can test your code by running the example in ``DietProblem.java``, that solves the `Diet problem <https://en.wikipedia.org/wiki/Stigler_diet>`_ .

Once your code is ready, you can submit it onto inginious and work on the report.

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
