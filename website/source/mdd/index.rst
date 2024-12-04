.. _mdd:


*************************************************************************************************
MDD
*************************************************************************************************

Theory
=======================================
* `Slides <../_static/slides/08-mdd.pdf>`_



Project : MDD
===================================================

In the 6th project, you will optimize hard combinatorial problems using the 
branch-and-bound with MDD paradigm. In practice you are asked to implement:
a sequential version for the solver interface, and a model + relaxation to solve
the maximum decarbonation problem.

Solver implementation
---------------------------

To get started with your implementation of the `SequentualSolver` we advise you
to go read the pseudo-code given in the slides and then to give a look at the
parallel version which is implemented for you (`ParallelSolver`). 

Once you are done with the implementation of your sequential solver, you will be
able to validate it against the tests in `TestSequentialSolver`.

Modeling the Max Decarbonation Problem
-----------------------------------------

After that, you are asked to model the maximum decarbonation problem in terms
of dynamic programming. To that end, you will want to start by defining the
content of your `state` (class `MaximumDecarbonationState`) and then to implement
the required methods in `MaximumDecarbonationProblem`. 

Once you have completed these first two steps, you should validate your implementation
against the tests provided in `TestMaximumDecarbonationModelFast` and `TestMaximumDecarbonationModel`.

The second step to solving the max decarbonation problem with BaB-MDD will be
to implement a relaxation (merge heuristic) to use when compiling relaxed DDs.
You are expected to write that implementation in the class `MaximumDecarbonationRelaxation`.
Finally, you will write the implementation of a state ranking which will be
used to compare states and select the ones that are deemed the most promising
(in class `MaximumDecarbonationStateRanking`). 

Then, you will validate your implementation work using: the tests in 
`TestMaximumDecarbonationFast` and `TestMaximumDecarbonation`.

Gradescope
---------------

On `Gradescope <https://www.gradescope.com/>`_, find the written assignment for the project about branch-and-bound with decision diagrams.
You will first get a hands-on reminder of what relaxed and restricted DDs are.
Afterwards, you will give the details of how to model the maximum decarbonation
problem in terms of dynamic programming along with a relaxation to merge nodes
when a layer grows too large.


