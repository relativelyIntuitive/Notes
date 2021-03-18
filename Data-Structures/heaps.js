class Heap{
    constructor(){
        this.heap = [null]
    }
    insert(val){
        //your code here
        this.heap.push(val)
        let current = this.heap.length -1 
        let parent = Math.floor(current/2)
        console.log(`current child index is ${current}. Parent index is ${parent} when the val we are inserting is ${val}`)
        while(this.heap[current] < this.heap[parent]){ //while heap is out of min-heap-order
            [this.heap[current], this.heap[parent]] = [this.heap[parent], this.heap[current]]
            current = parent
            parent = Math.floor(current/2)
        } 
        console.log(`this is what the heap looks like after inserting ${val}: ----> ${this.heap}`)
        return this.heap
    }
}


heap1 = new Heap
heap1.insert(5)
heap1.insert(15)
heap1.insert(3)
heap1.insert(12)
heap1.insert(1)


// [null, 5, 15]