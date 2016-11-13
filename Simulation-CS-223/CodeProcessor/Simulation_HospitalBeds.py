import rngStream

import math


class SimulationEngine:
    def __init__(self, bedsSCU, bedsICU, arrivalProb, a, mu):
        #tracking values
        self.dayCount = 0
        self.dayCountFilledPast7 = 0
        self.bedFilled = False
        self.turnedAwayPersons = 0
        #parameters
        self.bedsScu = bedsSCU
        self.bedsIcu = bedsICU
        self.arrivalProbability = arrivalProb
        self.aParamDistribution = a
        self.averageLengthOfStay = mu
        #created objects
        self.minBedScu = 1
        self.minBedIcu = bedsICU
        self.systemState = [0] * (bedsSCU + bedsICU)
        self.eventsList = []
        #streams
        self.arrivalStream = rngStream.RngStream("arrival stream")
        self.interarrivalStream = rngStream.RngStream("inter-arrival stream")
        self.lengthOfStayStream = rngStream.RngStream("length of stay stream")
        self.criticalStream = rngStream.RngStream("time until critical event stream")

    def MoveToNextEvent(self):
        if(self.dayCount == 0):
            self.StartNewArrivalClock()

        closestEventTime = 100000 #arbitrary value, should never be needed, just set to be safe, should be way above actual values
        closestEventPosition = 0

        #find closest event set it to be returned
        for idx, val in enumerate(self.eventsList):
            if val.time < closestEventTime:
                closestEventTime = val.time
                closestEventPosition = idx

        #move all clocks
        for event in self.eventsList:
            event.time = event.time - closestEventTime

        #clock always moves
        self.dayCount += closestEventTime

        #clock moves if filled past 7
        if sum(self.systemState) >= 7:
            self.dayCountFilledPast7 += closestEventTime

        #check if beds filled
        if not self.bedFilled:
            if self.minBedIcu == -1 and self.minBedScu == -1:
                self.bedFilled = True

        return closestEventPosition

    def ExecuteEvent(self, eventPosition):
        eventClock = self.eventsList.pop(eventPosition)

        if eventClock.type == "arrival":
            self.ExecuteArrival()
            self.StartNewArrivalClock()
        elif eventClock.type == "discharge":
            self.ExecuteDischarge(eventClock)
        elif eventClock.type == "critical":
            self.ExecuteCritical(eventClock)

        self.SetMinBedNumbers()

    def StartNewArrivalClock(self):
        clocktime = RandomVariableGenerator.GenerateA(self.interarrivalStream, self.aParamDistribution)
        arrivalClock = EventClock("arrival", clocktime, "not assigned", 0) #arrival clock doesn't know where bed will be
        self.eventsList.append(arrivalClock)

    def StartNewDischargeClock(self, hospital, bed):
        clocktime = RandomVariableGenerator.GenerateLengthOfStay(self.lengthOfStayStream, self.averageLengthOfStay)
        dischargeClock = EventClock("discharge", clocktime, hospital, bed)
        self.eventsList.append(dischargeClock)

    def StartNewCriticalClock(self, hospital, bed):
        clocktime = RandomVariableGenerator.GenerateCriticalEventTimer(self.criticalStream)
        dischargeTime = 1000 #just put a random number here that I know wont be exceeded
        eventPosition = -1

        #only start a critical clock if time < regular discharge time
        for index ,event in enumerate(self.eventsList):
            if event.bedPosition == bed and event.time < dischargeTime and event.type == "discharge":
                dischargeTime = event.time
                eventPosition = index

        if float(clocktime) < dischargeTime:
            criticalClock = EventClock("critical", clocktime, hospital, bed)
            self.eventsList.append(criticalClock)
            self.eventsList.pop(eventPosition) #remove discharge since critical event will happen first

    def ExecuteArrival(self):
        if self.arrivalStream.RandU01() <= self.arrivalProbability:
            if(self.minBedScu != -1):
                self.systemState[self.minBedScu - 1] = 1
                self.StartNewDischargeClock("scu", self.minBedScu)
                self.StartNewCriticalClock("scu", self.minBedScu)
                # print("arrived in SCU and taking bed #" + str(self.minBedScu))
            else:
                self.turnedAwayPersons += 1
                # print("no space in SCU, going to another hospital")
        else:
            if (self.minBedIcu != -1):
                self.systemState[self.minBedIcu - 1] = 1
                self.StartNewDischargeClock("icu", self.minBedIcu)
                self.StartNewCriticalClock("icu", self.minBedIcu)
                # print("arrived in ICU and taking bed #" + str(self.minBedIcu))
            else:
                self.turnedAwayPersons += 1
                # print("no space in ICU, going to another hospital")

    def ExecuteDischarge(self, event):
        #technically don't need "if" here, just added so can output to screen
        if event.hospitalType == "scu":
            self.systemState[event.bedPosition - 1] = 0
            # print("discharged from SCU and leaving bed #"  + str(event.bedPosition))
        else:
            self.systemState[event.bedPosition - 1] = 0
            # print("discharged from ICU and leaving bed #" + str(event.bedPosition))

    def ExecuteCritical(self, event):
        if self.minBedIcu != -1: #check if full
            self.systemState[event.bedPosition - 1] = 0
            #execute same code as arrival to ICU
            self.systemState[self.minBedIcu - 1] = 1
            self.StartNewDischargeClock("icu", self.minBedIcu)

    def SetMinBedNumbers(self):
        for i in range(self.bedsScu):
            if self.systemState[i] == 0:
                self.minBedScu = i + 1
                break
            else:
                self.minBedScu = self.bedsScu + 1
        for j in range(self.bedsScu, self.bedsScu + self.bedsIcu):
            if self.systemState[j] == 0:
                self.minBedIcu = j + 1
                break
            else:
                self.minBedIcu = self.bedsIcu + 1

        if self.minBedScu == self.bedsScu + 1:
            self.minBedScu = -1

        if self.minBedIcu == self.bedsIcu + 1:
            self.minBedIcu = -1

class EventClock:
    def __init__(self, clockType, clockTime, hospital, bed):
        self.type = clockType
        self.time = clockTime
        self.hospitalType = hospital
        self.bedPosition = bed

class RandomVariableGenerator:
    @staticmethod
    def GenerateA(stream, a):
        u = stream.RandU01()
        a = math.sqrt(u * math.pow(a,2))
        return a

    @staticmethod
    def GenerateLengthOfStay(stream, mu):
        uOne = stream.RandU01()
        uTwo = stream.RandU01()
        length = -1 * math.log(uOne * uTwo * mu)
        return length

    @staticmethod
    def GenerateCriticalEventTimer(stream):
        uOne = stream.RandU01()
        uTwo = stream.RandU01()
        time = uOne + uTwo
        return time
