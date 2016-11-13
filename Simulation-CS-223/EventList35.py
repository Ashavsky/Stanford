#Sample Code Provided by Professor Haas (not written by me but used in the Machine Reliability Simultion)

# Implementation of event list in python using a heap data structure
# To test the implementation, run the script and at the prompt, enter commands such as:
# p 1 2.0    (adds event with id=1 and event time=2.0 to event list)
# c 3        (cancels event with id=3 by removing from event list)
# n          (pops the next event off of the event list)
# q          (quits the program)
# 
# 
# This (rather simple) implementation assumes that
# - event ids in the event list are unique, could be string or integers
# - event times are all unique (i.e., unique trigger events with no ties)
# - all speeds (in the GSMP sense) are equal to 1

import heapq  
 
class EventList:
    """Event list using a heap data structure"""
    def __init__(self):
        self.event_list = []
        self.event_record = {}

    def add_event(self, event_id, time):
        """Adds event_id with time in the event list"""
        if event_id in self.event_record:
            self.cancel_event(event_id)
        new_event = [time, event_id]
        self.event_record[event_id] = new_event
        heapq.heappush(self.event_list, new_event)

    def cancel_event(self, event_id):
        """Cancels event having id = event_id"""
        if event_id in self.event_record:
            to_remove = self.event_record.pop(event_id)
            to_remove[-1] = "<canceled>"
        else:
            raise KeyError("Event %s not in list." %str(event_id))

    def next_event(self):
        """Return tuple containing (event_id, time)"""
        if self.event_list:
            next_event = heapq.heappop(self.event_list)
            if next_event[-1] != "<canceled>":
                del self.event_record[next_event[-1]]
                return next_event[-1], next_event[0]
            return self.next_event()
        else:
            raise KeyError("Popping from an empty event list.")

    def reset(self):
        self.event_list = []
        self.event_record = {}

    def __str__(self):
        el = sorted(self.event_list)
        return "\n".join([str(x) for x in el])


if __name__ == "__main__":
    def print_status(event_list):
        print("Event List: [time of event, event id]")
        print(event_list)
    
    usage = ("\nUsage: \n"
             "p event_id time (add event to list) \n"
             "c event_id (cancel event) \n"
             "n (print next event) \n"
             "q (quit the demo) \n"
             )
    print(usage)
    cmd = input("Command: ")
    event_list = EventList()
    while cmd != "q":
        cmd = cmd.strip().split()
        if cmd[0] == "p":
            if len(cmd) != 3: raise ValueError("Invalid command. See usage.")
            event_list.add_event(cmd[1], cmd[2])
            print_status(event_list)
        elif cmd[0] == "c":
            if len(cmd) != 2: raise ValueError("Invalid command. See usage.")
            event_list.cancel_event(cmd[1])
            print_status(event_list)
        elif cmd[0] == "n":
            if len(cmd) != 1: raise ValueError("Invalid command. See usage.")
            print("Next event: ", event_list.next_event())
            print_status(event_list)
        elif cmd[0] == "q":
            print("Quitting")
        else:
            raise ValueError("Invalid command. See usage.")
        cmd = input("Command: ")
                                               
                                    
    
