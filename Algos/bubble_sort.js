// iterates over given list as many times as it takes to sort from least to greatest value

let x = [5, 3, 1, 9, 6, 2, 8, 7, 4];

function bubbleSort(input_arr) {
    for (let i = 0; i < input_arr.length; i++) {
        for (let j = 0; j < input_arr.length - i; j++) {
            if (input_arr[j] > input_arr[j + 1]) {
                let swapTemp = input_arr[j];
                input_arr[j] = input_arr[j + 1];
                input_arr[j + 1] = swapTemp;
            }
        }
    }
    return input_arr
};

console.log(bubbleSort(x));