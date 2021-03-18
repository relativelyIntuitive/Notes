function stringToArray(strInput){
    let result = [];
    let word = ""
    for (var i = 0; i< strInput.length; i++){
        if (strInput[i] != " "){
            word += strInput[i]
        }
        else if (strInput[i] == " "){
            console.log("word is:", word)
            if (word.length > 0){
                result.push(word)
            }
            word = ""
        }
    }
    console.log(result)
   

}


stringToArray("  Life   is not a drill! ")