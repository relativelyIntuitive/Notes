// WRITE A FUNCITON THAT RETURNS WHETER THE GIVEN ARRAY HAS A BALANCE POINT BETWEEN THE VALUES
// WHERE ONE SIDE IS EQUAL TO THE OTHER

function balancePoint(arr) {
    let leftSum = 0;
    let rightSum = 0;
    for (let i = 0; i < arr.length; i++) {
        leftSum = 0;
        rightSum = 0;
        for (let l = i; l > -1; l--) {
            leftSum += arr[l];
        }
        for (let r = i + 1; r < arr.length; r++) {
            rightSum += arr[r];
        }
        if (rightSum == leftSum) {
            console.log('true');
            return true;
        }
    }
    console.log('false');
    return false;
}

balancePoint([1,2,3,4,10]) // TRUE
// -- > 1+2+3+4 = 10    10 == 10 so balanced
balancePoint([1,2,3,2,1])  // FALSE
balancePoint([1,2,4,1,2,3,2,1])  // TRUE
balancePoint([2,2])  // TRUE

// =============================================
// =============================================


// WRITE A FUNCITON THAT RETURNS WHETER THE GIVEN ARRAY HAS A BALANCE INDEX
// WHERE THE SUM ON EITHER SIDE OF THE INDEX IS THE SAME

function balanceIndex(arr) {
    if (arr.length < 3) {
        console.log('no index!')
        return 'no index!';
    }
    let leftSum = 0;
    let rightSum = 0;
    for (let i = 0; i < arr.length; i++) {
        leftSum = 0;
        rightSum = 0;
        for (let l = i - 1; l > -1; l--) {
            leftSum += arr[l];
        }
        for (let r = i + 1; r < arr.length; r++) {
            rightSum += arr[r];
        }
        if (rightSum == leftSum) {
            console.log(i);
            return i;
        }
    }
    console.log('false');
    return false;
}

balanceIndex([4,2,2,6])

   // console.log(balanceIndex([-2,5,7,0,3]))
   // console.log(balanceIndex([9,9]))
   // console.log(balanceIndex([4,2,2,6]))
   // console.log(balanceIndex([9,1,9]))
   // console.log(balanceIndex([1,8,1,2,3,4]))