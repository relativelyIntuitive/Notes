// “ String: Dedupe
// Remove duplicate characters (case-sensitive) including punctuation. Keep only the last instance of each character. Given "Snaps! crackles! pops!", return "Snrackle ops!”

// Excerpt From: Martin Puryear. “Algorithm Challenges: E-book for Dojo Students.” iBooks. 

// "Snaps! crackles! pops!"

// "Snrackle! ops"

function dedupe(str){
    //your code here
    let tempArr = [];
    for(var i= 0; i< str.length; i++){
        //if the character at index i is unique char (it is not in the temparr), we will push it into the arry
        if (tempArr.includes(str[i])!= true){
            tempArr.push(str[i])
        }
        else{
            for(var j = 0; j<tempArr.length; j++){
                if(tempArr[j] == str[i]){
                    tempArr.splice(j, 1)
                    break
                }
                
            }
            tempArr.push(str[i])

        }
    } 
    console.log(tempArr)
    let finalresult = ""
    for (var i =0; i< tempArr.length; i++){
        finalresult += tempArr[i]
    }
    console.log(finalresult)
}

dedupe("Snaps! crackles! pops!")

function dedupeShoutOutcorbinfosho(str){
    let tempArr = []
    for(var i = str.length-1; i>=0; i--){
        if (tempArr.includes(str[i])!= true){
            tempArr.push(str[i])
        }
    }
    let result = tempArr.reverse()
    console.log(result)
    let finalresult = ""
    for (var i =0; i< tempArr.length; i++){
        finalresult += tempArr[i]
    }
    console.log(finalresult)
}

dedupeShoutOutcorbinfosho("Snaps! crackles! pops!")