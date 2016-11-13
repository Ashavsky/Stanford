from Estimator import Estimator
from Simulation_ServiceSystem import SimulationEngine

if __name__ == "__main__":
    repcount = 26000
    print("Part a results")
    estimator1 = Estimator(.01, 2.575, "99%")
    for rep in range(repcount):
        simEngine1 = SimulationEngine("poisson")
        while(simEngine1.time < 1000):
            simEngine1.MoveToNextEventAndExecute()
        val = simEngine1.totalJobHoldLength / 1000
        #print(val)
        estimator1.process_next_val(val)

    print("Estimate/Mean: %.3f" %estimator1.get_mean())
    print("with", estimator1.get_conf_interval())

    print("")
    print("Part b results")
    estimator2 = Estimator(.01, 2.575, "99%")
    for rep in range(repcount):
        simEngine2 = SimulationEngine("weibull")
        while (simEngine2.time < 1000):
            simEngine2.MoveToNextEventAndExecute()
        val = simEngine2.totalJobHoldLength / 1000
        # print(val)
        estimator2.process_next_val(val)

    print("Estimate/Mean: %.3f" % estimator2.get_mean())
    print("with", estimator2.get_conf_interval())

    print("")
    print("Part c results")
    estimator3 = Estimator(.01, 2.575, "99%")
    for rep in range(repcount):
        simEngine3 = SimulationEngine("weibullC")
        while (simEngine3.time < 1000):
            simEngine3.MoveToNextEventAndExecute()
        val = simEngine3.totalJobHoldLength / 1000
        # print(val)
        estimator3.process_next_val(val)

    print("Estimate/Mean: %.3f" % estimator3.get_mean())
    print("with", estimator3.get_conf_interval())

    print("")
    print("Part d results")
    estimator4 = Estimator(.01, 2.575, "99%")
    for rep in range(repcount):
        simEngine4 = SimulationEngine("partD")
        while (simEngine4.time < 1000):
            simEngine4.MoveToNextEventAndExecute()
        val = simEngine4.totalJobHoldLength / 1000
        # print(val)
        estimator4.process_next_val(val)

    print("Estimate/Mean: %.3f" % estimator4.get_mean())
    print("with", estimator4.get_conf_interval())

    print("")
    print("Part e results")
    estimator5 = Estimator(.01, 2.575, "99%")
    for rep in range(repcount):
        simEngine5 = SimulationEngine("partD")
        while (simEngine5.time < 1000):
            simEngine5.MoveToNextEventAndExecute()
        val = simEngine5.totalJobHoldLength / 1000
        # print(val)
        estimator5.process_next_val(val)

    print("Estimate/Mean: %.3f" % estimator5.get_mean())
    print("with", estimator5.get_conf_interval())
