	.. _intro:



************
Organization
************

* `Slides <../_static/slides/00-intro.pdf>`_

Pedagogy
=======================================

The presentation of the algorithms will be either proposed in the form of lectures, videos or reading and will be accompanied by practical work (assignments / micro-projects) requesting the implementation algorithms to solve a practical optimization problem and the writing of reports.

Evalutation
==============

January
"""""""""

For the first session, the global grade for the course is solely based on the grades of the 6 computing projects, submitted and evaluated during the semester.

August
""""""""

For the second session, previously submitted projects will not be re-evaluated and cannot be resubmitted.
The project grade will count for 10 points in the second session, and a new project will be proposed, also worth 10 points, to improve the grade.
This new project will be an individual programming project.
It will require a written report, and if the professor deems it necessary, an interview about the project may also be arranged to ensure all theoretical concepts are well understood.

Projects are invididual.
Neverthess, In this course, we recognize the evolving nature of technology and the potential benefits of using generative AI tools in the programming process. However, academic honesty and originality remain paramount. To that end:

* Generative AI Usage: Students are permitted to use generative AI tools to assist with their assignments. Such tools can provide inspiration, suggest coding approaches, or help troubleshoot issues.
* Original Work: While AI can be a tool, it should not be the sole author of your assignment. Your submission should be primarily your own work. Directly copying and pasting solutions from AI outputs without understanding or modification is discouraged. Similarly, collaborating with fellow students is a valuable part of the learning process, but directly copying another student's work is considered plagiarism.
* Source Indication: Whenever you use generative AI to assist in your assignment, you are required to indicate so by providing a brief comment in your code on how the AI was used. For example:

.. code-block:: python

	# Used AI to suggest optimization for this loop.
	for i in range(10): ...
	 


Failure to adhere to these guidelines may result in a reduction of marks or other academic penalties.
The same consequences will hold for a student that voluntarily shares his code or make available to other students (this includes sharing your code on a public or private repository).
If deemed necessary by the instructor, an interview about the projects may also be conducted.



Tools
==============


This course will use *Java* language version Java8_.
Recommanded IDE is IntelliJ_.

.. _Java8: https://docs.oracle.com/javase/8/docs/api.
.. _IntelliJ: https://www.jetbrains.com/idea/
.. _Inginious: https://inginious.info.ucl.ac.be
.. _JUnit4: https://junit.org/junit4/.


Projects and submissions
==============================


Projects consist of two parts: theoretical questions or exercises to be solved by hand and a programming assignment to put everything you have learned in practice.
To that end, the course will use two different platforms: Gradescope and Inginious.
Below are some instructions on how to set up and use these two tools, followed by the project descriptions.

Gradescope
""""""""""""""""

#. Go to `Gradescope <https://www.gradescope.com/courses/869042>`_ and connect with your UCLouvain account.
#. Add the LINFO2266-2024-2025 (Course ID: 869042) course by clicking on **Enroll in Course** and entering the code 7E5W86.
#. You will find the theoretical part of the projects there, which consist of a PDF document with a few questions.
#. Answer these questions either by printing the document or filling it on your computer. *If you follow the instructions for the Inginious part, you will find the .tex files in the* ``tex/`` *folder of the repository so you can fill them directly.*
#. Do not forget to submit your answers on Gradescope when you are done.

Inginious
""""""""""""""

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
