
// convert an argued binary number to decimal

function binToDec(binNum) {
    //convert the number to a string to allow iteration
    const binString = binNum.toString();
    let result = 0;
    for (let i = 0; i < binString.length; i++) {
        if (binString[i] == 1) {
            result += (2**(binString.length - i - 1));
        }
    }
    console.log(result);
    return result;
}

binToDec(100101110101);

