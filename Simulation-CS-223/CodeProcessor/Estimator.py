from math import sqrt

class Estimator:
    """ Computes point estimates and confidence intervals """
    def __init__(self, epsilonVal, z, conf_str):
        self.k = 0  # number of values processed so far
        self.epsilon = epsilonVal  # Determines the width of the CI
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

    def get_num_trials(self, relative=True):
        var = self.get_variance()
        width = self.get_mean() * self.epsilon if relative else self.epsilon
        return int((var * self.z * self.z)/(width * width))
