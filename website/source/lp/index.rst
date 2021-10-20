.. _lp:


*************************************************************************************************
Linear Programming
*************************************************************************************************

Theory
=======================================

* `Videos <https://youtube.com/playlist?list=PLq6RpCDkJMyoSSeucDx7FyUpMDjhc-Kyf>`_
* `Slides <https://www.icloud.com/keynote/0Tu8miHL9lE61RhouiWizTIJQ#03-linear-programming>`_

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
