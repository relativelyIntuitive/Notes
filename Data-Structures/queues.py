class Node:
    def __init__(self, valueInput):
        self.value = valueInput
        self.next = None


class Queue:
    def __init__(self):
        self.head = None
        self.tail = None

def queueIsPalindrome(queueInput):
    runner = queueInput.head
    arrayResult = []
    while runner != None:
        arrayResult.append(runner.value)
        runner = runner.next
    print(arrayResult)
    # for i in range(0, 2, 1):
    for i in range(0, len(arrayResult)//2, 1):
        if arrayResult[i] != arrayResult[len(arrayResult)-1-i]:
            return False
    return True

    def enqueue(self, valueInput):
        #create a new node
        newNode = Node(valueInput)
        if self.head == None:
            self.head = newNode
            self.tail = newNode
        else:
            self.tail.next = newNode
            self.tail = self.tail.next
        return self

    def dequeue(self):
        if self.head == None:
            print("nothing in the queue to remove")
            return self
        else:
            self.head = self.head.next
            return self

    def front(self):
        if self.head != None:
            print(self.head.value)
            return self.head.value
        else:
            print("this queue is empty, no objects at the front")
            return None

    def contains(self, valToFind):
        runner = self.head
        while runner!= None:
            if runner.value == valToFind:
                return True
            runner = runner.next
        return False

    def isEmpty(self):
        if self.head == None:
            return True
        else:
            return False

    def size(self):
        count = 0
        runner = self.head
        while runner != None: #while runner is pointing to a node
            count += 1
            runner = runner.next
        print(count)
        return count

    def display(self):
        runner = self.head
        while runner != None:
            print(runner.value)
            runner = runner.next

chikFilaDT = Queue()
chikFilaDT.enqueue("Zavian").enqueue("Davon").enqueue("Veer").dequeue().display()