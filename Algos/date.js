function findTheDate(str, numba) {
    // given a string representing a day of the week ('Mon', 'Fri') and a number. determine which day of the week would be that many days later
    
    let x;
    switch(str){
        case "Sun":
            x=0;
            break;
        case "Mon":
            x=1;
            break;
        case "Tue":
            x=2;
            break;
        case "Wed":
            x=3;
            break;
        case "Thur":
            x=4;
            break;
        case "Fri":
            x=5;
            break;
        case "Sat":
            x=6;
            break;
    }
    let futureDay
    dayRequest=(x+numba)%7
    switch(dayRequest){
        case 0:
            futureDay="Sun";
            break;
        case 1:
            futureDay="Mon";
            break;
        case 2:
            futureDay="Tue";
            break;
        case 3:
            futureDay="Wed";
            break;
        case 4:
            futureDay="Thur";
            break;
        case 5:
            futureDay="Fri";
            break;
        case 6:
            futureDay="Sat";
            break;
    }
    
    console.log(futureDay)
    return futureDay;
}

function findDate(str,  numba) {
    // given a date, and number, calculate the date that you will get in that many days
    let arr = (str.split("-"))
    date=new Date()
    date.setMonth(arr[0]-1)
    date.setDate(Number(arr[1])+numba)
    date.setFullYear(arr[2])
    // date.setDate(numba)
    console.log(date)
    return date
}