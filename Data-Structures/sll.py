# -SLL:
        # (Single-linked-list)
        # -Works with any language that supports classes
        # -way to link objects together via class attributes without putting them in a sequence instance together

class Node:
    def __init__(self, valueInput):
        self.value = valueInput
        self.next = None


class SLL:
    def __init__(self):
        # the attribute self.head represents the first node in a sequence of nodes
        self.head = None

    def add_to_back(self, valueInput):
        # create a new node
        newnode = Node(valueInput)
        if self.head == None:
            self.head = newnode
        else:
            # create a runner and loop it until it points to the last node in the SLL
            runner = self.head
            while runner.next != None:
                # increments runner by one node
                runner = runner.next
            runner.next = newnode
        return self

    def add_to_front(self, valueInput):
        newNode = Node(valueInput)
        newNode.next = self.head
        self.head = newNode
        return self

    def min_to_front(self):
        runner = self.head
        min = self.head.value
        while runner != None:
            if runner.value < min:
                min = runner.value
            runner = runner.next
        runner = self.head
        while runner.next != None:
            if runner.next.value == min:
                minNode = runner.next
                runner.next = minNode.next
                minNode.next = self.head
                self.head = minNode
                return self
            runner = runner.next
        return self

    def contains(self, valToFind):
        runner = self.head
        while runner!= None:
            if runner.value == valToFind:
                return True
            runner = runner.next
        return False

    def prepend_val(self, valToInsert, valToFind):
        # create a new node
        newnode = Node(valToInsert)
        # RUNNN through the SLL and find the node containing the valToFind
        if self.head.value == valToFind:
            self.add_to_front(valToInsert)
            return self
        runner = self.head
        while runner != None:
            if runner.next.value == valToFind and runner.next != None:
                before = runner
                break
            runner = runner.next
        print("the found node's value is this", before.value)
        newnode.next = before.next
        before.next = newnode
        return self

    def maxToBack(self):
        runner = self.head
        max = self.head.value

        while runner != None:
            if runner.value > max:
                max = runner.value
            runner = runner.next

        runner = self.head
        maxNode = runner
        while runner.next != None:
            if runner.next.value == max:
                maxNode = runner.next
                runner.next = maxNode.next
                maxNode.next = None
            runner = runner.next
        runner.next = maxNode
        
        return self

    def remove_back(self):
        if self.head != None:
            runner = self.head
            # check if there is only one node in the list, then remove that node
            if self.head.next == None:
                self.head = None
                return self
            while runner.next.next != None:
                runner = runner.next
            runner.next = None
            return self
        else:
            print('there are no nodes in here fam')

    def remove_negatives(self):
        while self.head.value < 0:
            self.head = self.head.next
        runner = self.head
        while runner.next !=  None:
            if runner.next.value < 0:
                runner.next = runner.next.next
            runner = runner.next
        print("hi")
        if runner.value < 0:
            self.remove_back()
        return self

    def display(self):
        runner = self.head
        while runner != None:
            print(runner.value)
            runner = runner.next


sll1 = SLL()
