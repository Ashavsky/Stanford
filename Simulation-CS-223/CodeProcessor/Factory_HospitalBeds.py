from Estimator import Estimator
from Simulation_HospitalBeds import SimulationEngine

if __name__ == "__main__":
    print("Part i results")
    estimator1 = Estimator(.01, 2.575, "99%")
    for rep in range(7785):
        simEngine1 = SimulationEngine(5,4,.68, 0.08, 0.25)
        while(not (simEngine1.minBedScu == -1 and simEngine1.minBedIcu == -1)):
            eventPostion = simEngine1.MoveToNextEvent()
            simEngine1.ExecuteEvent(eventPostion)
        val = simEngine1.dayCount
        # print(val)
        estimator1.process_next_val(val)

    print("Estimate/Mean: %.3f" %estimator1.get_mean())
    print("with", estimator1.get_conf_interval())
    print("Est. # of repetitions for +/-", estimator1.epsilon, "accuracy: ", estimator1.get_num_trials(True))

    print("Part ii results")
    estimator2 = Estimator(.01, 2.575, "99%")
    for rep in range(1000):
        simEngine2 = SimulationEngine(5, 4, .68, 0.08, 0.25)
        while (simEngine2.dayCount < 31):
            eventPostion = simEngine2.MoveToNextEvent()
            simEngine2.ExecuteEvent(eventPostion)
        val = simEngine2.dayCountFilledPast7 / simEngine2.dayCount
        # print(val)
        estimator2.process_next_val(val)

    print("Estimate/Mean: %.3f" % estimator2.get_mean())
    print("with", estimator2.get_conf_interval())
    print("Est. # of repetitions for +/-", estimator2.epsilon, "accuracy: ", estimator2.get_num_trials(True))

    print("Part iii results")
    estimator3 = Estimator(.01, 2.575, "99%")
    for rep in range(1000):
        simEngine3 = SimulationEngine(5, 4, .68, 0.08, 0.25)
        while (simEngine3.dayCount < 56):
            eventPostion = simEngine3.MoveToNextEvent()
            simEngine3.ExecuteEvent(eventPostion)
        val = 1 if simEngine3.bedFilled else 0
        # print(val)
        estimator3.process_next_val(val)

    print("Estimate/Mean: %.3f" % estimator3.get_mean())
    print("with", estimator3.get_conf_interval())
    print("Est. # of repetitions for +/-", estimator3.epsilon, "accuracy: ", estimator3.get_num_trials(True))

    print("Part iv results")
    estimator4 = Estimator(.01, 2.575, "99%")
    for rep in range(1000):
        simEngine3 = SimulationEngine(5, 4, .68, 0.08, 0.25)
        while (simEngine3.dayCount < 31):
            eventPostion = simEngine3.MoveToNextEvent()
            simEngine3.ExecuteEvent(eventPostion)
        val = simEngine3.turnedAwayPersons
        # print(val)
        estimator4.process_next_val(val)

    print("Estimate/Mean: %.3f" % estimator4.get_mean())
    print("with", estimator4.get_conf_interval())
    print("Est. # of repetitions for +/-", estimator4.epsilon, "accuracy: ", estimator4.get_num_trials(True))

