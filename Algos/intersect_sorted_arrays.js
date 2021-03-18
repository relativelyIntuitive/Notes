//set [4,8,10,55]
//multiset [3,5,5,10,12,12,12]

//efficiently combine two sorted arrays into an array containing the sorted multiset intersection of the two.


// [1,2,3,3,4,4,6]  [2,3,4,4,6] 

// [2,3,4,4,6]

// [1,2,2,2,7] [-2,2,6,6,7] result = [2,2,7] 


const intersect = (arr1, arr2) =>{
    let result = [];
    let i = 0;
    let j = 0;

    while ( i < arr1.length && j < arr2.length ) {
        if (arr1[i] == arr2[j]) {
            result.push(arr1[i])
            i+=1
            j+=1
        }
        else if (arr1[i]<arr2[j]) {
            i+=1
        }
        else {
            j+=1
        }
    }
    return result
}

// [1,2,3,3,4,4,6]     [2,3,4,4,6]
// i=0->1->2->3->4->5->6->7
// j=0 ->1->2->3->4->5
// [2,3,4,4,6]


console.log(intersect([1,2,3,3,4,4,6] , [2,3,4,4,6]))