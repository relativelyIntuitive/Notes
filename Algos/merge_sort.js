let x = [5,8,12]
let y = [3,4,9,18]


function merge(arr1, arr2){
    let results = []
    let i = 0
    let j = 0
    while (i< arr1.length && j< arr2.length ){
        if (arr1[i] < arr2[j]){
            results.push(arr1[i])
            i++
        }
        else{
            results.push(arr2[j])
            j++
        }
    }
    while (i< arr1.length){
        results.push(arr1[i])
        i++
    }

    while (j< arr2.length){
        results.push(arr2[j])
        j++
    }

    // console.log("i is:", i)
    // console.log("j is:", j)
    // console.log(results)
    return results
}

// merge(x,y)
// results: [3, 4, 5, 8, 9, 12, 18 ]
// i: 0 -> 1  -> 2 -> 3
// j: 0-> 1 -> 2 -> 3


// [13, 4, -5, 8]
// [13,4]    [-5, 8]

// [13] [4] [-5] [8]
// [4,13] [-5, 8]
// [-5, 4, 8, 13]



//break up the array into halves until you have arrays that have one element
//once you have smaller sorted arrays, merge those arrays with other sorted arrays until you are back to the full length of the original array

function mergeSort(arr){
    //base case
    if (arr.length <= 1 ){
        return arr
    }
    let midpoint = Math.floor(arr.length/2)
    let left = mergeSort(arr.slice(0, midpoint))
    let right = mergeSort(arr.slice(midpoint, arr.length))

    return merge(left, right)

}

console.log(mergeSort([13, 4, -5, 8, 5,10, 3]))


// var fruits = ["Banana", "Orange", "Lemon", "Apple", "Mango"];
// var leftside = fruits.slice(0,2);
// var rightside = fruits.slice(2, fruits.length)
// console.log(leftside)
// console.log(rightside)