words = [
    ["quick", "lazy"],
    ["brown", "red", "grey"],
    ["fox", "dog"]
]

function allCombinations(words){
    let result = [];
    function helper(comb, i){
        if(i==words.length){
            result.push(comb)
        }
        else{
            for(var j = 0; j< words[i].length; j++){
                helper(comb + words[i][j] + " ", i + 1 )
            }
        }
    }
    helper("", 0)
    return result
}

console.log(allCombinations(words))