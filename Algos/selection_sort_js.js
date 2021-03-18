//find the max, then swap it once with the last element, moving inward

function selectSort(inputArr) {
    for(let i = 0; i < inputArr.length; i++){
        let bigIdx = 0;
        for(let j=0; j < inputArr.length - i; j++) {
            if (inputArr[j] > inputArr[bigIdx]) {
                bigIdx = j;
            }
        }
        let temp = inputArr[bigIdx];
        inputArr[bigIdx] = inputArr[inputArr.length - 1 - i]
        inputArr[inputArr.length - 1 - i] = temp
    }
    return inputArr
}

let arr = [8,3,5,7]
console.log(selectSort(arr));
