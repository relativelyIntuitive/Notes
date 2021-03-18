
let arr1 = [3,0,5,2,6,1]

function insertionSort(arr){
    for(let i = 0; i<arr.length; i++){
        // storing element we want to check and insert into the right place in temp
        let temp = arr[i]
        let j = i
        //seeing if the element we want to sort has an element to the left that is less than it, if so enter the while loop 
        while(j>0 && temp < arr[j-1]){
            //replace the element at the current index with the element to the left of it
            arr[j] = arr[j-1]
            j = j-1
        }
        //at this point, we have found a position where its appropriate to insert the temp
        arr[j] = temp

    }
    console.log(arr)
    return arr
}


insertionSort(arr1)

// arr = [0,1,2,3,5,6]
// length of array = 6
// i = 0-> 1 -> 2 -> 3 -> 4 -> 5
// temp = 1
//j = 0 -> 1 -> 0 -> 2 -> 3 -> 2 -> 1 -> 4 -> 5> 4