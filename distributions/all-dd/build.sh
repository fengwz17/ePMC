#!/bin/bash
. ../auxiliary.sh

assemble_begin all-dd
prepare_plugin util
prepare_plugin value-basic
prepare_plugin dd
prepare_plugin expression-basic
prepare_plugin graph
prepare_plugin algorithm
prepare_plugin graphsolver
prepare_plugin automata
prepare_plugin jani-model
prepare_plugin prism-format
prepare_plugin command-check
prepare_plugin command-explore
prepare_plugin command-expression2automaton
prepare_plugin command-help
prepare_plugin command-lump
prepare_plugin propertysolver-propositional
prepare_plugin propertysolver-operator
prepare_plugin propertysolver-reward
prepare_plugin propertysolver-filter
prepare_plugin propertysolver-pctl
prepare_plugin propertysolver-multiobjective
prepare_plugin propertysolver-ltl-lazy
prepare_plugin dd-beedeedee
prepare_plugin dd-buddy
prepare_plugin dd-cacbdd
prepare_plugin dd-cudd
prepare_plugin dd-cudd-mtbdd
prepare_plugin dd-jdd
prepare_plugin dd-meddly
prepare_plugin dd-sylvan
prepare_plugin dd-sylvan-mtbdd
prepare_plugin automaton-determinisation
prepare_plugin graphsolver-iterative
assemble_end all-dd
