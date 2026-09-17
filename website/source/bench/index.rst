.. _bench:


*************************************************************************************************
Benchmarking Optimization Algorithms
*************************************************************************************************

Theory
=======================================

* `Slides <../_static/slides/01a-bench.pdf>`_


Overview
=======================================

When developing solvers and heuristics for combinatorial optimization, rigorous empirical evaluation is crucial to compare approaches and draw scientifically sound conclusions.

Key Topics
---------------------------------------

* **Pitfalls of naive metrics**: Why simple arithmetic means over runtimes can be heavily skewed by the hardest instances or distorted by timeouts.
* **Performance Ratios**: Measuring the relative performance of solver :math:`s` on instance :math:`p` with respect to the best solver on that instance:

  .. math::

     r_{p, s} = \frac{t_{p, s}}{\min_{s' \in \mathcal{S}} t_{p, s'}}

* **Dolan-Moré Performance Profiles**: Evaluating the empirical cumulative distribution function of performance ratios:

  .. math::

     \rho_s(\tau) = \frac{1}{|\mathcal{P}|} \left| \left\{ p \in \mathcal{P} : r_{p, s} \le \tau \right\} \right|

  where :math:`\rho_s(1)` represents the fraction of instances on which solver :math:`s` is the fastest, and the asymptotic limit represents the fraction of instances successfully solved within the cutoff time.
* **Virtual Best Solver (VBS)**: The ideal solver selecting the best-performing algorithm for each instance, providing an empirical lower bound.
* **Best Practices**: Representative benchmark sets, timeout handling, seed management, and reproducible experimentation.

References
---------------------------------------

* Dolan, E. D., & Moré, J. J. (2002). *Benchmarking optimization software with performance profiles*. Mathematical Programming, 91(2), 201-213.
