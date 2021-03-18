// “ Union Sorted Arrays
// Efficiently combine two already-sorted arrays into a new sorted array containing the multiset union. Example: given [1,2,2,2,7] and [2,2,6,6,7], return [1,2,2,2,6,6,7].”

// Excerpt From: Martin Puryear. “Algorithm Challenges: E-book for Dojo Students.” iBooks. 

function union(arr1, arr2) {
    let i = 0
    let j = 0
    let result = []
    while (i < arr1.length || j < arr2.length) {
        if (i >= arr1.length) {
            result = result.concat(arr2.slice(j))
            j = arr2.length
        }
        else if (j >= arr2.length) {
            result = result.concat(arr1.slice(i))
            i = arr1.length
        }
        else if (arr1[i] == arr2[j]) {
            result.push(arr1[i])
            i++
            j++
        }
        else if (arr1[i] > arr2[j]) {
            result.push(arr2[j])
            j++
        }
        else {
            result.push(arr1[i])
            i++
        }
    }
    console.log(result);
    return result;
}

union([1, 2, 2, 5, 5, 5, 7, 9, 9], [1, 1, 1, 2, 2, 5, 7, 7, 10]);

// prints [1, 1, 1, 2, 2, 5, 5, 5, 7, 7, 9, 9, 10]