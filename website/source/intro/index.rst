	.. _intro:



************
Organization
************

* `Slides <../_static/slides/00-intro.pdf>`_

Pedagogy
=======================================

The presentation of the algorithms will be either proposed in the form of lectures, videos or reading and will be accompanied by practical work (assignments / micro-projects) requesting the implementation algorithms to solve a practical optimization problem and the writing of reports.

Evaluation
==========

January — First Session
-----------------------

The January grade consists of two independent components worth 10 points each, for a total of 20 points:

* **10 points** for practical programming;
* **10 points** for theory.

Practical Component — 10 points
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The 10 practical points can be obtained in one of two ways:

* by passing the practical exam on INGInious, organized at the end of the quadrimester and lasting two hours;
* or by passing the programming component on paper of the January exam.

The INGInious exam covers the programming projects completed during the quadrimester.

Failing the INGInious exam does not mean losing the 10 practical points: students can still obtain these points through the programming component on paper of the January exam.

However, students are encouraged to take part in the INGInious exam and try to pass it. If they pass, the 10 practical points are secured before the January exam, allowing them to focus on the theoretical component during the paper exam and therefore reducing the amount of programming material they need to prepare.

Theoretical Component — 10 points
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

The 10 theoretical points are assessed during the January paper exam.

The exam may include questions relating to the projects completed during the quadrimester and to their source code.

August — Second Session
-----------------------

During the second session, the exam is entirely conducted on paper and is also divided into two components worth 10 points each:

* **10 points** for theory;
* **10 points** for practical programming, in the form of programming exercises on paper.

The practical component of the August exam corresponds to the 10 points that were initially assessed through the INGInious exam.

However, the 10 practical points corresponding to the programming component on paper in January cannot be carried over separately to August.

Summary
-------

.. list-table::
   :widths: 20 25 40 15
   :header-rows: 1

   * - Session
     - Theory
     - Practical
     - Total
   * - **January**
     - 10 pts on paper
     - 10 pts via INGInious or programming on paper
     - 20 pts
   * - **August**
     - 10 pts on paper
     - 10 pts of programming on paper (carry-over of the INGInious component)
     - 20 pts

Exam Conditions
---------------

All assessments are individual. No collaboration, communication, or outside assistance is permitted during the assessments.

The use of generative AI is strictly prohibited during the INGInious exam as well as during the paper exams, both in January and in August.

Students must therefore complete all assessments individually and without using any generative AI tools.

.. note::
   The INGInious exam is therefore a risk-free opportunity in January: if a student fails it, they can still obtain the 10 practical points through the programming component on paper in January. If they pass it, they secure this component in advance and benefit from a reduced amount of programming material to prepare for the January exam.


Tools
=====

This course will use *Java* language version Java8_.

The recommended IDE is IntelliJ_, as this is the IDE that will be available during the practical programming test on Inginious.

.. _Java8: https://docs.oracle.com/javase/8/docs/api.
.. _IntelliJ: https://www.jetbrains.com/idea/
.. _Inginious: https://inginious.info.ucl.ac.be
.. _JUnit4: https://junit.org/junit4/.



Projects and submissions
==============================


The programming assignments put everything you have learned in practice.
Below are some instructions on how to set up and use Inginious, followed by the project descriptions.

..
    Gradescope
    """"""""""""""""

    #. Go to `Gradescope <https://www.gradescope.com/courses/1133415>`_ and connect with your UCLouvain account.
    #. Add the LINFO2266-2025-2026 (Course ID: 1133415) course by clicking on **Enroll in Course** and entering the code D3VYJP.
    #. You will find the theoretical part of the projects there, which consist of a PDF document with a few questions.
    #. Answer these questions either by printing the document or filling it on your computer. *If you follow the instructions for the Inginious part, you will find the .tex files in the* ``tex/`` *folder of the repository so you can fill them directly.*
    #. Do not forget to submit your answers on Gradescope when you are done.


Inginious
---------


#. Go to `Inginious <https://inginious.info.ucl.ac.be/>`_ and connect with your UCLouvain account.
#. Find LINFO2266 in the course list (**[LINGI 2266] Advanced Algorithms for Optimization**) and add it by clicking on **Enroll in the course**.
#. Go to the task called **Create your repository** and enter your Github username. This will create a Github repository with the projects of the course that you can clone on your computer.
#. This repository contains a Maven project that you can easily open with  `IntelliJ <https://www.jetbrains.com/idea/>`_ (or `Visual Studio Code <https://code.visualstudio.com/>`_ with the recommended Java extensions).
#. For each project, a package is already created in the ``src/main/java/`` folder with some Java classes.
#. Follow the instructions of each project on how to fill some of those classes.
#. Once you think you have something working, you can run the tests provided in the corresponding ``src/test/java/`` folder.
#. Finally, submit your code on Inginious on the task dedicated to each project.


Contact and communication
=======================================

Important communications will be made using `Moodle <https://moodle.uclouvain.be/course/view.php?id=1474>`_.

Prof: `Pierre Schaus <pierre.schaus@uclouvain.be>`_ and
TAs:  `Alice Burlats <auguste.burlats@uclouvain.be>`_ and `Amaury Guichard <amaury.guichard@uclouvain.be>`_
