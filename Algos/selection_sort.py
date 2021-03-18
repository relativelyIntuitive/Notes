# iterates through loop, moving the minimum to the front each time and then iterating through again, 
# starting at the next index from the minimum 

x = [2,6,5,3,9,8,1,7,4]

def selectionSort(input_list):
    for i in range(len(input_list) - 1):
        bigIdx = 0
        for j in range(len(input_list) - i):
            if input_list[j] > input_list[bigIdx]:
                bigIdx = j
        input_list[bigIdx], input_list[len(input_list) - 1 - i] = input_list[len(input_list) - 1 - i], input_list[bigIdx]
    print(input_list)
    return input_list

selectionSort(x)