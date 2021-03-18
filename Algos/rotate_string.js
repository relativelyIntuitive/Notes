function rotateString(str, num){
    // let result = str.slice(0, str.length-num) //gives me from index 2 until the end
    // let anothaone = str.slice(str.length-num, str.length) //gives me the last 2
    // console.log(result)
    // console.log(anothaone)
    // console.log(anothaone + result)
    if (num > str.length){
        num = (num % str.length)
    }

    let result = str.slice(str.length-num, str.length) + str.slice(0, str.length-num)
    return result
}


console.log(rotateString("abcdefg", 6))


function isRotation(str1, str2){
    if (str1.length != str2.length){
        return false
    }
    else{
        for(let i = 0; i< str1.length; i++){
            let rotatedstr1 = rotateString(str1, i)
            if (rotatedstr1 == str2){
                return true
            }
        }
        return false
    }


}

console.log(isRotation("abcdefg", "fgabcde")) //true
console.log(isRotation("abcdefg", "fgaacde")) //false
console.log(isRotation("abcdefg", "ffgabcde")) //false