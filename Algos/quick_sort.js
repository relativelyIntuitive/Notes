//partition an array

//it will help to accept three arguments: an array, start index, and an end index (these can default to 0 and array.length-1, respectively)

//swap the starting element(i.e the pivot) with the pivot index
//return the pivot index

function pivot(arr, start= 0, end= arr.length-1){
    function swap(array, i, j){
        var temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }
    //grab the pivot from the start of the array
    var pivot = arr[start]
    //store the current pivot index in a variable (this will keep track of where the pivot should end up)
    var swapindex = start
    //loop through the array from the start until the end
    for(var i = start+1; i<=end; i++ ){
        //if the pivot is greater than the current element, increment the pivot index variable and then swap the current element with the element at the pivot index
        if(pivot > arr[i]){
            swapindex++
            swap(arr, swapindex, i)
        }
    }
    swap(arr, start, swapindex)
    // console.log(arr)
    return swapindex;
}

// console.log(pivot([6,2,5,8,-7,9,-10,100,12]))

// pivot = 6
// swapidx = 0->1->2->3 ->4
// i= 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
// [-10,2,5,-7,6,9,8,100,12]

function quicksort(arr, left = 0, right= arr.length -1){
    if (left < right) {

        let pivotIndex = pivot(arr, left, right)
        //left side
        quicksort(arr, left, pivotIndex-1)
        //right side
        quicksort(arr, pivotIndex+1, right)
    }
    return arr
}

// if (left > right) {
//     return arr
// }
// else{
//     let pivotIndex = pivot(arr, left, right)
//     //left side
//     quicksort(arr, left, pivotIndex-1)
//     //right side
//     quicksort(arr, pivotIndex+1, right)
// }
// }
console.log(quicksort([6,2,5,8,-7,9,-10,100,12]))