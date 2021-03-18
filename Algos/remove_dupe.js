
// GIVEN AN ARRAY, RETURN ARRAY WITHOUT DUPES
// [1,2,1,3,4,2] => [1,2,3,4]

// CHALLENGE: RETURN SAME ARRAY

function removeDupe(arr) {
    var newarr = [];
    for (num in arr) {
        if (!newarr.includes(arr[num])) {
            newarr.push(arr[num]);
        }
    }
    return newarr;
}


console.log(removeDupe([1, 2, 1, 3, 4, 2]));
// console.log(removeDupe([1,2,3]))
