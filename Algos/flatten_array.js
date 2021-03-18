
function flatten(inputArr){
    let results = [];

    for(val of inputArr){
        if (typeof val == "object"){
            for(subVal of val){
                results.push(subVal);
            }
        } else {
            results.push(val);
        }
    }

    console.log(results);
    return results;
}

flatten([4, 5 , 6, [4, 5, 65], 4, 87, [ 2, 5, 43]]);


// recursive solution???
// doesn't work yet

// function flatten(inputArr, results = []){
//     for(val of inputArr){
//         if (typeof val == "object"){
//             return flatten(val, results)
//         } else {
//             results.push(val);
//         }
//     }

//     console.log(results);
//     return results;
// }