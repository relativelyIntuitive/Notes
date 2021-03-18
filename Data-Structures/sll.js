class Node{
    constructor(val){
        this.val = val;
        this.next = null;
    }
}


class SLL{
    constructor(){
        this.head = null;
    }

    addToFront(val){
        //make a new node
        //set the next value of the node to current head
        //set the head to the new node
        let newNode = new Node(val);
        newNode.next = this.head;
        this.head = newNode;
    }

    addToBack(val){
        if(!this.head) {
            return -1;
        }
        // EDGE CASE: What happens if there is nothing in the list?
        let runner = this.head;
        while(runner.next != null){
            runner = runner.next;
        }
        runner.next = new Node(val);
    }

    reverseList() {
        var itemBeforeRunner = null;
        var itemAfterRunner = null;
        var runner = this.head;

        while (runner != null) {
            itemAfterRunner = runner.next;
            runner.next = itemBeforeRunner;
            itemBeforeRunner = runner;
            runner = itemAfterRunner;     
        }
        this.head = itemBeforeRunner;
      }

    hasCycle() {
        //RETURN TRUE OR FALSE IF SINGLY LINKED LIST HAS LOOP
        let loopCount = 0;
        var runner = this.head;
        while(runner != null){
            console.log("here");
            loopCount++;
            console.log(`${runner.val} ->`);
            runner = runner.next;
            console.log(loopCount);
        }
        if(loopCount > this.nodeCount) {
            console.log("true");
            return true;
        }
        console.log("false");
    }

    printMe(){
        let runner = this.head;
        while(runner != null){
            console.log(`${runner.val} ->`);
            runner = runner.next;
        }
    }

    printValuesForward(){
        let runner = this.head;
        while(runner != null){
            console.log(`${runner.val} ->`);
            runner = runner.next;
        }
    }

    printValuesBackward(){
        let runner= this.tail;
        while(runner != null){
            console.log(`${runner.val} ->`)
            runner=runner.prev
        }
    }

    addToFront(val){
        let newNode=new Node(val)
        if (this.head==null){
            this.head=newNode
            this.tail=newNode
        }
        else{
            let runner=newNode
            runner.next=this.head
            let prevHead=this.head
            prevHead.prev=runner
            this.head=runner
        }
    }

    addToBack(val){
        let newNode=new Node(val)
        if (this.tail==null){
            this.tail=newNode
            this.head=newNode
            
        }
        else{
            let runner=newNode
            runner.prev=this.tail
            let prevTail=this.tail
            prevTail.next=runner
            this.tail=runner
        }
    }

    removeFromFront(){
        if(this.head==null){
            console.log("Nice try, empty list")
        }
        else if(this.head.next==null){
            this.head=null
            this.tail=null
        }
        else{
            let runner= this.head
            let newHead= runner.next
            newHead.prev=null
            this.head=newHead
        }
    }

    removeFromEnd(){
        if(this.head==null){
            console.log("Nice try, empty list")
        }
        else if(this.head.next==null){
            this.head=null
            this.tail=null
        }
        else{
            let runner= this.tail
            let newTail= runner.prev
            newTail.next=null
            this.tail=newTail
        }
    }

    prependToValue(findvalue, newValue){
        if(this.head==null){
            console.log("Nice try, empty list")
            let arr=[]
        }
        else{
            let runner =this.head;
            //This looks for the value in the array or goes to the end of the list
            while(runner.val!=findvalue||runner==null){
                runner=runner.next
            }
            if (runner==null)
            {
                console.log("The list does not contain the value")
            }
            else if(runner==this.head){
                this.addToFront(newValue)
            }
            else{
                //console.log(runner)
                let newNode = new Node(newValue)
                let prevNode=runner.prev
                prevNode.next=newNode
                newNode.prev= runner.prev
                runner.prev=newNode
                newNode.next=runner
            }
        }
    }

    appendValue(findvalue, newValue) {
        // add a value after a value in a DLL
        if(this.head==null){
            console.log("Nice try, empty list")
        }
        else{
            let runner =this.head;
            //This looks for the value in the array or goes to the end of the list
            while(runner.val!=findvalue||runner==null){
                runner=runner.next
            }
            if (runner==null)
            {
                console.log("The list does not contain the value")
            }
            else if(runner==this.tail){
                this.addToBack(newValue)
            }
            else{
                let newNode = new Node(newValue)
                let nextNode =runner.next
                nextNode.prev =newNode
                newNode.next= runner.next
                runner.next=newNode
                newNode.prev=runner
            }
        }
    }

    removeValue(findvalue){
        // remove a node based on the value
        if(this.head==null){
            console.log("Nice try, empty list")
        }
        else{
            let runner =this.head;
            //This looks for the value in the array or goes to the end of the list
            while(runner.val!=findvalue||runner==null){
                runner=runner.next
            }
            if (runner==null)
            {
                console.log("The list does not contain the value")
            }
            else if(runner==this.head){
                this.removeFromFront()
            }
            else if(runner==this.tail){
                this.removeFromEnd()
            }
            else{
                prevNode=runner.prev
                nextNode=runner.next
                prevNode.next=nextNode
                nextNode.prev=prevNode
            }
        }
    }
}