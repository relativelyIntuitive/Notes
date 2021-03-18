function toHex(num){
    var remainders = [];
    var hexchars = '0123456789ABCDEF'
    var output = ""
    //loop until the num hits 0--> do that process of dividing by 16 and finding the remainder
    while(num>0){ 
        //find the remainder
        remainder = num % 16
        remainders.push(remainder) //push the remainder to array to store it for later
        num = Math.floor(num/16)
    }

    console.log(remainders)

    for(var i = remainders.length-1; i>=0; i--){
        output += hexchars[remainders[i]]
    }
    return "0x"+ output

}


console.log(toHex(479)) //'0x1df'