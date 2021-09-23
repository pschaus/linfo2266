.. _dp:


*************************************************************************************************
Dynamic Programming
*************************************************************************************************

Theory
=======================================

Slides and video (keynote)

* `Videos <https://youtube.com/playlist?list=PLq6RpCDkJMyr-4iiykzoz6nMb0gEI4tjR>`_
* `Slides <https://www.icloud.com/keynote/0rC8e10kIxOdGdN5QlIe6ASpw#01-dynamic-programming>`_



Exercises
=======================================

Longest Increasing Subsequence
"""""""""""""""""""""""""""""""""""""""

Given a sequence of integers, the problem asks to find the longest (strictly) increasing subsequence.
A *subsequence* is a subset of the elements of a sequence and appearing in the same order.

.. csv-table:: A sequence of integers with a longest increasing subsequence in bold.

    **0**,8,4,12,**2**,10,**6**,14,1,**9**,5,**13**,3,11,7,**15**

#. Find a dynamic programming model for this problem and formulate the Bellman recurrence equations.
#. Write a pseudocode for your algorithm. What is its time and space complexity?
#. (Bonus) Implement and verify your algorithm on `LeetCode <https://leetcode.com/problems/longest-increasing-subsequence/>`_.
