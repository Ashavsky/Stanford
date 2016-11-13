Initial states of g1, g2, and g3:

The current state of the Rngstream g1:
   Cg = { 12345, 12345, 12345, 12345, 12345, 12345 }


The current state of the Rngstream g2:
   Cg = { 3692455944, 1366884236, 2968912127, 335948734, 4161675175, 475798818 }


The current state of the Rngstream g3:
   Cg = { 1015873554, 1310354410, 2249465273, 994084013, 2912484720, 3876682925 }


State of g1 after reset and 35 calls to RandInt (1, 10):
The current state of the Rngstream g1:
   Cg = { 1040577685, 3747037609, 277208355, 1712706441, 627445683, 2408037141 }


   sum of 35 integers in [1, 10] = 186

RandU01 (g1) =   0.079416736150

State of g1 after reset, IncreasedPrecis (1) and 17 calls to RandInt (1, 10):
The current state of the Rngstream g1:
   Cg = { 2633078466, 1040577685, 3747037609, 3152585150, 1712706441, 627445683 }


State of g1 after IncreasedPrecis (0) and 1 call to RandInt
The current state of the Rngstream g1:
   Cg = { 1040577685, 3747037609, 277208355, 1712706441, 627445683, 2408037141 }


State of g1 after reset, IncreasedPrecis (1) and 17 calls to RandU01:
The current state of the Rngstream g1:
   Cg = { 2633078466, 1040577685, 3747037609, 3152585150, 1712706441, 627445683 }


State of g1 after IncreasedPrecis (0) and 1 call to RandU01
The current state of the Rngstream g1:
   Cg = { 1040577685, 3747037609, 277208355, 1712706441, 627445683, 2408037141 }


Sum of first 100 output values from stream g3:
   sum =  53.985901592315


Reset stream g3 to its initial seed.
First 5 output values from stream g3:
  0.728509786197
  0.965587282284
  0.996184130480
  0.114988416181
  0.973145419130

Reset stream g3 to the next Substream, 4 times.
First 5 output values from stream g3, fourth Substream:
  0.173454539636
  0.032863516089
  0.264316912968
  0.305940360212
  0.547630303518

Reset stream g2 to the beginning of Substream.
 Sum of 100000 values from stream g2 with double precision:  50098.22414959
 Sum of 100000 antithetic output values from stream g3:  50017.22317750

SetPackageSeed to seed = { 1, 1, 1, 1, 1, 1 }

Create an array of 4 named streams and write their full state
The Rngstream Poisson:
Anti = False
IncPrec = False
   Ig = { 1, 1, 1, 1, 1, 1 }
   Bg = { 1, 1, 1, 1, 1, 1 }
   Cg = { 1, 1, 1, 1, 1, 1 }

The Rngstream Laplace:
Anti = False
IncPrec = False
   Ig = { 2662865579, 741857976, 4206142246, 3352832365, 2519202871, 655500294 }
   Bg = { 2662865579, 741857976, 4206142246, 3352832365, 2519202871, 655500294 }
   Cg = { 2662865579, 741857976, 4206142246, 3352832365, 2519202871, 655500294 }

The Rngstream Galois:
Anti = False
IncPrec = False
   Ig = { 3784663252, 802042081, 160569404, 3391851556, 2150317468, 54240022 }
   Bg = { 3784663252, 802042081, 160569404, 3391851556, 2150317468, 54240022 }
   Cg = { 3784663252, 802042081, 160569404, 3391851556, 2150317468, 54240022 }

The Rngstream Cantor:
Anti = False
IncPrec = False
   Ig = { 3276123839, 3170955788, 1470482105, 884064067, 1017894425, 16401881 }
   Bg = { 3276123839, 3170955788, 1470482105, 884064067, 1017894425, 16401881 }
   Cg = { 3276123839, 3170955788, 1470482105, 884064067, 1017894425, 16401881 }

--------------------------------------
Final Sum = 23.170963181665

--------------------------------------
Benchmark comparison with random.random:
random.random module: 1.122631073
rngStream module: 1.30329108238