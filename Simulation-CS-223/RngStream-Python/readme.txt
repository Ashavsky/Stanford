AN OBJECT-ORIENTED RANDOM-NUMBER PACKAGE IN PYTHON 
WITH MANY LONG STREAMS AND SUBSTREAMS

Overview
====================================

This package implements the RngStream class for generating pseudo-random uniform numbers, with a few additional tools. For efficiency, the package has been ported from C using Cython.

A generator computes a stream of seeds that are converted to pseudo-random uniform numbers on (0,1). The stream is further divided up into very long substreams, which can be useful for synchronizing streams when applying variance reduction techniques such as common random numbers or antithetic variates. Substreams also provide an easy way to switch between pilot runs and production runs of a simulation program.

The backbone generator is the combined multiple recursive generator (CMRG) Mrg32k3a proposed in 

L’Ecuyer, P. 1999. “Good parameters and implementations for combined multiple recursive random number generators”. Operations Research, 47(1), 159–164,

implemented in 64-bit floating-point arithmetic. This backbone generator has period length 2^191. The values of V , W, and Z mentioned below are 2^51, 2^76, and 2^127, respectively.

The seed of the RNG, and the state of a stream at any given step, are 6-dimensional vectors of 32-bit integers. The default initial seed of the package is
[12345, 12345, 12345, 12345, 12345, 12345]. Internally, Ig stores the initial seed for the entire stream, Bg stores the initial seed for the current sub stream, and Cg stores the current seed, i.e., the current state of the generator.

====================================
Installing the Generator
====================================

The generator has been pre-compiled for both 64 bit Windows (Python 3.5) and OS X, (Python 2.7 and Python 3.5). Simply copy the appropriate .pyd file (Windows) or .so file (OS X) to the same directory as your simulation program. Rename the file to rngStream.pyd or rngStream.so as appropriate. Then, in your simulation program, add the line:

import rngStream

and the generator is ready to use. Ask the TAs if you need to compile the generator
for other platforms. (Note: compiling for Windows 64 bit + Python 2.7 is VERY hard
to do: you need to use an older compiler, because the code must be compiled using the SAME C compiler that is used to compile Python 2.7 itself.)

To test whether the generator works, put the .pyd or .so file in the same directory as rstestPyXX.py (where XX = 27 or 35) and execute the command:

python rstestPyXX.py

The results should match those in the file retest.res

====================================
Using the Generator
=====================================

The test file rstestPyXX.py gives examples of how to use the generator in a Python program. Various features of the generator are described below.

Setting the Package seed (Optional):
====================================

The command

rngStream.SetPackageSeed(s)

initializes the seed for the entire package. This will be the seed (initial state) of the first stream. If this procedure is not called, the default initial seed is [12345, 12345, 12345, 12345, 12345, 12345]. If it is called, the first 3 values of the seed must all be less than m1 = 4294967087, and not all 0; and the last 3 values must all be less than m2 = 4294944443, and not all 0.

Creating a New Stream:
======================

The command to create an object that will generate a stream of uniform random numbers is

ug1 = rngStream.RngStream(“unigen1”)

This creates a generator ug1 whose name is “unigen1”. (Names can be useful when generating debugging output as in the file rstestPyXX.res discussed above.) We will use this generator as a running example.

Constructing a generator as above creates a new stream, initializes its seed Ig, and sets Bg and Cg equal to Ig. The seed Ig is equal to the initial seed of the package given by setPackageSeed if this is the first created stream, otherwise it is Z steps ahead of the seed of the most recently created stream. The switches for “antithetic generation” and “increased precision” are initially set to False (see below).

Other Class Methods:
====================

1) ug1.ResetStartStream()

Reinitializes the ug1 stream to its initial state: Cg and Bg are set to Ig.

—————————

2) ug1.ResetStartSubstream()

Reinitializes the ug1 stream to the beginning of its current substream: Cg is set to Bg.

—————————

3) ug1.ResetNextSubstream()

Reinitializes the stream to the beginning of its next substream: Ng is computed, and Cg and Bg are set to Ng.

—————————

4) ug1.SetAntithetic(a)

After this procedure is called with a = True (or, equivalently, a = 1), the stream starts generating antithetic variates, i.e., 1 − U instead of U, until the procedure is called again with a = False (or a = 0).

—————————

5) ug1.IncreasedPrecis(incp)

After calling this procedure with incp = True (or incp = 1), each call to the ugh generator will return a uniform random number with more bits of resolution (53 bits if the machine follows the IEEE-754 floating-point standard) instead of 32 bits, and will advance the state of the ug1 stream by 2 steps instead of 1. More precisely, in the non-antithetic case, the instruction

u = ug1.RandU01()

will be equivalent to 

u = (ug1.RandU01() + ug1.RandU01(g) * fact) % 1.0

where the constant fact is equal to 2^(−24). This also applies when calling RandU01 indirectly (e.g., via RandInt, etc.). By default, or if this procedure is called again with incp = false, each call to RandU01 for this stream advances the state by 1 step and returns a number with 32 bits of resolution.

—————————

6) ug1.SetSeed(s)

Sets the initial seed Ig of this stream to the vector s = [s0, s1, s2, s3, s4, s5].5]. This vector (implemented as a Python list) should contain valid seed values as described in SetPackageSeed. The state of the stream is then reset to
this initial seed. The states and seeds of the other streams are not modified. As a result, after calling this method, the initial seeds of the streams are no longer spaced Z values apart. We discourage the use of this method; proper use of the Reset methods is preferable.

—————————

7) ug1.GetState(s)

Sets s equal to the current state Cg of this stream. This is a vector of 6 integers represented in floating point. This method is convenient if we want to save the state for subsequent use.

—————————

8) ug1.WriteState()

Prints (to standard output) the name and current state Cg of ug1.

—————————

9) ug1.WriteStateFull()

Prints (to standard output) the name of ug1 and the values of all its internal variables.

—————————

10) ug1.RandU01()

Returns a pseudo-random number from the uniform distribution over the interval (0, 1), using the ugh stream, after advancing its state by one step. Normally, the returned number has 32 bits of resolution, in the sense that it is always a multiple of 1/(2^32 − 208). However, if the precision has been increased by calling IncreasedPrecis for this stream, the resolution is higher and the stream state advances by two steps.

—————————

11) ug1.randInt(i, j)

Returns a (pseudo)random number from the discrete uniform distribution over the integers {i, i + 1, . . . , j}, using the ugh stream. Makes one call to randU01.
