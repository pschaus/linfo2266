.. _cp:


*************************************************************************************************
CP
*************************************************************************************************

Theory
=======================================


* `Slides <../_static/slides/07-intro-cp.pdf>`_



Project : Constraint Programming
===================================================

In the 5th project, you will discover Constraint Programming by solving 3 exercises: the Magic Square, the Killer Sudoku and the Knight-Tour problems.
Those problems are rather hard to solve, and you will use a Constraint Programming solver to tackle them.
But first you have to complete certain methods to ensure that your solver is ready to be used.

Solver implementation
---------------------------

Here are the required steps to have your required constraints working:

#. Implement the ``removeAbove`` and ``removeBelow`` methods from the ``Domain`` class. Those methods will remove all values within a domain that are greater / lower than a given threshold.
#. Implement the propagator from the ``Sum`` constraint. This constraint is applied on an array of ``Variable`` :math:`x` and on one expected sum, :math:`y`. It ensures that :math:`\sum x = y`. Your algorithm must be bound-consistent: you only need to update the maximum and minimum values of the variables present within the constraint.
#. Implement the propagator from the ``LessOrEqual`` constraint. This constraint is applied on two ``Variable``: :math:`x` and :math:`y`, and ensures that :math:`x \leq y`. Your algorithm must be bound-consistent: you only need to update the maximum and minimum values of the variables present within the constraint.

For each of those steps, you will find corresponding unit tests to ensure that your solver is working as expected before moving on to the modeling.

Modeling the problems
---------------------------

There are two problems to model in this project:

#. The Magic Square Problem. Given an square of :math:`n\times n` cells, you need to find an assignment of values to each cell such that

  #. Every value appears once and only once;
  #. The sum of every row, column and of both diagonal within the square are the same.

#. The Killer Sudoku Problem. In this variation of the Sudoku, the cells belong to a group. The sum of values within the cell belonging to a group must equal to a given input value. The whole set of constraints in this problem is thus

  #. Each row, column, and subsquare contains each number exactly once;
  #. The sum of all numbers in a group must match the expected sum of the group.


#. The KnightTour Problem: A knight's tour is a sequence of moves of a knight on a chessboard, such that

  #. The knight visits every square exactly once, and
  #. returns to the starting square.

The implementation needs to be done within the ``KnightTourSolver`` by completing the TODO's.
You'll also need to implement the ``KnightMove`` constraint.

Gradescope
---------------

On `Gradescope <https://www.gradescope.com/>`_, find the written assignment for the project about constraint programming.
You will first give some details about the modeling of a Magic Square Problem.
Afterwards, you will examine how to derive additional solutions by examining the symmetries within the problem.
Finally, a last step will ask you to run some experiments using your solver.
You'll also study an interesting alternative model for the KnightTour problem.

..
    Exercises
    =======================================

    N-Queens Problem: First-Fail Strategy
    """""""""""""""""""""""""""""""""""""""

    The N-Queens problem is the following: how to place N queens on a NxN chess board without any queen attacking each other.

    Given a 6-Queens problem with one variable deciding the position of the queen in each row and the following partial assignments,
    which variable will be chosen next by the first-fail strategy in each case?

    .. list-table::

        * - .. figure:: nqueens_a.png

                Partial assignment A
                :math:`q_1 = 3, q_6 = 1`

          - .. figure:: nqueens_b.png

                Partial assignment B
                :math:`q_3 = 5, q_5 = 4`

          - .. figure:: nqueens_c.png

                Partial assignment C
                :math:`q_4 = 2, q_5 = 4`


    Element Constraint and Bound vs. Domain Consistency
    """""""""""""""""""""""""""""""""""""""

    The Element constraint has the form :math:`X = C(Y)`
    where :math:`C` is a vector of integers and :math:`X` and :math:`Y` are integer variables.

    Given :math:`dom(X) = \{0, 1, 2, 3, 4, 5\}` and :math:`dom(Y) = \{0, 1, 2, 3, 4, 5\}`,
    and the array :math:`C = [2, 4, 5, 1, 2, 5]`.

    #. How will the domains of :math:`X` and :math:`Y` evolve after imposing the constraint?
    #. And after removing 5 from the domain of :math:`X`?
    #. For the different cases given in the table below, mention if *domain consistency* or *bound consistency* is achieved.

    ========================= =========================
     :math:`dom(X)`            :math:`dom(Y)`
    ========================= =========================
     :math:`\{0,1,2,3,4,5\}`   :math:`\{0,1,2,3,4,5\}`
     :math:`\{1,2,3,4,5\}`     :math:`\{0,2,3\}`
     :math:`\{2,5\}`           :math:`\{0,2,5\}`
     :math:`\{2,3,4,5\}`       :math:`\{0,1,2,3\}`
    ========================= =========================

    AllDifferent Constraint
    """""""""""""""""""""""""""""""""""""""

    Given the variables :math:`X1, X2, X3, X4` and their domains :math:`dom(X1)=\{1,3\}, dom(X2)=\{1,3\}, dom(X3)=\{1,3,8\}, dom(X4)=\{8,9\}`.


    The constraint AllDifferent :math:`(X1,X2,X3,X4)` links them.

    Justify why the value 8 should be removed from the domain of :math:`X4`.
