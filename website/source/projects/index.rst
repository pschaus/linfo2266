.. _projects:


*************************************************************************************************
Projects
*************************************************************************************************

Projects will usually consist of two parts: theoretical questions or exercises to be solved by hand and a programming assignment to put everything you have learned in practice.
To that end, the course will use two different platforms: Gradescope and Inginious.
Below are some instructions on how to set up and use these two tools, followed by the project descriptions.

Gradescope
===================================================

#. Go to `Gradescope <https://www.gradescope.com/>`_ and connect with your UCLouvain account.
#. Add the LINFO2266 course by clicking on **Enroll in Course** and entering the code given in class.
#. You will find the theoretical part of the projects there, which consist of a PDF document with a few questions.
#. Answer these questions either by printing the document or filling it on your computer. *If you follow the instructions for the Inginious part, you will find the .tex files in the* ``tex/`` *folder of the repository so you can fill them directly.*
#. Do not forget to submit your answers on Gradescope when you are done.

Inginious
===================================================

#. Go to `Inginious <https://inginious.info.ucl.ac.be/>`_ and connect with your UCLouvain account.
#. Find LINFO2266 in the course list (**[LINGI 2266] Advanced Algorithms for Optimization**) and add it by clicking on **Enroll in the course**.
#. Go to the task called **Create your repository** and enter your Github username. This will create a Github repository with the projects of the course that you can clone on your computer.
#. This repository contains a Maven project that you can easily open with  `IntelliJ <https://www.jetbrains.com/idea/>`_ (or `Visual Studio Code <https://code.visualstudio.com/>`_ with the recommended Java extensions).
#. For each project, a package is already created in the ``src/main/java/`` folder with some Java classes.
#. Follow the instructions of each project on how to fill some of those classes.
#. Once you think you have something working, you can run the tests provided in the corresponding ``src/test/java/`` folder.
#. Finally, submit your code on Inginious on the task dedicated to each project.

Project 1: Dynamic Programming
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

Project 2: Branch and Bound, Lagrangian Relaxation
===================================================


The second project is about Branch and Bound (BnB), Lagrangian Relaxation and the `Traveling Salesman Problem <https://en.wikipedia.org/wiki/Travelling_salesman_problem>`_ (TSP).

Your implementation work will be in the in the ``branchandbound`` package. As a preliminary, step, read first the class called ``BranchAndBoundKnapsack` as this class is a good example of what you will do for the TSP, that is:

1. Implement the state/node representation for the BnB search.
2. Imlement a lower-bounding procedure to prune the BnB search.


Gradescope
--------------

On `Gradescope <https://www.gradescope.com/>`_, find the written assignment on BnB where you will learn about the TSP and model it on paper with this technique. Submit your answers when you are ready.

Implementation
---------------

Then, go to your personal LINFO2266 Github repository, where you will specify the classes to model and solve real TSP instances with branch and bound in the package `branchandbound`(don't forget to pull to get the latest update).

The implementation work is composed of four steps:

#. Implement the simple one-tree based bound procedure in the ``SimpleOneTree`` class. You can test your result by executing ``SimpleOneTreeTestFast``.
#. Implement the branch and bound for the TSP in the ``BranchAndBoundTSP`` class which will use the ``SimpleOneTree`` bound procedure you just implemented earlier. You can test your result by executing ``BranchAndBoundTSPTestFast``.
#. Implement an enhanced bound calculation for the one-tree based on Lagrangian relaxation in the ``HeldKarpOneTree'' class. You can test your result by executing ``HeldKarpOneTreeFast``.
#. Replace in your branch and bound for the TSP ``BranchAndBoundTSP``, the bound calculation by your new reinforced bound. You can test your result by executing ``BranchAndBoundTSPTest``.


     


Project 3: Linear Programming and Maximum-Flows
===================================================

Project 4: Local Search
===================================================

Project 5: Constraint Programming
===================================================

Project 6: MDD
===================================================
