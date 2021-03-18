// SEARCH THROUGH A SPRTED ARRAY BY DIVIDING THE ARRAY IN HALF AFTER EACH SEARCH
// ONCE TARGET IS LOCATED RETURN TRUE
// DO BOTH RECURSIVELY AND ITERATIVELY

function binarySearch(arr, target){
    // YOUR CODE HERE
    if (arr.length<2){
        if(target==arr[0]){
            return true
        }
    }
    let middle=Math.floor(arr.length/2) //Find the middle
    
    while(middle!=0){// 
        if(target==arr[middle]){
            return true
        }
        else if(target>arr[middle]){
            arr=arr.slice(middle, arr.length)
            }
        else{
            arr= arr.slice(0,middle)
        }
        middle=Math.floor(arr.length/2)
    }
    return false
}

function binarySearchRecursive(arr, target){
// YOUR CODE HERE
    if (arr.length==0){
        return "You need an array doodoo"
    }
    if(arr.length==1){
        if(target==arr[0]){
            return true
        }
        else{
            return false
        }
    }
    middle=Math.floor(arr.length/2)
    if(target==arr[middle]){
        return true
    }
    else if(target>arr[middle]){
        arr=arr.slice(middle, arr.length)
        }
    else{
        arr= arr.slice(0,middle)
    }
    return binarySearchRecursive(arr, target)
}

//console.log(binarySearch([1,2,3,4,5,6,7,8,9,10], 9));
// ---> 1 2 3 4 5  |  6 7 8 9 10
// ---> 6 7  |  8 9 10
// ---> 8  [9]  10  (found)

console.log(binarySearchRecursive([1,2,3,4,5,6,7,8,9,10,12,13], 10))    
console.log(binarySearchRecursive([0, 2, 4, 6, 8, 10, 12, 14, 16],  9))    
console.log(binarySearchRecursive([0, 2, 4, 6, 8, 10, 12, 14, 16], 16 ))    
console.log(binarySearchRecursive([0, 2, 4, 6, 8, 12, 14, 16], 8 ))    
console.log(binarySearchRecursive([8], 8 ))  