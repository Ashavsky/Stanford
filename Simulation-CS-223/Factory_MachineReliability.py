from Estimator import Estimator
from Simulation_MachineReliability import SimulationEngine
from math import log, sqrt, ceil

if __name__ == "__main__":
    estimator1 = Estimator(.01, 2.76, "99%")
    listOfSamples = [[0]*100,[0]*100,[0]*100,[0]*100,[0]*100]
    largeListOfSamples = [0] * 500
    listOfQuartiles = [0] *5
    listOfMeans = [0] * 5
    for section in range(5):
        for rep in range(100):
            simEngine = SimulationEngine(3,6)
            while(simEngine.timePassed <= 100):
                eventId = simEngine.MoveToNextEvent()
                simEngine.ExecuteEvent(eventId)
            val = simEngine.repairmanoneTimePassed
            listOfSamples[section][rep] = val
            largeListOfSamples[section*100+rep] = val

    i = 0
    #get the overall average
    largeListOfSamples.sort()
    largeQ1 = largeListOfSamples[124]
    largeQ3 = largeListOfSamples[374]
    largeQuartileRange = largeQ3 - largeQ1

    #get the sections
    for list in listOfSamples:
        list.sort()
        q1 = list[24]
        q3 = list[74]
        iQuartileRange = q3 - q1
        iQuartileRangeJackKnifed = 5*largeQuartileRange - 4 * iQuartileRange
        listOfQuartiles[i] = iQuartileRangeJackKnifed

        mean = sum(list) / float(len(list))
        listOfMeans[i] = mean
        estimator1.process_next_val(iQuartileRange)
        i += 1

    avgIQuartileRange = sum(listOfQuartiles) / float(len(listOfQuartiles))

    alpha_j = (1/5)*(listOfQuartiles[0]+listOfQuartiles[1]+listOfQuartiles[2]+listOfQuartiles[3]+listOfQuartiles[4])
    v_j = (1/4)*((listOfQuartiles[0] - alpha_j)**2 + (listOfQuartiles[1] - alpha_j)**2 + (listOfQuartiles[2] - alpha_j)**2 + (listOfQuartiles[3] - alpha_j)**2 + (listOfQuartiles[4] - alpha_j)**2)
    t = 2.776

    print('Mean: ')
    print(alpha_j)
    print('95% CI: ')
    print([alpha_j - t*sqrt(v_j/5), alpha_j + t*sqrt(v_j/5)])

