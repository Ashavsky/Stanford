import rngStream

germe = [1, 1, 1, 1, 1, 1]

g1 = rngStream.RngStream("g1")
g2 = rngStream.RngStream("g2")
g3 = rngStream.RngStream("g3")

gar = [0,0,0,0]

print "Initial states of g1, g2, and g3:\n"
g1.WriteState()
g2.WriteState()
g3.WriteState()

sum = g2.RandU01() + g3.RandU01()

for i in range(12345): g2.RandU01()

g1.ResetStartStream()

sumi = 0
for i in range(35): sumi += g1.RandInt(1, 10)
print "State of g1 after reset and 35 calls to RandInt (1, 10):"
g1.WriteState()
print "   sum of 35 integers in [1, 10] = %d\n"%sumi

sum += sumi / 100.0

print "RandU01 (g1) = %16.12f\n"%g1.RandU01()

sum3 = 0.0
g1.ResetStartStream()
g1.IncreasedPrecis(1)
sumi = 0

for i in range(17): sumi += g1.RandInt(1, 10)
print "State of g1 after reset, IncreasedPrecis (1) and 17 calls to RandInt (1, 10):"
g1.WriteState()
g1.IncreasedPrecis(0)
g1.RandInt(1, 10)
print "State of g1 after IncreasedPrecis (0) and 1 call to RandInt"
g1.WriteState()

sum3 = sumi / 10.0

g1.ResetStartStream()
g1.IncreasedPrecis(1)
for i in range(17): sum3 += g1.RandU01()
print "State of g1 after reset, IncreasedPrecis (1) and 17 calls to RandU01:"
g1.WriteState()
g1.IncreasedPrecis(0)
g1.RandU01()
print "State of g1 after IncreasedPrecis (0) and 1 call to RandU01"
g1.WriteState()

sum += sum3 / 10.0

sum3 = 0.0
print "Sum of first 100 output values from stream g3:"
for i in range(100): sum3 += g3.RandU01()
print "   sum = %16.12f\n"%sum3

sum += sum3 / 10.0

print "\nReset stream g3 to its initial seed."
g3.ResetStartSubstream()
print "First 5 output values from stream g3:"
for i in range(5): print "%16.12f"%g3.RandU01()
sum += g3.RandU01()

print "\nReset stream g3 to the next Substream, 4 times."
for i in range(4): g3.ResetNextSubstream()
print "First 5 output values from stream g3, fourth Substream:"
for i in range(5): print "%16.12f"%g3.RandU01()
sum += g3.RandU01()

print "\nReset stream g2 to the beginning of Substream."
g2.ResetStartSubstream()
g2.IncreasedPrecis(1)
sum3 = 0.0
for i in range(100000): sum3 += g2.RandU01()
print " Sum of 100000 values from stream g2 with double precision:" + "%16.8f"%sum3
sum += sum3 / 10000.0
g2.IncreasedPrecis(0)

g3.SetAntithetic(1)
sum3 = 0.0
for i in range(100000): sum3 += g3.RandU01()
print " Sum of 100000 antithetic output values from stream g3:" + "%16.8f"%sum3
sum += sum3 / 10000.0

del g1
del g2
del g3

print "\nSetPackageSeed to seed = { 1, 1, 1, 1, 1, 1 }"
rngStream.SetPackageSeed(germe)

print "\nCreate an array of 4 named streams and write their full state"
gar[0] = rngStream.RngStream("Poisson")
gar[1] = rngStream.RngStream("Laplace")
gar[2] = rngStream.RngStream("Galois")
gar[3] = rngStream.RngStream("Cantor")

for i in range(4): gar[i].WriteStateFull()

for i in range(4):sum += gar[i].RandU01()

for i in reversed(range(4)): del gar[i]

print "--------------------------------------"
print "Final Sum = %.12f\n"%sum

print "--------------------------------------"
print "Benchmark comparison with random.random:"

import random
import time

n = 10000000
t1 = time.time()
for i in range(n):
    random.random()
print "random.random module:", time.time() - t1

g = rngStream.RngStream("g")
t2 = time.time()
for i in range(n):
    g.RandU01()
print "rngStream module:", time.time() - t2


