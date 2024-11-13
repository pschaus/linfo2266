.. _cp:


*************************************************************************************************
CP
*************************************************************************************************

Theory
=======================================


* `Slides <../_static/slides/07-intro-cp.pdf>`_



Project : Puzzles with CP
=======================================

Work in progress ...

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
