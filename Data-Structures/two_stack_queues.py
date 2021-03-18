
class TwoStackQueue:
    def __init__(self):
        self.s1 = SLStack()
        self.s2 = SLStack()

    def enqueue(self, val):
        # add to stack 1
        self.s1.push(val)
        return self

    def dequeue(self):
        # pop everything into stack 2 IF S2 is empty
        # pop off top of s2
        if self.s1.isEmpty() and self.s2.isEmpty():
            print("can't dequeue an empty queue")
            return False
        else:
            if not self.s2.isEmpty():
                return self.s2.pop()
            else:
                while not self.s1.isEmpty():
                    self.s2.push(self.s1.pop())
        return self.s2.pop(