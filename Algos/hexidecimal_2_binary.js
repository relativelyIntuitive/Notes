
function hexToBin(hex) {
    let result = "";
    let value = hex;
    while (value > 0) {
        let remainder = value % 2;
        result += remainder;
        value = Math.floor(value / 2);
    }
    result = result.split("").reverse().join("")
    result = "0b" + result;
    console.log(result);
}

hexToBin(25);

