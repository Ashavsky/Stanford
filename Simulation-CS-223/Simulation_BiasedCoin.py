# Simulates the gambling game of Lecture # 1
#
# MS&E 223: Simulation
#
# Usage: python gamble.py -n k [-t -i -d m]
# where -n (--num) k: Number of replications given by k
# -t (--trial): Trial run to get required number of replications
# -i (--confint): Calculate the CI of the point estimator
# -d (--debug) m: Verbose output needed, levels from 0-2
#

import rngStream
from math import sqrt
from argparse import ArgumentParser

class Estimator:
    """ Computes point estimates and confidence intervals """
    def __init__(self, z, conf_str):
        self.k = 0  # number of values processed so far
        self.sum = 0.0  # running sum of values
        self.v = 0.0  # running value of (k-1)*variance
        self.z = float(z) # quantile for normal Confidence Interval
        self.conf_str = conf_str # string of form "xx%" for xx% Confidence Interval

    def reset(self):
        self.k = 0
        self.sum = 0
        self.v = 0

    def process_next_val(self, value):
        self.k += 1
        if self.k > 1:
            diff = self.sum - (self.k - 1) * value
            self.v += diff/self.k * diff/(self.k-1)
        self.sum += value

    def get_variance(self):
        if self.k > 1:
            var = self.v/(self.k-1)
        else:
            # raise RuntimeError("Variance undefined for number of observations = 1")
            var = 0 #trying to avoid exception here
        return var

    def get_mean(self):
        return self.sum/self.k if self.k > 1 else 0

    def get_conf_interval(self):
        hw = self.z * sqrt(self.get_variance()/self.k)
        point_est = self.get_mean()
        c_low = point_est - hw
        c_high = point_est + hw
        return self.conf_str + " Confidence Interval [ %.4f" %c_low +  ", %.4f" %c_high + "]"

    def get_num_trials(self, epsilon, relative=True):
        var = self.get_variance()
        width = self.get_mean() * epsilon if relative else epsilon
        return int((var * self.z * self.z)/(width * width))

class GameEngine:
    def __init__(self, epsilonVal, confidenceInterval, confidenceIntervalString):
        self.est = Estimator(confidenceInterval, confidenceIntervalString)  # 95% CI
        self.epsilon = epsilonVal  # Determines the width of the CI
        self.unigenA = rngStream.RngStream("coin flip A")  # Instantiate the random number generator
        self.unigenB = rngStream.RngStream("coin flip B")
        self.unigenC = rngStream.RngStream("coin flip B")
        # self.unigen.ResetNextSubstream()

    def play_game_a(self):
        capital = 0
        coinTossCount = 100
        for i in range(coinTossCount):
            if self.unigenA.RandU01() <= .495:
                capital += 1
            else:
                capital += -1
        self.est.process_next_val(capital)
        return capital

    def play_game_b(self):
        capital = 0
        coinTossCount = 100
        for i in range(coinTossCount):
            if(capital % 3 == 0): #use coin 2
                if self.unigenB.RandU01() <= .095:
                    capital += 1
                else:
                    capital += -1
            else: #use coin 3
                if self.unigenB.RandU01() <= .745:
                    capital += 1
                else:
                    capital += -1
        self.est.process_next_val(capital)
        return capital

    def play_game_c(self):
        capital = 0
        coinTossCount = 100
        for i in range(coinTossCount):
            if self.unigenC.RandU01() <= 0.5: #play game A
                if self.unigenA.RandU01() <= .495:
                    capital += 1
                else:
                    capital += -1
            else: #play game B
                if (capital % 3 == 0):  # use coin 2
                    if self.unigenB.RandU01() <= .095:
                        capital += 1
                    else:
                        capital += -1
                else:  # use coin 3
                    if self.unigenB.RandU01() <= .745:
                        capital += 1
                    else:
                        capital += -1

        self.est.process_next_val(capital)
        return capital



