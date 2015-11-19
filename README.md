# pharos
An optimizer developed for SDN based large scale networks. This is developed for a DOE project that aims at providing optimized resource management for multi-domain multi-provider SDN networks. 

It uses javailp, a java interface to Integer Linear Programming solvers. As for the solver, I use lp_solve (http://lpsolve.sourceforge.net/5.5/). I also tried Gurobi, however, it only works for small problems. This is not Gurobi's fault, but the way the variables are name, for bigger problems, the size of a variable seems to be too long.

The work is published in HotSDN'14, Proceedings of the third workshop on Hot topics in software defined networking, "A resource delegation framework for software defined networks".

