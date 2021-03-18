class Node:
    def __init__(self, val):
        self.value = val
        self.next = None

class SLStack:
    def __init__(self):
        self.top = None
    
    def push(self, val):
        # adds val to our stack
        newNode = Node(val)
        newNode.next = self.top
        self.top = newNode
        return self

    def pop(self):
        # remove and return the top val
        prevTop = self.top
        self.top = self.top.next
        return prevTop.value

    def top(self):
        # return(don't remove) the stack's top value
        return self.top.value

    def contains(self, val):
        # return whether given val is within the stack
        runner = self.top
        while runner != None:
            if runner.value == val:
                return True
            runner = runner.next
        return False

    def isEmpty(self):
        # return whether the stack is empty
        return self.top == None
    
    def size(self):
        # return the number of nodes in the stack
        count = 0
        runner = self.top
        while runner != None:
            count += 1
            runner = runner.next
        return count

    def printAll(self):
        runner = self.top
        while runner != None:
            print(runner.value)
            runner = runner.next