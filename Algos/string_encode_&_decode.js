//  String encode
// “You are given a string that may contain sequences of consecutive characters. Create a function to shorten a string by including the character, then the number of times it appears. For "aaaabbcddd", return "a4b2c1d3". If result is not shorter (such as "bb"=>"b2"), return the original string.

function encode(str){
    //your code here
    if (str.length == 0){
        return false
    }
    let output = "";
    let outputletter = str[0];
    let count = 1;
    for(var i = 0; i< str.length; i++){
        if (str[i] == str[i+1]){
            count +=1
        }
        else{
           output += `${outputletter}${count}` 
           outputletter = str[i+1]
           count = 1
        }

    }
    console.log(output)




}

encode("aaabbccccdd") //'a3b2c4d2'
encode("aaabbccccdefg") 



//  String Decode
// Given an encoded string (see above), decode and return it. Given "a3b2c1d3", return "aaabbcddd".”

// Excerpt From: Martin Puryear. “Algorithm Challenges: E-book for Dojo Students.” iBooks. 

function decode(str){
    //your code here
    let output = "";
    for (var i = 0; i< str.length; i+=2){
        
        for (var j = 0; j< str[i+1]; j++ ){ //run j number of times
            output += str[i]
        } 
    }
    console.log(output)

}

decode('a3b2c4d2') //aaabbccccdd