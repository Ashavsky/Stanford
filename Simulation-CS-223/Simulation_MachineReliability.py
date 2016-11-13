import rngStream

import math

from EventList35 import EventList


class SimulationEngine:
    def __init__(self, n, m):
        #tracking values
        self.timePassed = 0
        self.repairmanoneTimePassed = 0
        #parameters
        self.minFunctionalMachines = n
        self.totalMachines = m
        #created objects
        self.systemState = [0] * (m + 2)
        self.eventList = EventList()
        #streams
        self.machineStopStream = rngStream.RngStream("stream for generating breakdown times")
        self.repairStream = rngStream.RngStream("repair completion stream")
        #set initial values
        self.InitializeSystem()

    def InitializeSystem(self):
        #set initial state
        for i in range(self.minFunctionalMachines):
            self.systemState[i] = 2
        for j in range(self.minFunctionalMachines, self.totalMachines):
            self.systemState[j] = 1
        #set initial clocks. Note, modified problem to have machine count be 0 based (so 0 to M-1)
        for id in range(self.minFunctionalMachines):
            clockSetting = RandomVariableGenerator.GenerateStopTime(self.machineStopStream)
            self.eventList.add_event(id + 1, clockSetting)

    def MoveToNextEvent(self):
        nextEvent = self.eventList.next_event()
        isRepairmanBusy = self.systemState[self.totalMachines] > 0
        if isRepairmanBusy and nextEvent[1] <= 100:
            self.repairmanoneTimePassed += nextEvent[1] - self.timePassed
        self.timePassed = nextEvent[1]
        return nextEvent[0]

    def ExecuteEvent(self, eventId):
        L1 = self.systemState[self.totalMachines]
        L2 = self.systemState[self.totalMachines + 1]
        failureClock = RandomVariableGenerator.GenerateStopTime(self.machineStopStream)
        #machine failure
        if eventId <= self.totalMachines:
            self.systemState[eventId -1] = 0
            if self.MinSpareId() > 0:
                id = self.MinSpareId()
                self.systemState[self.MinSpareId() - 1] = 2
                self.eventList.add_event(id, failureClock + self.timePassed)
            if L1 == 0 and L2 == 0:
                repairmanChoice = RandomVariableGenerator.GenerateRepairmanChoice(self.repairStream)
                if repairmanChoice == 1:
                    self.systemState[self.totalMachines] = eventId
                    self.eventList.add_event(self.totalMachines + 1, self.timePassed+failureClock)
                else:
                    self.systemState[self.totalMachines + 1] = eventId
                    self.eventList.add_event(self.totalMachines + 2, self.timePassed+failureClock)
            elif L1 == 0:
                self.systemState[self.totalMachines] = eventId
                self.eventList.add_event(self.totalMachines + 1, self.timePassed+failureClock)
            elif L2 == 0:
                self.systemState[self.totalMachines + 1] = eventId
                self.eventList.add_event(self.totalMachines + 2, self.timePassed+failureClock)
        #completion of repair
        else:
            repairmanId = eventId - self.totalMachines
            if self.SumRunningMachines() < self.minFunctionalMachines:
                if repairmanId == 1:
                    self.systemState[L1-1] = 2
                    self.eventList.add_event(L1, failureClock + self.timePassed)
                else:
                    self.systemState[L2-1] = 2
                    self.eventList.add_event(L2, failureClock + self.timePassed)
            else:
                if repairmanId == 1:
                    self.systemState[L1 - 1] = 1
                else:
                    self.systemState[L2 - 1] = 1
            if self.MinBrokenMachine() > 0:
                self.systemState[eventId - 1] = self.MinBrokenMachine()
                repairClock = RandomVariableGenerator.GenerteRepairTime(self.repairStream)
                self.eventList.add_event(eventId, self.timePassed + repairClock)
            else:
                self.systemState[eventId - 1] = 0

    def MinSpareId(self):
        for i in range(self.totalMachines):
            if self.systemState[i] == 1:
                return i + 1
        return 0

    def MinBrokenMachine(self):
        for i in range(self.totalMachines):
            if self.systemState[i] == 0 and self.systemState[self.totalMachines] != i + 1 and self.systemState[self.totalMachines + 1] != i + 1:
                return i + 1
        return 0

    def SumRunningMachines(self):
        sum = 0
        for i in range(self.totalMachines):
            if self.systemState[i] == 2:
                sum += 1
        return sum


class RandomVariableGenerator:
    @staticmethod
    def GenerateStopTime(stream):
        u = stream.RandU01()
        return -3 * math.log(u)

    @staticmethod
    def GenerteRepairTime(stream):
        u = stream.RandU01()
        if u < .5:
            return math.sqrt((8 / 9) * u)
        else:
            return (4 / 3) - math.sqrt((8 / 9) - (8 / 9) * u)

    @staticmethod
    def GenerateRepairmanChoice(stream):
        u = stream.RandU01()
        if(u <= .5):
            return 1
        else:
            return 2
