.. _lagrangian:


*************************************************************************************************
Lagrangian Relaxation
*************************************************************************************************

Theory
=======================================

.. * `Videos <https://youtube.com/playlist?list=PLq6RpCDkJMyqeA5zIrSCy8tqWrJsWSZEf>`_

* `Slides <../_static/slides/03-lagrangian-relaxation.pdf>`_

..
	Exercises
	=======================================

	Set Covering Problem
	"""""""""""""""""""""""""""""""""""""""

	Given :math:`m` sets of integers, such that :math:`\cup_{i=1}^m S_i = \{1,\ldots,n\}`, associated each with a cost :math:`c_i`.
	The goal of the *set covering problem* is to find the sets covering the universe :math:`U=\{1,\ldots,n\}` at minimum cost.

	.. math::
	    \min \quad & \sum_{i=1}^m c_ix_i & \\
	    & \sum_{\substack{i=1\\j \in S_i}}^m x_i \ge 1 \quad& j=1,\ldots,n\\
	    & x_i \in \{0,1\} & i=1,\ldots,m

	#. Give the Lagrangian relaxation formula.
	#. Given the sets :math:`S_1 = \{1,2\}, S_2 = \{3\}, S_3 = \{1,3\}, S_4 = \{2,3\}` with :math:`c_1=2, c_2=3, c_3=4, c_4=5`. What is the lower-bound for :math:`\lambda_1 = 1.5, \lambda_2 = 1.6, \lambda_3 = 2.2`?
	#. Design a subgradient procedure to compute a lower-bound.
