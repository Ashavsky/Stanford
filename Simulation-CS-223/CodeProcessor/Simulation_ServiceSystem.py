import rngStream

import math

class SimulationEngine:
    def __init__(self, interArrivalType):
        #state
        self.systemState = []
        self.nextArrivalClock = 0
        self.nextDischargeClock = 0
        self.time = 0
        self.totalJobCount = 0
        self.totalJobHoldLength = 0
        self.lastZ = -100 #used for parts c and d, used value Z will never be to be safe
        #streams
        self.arrivalType = interArrivalType
        self.dischargeStream1 = rngStream.RngStream("discharge stream")
        self.dischargeStream2 = rngStream.RngStream("discharge stream")
        self.normalVariableStream1 = rngStream.RngStream("normal var stream 1")
        self.normalVariableStream2 = rngStream.RngStream("normal var stream 2")
        if interArrivalType == "poisson":
            self.arrivalStream = rngStream.RngStream("poisson stream")
        elif interArrivalType == "weibull":
            self.arrivalStream = rngStream.RngStream("weibull stream")
        elif interArrivalType == "weibullC":
            self.arrivalStream = rngStream.RngStream("weibull C stream")
        elif interArrivalType == "partD":
            self.arrivalStream = rngStream.RngStream("part D stream")


            self
    def MoveToNextEventAndExecute(self):
        if(self.time == 0):
            self.systemState.append(1)
            self.StartNewArrivalClock()
            self.StartNewDischargeClock()
            self.totalJobCount += 1

        if self.nextDischargeClock == 0 or self.nextDischargeClock > self.nextArrivalClock:
            closestEventTime = self.nextArrivalClock
            closestEventType = "arrival"
            self.nextDischargeClock -= self.nextArrivalClock
        else:
            closestEventTime = self.nextDischargeClock
            closestEventType = "discharge"
            self.nextArrivalClock -= self.nextDischargeClock

        #clock always moves
        self.time += closestEventTime

        #add to hold time count
        jobCountDuringTimeRange = len(self.systemState)
        holdTime = closestEventTime * jobCountDuringTimeRange
        self.totalJobHoldLength += holdTime

        self.ExecuteEvent(closestEventType)

    def ExecuteEvent(self, eventType):
        if eventType == "arrival":
            self.StartNewArrivalClock()
            self.systemState.append(1)
            if len (self.systemState) == 1:
                self.StartNewDischargeClock()
            self.totalJobCount += 1
        elif eventType == "discharge":
            if len(self.systemState) > 0:
                self.systemState.pop(0)
                self.StartNewDischargeClock()

    def StartNewArrivalClock(self):
        if self.arrivalType == "poisson":
            clocktime = RandomVariableGenerator.GeneratePoisson(self.arrivalStream)
        elif self.arrivalType == "weibull":
            clocktime = RandomVariableGenerator.GenerateWeibull(self.arrivalStream)
        elif self.arrivalType == "weibullC":
            clocktime = RandomVariableGenerator.GenerateWeibullC(self.arrivalStream)
        elif self.arrivalType == "partD":
            zList = RandomVariableGenerator.CreateTwoRandomVariables(self.normalVariableStream1, self.normalVariableStream2)
            clocktime = RandomVariableGenerator.GeneratePartD(zList, self.lastZ)
            self.lastZ = zList[0]
        elif self.arrivalType == "partE":
            zList = RandomVariableGenerator.CreateTwoRandomVariables(self.normalVariableStream1, self.normalVariableStream2)
            clocktime = RandomVariableGenerator.GeneratePartE(zList, self.lastZ)
            self.lastZ = zList[0]
        else:
            clocktime = 0 #not used, but ensures all paths return a value
        self.nextArrivalClock = clocktime

    def StartNewDischargeClock(self):
        #clocktime = RandomVariableGenerator.GenerateTriangularService(self.dischargeStream)
        clocktime = RandomVariableGenerator.GenerateTriangularService(self.dischargeStream1, self.dischargeStream2)
        self. nextDischargeClock = clocktime

class RandomVariableGenerator:
    @staticmethod
    def GenerateTriangularService(stream1, stream2):
        u1 = stream1.RandU01()
        u2 = stream2.RandU01()
        x = .99 * (u1 + u2)
        return x

    @staticmethod
    def GeneratePoisson(stream):
        u = stream.RandU01()
        x = -math.log(u)
        return x

    @staticmethod
    def GenerateWeibull(stream):
        u = stream.RandU01()
        x = math.pow(-math.log(u), 1/2.1013491) /.8856899
        return x

    @staticmethod
    def GenerateWeibullC(stream):
        u = stream.RandU01()
        x = math.pow(-math.log(u), 1/.5426926) /1.7383757
        return x

    @staticmethod
    def CreateTwoRandomVariables(stream1, stream2):
        u1 = stream1.RandU01()
        u2 = stream2.RandU01()
        x1 = math.sqrt(-2 * math.log(u1)) * math.cos(2 * math.pi * u2)
        x2 = math.sqrt(-2 * math.log(u1)) * math.sin(2 * math.pi * u2)
        return [x1, x2]

    @staticmethod
    def GeneratePartD(zList, previousZ):
        #Random Variables
        zN = zList[0]
        zNMinus1 = previousZ if not previousZ == -100 else zList[1]
        #Generate yN
        yN = (zN - zNMinus1) / math.sqrt(2)
        #Generate xN
        if yN > 0:
            xN = -math.log(RandomVariableGenerator.NormalDistributionFuncion(yN))
        else:
            xN = -math.log(1 - RandomVariableGenerator.NormalDistributionFuncion(-1 * yN))

        return xN

    @staticmethod
    def GeneratePartE(zList, previousZ):
        # Random Variables
        zN = zList[0]
        zNMinus1 = previousZ if not previousZ == -100 else zList[1]
        # Generate yN
        yN = (zN + zNMinus1) / math.sqrt(2)
        # Generate xN
        if yN > 0:
            xN = -math.log(RandomVariableGenerator.NormalDistributionFuncion(yN))
        else:
            xN = -math.log(1 - RandomVariableGenerator.NormalDistributionFuncion(-1 * yN))

        return xN

    @staticmethod
    def NormalDistributionFuncion(val):
        y = RandomVariableGenerator.FunctionY(val)
        a1 = 0.4361836
        a2 = -0.1201676
        a3 = 0.9472980
        valReturn = 1 - (a1 * y + a2 * math.pow(y,2) + a3 * math.pow(y,3)) / math.sqrt(2 * math.pi) * math.exp(-1 * math.pow(val,2) / 2)
        return valReturn

    @staticmethod
    def FunctionY(val):
        y = 1 / (1 + .33267 * val)
        return y